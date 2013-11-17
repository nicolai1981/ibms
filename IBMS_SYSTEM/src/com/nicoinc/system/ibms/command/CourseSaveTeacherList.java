package com.nicoinc.system.ibms.command;

import java.util.List;

import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.model.Course;
import com.nicoinc.system.ibms.model.CourseSubscribe;

public class CourseSaveTeacherList extends Command {
    public CourseSaveTeacherList(Course course, List<CourseSubscribe> teacherList, CommandListener listener) {
        super(COMMAND.COURSE_SAVE_TEACHER_LIST, "course_saveTeacherList.php");
        addListener(listener);

        mHttpRequest.addParam("COURSE_ID", String.valueOf(course.mId));

        StringBuilder param = new StringBuilder();
        for (CourseSubscribe teacher : teacherList) {
            param.append("," + teacher.mMemberId);
        }
        if (param.length() > 0) {
            param.deleteCharAt(0);
        }
        mHttpRequest.addParam("TEACHER_ID_LIST", param.toString());
    }
}
