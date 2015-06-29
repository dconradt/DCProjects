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


  public static void main(String[] args) {
      PhoneBill newBill = new PhoneBill();
      int length = args.length;// Get the number of argments passed in
      String [] phoneCall = new String[length]; // Set the array size for the phone call record to be passed to the phone bill.
      boolean success = false; // Used to verify the success of reading command line arguments.
      Class c = AbstractPhoneBill.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath
      System.err.println("Missing command line arguments");
      for (String arg : args) {
          System.out.println(arg);
      }

      // Verify there are the appropriate number of arguments
      if(length != 9) {
          exitProgram("Invalid number of arguments!");
      }


      //Iterate through the arguments, verify format where appropriate and populate the phone call array.
      for(int i = 0; i < length ; i++){
          success = false;
          if(i == 2) {
              phoneCall[i] = args[i];
          }
          else if(i == 3 || i == 4){
              success = verifyPhoneNumber(args[i]);
              if(success){
                  phoneCall[i] = args[i];
              }
              else{
                  exitProgram("The phone number must be of the form nnn-nnn-nnnn");
              }
          }
          else if(i == 5 || i == 7){
              String timeStamp = args[i] + " " + args[i+1];
              success = verifyDateFormat(timeStamp);
              if (success) {
                  phoneCall[i] = timeStamp;
              }
              else{
                  exitProgram("The date and time must be of the format dd/mm/yyy/ hh:mm");
              }
          }
      }
      if(success){
          newBill.addPhoneCall(phoneCall);
      }

      System.exit(1);

  }


    // Verify the date formatting is correct. Java regex pattern and matcher are used to describe the required input. Returns the value
    // of the successful or failur of a match.
    private static boolean verifyDateFormat(String date) {
        String dateToCheck = date;
        Pattern datePattern = Pattern.compile("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/(20\\d\\d) ([01]?[0-9]|2[0-3]):[0-5][0-9]");
        Matcher dateCorrect = datePattern.matcher(dateToCheck);
        return dateCorrect.matches();
    }


    // Verify the phone number formatting is correct. Java regex pattern and matcher are used to describe the required input. Returns the value
    // of the successful or failur of a match.
    private static boolean verifyPhoneNumber(String phoneNumber) {
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