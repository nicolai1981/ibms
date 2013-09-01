package com.nicoinc.system.ibms.command;

import java.text.ParseException;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nicoinc.system.ibms.command.RequestResult.CODE;
import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.http.HttpRequest;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.Course;
import com.nicoinc.system.ibms.model.CourseType;

public class CourseGetList extends Command {

    public CourseGetList(CommandListener listener) {
        super(COMMAND.COURSE_GET_LIST);
        addListener(listener);
    }

    @Override
    public void doRun() {
        new HttpRequest(WEB_URL + "course_getList.php", mResult).start();
        if (mResult.getCode() == CODE.OK) {
            mResult.setCode(CODE.SERVER_ERROR);
            JsonObject root = mResult.getJSON();
            if (!root.has("CODE")) {
                return;
            }
            int code = root.get("CODE").getAsInt();
            if (code != 0) {
                mResult.setCode(CODE.UNKNOWN);
                return;
            }

            if (!root.has("COURSE_LIST")) {
                return;
            }

            ArrayList<Course> courseList = new ArrayList<Course>();
            JsonArray jsonList = root.get("COURSE_LIST").getAsJsonArray();
            for (int i=0; i < jsonList.size(); i++) {
                JsonObject item = jsonList.get(i).getAsJsonObject();
                Course course = new Course();

                if (!item.has("ID")) {
                    continue;
                }
                course.mId = item.get("ID").getAsLong();

                if (!item.has("COURSE_TYPE_ID")) {
                    continue;
                }
                course.mCourseTypeId = item.get("COURSE_TYPE_ID").getAsLong();

                if (!item.has("START_DATE")) {
                    continue;
                }
                try {
                    course.mStartDate = sDateFormatter.parse(item.get("START_DATE").getAsString());
                } catch (ParseException e) {
                    e.printStackTrace();
                    continue;
                }

                if (!item.has("END_DATE")) {
                    continue;
                }
                try {
                    String endDate = item.get("END_DATE").getAsString();
                    if (!endDate.equals("0000-00-00")) {
                        course.mEndDate = sDateFormatter.parse(endDate);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    continue;
                }

                for (CourseType courseType : Application.getInstance().getCourseTypeList()) {
                    if (courseType.mId == course.mCourseTypeId) {
                        course.mCourseTypeName = courseType.mName;
                        break;
                    }
                }
                courseList.add(course);

                if (!item.has("TOTAL_LESSONS")) {
                    continue;
                }
                course.mTotalLessons = item.get("TOTAL_LESSONS").getAsInt();
            }

            Application.getInstance().setCourseList(courseList);
            mResult.setCode(CODE.OK);
        }
    }
}
