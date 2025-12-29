package top.tigercrl.bhgm.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.happyghast.HappyGhast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import static top.tigercrl.bhgm.BetterHappyGhastMovement.isShiftKeyDown;

@Mixin(HappyGhast.class)
public class HappyGhastMixin {
    @Inject(method = "getRiddenInput", at = @At("HEAD"), cancellable = true)
    public void getRiddenInput(Player player, Vec3 vec3, CallbackInfoReturnable<Vec3> cir) {
        float x = player.xxa;
        float y = 0;
        float z = 0;

        if (player.zza > 0) {
            z = 1;
        } else if (player.zza < 0) {
            z = -0.5F;
        }

        if (player.isJumping()) {
            y += 0.5F;
        }
        if (isShiftKeyDown) {
            y -= 0.5F;
        }

        cir.setReturnValue(new Vec3(x, y, z).scale(3.9 * ((HappyGhast) (Object) this).getAttributeValue(Attributes.FLYING_SPEED)));
    }
}
