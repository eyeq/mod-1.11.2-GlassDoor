package eyeq.glassdoor.block;

import eyeq.glassdoor.GlassDoor;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDoorGlass extends BlockDoor {
    public BlockDoorGlass() {
        super(Material.GLASS);
        this.setHardness(3.0F);
        this.setSoundType(SoundType.GLASS);
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(GlassDoor.glassDoorItem);
    }
}
