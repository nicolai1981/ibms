package com.nicoinc.system.ibms.command;

import java.util.Calendar;

import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.model.CourseSubscribe;

public class CourseCreateSubscribe extends Command {
    public CourseCreateSubscribe(CourseSubscribe subscribe, CommandListener listener) {
        super(COMMAND.COURSE_CREATE_SUBSCRIBE, "course_createSubscribe.php");
        addListener(listener);

        mHttpRequest.addParam("COURSE_ID", String.valueOf(subscribe.mCourseId));
        mHttpRequest.addParam("MEMBER_ID", String.valueOf(subscribe.mMemberId));
        mHttpRequest.addParam("START_DATE", sDateFormatter.format(Calendar.getInstance().getTime()));
    }
}
