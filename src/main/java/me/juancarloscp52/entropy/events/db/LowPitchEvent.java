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

public class LowPitchEvent extends AbstractTimedEvent {
    public static final EventType<LowPitchEvent> TYPE = EventType.builder(LowPitchEvent::new).category(EventCategory.PITCH).build();

    @Override
    public void initClient() {
        Variables.forcePitch = true;
        Variables.forcedPitch = 0.375f;
    }

    @Override
    public void endClient() {
        Variables.forcePitch = false;
        Variables.forcedPitch = 0;
        super.endClient();
    }

    @Override
    public short getDuration() {
        return (short) (super.getDuration() * 2);
    }

    @Override
    public EventType<LowPitchEvent> getType() {
        return TYPE;
    }
}
