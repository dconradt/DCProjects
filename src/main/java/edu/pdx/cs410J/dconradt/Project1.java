package edu.pdx.cs410J.dconradt;

import edu.pdx.cs410J.AbstractPhoneBill;

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

      Class c = AbstractPhoneBill.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath

      System.err.println("Missing command line arguments");
      for (String arg : args) {
          System.out.println(arg);
      }
      System.exit(1);

      //String startTime = null; // Used to hold the start time from the argument list.
     // String endTime = null; // Used to hold the end time from the argument list.
     // startTime = args[5] + " " + args[6]; // Contatenate the array argument into a valid date time format
     // endTime = args[7] + " " + args[8]; // Contatenate the array argument into a valid date time format
      //System.out.println(args[5] + " " + args[6] + " - " + args[7] + " " + args[8]);


      /*for(int i =0; i<7; ++i){
          System.out.println(args[i] + "\n");
          if(i == 5)
              startTime = args[5] + " " + args[6];
          else if (i == 7)
              endTime = args[7] + " " + args[8];
      }
      System.out.println(startTime + " - " + endTime + "\n");
      System.out.println("Finished");*/




  }


}