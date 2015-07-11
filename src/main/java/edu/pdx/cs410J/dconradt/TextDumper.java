package edu.pdx.cs410J.dconradt;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

/**
 *@author Dan Conraddt 7/8/2015.
 */
public class TextDumper implements PhoneBillDumper {
    private Formatter recordFormat;
    @Override
    public void dump(AbstractPhoneBill abstractPhoneBill) throws IOException {
            recordFormat.format("%s\\t%s\\t%s\t%s\t%s\t%s", abstractPhoneBill.getCustomer(), abstractPhoneBill.getPhoneCalls().toString());
    }

    public void dumpfile(String fileName, PhoneBill newBill){
        File billData = new File(fileName);
        if(billData.exists()) {
            try {
                recordFormat = new Formatter(fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                dump(newBill);
            } catch (IOException e) {
                System.out.println("The file cannot be written too.");
            }
        }
    }
}
