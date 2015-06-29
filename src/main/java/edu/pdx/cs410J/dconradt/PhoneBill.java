package edu.pdx.cs410J.dconradt;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Dan Conradt on 6/28/2015.
 */
public class PhoneBill extends AbstractPhoneBill {
    private static String customerName; // Customer Name of phone bill
    ArrayList customerCalls = new ArrayList();// List of calls to appear on the customer phone bill

    @Override
    public void addPhoneCall(AbstractPhoneCall abstractPhoneCall) {
        customerCalls.add(abstractPhoneCall);
    }

    @Override
    public String getCustomer(){
        return null;
    }

    @Override
    public Collection getPhoneCalls() {
        // Return the arraylist of customer calls
        return customerCalls;
    }
}
