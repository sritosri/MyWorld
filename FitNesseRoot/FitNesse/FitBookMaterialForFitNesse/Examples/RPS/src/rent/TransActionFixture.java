package rent;

import java.util.Date;


import prs.ClientTransaction;
import prs.Duration;
import prs.RentEz;
import prs.RentalItemType;

public class TransActionFixture extends fitlibrary.DoFixture { //COPY:ALL
    private ClientTransaction transaction; //COPY:ALL
    private RentEz rentEz; //COPY:ALL
    //COPY:ALL
    public TransActionFixture(RentEz rentEz, //COPY:ALL
            ClientTransaction transaction) { //COPY:ALL
        this.rentEz = rentEz; //COPY:ALL
        this.transaction = transaction; //COPY:ALL
    } //COPY:ALL
    public Money rentForWeeks(int count, String hireItemName, //COPY:CLOCK
            int weeks) { //COPY:CLOCK
        return transaction.rent(count,getRentalItemType(hireItemName), //COPY:CLOCK
                new Duration(0,0,weeks)); //COPY:CLOCK
    } //COPY:CLOCK
    public Money rentFor(int count, String hireItemName, Duration duration) { //COPY:DURATION
        return transaction.rent(count,  //COPY:DURATION
                getRentalItemType(hireItemName), //COPY:DURATION
                duration); //COPY:DURATION
    } //COPY:DURATION
    public Money totalIsDollar() {
        return transaction.getTotal();
    }
    public boolean payWithCashDollar(Money amount) { //COPY:ALL
        return transaction.payCash(amount); //COPY:ALL
    } //COPY:ALL
    public Money returnItems(int count, String name) {
        return returnItemsCostToFix(count,name,new Money());
    }
    public Money returnItemsCostToFix(int count, String name, Money cost) {
        return transaction.returnItems(cost,count,getRentalItemType(name));
    }
    private RentalItemType getRentalItemType(String name) {
        return rentEz.getRentalItemType(name);
    }
    public Money bookOnForHoursDaysWeeks(int count, String hireItemType,
            Date date, int hours, int days, int weeks) throws Exception {
        return transaction.book(count, getRentalItemType(hireItemType), date, new Duration(hours, days, weeks));
    }
    public boolean acceptBookingOfForForHoursDaysWeeks(int count,
            String hireItemType, Date bookingDate, int hours, int days, int weeks) {
        return transaction.acceptBooking(getRentalItemType(hireItemType), count, 
                bookingDate, new Duration(hours, days, weeks));
    }
    public boolean refundCashDollar(Money cashAmount) { //COPY:CLOCK
        return transaction.refundCash(cashAmount); //COPY:CLOCK
    } //COPY:CLOCK
    public Money changePeriodOfForForHoursDaysWeeksToHoursDaysWeeks(int count,
            String hireItemType, Date bookingDate, int hours, int days, int weeks,
            int newHours, int newDays, int newWeeks) {
        return transaction.changePeriodOfBooking(count, getRentalItemType(hireItemType), 
                bookingDate, new Duration(hours,
                        days, weeks), new Duration(newHours, newDays, newWeeks));
    }
    public Money cancelBookingOfForForHoursDaysWeeks(int count,
            String hireItemType, Date bookingDate, int hours, int days, int weeks) {
        return transaction.cancelBooking(count, getRentalItemType(hireItemType), bookingDate, new Duration(hours,
                days, weeks));
    }
    public Money buy(int count, String type) throws Exception {
        return transaction.buy(count, rentEz.getSalesItemType(type));
    }
    public boolean payOnAccountDollar(Money amount) {
        return transaction.payOnAccount(amount);
    }
    public boolean cancelTransaction() {
        return transaction.cancel();
    }
    public boolean completeTransaction() { //COPY:ALL
        return transaction.complete(); //COPY:ALL
    } //COPY:ALL
    //... //COPY:ALL
} //COPY:ALL
