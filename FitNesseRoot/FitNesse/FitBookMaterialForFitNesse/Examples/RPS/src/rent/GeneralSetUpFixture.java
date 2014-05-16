package rent;

import prs.MissingException;
import prs.Rates;
import prs.RentEz;
import prs.StaffMember;
import fitlibrary.SetUpFixture;

public class GeneralSetUpFixture extends SetUpFixture { //COPY:ALL
    private RentEz rentEz; //COPY:ALL
    //COPY:ALL
    public GeneralSetUpFixture(RentEz phs) { //COPY:ALL
        this.rentEz = phs; //COPY:ALL
    } //COPY:ALL
    /** Staff member set up */
    public void namePhone(String name, String phone) throws Exception {
        rentEz.createStaffMember(admin(), name, phone);
    }
    public void staffNamePhone(String name, String phone) throws Exception { //COPY:ALL
        rentEz.createStaffMember(admin(), name, phone); //COPY:ALL
    } //COPY:ALL
    /** Client set up */
    public void namePhoneAccountLimit(String name, String phone, Money accountLimit) throws Exception {
        rentEz.createClient(admin(), name, phone);
    }
    public void clientNamePhoneAccountLimit(String name, String phone, Money accountLimit) throws Exception {
        rentEz.createClient(admin(), name, phone);
    }
    public void clientNamePhone(String name, String phone) throws Exception { //COPY:ALL
        rentEz.createClient(admin(), name, phone); //COPY:ALL
    } //COPY:ALL
    /** Rental item type setup */
    public void nameInitialHashHourlyRateDailyRateWeeklyRateBond(
            String name, int initialHash, 
            Money hourlyRate, Money dailyRate, Money weeklyRate,
            Money bond) throws Exception {
        rentEz.createRentalItemType(name,initialHash,
                new Rates(hourlyRate,dailyRate,weeklyRate),bond);
    }
    public void rentalItemNameCountDollarSlashHourDollarSlashDayDollarSlashWeekDeposit(
            String name, int initialHash,
            Money hourlyRate, Money dailyRate, Money weeklyRate,
            Money bond) throws Exception {
        rentEz.createRentalItemType(name,initialHash,
                new Rates(hourlyRate,dailyRate,weeklyRate),bond);
    }
    /** Buy item type set up */
    public void nameInitialHashSellingPrice(String name, 
            int initialHash, Money sellingPrice) throws Exception {
        rentEz.createBuyItemType(name, initialHash, sellingPrice);
    }
    // ...  //COPY:ALL
    private StaffMember admin() throws MissingException { //COPY:ALL
        return rentEz.getStaffMember("Admin"); //COPY:ALL
    } //COPY:ALL
}