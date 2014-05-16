/*
 * @author Rick Mugridge on Nov 7, 2004
 *
 * Copyright (c) 2004 Rick Mugridge, University of Auckland, NZ
 * Released under the terms of the GNU General Public License version 2 or later.
 *
 */
package rent;

import java.util.Date;

import prs.Client;
import prs.Duration;
import prs.RentEz;
import prs.Rental;
import prs.RentalItemType;
import prs.StaffMember;
import fitlibrary.SetUpFixture;

/**
 *
 */
public class RentalEntryFixture extends SetUpFixture { //COPY:ALL
    private Client client; //COPY:ALL
    private RentEz rentEz; //COPY:ALL
    private StaffMember staff; //COPY:ALL
    //COPY:ALL
    public RentalEntryFixture(RentEz rentEz, //COPY:ALL
            StaffMember staff, Client client) { //COPY:ALL
        this.rentEz = rentEz; //COPY:ALL
        this.staff = staff; //COPY:ALL
        this.client = client; //COPY:ALL
    } //COPY:ALL
    public void rentalItemCountStartDateEndDate(String rentalItem,//COPY:ALL
            int count, Date startDate, Date endDate) { //COPY:ALL
        Duration duration = Duration.createDuration(startDate,endDate); //COPY:ALL
        RentalItemType hireItemType = rentEz.getRentalItemType(rentalItem); //COPY:ALL
        Rental rental = new Rental(hireItemType,count,startDate, //COPY:ALL
        		duration,client,staff); //COPY:ALL
        client.addHire(rental); //COPY:ALL
    } //COPY:ALL
} //COPY:ALL
