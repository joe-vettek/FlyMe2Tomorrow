package xueluoanping.flyme2tomorrow.handler;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class PlantNearestAttackableTargetGoal extends TargetGoal {
    private static final int DEFAULT_RANDOM_INTERVAL = 10;
    protected final TagKey<EntityType<?>> targetType;
    protected final int randomInterval;
    @Nullable
    protected LivingEntity target;
    /**
     * This filter is applied to the Entity search. Only matching entities will be targeted.
     */
    protected TargetingConditions targetConditions;


    public PlantNearestAttackableTargetGoal(Mob pMob, TagKey<EntityType<?>> pTargetType, boolean pMustSee, boolean pMustReach) {
        this(pMob, pTargetType, DEFAULT_RANDOM_INTERVAL, pMustSee, pMustReach, null);
    }

    public PlantNearestAttackableTargetGoal(Mob pMob, TagKey<EntityType<?>> pTargetType, int pRandomInterval, boolean pMustSee, boolean pMustReach, @Nullable Predicate<LivingEntity> pTargetPredicate) {
        super(pMob, pMustSee, pMustReach);
        this.targetType = pTargetType;
        this.randomInterval = reducedTickDelay(pRandomInterval);
        this.setFlags(EnumSet.of(Flag.TARGET));
        this.targetConditions = TargetingConditions.forCombat().range(this.getFollowDistance()).selector(pTargetPredicate);
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        if (this.randomInterval > 0 && this.mob.getRandom().nextInt(this.randomInterval) != 0) {
            return false;
        } else {
            this.findTarget();
            return this.target != null;
        }
    }

    protected AABB getTargetSearchArea(double pTargetDistance) {
        return this.mob.getBoundingBox().inflate(pTargetDistance, 4.0D, pTargetDistance);
    }

    protected void findTarget() {
        List<LivingEntity> entities = this.mob.level().getEntities(EntityTypeTest.forClass(LivingEntity.class), this.getTargetSearchArea(this.getFollowDistance()), (p_148152_) -> true);
        entities.removeIf(this::removeTest);
        this.target = this.mob.level().getNearestEntity(entities, this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
    }

    private boolean removeTest(LivingEntity livingEntity) {
        return !testIfTarget(livingEntity);
    }

    private boolean testIfTarget(LivingEntity livingEntity) {
        return livingEntity.getType().is(targetType)

                // || (this.mob).goalSelector.getAvailableGoals().stream().filter(s->s.getGoal() instanceof MeleeAttackGoal).findFirst()
                // .map(c->c!=null)
        ;
    }


    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
        this.mob.setTarget(this.target);
        super.start();
    }

    public void setTarget(@Nullable LivingEntity pTarget) {
        this.target = pTarget;
    }
}
