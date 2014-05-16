package rent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import prs.*;

import fitlibrary.ArrayFixture;
import fitlibrary.DoFixture;
import fit.Fixture;
import fitlibrary.SetFixture;
import fitlibrary.SetUpFixture;
import fitlibrary.SubsetFixture;

public class StartApplication extends DoFixture { //COPY:ALL
    private final MockClock mockClock = new MockClock();  //COPY:CLOCK
    private final DateFormat dateFormat //COPY:PARSE
    	= new SimpleDateFormat("yyyy/MM/dd HH:mm"); //COPY:PARSE
    private RentEz rentEz; //COPY:ALL
    private GeneralSetUpFixture setUp; //COPY:SETUP
    //COPY:CLOCK //COPY:TRANS //COPY:PARSEMONEY //COPY:PARSE //COPY:SETUP//COPY:DURATION 
    public StartApplication() throws Exception { //COPY:PARSEMONEY //COPY:CLOCK //COPY:SETUP
        rentEz = new RentEz(mockClock);  //COPY:CLOCK
        /*
        rentEz = new RentEz();  //COPY:SETUP
         */
        setUp = new GeneralSetUpFixture(rentEz); //COPY:SETUP
        // ...  //COPY:PARSEMONEY
        registerParseDelegate(Date.class,dateFormat); //COPY:PARSE
    } //COPY:PARSEMONEY //COPY:CLOCK //COPY:SETUP
    public Fixture setup() { //COPY:SETUP
        return setUp; //COPY:SETUP
    } //COPY:SETUP
    public Fixture enterStaff() {
        return setUp;
    }
    public Fixture staffList() {
        ArrayList staffMembers = new ArrayList();
        for (Iterator it = rentEz.getStaffMembers(); it.hasNext();) {
            prs.StaffMember staff = (prs.StaffMember) it.next();
            if (!(staff.getName().equals("Admin")))
                staffMembers.add(staff);
        }
        return new SetFixture(staffMembers);
    }
    public Fixture enterClients() {
        return setUp;
    }
    public Fixture clientList() {
        return new SetFixture(rentEz.getClients());
    }
    public Fixture client(String clientName) throws MissingException {
        return new ClientFixture(getClient(clientName));
    }
    public Fixture enterRentalItemTypes() {
        return setUp;
    }
    public Fixture rentalItemList() {
        return new SetFixture(mapRentalItemsToAdapter());
    }
    public Fixture rentalItemSubset() {
        return new SubsetFixture(mapRentalItemsToAdapter());
    }
    private ArrayList mapRentalItemsToAdapter() {
        ArrayList hireItemTypes = new ArrayList();
        for (Iterator it = rentEz.getHireItemTypes(); it.hasNext(); ) {
            prs.RentalItemType hireItemType = (prs.RentalItemType)it.next();
            hireItemTypes.add(new RentalItemAdapter(hireItemType));
        }
        return hireItemTypes;
    }
    public Fixture identifiedRentalItemSubset() {
        return new SubsetFixture(rentEz.getAllIdentifedHireItems());
    }
    public Fixture enterBuyItemTypes() {
        return setUp;
    }
    public Fixture rentalsOfClient(String clientName) throws MissingException {
        return new ArrayFixture(getClient(clientName).getHires());
//        return new SetFixture(getClient(clientName).getHires());
    }
    public Fixture beginTransactionForClientStaff(String clientName,  //COPY:TRANS
            String staffMemberName) throws MissingException {  //COPY:TRANS
        ClientTransaction transaction = rentEz.beginClientTransaction( //COPY:TRANS
                clientName,staffMemberName);  //COPY:TRANS
        return new TransActionFixture(rentEz,transaction); //COPY:TRANS
    } //COPY:TRANS
    public Fixture beginAdminTransaction(String staffMemberName) throws Exception {
        return new AdminTransAction(rentEz.beginAdminTransaction(staffMemberName),rentEz);
    }
    public boolean timeIsNow(Date time) {  //COPY:CLOCK
        mockClock.setTime(time);  //COPY:CLOCK
        return true;  //COPY:CLOCK
    }  //COPY:CLOCK
    public Fixture forMaintenanceList() {
        return new SetFixture(rentEz.forMaintenance());
    }
    public Fixture clientBookingList(String clientName) throws MissingException {
        return new SetFixture(getClient(clientName).getBookings());
    }
    public Fixture salesGoodsSubset() {
        return new SubsetFixture(rentEz.getBuyItems());
    }
    public Fixture calculateChargeFairlyPerHourPerDay(Money perHour, Money perDay) {
        return new CalculateChargeFairly(new Rates(perHour,perDay,new Money()));
    }
    public Fixture calculateChargeFairlyPerDayPerWeek(Money perDay, Money perWeek) {
        return new CalculateChargeFairly(new Rates(new Money(),perDay,perWeek));
    }
    public Fixture setUpRentals(String name) throws Exception {
        return new SetUpRentals(name);
    }
    public class SetUpRentals extends SetUpFixture {
        private ClientTransaction clientTransaction;
        private Money cost = new Money();
        
