package slarper.coppercalabash.item;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import slarper.camouflage.item.NickItem;

import java.util.Objects;
import java.util.Optional;

public class CopperCalabashFilledItem extends NickItem {
    public CopperCalabashFilledItem(Settings settings) {
        super(settings);
    }
    // full Copper Calabash's nickname is "item.coppercalabash.copper_calabash_filled.with." + entity's id
    public static final String COPPER_CALABASH_FILLED_NICKNAME_PREFIX = "item.coppercalabash.copper_calabash_filled.with.";


    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {

        // swing hand
        if (context.getPlayer() instanceof ClientPlayerEntity){
            context.getPlayer().swingHand(context.getHand());
        }

        // try to reveal mob from nbt
        Optional<Entity> optional = EntityType.getEntityFromNbt(context.getStack().getOrCreateSubNbt(EntityType.ENTITY_TAG_KEY),context.getWorld());
        if (optional.isPresent()){
            Entity entity = optional.get();
            entity.setPosition(context.getHitPos());
            context.getWorld().spawnEntity(entity);
            ItemStack calabash = new ItemStack(CopperCalabashItems.COPPER_CALABASH);
            Objects.requireNonNull(context.getPlayer()).setStackInHand(context.getHand(),calabash);
        }

        return super.useOnBlock(context);
    }
}
