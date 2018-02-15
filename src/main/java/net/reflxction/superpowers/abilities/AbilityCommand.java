package net.reflxction.superpowers.abilities;

import net.reflxction.superpowers.core.SuperPowers;
import net.reflxction.superpowers.gui.AbilityGUI;
import net.reflxction.superpowers.utils.managers.AbilityManager;
import net.reflxction.superpowers.utils.CheckUtils;
import net.reflxction.superpowers.utils.StringUtils;
import net.reflxction.superpowers.utils.managers.ConfigVariables;
import net.reflxction.superpowers.utils.managers.MessageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Reflxction, on 01/31/18.
 */
public class AbilityCommand implements CommandExecutor {

    private SuperPowers m = SuperPowers.getPlugin(SuperPowers.class);

    public AbilityCommand(SuperPowers m) {
        this.m = m;
    }

    private AbilityGUI a = new AbilityGUI(m);

    private MessageManager mm = new MessageManager(m);

    private ConfigVariables c = new ConfigVariables(m);

    private StringUtils su = new StringUtils();

    private AbilityManager au = new AbilityManager(m);

    private HashMap<UUID, Integer> cooldownMap = new HashMap();
    private HashMap<UUID, Integer> cooldownTime = new HashMap<>();
    private HashMap<UUID, BukkitRunnable> cooldownTask = new HashMap<>();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!CheckUtils.isPlayer(sender)) {
            sender.sendMessage(mm.getNotPlayerMessage());
        }
        if (CheckUtils.isPlayer(sender)) {
            Player p = (Player) sender;
            if (p.hasPermission("superpowers.abilities.use-command")) {
                if (cooldownMap.containsKey(p.getUniqueId())) {
                    p.sendMessage(su.format(su.filter("{player} is on cooldown! Time left: {time_left}, {ability}", p, cooldownTime.get(p.getUniqueId()))));
                    return true;
                } else {
                    final UUID uuid = p.getUniqueId();
                    cooldownMap.put(uuid, c.getCommandCooldown());
                    cooldownTime.put(uuid, c.getCommandCooldown());
                    cooldownTask.put(uuid, new BukkitRunnable() {
                        public void run() {
                            cooldownTime.put(uuid, cooldownTime.get(uuid) - 1);
                            if (cooldownTime.get(uuid) == 0) {
                                cooldownTime.remove(uuid);
                                cooldownTask.remove(uuid);
                                cooldownMap.remove(uuid);
                                cancel();
                            }
                        }
                    });
                    cooldownTask.get(uuid).runTaskTimer(m, 20, 20);
                    a.openGUI(p);
                    return true;
                }
            } else {
                p.sendMessage(mm.getNoPermissionCmdMessage());
            }
        }
        return false;
    }
}
