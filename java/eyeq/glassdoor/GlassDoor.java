package eyeq.glassdoor;

import com.google.common.collect.Lists;
import eyeq.glassdoor.block.BlockDoorGlass;
import eyeq.glassdoor.block.BlockPressurePlateGlass;
import eyeq.util.client.model.UModelCreator;
import eyeq.util.client.model.UModelLoader;
import eyeq.util.client.model.gson.ItemmodelJsonFactory;
import eyeq.util.client.renderer.ResourceLocationFactory;
import eyeq.util.client.renderer.block.statemap.StateMapper;
import eyeq.util.client.resource.ULanguageCreator;
import eyeq.util.client.resource.lang.LanguageResourceManager;
import net.minecraft.block.*;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;

import static eyeq.glassdoor.GlassDoor.MOD_ID;

@Mod(modid = MOD_ID, version = "1.0", dependencies = "after:eyeq_util")
@Mod.EventBusSubscriber
public class GlassDoor {
    public static final String MOD_ID = "eyeq_glassdoor";

    @Mod.Instance(MOD_ID)
    public static GlassDoor instance;

    private static final ResourceLocationFactory resource = new ResourceLocationFactory(MOD_ID);

    public static Block glassDoor;
    public static Block glassPressurePlate;

    public static Item glassDoorItem;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        addRecipes();
        if(event.getSide().isServer()) {
            return;
        }
        renderBlockModels();
        renderItemModels();
        createFiles();
    }

    @SubscribeEvent
    protected static void registerBlocks(RegistryEvent.Register<Block> event) {
        glassDoor = new BlockDoorGlass().setUnlocalizedName("glassDoor");
        glassPressurePlate = new BlockPressurePlateGlass(BlockPressurePlate.Sensitivity.EVERYTHING).setUnlocalizedName("glassPressurePlate");

        GameRegistry.register(glassDoor, resource.createResourceLocation("door_glass"));
        GameRegistry.register(glassPressurePlate, resource.createResourceLocation("pressure_plate_glass"));
    }

    @SubscribeEvent
    protected static void registerItems(RegistryEvent.Register<Item> event) {
        glassDoorItem = new ItemDoor(glassDoor).setUnlocalizedName("glassDoor");

        GameRegistry.register(glassDoorItem, glassDoor.getRegistryName());
        GameRegistry.register(new ItemBlock(glassPressurePlate), glassPressurePlate.getRegistryName());
    }

    public static void addRecipes() {
        GameRegistry.addRecipe(new ItemStack(glassDoorItem, 3), "XX", "XX", "XX",
                'X', Blocks.GLASS);
        GameRegistry.addRecipe(new ItemStack(glassPressurePlate), "XX",
                'X', Blocks.GLASS);
    }

    @SideOnly(Side.CLIENT)
    public static void renderBlockModels() {
        ModelLoader.setCustomStateMapper(glassDoor, new StateMapper(resource, null, "door_glass", Lists.newArrayList(BlockDoor.POWERED)));
    }

    @SideOnly(Side.CLIENT)
    public static void renderItemModels() {
        UModelLoader.setCustomModelResourceLocation(glassDoorItem);
        UModelLoader.setCustomModelResourceLocation(glassPressurePlate);
    }

    public static void createFiles() {
        File project = new File("../1.11.2-GlassDoor");

        LanguageResourceManager language = new LanguageResourceManager();

        language.register(LanguageResourceManager.EN_US, glassDoorItem, "Glass Door");
        language.register(LanguageResourceManager.JA_JP, glassDoorItem, "ガラスのドア");
        language.register(LanguageResourceManager.EN_US, glassPressurePlate, "Glass Pressure Plate");
        language.register(LanguageResourceManager.JA_JP, glassPressurePlate, "ガラスの感圧板");

        ULanguageCreator.createLanguage(project, MOD_ID, language);

        UModelCreator.createItemJson(project, glassDoorItem, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
    }
}
