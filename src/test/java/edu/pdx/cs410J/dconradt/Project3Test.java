package edu.pdx.cs410J.dconradt;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import edu.pdx.cs410J.InvokeMainTestCase;
import static junit.framework.Assert.assertEquals;

/**
 * Tests the functionality in the {@link Project3} main class.
 */
public class Project3Test extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project3} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project3.class, args );
    }

    /**
    * Tests that invoking the main method with no arguments issues an error
    */
  //  @Test
   // public void testNoCommandLineArguments() {
   //     MainMethodResult result = invokeMain();
   //     assertEquals(new Integer(1), result.getExitCode());
   //     assertTrue(result.getErr().contains( "Missing command line arguments" ));
   // }

    /**
     * Tests the verify date format function.
     */
    @Test
    public void testValidDateTimeFormat(){
        Project3 newProject = new Project3();
        assertTrue(newProject.verifyDateFormat("11/21/2015 12:21"));
    }

    /**
     * Tests the phone number format
     */
    @Test
    public void testValidPhoneNumber(){
        Project3 newProject = new Project3();
        assertTrue(newProject.verifyPhoneNumber("123-456-7777"));
    }

}