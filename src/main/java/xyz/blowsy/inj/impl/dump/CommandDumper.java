package xyz.blowsy.inj.impl.dump;

import org.objectweb.asm.*;
import xyz.blowsy.inj.Main;

public class CommandDumper implements Opcodes {

    public static byte[] dump(String injClassName, String cmdClassName) {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;

        // mappings
        final String getCommandName = "func_71517_b", getCommandUsage = "func_71518_a",
                     processCommand = "func_71515_b", canCommandSenderUseCommand = "func_71519_b";

        cw.visit(52, ACC_PUBLIC + ACC_SUPER, cmdClassName, null, "net/minecraft/command/CommandBase", null);

        cw.visitSource("CommandClass.java", null);

        {
            fv = cw.visitField(ACC_PRIVATE, "s", "Ljava/lang/String;", null, null);
            fv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(7, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "net/minecraft/command/CommandBase", "<init>", "()V", false);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(9, l1);
            mv.visitVarInsn(ALOAD, 0);

            // set custom command
            mv.visitLdcInsn(Main.customCommandName);

            mv.visitFieldInsn(PUTFIELD, cmdClassName, "s", "Ljava/lang/String;");
            mv.visitInsn(RETURN);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLocalVariable("this", "L" + cmdClassName + ";", null, l0, l2, 0);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, getCommandName, "()Ljava/lang/String;", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(12, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, cmdClassName, "s", "Ljava/lang/String;");
            mv.visitInsn(ARETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + cmdClassName + ";", null, l0, l1, 0);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, getCommandUsage, "(Lnet/minecraft/command/ICommandSender;)Ljava/lang/String;", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(16, l0);
            mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
            mv.visitLdcInsn("/");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/Object;)Ljava/lang/StringBuilder;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
            mv.visitInsn(ARETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + cmdClassName + ";", null, l0, l1, 0);
            mv.visitLocalVariable("s", "Lnet/minecraft/command/ICommandSender;", null, l0, l1, 1);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, processCommand, "(Lnet/minecraft/command/ICommandSender;[Ljava/lang/String;)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(22, l0);

            // call method InjectionClass.g();
            mv.visitMethodInsn(INVOKESTATIC, injClassName, "g", "()V", false);

            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(23, l1);
            mv.visitInsn(RETURN);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLocalVariable("this", "L" + cmdClassName + ";", null, l0, l2, 0);
            mv.visitLocalVariable("s", "Lnet/minecraft/command/ICommandSender;", null, l0, l2, 1);
            mv.visitLocalVariable("a", "[Ljava/lang/String;", null, l0, l2, 2);
            mv.visitMaxs(0, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, canCommandSenderUseCommand, "(Lnet/minecraft/command/ICommandSender;)Z", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(25, l0);
            mv.visitInsn(ICONST_1);
            mv.visitInsn(IRETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + cmdClassName + ";", null, l0, l1, 0);
            mv.visitLocalVariable("s", "Lnet/minecraft/command/ICommandSender;", null, l0, l1, 1);
            mv.visitMaxs(1, 2);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }

}
