package xueluoanping.flyme2tomorrow.mixin;


import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xueluoanping.flyme2tomorrow.ModUtil;

@Mixin({Player.class})
public abstract class MixinPlayer {

    @Inject(method = "touch", at = @At("HEAD"), cancellable = true)
    private void mixin$touch(Entity pEntity, CallbackInfo ci) {
        if (pEntity instanceof AbstractArrow
                && ModUtil.isHorrrsPvz(pEntity)) {
            ci.cancel();
        }
    }

    // @Inject(method = "hurt",
    //         at = @At(value = "HEAD"), cancellable = true)
    // private void mixin$hurt(DamageSource pSource, float pAmount, CallbackInfoReturnable<Boolean> cir, @Local(argsOnly = true) DamageSource damageSource) {
    //     Entity directEntity = damageSource.getDirectEntity();
    //     if (damageSource.is(DamageTypes.EXPLOSION)
    //             && ModUtil.isHorrrsPvz(directEntity)) {
    //         cir.setReturnValue(false);
    //     }
    // }

}
