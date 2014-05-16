package rent;
import fit.Fixture;

/*
 * @author Rick Mugridge 21/07/2004
 * Copyright (c) 2004 Rick Mugridge, University of Auckland, NZ
 * Released under the terms of the GNU General Public License version 2 or later.
 */

/**
 * 
 */
public class CalculateCharge extends fitlibrary.DoFixture{
	public Fixture dollarPerHourPerDayPerWeek(double perHour, double perDay, double perWeek) {
		return new FairChargeWithColumn(perHour,perDay,perWeek);
	}
}
