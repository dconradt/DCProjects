package edu.pdx.cs410J.dconradt;

import edu.pdx.cs410J.AbstractPhoneCall;
import java.util.Date;

/**
 * Created by Dan Conradt on 6/28/2015.
 * The PhoneCall class manages the attributes of a phone call.
 */
public class PhoneCall extends AbstractPhoneCall {


    private String endTime;// End time of a call
    private String callerNumber;// Number of the caller
    private String calleeNumber;// Number of the callee
    private String startTime;// Start time of the call

    /**Get the callee phone number
     *
     * @return Returns the callee phone number
     */
    @Override
    public String getCallee() {

        return calleeNumber;
    }

    /**Get the caller phone number
     *
     * @return Returns the caller phone number
     */
    @Override
    public String getCaller() {
        return callerNumber;
    }

    /**Get the end time of a call
     *
     * @return  Returns the end time of the phone call
     */
    @Override
    public Date getEndTime() {
        return super.getEndTime();
    }

    /**Get the start time of the call
     *
     * @return Returns the start time of the phone call
     */
    @Override
    public Date getStartTime() {
        return super.getStartTime();
    }

    /**Get the end time of the current call
     *
     * @return Returns the end time as a string
     */
    @Override
    public String getEndTimeString() {
        return endTime;
    }

    /**Get the start time of the current call
     *
     * @return  Returns the starTime as a string
     */
    @Override
    public String getStartTimeString() {
        return startTime;
    }


    /** Set the caller number
     *
     * @param callerNumber  Sets the caller number of the current instance
     */
    public void setCallerNumber(String callerNumber) {

        this.callerNumber = callerNumber;
    }

    /**Set the Callee number
     *
     * @param calleeNumber  Sets the callee number of the current instance
     */
    public void setCalleeNumber(String calleeNumber) {

        this.calleeNumber = calleeNumber;
    }

    /**Set the current call start time
     *
     * @param startTime     Sets the start time of the call
     */
    public void setStartTime(String startTime) {

        this.startTime = startTime;
    }

    /**Set the current call end time
     *
     * @param endTime Sets the end time of the call
     */
    public void setEndTime(String endTime) {

        this.endTime = endTime;
    }
}
