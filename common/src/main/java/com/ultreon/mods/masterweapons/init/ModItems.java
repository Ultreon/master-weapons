package com.ultreon.mods.masterweapons.init;

import com.ultreon.mods.masterweapons.MasterWeapons;
import com.ultreon.mods.masterweapons.items.*;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;

import java.util.function.Supplier;

/**
 * Item initialization class.
 *
 * @author Qboi123
 * @see Item
 * @since 2.0.0
 */
@SuppressWarnings("unused")
public class ModItems {
    private static final DeferredRegister<Item> REGISTER = DeferredRegister.create(MasterWeapons.MOD_ID, Registry.ITEM_REGISTRY);
    public static final RegistrySupplier<SwordItem> ULTRAN_SWORD = register("ultran_sword", UltranSword::new);
    public static final RegistrySupplier<AxeItem> ULTRAN_AXE = register("ultran_axe", UltranAxe::new);
    public static final RegistrySupplier<PickaxeItem> ULTRAN_PICKAXE = register("ultran_pickaxe", UltranPickaxe::new);
    public static final RegistrySupplier<ShovelItem> ULTRAN_SHOVEL = register("ultran_shovel", UltranShovel::new);
    public static final RegistrySupplier<HoeItem> ULTRAN_HOE = register("ultran_hoe", UltranHoe::new);
    public static final RegistrySupplier<ArmorItem> ULTRAN_HELMET = register("ultran_helmet", () -> new UltranArmor(EquipmentSlot.HEAD));
    public static final RegistrySupplier<ArmorItem> ULTRAN_CHESTPLATE = register("ultran_chestplate", () -> new UltranArmor(EquipmentSlot.CHEST));
    public static final RegistrySupplier<ArmorItem> ULTRAN_LEGGINGS = register("ultran_leggings", () -> new UltranArmor(EquipmentSlot.LEGS));
    public static final RegistrySupplier<ArmorItem> ULTRAN_BOOTS = register("ultran_boots", () -> new UltranArmor(EquipmentSlot.FEET));
    public static final RegistrySupplier<ArrowItem> ULTRAN_ARROW = register("ultran_arrow", () -> new UltranArrowItem(new Item.Properties().tab(MasterWeapons.TAB)));

    public static final RegistrySupplier<Item> RAW_ULTRAN = register("raw_ultran", () -> new Item(new Item.Properties().tab(MasterWeapons.TAB)));
    public static final RegistrySupplier<Item> ULTRAN_INGOT = register("ultran_ingot", () -> new Item(new Item.Properties().tab(MasterWeapons.TAB)));
    public static final RegistrySupplier<Item> ULTRAN_NUGGET = register("ultran_nugget", () -> new Item(new Item.Properties().tab(MasterWeapons.TAB)));

    static <T extends Item> RegistrySupplier<T> register(String name, Supplier<T> supplier) {
        return REGISTER.register(name, supplier);
    }

    /**
     * Registers all items.
     */
    public static void register() {
        REGISTER.register();
    }
}