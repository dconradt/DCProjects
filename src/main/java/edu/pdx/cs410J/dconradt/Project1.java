package edu.pdx.cs410J.dconradt;

import edu.pdx.cs410J.AbstractPhoneBill;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The main class for the CS410J Phone Bill Project
 * Dan Conradt
 * 6/29/2015
 * This class will parse command line args ([options] <args>) and validate correct format for each option and argument. Options will include -README and -print.
 * Argument list is in the order of customer. callerNumber, calleeNumber, startTime, endTime. Multi phrase customer names will be enclosed
 * in quotes, phone numbers must be in the format nnn-nnn-nnnn with n being an integer 0-9, dates must be in the form mm/dd/yyyy hh:mm
 * and will not be quoted.
 */
public class Project1 {
    static PhoneBill newBill = new PhoneBill(); // Instance of a phone bill to pass the new call to.
    static PhoneCall newCall = new PhoneCall();// Instance of a newCall to record

  public static void main(String[] args) {
      Class c = AbstractPhoneBill.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath
     // System.err.println("Missing command line arguments");
     // for (String arg : args) {
        //  System.out.println(arg);
     // }

      processCommandLine(args);// Process the command line arguments

      System.exit(1);

  }

    //Parse the commmand line arguements
    public static void processCommandLine(String [] args){

        String [] options = new String[2];
        String [] arguments = new String [6];
        String timeStamp = null;
        String [] phoneCall = new String[arguments.length]; // Set the array size for the phone call record to be passed to the phone bill.
        int argIndex = 0;
        int optionIndex = 0;
        boolean success = false; // Used to verify the success of reading command line arguments.
        boolean print = false; // Print flag set when option to print is requested.
        boolean readMe = false; // Sets the README flag to print a README for this project then exits.

        //Iterate through the arguments, verify phone number and date/time formats where appropriate and populate the phone call array.
        for(int i = 0; i < 2 ; i++) {
            if (args[i].equalsIgnoreCase("-print")) {
                options[i] = args[i];
                print = true;
            } else if (args[i].equalsIgnoreCase("-README"))            {
                options[i] = args[i];
                readMe = true;
            }
            argIndex = i;
        }

        newBill.setCustomer(args[argIndex + 1]);

        success = verifyPhoneNumber(args[argIndex + 2]);
        if(success)
            newCall.setCallerNumber(args[argIndex + 2]);
        else
            exitProgram("The phone number is invalid or the form nnn-nnn-nnnn.");

        success = verifyPhoneNumber(args[argIndex + 3]);
        if(success)
            newCall.setCalleeNumber(args[argIndex + 3]);
        else
            exitProgram("The phone number is invalid or the form nnn-nnn-nnnn.");

        timeStamp = args[argIndex + 4] + " " + args[argIndex + 5];
        success = verifyDateFormat(timeStamp);
        if (success)
            newCall.setStartTime(timeStamp);
        else
            exitProgram("The date and time must be of the format dd/mm/yyy/ hh:mm");

        timeStamp = args[argIndex + 6] + " " + args[argIndex + 7];
        success = verifyDateFormat(timeStamp);
        if (success)
            newCall.setEndTime(timeStamp);
        else
            exitProgram("The date and time must be of the format dd/mm/yyy/ hh:mm");

        newBill.addPhoneCall(newCall);
        if (print)
            System.out.println(newCall.toString());
        if(readMe) {
            System.out.println("README");
            System.exit(1);
        }
    }

    // Verify the date formatting is correct. Java regex pattern and matcher are used to describe the required input. Returns the value
    // of the successful or failur of a match.
    public static boolean verifyDateFormat(String date) {
        String dateToCheck = date;
        Pattern datePattern = Pattern.compile("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/(20\\d\\d) ([01]?[0-9]|2[0-3]):[0-5][0-9]");
        Matcher dateCorrect = datePattern.matcher(dateToCheck);
        return dateCorrect.matches();
    }


    // Verify the phone number formatting is correct. Java regex pattern and matcher are used to describe the required input. Returns the value
    // of the successful or failur of a match.
    public static boolean verifyPhoneNumber(String phoneNumber) {
        String checkPhoneNumber = phoneNumber;
        Pattern numberPattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
        Matcher numberPatternMatch = numberPattern.matcher(checkPhoneNumber);
        return numberPatternMatch.matches();
    }


    // Notifies the user of the problem and exits the program.
    public static void exitProgram(String description){
        System.out.println(description);
        System.exit(1);
    }
}