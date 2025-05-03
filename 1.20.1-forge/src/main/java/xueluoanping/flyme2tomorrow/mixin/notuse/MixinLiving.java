package xueluoanping.flyme2tomorrow.mixin.notuse;


import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({LivingEntity.class})
public abstract class MixinLiving {

    // @Inject(method = "hurt",
    //         at = @At(value = "HEAD"), cancellable = true)
    // private void mixin$hurt(DamageSource pSource, float pAmount, CallbackInfoReturnable<Boolean> cir, @Local(argsOnly = true) DamageSource damageSource) {
    //     Entity directEntity = damageSource.getDirectEntity();
    //     if ((Object) this instanceof Player
    //             && damageSource.is(DamageTypes.EXPLOSION)
    //             && ModUtil.isHorrrsPvz(directEntity)) {
    //         cir.setReturnValue(false);
    //     }
    // }

}
