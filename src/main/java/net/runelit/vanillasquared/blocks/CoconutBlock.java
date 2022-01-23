//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.runelit.vanillasquared.blocks;

import net.runelit.vanillasquared.misc.CoconutFallingBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class CoconutBlock extends FallingBlock implements Waterloggable {
    public static final BooleanProperty HANGING;
    public static final BooleanProperty field_26441;
    protected static final VoxelShape STANDING_SHAPE;
    protected static final VoxelShape HANGING_SHAPE;

    public CoconutBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(HANGING, false)).with(field_26441, false));
    }

    @Override
    public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        FallingBlockEntity fallingBlockEntity = new CoconutFallingBlockEntity(world, hit.getBlockPos().getX() + 0.5D, hit.getBlockPos().getY(), hit.getBlockPos().getZ() + 0.5D, world.getBlockState(hit.getBlockPos()));
        this.configureFallingBlockEntity(fallingBlockEntity);
        world.spawnEntity(fallingBlockEntity);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (canFallThrough(world.getBlockState(pos.down())) && canFallThrough(world.getBlockState(pos.up())) && pos.getY() >= 0) {
            FallingBlockEntity fallingBlockEntity = new CoconutFallingBlockEntity(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, world.getBlockState(pos));
            this.configureFallingBlockEntity(fallingBlockEntity);
            world.spawnEntity(fallingBlockEntity);
        }
    }

    protected void configureFallingBlockEntity(FallingBlockEntity entity) {
        entity.setHurtEntities(true);
    }

    @Override
    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity) {
        if (!fallingBlockEntity.isSilent()) {
            world.syncWorldEvent(1031, pos, 0);
        }

    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        Direction[] var3 = ctx.getPlacementDirections();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Direction direction = var3[var5];
            if (direction.getAxis() == Axis.Y) {
                BlockState blockState = (BlockState)this.getDefaultState().with(HANGING, direction == Direction.UP);
                if (blockState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
                    return (BlockState)blockState.with(field_26441, fluidState.getFluid() == Fluids.WATER);
                }
            }
        }

        return null;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return (Boolean)state.get(HANGING) ? STANDING_SHAPE : HANGING_SHAPE;
    }

    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(new Property[]{HANGING, field_26441});
    }



    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = attachedDirection(state).getOpposite();
        BlockState bstate = world.getBlockState(pos.offset(direction.getOpposite()));
        return bstate.isFullCube(world, pos.offset(direction.getOpposite()));
    }

    protected static Direction attachedDirection(BlockState state) {
        return (Boolean)state.get(HANGING) ? Direction.DOWN : Direction.UP;
    }

    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.DESTROY;
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if ((Boolean)state.get(field_26441)) {
            world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return attachedDirection(state).getOpposite() == direction && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.get(field_26441) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    static {
        HANGING = Properties.HANGING;
        field_26441 = Properties.WATERLOGGED;
        STANDING_SHAPE = VoxelShapes.union(Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D), Block.createCuboidShape(6.0D, 7.0D, 6.0D, 10.0D, 9.0D, 10.0D));
        HANGING_SHAPE = VoxelShapes.union(Block.createCuboidShape(1.0D, 2.0D, 1.0D, 15.0D, 16.0D, 15.0D), Block.createCuboidShape(6.0D, 8.0D, 6.0D, 10.0D, 10.0D, 10.0D));
    }
}
