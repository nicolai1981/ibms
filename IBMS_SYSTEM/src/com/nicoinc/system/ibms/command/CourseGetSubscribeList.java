package com.nicoinc.system.ibms.command;

import java.text.ParseException;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nicoinc.system.ibms.command.RequestResult.CODE;
import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.http.HttpRequest;
import com.nicoinc.system.ibms.model.Course;
import com.nicoinc.system.ibms.model.CourseSubscribe;

public class CourseGetSubscribeList extends Command {
    public static final String SUBSCRIBE_LIST = "SUBSCRIBE_LIST";
    private HttpRequest mHttpRequest;

    public CourseGetSubscribeList(Course course, CommandListener listener) {
        super(COMMAND.COURSE_GET_SUBSCRIBE_LIST);
        addListener(listener);
        mHttpRequest = new HttpRequest(WEB_URL + "course_getSubscribeList.php", mResult);
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

            if (!root.has("SUBSCRIBE_LIST")) {
                return;
            }

            ArrayList<CourseSubscribe> subscribeList = new ArrayList<CourseSubscribe>();
            JsonArray jsonList = root.get("SUBSCRIBE_LIST").getAsJsonArray();
            for (int i=0; i < jsonList.size(); i++) {
                JsonObject item = jsonList.get(i).getAsJsonObject();
                CourseSubscribe subscribe = new CourseSubscribe();

                if (!item.has("ID")) {
                    continue;
                }
                subscribe.mId = item.get("ID").getAsLong();

                if (!item.has("COURSE_ID")) {
                    continue;
                }
                subscribe.mCourseId = item.get("COURSE_ID").getAsLong();

                if (!item.has("MEMBER_ID")) {
                    continue;
                }
                subscribe.mMemberId = item.get("MEMBER_ID").getAsLong();

                if (!item.has("NAME")) {
                    continue;
                }
                subscribe.mMemberName = item.get("NAME").getAsString();

                if (!item.has("IS_TEACHER")) {
                    continue;
                }
                subscribe.mIsTeacher = item.get("IS_TEACHER").getAsInt() == 1;

                if (!item.has("COMPLETED")) {
                    continue;
                }
                subscribe.mCompleted = item.get("COMPLETED").getAsInt() == 1;

                if (!item.has("TOTAL_LESSONS")) {
                    continue;
                }
                subscribe.mTotalLessons = item.get("TOTAL_LESSONS").getAsInt();

                if (!item.has("START_DATE")) {
                    continue;
                }
                try {
                    subscribe.mStartDate = sDateFormatter.parse(item.get("START_DATE").getAsString());
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
                        subscribe.mEndDate = sDateFormatter.parse(endDate);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    continue;
                }

                subscribeList.add(subscribe);
            }

            mResult.setData(SUBSCRIBE_LIST, subscribeList);
            mResult.setCode(CODE.OK);
        }
    }
}
