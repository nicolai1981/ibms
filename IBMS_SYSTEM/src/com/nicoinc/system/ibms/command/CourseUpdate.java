package com.nicoinc.system.ibms.command;

import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.model.Course;

public class CourseUpdate extends Command {
    public CourseUpdate(Course course, CommandListener listener) {
        super(COMMAND.COURSE_UPDATE, "course_update.php");
        addListener(listener);
        mHttpRequest.addParam("ID", String.valueOf(course.mId));
        mHttpRequest.addParam("COURSE_TYPE_ID", String.valueOf(course.mCourseTypeId));
        mHttpRequest.addParam("START_DATE", sDateFormatter.format(course.mStartDate));
        mHttpRequest.addParam("END_DATE", sDateFormatter.format(course.mEndDate));
        mHttpRequest.addParam("TOTAL_LESSONS", String.valueOf(course.mTotalLessons));
        if (course.mDeactivateDate.getTime() == 0) {
            mHttpRequest.addParam("DEACTIVATE_DATE", "0000-00-00");
        } else {
            mHttpRequest.addParam("DEACTIVATE_DATE", sDateFormatter.format(course.mDeactivateDate));
        }
    }
}
