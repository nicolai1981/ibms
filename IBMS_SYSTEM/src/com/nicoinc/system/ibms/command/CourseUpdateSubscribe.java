package com.nicoinc.system.ibms.command;

import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.model.CourseSubscribe;

public class CourseUpdateSubscribe extends Command {
    public CourseUpdateSubscribe(CourseSubscribe subscribe, CommandListener listener) {
        super(COMMAND.COURSE_UPDATE_SUBSCRIBE, "course_updateSubscribe.php");
        addListener(listener);

        mHttpRequest.addParam("ID", String.valueOf(subscribe.mId));
        mHttpRequest.addParam("COURSE_ID", String.valueOf(subscribe.mCourseId));
        mHttpRequest.addParam("MEMBER_ID", String.valueOf(subscribe.mMemberId));
        mHttpRequest.addParam("START_DATE", sDateFormatter.format(subscribe.mStartDate));
        mHttpRequest.addParam("END_DATE", (subscribe.mEndDate.getTime() == 0) ? "0000-00-00" : sDateFormatter.format(subscribe.mEndDate));
        mHttpRequest.addParam("IS_TEACHER", subscribe.mIsTeacher ? "1" : "0");
        mHttpRequest.addParam("COMPLETED", subscribe.mCompleted ? "1" : "0");
        mHttpRequest.addParam("TOTAL_LESSONS", String.valueOf(subscribe.mTotalLessons));
    }
}
