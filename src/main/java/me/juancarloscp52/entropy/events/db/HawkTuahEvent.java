package me.juancarloscp52.entropy.events.db;

import me.juancarloscp52.entropy.Entropy;
import me.juancarloscp52.entropy.events.AbstractInstantEvent;
import me.juancarloscp52.entropy.events.EventType;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.horse.Llama;

public class HawkTuahEvent extends AbstractInstantEvent {
    public static final EventType<HawkTuahEvent> TYPE = EventType.builder(HawkTuahEvent::new).build();

    @Override
    public void init() {
        Entropy.getInstance().eventHandler.getActivePlayers().forEach(
                serverPlayerEntity -> {
                    for (int i = 0; i < 4; i++) {
                        Llama llama = EntityType.LLAMA.spawn(serverPlayerEntity.serverLevel(), serverPlayerEntity.blockPosition().east(2), EntitySpawnReason.EVENT);
                        llama.setTarget(serverPlayerEntity);
                    }
                }
        );
    }

    @Override
    public EventType<HawkTuahEvent> getType() {
        return TYPE;
    }
}
