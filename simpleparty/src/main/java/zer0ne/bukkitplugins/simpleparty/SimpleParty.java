/**
 *   Copyright 2015 zer0ne (https://github.com/TheZer0ne)
 *   This file is part of simpleparty.
 *
 *   simpleparty is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   simpleparty is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with simpleparty.  If not, see <http://www.gnu.org/licenses/>.
 */
package zer0ne.bukkitplugins.simpleparty;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import zer0ne.bukkitplugins.simpleparty.commands.AddMemberCommand;
import zer0ne.bukkitplugins.simpleparty.commands.CreatePartyCommand;
import zer0ne.bukkitplugins.simpleparty.commands.LeavePartyCommand;
import zer0ne.bukkitplugins.simpleparty.commands.ReactToInviteCommand;
import zer0ne.bukkitplugins.simpleparty.commands.RemoveMemberCommand;

public class SimpleParty extends JavaPlugin {

	public static PartyHandler pHandler = new PartyHandler();
	/**
	 * used in different command classes to iterate over online players
	 */
	public static Server server = Bukkit.getServer();
	
	@Override
	public void onEnable(){
		//TODO add logic
		registerCommands();
	}
	
	@Override
	public void onDisable(){
		//TODO add logic
	}
	
	private void registerCommands(){
		this.getCommand("pcreate").setExecutor(new CreatePartyCommand());
		this.getCommand("padd").setExecutor(new AddMemberCommand());
		this.getCommand("premove").setExecutor(new RemoveMemberCommand());
		this.getCommand("pleave").setExecutor(new LeavePartyCommand());
		this.getCommand("paccept").setExecutor(new ReactToInviteCommand());
	}
	
	
}
