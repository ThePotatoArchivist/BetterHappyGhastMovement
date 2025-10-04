package top.tigercrl.bhgm.mixin;

import net.minecraft.client.player.ClientInput;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static top.tigercrl.bhgm.BetterHappyGhastMovement.*;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin {
    @Shadow
    public ClientInput input;

    @Unique
    private int bhgm$dismountTriggerTime = 0;

    @Inject(method = "aiStep", at = @At("TAIL"))
    private void aiStep(CallbackInfo ci) {
        if (isControllingHappyGhast((LocalPlayer) (Object) this)) {
            if (bhgm$dismountTriggerTime > 0)
                bhgm$dismountTriggerTime--;

            if (!isShiftKeyDown && input.keyPresses.shift()) {
                if (bhgm$dismountTriggerTime == 0)
                    bhgm$dismountTriggerTime = 7;
                else {
                    canDismount = true;
                    bhgm$dismountTriggerTime = 0;
                }
            }

            isShiftKeyDown = input.keyPresses.shift();
        } else {
            canDismount = false;
            isShiftKeyDown = false;
            bhgm$dismountTriggerTime = 0;
        }
    }
}
