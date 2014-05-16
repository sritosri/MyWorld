package rent;
import prs.Duration;
import prs.Rates;
import fit.Fixture;

/*
 * @author Rick Mugridge 21/07/2004
 * Copyright (c) 2004 Rick Mugridge, University of Auckland, NZ
 * Released under the terms of the GNU General Public License version 2 or later.
 */

/**
 * 
 */
public class CalculateRefund2 extends fitlibrary.DoFixture {
	public Fixture refundDollarPerHourPerDayPerWeek(Rates rates) {
		return new Refund(rates);
	}
	public Fixture refundDollarPerHourPerDayPerWeek(Money hourly, Money daily, Money weekly) {
		return new Refund(new Rates(hourly,daily,weekly));
	}
	public class Refund extends fitlibrary.CalculateFixture {
		private Rates rates;
		
		public Refund(Rates rates) {
			this.rates = rates;
		}
		public Money refundPaidTimeActualTime(Duration paid, Duration actual) {
			return rates.forPeriod(paid).minus(rates.forPeriod(actual));
		}
		public Object parse(String s, Class type) throws Exception {
			if (type == Duration.class)
			    return Duration.parse(s);
			return super.parse(s, type);
		}
	}
}
