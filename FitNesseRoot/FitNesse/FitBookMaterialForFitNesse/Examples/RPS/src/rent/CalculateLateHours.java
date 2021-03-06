package rent;
/*
 * @author Rick Mugridge 23/07/2004
 * Copyright (c) 2004 Rick Mugridge, University of Auckland, NZ
 * Released under the terms of the GNU General Public License version 2 or later.
 */

/**
 * 
 */
public class CalculateLateHours extends fit.ColumnFixture {
	public double hoursLate;
	public int grace;
	public boolean countGrace;
	public int highDemand;
	
	public int extraHours() {
		if (!countGrace)
			hoursLate -= grace;
		if (hoursLate >= grace)
			return highDemand + (int)hoursLate;
		else
			return 0;
	}
}
