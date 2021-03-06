package openblocks.enchantments.flimflams;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import openblocks.api.IFlimFlamEffect;
import openmods.utils.WorldUtils;

public class SheepDyeFlimFlam implements IFlimFlamEffect {

	private static final Random random = new Random();

	@Override
	public boolean execute(EntityPlayer target) {
		World world = target.worldObj;
		AxisAlignedBB around = target.boundingBox.expand(20, 20, 20);
		List<EntitySheep> sheeps = WorldUtils.getEntitiesWithinAABB(world, EntitySheep.class, around);
		if (sheeps.isEmpty()) return false;

		EntitySheep chosenOne = sheeps.get(random.nextInt(sheeps.size()));
		int color = chosenOne.getFleeceColor();
		chosenOne.setFleeceColor(color + random.nextInt(15));
		return true;
	}

	@Override
	public String name() {
		return "sheep-dye";
	}

	@Override
	public float weight() {
		return 1;
	}

	@Override
	public float cost() {
		return 5;
	}

	@Override
	public boolean isSilent() {
		return false;
	}

}
