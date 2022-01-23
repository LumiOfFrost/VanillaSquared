package net.runelit.vanillasquared;

import net.minecraft.entity.damage.DamageSource;
import net.runelit.vanillasquared.misc.MYDamageSource;

public class ModDamageSource extends DamageSource {

    protected ModDamageSource(String name) {
        super(name);
    }

    public static final DamageSource COCONUT_FALL = new MYDamageSource("coconut_fall");

}
