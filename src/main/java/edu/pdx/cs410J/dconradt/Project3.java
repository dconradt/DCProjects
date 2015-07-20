package edu.pdx.cs410J.dconradt;

import edu.pdx.cs410J.ParserException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The main class for the CS410J Phone Bill Project
 * @author Dan Conradt
 * 7/8/2015
 * This class will parse command line args ([options] <args>) and validate correct format for each option and argument. Options may include -README and -print.
 * Argument list is in the order of customer. callerNumber, calleeNumber, startTime, endTime. Multi phrase customer names will be enclosed
 * in quotes, phone numbers must be in the format nnn-nnn-nnnn with n being an integer 0-9, dates must be in the form mm/dd/yyyy hh:mm
 * and will not be quoted.
 *
 * Updated 7/12/2015:
 * Added functionality to read and write the phone bill data to a specified file in the command line argurments.
 *
 * Updated 7/20/2015
 * Added functionality, use the java.util.date to compute the length of a call and format the call start and end times in SHORT format. Added a PrettyPrinter class
 * to output a user friendly formatted phone bill to either a specified file or to the console.
 *
 */
public class Project3 {
    static PhoneBill newBill = new PhoneBill(); // Instance of a phone bill to pass the new call to.
    static PhoneCall newCall = new PhoneCall();// Instance of a newCall to record
    static TextParser readFile = new TextParser();// Instance of a TextParser for reading phone bill files.
    static TextDumper writeFile = new TextDumper();// Instance of a TextDumper to write to the phone bill file
    static PrettyPrinter writePretty = new PrettyPrinter(); // Instance of a Pretty Printer object.

    /**
     * Main method to manage the data interaction between the classes.
     * @param args  Arguements passed to the program
     */
  public static void main(String[] args) {
      processCommandLine(args);/** Process the command line arguments*/
      System.exit(1);
  }

