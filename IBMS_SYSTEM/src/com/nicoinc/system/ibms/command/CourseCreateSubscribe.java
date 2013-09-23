package com.nicoinc.system.ibms.command;

import java.util.Calendar;

import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.http.HttpRequest;
import com.nicoinc.system.ibms.model.CourseSubscribe;

public class CourseCreateSubscribe extends Command {
    private HttpRequest mHttpRequest;

    public CourseCreateSubscribe(CourseSubscribe subscribe, CommandListener listener) {
        super(COMMAND.COURSE_CREATE_SUBSCRIBE);
        addListener(listener);

        mHttpRequest = new HttpRequest(WEB_URL + "course_createSubscribe.php", mResult);
        mHttpRequest.addParam("COURSE_ID", String.valueOf(subscribe.mCourseId));
        mHttpRequest.addParam("MEMBER_ID", String.valueOf(subscribe.mMemberId));
        mHttpRequest.addParam("START_DATE", sDateFormatter.format(Calendar.getInstance().getTime()));
    }

    @Override
    public void doRun() {
        mHttpRequest.start();
    }
}