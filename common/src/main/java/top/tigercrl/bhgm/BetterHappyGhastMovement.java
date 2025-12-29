package top.tigercrl.bhgm;

import net.minecraft.world.entity.animal.happyghast.HappyGhast;
import net.minecraft.world.entity.player.Player;

public final class BetterHappyGhastMovement {
    public static final String MOD_ID = "bhgm";

    public static boolean canDismount = false;
    public static boolean isShiftKeyDown = false;

    public static void init() {
        // Write common init code here.
    }

    public static boolean isControllingHappyGhast(Player p) {
        return p != null && p.getVehicle() instanceof HappyGhast && p.canControlVehicle();
    }
}
