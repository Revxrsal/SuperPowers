package net.reflxction.superpowers.core;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

/**
 * Created by Reflxction, on 02/12/18.
 */
public abstract class AbstractProjectileListener {

    protected String projectileName;

    @EventHandler
    public abstract void onProjectileLaunch(ProjectileLaunchEvent event);

    @EventHandler
    public abstract void onProjectileHit(ProjectileHitEvent event);

    protected void setName(String name) {
        this.projectileName = ChatColor.translateAlternateColorCodes('&', name);
    }

}
