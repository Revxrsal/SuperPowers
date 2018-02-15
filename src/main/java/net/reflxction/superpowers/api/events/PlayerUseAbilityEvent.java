package net.reflxction.superpowers.api.events;

import net.reflxction.superpowers.core.AbilityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

/**
 * Created by Reflxction, on 02/03/18.
 */
public class PlayerUseAbilityEvent extends PlayerEvent implements Cancellable {

    private AbilityType abilityType;

    private boolean cancelled;

    private HandlerList handlers = new HandlerList();

    public PlayerUseAbilityEvent(Player who, AbilityType abilityType) {
        super(who);
        this.abilityType = abilityType;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    public AbilityType getAbility() {
        return abilityType;
    }

}
