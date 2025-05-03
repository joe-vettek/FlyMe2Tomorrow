package xueluoanping.flyme2tomorrow.handler;


import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xueluoanping.flyme2tomorrow.FlyMe2Tomorrow;
import xueluoanping.flyme2tomorrow.ModUtil;
import xueluoanping.flyme2tomorrow.config.General;

@Mod.EventBusSubscriber(modid = FlyMe2Tomorrow.MOD_ID)
public class AllListener {

    // public final static TagKey<EntityType<?>> PVZ_ZENGARDEN_NO_PET = TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath("pvz_zengarden", "no_pettable_plant"));
    // public final static TagKey<EntityType<?>> PVZ_ZENGARDEN = TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath("pvz_zengarden", "faction/plants"));

    @SubscribeEvent
    public static void onEntityJoinLevelEvent(EntityJoinLevelEvent event) {
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            if (event.getEntity() instanceof LivingEntity livingEntity) {
                if (livingEntity instanceof PathfinderMob pathfinderMob) {
                    // String namespace = BuiltInRegistries.ENTITY_TYPE
                    //         .getKey(pathfinderMob.getType())
                    //         .getNamespace();
                    // if (
                    //         // namespace.equals("pvz_zengarden") ||
                    //         pathfinderMob.getType().is(HORRRS_PVZ)
                    // ) {
                    //     pathfinderMob.targetSelector.addGoal(
                    //             3, new WNearestAttackableTargetGoal<>(pathfinderMob, Enemy.class, true, true)
                    //     );
                    // }
                    if (pathfinderMob instanceof Enemy
                            || pathfinderMob instanceof Monster) {
                        // pathfinderMob.targetSelector.addGoal(
                        //         1, new PlantNearestAttackableTargetGoal(pathfinderMob, PVZ_ZENGARDEN_NO_PET, true, false)
                        // );
                        pathfinderMob.targetSelector.addGoal(
                                1, new PlantNearestAttackableTargetGoal(pathfinderMob, ModUtil.HORRRS_PVZ_GUARD, true, false)
                        );
                        // pathfinderMob.targetSelector.addGoal(
                        //         5, new PlantNearestAttackableTargetGoal(pathfinderMob, PVZ_ZENGARDEN, true, false)
                        // );
                        pathfinderMob.targetSelector.addGoal(
                                5, new PlantNearestAttackableTargetGoal(pathfinderMob, ModUtil.HORRRS_PVZ, true, false)
                        );
                    }
                }

            }
        }
    }


    @SubscribeEvent
    public static void onLivingHurtEvent(LivingAttackEvent event) {
        if (event.getEntity() instanceof Player) {
            if (ModUtil.isHorrrsPvz(event.getSource().getDirectEntity())
                    || ModUtil.isHorrrsPvz(event.getSource().getEntity())) {
                event.setCanceled(true);
            }
        }
    }


    @SubscribeEvent
    public static void onPlayerEventClone(PlayerEvent.Clone playerEvent) {
        if (playerEvent.isWasDeath()) {
            Player player = playerEvent.getOriginal();
            Player newPlayer = playerEvent.getEntity();
            if (player instanceof ServerPlayer serverPlayer
                    && newPlayer instanceof ServerPlayer newServerPlayer) {
                BlockPos respawnPosition = serverPlayer.getRespawnPosition();
                try {
                    if (respawnPosition == null) {
                        respawnPosition = newServerPlayer.level().getSharedSpawnPos();
                    }
                    if (respawnPosition != null && newServerPlayer.level() instanceof ServerLevel serverLevel) {
                        boolean force = false;
                        if (serverLevel.players().isEmpty() && General.forceJump.get()) {
                            long newTime = ((serverLevel.getDayTime() / 24000 + 1) * 24000) - General.jumpTime.get();
                            serverLevel.setDayTime(Math.max(0, newTime));
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
