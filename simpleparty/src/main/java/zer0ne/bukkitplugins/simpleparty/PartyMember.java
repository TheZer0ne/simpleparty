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

import org.bukkit.entity.Player;

public class PartyMember{

	private Player player;
	private PlayerRank rank;
	private Party party;
	private PartyInvitation invitation;
	
	public PartyMember(Player player, PlayerRank rank){
		this.player = player;
		this.rank = rank;
		this.party = null;
		this.invitation = null;
	}
	
	public PartyMember(final Player player, final PlayerRank rank, final Party party){
		this.player = player;
		this.rank = rank;
		this.party = party;
		this.invitation = null;
	}
	
	/**
	 * set player rank
	 * @param rank new player rank
	 */
	public void setRank(final PlayerRank rank){
		this.rank = rank;
	}
	
	public void setInvitation(final PartyInvitation invite){
		this.invitation = invite;
		final String partyName = invitation.getParty().getName();
		informPlayer(partyName);
	}
	
	public PartyInvitation getInvitation(){
		return this.invitation;
	}
	
	public PlayerRank getRank(){
		return this.rank;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	/**
	 * returns the player's party
	 * @return player's party
	 */
	public Party getParty(){
		return this.party;
	}
	
	public void setParty(final Party party){
		this.party = party;
	}
	
	private void informPlayer(final String partyName){
		this.player.sendMessage("Du wurdest in die Party " + partyName + " eingeladen.");
		this.player.sendMessage("Tippe /paccept ein um die Einladung anzunehmen!");
	}
}
