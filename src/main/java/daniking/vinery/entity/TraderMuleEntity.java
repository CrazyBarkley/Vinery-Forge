package daniking.vinery.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RunAroundLikeCrazyGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.animal.horse.TraderLlama;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class TraderMuleEntity extends TraderLlama implements IAnimatable {
	private final AnimationFactory animationFactory = new AnimationFactory(this);
	
	public TraderMuleEntity(EntityType<? extends TraderMuleEntity> entityType, Level world) {
		super(entityType, world);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new RunAroundLikeCrazyGoal(this, 1.0));
		this.goalSelector.addGoal(3, new PanicGoal(this, 1.0));
		this.goalSelector.addGoal(4, new BreedGoal(this, 1.0));
		this.goalSelector.addGoal(5, new TemptGoal(this, 1.0, Ingredient.of(Items.HAY_BLOCK), false));
		this.goalSelector.addGoal(6, new FollowParentGoal(this, 1.0));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 6.0f));
		this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
	}
	
	private PlayState animControllerMain(AnimationEvent<?> e) {
		if (this.getTemper() > 0) {
			e.getController().setAnimation(new AnimationBuilder().addAnimation("fight / roar", false));
		} else if (this.isEating()) {
			e.getController().setAnimation(new AnimationBuilder().addAnimation("eat", false).addAnimation("eat2", true));
		} else if (e.isMoving()) {
			e.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
		} else {
			e.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
		}
		return PlayState.CONTINUE;
	}
	
	@Override
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(new AnimationController<>(this, "controller", 3F, this::animControllerMain));
	}
	
	@Override
	public AnimationFactory getFactory() {
		return animationFactory;
	}
	
	@Override
	protected Llama makeBabyLlama() {
		return null;
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		super.getAmbientSound();
		return SoundEvents.DONKEY_AMBIENT;
	}
	
	@Override
	protected SoundEvent getAngrySound() {
		super.getAngrySound();
		return SoundEvents.DONKEY_ANGRY;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		super.getDeathSound();
		return SoundEvents.DONKEY_DEATH;
	}
	
	@Override
	@Nullable
	protected SoundEvent getEatingSound() {
		return SoundEvents.DONKEY_EAT;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		super.getHurtSound(source);
		return SoundEvents.DONKEY_HURT;
	}
	
	
}