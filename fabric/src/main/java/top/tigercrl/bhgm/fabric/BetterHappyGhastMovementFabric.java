package top.tigercrl.bhgm.fabric;

import top.tigercrl.bhgm.BetterHappyGhastMovement;
import net.fabricmc.api.ModInitializer;

public final class BetterHappyGhastMovementFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        BetterHappyGhastMovement.init();
    }
}
