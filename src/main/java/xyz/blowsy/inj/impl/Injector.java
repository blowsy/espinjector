package xyz.blowsy.inj.impl;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;
import xyz.blowsy.inj.Utils;
import xyz.blowsy.inj.impl.dump.CommandDumper;
import xyz.blowsy.inj.impl.dump.InjectionDumper;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;

public class Injector {

    private static JarOutputStream outputStream;
    private static Map<String, ClassNode> classes = new HashMap<>();
    private static long start = 0;

    public static final String CLASS_NAME = InjectionClass.class.getSimpleName(), CMD_CLASS_NAME = CommandClass.class.getSimpleName();

    public static void run(File file) throws Exception {
        Utils.sendLine();
        Utils.sendMessage("&eRunning injector on: &7" + file.getName() + " (" + Utils.getFileSizeKB(file) + " KB)");
        File outputFile = new File(file.getAbsolutePath().replace(".jar", "-new.jar"));
        if (outputFile.exists()) {
            Utils.sendMessage("&eOverriding existing file.");
        }
        start = System.currentTimeMillis();
        outputStream = new JarOutputStream(new FileOutputStream(outputFile));
        JarFile jarFile = new JarFile(file);
        parseInput(jarFile);
        transform();
        compileJar();
        Utils.sendMessage("&eProcess finished in &3" + Utils.longToSeconds(Utils.timeBetween(start, System.currentTimeMillis())) + " &eseconds.");
        Utils.sendMessage("&eNew file: &7" + outputFile.getName() + " (" + Utils.getFileSizeKB(outputFile) + " KB)");
        Utils.sendLine();
    }

    private static void transform() {
        Utils.sendMessage("&eThere is a total of &3" + classes.size() + " &eclasses.");
        final String targetMethodDesc = Utils.classToMethodDesc(FMLInitializationEvent.class);
        final String targetAttribute = Utils.classToAttribute(Mod.EventHandler.class);
        for (ClassNode classNode : classes.values()) {
            for (MethodNode methodNode : classNode.methods) {
                if (methodNode.desc.equals(targetMethodDesc) && methodNode.visibleAnnotations != null) {
                    for (AnnotationNode annNode : methodNode.visibleAnnotations) {
                        if (!annNode.desc.equals(targetAttribute)) continue;
                        foundInit(classNode, methodNode);
                        return;
                    }
                }
            }
        }
        Utils.sendMessage("&cUnable to find initialization event method.");
    }

    private static void foundInit(ClassNode classNode, MethodNode methodNode) {
        Utils.sendMessage("&aLocated initialization event method.");
        // creating new class names
        String injClassName = classNode.name.replace(Utils.getClassName(classNode), CLASS_NAME);
        String cmdClassName = classNode.name.replace(Utils.getClassName(classNode), CMD_CLASS_NAME);
        // injecting InjectionClass
        ClassNode injClass = new ClassNode();
        ClassReader injReader = new ClassReader(InjectionDumper.dump(injClassName, cmdClassName));
        injReader.accept(injClass, 0);
        injClass.name = injClassName;
        classes.put(injClass.name, injClass);
        // injecting CommandClass
        ClassNode cmdClass = new ClassNode();
        ClassReader cmdReader = new ClassReader(CommandDumper.dump(injClassName, cmdClassName));
        cmdReader.accept(cmdClass, 0);
        cmdClass.name = cmdClassName;
        classes.put(cmdClass.name, cmdClass);
        // add InjectionClass call to fml initilization event
        methodNode.instructions.insertBefore(methodNode.instructions.getFirst(), callMethod(injClassName));
    }

    private static InsnList callMethod(String className) {
        InsnList insnList = new InsnList();
        // gets the static field "MinecraftForge.EVENT_BUS"
        insnList.add(new FieldInsnNode(Opcodes.GETSTATIC, "net/minecraftforge/common/MinecraftForge", "EVENT_BUS", "Lnet/minecraftforge/fml/common/eventhandler/EventBus;"));
        // instantiates new class
        insnList.add(new TypeInsnNode(Opcodes.NEW, className));
        insnList.add(new InsnNode(Opcodes.DUP));
        insnList.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, className, "<init>", "()V", false));
        // calls the method "MinecraftForge.EVENT_BUS.register()"
        insnList.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "net/minecraftforge/fml/common/eventhandler/EventBus", "register", "(Ljava/lang/Object;)V", false));
        return insnList;
    }

    private static void parseInput(JarFile jarFile) throws IOException {
        classes.clear();
        jarFile.stream().forEach(entry -> {
            try {
                if (entry.getName().endsWith(".class")) {
                    ClassReader classReader = new ClassReader(jarFile.getInputStream(entry));
                    ClassNode classNode = new ClassNode();
                    classReader.accept(classNode, 0);
                    classes.put(classNode.name, classNode);
                } else if (!entry.isDirectory()) {
                    outputStream.putNextEntry(new ZipEntry(entry.getName()));
                    outputStream.write(toByteArray(jarFile.getInputStream(entry)));
                    outputStream.closeEntry();
                }
            } catch (IOException e) {
                Utils.sendMessage("&cError parsing file components.");
                e.printStackTrace();
            }
        });
        jarFile.close();
    }

    private static void compileJar() throws IOException {
        classes.values().forEach(classNode -> {
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            try {
                classNode.accept(classWriter);
                JarEntry jarEntry = new JarEntry(classNode.name.concat(".class"));
                outputStream.putNextEntry(jarEntry);
                outputStream.write(classWriter.toByteArray());
            } catch (Exception e) {
                Utils.sendMessage("&cError dumping class &3" + Utils.getClassName(classNode) + " &cinto new Jar.");
                e.printStackTrace();
            }
        });
        outputStream.close();
    }

    private static byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[0xFFFF];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }
        outputStream.flush();
        return outputStream.toByteArray();
    }

}
