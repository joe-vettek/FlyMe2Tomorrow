package xueluoanping.flyme2tomorrow.client.shader;

import net.irisshaders.iris.Iris;
import net.irisshaders.iris.shaderpack.materialmap.WorldRenderingSettings;
import xueluoanping.flyme2tomorrow.mixin.SimpleMixinPlugin;

public class IrisHook {

    public static void reload() {
        if (SimpleMixinPlugin.isIrisLikeLoad()) {
            IrisAttach.reload(WorldRenderingSettings.INSTANCE.getBlockStateIds(), Iris.getIrisConfig().getShaderPackName().orElse(""));
            ((IAttach) WorldRenderingSettings.INSTANCE).swaying_garden$set(true);
        }
    }
}
