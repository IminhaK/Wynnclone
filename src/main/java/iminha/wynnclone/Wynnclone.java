package iminha.wynnclone;

import iminha.wynnclone.item.BaseItem;
import iminha.wynnclone.item.WeaponBaseItem;
import iminha.wynnclone.item.WynnRarity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
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
    public static final RegistryObject<Item> testitem = Wynnclone.ITEMS.register("testitem", () ->
            new BaseItem(new Item.Properties().stacksTo(1).tab(Wynnclone.WYNN_TAB), WynnRarity.MYTHIC));
    public static final RegistryObject<Item> weaponitem = Wynnclone.ITEMS.register("weaponitem", () ->
            new WeaponBaseItem(new Item.Properties().stacksTo(1).tab(Wynnclone.WYNN_TAB), WynnRarity.LEGENDARY));

    public Wynnclone() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
