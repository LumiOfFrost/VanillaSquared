package net.runelit.vanillasquared.misc;

import com.google.common.collect.Lists;
import net.minecraft.advancement.AdvancementManager;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;
import net.runelit.vanillasquared.BlockInit;
import net.runelit.vanillasquared.ModDamageSource;
import net.runelit.vanillasquared.ModSoundEvents;
import net.minecraft.block.AnvilBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.runelit.vanillasquared.world.features.DesertPalm;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class CoconutFallingBlockEntity extends FallingBlockEntity {

    private BlockState block;
    public int timeFalling;
    public boolean dropItem;
    private boolean destroyedOnLanding;
    private boolean hurtEntities;
    private int fallHurtMax;
    private float fallHurtAmount;
    public NbtCompound blockEntityData;

    public CoconutFallingBlockEntity(World world, double x, double y, double z, BlockState block) {
        super(world, x, y, z, block);
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier) {

        System.out.println("yas");
            int i = MathHelper.ceil(fallDistance - 1.0F);
            if (i > 0) {
                List<Entity> list = Lists.newArrayList(this.world.getOtherEntities(this, this.getBoundingBox()));

                Iterator var7 = list.iterator();

                while(var7.hasNext()) {
                    ServerAdvancementLoader serverAdvancementLoader = getServer().getAdvancementLoader();
                    Entity entity = (Entity)var7.next();
                    if (Math.random() < 0.1) {
                        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ModSoundEvents.COCONUT_DAMAGE, SoundCategory.NEUTRAL, 0.5F, 1.0F);

                    }
                    entity.damage(ModDamageSource.COCONUT_FALL, 4);
                    if (entity instanceof ServerPlayerEntity) {
                    ((ServerPlayerEntity) entity).getAdvancementTracker().grantCriterion(serverAdvancementLoader.get(new Identifier("vanillasquared", "bonk")), "get_bonked");
                    }
                }
            }

        return false;
    }
}
