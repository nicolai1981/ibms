package com.nicoinc.system.ibms.command;

import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.http.HttpRequest;
import com.nicoinc.system.ibms.model.CourseType;

public class CourseTypeCreate extends Command {
    private HttpRequest mHttpRequest;

    public CourseTypeCreate(CourseType courseType, CommandListener listener) {
        super(COMMAND.CREATE_COURSE_TYPE);
        addListener(listener);
        mHttpRequest = new HttpRequest(WEB_URL + "createCourseType.php", mResult);
        mHttpRequest.addParam("NAME", courseType.mName);
        mHttpRequest.addParam("START_DATE", sDateFormatter.format(courseType.mStartDate));
    }

    @Override
    public void doRun() {
        mHttpRequest.start();
    }
}