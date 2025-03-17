package xueluoanping.flyme2tomorrow.data.lang;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import xueluoanping.flyme2tomorrow.FlyMe2Tomorrow;

public class Lang_EN extends LangHelper {
    public Lang_EN(PackOutput gen, ExistingFileHelper helper) {
        super(gen, helper, FlyMe2Tomorrow.MOD_ID, "en_us");
    }


    @Override
    protected void addTranslations() {
        add("menu.create_vault_terminal.tittle","\"%s\"");
        add("hint.create_vault_terminal.not_open","Can not open the storage block!");
    }


}
