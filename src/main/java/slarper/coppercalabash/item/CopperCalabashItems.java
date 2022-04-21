package slarper.coppercalabash.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import slarper.coppercalabash.CopperCalabash;

public class CopperCalabashItems {
    public static void load(){}
    private static Item register(String id, Item item){
        return Registry.register(Registry.ITEM, new Identifier(CopperCalabash.MOD_ID, id), item);
    }

    public static final Item COPPER_CALABASH;
    public static final Item COPPER_CALABASH_FULL;

    static {
        COPPER_CALABASH = register("copper_calabash", new CopperCalabashItem(new FabricItemSettings().maxCount(1).group(ItemGroup.TOOLS)));
        COPPER_CALABASH_FULL = register("copper_calabash_full", new CopperCalabashFilledItem(new FabricItemSettings().maxCount(1)));
    }
}
