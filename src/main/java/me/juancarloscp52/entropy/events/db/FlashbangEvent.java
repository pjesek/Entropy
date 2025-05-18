package me.juancarloscp52.entropy.events.db;

import me.juancarloscp52.entropy.client.EntropyClient;
import me.juancarloscp52.entropy.events.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;

import java.util.Random;

public class FlashbangEvent extends AbstractTimedEvent {
    public static final EventType<FlashbangEvent> TYPE = EventType.builder(FlashbangEvent::new).category(EventCategory.SCREEN_ASPECT).build();
    private Minecraft client;

    private static final Random RANDOM = new Random();
    int stayDurationTicks = 60;
    private final int durationTicks = 260;
    int fadeDurationTicks = durationTicks - stayDurationTicks;
    int remainingTicks;

    int nextFlashbangTicksLeft;

    @Override
    @Environment(EnvType.CLIENT)
    public void initClient() {
        client = Minecraft.getInstance();
        nextFlashbangTicksLeft = 0;
        remainingTicks = -1;
    }

    @Override
    public void render(GuiGraphics drawContext, DeltaTracker tickCounter) {
        if (remainingTicks < 0) {
            return;
        }

        client = Minecraft.getInstance();
        int screenWidth = client.getWindow().getGuiScaledWidth();
        int screenHeight = client.getWindow().getGuiScaledHeight();

        float currentOpacity;
        if(remainingTicks > fadeDurationTicks) {
            currentOpacity = 1.0f;
        } else {
            float fadingProgress = 1.0f - (float) remainingTicks / fadeDurationTicks;
            currentOpacity = 1.0f - fadingProgress;
        }

        currentOpacity = Mth.clamp(currentOpacity, 0.0f, 1.0f);
        int alpha = (int) (currentOpacity * 255.0f);

        drawContext.fill(0, 0, screenWidth, screenHeight, (alpha << 24) | 0xFFFFFF);
        remainingTicks--;
    }

    @Override
    public void tickClient() {
        nextFlashbangTicksLeft--;
        if(nextFlashbangTicksLeft <= 0) {
            remainingTicks = durationTicks;
            if(client.player != null) client.player.playSound(EntropyClient.flashbangSound, 100, 1f);
            nextFlashbangTicksLeft = RANDOM.nextInt(10, 100);
        }
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