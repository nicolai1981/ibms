package com.nicoinc.system.ibms.model;

import java.util.Date;

public class CourseSubscribe {
    public long mId = 0;
    public long mCourseId = 0;
    public long mMemberId = 0;
    public String mMemberName = null;
    public Date mStartDate = new Date(0);
    public Date mEndDate = new Date(0);
    public boolean mIsTeacher = false;
    public boolean mCompleted = false;
    public int mTotalLessons = 0;

    @Override
    public String toString() {
        return mMemberName;
    }
}
