package io.github.lukas2005.multicraft;

import io.github.lukas2005.multicraft.items.ModItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class EventHandler {

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> e) {
        for (Item item : ModItems.ModItems.values()) {
            e.getRegistry().register(item);
            Main.proxy.registerItemRender(item);
        }

        ModCrafting.init();
    }

    @SubscribeEvent
    public static void onDrop(LivingDropsEvent e) {
        System.out.println(e.getEntityLiving().getClass().getName());
        switch (e.getEntityLiving().getClass().getName()) {
            case("net.minecraft.entity.passive.EntityParrot"):
                e.getEntityLiving().dropItem(ModItems.getItem("raw_parrot_meat"), 1);
                break;
            case("net.minecraft.entity.passive.EntityLlama"):
                for (EntityItem eItem : e.getDrops()) {
                    if (eItem.getItem().getItem() == Items.LEATHER) eItem.setDead();
                }
                int amount = 2;
                int randomInt = Main.random.nextInt(100);
                if (randomInt < 60) amount = 2;
                if (randomInt > 60 && randomInt < 80) amount = 3;
                if (randomInt > 80 && randomInt <= 100) amount = 4;
                e.getEntityLiving().dropItem(ModItems.getItem("llama_fur"), amount);
                break;
        }
    }

}
