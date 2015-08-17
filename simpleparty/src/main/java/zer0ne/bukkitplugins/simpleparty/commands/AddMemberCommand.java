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

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import zer0ne.bukkitplugins.simpleparty.Party;
import zer0ne.bukkitplugins.simpleparty.PartyInvitation;
import zer0ne.bukkitplugins.simpleparty.PartyMember;
import zer0ne.bukkitplugins.simpleparty.PlayerRank;
import zer0ne.bukkitplugins.simpleparty.errorhandling.ErrorTypes;
import static zer0ne.bukkitplugins.simpleparty.errorhandling.ErrorMessages.sendErrorMessage;
import static zer0ne.bukkitplugins.simpleparty.SimpleParty.pHandler;
import static zer0ne.bukkitplugins.simpleparty.SimpleParty.server;

public class AddMemberCommand implements CommandExecutor{

	public boolean onCommand(final CommandSender sender,final Command command,final String label,final String[] args) {
		final String cmdName = command.getName();
		if("padd".equalsIgnoreCase(cmdName)){
			if(args.length < 1 || args.length > 1){
				return false;
			}
			if(sender instanceof Player && args.length == 1 ){
				final Player playerSender = (Player) sender;
				final PartyMember pm = pHandler.getMember(playerSender);
				if(args[0].equalsIgnoreCase(playerSender.getName())){
					sendErrorMessage(playerSender, ErrorTypes.SameNameError);
					return true;
				}
				if(pm.getRank() == PlayerRank.OWNER){
					for(Player p : server.getOnlinePlayers()){
						if(p.getName().equalsIgnoreCase(args[0])){
							final Party party = pm.getParty();
							pHandler.getMember(p).setInvitation(new PartyInvitation(party, p));
							playerSender.sendMessage(args[0] + " erfolgreich eingeladen!");
							return true;
						}
						sendErrorMessage(playerSender, ErrorTypes.PlayerNotFound);
						return true;
					}
				}
				sendErrorMessage(playerSender, ErrorTypes.NoOwnerPermission);
			}
		}
		return true;
	}



}
