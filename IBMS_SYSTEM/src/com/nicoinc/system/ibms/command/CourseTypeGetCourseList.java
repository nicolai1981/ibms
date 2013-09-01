package com.nicoinc.system.ibms.command;

import java.text.ParseException;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nicoinc.system.ibms.command.RequestResult.CODE;
import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.http.HttpRequest;
import com.nicoinc.system.ibms.model.Course;
import com.nicoinc.system.ibms.model.CourseType;

public class CourseTypeGetCourseList extends Command {
    public static final String HISTORY_LIST = "HISTORY_LIST";
    private HttpRequest mHttpRequest;

    public CourseTypeGetCourseList(CourseType courseType, CommandListener listener) {
        super(COMMAND.COURSE_TYPE_COURSE_LIST);
        addListener(listener);
        mHttpRequest = new HttpRequest(WEB_URL + "courseType_getCourseList.php", mResult);
        mHttpRequest.addParam("ID", String.valueOf(courseType.mId));
    }

    @Override
    public void doRun() {
        mHttpRequest.start();

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

            if (!root.has("HISTORY_LIST")) {
                return;
            }

            ArrayList<Course> courseList = new ArrayList<Course>();
            JsonArray jsonList = root.get("HISTORY_LIST").getAsJsonArray();
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

                courseList.add(course);
            }

            mResult.setData(HISTORY_LIST, courseList);
            mResult.setCode(CODE.OK);
        }
    }
}
