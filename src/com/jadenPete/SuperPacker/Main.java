package com.jadenPete.SuperPacker;

import java.lang.reflect.Field;
import org.apache.commons.lang.StringUtils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;

public class Main extends JavaPlugin {
	FileConfiguration config = getConfig();
	
	// Fired when the plugin is first enabled
	@Override
	public void onEnable(){
		this.getConfig().options().copyDefaults(true);
		this.saveDefaultConfig();
	}
	
	// Fired when plugin is disabled
	@Override
	public void onDisable() {
	
	}
	
	// Sends a message to command executor
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(sender instanceof Player){
			if(args.length == 1 && StringUtils.isNumeric(args[0])){
				ItemStack currentItem;
				
				if(((Player) sender).getInventory().getItemInMainHand() != null &&
				   ((Player) sender).getInventory().getItemInMainHand().getType() != Material.AIR){
					currentItem = ((Player) sender).getInventory().getItemInMainHand();
				} else if(((Player) sender).getInventory().getItemInOffHand() != null &&
						  ((Player) sender).getInventory().getItemInOffHand().getType() != Material.AIR){
					currentItem = ((Player) sender).getInventory().getItemInOffHand();
				} else {
					sender.sendMessage(config.getString("messages.no-item"));
					
					return true;
				}
				
				try {
					Field stackSize = Item.class.getDeclaredField("maxStackSize");
					
					stackSize.setAccessible(true);
					stackSize.setInt(currentItem, Integer.parseInt(args[0]));
				} catch(Exception e) {
					//sender.sendMessage(config.getString("messages.exception"));
					e.printStackTrace();
					
					sender.sendMessage(currentItem.);
				}
			} else {
				return false;
			}
		} else {
			sender.sendMessage(config.getString("messages.non-player"));
		}
		
		return true;
	}
}
