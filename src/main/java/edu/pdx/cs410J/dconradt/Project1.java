package edu.pdx.cs410J.dconradt;

import edu.pdx.cs410J.AbstractPhoneBill;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The main class for the CS410J Phone Bill Project
 * @author Dan Conradt
 * 6/29/2015
 * This class will parse command line args ([options] <args>) and validate correct format for each option and argument. Options may include -README and -print.
 * Argument list is in the order of customer. callerNumber, calleeNumber, startTime, endTime. Multi phrase customer names will be enclosed
 * in quotes, phone numbers must be in the format nnn-nnn-nnnn with n being an integer 0-9, dates must be in the form mm/dd/yyyy hh:mm
 * and will not be quoted.
 *
 *
 */
public class Project1 {
    static PhoneBill newBill = new PhoneBill(); // Instance of a phone bill to pass the new call to.
    static PhoneCall newCall = new PhoneCall();// Instance of a newCall to record

    /**
     * Main method to manage the data interaction between the classes.
     * @param args  Arguements passed to the program
     */
  public static void main(String[] args) {
      processCommandLine(args);/** Process the command line arguments*/
      System.exit(1);
  }

    /**
     * Parses the commmand line arguements
     * @param args  Command line arguements
     */
    public static void processCommandLine(String [] args){

        String [] options = new String[2];/** Array to hold options */
        String [] arguments = new String [6];/** Array to hold arguments */
        String timeStamp = null; /** String to hold the concatenation of the date and time arguments */
        String [] phoneCall = new String[arguments.length]; /** Set the array size for the phone call record to be passed to the phone bill. */
        int argIndex = 0;/** index for the argument list */
        int optionIndex = 0;/** index for the option array */
        boolean success = false; /** Used to verify the success of reading command line arguments. */
        boolean print = false; /** Print flag set when option to print is requested. */
        boolean readMe = false; /** Sets the README flag to print a README for this project then exits.*/

        /** Iterate through the arguments, verify phone number and date/time formats where appropriate and populate the phone call array. */
        for(int i = 0; i < 2 ; ++i) {
            if (args[i].equalsIgnoreCase("-print")) {
                options[i] = args[i];
                print = true;
            } else if (args[i].equalsIgnoreCase("-README") &&!print) {
                System.out.println("\n***README***\n\nDan Conradt - Project1\n\nI have implemented three classes - Project1, PhoneBill and PhoneCall. PhonebBill and PhoneCall\nextend the AbstractPhoneBill and AbstractPhoneCall classes respectively" +
                        "This program takes\nan input of upto 2 options and requires 5 arguments describing a phone call.  It validates the\noptions and arguments for validity and formatting.  The date and time must be " +
                        "actual dates in the\nform specified as mm/dd/yyyy hh:mm.  The Phone numbers must be of the form nnn-nnn-nnnn.\nErrors in formating or validity of the phone numbers or date and time will output a message\nand the program will exit.  " +
                        "If all arguments are valid the program will create an new phone call\nrecord using an array list of phone calls in the phone bill class.  If the option -print is\nprovided then the program " +
                        "will output the phone call from the given parameters.");
                System.exit(1);
            }
            else if(args[i].equalsIgnoreCase("-README") ){
                options[i] = args[i];
                readMe = true;
            }
        }
        if(print)
            ++argIndex;
        if(readMe)
            ++argIndex;
        newBill.setCustomer(args[argIndex]);

        success = verifyPhoneNumber(args[argIndex + 1]);
        if(success)
            newCall.setCallerNumber(args[argIndex + 1]);
        else
            exitProgram("The phone number is invalid or the form nnn-nnn-nnnn.");

        success = verifyPhoneNumber(args[argIndex + 2]);
        if(success)
            newCall.setCalleeNumber(args[argIndex + 2]);
        else
            exitProgram("The phone number is invalid or the form nnn-nnn-nnnn.");

        timeStamp = args[argIndex + 3] + " " + args[argIndex + 4];
        success = verifyDateFormat(timeStamp);
        if (success)
            newCall.setStartTime(timeStamp);
        else
            exitProgram("The date and time must be of the format dd/mm/yyy/ hh:mm");

        timeStamp = args[argIndex + 5] + " " + args[argIndex + 6];
        success = verifyDateFormat(timeStamp);
        if (success)
            newCall.setEndTime(timeStamp);
        else
            exitProgram("The date and time must be of the format dd/mm/yyy/ hh:mm");

        newBill.addPhoneCall(newCall);
        if (print)
            System.out.println("\n" + newCall.toString());
        if(readMe) {
            System.out.println("\n***README***\n\nDan Conradt - Project1\n\nI have implemented three classes - Project1, PhoneBill and PhoneCall. PhonebBill and PhoneCall\nextend the AbstractPhoneBill and AbstractPhoneCall classes respectively" +
                    "This program takes\nan input of upto 2 options and requires 5 arguments describing a phone call.  It validates the\noptions and arguments for validity and formatting.  The date and time must be " +
                    "actual dates in the\nform specified as mm/dd/yyyy hh:mm.  The Phone numbers must be of the form nnn-nnn-nnnn.\nErrors in formating or validity of the phone numbers or date and time will output a message\nand the program will exit.  " +
                    "If all arguments are valid the program will create an new phone call\nrecord using an array list of phone calls in the phone bill class.  If the option -print is\nprovided then the program " +
                    "will output the phone call from the given parameters." );
            System.exit(1);
        }
    }

    /**
     * Verify the date formatting is correct. Java regex pattern and matcher are used to describe the required input. Returns the value
     * of the successful or failure of a match.
     *
     * @param date  Date to be verified
     * @return Return true if date is valid otherwise false
     */
    public static boolean verifyDateFormat(String date) {
        String dateToCheck = date;
        Pattern datePattern = Pattern.compile("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/(\\d\\d\\d\\d) ([01]?[0-9]|2[0-3]):[0-5][0-9]");
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