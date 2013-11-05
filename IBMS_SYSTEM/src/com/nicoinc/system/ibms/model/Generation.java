package com.nicoinc.system.ibms.model;

import java.util.Date;

public class Generation {
    public long mId = 0;
    public String mName = null;
    public long mLeaderId = 0;
    public String mLeaderName = null;
    public Date mStartDate = new Date(0);
    public Date mEndDate = new Date(0);

    @Override
    public String toString() {
        return mName;
    }
}
