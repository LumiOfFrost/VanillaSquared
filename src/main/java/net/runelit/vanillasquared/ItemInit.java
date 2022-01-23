package net.runelit.vanillasquared;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.runelit.vanillasquared.items.*;
import net.runelit.vanillasquared.items.NetherWalkerBoots;
import net.runelit.vanillasquared.misc.DiscItem;
import net.runelit.vanillasquared.misc.StriderArmorMaterial;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import net.runelit.vanillasquared.items.LavaPouch;
import net.runelit.vanillasquared.items.LavaPouchFilled;

public class ItemInit implements ModInitializer {

	//Other
	public static final ArmorMaterial STRIDER_ARMOR_MATERIAL = new StriderArmorMaterial();
	//Items
	public static final Item STRIDER_HIDE = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS).fireproof());
	//Tools
	public static final Item MUSIC_DISC_DOG = new DiscItem(14, ModSoundEvents.MUSIC_DISC_DOG, new FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.RARE).maxCount(1));
	public static final Item MUSIC_DISC_FLOAT = new DiscItem(15, ModSoundEvents.MUSIC_DISC_FLOAT, new FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.RARE).maxCount(1));
	public static final Item LAVA_POUCH = new LavaPouch(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1).fireproof());
	public static final Item LAVA_POUCH_FILLED = new LavaPouchFilled(new FabricItemSettings().maxCount(1).recipeRemainder(LAVA_POUCH).fireproof());
	//Armor
	public static final Item STRIDER_BOOTS = new NetherWalkerBoots(STRIDER_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Settings().maxDamage(91).group(ItemGroup.COMBAT).fireproof());
	@Override
	public void onInitialize() {

		//Items
		Registry.register(Registry.ITEM, new Identifier("vanillasquared", "strider_hide"), STRIDER_HIDE);
		//Tools
		Registry.register(Registry.ITEM, new Identifier("vanillasquared", "lava_pouch"), LAVA_POUCH);
		Registry.register(Registry.ITEM, new Identifier("vanillasquared", "lava_pouch_filled"), LAVA_POUCH_FILLED);
		Registry.register(Registry.ITEM, new Identifier("vanillasquared", "music_disc_float"), MUSIC_DISC_FLOAT);
		Registry.register(Registry.ITEM, new Identifier("vanillasquared", "music_disc_dog"), MUSIC_DISC_DOG);
		//Armor
		Registry.register(Registry.ITEM, new Identifier("vanillasquared", "stridewalker_boots"), STRIDER_BOOTS);
		//Other
		FuelRegistry.INSTANCE.add(LAVA_POUCH_FILLED, 1800);

	}
}