        public SetUpRentals(String clientName) throws Exception {
            clientTransaction = rentEz.beginClientTransaction(clientName,"Admin");
        }
        public void rentalItemCountStartDateEndDate(String name, int count, Date start, Date end) throws Exception {
            Duration duration = Duration.createDuration(start,end);
            Money hireCost = clientTransaction.rent(count,rentEz.getRentalItemType(name),duration);
            cost = cost.plus(hireCost);
        }
        public void tearDown() throws Exception {
            clientTransaction.payCash(cost);
            if (!clientTransaction.complete())
                throw new RuntimeException("Unable to complete");
        }
    }
    //... //COPY:STAFFSETUP
    /** Rental for a client set up 
     * @throws MissingException*/
    public Fixture rentalsForClient(String clientName)
    		throws MissingException {
        return new RentalEntryFixture(rentEz,makeDummyStaff(),
                rentEz.getClient(clientName));
    }
    public Fixture refundDollarPerHourPerDayPerWeek(Money perHour, //COPY:DURATION
            Money perDay, Money perWeek) throws Exception { //COPY:DURATION
        StaffMember staff = makeDummyStaff(); //COPY:DURATION
        Client client = makeDummyClient(); //COPY:DURATION
        RentalItemType item = makeDummyRentalItem( //COPY:DURATION
                new Rates(perHour, perDay, perWeek)); //COPY:DURATION
        return new Refunder(rentEz,staff,client,item.getName()); //COPY:DURATION
        //	    return new Refund(perHour,perDay,perWeek);
    } //COPY:DURATION
    private RentalItemType makeDummyRentalItem(Rates rates) { //COPY:DURATION
        final String name = "dummy-rental"; //COPY:DURATION
        final int count = 1; //COPY:DURATION
        final Money bond = new Money(0); //COPY:DURATION
        rentEz.removeRentalItemType(name); //COPY:DURATION
        rentEz.createRentalItemType(name,count,rates,bond); //COPY:DURATION
        return rentEz.getRentalItemType(name); //COPY:DURATION
    } //COPY:DURATION
    private Client makeDummyClient() throws RpsException { //COPY:DURATION
        final String name = "dummy-client"; //COPY:DURATION
        try { //COPY:DURATION
            return getClient(name); //COPY:DURATION
        } catch (MissingException e) { //COPY:DURATION
            rentEz.createClient(rentEz.getAdminStaff(), name, "phone"); //COPY:DURATION
            return getClient(name); //COPY:DURATION
        } //COPY:DURATION
    } //COPY:DURATION
    private StaffMember makeDummyStaff() throws MissingException { //COPY:STAFFSETUP
        final String name = "dummy-staff"; //COPY:STAFFSETUP
        try { //COPY:STAFFSETUP
            return rentEz.getStaffMember(name); //COPY:STAFFSETUP
        } catch (MissingException e) { //COPY:STAFFSETUP
            rentEz.createStaffMember(rentEz.getAdminStaff(), //COPY:STAFFSETUP
            		name,"phone"); //COPY:STAFFSETUP
            return rentEz.getStaffMember(name); //COPY:STAFFSETUP
        } //COPY:STAFFSETUP
    } //COPY:STAFFSETUP
    private Client getClient(String clientName) throws MissingException {
        return rentEz.getClient(clientName);
    }
    public class Refund extends fitlibrary.CalculateFixture {
        private Rates rates;
        
        public Refund(Money perHour, Money perDay, Money perWeek) {
            rates = new Rates(perHour,perDay,perWeek);
        }
        public Money refundPaidTimeActualTime(Duration paid, Duration actual) {
            return rates.forPeriod(paid).minus(rates.forPeriod(actual));
        }
    }
    // ...  //COPY:PARSE //COPY:DURATION //COPY:CLOCK
} //COPY:ALL
