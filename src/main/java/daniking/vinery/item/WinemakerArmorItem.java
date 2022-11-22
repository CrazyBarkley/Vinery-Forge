package daniking.vinery.item;

import daniking.vinery.client.ClientModEvents;
import daniking.vinery.registry.ObjectRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class WinemakerArmorItem extends ArmorItem {
	public WinemakerArmorItem(ArmorMaterial material, EquipmentSlot slot, Properties settings) {
		super(material, slot, settings);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, TooltipFlag context) {
		Player player = ClientModEvents.getClientPlayer();
		if (player == null) return;
		ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
		ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
		ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
		ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
		tooltip.add(Component.nullToEmpty(""));
		tooltip.add(Component.nullToEmpty(ChatFormatting.AQUA + I18n.get("vinery.tooltip.winemaker_armor")));
		tooltip.add(Component.nullToEmpty((helmet != null && helmet.getItem() instanceof WinemakerArmorItem ? ChatFormatting.GREEN.toString() : ChatFormatting.GRAY.toString()) + "- [" + ObjectRegistry.STRAW_HAT.getDescription().getString() + "]"));
		tooltip.add(Component.nullToEmpty((chestplate != null && chestplate.getItem() instanceof WinemakerArmorItem ? ChatFormatting.GREEN.toString() : ChatFormatting.GRAY.toString()) + "- [" + ObjectRegistry.VINEMAKER_APRON.getDescription().getString() + "]"));
		tooltip.add(Component.nullToEmpty((leggings != null && leggings.getItem() instanceof WinemakerArmorItem ? ChatFormatting.GREEN.toString() : ChatFormatting.GRAY.toString()) + "- [" + ObjectRegistry.VINEMAKER_LEGGINGS.getDescription().getString() + "]"));
		tooltip.add(Component.nullToEmpty((boots != null && boots.getItem() instanceof WinemakerArmorItem ? ChatFormatting.GREEN.toString() : ChatFormatting.GRAY.toString()) + "- [" + ObjectRegistry.VINEMAKER_BOOTS.getDescription().getString() + "]"));
		tooltip.add(Component.nullToEmpty(""));
		tooltip.add(Component.nullToEmpty(ChatFormatting.GRAY + I18n.get("vinery.tooltip.winemaker_armor2")));
		tooltip.add(Component.nullToEmpty(((helmet != null && helmet.getItem() instanceof WinemakerArmorItem &&
				chestplate != null && chestplate.getItem() instanceof WinemakerArmorItem &&
				leggings != null && leggings.getItem() instanceof WinemakerArmorItem &&
				boots != null && boots.getItem() instanceof WinemakerArmorItem) ? ChatFormatting.DARK_GREEN.toString() : ChatFormatting.GRAY.toString()) + I18n.get("vinery.tooltip.winemaker_armor3")));
	}
}