package edu.pdx.cs410J.dconradt;

import edu.pdx.cs410J.AbstractPhoneCall;
import java.util.Date;

/**
 * Created by Dan Conradt on 6/28/2015.
 */
public class PhoneCall extends AbstractPhoneCall {


    private String endTime;
    private String callerNumber;
    private String calleeNumber;
    private String startTime;

    @Override
    public String getCallee() {
        return calleeNumber;
    }

    @Override
    public String getCaller() {
        return callerNumber;
    }

    @Override
    public Date getEndTime() {
        return super.getEndTime();
    }

    @Override
    public Date getStartTime() {
        return super.getStartTime();
    }

    @Override
    public String getEndTimeString() {
        return endTime;
    }

    @Override
    public String getStartTimeString() {
        return startTime;
    }


    public void setCallerNumber(String callerNumber) {
        this.callerNumber = callerNumber;
    }

    public void setCalleeNumber(String calleeNumber) {
        this.calleeNumber = calleeNumber;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
