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

    public void parseFile(String fileName, PhoneBill newBill) throws IOException {
        //PhoneBill oldBill = new PhoneBill();
        PhoneCall oldCall = new PhoneCall();
        File billData = new File(fileName);
        if(billData.exists()) {
            try {
                BufferedReader getBillData = new BufferedReader(new FileReader(billData));
                String phoneBillRecord = getBillData.readLine();
                while(phoneBillRecord != null){
                    String [] record = phoneBillRecord.split("\t");
                    int i = 0;
                    newBill.setCustomer(record[i]);
                    oldCall.setCallerNumber(record[i + 1]);
                    oldCall.setCalleeNumber(record[i + 2]);
                    oldCall.setStartTime(record[i + 3]);
                    oldCall.setEndTime(record[i + 4]);
                    newBill.addPhoneCall(oldCall);
                    phoneBillRecord = getBillData.readLine();
                }
                getBillData.close();
            }catch(IOException e){
                System.out.println("Error reading the file.");
                System.exit(1);
            }
        }
    }
}
