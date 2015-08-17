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
import static zer0ne.bukkitplugins.simpleparty.errorhandling.ErrorMessages.sendErrorMessage;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import zer0ne.bukkitplugins.simpleparty.Party;
import zer0ne.bukkitplugins.simpleparty.PartyMember;
import zer0ne.bukkitplugins.simpleparty.errorhandling.ErrorTypes;

/**
 * @author zer0ne
 *
 */
public class LeavePartyCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		String cmdName = command.getName();
		if("pleave".equalsIgnoreCase(cmdName)){
			if(sender instanceof Player){
				Player player = (Player) sender;
				PartyMember pMember = pHandler.getMember(player);
				Party party = pMember.getParty();
				if(party == null){
					sendErrorMessage(player, ErrorTypes.PlayerNotInAParty);
					return true;
				}
				party.removeMember(pMember);
				player.sendMessage("Du hast die Party verlassen!");
				return true;
			}
		}
		return false;
	}

	
}
