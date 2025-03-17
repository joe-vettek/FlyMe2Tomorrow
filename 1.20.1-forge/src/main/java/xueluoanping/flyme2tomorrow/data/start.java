package xueluoanping.flyme2tomorrow.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import xueluoanping.flyme2tomorrow.FlyMe2Tomorrow;
import xueluoanping.flyme2tomorrow.data.lang.Lang_EN;
import xueluoanping.flyme2tomorrow.data.lang.Lang_ZH;

import java.util.concurrent.CompletableFuture;


public class start {
    public final static String MODID = FlyMe2Tomorrow.MOD_ID;

    public static void dataGen(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        if (event.includeServer()) {
            FlyMe2Tomorrow.logger("Generate We Data!!!");
        }

        if (event.includeClient()) {
            generator.addProvider(event.includeClient(), new Lang_EN(packOutput, helper));
            generator.addProvider(event.includeClient(), new Lang_ZH(packOutput, helper));
        }


    }
}
