package chenjunfu2.carpetsorteraddition.area;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TortalArea
{
	static final int AREA_X_MAX = 256;
	static final int AREA_Y_MAX = 128;
	static final int AREA_Z_MAX = 256;
	
	private final BlockPos posBeg;
	private final BlockPos posEnd;//区域对角坐标
	private final Box areaBox;//区域边界框
	private HashMap<AreaAttribute,List<SubArea>> subAreaList;//按功能分区的子区域列表
	
	private TortalArea(BlockPos posBeg, BlockPos posEnd)
	{
		this.posBeg = posBeg;
		this.posEnd = posEnd;
		this.areaBox = new Box(this.posBeg,this.posEnd);
		this.subAreaList = new HashMap<>();
	}
	
	public static TortalArea CreateArea(BlockPos posBeg, BlockPos posEnd)
	{
		if(CheckArea(new Box(posBeg,posEnd)) != 0)
		{
			return null;
		}
		
		return new TortalArea(posBeg, posEnd);
	}
	
	BlockPos getPosBeg()
	{
		return this.posBeg;
	}
	
	BlockPos getPosEnd()
	{
		return this.posEnd;
	}
	
	Box getAreaBox()
	{
		return this.areaBox;
	}
	
	HashMap<AreaAttribute,List<SubArea>> getSubAreaList()
	{
		return this.subAreaList;
	}
	
	public SubArea AddSubArea(AreaAttribute attribute, BlockPos posBeg, BlockPos posEnd)
	{
		SubArea subArea = SubArea.CreateArea(this, attribute, posBeg, posEnd);
		if(subArea == null)
		{
			return null;
		}
		
		subAreaList.computeIfAbsent(attribute, k -> new ArrayList<>()).add(subArea);
		
		return subArea;
	}
	
	private static int CheckArea(Box checkBox)
	{
		int invalidAxesMask = 0;
		
		if(checkBox.getXLength() > AREA_X_MAX)
		{
			invalidAxesMask |= 0b0001;
		}
		
		if(checkBox.getYLength() > AREA_Y_MAX)
		{
			invalidAxesMask |= 0b0010;
		}
		
		if(checkBox.getZLength() > AREA_Z_MAX)
		{
			invalidAxesMask |= 0b0100;
		}
		
		return invalidAxesMask;
	}

}
