/*
 * @author Rick Mugridge on Sep 26, 2004
 *
 * Copyright (c) 2004 Rick Mugridge, University of Auckland, NZ
 * Released under the terms of the GNU General Public License version 2 or later.
 *
 */
package rent;

import prs.RentalItemType;

/**
 *
 */
public class RentalItemAdapter {
    private RentalItemType hireItemType;
	
	public RentalItemAdapter(RentalItemType hireItemType) {
	    this.hireItemType = hireItemType;
	}
    public Money getBond() {
        return hireItemType.getBond();
    }
    public Money getDailyRate() {
        return hireItemType.getRates().getDaily();
    }
    public int getFreeHash() {
        return hireItemType.getFreeCount();
    }
    public int getCount() {
        return 500; // hireItemType.getFreeCount(); // TEMP
    }
    public Money getHourlyRate() {
        return hireItemType.getRates().getHourly();
    }
    public String getName() {
        return hireItemType.getName();
    }
    public Money getWeeklyRate() {
        return hireItemType.getRates().getWeekly();
    }
}
