package me.juancarloscp52.entropy.events.db;

import me.juancarloscp52.entropy.Entropy;
import me.juancarloscp52.entropy.events.AbstractTimedEvent;
import me.juancarloscp52.entropy.events.EventType;
import net.minecraft.world.level.GameType;

public class SpectatorEvent extends AbstractTimedEvent {
    public static final EventType<SpectatorEvent> TYPE = EventType.builder(SpectatorEvent::new).build();

    @Override
    public void init() {
        Entropy.getInstance().eventHandler.getActivePlayers().forEach(serverPlayerEntity -> serverPlayerEntity.setGameMode(GameType.SPECTATOR));
    }
    @Override
    public void end() {
        Entropy.getInstance().eventHandler.getActivePlayers().forEach(serverPlayerEntity -> serverPlayerEntity.setGameMode(GameType.SURVIVAL));
        super.end();
    }

    @Override
    public short getDuration() {
        return (short) 200;
    }

    @Override
    public EventType<SpectatorEvent> getType() {
        return TYPE;
    }
}
