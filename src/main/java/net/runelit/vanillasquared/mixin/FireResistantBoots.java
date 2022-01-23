package net.runelit.vanillasquared.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.runelit.vanillasquared.ItemInit;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class FireResistantBoots extends Entity {

    ItemStack striderBoots = new ItemStack(ItemInit.STRIDER_BOOTS);

    @Shadow
    public abstract ItemStack getEquippedStack(EquipmentSlot equipmentSlot);

    public FireResistantBoots(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "applyEnchantmentsToDamage", at = @At("RETURN"), cancellable = true)
    public void applyEnchantmantsToDamage(DamageSource source, float amount, CallbackInfoReturnable info) {
        if(source.isFire() && this.getEquippedStack(EquipmentSlot.FEET).isItemEqualIgnoreDamage(striderBoots)) {
            ItemStack headSlot = this.getEquippedStack(EquipmentSlot.FEET);
            headSlot.damage(2, ((LivingEntity) (Object)this), (livingEntityx) -> {
                livingEntityx.sendEquipmentBreakStatus(EquipmentSlot.FEET);
            });
            amount *= 0.5f;

        }

        info.setReturnValue(amount);

    }

}
