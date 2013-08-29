package com.nicoinc.system.ibms.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Course {
    private static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    public long mId = 0;
    public long mCourseTypeId = 0;
    public Date mStartDate = new Date(0);
    public Date mEndDate = new Date(0);

    @Override
    public String toString() {
        return sDateFormatter.format(mStartDate) + " - " + sDateFormatter.format(mEndDate);
    }
}
