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

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class PartyHandler {

	private final transient List<Party> parties;
	private final transient List<PartyMember> partyMembers;

	/**
	 * creates new PartyHandler
	 */
	public PartyHandler(){
		this.parties = new ArrayList<Party>();
		this.partyMembers = new ArrayList<PartyMember>();
	}

	/**
	 * adds party to handler
	 * @param party
	 * @return true, if adding was successful
	 */
	public boolean addPartyToHandler(final Party party){
		if(checkParty(party.getName())){
			this.parties.add(party);
			return true;
		}
		return false;
	}

	/**
	 * removes party
	 * @param party
	 * @return true, if removal was successful
	 */
	public boolean removePartyFromHandler(final Party party){
		if(parties.contains(party)){
			parties.remove(party);
			return true;
		}
		return false;
	}

	/**
	 * returns all parties this handler knows about
	 * @return parties contained in this handler
	 */
	public List<Party> getParties(){
		return this.parties;
	}

	/**
	 * adds a new PartyMember object to the handler
	 * @param member PartyMember that will be added
	 * @return true, if adding was successful
	 */
	private boolean addMemberToHandler(final PartyMember member){
		if(checkPlayer(member.getPlayer())){
			partyMembers.add(member);
			return true;
		}
		return false;
	}

	/**
	 * removes member
	 */
	public void removeMemberFromHandler(){
		// not used currently
	}

	/**
	 * returns all PartyMember objects the handler knows about
	 * @return list of members
	 */
	public List<PartyMember> getMembersOfHandler(){
		return this.partyMembers;
	}

	/**
	 * returns the requested PartyMember or returns a new PartyMember if the requested one was not
	 * added to the handler yet. In this case the new PartyMember is added to the handler
	 * @param player
	 * @return requested PartyMember object
	 */
	public PartyMember getMember(final Player player){
		for(PartyMember pMember : partyMembers){
			if(pMember.getPlayer() == player){
				return pMember;
			}
		}
		final PartyMember pm = new PartyMember(player, PlayerRank.NONE);
		this.addMemberToHandler(pm);
		return pm;
	}

	private boolean checkPlayer(final Player player){
		final List<Player> players = new ArrayList<Player>();
		for(PartyMember pMember : partyMembers){
			players.add(pMember.getPlayer());
		}
		return players.contains(player);
	}

	/**
	 * returns true if the party is not found in this handler
	 * @param partyName
	 * @return true if party is not found in handler
	 */
	private boolean checkParty(final String partyName){
		final List<Party> partyList = this.getParties();
		for(Party party : partyList){
			if(partyName.equalsIgnoreCase(party.getName())) {
				return false;
			}
		}
		return true;
	}
}
