package iminha.wynnclone;

import iminha.wynnclone.item.*;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.data.ModelProperty;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Wynnclone.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Wynnclone {
    public static final String MODID = "wynn";
    public static final Logger LOG = LogManager.getLogger(MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final CreativeModeTab WYNN_TAB = new WynnCreativeTab();

    //Items
    public static final RegistryObject<Item> lootbox = Wynnclone.ITEMS.register("lootbox", () ->
            new LootItem(new Item.Properties().stacksTo(8).tab(Wynnclone.WYNN_TAB)));
    public static final RegistryObject<Item> mageweapon = Wynnclone.ITEMS.register("mage_weapon", () ->
            new MageWeaponItem(new Item.Properties().stacksTo(1).tab(Wynnclone.WYNN_TAB)));
    public static final RegistryObject<Item> shamanweapon = Wynnclone.ITEMS.register("shaman_weapon", () ->
            new ShamanWeaponItem(new Item.Properties().stacksTo(1).tab(Wynnclone.WYNN_TAB)));
    public static final RegistryObject<Item> warriorweapon = Wynnclone.ITEMS.register("warrior_weapon", () ->
            new WarriorWeaponItem(new Item.Properties().stacksTo(1).tab(Wynnclone.WYNN_TAB)));
    public static final RegistryObject<Item> archerweapon = Wynnclone.ITEMS.register("archer_weapon", () ->
            new ArcherWeaponItem(new Item.Properties().stacksTo(1).tab(Wynnclone.WYNN_TAB)));
    public static final RegistryObject<Item> assassinweapon = Wynnclone.ITEMS.register("assassin_weapon", () ->
            new AssassinWeaponItem(new Item.Properties().stacksTo(1).tab(Wynnclone.WYNN_TAB)));
    public static final RegistryObject<Item> pickaxetool = Wynnclone.ITEMS.register("pickaxe", () ->
            new PickaxeItem(new Item.Properties().stacksTo(1).tab(Wynnclone.WYNN_TAB)));

    public Wynnclone() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WynnConfig.spec, "wynn-common.toml");
    }

    @SubscribeEvent
    public static void modelOverrides(FMLClientSetupEvent e) {
        //TODO:Item model property for loot chest based on rarity
    }
}
