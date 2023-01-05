package com.gizzmo.bosscustomisation.boss;

import org.bukkit.Location;
import org.bukkit.entity.Creature;

public class Boss
{
	private String NAME;
	
	private Double MAX_ALIVE;

	private Creature TYPE;
		
	private Location SPAWN;
	
	private boolean STILL_ALIVE = false;
	
	public Boss(String NAME_, Double MAX_ALIVE_, Creature TYPE_, Location SPAWN_) {
		setNAME(NAME_);
		setMaxALIVE(MAX_ALIVE_);
		setTYPE(TYPE_);
		setSPAWN(SPAWN_);
		setSTILL_ALIVE(true);
	}

	/**
	 * @return The NAME
	 */
	public String getNAME() {
		return NAME;
	}

	/**
	 * @param NEW_NAME The NAME to set
	 */
	public void setNAME(String NEW_NAME) {
		NAME = NEW_NAME;
	}

	/**
	 * @return The MAX_ALIVE
	 */
	public Double getMaxALIVE() {
		return MAX_ALIVE;
	}

	/**
	 * @param NEW_MAX_ALIVE The MAX_ALIVE to set
	 */
	public void setMaxALIVE(Double NEW_MAX_ALIVE) {
		MAX_ALIVE = NEW_MAX_ALIVE;
	}

	/**
	 * @return The TYPE
	 */
	public Creature getTYPE() {
		return TYPE;
	}

	/**
	 * @param NEW_TYPE The TYPE to set
	 */
	private void setTYPE(Creature NEW_TYPE) {
		TYPE = NEW_TYPE;
	}

	/**
	 * @return The LOCATION
	 */
	public Location getSPAWN() {
		return SPAWN;
	}

	/**
	 * @param NEW_SPAWN The LOCATION to set
	 */
	public void setSPAWN(Location NEW_SPAWN) {
		SPAWN = NEW_SPAWN;
	}

	/**
	 * @return The STILL_ALIVE
	 */
	public boolean isSTILL_ALIVE() {
		return STILL_ALIVE;
	}

	/**
	 * @param STILL_ALIVE_ The BOOLEAN to set
	 */
	public void setSTILL_ALIVE(boolean STILL_ALIVE_) {
		STILL_ALIVE = STILL_ALIVE_;
	}
}
