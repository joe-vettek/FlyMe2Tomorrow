package xueluoanping.flyme2tomorrow.data.lang;


import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import xueluoanping.flyme2tomorrow.FlyMe2Tomorrow;


public class Lang_ZH extends LangHelper {
    public Lang_ZH(PackOutput gen, String modid, ExistingFileHelper helper) {
        super(gen, helper, modid, "zh_cn");
    }


    @Override
    protected void addTranslations() {
        add(FlyMe2Tomorrow.rl("tomorrow").toLanguageKey("message"), "又是新的一天！");
        add(FlyMe2Tomorrow.rl("to_bed").toLanguageKey("message"), "你太累了，是时候休息了。");
        add(FlyMe2Tomorrow.rl("force_jump").toLanguageKey("config"), "强制时间流逝");
        add(FlyMe2Tomorrow.rl("jump_time").toLanguageKey("config"), "最终时间");
        add(FlyMe2Tomorrow.rl("sleep_therapy").toLanguageKey("config"), "睡疗");
    }
}
