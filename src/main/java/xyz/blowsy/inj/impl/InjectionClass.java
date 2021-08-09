package xyz.blowsy.inj.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.Timer;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.lang.reflect.Field;

public class InjectionClass {

    // view this class as "ASMified" and copy paste it to InjectionDumper

    private static final String inj = "CheatInjector created by blowsy.";

    private static boolean en = false; // cheat enabled or not

    private static Minecraft mc; // minecraft
    private Field t; // timer

    public InjectionClass() {
        // registering command class (remove when viewing ASMified)
        ClientCommandHandler.instance.registerCommand(new CommandClass());
        mc = Minecraft.getMinecraft();
        try {
            t = Minecraft.class.getDeclaredField("field_71428_T");
            t.setAccessible(true);
        } catch (Exception e) { }
    }

    @SubscribeEvent
    public void r(RenderWorldLastEvent e) {
        if (!en || !nc()) return;
        for (EntityPlayer en : mc.theWorld.playerEntities) {
            if (en != mc.thePlayer && en.deathTime == 0) {
                esp(en);
            }
        }
    }

    private void esp(EntityPlayer e) {
        double x = e.lastTickPosX + (e.posX - e.lastTickPosX) * t().renderPartialTicks - mc.getRenderManager().viewerPosX;
        double y = e.lastTickPosY + (e.posY - e.lastTickPosY) * t().renderPartialTicks - mc.getRenderManager().viewerPosY;
        double z = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * t().renderPartialTicks - mc.getRenderManager().viewerPosZ;
        GlStateManager.pushMatrix();
        // health ratio
        double r = e.getHealth() / e.getMaxHealth();
        int b = (int) (74 * r);
        // health color
        int c = r < 0.3 ? Color.red.getRGB() : (r < 0.5 ? Color.orange.getRGB() : (r < 0.7 ? Color.yellow.getRGB() : Color.green.getRGB())); // rgb for health
        GL11.glTranslated(x, y - 0.2D, z);
        GL11.glRotated(-mc.getRenderManager().playerViewY, 0.0D, 1.0D, 0.0D);
        GlStateManager.disableDepth();
        GL11.glScalef(0.03F, 0.03F, 0.03F);
        Gui.drawRect(21, -1, 26, 75, Color.black.getRGB());
        Gui.drawRect(22, b, 25, 74, Color.darkGray.getRGB());
        Gui.drawRect(22, 0, 25, b, c); // health
        GlStateManager.enableDepth();
        GlStateManager.popMatrix();
    }

    private Timer t() { // getTimer
        try {
            return (Timer) t.get(mc);
        } catch (IllegalAccessException | IndexOutOfBoundsException e) { return null; }
    }

    // nullCheck
    private boolean nc() {
        return mc.thePlayer != null && mc.theWorld != null;
    }

    // toggle module
    public static void g() {
        mc.thePlayer.addChatMessage(new ChatComponentText("set: " + (en = !en)));
    }

}
