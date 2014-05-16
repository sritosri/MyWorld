/*
 * @author Rick Mugridge 4/10/2004
 * Copyright (c) 2004 Rick Mugridge, University of Auckland, NZ
 * Released under the terms of the GNU General Public License version 2 or later.
 */
package rent;

import prs.Rates;
import fitlibrary.DoFixture;

/**
 * 
 */
public class CalculateFairCharge extends DoFixture { //COPY:ALL
	public FairCharge ratesDollarPerHourPerDayPerWeek(Money perHour,  //COPY:ALL
	        Money perDay, Money perWeek) { //COPY:ALL
		return new FairCharge(new Rates(perHour,perDay,perWeek)); //COPY:ALL
	} //COPY:ALL
	public FairCharge dollarPerHourPerDayPerWeek(Money perHour,
	        Money perDay, Money perWeek) {
		return new FairCharge(new Rates(perHour,perDay,perWeek));
	}
} //COPY:ALL
