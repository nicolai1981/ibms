package com.nicoinc.system.ibms.command;

import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.model.CourseType;

public class CourseTypeUpdate extends Command {
    public CourseTypeUpdate(CourseType courseType, CommandListener listener) {
        super(COMMAND.COURSE_TYPE_UPDATE, "courseType_update.php");
        addListener(listener);
        mHttpRequest.addParam("ID", String.valueOf(courseType.mId));
        mHttpRequest.addParam("NAME", courseType.mName);
        mHttpRequest.addParam("START_DATE", sDateFormatter.format(courseType.mStartDate));
        if (courseType.mEndDate.getTime() == 0) {
            mHttpRequest.addParam("END_DATE", "0000-00-00");
        } else {
            mHttpRequest.addParam("END_DATE", sDateFormatter.format(courseType.mEndDate));
        }
    }
}
