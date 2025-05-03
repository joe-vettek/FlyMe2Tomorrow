
package xueluoanping.flyme2tomorrow.mixin;


import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.EnderMan;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import xueluoanping.flyme2tomorrow.ModUtil;

@Mixin(value = {EnderMan.class},remap = true)
public abstract class MixinEnderman {


    @ModifyExpressionValue(method = "hurt",
            remap = true,
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/damagesource/DamageSource;is(Lnet/minecraft/tags/TagKey;)Z"))
    private boolean mixin$hurt(boolean original,
                                @Local(argsOnly = true) DamageSource damageSource) {
        Entity directEntity = damageSource.getDirectEntity();
        if (damageSource.is(DamageTypeTags.IS_PROJECTILE)
                && ModUtil.isHorrrsPvz_MushRromm(directEntity)) {
            original = false;
        }
        return original;
    }


}
