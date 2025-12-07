package top.tigercrl.bhgm.mixin;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static top.tigercrl.bhgm.BetterHappyGhastMovement.isControllingHappyGhast;

@Mixin(Camera.class)
public abstract class CameraMixin {
    @Shadow
    private float eyeHeight;

    @Inject(method = "tick", at = @At("TAIL"))
    public void correctEyeHeight(CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer p = mc.player;
        if (mc.getCameraEntity() == p && isControllingHappyGhast(p)) {
            eyeHeight += (1.62F - p.getEyeHeight()) * 0.5F;
        }
    }
}
