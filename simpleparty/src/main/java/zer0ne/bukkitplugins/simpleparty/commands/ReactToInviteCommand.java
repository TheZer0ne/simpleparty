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
import zer0ne.bukkitplugins.simpleparty.PartyInvitation;
import zer0ne.bukkitplugins.simpleparty.PartyMember;
import zer0ne.bukkitplugins.simpleparty.PlayerRank;
import zer0ne.bukkitplugins.simpleparty.errorhandling.ErrorTypes;

public class ReactToInviteCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		final String cmdName = command.getName();
		if("paccept".equalsIgnoreCase(cmdName)){
			if(sender instanceof Player){
				Player playerSender = (Player) sender;
				PartyMember pMember = pHandler.getMember(playerSender);
				PartyInvitation partyInvite = pMember.getInvitation();
				if(partyInvite == null){
					sendErrorMessage(playerSender, ErrorTypes.NoInvitationAvailable);
				}
				//TODO use config for expiration time
				if(partyInvite.checkInvitation(30)){
					Party party = partyInvite.getParty();
					if(!party.addMember(pMember)){
						//TODO notify owners that the member limit is reached
						sendErrorMessage(playerSender, ErrorTypes.MaxMembersOverflow);
						return true;
					}
					pMember.setParty(party);
					pMember.setRank(PlayerRank.MEMBER);
					return true;
				}
				sendErrorMessage(playerSender, ErrorTypes.InvitationNotValid);
			}
		}
		return true;
	}
	

}
