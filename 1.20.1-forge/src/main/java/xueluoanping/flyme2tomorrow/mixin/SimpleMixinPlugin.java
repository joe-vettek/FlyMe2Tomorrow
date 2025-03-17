package xueluoanping.flyme2tomorrow.mixin;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

/**
 * Non-critical mixin config plugin, just disables mixins if Distant Horizons isn't present,
 * since otherwise the log gets spammed with warnings.
 */
public class SimpleMixinPlugin implements IMixinConfigPlugin {


    private static int isIrisLikeLoad = 0;

    public static boolean isIrisLikeLoad(){
        return isIrisLikeLoad ==1;
    }

    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {

        if (isIrisLikeLoad == 0) {
            try {
                Class<?> ignored = Class.forName("net.irisshaders.iris.shaderpack.materialmap.WorldRenderingSettings", false, ClassLoader.getSystemClassLoader());

                isIrisLikeLoad = 1;
            } catch (Exception ignored) {
                isIrisLikeLoad = 2;
            }
        }

        return isIrisLikeLoad();
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
