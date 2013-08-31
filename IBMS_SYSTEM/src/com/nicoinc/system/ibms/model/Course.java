package com.nicoinc.system.ibms.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Course {
    public static final int COMPLETE_INFORMATION = 0;
    public static final int DATE_INFORMATION = 1;

    private static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    public long mId = 0;
    public long mCourseTypeId = 0;
    public String mCourseTypeName = null;
    public Date mStartDate = new Date(0);
    public Date mEndDate = new Date(0);
    public int mTotalLessons = 0;
    public int mStringFormat = DATE_INFORMATION;

    @Override
    public String toString() {
        if (mStringFormat == COMPLETE_INFORMATION) {
            return mCourseTypeName + " - " + sDateFormatter.format(mStartDate) + " - " + sDateFormatter.format(mEndDate);
        }
        return sDateFormatter.format(mStartDate) + " - " + sDateFormatter.format(mEndDate);
    }
}
