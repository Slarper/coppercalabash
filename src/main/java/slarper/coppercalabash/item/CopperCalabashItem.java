package slarper.coppercalabash.item;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import slarper.camouflage.item.NickItem;

public class CopperCalabashItem extends Item {
    public CopperCalabashItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {


        // Only catch passive mob
        if (!(entity instanceof AnimalEntity)) {
            return ActionResult.PASS;
        }

        // swing hand
        if (user instanceof ClientPlayerEntity){
            user.swingHand(hand);
        }

        // delete the mob
        entity.remove(Entity.RemovalReason.UNLOADED_TO_CHUNK);

        // give user a new calabash with mob
        ItemStack calabash = new ItemStack(CopperCalabashItems.COPPER_CALABASH_FULL);
        entity.saveSelfNbt(calabash.getOrCreateSubNbt(EntityType.ENTITY_TAG_KEY));

        NickItem.putNickName(CopperCalabashFilledItem.COPPER_CALABASH_FILLED_NICKNAME_PREFIX + entity.getType().getTranslationKey(), calabash);
        // you can put model nbt here.
        user.setStackInHand(hand,calabash);

        return super.useOnEntity(stack, user, entity, hand);
    }


}
