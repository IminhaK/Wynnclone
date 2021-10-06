package iminha.wynnclone;

import iminha.wynnclone.item.*;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(Wynnclone.MODID)
public class Wynnclone {
    public static final String MODID = "wynn";
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final CreativeModeTab WYNN_TAB = new WynnCreativeTab();

    //Items
    public static final RegistryObject<Item> lootbox = Wynnclone.ITEMS.register("lootbox", () ->
            new LootItem(new Item.Properties().stacksTo(8).tab(Wynnclone.WYNN_TAB)));
    public static final RegistryObject<Item> mageweapon = Wynnclone.ITEMS.register("mage_weapon", () ->
            new MageBaseItem(new Item.Properties().stacksTo(1).tab(Wynnclone.WYNN_TAB)));
    public static final RegistryObject<Item> shamanweapon = Wynnclone.ITEMS.register("shaman_weapon", () ->
            new ShamanBaseItem(new Item.Properties().stacksTo(1).tab(Wynnclone.WYNN_TAB)));
    public static final RegistryObject<Item> warriorweapon = Wynnclone.ITEMS.register("warrior_weapon", () ->
            new WarriorBaseItem(new Item.Properties().stacksTo(1).tab(Wynnclone.WYNN_TAB)));
    public static final RegistryObject<Item> archerweapon = Wynnclone.ITEMS.register("archer_weapon", () ->
            new ArcherBaseItem(new Item.Properties().stacksTo(1).tab(Wynnclone.WYNN_TAB)));
    public static final RegistryObject<Item> assassinweapon = Wynnclone.ITEMS.register("assassin_weapon", () ->
            new AssassinBaseItem(new Item.Properties().stacksTo(1).tab(Wynnclone.WYNN_TAB)));

    public Wynnclone() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
