package com.nicoinc.system.ibms.command;

import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.http.HttpRequest;
import com.nicoinc.system.ibms.model.Course;

public class CourseUpdate extends Command {
    private HttpRequest mHttpRequest;

    public CourseUpdate(Course course, CommandListener listener) {
        super(COMMAND.UPDATE_COURSE);
        addListener(listener);
        mHttpRequest = new HttpRequest(WEB_URL + "updateCourse.php", mResult);
        mHttpRequest.addParam("ID", String.valueOf(course.mId));
        mHttpRequest.addParam("COURSE_TYPE_ID", String.valueOf(course.mCourseTypeId));
        mHttpRequest.addParam("START_DATE", sDateFormatter.format(course.mStartDate));
        mHttpRequest.addParam("END_DATE", sDateFormatter.format(course.mEndDate));
        mHttpRequest.addParam("TOTAL_LESSONS", String.valueOf(course.mTotalLessons));
    }

    @Override
    public void doRun() {
        mHttpRequest.start();
    }
}
