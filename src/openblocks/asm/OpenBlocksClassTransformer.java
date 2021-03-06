package openblocks.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import openmods.asm.VisitorHelper;
import openmods.asm.VisitorHelper.TransformProvider;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import cpw.mods.fml.relauncher.FMLRelaunchLog;

public class OpenBlocksClassTransformer implements IClassTransformer {

	@Override
	public byte[] transform(final String name, String transformedName, byte[] bytes) {
		if (bytes == null) return bytes;

		if (transformedName.equals("net.minecraft.entity.player.EntityPlayer")) return VisitorHelper.apply(bytes, ClassWriter.COMPUTE_FRAMES, new TransformProvider() {
			@Override
			public ClassVisitor createVisitor(ClassVisitor cv) {
				FMLRelaunchLog.info("[OpenBlocks] Trying to patch EntityPlayer.isInBed (class: %s)", name);
				return new EntityPlayerVisitor(name, cv);
			}
		});

		if (transformedName.equals("net.minecraft.world.gen.structure.MapGenStructure")) return VisitorHelper.apply(bytes, ClassWriter.COMPUTE_FRAMES, new TransformProvider() {
			@Override
			public ClassVisitor createVisitor(ClassVisitor cv) {
				FMLRelaunchLog.info("[OpenBlocks] Trying to patch MapGenStructure (class: %s)", name);
				return new MapGenStructureVisitor(name, cv);
			}
		});

		return bytes;
	}
}
