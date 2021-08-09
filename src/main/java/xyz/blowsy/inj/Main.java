package xyz.blowsy.inj;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Mod(modid = "examplemod-2", name = "Example Mod", version = "1.0", acceptedMinecraftVersions = "[1.8.9]")
public class Main {

    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static final String FOLDER_NAME = "InjectorFolder";
    public static final String FOLDER_PATH = Minecraft.getMinecraft().mcDataDir + File.separator + FOLDER_NAME;
    public static final List<File> files = new ArrayList<>();

    public static String customCommandName = "cheat";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        shutdownHook();
        ClientCommandHandler.instance.registerCommand(new Command());
        loadFolder();
    }

    public static void loadFolder() {
        files.clear();
        File path = new File(FOLDER_PATH);
        Utils.sendMessage("&eChecking path: &7" + FOLDER_NAME);
        if (!path.exists()) {
            path.mkdirs(); // create folder
            return;
        }
        File[] folder = path.listFiles();
        if (folder == null) {
            return;
        }
        for (int i = 0; i < folder.length; i++) {
            File file = folder[i];
            if (!file.canRead() || !file.getName().endsWith(".jar")) {
                continue;
            }
            files.add(file);
        }
        Utils.sendMessage("&eLoaded folder. Identified &3" + files.size() + " &efiles.");
    }

    public static ExecutorService getExecutor() {
        return executor;
    }

    private void shutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            executor.shutdown();
        }));
    }

}
