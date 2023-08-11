/**
 * Copyright (c) 2023 GregTech-6 Team
 *
 * This file is part of GregTech.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GregTech. If not, see <http://www.gnu.org/licenses/>.
 */

package gregtech.worldgen;

import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.IL;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import twilightforest.TFTreasure;

import java.util.HashMap;
import java.util.Random;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class TwilightTreasureReplacer extends TFTreasure {
	public static final HashMap<String, TwilightTreasureReplacer> TWILIGHT_TREASURE = new HashMap<>();
	
	public final TFTreasure mTreasure;
	public final String mCategory;
	public final short mChestID;
	public final int mTreasureID;
	
	public static TFTreasure create(TFTreasure aTreasure, int aIndex, String aCategory) {return new TwilightTreasureReplacer(aTreasure, aIndex, aCategory);}
	public static TFTreasure create(TFTreasure aTreasure, int aIndex, String aCategory, long aChestID) {return new TwilightTreasureReplacer(aTreasure, aIndex, aCategory, aChestID);}
	public TwilightTreasureReplacer(TFTreasure aTreasure, int aIndex, String aCategory) {this(aTreasure, aIndex, aCategory, (short)32745);}
	public TwilightTreasureReplacer(TFTreasure aTreasure, int aIndex, String aCategory, long aChestID) {
		super(aIndex);
		mCategory = "twilightforest:"+aCategory;
		mChestID = (short)aChestID;
		mTreasureID = aIndex;
		mTreasure = aTreasure;
		TWILIGHT_TREASURE.put(mCategory, this);
		ST.LOOT_TABLES.add(mCategory);
	}
	
	@Override public boolean generate(World aWorld, Random aRandom, int aX, int aY, int aZ) {return generate(aWorld, aRandom, aX, aY, aZ, Blocks.chest);}
	@Override public boolean generate(World aWorld, Random aRandom, int aX, int aY, int aZ, Block aChest) {
		MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
		if (tRegistry == null) return super.generate(aWorld, aRandom, aX, aY, aZ, aChest);
		tRegistry.mBlock.placeBlock(aWorld, aX, aY, aZ, SIDE_UNKNOWN, mChestID, UT.NBT.make(NBT_FACING, ALL_SIDES_HORIZONTAL[RNGSUS.nextInt(ALL_SIDES_HORIZONTAL.length)], NBT_TRAPPED, T, "gt.dungeonloot", mCategory), F, T);
		return T;
	}
	
	public static boolean generate(IInventory aInventory, String aCategory) {
		TwilightTreasureReplacer tTreasure = TWILIGHT_TREASURE.get(aCategory);
		return tTreasure != null && tTreasure.generate(aInventory);
	}
	
	public boolean generate(IInventory aInventory) {
		boolean rReturn = T;
		for (int i = 0, j = (mTreasureID == 13                                           ?  8 :                          10); i < j; i++) rReturn &= addToInventory(aInventory, mTreasure.getCommonItem  (RNGSUS));
		for (int i = 0, j = (mTreasureID == 13                                           ? 27 :                           6); i < j; i++) rReturn &= addToInventory(aInventory, mTreasure.getUncommonItem(RNGSUS));
		for (int i = 0, j = (mTreasureID == 13 || mTreasureID == 21 || mTreasureID == 22 ?  1 : mTreasureID == 10 ?  4 :  2); i < j; i++) rReturn &= addToInventory(aInventory, mTreasure.getRareItem    (RNGSUS));
		return rReturn;
	}
	
	public boolean addToInventory(IInventory aInventory, ItemStack aStack) {
		int tSlot = findEmptySlot(aInventory);
		if (tSlot == -1) return F;
		aInventory.setInventorySlotContents(tSlot, IL.TF_Uncrafting.equal(aStack, T, T) ? IL.TF_Transformation_Powder.get(12+RNGSUS.nextInt(13)) : aStack);
		return T;
	}
	
	public int findEmptySlot(IInventory aInventory) {
		int j = aInventory.getSizeInventory();
		for (int i = 0; i < 100; i++) {int k = RNGSUS.nextInt(j); if (aInventory.getStackInSlot(k) == null) return k;}
		for (int i = 0; i <   j; i++) if (aInventory.getStackInSlot(i) == null) return i;
		return -1;
	}
}
