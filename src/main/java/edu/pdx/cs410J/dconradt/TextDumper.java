package edu.pdx.cs410J.dconradt;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.*;
import java.util.Collection;

/**
 *@author Dan Conraddt 7/8/2015.
 * TextDumper is used to populate a file with the data from an
 * AbstractPhoneBill.  Each phone call record is formatted to
 * be a tab delimited record.
 */
public class TextDumper implements PhoneBillDumper {

    @Override
    public void dump(AbstractPhoneBill abstractPhoneBill) throws IOException {

    }

    /** Dumps an AbstractPhoneBill to the file.
     *
     * @param fileName // name of the file to dump the data to
     * @param abstractPhoneBill// takes an instance of AbstractPhoneBill
     * @throws IOException// Exception handling for file IO
     */
    public void dump(String fileName, AbstractPhoneBill abstractPhoneBill)throws IOException{
        File phoneRecord = new File(fileName);
        String customerName = abstractPhoneBill.getCustomer();
        Collection phoneCalls = abstractPhoneBill.getPhoneCalls();

        try {
            //dump(newBill);
            PrintWriter newRecord = new PrintWriter(new BufferedWriter(new FileWriter(phoneRecord)));

            for(Object billRecord : phoneCalls) {
                buildRecord((PhoneCall) billRecord, newRecord, customerName);
            }
            newRecord.close();
        } catch (IOException e) {
            System.out.println("The file cannot be written too.");
            System.exit(1);
        }


    }

    /**
     * Formats a phone bill call record to tab delimited formatting and prints it to the file.
     * @param billRecord A phone call record
     * @param newRecord PrintWriter instance to print the phone call to the file.
     * @param customerName Phone bill customer to be written to the file.
     */
    private void buildRecord(PhoneCall billRecord, PrintWriter newRecord, String customerName) {
        String phoneBillRecord = customerName + "\t" + billRecord.getCaller() + "\t" + billRecord.getCallee() + "\t"
                + billRecord.getStartTimeString() + "\t" + billRecord.getEndTimeString();
        newRecord.println(phoneBillRecord);


    }
}
