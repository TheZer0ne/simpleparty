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
package zer0ne.bukkitplugins.simpleparty.errorhandling;

import org.bukkit.entity.Player;

public abstract class ErrorMessages {

	// TODO use messages.yml for error messages
	public static String getErrorMessage(final ErrorTypes err){
		if(err == ErrorTypes.PlayerNotInAParty){
			return "Du befindest dich in keiner Party";
		}
		if(err == ErrorTypes.PartyNotFound){
			return "Die angegebene Party konnte nicht gefunden werden";
		}
		if(err == ErrorTypes.PlayerNotFound){
			return "Der angegebene Spieler konnte nicht gefunden werden";
		}
		if(err == ErrorTypes.PartyExisting){
			return "Die angegebene Party exisitiert bereits!";
		}
		if(err == ErrorTypes.NoOwnerPermission){
			return "Du besitzt keine Erlaubnis Mitglieder in die Party einzuladen";
		}
		if(err == ErrorTypes.MaxMembersOverflow){
			return "Die maximale Anzahl der Partymitglieder ist bereits erreicht!";
		}
		if(err == ErrorTypes.SameNameError){
			return "Du kannst dich nicht selbst einladen!";
		}
		if(err == ErrorTypes.PlayerAlreadyInAParty){
			return "Du befindest dich bereits in einer Party. Nutze /pleave";
		}
		if(err == ErrorTypes.NoInvitationAvailable){
			return "Du besitzt keine Einladung zu einer Party";
		}
		if(err == ErrorTypes.InvitationNotValid){
			return "Die zuletzt erhaltene Einladung ist nicht mehr gültig";
		}
		return "Ein Fehler ist aufgetreten. Melde dich bitte bei einem Administrator";
	}
	
	public static void sendErrorMessage(final Player player, final ErrorTypes err){
		player.sendMessage(getErrorMessage(err));
	}
	
}
