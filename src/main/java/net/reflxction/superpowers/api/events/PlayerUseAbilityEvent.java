/*
 * * Copyright 2017-2018 github.com/ReflxctionDev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.reflxction.superpowers.api.events;

import net.reflxction.superpowers.core.AbilityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerUseAbilityEvent extends PlayerEvent implements Cancellable {

    // The player ability
    private AbilityType ability;

    // If the event is cancelled
    private boolean cancelled;

    // Bukkit event handler
    private static HandlerList handlers = new HandlerList();

    /**
     * @param who     The player
     * @param ability The ability
     */
    public PlayerUseAbilityEvent(Player who, AbilityType ability) {
        super(who);
        this.ability = ability;
    }

    /**
     * @return Handler list which bukkit needs to handle the event
     */
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    /**
     * @return The actual method that bukkit needs to run the API, idk why dont ask me lol
     */
    public static HandlerList getHandlerList() {
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
        return ability;
    }

}
