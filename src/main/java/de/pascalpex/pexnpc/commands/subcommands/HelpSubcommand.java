package de.pascalpex.pexnpc.commands.subcommands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import de.pascalpex.pexnpc.PexNPC;
import de.pascalpex.pexnpc.util.MessageHandler;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.command.CommandSender;

public class HelpSubcommand implements Command<CommandSourceStack> {
    @Override
    public int run(CommandContext<CommandSourceStack> context) {
        CommandSender sender = context.getSource().getSender();
        HoverEvent<Component> hoverEvent = HoverEvent.showText(MessageHandler.parse("<aqua>Click to execute"));

        sender.sendMessage(MessageHandler.prefixedMini("PexNPC " + PexNPC.getPluginVersion() + " by Pascalpex"));
        sender.sendMessage(MessageHandler.prefixedMini("Available commands:"));
        sender.sendMessage(MessageHandler.prefixedMini("/charakter hilfe <dark_gray>| <gold>Shows this page").hoverEvent(hoverEvent).clickEvent(ClickEvent.suggestCommand("/charakter hilfe")));
        sender.sendMessage(MessageHandler.prefixedMini("/charakter neuladen <dark_gray>| <gold>Reloads the files and NPCs").hoverEvent(hoverEvent).clickEvent(ClickEvent.suggestCommand("/charakter neuladen")));
        sender.sendMessage(MessageHandler.prefixedMini("/charakter erstellen [NAME] <dark_gray>| <gold>Creates a new NPC").hoverEvent(hoverEvent).clickEvent(ClickEvent.suggestCommand("/charakter erstellen")));
        sender.sendMessage(MessageHandler.prefixedMini("/charakter liste <dark_gray>| <gold>Shows all NPCs and their IDs").hoverEvent(hoverEvent).clickEvent(ClickEvent.suggestCommand("/charakter liste")));
        sender.sendMessage(MessageHandler.prefixedMini("/charakter löschen [ID] <dark_gray>| <gold>Deletes a NPC").hoverEvent(hoverEvent).clickEvent(ClickEvent.suggestCommand("/charakter löschen")));
        sender.sendMessage(MessageHandler.prefixedMini("/charakter name [ID] [NAME] <dark_gray>| <gold>Changes a name").hoverEvent(hoverEvent).clickEvent(ClickEvent.suggestCommand("/charakter name")));
        sender.sendMessage(MessageHandler.prefixedMini("/charakter hierhin [ID] <dark_gray>| <gold>Moves a NPC to you").hoverEvent(hoverEvent).clickEvent(ClickEvent.suggestCommand("/charakter hierhin")));
        sender.sendMessage(MessageHandler.prefixedMini("/charakter skin [ID] [NAME] <dark_gray>| <gold>Changes a skin").hoverEvent(hoverEvent).clickEvent(ClickEvent.suggestCommand("/charakter skin")));
        sender.sendMessage(MessageHandler.prefixedMini("/charakter befehl [ID] [CMD] <dark_gray>| <gold>Gives a command to a NPC").hoverEvent(hoverEvent).clickEvent(ClickEvent.suggestCommand("/charakter befehl")));
        sender.sendMessage(MessageHandler.prefixedMini("/charakter msg [ID] [MSG] <dark_gray>| <gold>Gives a message to a NPC").hoverEvent(hoverEvent).clickEvent(ClickEvent.suggestCommand("/charakter msg")));
        sender.sendMessage(MessageHandler.prefixedMini("/charakter gegenstand [ID] [SLOT] <dark_gray>| <gold>Gives an item to a NPC").hoverEvent(hoverEvent).clickEvent(ClickEvent.suggestCommand("/charakter gegenstand")));
        sender.sendMessage(MessageHandler.prefixedMini("/charakter leeren [ID] <dark_gray>| <gold>Clears the command, message and items of a NPC").hoverEvent(hoverEvent).clickEvent(ClickEvent.suggestCommand("/charakter leeren")));
        sender.sendMessage(MessageHandler.prefixedMini("/charakter tp [ID] <dark_gray>| <gold>Teleports to a NPC").hoverEvent(hoverEvent).clickEvent(ClickEvent.suggestCommand("/charakter tp")));
        sender.sendMessage(MessageHandler.prefixedMini("/charakter inspektieren <dark_gray>| <gold>Toggles the inspection mode").hoverEvent(hoverEvent).clickEvent(ClickEvent.suggestCommand("/charakter inspektieren")));
        return SINGLE_SUCCESS;
    }
}
