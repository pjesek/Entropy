/*
 * Copyright (c) 2021 juancarloscp52
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package me.juancarloscp52.entropy.events.db;

import me.juancarloscp52.entropy.Variables;
import me.juancarloscp52.entropy.events.AbstractTimedEvent;
import me.juancarloscp52.entropy.events.EventCategory;
import me.juancarloscp52.entropy.events.EventType;

public class RandomDropsEvent extends AbstractTimedEvent {
    public static final EventType<RandomDropsEvent> TYPE = EventType.builder(RandomDropsEvent::new).category(EventCategory.DROPS).build();

    @Override
    public void init() {
        Variables.randomDrops = true;
    }

    @Override
    public void end() {
        Variables.randomDrops = false;
        super.end();
    }

    @Override
    public EventType<RandomDropsEvent> getType() {
        return TYPE;
    }
}
