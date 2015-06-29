package edu.pdx.cs410J.dconradt;

import edu.pdx.cs410J.AbstractPhoneBill;

/**
 * The main class for the CS410J Phone Bill Project
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
      for(int i =0; i<7; ++i){
          System.out.println(args[i] + "\n");
      }
  }


}