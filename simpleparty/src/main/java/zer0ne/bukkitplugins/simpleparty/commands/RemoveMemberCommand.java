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
package zer0ne.bukkitplugins.simpleparty.commands;

import static zer0ne.bukkitplugins.simpleparty.SimpleParty.pHandler;
import static zer0ne.bukkitplugins.simpleparty.SimpleParty.server;
import static zer0ne.bukkitplugins.simpleparty.errorhandling.ErrorMessages.sendErrorMessage;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import zer0ne.bukkitplugins.simpleparty.Party;
import zer0ne.bukkitplugins.simpleparty.PartyMember;
import zer0ne.bukkitplugins.simpleparty.PlayerRank;
import zer0ne.bukkitplugins.simpleparty.errorhandling.ErrorTypes;
/**
 * @author zer0ne
 *
 */
public class RemoveMemberCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		final String cmdName = command.getName();
		if("premove".equalsIgnoreCase(cmdName)){
			if(args.length < 1 && args.length > 1){
				return false;
			}
			if(sender instanceof Player && args.length == 1 ){
				Player playerSender = (Player) sender;
				PartyMember pMemberOwner = pHandler.getMember(playerSender);
				if (pMemberOwner.getRank() == PlayerRank.OWNER){
					for(Player p : server.getOnlinePlayers()){
						if(p.getName().equalsIgnoreCase(args[0])){
							PartyMember pMemberMember = pHandler.getMember(p);
							Party ownerParty = pMemberOwner.getParty();
							Party memberParty = pMemberOwner.getParty();
							if(ownerParty == memberParty){
								ownerParty.removeMember(pMemberMember);
								playerSender.sendMessage("Spieler aus der Party entfernt.");
								p.sendMessage("Du wurdest aus der Party entfernt!");
								return true;
							}
						}
					}
					sendErrorMessage(playerSender, ErrorTypes.PlayerNotFound);
				}
			}
		}
		return false;
	}
}