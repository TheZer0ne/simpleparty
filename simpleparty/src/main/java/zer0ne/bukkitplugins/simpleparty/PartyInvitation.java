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

import java.util.Date;

import org.bukkit.entity.Player;

/**
 * @author zer0ne
 *
 */
public class PartyInvitation {

	private transient Party party;
	private transient Player player;
	private transient long inviteTime;
	
	/**
	 * creates a new PartyInvitation object. it saves the current system time in milliseconds
	 * @param party the party the player is invited to
	 * @param player the player who is invited to the party
	 */
	public PartyInvitation(final Party party, final Player player){
		this.party = party;
		this.player = player;
		this.inviteTime = new Date().getTime();
	}
	
	public Party getParty(){
		return this.party;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public long getInvitationTimeInMilliSeconds(){
		return this.inviteTime;
	}
	
	/**
	 * returns whether this invitation is still valid or not
	 * @param expirationTime time in seconds
	 * @return true if invitation is still valid
	 */
	public boolean checkInvitation(final int expirationTime){
		long currentTime = new Date().getTime();
		if(this.inviteTime + expirationTime*1000 > currentTime){
			return true;
		}else{
			return false;
		}
	}
}
