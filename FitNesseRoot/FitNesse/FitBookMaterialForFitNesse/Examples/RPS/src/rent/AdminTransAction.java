package rent;

import java.util.Date;


import prs.AdminTransaction;
import prs.Rates;
import prs.RentEz;
import prs.RentalItemType;
import fitlibrary.DoFixture;

public class AdminTransAction extends DoFixture{
    private RentEz phs;
	private AdminTransaction transaction;
	
	public AdminTransAction(AdminTransaction transaction,
	        RentEz phs) throws Exception {
	    super(transaction);
	    this.phs = phs;
		this.transaction = transaction;
	}
	public boolean addOfTypeCostingSlashHourSlashDaySlashWeekBond(int count, 
	        String type, Rates rates, Money bond){
		return transaction.addRentalItemType(count, type, rates, bond);
	}
	public boolean completeTransaction() throws Exception{
		return transaction.complete();
	}
	public boolean addIdentifiedOfTypeLastMaintainedPeriodOfMonths(String id, 
	        String type, Date lastMaintainedDate, double monthsBtwMaintenance){
		return transaction.addIdentifiedRentalItem(id,type,lastMaintainedDate,monthsBtwMaintenance);
	}
	public boolean abortTransaction(){
		return transaction.abort();
	}
	public boolean maintenanceComplete(String itemIdentifier) {
		RentalItemType rentalItemTypeFor = phs.rentalItemTypeFor(itemIdentifier);
        return transaction.maintenanceComplete(rentalItemTypeFor, itemIdentifier);
	}
}
