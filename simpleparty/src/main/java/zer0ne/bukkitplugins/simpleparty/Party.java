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

/**
 * @author zer0ne
 *
 */
public class Party {

	private final transient String name;
	private transient int memberQuantity;
	private final transient int maxMembers;
	private transient final List<PartyMember> members;

	/**
	 * creates a new party 
	 * @param name name of party
	 * @param memberQuantity number of possible party member
	 * @param member owner of the new party
	 */
	public Party(final String name, final int qty, final PartyMember member){
		this.name = name;
		this.maxMembers = qty;
		this.memberQuantity = 1;
		this.members = new ArrayList<PartyMember>(qty);
		members.add(member);
		member.setParty(this);
		member.setRank(PlayerRank.OWNER);
	}

	/**
	 * creates a new party. member quantity is automatically set to 10.
	 * @param name name of party
	 * @param member owner of the new party
	 */
	public Party(final String name, final PartyMember member){
		this.name = name;
		this.maxMembers = 10;
		this.memberQuantity = 1;
		this.members = new ArrayList<PartyMember>(10);
		members.add(member);
	}

	/**
	 * adds member to party if quantity is lower than the maximum of players allowed in this party
	 * @param member
	 * @return true, if adding was successful
	 */
	public boolean addMember(final PartyMember member){
		final Party p = member.getParty();
		if(p != null){
			this.removeMember(p, member);
		}
		if(memberQuantity<maxMembers){
			this.members.add(member);
			memberQuantity+=1;
			return true;
		}	
		return false;
	}

	/**
	 * removes one party member
	 * @param member member to be removed
	 * @return true, if removal was successful
	 */
	public boolean removeMember(final PartyMember member){
		if(members.contains(member)){
			members.remove(member);
			member.setParty(null);
			member.setRank(PlayerRank.NONE);
			return true;
		}
		return false;
	}
	
	/**
	 * removes one party member of a given party.
	 * this method is used internal to remove members out of their previous party if they accept a new invitation
	 * @param party
	 * @param member
	 * @return
	 */
	private boolean removeMember(final Party party, final PartyMember member){
		if(party.getMembers().contains(member)){
			party.getMembers().remove(member);
			member.setParty(null);
			member.setRank(PlayerRank.NONE);
			return true;
		}
		return false;
	}

	/**
	 * returns the party members
	 * @return members currently in this party
	 */
	public List<PartyMember> getMembers(){
		return this.members;
	}

	/**
	 * returns name of party
	 * @return name of party
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * adds a party owner
	 * @param pMember new owner
	 */
	public void addOwner(final PartyMember pMember){
		//TODO create method
	}
}
