package me.juancarloscp52.entropy.events.db;

import me.juancarloscp52.entropy.Variables;
import me.juancarloscp52.entropy.events.AbstractTimedEvent;
import me.juancarloscp52.entropy.events.EventType;


public class SpinningEvent extends AbstractTimedEvent {
    public static final EventType<SpinningEvent> TYPE = EventType.builder(SpinningEvent::new).build();

    @Override
    public void initClient() {
        Variables.mouseSpinning = true;
    }

    @Override
    public void endClient() {
        Variables.mouseSpinning = false;
        super.endClient();
    }

    @Override
    public short getDuration() {
        return (short) (super.getDuration() * 1.5);
    }

    @Override
    public EventType<SpinningEvent> getType() {
        return TYPE;
    }
}
