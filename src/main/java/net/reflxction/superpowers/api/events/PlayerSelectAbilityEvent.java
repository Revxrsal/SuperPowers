package net.reflxction.superpowers.api.events;

import net.reflxction.superpowers.core.AbilityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

/**
 * Created by Reflxction, on 02/03/18.
 */
public class PlayerSelectAbilityEvent extends PlayerEvent implements Cancellable {

    private AbilityType newAbilityType;

    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;

    public PlayerSelectAbilityEvent(final Player who, final AbilityType newAbilityType) {
        super(who);
        this.newAbilityType = newAbilityType;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public AbilityType getNewAbility() {
        return newAbilityType;
    }
}
