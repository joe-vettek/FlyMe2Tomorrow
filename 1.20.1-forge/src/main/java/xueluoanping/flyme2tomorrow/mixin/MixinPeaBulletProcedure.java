package xueluoanping.flyme2tomorrow.mixin;


import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.mcreator.pvzzengarden.procedures.PeaBulletProcedure;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.LevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.List;

@Pseudo
@Mixin({PeaBulletProcedure.class})
public abstract class MixinPeaBulletProcedure {

    //
    // @Final
    // @Shadow(remap = false)
    // public static Heightmap.Types MOTION_BLOCKING_NO_LEAVES;

    // @Inject(at = {@At(value = "INVOKE",
    //         target = "Ljava/util/List;iterator()Ljava/util/Iterator;")},
    //         method = {"execute"},
    //         require = 0,
    //         remap = false)
    // private static void mixin$execute(LevelAccessor world,
    //                                   double x,
    //                                   double y,
    //                                   double z,
    //                                   Entity entity,
    //                                   CallbackInfo ci,
    //                                   @Local(ordinal = 3) double SpecialDamage,
    //                                   @Local List<Entity> _entfound) {
    //
    //     Iterator var12 = _entfound.iterator();
    //
    //     while (var12.hasNext()) {
    //         Entity entityiterator = (Entity) var12.next();
    //         float var10000;
    //         if (entity instanceof LivingEntity) {
    //             LivingEntity _livEnt = (LivingEntity) entity;
    //             var10000 = _livEnt.getHealth();
    //         } else {
    //             var10000 = -1.0F;
    //         }
    //         if (var10000 > 0.0F && entityiterator instanceof Enemy && !(entityiterator instanceof Monster)) {
    //             entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)), 4.0F);
    //             entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("pvz_zengarden:general_plant_damage")))), (float) (4.0 + SpecialDamage));
    //             if (entity.getPersistentData().getDouble("BulletSpecial") >= 1.0) {
    //                 entityiterator.setSecondsOnFire(2);
    //             } else if (entity.getPersistentData().getDouble("BulletSpecial") == -1.0) {
    //                 LivingEntity _entity;
    //                 if (entityiterator instanceof LivingEntity) {
    //                     _entity = (LivingEntity) entityiterator;
    //                     if (!_entity.level().isClientSide()) {
    //                         _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 80, 2));
    //                     }
    //                 }
    //                 if (entityiterator instanceof LivingEntity) {
    //                     _entity = (LivingEntity) entityiterator;
    //                     if (!_entity.level().isClientSide()) {
    //                         _entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 1));
    //                     }
    //                 }
    //                 entityiterator.setTicksFrozen(entityiterator.getTicksFrozen() + 400);
    //             }
    //         }
    //     }
    // }

    // @Definition(id = "entityiterator", local = @Local(ordinal = 1, type = Entity.class))
    @Definition(id = "Monster", type = Monster.class)
    @Expression("? instanceof Monster")
    @ModifyExpressionValue(method = "execute", at = @At("MIXINEXTRAS:EXPRESSION"))
    private static boolean mixin$execute_also_enemy_should_be_attacked(boolean original, @Local(ordinal = 1) Entity entity) {
        if (entity instanceof Enemy)
            original = true;
        return original;
    }
}
