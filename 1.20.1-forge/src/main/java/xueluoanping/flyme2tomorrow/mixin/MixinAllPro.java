package xueluoanping.flyme2tomorrow.mixin;


import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.mcreator.horrrspvz.init.HorrrsPvzModEntities;
import net.mcreator.horrrspvz.procedures.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;


public abstract class MixinAllPro {

    @Pseudo
    @Mixin({PrimalPeaBulletAttackProcedure.class})
    public static abstract class MixinAllPro1 {
        @Inject(method = "execute", at = @At("HEAD"), cancellable = true, remap = false)
        private static void mixin$touch(Entity entity, Entity immediatesourceentity, CallbackInfo ci) {
            if (entity instanceof Player) {
                ci.cancel();
            }
        }
    }

    @Pseudo
    @Mixin({IcePeaBulletHitProcedure.class, FreezeWorkProcedure.class, IceAngelStarfruitBulletAttackProcedure.class})
    public static abstract class MixinAllPro11 {
        @Inject(method = "execute", at = @At("HEAD"), cancellable = true, remap = false)
        private static void mixin$touch(Entity entity, CallbackInfo ci) {
            if (entity instanceof Player player) {
                // player.removeEffect(HorrrsPvzModMobEffects.FREEZE.get());
                ci.cancel();
            }
        }
    }

    @Pseudo
    @Mixin({IceWatermelonPitcherBulletHitEneityProcedure.class})
    public static abstract class MixinAllPro2 {
        @WrapOperation(method = "execute", remap = false, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z"))
        private static boolean mixin$touch(LivingEntity instance, MobEffectInstance pEffectInstance, Operation<Boolean> original) {
            if (!(instance instanceof Player)) {
                original.call(instance, pEffectInstance);
            }
            return true;
        }

    }

    @Pseudo
    @Mixin({StarfruitAttackProcedure.class})
    public static abstract class MixinAllPro3 {
        // @Inject(method = "execute", at = @At("HEAD"), cancellable = true, remap = false)
        // private static void mixin$touch(LevelAccessor world, double x, double y, double z, Entity entity, CallbackInfo ci) {
        //     if (entity instanceof Player) {
        //         ci.cancel();
        //     }
        // }
    }

    @Pseudo
    @Mixin({JalapenoAttackProcedure.class, ColdSnapdragonAttackProcedure.class})
    public static abstract class MixinAllPro4 {
        @ModifyExpressionValue(method = "execute", at = @At(value = "INVOKE", target = "Ljava/util/stream/Stream;toList()Ljava/util/List;"), remap = false)
        private static <T> List<T> mixin$touch(List<T> original) {
            List<T> oo = new ArrayList<>(original);
            oo.removeIf(t -> t instanceof Player);
            return oo;
        }
    }

    @Pseudo
    @Mixin({ColdSnapdragonAttackProcedure.class})
    public static abstract class MixinAllPro41 {
        @WrapOperation(method = "execute", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z"))
        private static boolean mixin$touch(LivingEntity instance, MobEffectInstance pEffectInstance, Operation<Boolean> original) {
            if (!(instance instanceof Player)) {
                original.call(instance, pEffectInstance);
            }
            return true;
        }
    }

    @Pseudo
    @Mixin({HorrrsPvzModEntities.class})
    public static abstract class MixinAllPro0 {
        @WrapOperation(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/EntityType$Builder;of(Lnet/minecraft/world/entity/EntityType$EntityFactory;Lnet/minecraft/world/entity/MobCategory;)Lnet/minecraft/world/entity/EntityType$Builder;"))
        private static <T extends Entity> EntityType.Builder<T> mixin$init(EntityType.EntityFactory<T> pFactory, MobCategory pCategory, Operation<EntityType.Builder<T>> original) {
            if (pCategory == MobCategory.MONSTER) {
                pCategory = MobCategory.CREATURE;
            }
            return original.call(pFactory, pCategory);
        }
    }
}
