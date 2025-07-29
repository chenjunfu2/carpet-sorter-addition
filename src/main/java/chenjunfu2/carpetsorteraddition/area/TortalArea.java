package chenjunfu2.carpetsorteraddition.area;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class TortalArea
{
	static final int AREA_X_MAX = 256;
	static final int AREA_Y_MAX = 128;
	static final int AREA_Z_MAX = 256;
	
	private final BlockPos posBeg;
	private final BlockPos posEnd;//区域对角坐标
	private final Box areaBox;//区域边界框
	
	private HashMap<String, SubArea> subAreaList;//按名称的映射
	private HashMap<AreaAttribute, HashSet<String>> subAreaAttrList;//按功能分区的子区域列表，用于快速查找
	private HashSet<UUID> areaSorterPlayerList;
	
	private TortalArea(BlockPos posBeg, BlockPos posEnd)
	{
		this.posBeg = posBeg;
		this.posEnd = posEnd;
		this.areaBox = new Box(this.posBeg,this.posEnd);
		this.subAreaList = new HashMap<>();
		this.subAreaAttrList = new HashMap<>();
		this.areaSorterPlayerList = new HashSet<>();
	}
	
	public static TortalArea CreateArea(BlockPos posBeg, BlockPos posEnd)
	{
		if(CheckArea(new Box(posBeg,posEnd)) != 0)
		{
			return null;
		}
		
		return new TortalArea(posBeg, posEnd);
	}
	
	public boolean AddSorterPlayerToArea(UUID playerUUID)
	{
		return this.areaSorterPlayerList.add(playerUUID);
	}
	
	public boolean RemoveSorterPlayerFromArea(UUID playerUUID)
	{
		return this.areaSorterPlayerList.remove(playerUUID);
	}
	
	public HashSet<UUID> getAreaSorterPlayerList()
	{
		return this.areaSorterPlayerList;
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
	
	//返回null：相交or重名
	public @Nullable SubArea AddSubArea(String subAreaName, AreaAttribute attribute, BlockPos posBeg, BlockPos posEnd)
	{
		//已存在同名，失败
		if(GetSubArea(subAreaName) != null)
		{
			return null;
		}
		
		//先判定区域是否在主区域内部
		Box newSubBox = new Box(posBeg,posEnd);
		if(!isBoxInside(this.areaBox,newSubBox))//不在，失败
		{
			return null;
		}
		
		//继续判定是否与现有的任何一个区域重叠
		for(var it : subAreaList.values())
		{
			if(isBoxIntersecting(newSubBox,it.getAreaBox()))
			{
				return null;//只要有任意一个相交直接失败
			}
		}
		
		//没有任何重叠并且在主区域内部（很好，可以执行插入）
		SubArea newSubArea = SubArea.CreateArea(this, subAreaName, attribute, posBeg, posEnd);
		subAreaList.put(subAreaName,newSubArea);//放入名称表
		subAreaAttrList.computeIfAbsent(newSubArea.getAttribute(), k-> new HashSet<>()).add(newSubArea.getName());//属性反查表
		
		return newSubArea;
	}
	
	
	public @Nullable SubArea GetSubArea(String subAreaName)
	{
		return this.subAreaList.get(subAreaName);
	}
	
	
	public @Nullable SubArea RemoveSubArea(String subAreaName)
	{
		var removeArea = subAreaList.remove(subAreaName);//从名称查找表移出
		if(removeArea == null)
		{
			return null;
		}
		
		//从属性->名称列表 反查表移出（如果有）
		var attrSet = subAreaAttrList.get(removeArea.getAttribute());
		if(attrSet!=null)
		{
			attrSet.remove(removeArea.getName());
			if(attrSet.isEmpty())//如果这是这个属性的最后一个子区域，删除此属性
			{
				subAreaAttrList.remove(removeArea.getAttribute());
			}
		}

		//返回被删除的子区域
		return removeArea;
	}
	
	//内部判定
	private static boolean isBoxInside(Box outer, Box inner)
	{
		return outer.contains(inner.minX, inner.minY, inner.minZ) &&
			   outer.contains(inner.maxX, inner.maxY, inner.maxZ);
	}
	
	//相交判定
	public static boolean isBoxIntersecting(Box a, Box b)
	{
		return a.intersects(b);
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
