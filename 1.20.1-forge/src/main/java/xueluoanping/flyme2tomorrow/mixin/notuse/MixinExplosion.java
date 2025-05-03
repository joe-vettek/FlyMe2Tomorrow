
package xueluoanping.flyme2tomorrow.mixin.notuse;


import net.minecraft.world.level.Explosion;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({Explosion.class})
public abstract class MixinExplosion {

    // @Shadow
    // @Final
    // private DamageSource damageSource;
    //
    // @Definition(id = "Player", type = Player.class)
    // @Expression("? instanceof  Player")
    // @ModifyExpressionValue(method = "explode", at = @At("MIXINEXTRAS:EXPRESSION"))
    // private boolean mixin$touch(boolean original) {
    //     Entity directEntity = damageSource.getDirectEntity();
    //     if (ModUtil.isHorrrsPvz(directEntity)) {
    //         original = false;
    //     }
    //     return original;
    // }


}
