package de.pascalpex.pexnpc;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import de.pascalpex.pexnpc.commands.IDArgument;
import de.pascalpex.pexnpc.commands.NPCSlotArgument;
import de.pascalpex.pexnpc.commands.subcommands.*;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.bootstrap.PluginProviderContext;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class PexNPCBootstrap implements PluginBootstrap {
    @Override
    public void bootstrap(BootstrapContext context) {
        context.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            IDArgument idArgument = new IDArgument();
            HelpSubcommand helpSubcommand = new HelpSubcommand();

            LiteralCommandNode<CommandSourceStack> advancedCommandRoot = Commands.literal("charakter")
                    .requires(commandSourceStack -> commandSourceStack.getSender().hasPermission("pexnpc.command"))
                    .then(Commands.literal("hilfe")
                            .executes(helpSubcommand))
                    .then(Commands.literal("neuladen")
                            .executes(new ReloadSubcommand()))
                    .then(Commands.literal("erstellen")
                            .then(Commands.argument("name", StringArgumentType.greedyString())
                                    .executes(new CreateSubcommand())))
                    .then(Commands.literal("liste")
                            .executes(new ListSubcommand()))
                    .then(Commands.literal("löschen")
                            .then(Commands.argument("npc", idArgument)
                                    .executes(new DeleteSubcommand())))
                    .then(Commands.literal("name")
                            .then(Commands.argument("npc", idArgument)
                                    .then(Commands.argument("name", StringArgumentType.greedyString())
                                            .executes(new NameSubcommand()))))
                    .then(Commands.literal("hierhin")
                            .then(Commands.argument("npc", idArgument)
                                    .executes(new MovehereSubcommand())))
                    .then(Commands.literal("tp")
                            .then(Commands.argument("npc", idArgument)
                                    .executes(new TpSubcommand())))
                    .then(Commands.literal("skin")
                            .then(Commands.argument("npc", idArgument)
                                    .then(Commands.argument("skin", StringArgumentType.word())
                                            .suggests((cmdContext, builder) -> {
                                                String currentInput = "";
                                                try {
                                                    currentInput = StringArgumentType.getString(cmdContext, "skin").toLowerCase();
                                                } catch (IllegalArgumentException ignored) {
                                                } // Command does not contain a skin argument yet
                                                for (Player player : Bukkit.getOnlinePlayers()) {
                                                    String playerName = player.getName();
                                                    if (playerName.toLowerCase().startsWith(currentInput)) {
                                                        builder.suggest(playerName);
                                                    }
                                                }
                                                return builder.buildFuture();
                                            })
                                            .executes(new SkinSubcommand()))))
                    .then(Commands.literal("befehl")
                            .then(Commands.argument("npc", idArgument)
                                    .then(Commands.argument("cmd", StringArgumentType.greedyString())
                                            .executes(new CmdSubcommand()))))
                    .then(Commands.literal("msg")
                            .then(Commands.argument("npc", idArgument)
                                    .then(Commands.argument("msg", StringArgumentType.greedyString())
                                            .executes(new MsgSubcommand()))))
                    .then(Commands.literal("gegenstand")
                            .then(Commands.argument("npc", idArgument)
                                    .then(Commands.argument("slot", new NPCSlotArgument())
                                            .executes(new ItemSubcommand()))))
                    .then(Commands.literal("leeren")
                            .then(Commands.argument("npc", idArgument)
                                    .executes(new ClearSubcommand())))
                    .then(Commands.literal("inspektieren")
                            .executes(new InspectSubcommand()))
                    .executes(helpSubcommand)
                    .build();

            commands.registrar().register(advancedCommandRoot);
        });
    }

    @Override
    public @NotNull JavaPlugin createPlugin(@NotNull PluginProviderContext context) {
        return new PexNPC();
    }
}
