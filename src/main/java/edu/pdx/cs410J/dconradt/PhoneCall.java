package edu.pdx.cs410J.dconradt;

import edu.pdx.cs410J.AbstractPhoneCall;
import java.util.Date;

/**
 * Created by Dan Conradt on 6/28/2015.
 */
public class PhoneCall extends AbstractPhoneCall {

    @Override
    public String getCallee() {
        return null;
    }

    @Override
    public String getCaller() {
        return null;
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
        return null;
    }

    @Override
    public String getStartTimeString() {
        return null;
    }
}
