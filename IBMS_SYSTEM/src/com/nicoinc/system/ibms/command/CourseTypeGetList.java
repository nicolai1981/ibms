package com.nicoinc.system.ibms.command;

import java.text.ParseException;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nicoinc.system.ibms.command.RequestResult.CODE;
import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.CourseType;

public class CourseTypeGetList extends Command {

    public CourseTypeGetList(CommandListener listener) {
        super(COMMAND.COURSE_TYPE_GET_LIST, "courseType_getList.php");
        addListener(listener);
    }

    @Override
    public void doRun() {
        super.doRun();

        if (mResult.getCode() == CODE.OK) {
            mResult.setCode(CODE.SERVER_ERROR);
            JsonObject root = mResult.getJSON();
            if (!root.has("COURSE_TYPE_LIST")) {
                return;
            }

            ArrayList<CourseType> courseTypeList = new ArrayList<CourseType>();
            JsonArray jsonList = root.get("COURSE_TYPE_LIST").getAsJsonArray();
            for (int i=0; i < jsonList.size(); i++) {
                JsonObject item = jsonList.get(i).getAsJsonObject();
                CourseType courseType = new CourseType();

                if (!item.has("ID")) {
                    continue;
                }
                courseType.mId = item.get("ID").getAsLong();

                if (!item.has("NAME")) {
                    continue;
                }
                courseType.mName = item.get("NAME").getAsString();

                if (!item.has("START_DATE")) {
                    continue;
                }
                try {
                    courseType.mStartDate = sDateFormatter.parse(item.get("START_DATE").getAsString());
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
                        courseType.mEndDate = sDateFormatter.parse(endDate);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    continue;
                }

                courseTypeList.add(courseType);
            }

            Application.getInstance().setCourseTypeList(courseTypeList);
            mResult.setCode(CODE.OK);
        }
    }
}
