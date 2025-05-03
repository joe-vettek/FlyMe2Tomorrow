package xueluoanping.flyme2tomorrow;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;

public class ModUtil {

    public static final String HORRRS_PVZ_MOD_ID = "horrrs_pvz";

    public final static TagKey<EntityType<?>> HORRRS_PVZ = TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(HORRRS_PVZ_MOD_ID, "plants"));
    public final static TagKey<EntityType<?>> HORRRS_PVZ_GUARD = TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(HORRRS_PVZ_MOD_ID, "guard"));

    public static boolean isHorrrsPvz(Entity pEntity) {
        if (pEntity == null) return false;
        if (pEntity instanceof PathfinderMob pathfinderMob) {
            return pathfinderMob.getType().is(ModUtil.HORRRS_PVZ);
        }
        return BuiltInRegistries.ENTITY_TYPE.getKey(pEntity.getType())
                .getNamespace().equals(HORRRS_PVZ_MOD_ID);
    }

    public static boolean isHorrrsPvz_MushRromm(Entity pEntity) {
        if (pEntity == null) return false;
        ResourceLocation location = BuiltInRegistries.ENTITY_TYPE.getKey(pEntity.getType());
        String path = location.getPath();
        return location.getNamespace().equals(HORRRS_PVZ_MOD_ID)
                &&(path.equals("melancholic_mushroom_bullet")||
                path.equals("fume_shroom_bullet"));
    }

}
