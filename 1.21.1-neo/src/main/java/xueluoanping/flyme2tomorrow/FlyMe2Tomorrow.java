package xueluoanping.flyme2tomorrow;


import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xueluoanping.flyme2tomorrow.config.General;

import java.util.Objects;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(value = FlyMe2Tomorrow.MOD_ID,dist = Dist.CLIENT)
public class FlyMe2Tomorrow {
    public static final String MOD_ID = "flyme2tomorrow";
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final boolean useLogger = Objects.equals(System.getProperty("forgegradle.runs.dev"), "true");


    public FlyMe2Tomorrow(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, General.COMMON_CONFIG);

        if (FMLLoader.getDist() == Dist.CLIENT)
            modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);

    }

    public static void logger(Object... x) {
        // if (useLogger)
        {
            LOGGER.info(getStr(x));
        }
    }

    public static String getStr(Object... x) {
        StringBuilder output = new StringBuilder();
        for (Object i : x) {
            output.append(i).append(" ");
        }
        return output.toString();
    }

    public static void error(Object... x) {
        LOGGER.error(getStr(x));
    }

    public static ResourceLocation rl(String name) {
        return ResourceLocation.fromNamespaceAndPath(FlyMe2Tomorrow.MOD_ID, name);
    }
}
