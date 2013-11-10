package com.nicoinc.system.ibms.command;

import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.model.Course;

public class CourseCreate extends Command {
    public CourseCreate(Course course, CommandListener listener) {
        super(COMMAND.COURSE_CREATE, "course_create.php");
        addListener(listener);
        mHttpRequest.addParam("COURSE_TYPE_ID", String.valueOf(course.mCourseTypeId));
        mHttpRequest.addParam("START_DATE", sDateFormatter.format(course.mStartDate));
        mHttpRequest.addParam("END_DATE", sDateFormatter.format(course.mEndDate));
        mHttpRequest.addParam("TOTAL_LESSONS", String.valueOf(course.mTotalLessons));
    }
}
