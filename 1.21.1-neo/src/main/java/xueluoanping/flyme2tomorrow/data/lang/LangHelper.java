package xueluoanping.flyme2tomorrow.data.lang;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.LanguageProvider;

public abstract class LangHelper extends LanguageProvider {
    private final ExistingFileHelper helper;
    private final PackOutput output;


    public LangHelper(PackOutput output, ExistingFileHelper helper, String modid, String locale) {
        super(output, modid, locale);
        this.output = output;
        this.helper = helper;
        this.modid = modid;
        this.locale = locale;
    }


    protected abstract void addTranslations();

    private final String locale;
    public final String modid;

}
