package rent;

import prs.Duration;
import prs.Rates;
import fitlibrary.CalculateFixture;

public class CalculateChargeFairly extends CalculateFixture {
	public int hours;
	public int days;
	public int weeks;
	public double cost;
	private Rates rates;
	
    public CalculateChargeFairly(Rates rates) {
		this.rates = rates;
    }
    public Money costInDollar() {
		Duration period = new Duration(hours, days, weeks).adjustForRates(rates);
		return rates.forPeriod(period);
	}
	public Money costInDollarDuration(Duration duration) {
		Duration period = duration.adjustForRates(rates);
		return rates.forPeriod(period);
	    
	}
}
