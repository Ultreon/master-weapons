package com.ultreon.masterweapons;

import com.mojang.datafixers.DataFixerBuilder;
import com.mojang.datafixers.schemas.Schema;
import com.ultreon.masterweapons.datafixer.MWeaponsV2;
import com.ultreon.masterweapons.init.ModBlocks;
import com.ultreon.masterweapons.init.ModConfiguredFeatures;
import com.ultreon.masterweapons.init.ModItems;
import com.ultreon.masterweapons.init.ModPlacedFeatures;
import net.minecraft.Util;
import net.minecraft.util.datafix.fixes.BlockRenameFix;
import net.minecraft.util.datafix.fixes.ItemRenameFix;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

/**
 * Master Weapons main mod class.
 *
 * @author Qboi123
 */
@Mod(MasterWeapons.MOD_ID)
public class MasterWeapons {
    public static final String MOD_ID = "masterweapons";
    public static final String MOD_NAME = "Master Weapons";
    public static final CreativeModeTab TAB = new ModCreativeTab();
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    /**
     * DO NOT INVOKE
     */
    public MasterWeapons() {
        LOGGER.info("Just loaded Master Weapons Mod initializer.");

        // Registering event handlers to mod eventbus.
        LOGGER.info("Registering event handlers to mod eventbus.");
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::serverSetup);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        // Registering items and blocks to mod eventbus.
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);
        ModConfiguredFeatures.register(modEventBus);

        // Registering event handlers to forge eventbus.
        LOGGER.info("Registering event handlers to forge eventbus.");
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
        forgeEventBus.register(this);

        registerDataFixers();
    }

    // Todo: make the data fixers work.
    private void registerDataFixers() {
        DataFixerBuilder dataFixerBuilder = new DataFixerBuilder(2);

        BiFunction<Integer, Schema, Schema> sameNamespaced = MWeaponsV2::new;
        Schema schema = dataFixerBuilder.addSchema(2, sameNamespaced);
        dataFixerBuilder.addFixer(ItemRenameFix.create(schema, "Master Weapons item id renaming", (src) -> switch (src) {
            case "masterweapons:master_sword" -> "masterweapons:ultran_sword";
            case "masterweapons:master_pickaxe" -> "masterweapons:ultran_pickaxe";
            case "masterweapons:master_axe" -> "masterweapons:ultran_axe";
            case "masterweapons:master_shovel" -> "masterweapons:ultran_shovel";
            case "masterweapons:master_hoe" -> "masterweapons:ultran_hoe";
            case "masterweapons:master_helmet" -> "masterweapons:ultran_helmet";
            case "masterweapons:master_chestplate" -> "masterweapons:ultran_chestplate";
            case "masterweapons:master_leggings" -> "masterweapons:ultran_leggings";
            case "masterweapons:master_boots" -> "masterweapons:ultran_boots";
            case "masterweapons:master_block" -> "masterweapons:ultran_block";
            case "masterweapons:master_ore" -> "masterweapons:ultran_ore";
            case "masterweapons:master_ingot" -> "masterweapons:ultran_ingot";
            case "masterweapons:master_nugget" -> "masterweapons:ultran_nugget";
            default -> src;
        }));
        dataFixerBuilder.addFixer(BlockRenameFix.create(schema, "Master Weapons block id renaming", (src) -> switch (src) {
            case "masterweapons:master_block" -> "masterweapons:ultran_block";
            case "masterweapons:master_ore" -> "masterweapons:ultran_ore";
            default -> src;
        }));

        dataFixerBuilder.build(Util.bootstrapExecutor());
    }

    /**
     * Server setup handler.
     * Used for initializing non-graphical side.
     *
     * @param event an {@link FMLDedicatedServerSetupEvent} object.
     */
    private void serverSetup(final FMLDedicatedServerSetupEvent event) {
        LOGGER.info("Master Weapons mod on dedicated server setup.");
    }

    /**
     * Common setup handler.
     * Used for dual sided initialization. (Client & server.)
     *
     * @param event an {@link FMLCommonSetupEvent} object.
     */
    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Master Weapons mod on generic setup.");
    }

    /**
     * Client setup handler.
     * Used for initializing graphical side.
     *
     * @param event an {@link FMLClientSetupEvent} object.
     */
    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Master Weapons mod on client setup.");
    }

    /**
     * Server starting handler.
     *
     * @param event an {@link FMLDedicatedServerSetupEvent} object.
     */
    @SubscribeEvent
    public void serverStart(FMLDedicatedServerSetupEvent event) {
        LOGGER.info("Server starting with Master Weapons!");
    }

    /**
     * The Master Weapons mod item group (tab).
     *
     * @author Qboi123
     */
    private static class ModCreativeTab extends CreativeModeTab {
        public ModCreativeTab() {
            super(MOD_ID);
        }

        /**
         * Create the icon for the item group.
         *
         * @return an item stack for the icon.
         */
        @NotNull
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.ULTRAN_SWORD.get());
        }
    }
}