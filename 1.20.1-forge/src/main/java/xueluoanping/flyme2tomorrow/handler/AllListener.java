package xueluoanping.flyme2tomorrow.handler;


import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xueluoanping.flyme2tomorrow.FlyMe2Tomorrow;
import xueluoanping.flyme2tomorrow.config.General;

@Mod.EventBusSubscriber(modid = FlyMe2Tomorrow.MOD_ID)
public class AllListener {

    @SubscribeEvent
    public static void onPlayerEventClone(PlayerEvent.Clone playerEvent) {
        if (playerEvent.isWasDeath()) {
            Player player = playerEvent.getOriginal();
            Player newPlayer = playerEvent.getEntity();
            if (player instanceof ServerPlayer serverPlayer
                    && newPlayer instanceof ServerPlayer newServerPlayer) {
                BlockPos respawnPosition = serverPlayer.getRespawnPosition();
                try {
                    if(respawnPosition==null){
                        respawnPosition=newServerPlayer.level().getSharedSpawnPos();
                    }
                    if (respawnPosition != null && newServerPlayer.level() instanceof ServerLevel serverLevel) {
                        boolean force = false;
                        if (serverLevel.players().isEmpty() && General.forceJump.get()) {
                            long newTime = ((serverLevel.getDayTime() / 24000 + 1) * 24000) - 500;
                            serverLevel.setDayTime(Math.max(0,newTime));
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

}
