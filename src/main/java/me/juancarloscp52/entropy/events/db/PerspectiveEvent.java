package me.juancarloscp52.entropy.events.db;

import me.juancarloscp52.entropy.Variables;
import me.juancarloscp52.entropy.events.AbstractTimedEvent;
import me.juancarloscp52.entropy.events.Event;
import me.juancarloscp52.entropy.events.EventCategory;
import me.juancarloscp52.entropy.events.EventType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;

import java.util.Random;

public class PerspectiveEvent extends AbstractTimedEvent {
    public static final EventType<PerspectiveEvent> TYPE = EventType.builder(PerspectiveEvent::new).category(EventCategory.CAMERA).build();
    private Minecraft client;

    private static final Random RANDOM = new Random();
    int nextChangeTicksLeft;

    @Override
    @Environment(EnvType.CLIENT)
    public void initClient() {
        client = Minecraft.getInstance();
        nextChangeTicksLeft = -1;
    }

    @Override
    public void tickClient() {
        nextChangeTicksLeft--;
        if(nextChangeTicksLeft <= 0) {
            if(RANDOM.nextInt(100) > 50) {
                Variables.thirdPersonView = false;
                Variables.frontView = true;
            } else {
                Variables.thirdPersonView = true;
                Variables.frontView = false;
            }
            nextChangeTicksLeft = RANDOM.nextInt(5, 50);
        }
    }

    @Override
    public void end() {
        Variables.thirdPersonView = false;
        Variables.frontView = false;
    }

    @Override
    public short getDuration() {
        return super.getDuration();
    }


    @Override
    public EventType<? extends Event> getType() {
        return TYPE;
    }

    @Override
    public void endClient() {
        super.endClient();
    }
}