package com.creativemd.itemphysic.config;

import com.creativemd.creativecore.client.avatar.Avatar;
import com.creativemd.creativecore.client.avatar.AvatarItemStack;
import com.creativemd.ingameconfigmanager.api.common.branch.ConfigBranch;
import com.creativemd.ingameconfigmanager.api.common.branch.ConfigSegmentCollection;
import com.creativemd.ingameconfigmanager.api.common.segment.BooleanSegment;
import com.creativemd.ingameconfigmanager.api.common.segment.IntegerSegment;
import com.creativemd.ingameconfigmanager.api.common.segment.FloatSegment;
import com.creativemd.itemphysic.ItemDummyContainer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ItemPhysicBranch extends ConfigBranch{

	public ItemPhysicBranch(String name) {
		super(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected Avatar getAvatar() {
		return new AvatarItemStack(new ItemStack(Items.feather));
	}

	@Override
	public void loadCore() {
		
	}

	@Override
	public void createConfigSegments() {
		segments.add(new IntegerSegment("despawn", "despawn time", 6000));
		segments.add(new BooleanSegment("pickup", "custom pickup", false));
		segments.add(new BooleanSegment("throw", "custom throw", true));
		segments.add(new BooleanSegment("reducecalls", "enable", false));
		segments.add(new IntegerSegment("reducecallsc", "count", 5));
		segments.add(new FloatSegment("merge-radius-item", "count", 8.0f));
	}

	@Override
	public boolean needPacket() {
		return true;
	}

	@Override
	public void onRecieveFrom(boolean isServer, ConfigSegmentCollection collection) {
		ItemDummyContainer.despawnItem = (Integer) collection.getSegmentValue("despawn");
		ItemDummyContainer.customPickup = (Boolean) collection.getSegmentValue("pickup");
		ItemDummyContainer.customThrow = (Boolean) collection.getSegmentValue("throw");
		ItemDummyContainer.reducecalls = (Boolean) collection.getSegmentValue("reducecalls");
		ItemDummyContainer.reducecallsc = (Integer) collection.getSegmentValue("reducecallsc");
		ItemDummyContainer.mergeradiusitem = (Float) ((Object)collection.getSegmentValue("merge-radius-item"));
	}

}
