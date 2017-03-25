package eyeq.glassdoor.block;

import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPressurePlateGlass extends BlockPressurePlate {
    public BlockPressurePlateGlass(Sensitivity sensitivity) {
        super(Material.GLASS, sensitivity);
        this.setSoundType(SoundType.GLASS);
        this.setLightOpacity(0);
        this.setHardness(0.5F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
