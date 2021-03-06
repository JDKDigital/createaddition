package com.mrh0.createaddition.blocks.connector;

import com.mrh0.createaddition.index.CATileEntities;
import com.mrh0.createaddition.shapes.CAShapes;
import com.simibubi.create.foundation.block.ITE;
import com.simibubi.create.foundation.utility.VoxelShaper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class ConnectorBlock extends Block implements ITE<ConnectorTileEntity> {

	public static final VoxelShaper CONNECTOR_SHAPE = CAShapes.shape(6, 0, 6, 10, 5, 10).forDirectional();
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	
	public ConnectorBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return CONNECTOR_SHAPE.get(state.get(FACING).getOpposite());
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return CATileEntities.CONNECTOR.create();
	}

	@Override
	public Class<ConnectorTileEntity> getTileEntityClass() {
		return ConnectorTileEntity.class;
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		super.onBlockHarvested(worldIn, pos, state, player);
		if(player.isCreative())
			return;
		TileEntity te = worldIn.getTileEntity(pos);
		if(te == null)
			return;
		if(!(te instanceof ConnectorTileEntity))
			return;
		ConnectorTileEntity cte = (ConnectorTileEntity) te;
		
		cte.dropWires(worldIn);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext c) {
		return this.getDefaultState().with(FACING, c.getFace().getOpposite());
	}
}
