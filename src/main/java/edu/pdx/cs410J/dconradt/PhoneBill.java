package edu.pdx.cs410J.dconradt;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Dan Conradt on 6/28/2015.
 * The PhomeBill class will keep an array list of phone calls and manage the PhoneBill customer name.
 */
public class PhoneBill extends AbstractPhoneBill {

    private String customer;// Phone Bill Customer
    private ArrayList phoneCalls = new ArrayList();// Array list to hold phone calls

    // Add a phone call to the array list of phonce calls
    @Override
    public void addPhoneCall(AbstractPhoneCall abstractPhoneCall) {

        phoneCalls.add(abstractPhoneCall);
    }

    // Get the phone call list
    @Override
    public Collection getPhoneCalls() {

        return phoneCalls;
    }


    // Get the customer ame
    @Override
    public String getCustomer(){

        return customer;
    }

    // Set the customer name
    public void setCustomer(String customer) {

        this.customer = customer;
    }
}
