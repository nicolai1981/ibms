package com.nicoinc.system.ibms.model;

import java.util.Date;

public class Member {
    public long mId = 0;
    public int mType = 0;
    public String mName = null;
    public Date mBirthday = new Date(0);
    public int mGender = 0;
    public int mMaritialStatus = 0;
    public int mBloodType = 0;

    public String mRG = null;
    public String mCPF = null;
    public String mMobile = null;
    public String mPhoneHome = null;
    public String mPhoneWork = null;
    public String mEmail = null;
    public String mAddress = null;
    public String mAddressMore = null;
    public String mDistrict = null;
    public String mCity = null;
    public String mZIP = null;
    
    public Date mStartDate = new Date(0);
    public int mStartType = 0;

    public long mGenerationId = 0;
    public long mLeaderId = 0;
    public boolean mIsLeader = false;
    public Date mEndDate = new Date(0);

    @Override
    public String toString() {
        return mName;
    }
}
