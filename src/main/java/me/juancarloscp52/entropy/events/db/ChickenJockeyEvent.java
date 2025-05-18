package me.juancarloscp52.entropy.events.db;

import me.juancarloscp52.entropy.Entropy;
import me.juancarloscp52.entropy.client.EntropyClient;
import me.juancarloscp52.entropy.events.AbstractInstantEvent;
import me.juancarloscp52.entropy.events.EventType;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class ChickenJockeyEvent extends AbstractInstantEvent {
    public static final EventType<ChickenJockeyEvent> TYPE = EventType.builder(ChickenJockeyEvent::new).build();

    @Override
    public void init() {
        Entropy.getInstance().eventHandler.getActivePlayers().forEach(
                serverPlayer -> {
                    BlockPos pos = serverPlayer.blockPosition();
                    ServerLevel world = serverPlayer.serverLevel();

                    StructureTemplateManager manager = serverPlayer.server.getStructureManager();
                    ResourceLocation structureLoc = ResourceLocation.fromNamespaceAndPath("entropy", "chicken_ring");
                    BlockPos structurePos = pos.offset(-1, -1, -1);
                    //serverPlayer.server.getCommands().performPrefixedCommand(serverPlayer.server.createCommandSourceStack(), "place template entropy:test " + pos.getX() + " " + pos.getY() + " " + pos.getZ());
                    manager.get(structureLoc).ifPresent(template -> {
                        Entropy.LOGGER.info("present");
                        template.placeInWorld(world, structurePos, structurePos, new StructurePlaceSettings().setIgnoreEntities(false), world.random, Block.UPDATE_ALL);
                    });

                    BlockPos entityPos = pos.offset(4, 0, 4);
                    Chicken chicken = EntityType.CHICKEN.spawn(world, entityPos, EntitySpawnReason.EVENT);
                    Zombie zombie = EntityType.ZOMBIE.spawn(world, entityPos, EntitySpawnReason.EVENT);
                    zombie.setBaby(true);
                    zombie.startRiding(chicken);
                }
        );
    }

    @Override
    public void initClient() {
        Minecraft client = Minecraft.getInstance();
        if(client.player != null) client.player.playSound(EntropyClient.chickenJockeySound, 1, 1f);
    }

    @Override
    public EventType<ChickenJockeyEvent> getType() {
        return TYPE;
    }
}
