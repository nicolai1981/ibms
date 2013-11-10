package com.nicoinc.system.ibms.command;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nicoinc.system.ibms.command.RequestResult.CODE;
import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.Course;
import com.nicoinc.system.ibms.model.CourseType;

public class CourseGetList extends Command {

    public CourseGetList(CommandListener listener) {
        super(COMMAND.COURSE_GET_LIST, "course_getList.php");
        addListener(listener);
    }

    @Override
    public void doRun() {
        super.doRun();

        if (mResult.getCode() == CODE.OK) {
            mResult.setCode(CODE.SERVER_ERROR);
            JsonObject root = mResult.getJSON();
            if (!root.has("COURSE_LIST")) {
                return;
            }

            ArrayList<Course> courseAllList = new ArrayList<Course>();
            ArrayList<Course> courseActivatedList = new ArrayList<Course>();
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

                if (!item.has("DEACTIVATE_DATE")) {
                    continue;
                }
                try {
                    String deactivateDate = item.get("DEACTIVATE_DATE").getAsString();
                    if (!deactivateDate.equals("0000-00-00")) {
                        course.mDeactivateDate = sDateFormatter.parse(deactivateDate);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    continue;
                }

                for (CourseType courseType : Application.getInstance().getCourseTypeAllList()) {
                    if (courseType.mId == course.mCourseTypeId) {
                        course.mCourseTypeName = courseType.mName;
                        break;
                    }
                }
                courseAllList.add(course);
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, 1);
                if ((course.mDeactivateDate.getTime() == 0) && (calendar.getTimeInMillis() < course.mEndDate.getTime())) {
                    courseActivatedList.add(course);
                }

                if (!item.has("TOTAL_LESSONS")) {
                    continue;
                }
                course.mTotalLessons = item.get("TOTAL_LESSONS").getAsInt();

                if (!item.has("VERSION")) {
                    continue;
                }
                course.mVersion = item.get("VERSION").getAsInt();
            }

            Application.getInstance().setCourseAllList(courseAllList);
            Application.getInstance().setCourseActivatedList(courseActivatedList);
            mResult.setCode(CODE.OK);
        }
    }
}
