package xyz.blowsy.inj;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import org.objectweb.asm.tree.ClassNode;

import java.io.File;

public class Utils {

    public static final String cc = "\u00A7";
    public static final String prefix = "&7[&dINJ&7]&r";
    private static final String line = "&7&m-------------------------";

    public static final Minecraft mc = Minecraft.getMinecraft();

    public static boolean nullCheck() {
        return mc.thePlayer != null && mc.theWorld != null;
    }

    public static void sendMessage(String message) {
        if (!nullCheck()) return;
        String txt = replace(prefix + " " + message);
        mc.thePlayer.addChatMessage(new ChatComponentText(txt));
    }

    public static void sendLine() {
        sendMessage(line);
    }

    public static String replace(String text) {
        return text.replace("&", cc).replace("%and", "&");
    }

    public static double round(double number, int decimals) {
        if (decimals == 0) return Math.round(number);
        double power = Math.pow(10, decimals);
        return Math.round(number*power)/power;
    }

    public static long timeBetween(long start, long end) {
        return Math.abs(end - start);
    }

    public static double longToSeconds(long value) {
        return round(value / 1000d, 2);
    }

    public static double getFileSizeKB(File file) {
        return round(file.length() / 1000.0, 1);
    }

    public static String classToMethodDesc(Class _class) {
        final String prefix = "(L", suffix = ";)V"; // return type is void
        return prefix + _class.getName().replace(".", "/") + suffix;
    }

    public static String classToAttribute(Class _class) {
        final String prefix = "L", suffix = ";";
        return prefix + _class.getName().replace(".", "/") + suffix;
    }

    public static String getClassName(ClassNode classNode) {
        String name = classNode.name;
        if (classNode.name.contains("/")) {
            String[] split = classNode.name.split("/");
            if (split.length > 0) {
                name = split[split.length - 1];
            }
        }
        return name;
    }

}
