package com.nicoinc.system.ibms.command;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nicoinc.system.ibms.command.RequestResult.CODE;
import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.http.HttpRequest;
import com.nicoinc.system.ibms.model.Course;
import com.nicoinc.system.ibms.model.CourseSubscribe;

public class CourseGetTeacherList extends Command {
    public static final String TEACHER_LIST = "TEACHER_LIST";
    private HttpRequest mHttpRequest;

    public CourseGetTeacherList(Course course, CommandListener listener) {
        super(COMMAND.GET_COURSE_TEACHER_LIST);
        addListener(listener);
        mHttpRequest = new HttpRequest(WEB_URL + "getCourseTeacher.php", mResult);
        mHttpRequest.addParam("ID", String.valueOf(course.mId));
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

            if (!root.has("TEACHER_LIST")) {
                return;
            }

            ArrayList<CourseSubscribe> subscribeList = new ArrayList<CourseSubscribe>();
            JsonArray jsonList = root.get("TEACHER_LIST").getAsJsonArray();
            for (int i=0; i < jsonList.size(); i++) {
                JsonObject item = jsonList.get(i).getAsJsonObject();
                CourseSubscribe subscribe = new CourseSubscribe();

                if (!item.has("MEMBER_ID")) {
                    continue;
                }
                subscribe.mMemberId = item.get("MEMBER_ID").getAsLong();

                if (!item.has("NAME")) {
                    continue;
                }
                subscribe.mMemberName = item.get("NAME").getAsString();

                subscribeList.add(subscribe);
            }

            mResult.setData(TEACHER_LIST, subscribeList);
            mResult.setCode(CODE.OK);
        }
    }
}
