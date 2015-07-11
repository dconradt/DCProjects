package edu.pdx.cs410J.dconradt;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.PhoneBillParser;

import java.io.*;
import java.util.Collection;
import java.util.Scanner;

/**
 * @author  Dan Conradt 7/8/2015.
 */
public class TextParser implements PhoneBillParser {
    @Override
    public AbstractPhoneBill parse() throws ParserException {
        return null;
    }

    public void parseFile(String fileName){
        PhoneBill oldBill = new PhoneBill();
        PhoneCall oldCall = new PhoneCall();
        File billData = new File(fileName);
        if(billData.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(fileName);
            } catch (FileNotFoundException e) {
                System.out.println("The Phone Bill File does not exist.");
            }
            Scanner fileRead = new Scanner(fis);
            while (fileRead.hasNextLine()) {
                oldBill.setCustomer(fileRead.next());
                oldCall.setCallerNumber(fileRead.next());
                oldCall.setCalleeNumber(fileRead.next());
                oldCall.setStartTime(fileRead.next());
                oldCall.setEndTime(fileRead.next());
                try {
                    parse().addPhoneCall(oldCall);
                } catch (ParserException e) {
                    System.out.println("Error reading file data");
                }
            }
        }
    }
}
