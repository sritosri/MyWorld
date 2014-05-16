/*
 * @author Rick Mugridge on Nov 8, 2004
 *
 * Copyright (c) 2004 Rick Mugridge, University of Auckland, NZ
 * Released under the terms of the GNU General Public License version 2 or later.
 *
 */
package rent;

import java.util.Date;

import prs.Client;
import prs.ClientTransaction;
import prs.Duration;
import prs.RentEz;
import prs.StaffMember;
import fitlibrary.CalculateFixture;

/**
 *
 */
public class Refunder extends CalculateFixture { //COPY:ALL
    private RentEz rentEz; //COPY:ALL
    private StaffMember staff; //COPY:ALL
    private Client client; //COPY:ALL
    private String rentalItemName; //COPY:ALL
    //COPY:ALL
    public Refunder(RentEz rentEz, StaffMember staff,  //COPY:ALL
            Client client, String rentalItemName) { //COPY:ALL
        this.rentEz = rentEz; //COPY:ALL
        this.staff = staff; //COPY:ALL
        this.client = client; //COPY:ALL
        this.rentalItemName = rentalItemName; //COPY:ALL
    } //COPY:ALL
    public Money refundPaidTimeActualTime(Duration paidDuration, //COPY:ALL
            Duration actual) { //COPY:ALL
        final int count = 1; //COPY:ALL
        final Date startDate = new Date(); //COPY:ALL
        final Date endDate = paidDuration.dateAfter(startDate); //COPY:ALL
        //COPY:ALL
        // Use a transaction to rent the item for the period //COPY:ALL
        TransActionFixture transAction = new TransActionFixture(rentEz, //COPY:ALL
                new ClientTransaction(rentEz,startDate,staff,client)); //COPY:ALL
        Money cost = transAction.rentFor(count,rentalItemName,paidDuration); //COPY:ALL
        transAction.payWithCashDollar(cost); //COPY:ALL
        transAction.completeTransaction(); //COPY:ALL
        //COPY:ALL
        // Use a transaction to return the item after the required delay //COPY:ALL
        TransActionFixture finalTransAction = new TransActionFixture(rentEz, //COPY:ALL
                new ClientTransaction(rentEz,endDate,staff,client)); //COPY:ALL
        Money refund = finalTransAction.returnItems(count,rentalItemName); //COPY:ALL
        finalTransAction.refundCashDollar(refund); //COPY:ALL
        finalTransAction.completeTransaction(); //COPY:ALL
        return refund; //COPY:ALL
    } //COPY:ALL
} //COPY:ALL
