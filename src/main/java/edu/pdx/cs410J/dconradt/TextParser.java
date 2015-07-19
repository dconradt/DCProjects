package edu.pdx.cs410J.dconradt;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.PhoneBillParser;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author  Dan Conradt 7/8/2015.
 * TextParser parses data from a text file. It does validation on the phone numbers and start and stop times
 */
public class TextParser implements PhoneBillParser {
    @Override
    public AbstractPhoneBill parse() throws ParserException {
        System.out.println("Error parsing the file.");
        System.exit(1);
        return null;
    }

    /** Parse the given file into an array list of phone calls. */
    public void parse(String fileName, PhoneBill newBill)throws ParserException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat( "MM/dd/yyyy hh:mm a");
        String timeStamp = null;
        File billData = new File(fileName);
        if(billData.exists()) {
            try {
                BufferedReader getBillData = new BufferedReader(new FileReader(billData));
                String phoneBillRecord = getBillData.readLine();
                while(phoneBillRecord != null && phoneBillRecord.length()>0){
                    PhoneCall oldCall = new PhoneCall();
                    String [] record = phoneBillRecord.split("\t");
                    int i = 0;
                    newBill.setCustomer(record[i]);
                    if(verifyPhoneNumber(record[i+1]))
                        oldCall.setCallerNumber(record[i + 1]);
                    else
                        exitProgram("Invalid caller phone number found while parsing file.");
                    if(verifyPhoneNumber(record[i + 2]))
                        oldCall.setCalleeNumber(record[i + 2]);
                    else
                        exitProgram("Invalid callee phone number found while parsing file.");


                    Date callDateTime = null;
                    try {
                        callDateTime = dateFormatter.parse(record[i + 3]);
                    } catch (ParseException e) {
                        System.out.println("Error in Call Date/Time");
                    }
                    timeStamp = dateFormatter.format(callDateTime);
                    if(verifyDateFormat(timeStamp)){
                        oldCall.setStartTime(callDateTime);
                    }
                    else
                        exitProgram("Invalid start date/time found while parsing file.");
                    try {
                        callDateTime = dateFormatter.parse(record[i + 4]);
                    } catch (ParseException e) {
                        System.out.println("Error in Call Date/Time");
                    }
                    timeStamp = dateFormatter.format(callDateTime);
                    if(verifyDateFormat(timeStamp)) {
                        oldCall.setEndTime(callDateTime);
                    }
                    else
                        exitProgram("Invalid stop date/time found while parsing file.");
                    newBill.addPhoneCall(oldCall);
                    phoneBillRecord = getBillData.readLine();
                }
                getBillData.close();
            }catch(IOException e){
                System.out.println("Error parsing the file.");
                System.exit(1);
            }
        }
    }

    /**
     * Verify the date formatting is correct. Java regex pattern and matcher are used to describe the required input. Returns the value
     * of the successful or failure of a match.
     * @param date  Date to be verified
     * @return Return true if date is valid otherwise false
     */
    public static boolean verifyDateFormat(String date) {
        String dateToCheck = date;
        Pattern datePattern = Pattern.compile("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/(\\d\\d\\d\\d) (1[012]|0?[1-9]):[0-5][0-9](\\s)?(?i)(AM|am|PM|pm)");
        Matcher dateCorrect = datePattern.matcher(dateToCheck);
        return dateCorrect.matches();
    }


    /**
     * Verify the phone number formatting is correct. Java regex pattern and matcher are used to describe the required input. Returns the value
     * of the successful or failur of a match.
     * @param phoneNumber   Phone number to be verified
     * @return  Return true if a phone number is valid otherwise false
     */
    public static boolean verifyPhoneNumber(String phoneNumber) {
        String checkPhoneNumber = phoneNumber;
        Pattern numberPattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
        Matcher numberPatternMatch = numberPattern.matcher(checkPhoneNumber);
        return numberPatternMatch.matches();
    }


    /**
     *  Notifies the user of the problem and exits the program.
     * @param description   String message to print out before exiting the program
     */
    public static void exitProgram(String description){
        System.out.println(description);
        System.exit(1);
    }
}
