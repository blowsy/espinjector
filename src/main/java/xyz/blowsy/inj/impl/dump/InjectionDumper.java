package xyz.blowsy.inj.impl.dump;

import org.objectweb.asm.*;

public class InjectionDumper implements Opcodes {

    public static byte[] dump(String injClassName, String cmdClassName) {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        // mappings
        final String registerCommand = "func_71560_a", getMinecraft = "func_71410_x", theWorld = "field_71441_e", thePlayer = "field_71439_g",
                     playerEntities = "field_73010_i", deathTime = "field_70725_aQ", lastTickPosX = "field_70142_S", posX = "field_70165_t",
                     renderPartialTicks = "field_74281_c", getRenderManager = "func_175598_ae", viewerPosX = "field_78730_l", lastTickPosY = "field_70137_T",
                     posY = "field_70163_u", viewerPosY = "field_78731_m", lastTickPosZ = "field_70136_U", posZ = "field_70161_v",
                     viewerPosZ = "field_78728_n", pushMatrix = "func_179094_E", getHealth = "func_110143_aJ", getMaxHealth = "func_110138_aP",
                     playerViewY = "field_78735_i", disableDepth = "func_179097_i", drawRect = "func_73734_a", enableDepth = "func_179126_j",
                     popMatrix = "func_179121_F", addChatMessage = "func_145747_a";


        cw.visit(52, ACC_PUBLIC + ACC_SUPER, injClassName, null, "java/lang/Object", null);

        cw.visitSource("InjectionClass.java", null);

        {
            fv = cw.visitField(ACC_PRIVATE + ACC_FINAL + ACC_STATIC, "inj", "Ljava/lang/String;", null, "CheatInjector created by blowsy.");
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE + ACC_STATIC, "en", "Z", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE + ACC_STATIC, "mc", "Lnet/minecraft/client/Minecraft;", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE, "t", "Ljava/lang/reflect/Field;", null, null);
            fv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, "java/lang/Exception");
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLineNumber(32, l3);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            Label l4 = new Label();
            mv.visitLabel(l4);
            mv.visitLineNumber(34, l4);
            mv.visitFieldInsn(GETSTATIC, "net/minecraftforge/client/ClientCommandHandler", "instance", "Lnet/minecraftforge/client/ClientCommandHandler;");
            mv.visitTypeInsn(NEW, cmdClassName);
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, cmdClassName, "<init>", "()V", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraftforge/client/ClientCommandHandler", registerCommand, "(Lnet/minecraft/command/ICommand;)Lnet/minecraft/command/ICommand;", false);
            mv.visitInsn(POP);
            Label l5 = new Label();
            mv.visitLabel(l5);
            mv.visitLineNumber(36, l5);
            mv.visitMethodInsn(INVOKESTATIC, "net/minecraft/client/Minecraft", getMinecraft, "()Lnet/minecraft/client/Minecraft;", false);
            mv.visitFieldInsn(PUTSTATIC, injClassName, "mc", "Lnet/minecraft/client/Minecraft;");
            mv.visitLabel(l0);
            mv.visitLineNumber(39, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitLdcInsn(Type.getType("Lnet/minecraft/client/Minecraft;"));
            mv.visitLdcInsn("field_71428_T");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getDeclaredField", "(Ljava/lang/String;)Ljava/lang/reflect/Field;", false);
            mv.visitFieldInsn(PUTFIELD, injClassName, "t", "Ljava/lang/reflect/Field;");
            Label l6 = new Label();
            mv.visitLabel(l6);
            mv.visitLineNumber(40, l6);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, injClassName, "t", "Ljava/lang/reflect/Field;");
            mv.visitInsn(ICONST_1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/reflect/Field", "setAccessible", "(Z)V", false);
            mv.visitLabel(l1);
            mv.visitLineNumber(41, l1);
            Label l7 = new Label();
            mv.visitJumpInsn(GOTO, l7);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 1, new Object[]{injClassName}, 1, new Object[]{"java/lang/Exception"});
            mv.visitVarInsn(ASTORE, 1);
            mv.visitLabel(l7);
            mv.visitLineNumber(42, l7);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            Label l8 = new Label();
            mv.visitLabel(l8);
            mv.visitLocalVariable("this", "L" + injClassName + ";", null, l3, l8, 0);
            mv.visitMaxs(8, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "r", "(Lnet/minecraftforge/client/event/RenderWorldLastEvent;)V", null, null);
            {
                av0 = mv.visitAnnotation("Lnet/minecraftforge/fml/common/eventhandler/SubscribeEvent;", true);
                av0.visitEnd();
            }
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(46, l0);
            mv.visitFieldInsn(GETSTATIC, injClassName, "en", "Z");
            Label l1 = new Label();
            mv.visitJumpInsn(IFEQ, l1);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, injClassName, "nc", "()Z", false);
            Label l2 = new Label();
            mv.visitJumpInsn(IFNE, l2);
            mv.visitLabel(l1);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitLabel(l2);
            mv.visitLineNumber(47, l2);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitFieldInsn(GETSTATIC, injClassName, "mc", "Lnet/minecraft/client/Minecraft;");
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/Minecraft", theWorld, "Lnet/minecraft/client/multiplayer/WorldClient;");
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/multiplayer/WorldClient", playerEntities, "Ljava/util/List;");
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "iterator", "()Ljava/util/Iterator;", true);
            mv.visitVarInsn(ASTORE, 2);
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitFrame(Opcodes.F_APPEND, 1, new Object[]{"java/util/Iterator"}, 0, null);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true);
            Label l4 = new Label();
            mv.visitJumpInsn(IFEQ, l4);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true);
            mv.visitTypeInsn(CHECKCAST, "net/minecraft/entity/player/EntityPlayer");
            mv.visitVarInsn(ASTORE, 3);
            Label l5 = new Label();
            mv.visitLabel(l5);
            mv.visitLineNumber(48, l5);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitFieldInsn(GETSTATIC, injClassName, "mc", "Lnet/minecraft/client/Minecraft;");
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/Minecraft", thePlayer, "Lnet/minecraft/client/entity/EntityPlayerSP;");
            Label l6 = new Label();
            mv.visitJumpInsn(IF_ACMPEQ, l6);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/entity/player/EntityPlayer", deathTime, "I");
            mv.visitJumpInsn(IFNE, l6);
            Label l7 = new Label();
            mv.visitLabel(l7);
            mv.visitLineNumber(49, l7);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitMethodInsn(INVOKESPECIAL, injClassName, "esp", "(Lnet/minecraft/entity/player/EntityPlayer;)V", false);
            mv.visitLabel(l6);
            mv.visitLineNumber(51, l6);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitJumpInsn(GOTO, l3);
            mv.visitLabel(l4);
            mv.visitLineNumber(52, l4);
            mv.visitFrame(Opcodes.F_CHOP, 1, null, 0, null);
            mv.visitInsn(RETURN);
            Label l8 = new Label();
            mv.visitLabel(l8);
            mv.visitLocalVariable("en", "Lnet/minecraft/entity/player/EntityPlayer;", null, l5, l6, 3);
            mv.visitLocalVariable("this", "L" + injClassName + ";", null, l0, l8, 0);
            mv.visitLocalVariable("e", "Lnet/minecraftforge/client/event/RenderWorldLastEvent;", null, l0, l8, 1);
            mv.visitMaxs(2, 4);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE, "esp", "(Lnet/minecraft/entity/player/EntityPlayer;)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(55, l0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/entity/player/EntityPlayer", lastTickPosX, "D");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/entity/player/EntityPlayer", posX, "D");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/entity/player/EntityPlayer", lastTickPosX, "D");
            mv.visitInsn(DSUB);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, injClassName, "t", "()Lnet/minecraft/util/Timer;", false);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/util/Timer", renderPartialTicks, "F");
            mv.visitInsn(F2D);
            mv.visitInsn(DMUL);
            mv.visitInsn(DADD);
            mv.visitFieldInsn(GETSTATIC, injClassName, "mc", "Lnet/minecraft/client/Minecraft;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/Minecraft", getRenderManager, "()Lnet/minecraft/client/renderer/entity/RenderManager;", false);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/entity/RenderManager", viewerPosX, "D");
            mv.visitInsn(DSUB);
            mv.visitVarInsn(DSTORE, 2);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(56, l1);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/entity/player/EntityPlayer", lastTickPosY, "D");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/entity/player/EntityPlayer", posY, "D");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/entity/player/EntityPlayer", lastTickPosY, "D");
            mv.visitInsn(DSUB);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, injClassName, "t", "()Lnet/minecraft/util/Timer;", false);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/util/Timer", renderPartialTicks, "F");
            mv.visitInsn(F2D);
            mv.visitInsn(DMUL);
            mv.visitInsn(DADD);
            mv.visitFieldInsn(GETSTATIC, injClassName, "mc", "Lnet/minecraft/client/Minecraft;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/Minecraft", getRenderManager, "()Lnet/minecraft/client/renderer/entity/RenderManager;", false);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/entity/RenderManager", viewerPosY, "D");
            mv.visitInsn(DSUB);
            mv.visitVarInsn(DSTORE, 4);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLineNumber(57, l2);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/entity/player/EntityPlayer", lastTickPosZ, "D");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/entity/player/EntityPlayer", posZ, "D");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/entity/player/EntityPlayer", lastTickPosZ, "D");
            mv.visitInsn(DSUB);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, injClassName, "t", "()Lnet/minecraft/util/Timer;", false);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/util/Timer", renderPartialTicks, "F");
            mv.visitInsn(F2D);
            mv.visitInsn(DMUL);
            mv.visitInsn(DADD);
            mv.visitFieldInsn(GETSTATIC, injClassName, "mc", "Lnet/minecraft/client/Minecraft;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/Minecraft", getRenderManager, "()Lnet/minecraft/client/renderer/entity/RenderManager;", false);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/entity/RenderManager", viewerPosZ, "D");
            mv.visitInsn(DSUB);
            mv.visitVarInsn(DSTORE, 6);
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLineNumber(58, l3);
            mv.visitMethodInsn(INVOKESTATIC, "net/minecraft/client/renderer/GlStateManager", pushMatrix, "()V", false);
            Label l4 = new Label();
            mv.visitLabel(l4);
            mv.visitLineNumber(60, l4);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/entity/player/EntityPlayer", getHealth, "()F", false);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/entity/player/EntityPlayer", getMaxHealth, "()F", false);
            mv.visitInsn(FDIV);
            mv.visitInsn(F2D);
            mv.visitVarInsn(DSTORE, 8);
            Label l5 = new Label();
            mv.visitLabel(l5);
            mv.visitLineNumber(61, l5);
            mv.visitLdcInsn(new Double("74.0"));
            mv.visitVarInsn(DLOAD, 8);
            mv.visitInsn(DMUL);
            mv.visitInsn(D2I);
            mv.visitVarInsn(ISTORE, 10);
            Label l6 = new Label();
            mv.visitLabel(l6);
            mv.visitLineNumber(63, l6);
            mv.visitVarInsn(DLOAD, 8);
            mv.visitLdcInsn(new Double("0.3"));
            mv.visitInsn(DCMPG);
            Label l7 = new Label();
            mv.visitJumpInsn(IFGE, l7);
            mv.visitFieldInsn(GETSTATIC, "java/awt/Color", "red", "Ljava/awt/Color;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/awt/Color", "getRGB", "()I", false);
            Label l8 = new Label();
            mv.visitJumpInsn(GOTO, l8);
            mv.visitLabel(l7);
            mv.visitFrame(Opcodes.F_FULL, 7, new Object[]{injClassName, "net/minecraft/entity/player/EntityPlayer", Opcodes.DOUBLE, Opcodes.DOUBLE, Opcodes.DOUBLE, Opcodes.DOUBLE, Opcodes.INTEGER}, 0, new Object[]{});
            mv.visitVarInsn(DLOAD, 8);
            mv.visitLdcInsn(new Double("0.5"));
            mv.visitInsn(DCMPG);
            Label l9 = new Label();
            mv.visitJumpInsn(IFGE, l9);
            mv.visitFieldInsn(GETSTATIC, "java/awt/Color", "orange", "Ljava/awt/Color;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/awt/Color", "getRGB", "()I", false);
            mv.visitJumpInsn(GOTO, l8);
            mv.visitLabel(l9);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(DLOAD, 8);
            mv.visitLdcInsn(new Double("0.7"));
            mv.visitInsn(DCMPG);
            Label l10 = new Label();
            mv.visitJumpInsn(IFGE, l10);
            mv.visitFieldInsn(GETSTATIC, "java/awt/Color", "yellow", "Ljava/awt/Color;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/awt/Color", "getRGB", "()I", false);
            mv.visitJumpInsn(GOTO, l8);
            mv.visitLabel(l10);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitFieldInsn(GETSTATIC, "java/awt/Color", "green", "Ljava/awt/Color;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/awt/Color", "getRGB", "()I", false);
            mv.visitLabel(l8);
            mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[]{Opcodes.INTEGER});
            mv.visitVarInsn(ISTORE, 11);
            Label l11 = new Label();
            mv.visitLabel(l11);
            mv.visitLineNumber(64, l11);
            mv.visitVarInsn(DLOAD, 2);
            mv.visitVarInsn(DLOAD, 4);
            mv.visitLdcInsn(new Double("0.2"));
            mv.visitInsn(DSUB);
            mv.visitVarInsn(DLOAD, 6);
            mv.visitMethodInsn(INVOKESTATIC, "org/lwjgl/opengl/GL11", "glTranslated", "(DDD)V", false);
            Label l12 = new Label();
            mv.visitLabel(l12);
            mv.visitLineNumber(65, l12);
            mv.visitFieldInsn(GETSTATIC, injClassName, "mc", "Lnet/minecraft/client/Minecraft;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/Minecraft", getRenderManager, "()Lnet/minecraft/client/renderer/entity/RenderManager;", false);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/entity/RenderManager", playerViewY, "F");
            mv.visitInsn(FNEG);
            mv.visitInsn(F2D);
            mv.visitInsn(DCONST_0);
            mv.visitInsn(DCONST_1);
            mv.visitInsn(DCONST_0);
            mv.visitMethodInsn(INVOKESTATIC, "org/lwjgl/opengl/GL11", "glRotated", "(DDDD)V", false);
            Label l13 = new Label();
            mv.visitLabel(l13);
            mv.visitLineNumber(66, l13);
            mv.visitMethodInsn(INVOKESTATIC, "net/minecraft/client/renderer/GlStateManager", disableDepth, "()V", false);
            Label l14 = new Label();
            mv.visitLabel(l14);
            mv.visitLineNumber(67, l14);
            mv.visitLdcInsn(new Float("0.03"));
            mv.visitLdcInsn(new Float("0.03"));
            mv.visitLdcInsn(new Float("0.03"));
            mv.visitMethodInsn(INVOKESTATIC, "org/lwjgl/opengl/GL11", "glScalef", "(FFF)V", false);
            Label l15 = new Label();
            mv.visitLabel(l15);
            mv.visitLineNumber(68, l15);
            mv.visitIntInsn(BIPUSH, 21);
            mv.visitInsn(ICONST_M1);
            mv.visitIntInsn(BIPUSH, 26);
            mv.visitIntInsn(BIPUSH, 75);
            mv.visitFieldInsn(GETSTATIC, "java/awt/Color", "black", "Ljava/awt/Color;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/awt/Color", "getRGB", "()I", false);
            mv.visitMethodInsn(INVOKESTATIC, "net/minecraft/client/gui/Gui", drawRect, "(IIIII)V", false);
            Label l16 = new Label();
            mv.visitLabel(l16);
            mv.visitLineNumber(69, l16);
            mv.visitIntInsn(BIPUSH, 22);
            mv.visitVarInsn(ILOAD, 10);
            mv.visitIntInsn(BIPUSH, 25);
            mv.visitIntInsn(BIPUSH, 74);
            mv.visitFieldInsn(GETSTATIC, "java/awt/Color", "darkGray", "Ljava/awt/Color;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/awt/Color", "getRGB", "()I", false);
            mv.visitMethodInsn(INVOKESTATIC, "net/minecraft/client/gui/Gui", drawRect, "(IIIII)V", false);
            Label l17 = new Label();
            mv.visitLabel(l17);
            mv.visitLineNumber(70, l17);
            mv.visitIntInsn(BIPUSH, 22);
            mv.visitInsn(ICONST_0);
            mv.visitIntInsn(BIPUSH, 25);
            mv.visitVarInsn(ILOAD, 10);
            mv.visitVarInsn(ILOAD, 11);
            mv.visitMethodInsn(INVOKESTATIC, "net/minecraft/client/gui/Gui", drawRect, "(IIIII)V", false);
            Label l18 = new Label();
            mv.visitLabel(l18);
            mv.visitLineNumber(71, l18);
            mv.visitMethodInsn(INVOKESTATIC, "net/minecraft/client/renderer/GlStateManager", enableDepth, "()V", false);
            Label l19 = new Label();
            mv.visitLabel(l19);
            mv.visitLineNumber(72, l19);
            mv.visitMethodInsn(INVOKESTATIC, "net/minecraft/client/renderer/GlStateManager", popMatrix, "()V", false);
            Label l20 = new Label();
            mv.visitLabel(l20);
            mv.visitLineNumber(73, l20);
            mv.visitInsn(RETURN);
            Label l21 = new Label();
            mv.visitLabel(l21);
            mv.visitLocalVariable("this", "L" + injClassName + ";", null, l0, l21, 0);
            mv.visitLocalVariable("e", "Lnet/minecraft/entity/player/EntityPlayer;", null, l0, l21, 1);
            mv.visitLocalVariable("x", "D", null, l1, l21, 2);
            mv.visitLocalVariable("y", "D", null, l2, l21, 4);
            mv.visitLocalVariable("z", "D", null, l3, l21, 6);
            mv.visitLocalVariable("r", "D", null, l5, l21, 8);
            mv.visitLocalVariable("b", "I", null, l6, l21, 10);
            mv.visitLocalVariable("c", "I", null, l11, l21, 11);
            mv.visitMaxs(8, 12);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE, "t", "()Lnet/minecraft/util/Timer;", null, null);
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, "java/lang/IllegalAccessException");
            mv.visitTryCatchBlock(l0, l1, l2, "java/lang/IndexOutOfBoundsException");
            mv.visitLabel(l0);
            mv.visitLineNumber(77, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, injClassName, "t", "Ljava/lang/reflect/Field;");
            mv.visitFieldInsn(GETSTATIC, injClassName, "mc", "Lnet/minecraft/client/Minecraft;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/reflect/Field", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "net/minecraft/util/Timer");
            mv.visitLabel(l1);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitLineNumber(78, l2);
            mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[]{"java/lang/Exception"});
            mv.visitVarInsn(ASTORE, 1);
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitInsn(ACONST_NULL);
            mv.visitInsn(ARETURN);
            Label l4 = new Label();
            mv.visitLabel(l4);
            mv.visitLocalVariable("e", "Ljava/lang/Exception;", null, l3, l4, 1);
            mv.visitLocalVariable("this", "L" + injClassName + ";", null, l0, l4, 0);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE, "nc", "()Z", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(83, l0);
            mv.visitFieldInsn(GETSTATIC, injClassName, "mc", "Lnet/minecraft/client/Minecraft;");
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/Minecraft", thePlayer, "Lnet/minecraft/client/entity/EntityPlayerSP;");
            Label l1 = new Label();
            mv.visitJumpInsn(IFNULL, l1);
            mv.visitFieldInsn(GETSTATIC, injClassName, "mc", "Lnet/minecraft/client/Minecraft;");
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/Minecraft", theWorld, "Lnet/minecraft/client/multiplayer/WorldClient;");
            mv.visitJumpInsn(IFNULL, l1);
            mv.visitInsn(ICONST_1);
            Label l2 = new Label();
            mv.visitJumpInsn(GOTO, l2);
            mv.visitLabel(l1);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(ICONST_0);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[]{Opcodes.INTEGER});
            mv.visitInsn(IRETURN);
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLocalVariable("this", "L" + injClassName + ";", null, l0, l3, 0);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "g", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(88, l0);
            mv.visitFieldInsn(GETSTATIC, injClassName, "mc", "Lnet/minecraft/client/Minecraft;");
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/Minecraft", thePlayer, "Lnet/minecraft/client/entity/EntityPlayerSP;");
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitTypeInsn(NEW, "net/minecraft/util/ChatComponentText");
            mv.visitInsn(DUP);
            mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
            mv.visitLdcInsn("set: ");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitFieldInsn(GETSTATIC, injClassName, "en", "Z");
            Label l2 = new Label();
            mv.visitJumpInsn(IFNE, l2);
            mv.visitInsn(ICONST_1);
            Label l3 = new Label();
            mv.visitJumpInsn(GOTO, l3);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 0, new Object[]{}, 4, new Object[]{"net/minecraft/client/entity/EntityPlayerSP", l1, l1, "java/lang/StringBuilder"});
            mv.visitInsn(ICONST_0);
            mv.visitLabel(l3);
            mv.visitFrame(Opcodes.F_FULL, 0, new Object[]{}, 5, new Object[]{"net/minecraft/client/entity/EntityPlayerSP", l1, l1, "java/lang/StringBuilder", Opcodes.INTEGER});
            mv.visitInsn(DUP);
            mv.visitFieldInsn(PUTSTATIC, injClassName, "en", "Z");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Z)Ljava/lang/StringBuilder;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
            mv.visitMethodInsn(INVOKESPECIAL, "net/minecraft/util/ChatComponentText", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/entity/EntityPlayerSP", addChatMessage, "(Lnet/minecraft/util/IChatComponent;)V", false);
            Label l4 = new Label();
            mv.visitLabel(l4);
            mv.visitLineNumber(89, l4);
            mv.visitInsn(RETURN);
            mv.visitMaxs(6, 0);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(27, l0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTSTATIC, injClassName, "en", "Z");
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 0);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();

    }

}
