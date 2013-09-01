package com.nicoinc.system.ibms.command;

import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.http.HttpRequest;
import com.nicoinc.system.ibms.model.CourseType;

public class CourseTypeUpdate extends Command {
    private HttpRequest mHttpRequest;

    public CourseTypeUpdate(CourseType courseType, CommandListener listener) {
        super(COMMAND.COURSE_TYPE_UPDATE);
        addListener(listener);
        mHttpRequest = new HttpRequest(WEB_URL + "courseType_update.php", mResult);
        mHttpRequest.addParam("ID", String.valueOf(courseType.mId));
        mHttpRequest.addParam("NAME", courseType.mName);
        mHttpRequest.addParam("START_DATE", sDateFormatter.format(courseType.mStartDate));
        if (courseType.mEndDate.getTime() == 0) {
            mHttpRequest.addParam("END_DATE", "0000-00-00");
        } else {
            mHttpRequest.addParam("END_DATE", sDateFormatter.format(courseType.mEndDate));
        }
    }

    @Override
    public void doRun() {
        mHttpRequest.start();
    }
}
