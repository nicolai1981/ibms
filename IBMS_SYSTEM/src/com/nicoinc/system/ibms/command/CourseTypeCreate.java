package com.nicoinc.system.ibms.command;

import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.model.CourseType;

public class CourseTypeCreate extends Command {
    public CourseTypeCreate(CourseType courseType, CommandListener listener) {
        super(COMMAND.COURSE_TYPE_CREATE, "courseType_create.php");
        addListener(listener);
        mHttpRequest.addParam("NAME", courseType.mName);
        mHttpRequest.addParam("START_DATE", sDateFormatter.format(courseType.mStartDate));
    }

    @Override
    public void doRun() {
        mHttpRequest.start();
    }
}
