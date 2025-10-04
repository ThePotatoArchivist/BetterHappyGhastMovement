package top.tigercrl.bhgm.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.game.ServerboundPlayerInputPacket;
import net.minecraft.world.entity.player.Input;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static top.tigercrl.bhgm.BetterHappyGhastMovement.*;

@Mixin(ServerboundPlayerInputPacket.class)
public class ServerboundPlayerInputPacketMixin {
    @Mutable
    @Shadow
    @Final
    private Input input;

    @Inject(method = "<init>", at = @At("TAIL"))
    public void overrideShift(Input input, CallbackInfo ci) {
        LocalPlayer p = Minecraft.getInstance().player;
        if (isControllingHappyGhast(p)) {
            this.input = new Input(
                    input.forward(),
                    input.backward(),
                    input.left(),
                    input.right(),
                    input.jump(),
                    canDismount,
                    input.sprint()
            );
        }
    }
}
