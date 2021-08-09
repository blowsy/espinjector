package xyz.blowsy.inj;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import xyz.blowsy.inj.impl.Injector;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Command extends CommandBase {

    private final String COMMAND = "inject";
    private final long COOLDOWN = 2500L;
    private long PREV_EXEC = 0L;

    public String getCommandName() { return COMMAND; }

    public String getCommandUsage(ICommandSender sender) { return "/" + COMMAND; }

    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (!canPerform()) {
            return;
        }
        if (args.length == 1) {
            switch (args[0].toLowerCase()) {
                case "load":
                    Main.loadFolder();
                    return;
                case "open":
                    try {
                        Desktop.getDesktop().open(new File(Main.FOLDER_PATH));
                    } catch (IOException e) {
                        Utils.sendMessage("&cError locating folder. Reloaded folder.");
                        Main.loadFolder();
                    }
                    return;
                case "list":
                    if (Main.files.isEmpty()) {
                        Utils.sendMessage("&eNo files located in the directory.");
                        return;
                    }
                    Utils.sendMessage("&eLoaded files:");
                    for (File file : Main.files) {
                        Utils.sendMessage(" &e- &7" + file.getName());
                    }
                    return;
                default:
                    if (Main.files.isEmpty()) {
                        Utils.sendMessage("&eNo files located in the directory.");
                        return;
                    }
                    String name = args[0];
                    for (int i = 0; i < Main.files.size(); i++) {
                        File file = Main.files.get(i);
                        if (file.getName().equals(name)) {
                            if (!file.exists() || !file.canRead()) {
                                Utils.sendMessage("&eFile is no longer accessible. Reloaded folder.");
                                Main.loadFolder();
                                return;
                            }
                            Main.getExecutor().execute(() -> {
                                try {
                                    Injector.run(file);
                                } catch (Exception e) {
                                    Utils.sendMessage("&cThere was an error.");
                                    e.printStackTrace();
                                }
                            });
                            return;
                        }
                    }
                    Utils.sendMessage("&eUnable to locate file.");
                    return;
            }
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("cmd")) {
                Main.customCommandName = args[1];
                Utils.sendMessage("&eCommand set to &3/" + Main.customCommandName);
                return;
            }
        }
        Utils.sendMessage("&eInvalid command usage.");
    }

    public int getRequiredPermissionLevel() { return 0; }

    public boolean canCommandSenderUseCommand(ICommandSender sender) { return true; }

    private boolean canPerform() {
        if (Utils.timeBetween(PREV_EXEC, System.currentTimeMillis()) > COOLDOWN) {
            PREV_EXEC = System.currentTimeMillis();
            return true;
        }
        Utils.sendMessage("&cPlease do not spam these commands!");
        return false;
    }

}
