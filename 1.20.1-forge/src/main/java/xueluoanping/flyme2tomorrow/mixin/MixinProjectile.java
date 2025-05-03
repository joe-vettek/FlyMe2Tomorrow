
package xueluoanping.flyme2tomorrow.mixin;


import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xueluoanping.flyme2tomorrow.ModUtil;

@Mixin({Projectile.class})
public abstract class MixinProjectile extends Entity {

    protected MixinProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    // @Inject(method = "onHit", at = @At("HEAD"), cancellable = true)
    // private void mixin$touch(HitResult pResult, CallbackInfo ci) {
    //     if (pResult instanceof EntityHitResult entityHitResult)
    //         if (entityHitResult.getEntity() instanceof Player
    //                 && ModUtil.isHorrrsPvz(this)) {
    //             ci.cancel();
    //         }
    // }


}
