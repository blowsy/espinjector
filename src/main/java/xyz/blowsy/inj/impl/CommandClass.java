package xyz.blowsy.inj.impl;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandClass extends CommandBase {

    // view this class as "ASMified" and copy paste it to CommandDumper
    // make sure to replace "[PLACEHOLDER]" with command string

    private String s = "[PLACEHOLDER]";

    public String getCommandName() {
        return s;
    }

    public String getCommandUsage(ICommandSender s) {
        return "/" + s;
    }

    public void processCommand(ICommandSender s, String[] a) {
        InjectionClass.g();
    }

    public boolean canCommandSenderUseCommand(ICommandSender s) { return true; }

}
