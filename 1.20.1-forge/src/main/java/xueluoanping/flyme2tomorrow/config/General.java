package xueluoanping.flyme2tomorrow.config;

import net.minecraftforge.common.ForgeConfigSpec;
import xueluoanping.flyme2tomorrow.FlyMe2Tomorrow;

public class General {
    public static ForgeConfigSpec COMMON_CONFIG;

    public static ForgeConfigSpec.BooleanValue forceJump;
    public static ForgeConfigSpec.IntValue jumpTime;

    public static boolean isValidRegex(Object o) {
        if (!(o instanceof String)) {
            return false;
        }
        return true;
    }

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        COMMON_BUILDER.comment("Base settings")
                .push("Base");

        forceJump = COMMON_BUILDER.comment("Force jump to tomorrow if just single player or even day.")
                .translation(FlyMe2Tomorrow.rl("force_jump").toLanguageKey("config"))
                .define("ForceJump", true);
        jumpTime = COMMON_BUILDER.comment("How long till dawn? 0 meanings the start of new day.")
                .translation(FlyMe2Tomorrow.rl("jump_time").toLanguageKey("config"))
                .defineInRange("JumpTime", -500,-1000,3000);


        COMMON_BUILDER.pop();


        COMMON_CONFIG = COMMON_BUILDER.build();


    }


}
