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
import zer0ne.bukkitplugins.simpleparty.PartyHandler;
import zer0ne.bukkitplugins.simpleparty.PartyMember;
import zer0ne.bukkitplugins.simpleparty.PlayerRank;
import zer0ne.bukkitplugins.simpleparty.SimpleParty;
import zer0ne.bukkitplugins.simpleparty.errorhandling.ErrorTypes;

import static zer0ne.bukkitplugins.simpleparty.errorhandling.ErrorMessages.sendErrorMessage;;

/**
 * represents /pcreate <Name>
 * @author zer0ne
 *
 */
public class CreatePartyCommand implements CommandExecutor{

	private transient final PartyHandler handler = SimpleParty.pHandler;

	public boolean onCommand(final CommandSender sender,final Command command,final String label,final String[] args) {
		final String cmdName = command.getName();
		if ("pcreate".equalsIgnoreCase(cmdName)){
			if(args.length < 1 || args.length > 1){
				return false;
			}
			if(sender instanceof Player && args.length == 1 ){
				final Player player = (Player) sender;
				final PartyMember pMember = handler.getMember(player);
				if(pMember.getParty()!=null){
					sendErrorMessage(player, ErrorTypes.PlayerAlreadyInAParty);
					return true;
				}
				final Party party = new Party(args[0], pMember);
				if(!handler.addPartyToHandler(party)){
					sendErrorMessage(player, ErrorTypes.PartyExisting);
					return true;
				}
				pMember.setParty(party);
				pMember.setRank(PlayerRank.OWNER);
				sender.sendMessage("Party wurde erstellt. Name: " + args[0]);
				sender.sendMessage("Nutze /padd um Mitglieder hinzufügen.");
				return true;
			}
		}
		return true;
	}
}
