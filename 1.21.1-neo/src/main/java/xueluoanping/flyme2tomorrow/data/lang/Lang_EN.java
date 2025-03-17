package xueluoanping.flyme2tomorrow.data.lang;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import xueluoanping.flyme2tomorrow.FlyMe2Tomorrow;

public class Lang_EN extends LangHelper {
    public Lang_EN(PackOutput gen,String modid,  ExistingFileHelper helper) {
        super(gen, helper, modid, "en_us");
    }


    @Override
    protected void addTranslations() {
        add(FlyMe2Tomorrow.rl("tomorrow").toLanguageKey("message"), "Just a new day！");
        add(FlyMe2Tomorrow.rl("to_bed").toLanguageKey("message"), "Somebody help you to bed, you are just tired.");
        add(FlyMe2Tomorrow.rl("force_jump").toLanguageKey("config"), "Force Jump");
        add(FlyMe2Tomorrow.rl("jump_time").toLanguageKey("config"), "Jump Time");
        add(FlyMe2Tomorrow.rl("sleep_therapy").toLanguageKey("config"), "Sleep Therapy");

    }
}
