package net.runelit.vanillasquared.misc;

import com.google.common.collect.Lists;
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

import java.util.Iterator;
import java.util.List;

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

            int i = MathHelper.ceil(fallDistance - 1.0F);
            if (i > 0) {
                List<Entity> list = Lists.newArrayList(this.world.getOtherEntities(this, this.getBoundingBox()));
                boolean bl = this.block.isIn(BlockTags.ANVIL);
                DamageSource damageSource = ModDamageSource.COCONUT_FALL;
                Iterator var7 = list.iterator();

                while(var7.hasNext()) {
                    Entity entity = (Entity)var7.next();
                    if (this.block.isOf(BlockInit.COCONUT) && Math.random() > 0.25) {
                        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ModSoundEvents.COCONUT_DAMAGE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    }
                    entity.damage(damageSource, (float)Math.min(MathHelper.floor((float)i * this.fallHurtAmount), this.fallHurtMax));
                }

                if (bl && (double)this.random.nextFloat() < 0.05000000074505806D + (double)i * 0.05D) {
                    BlockState blockState = AnvilBlock.getLandingState(this.block);
                    if (blockState == null) {
                        this.destroyedOnLanding = true;
                    } else {
                        this.block = blockState;
                    }
                }
            }

        return false;
    }
}
