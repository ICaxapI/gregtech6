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

package gregtech.compat;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi.api.Abstract_Mod;
import gregapi.code.ModData;
import gregapi.compat.CompatMods;
import gregapi.data.*;
import gregapi.util.CR;
import gregapi.util.ST;

import static gregapi.data.CS.*;

public class Compat_Recipes_ProjectRed extends CompatMods {
	public Compat_Recipes_ProjectRed(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing PR Recipes.");
		CR.delate(MD.PR, "projectred.core.part", 40);
		
		CR.shaped(ST.make(MD.PR, "projectred.core.part", 1, 40), CR.DEF_NCC, " D ", "DID", " D ", 'D', OD.itemRedstone, 'I', OP.ingot.dat(ANY.Cu));
		CR.shaped(ST.make(MD.PR, "projectred.core.part", 4, 12), CR.DEF_NCC, " X", "s ", 'X', OP.plateGem.dat(ANY.Si));
		CR.shaped(ST.make(MD.PR, "projectred.core.part", 4, 13), CR.DEF_NCC, " X", "s ", 'X', OP.plateGem.dat(MT.RedstoneAlloy));
		
		RM.sawing(64, 64, F, 1000, ST.make(MD.PR, "projectred.core.part", 1, 11), ST.make(MD.PR, "projectred.core.part", 16, 12));
		
		RM.stonetypes(MT.STONES.Marble, F
		, ST.make(MD.PR_EXPLORATION, "projectred.exploration.stone", 1, 0)
		, NI
		, NI
		, NI
		, NI
		, NI
		, NI
		, NI
		);
		
		RM.stonetypes(MT.STONES.Basalt, F
		, ST.make(MD.PR_EXPLORATION, "projectred.exploration.stone", 1, 3)
		, ST.make(MD.PR_EXPLORATION, "projectred.exploration.stone", 1, 2)
		, NI
		, NI
		, NI
		, NI
		, NI
		, NI
		);
	}
}
