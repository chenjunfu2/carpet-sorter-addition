package chenjunfu2.carpetsorteraddition.area;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public class SubArea
{
	private final TortalArea tortalArea;//主区域信息
	private final String name;//子区域名称
	private final AreaAttribute attribute;//子区域功能
	private final BlockPos posBeg;
	private final BlockPos posEnd;//区域对角坐标
	private final Box areaBox;//区域边界框
	
	private SubArea(TortalArea tortalArea, String name, AreaAttribute attribute, BlockPos posBeg, BlockPos posEnd)
	{
		this.tortalArea = tortalArea;
		this.name = name;
		this.attribute = attribute;
		this.posBeg = posBeg;
		this.posEnd = posEnd;
		this.areaBox = new Box(this.posBeg,this.posEnd);
	}
	
	public static SubArea CreateArea(TortalArea tortalArea, String name, AreaAttribute attribute, BlockPos posBeg, BlockPos posEnd)
	{
		return new SubArea(tortalArea,name,attribute,posBeg,posEnd);
	}
	
	String getName()
	{
		return this.name;
	}
	
	TortalArea getTortalArea()
	{
		return this.tortalArea;
	}
	
	AreaAttribute getAttribute()
	{
		return this.attribute;
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
}
