package net.runelit.vanillasquared;

import net.fabricmc.api.ModInitializer;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSoundEvents implements ModInitializer {

    public static final Identifier ID_MUSIC_DISC_DOG = new Identifier("vanillasquared:music.music_disc_dog");
    public static SoundEvent MUSIC_DISC_DOG = new SoundEvent(ID_MUSIC_DISC_DOG);
    public static final Identifier ID_MUSIC_DISC_FLOAT = new Identifier("vanillasquared:music.music_disc_float");
    public static SoundEvent MUSIC_DISC_FLOAT = new SoundEvent(ID_MUSIC_DISC_FLOAT);
    public static final Identifier ID_COCONUT_DAMAGE = new Identifier("vanillasquared:bonc");
    public static SoundEvent COCONUT_DAMAGE = new SoundEvent(ID_COCONUT_DAMAGE);

    @Override
    public void onInitialize() {
        Registry.register(Registry.SOUND_EVENT, ModSoundEvents.ID_MUSIC_DISC_DOG, MUSIC_DISC_DOG);
        Registry.register(Registry.SOUND_EVENT, ModSoundEvents.ID_MUSIC_DISC_FLOAT, MUSIC_DISC_FLOAT);
        Registry.register(Registry.SOUND_EVENT, ModSoundEvents.ID_COCONUT_DAMAGE, COCONUT_DAMAGE);
    }
}