    /**
     * Parses the commmand line arguments
     * @param args  Command line arguments
     */
    public static void processCommandLine(String [] args) {

        String[] options = new String[2];/** Array to hold options */
        String[] arguments = new String[6];/** Array to hold arguments */
        String timeStamp = null; /** String to hold the concatenation of the date and time arguments */
        String fileName = null; /** Variable to hold the text file of the phone bill data.*/
        String prettyFile = null; /** Variable to hold the text file of the pretty file format
        //String [] phoneCall = new String[arguments.length]; /** Set the array size for the phone call record to be passed to the phone bill. */
        int argIndex = 0;/** index for the argument list */
        int argLength = 0;/** holds the length of the argument list */
        int optionCount = 0;/** Counter for the number of options input in the command line */
        boolean success = false; /** Used to verify the success of reading command line arguments. */
        boolean print = false; /** Print flag set when option to print is requested. */
        boolean file = false; /** File name is present so writ to file.*/
        boolean pretty = false;/** Determines if pretty file was requested.*/
        boolean prettyConsole = false; /** Determines if pretty prints to console.*/

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy hh:mm a");

        /** If not Argurments exit program */
        if(args.length == 0) {
            System.out.println("\nThere are too few command line argurments.");
            System.exit(1);
        }

        /** Iterate through the arguments, verify phone number and date/time formats where appropriate and populate the phone call array. */

        for (int i = 0; i < args.length; ++i) {
            if (args[i].equalsIgnoreCase("-print")) {
                print = true;
                ++optionCount;
            }else if((args[i].equalsIgnoreCase("-pretty"))){
                pretty = true;
                prettyFile = args[i + 1];
                optionCount = optionCount + 2;
                ++i;
            }else if (args[i].equalsIgnoreCase("-README")) {
                System.out.println("\n***README***\n\nDan Conradt - Project3\n\nI have implemented formatting the dates/time using the java.util.date and added the class PrettyPrinter to the project2 and updated it\nto Project3, PrettyPrinter implements PhoneBillDumper and " +
                        "This program takes an input of upto 4 options and requires 5 arguments describing\na phone call.  The addtional class will parse and write to a user friendly formatted phone bill file or to the console if '-' is provided\n" +
                        "after -pretty option.  The TextParser will parse the file into a phone bill collection at the start of the programe and validate the text file\ndata to ensure the file elements are of the correct format.  It validates the options and arguments " +
                        "for validity and formatting.  The date and\ntime must be actual dates in the form specified as mm/dd/yyyy hh:mm am|pm using a 12 hour clock.  The Phone numbers must be of the form nnn-nnn-nnnn.\nErrors in formating or validity of the phone " +
                        "numbers or date and time will output a message and the program will exit.  If all arguments are valid\nthe program will create a new phone call record and add it to the array list of phone calls in the " +
                        "phone bill class.  If the option -README is\nprovided then the program will output the README text description of the program and then exit. If no file exists for either -textFile or -pretty\nthen a new one will be created and written too, by the " +
                        "TextDumper class.");
                System.exit(1);
            } else if (args[i].equalsIgnoreCase("-textFile")) {
                file = true;
                fileName = args[i + 1];
                optionCount = optionCount + 2;
                ++i;
            } else if (args[i].startsWith("-")) {
                System.out.println("Argument: " + args[i] + " is not a valid option argument.");
                System.exit(1);
            }
        }

        /** Check to make sure no more or less arguments than required are in the command line */
        if (args.length < 10 || args.length > 15) {
            System.out.println("\nThere are too few or too many arguments");
            System.exit(1);
        }

        /** If a file is given read and parse the file*/
        if(file) {
            try {
                readFile.parse(fileName, newBill);
            } catch (ParserException e) {
                System.out.println("Error Parsing The File.");
            }

            argIndex = optionCount;
        }
        else
            argIndex = optionCount;

        try {
            if (newBill.getCustomer() == null || args[argIndex].equals(newBill.getCustomer()))
                newBill.setCustomer(args[argIndex]);
            else {
                System.out.println("The provided customer name does not match phone bill record file.");
                System.exit(1);
            }
            success = verifyPhoneNumber(args[argIndex + 1]);// verify phone format
            if (success)
                newCall.setCallerNumber(args[argIndex + 1]);
            else
                exitProgram("\nInvalid or missing phone number arguments.\nA phone number must be of the form nnn-nnn-nnnn.");
            success = verifyPhoneNumber(args[argIndex + 2]); // verify phone format
            if (success)
                newCall.setCalleeNumber(args[argIndex + 2]);
            else
                exitProgram("\nInvalid or missing phone number argument.\nA phone number must be of the form nnn-nnn-nnnn.");

            timeStamp = args[argIndex + 3] + " " + args[argIndex + 4] + " " + args[argIndex + 5];
            success = verifyDateFormat(timeStamp); // verify data and time format
            if (success) {
                Date callDateTime = null;
                try {
                    callDateTime = dateFormatter.parse(timeStamp);
                } catch (ParseException e) {
                    System.out.println("Error in Call Date/Time");
                    System.exit(1);
                }
                newCall.setStartTime(callDateTime);
            }
            else
                exitProgram("\nInvalid or missing Start Time argument.\nA date and time must be of the format dd/mm/yyy/ hh:mm am");
            timeStamp = args[argIndex + 6] + " " + args[argIndex + 7] + " " + args[argIndex + 8];
            success = verifyDateFormat(timeStamp);// verify data and time format
            if (success) {
                Date callDateTime = null;
                try {
                    callDateTime = dateFormatter.parse(timeStamp);
                } catch (ParseException e) {
                    System.out.println("Error in Call Date/Time");
                    System.exit(1);
                }
                newCall.setEndTime(callDateTime);
            }
            else
                exitProgram("\nInvalid or missing End Time Argument.\nA date and time must be of the format dd/mm/yyy/ hh:mm am");
            if (args.length > argIndex + 9) {
                System.out.println("There are too many arguments listed in the phone call information.");
                System.exit(1);

            }
            newBill.addPhoneCall(newCall);
            if (print)
                System.out.println("\n" + newCall.toString());
            if (file)
                try {
                    writeFile.dump(fileName, newBill);
                } catch (IOException e) {
                    System.out.println("File Error has occured, cannot write output file.");
                    System.exit(1);
                }
            if (pretty)
                try {
                    writePretty.prettyDump(prettyFile, newBill);
                } catch (IOException e) {
                    System.out.println("File Error has occured, cannot write output pretty file.");
                    System.exit(1);
                }
        } catch (RuntimeException ex) {
                System.out.print("\nInvalid Command Line Arguments.\n");
                System.exit(1);
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
        Pattern datePattern = Pattern.compile("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/(\\d\\d\\d\\d) (1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)");
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