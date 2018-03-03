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

public class PlayerSelectAbilityEvent extends PlayerEvent implements Cancellable {

    // The new ability the player selected
    private AbilityType newAbility;

    // The Bukkit-event-bus needed variables
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;

    /**
     * @param who        The player
     * @param newAbility The new ability
     */
    public PlayerSelectAbilityEvent(final Player who, final AbilityType newAbility) {
        super(who);
        this.newAbility = newAbility;
    }

    /**
     * @return The event if it is cancelled
     */
    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * @param cancel The boolean of the event if it's cancelled
     */
    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    /**
     * @return Event handler which bukkit needs
     */
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    /**
     * @return The new ability the player selected
     */
    public AbilityType getNewAbility() {
        return newAbility;
    }

    /**
     * @return The actual method that bukkit needs to run the API, idk why dont ask me lol
     */
    public static HandlerList getHandlerList() {
        return handlers;
    }
}

