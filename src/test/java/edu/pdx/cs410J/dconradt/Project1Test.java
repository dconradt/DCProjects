package edu.pdx.cs410J.dconradt;

import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import edu.pdx.cs410J.InvokeMainTestCase;
import static junit.framework.Assert.assertEquals;

/**
 * Tests the functionality in the {@link Project1} main class.
 */
public class Project1Test extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project1} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project1.class, args );
    }

    /**
    * Tests that invoking the main method with no arguments issues an error
    */
    @Test
    public void testNoCommandLineArguments() {
        MainMethodResult result = invokeMain();
        assertEquals(new Integer(1), result.getExitCode());
        assertTrue(result.getErr().contains( "Missing command line arguments" ));
    }

    /*
     * Tests the verify date format function.
     */
    @Test
    public void testValidDateTimeFormat(){
        Project1 newProject = new Project1();
        assertTrue(newProject.verifyDateFormat("11/21/2015 12:21"));
    }

    /*
    * Tests the phone number format
     */
    @Test
    public void testValidPhoneNumber(){
        Project1 newProject = new Project1();
        assertTrue(newProject.verifyPhoneNumber("123-456-7777"));
    }

}