package edu.pdx.cs410J.dconradt;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Formatter;
import java.util.Scanner;

/**
 *@author Dan Conraddt 7/8/2015.
 */
public class TextDumper implements PhoneBillDumper {

    @Override
    public void dump(AbstractPhoneBill abstractPhoneBill) throws IOException {

    }

    public void dumpfile(String fileName, PhoneBill phoneBill){
        File phoneRecord = new File(fileName);
        String customerName = phoneBill.getCustomer();
        Collection phoneCalls = phoneBill.getPhoneCalls();

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

    private void buildRecord(PhoneCall billRecord, PrintWriter newRecord, String customerName) {
        String phoneBillRecord = customerName + "\t" + billRecord.getCaller() + "\t" + billRecord.getCallee() + "\t"
                + billRecord.getStartTimeString() + "\t" + billRecord.getEndTimeString();
        newRecord.println(phoneBillRecord);


    }
}
