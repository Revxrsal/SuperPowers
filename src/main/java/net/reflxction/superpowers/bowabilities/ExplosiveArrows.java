package net.reflxction.superpowers.bowabilities;

import net.reflxction.superpowers.core.AbstractProjectileListener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

/**
 * Created by Reflxction, on 02/12/18.
 */
public class ExplosiveArrows extends AbstractProjectileListener {

    @Override
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        setName("&cExplosiveArrow");
        event.getEntity().setCustomName(projectileName);
    }

    @Override
    public void onProjectileHit(ProjectileHitEvent event) {

    }
}
