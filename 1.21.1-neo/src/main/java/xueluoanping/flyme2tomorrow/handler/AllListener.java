package xueluoanping.flyme2tomorrow.handler;


import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.behavior.SleepInBed;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.TagsUpdatedEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.SleepFinishedTimeEvent;
import xueluoanping.flyme2tomorrow.FlyMe2Tomorrow;
import xueluoanping.flyme2tomorrow.config.General;

@EventBusSubscriber(modid = FlyMe2Tomorrow.MOD_ID)
public class AllListener {


    @SubscribeEvent
    public static void onTagsUpdatedEvent(PlayerEvent.Clone playerEvent) {
        if (playerEvent.isWasDeath()) {
            Player player = playerEvent.getOriginal();
            Player newPlayer = playerEvent.getEntity();
            if (player instanceof ServerPlayer serverPlayer
                    && newPlayer instanceof ServerPlayer newServerPlayer) {
                BlockPos respawnPosition = serverPlayer.getRespawnPosition();
                try {
                    if (respawnPosition != null && newServerPlayer.level() instanceof ServerLevel serverLevel) {
                        boolean force = false;
                        if (serverLevel.players().isEmpty() && General.forceJump.get()) {
                            long newTime = ((serverLevel.getDayTime() / 24000 + 1) * 24000) - 500;
                            serverLevel.setDayTime(newTime);
                            serverLevel.updateSkyBrightness();
                            force = true;
                        }
                        // newServerPlayer.moveTo(respawnPosition, 0, 0);

                        if (serverLevel.isNight() || force) {
                            newServerPlayer.startSleeping(respawnPosition);

                            if (force)
                                newServerPlayer.sendSystemMessage(Component.translatable(FlyMe2Tomorrow.rl("tomorrow").toLanguageKey("message")));
                            else
                                newServerPlayer.sendSystemMessage(Component.translatable(FlyMe2Tomorrow.rl("to_bed").toLanguageKey("message")));

                        }
                    }
                } catch (Exception e) {
                    FlyMe2Tomorrow.LOGGER.error(e);
                }
            }
        }
    }


    @SubscribeEvent
    public static void onSleepFinishedTimeEvent(SleepFinishedTimeEvent sleepFinishedTimeEvent) {
        for (Player player : sleepFinishedTimeEvent.getLevel().players()) {
            player.getFoodData().setFoodLevel(20);
            player.heal(player.getMaxHealth());
        }
    }

}
