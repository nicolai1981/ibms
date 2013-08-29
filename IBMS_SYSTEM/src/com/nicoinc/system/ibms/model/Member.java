package com.nicoinc.system.ibms.model;

import java.util.Date;

public class Member {
    public long mId = 0;
    public int mType = 0;
    public String mName = null;
    public long mGenerationId = 0;
    public long mLeaderId = 0;
    public boolean mIsLeader = false;
    public Date mEndDate = new Date(0);
    public Date mStartDate = new Date(0);
    public Date mEnterDate = new Date(0);

    @Override
    public String toString() {
        return mName;
    }
}
