package com.nicoinc.system.ibms.main;

import java.util.ArrayList;
import java.util.List;

import com.nicoinc.system.ibms.model.Course;
import com.nicoinc.system.ibms.model.CourseType;
import com.nicoinc.system.ibms.model.Generation;
import com.nicoinc.system.ibms.model.Member;

public class Application {
    private static Application sInstance = null;
    private List<Member> mLeaderList;
    private List<Generation> mGenerationList;
    private List<CourseType> mCourseTypeList;
    private List<Course> mCourseList;
    private String mToken="NONE";

    public static Application getInstance() {
        if (sInstance == null) {
            sInstance = new Application();
        }
        return sInstance;
    }

    private Application() {
        mLeaderList = new ArrayList<Member>();
        mGenerationList = new ArrayList<Generation>();
        mCourseTypeList = new ArrayList<CourseType>();
        mCourseList = new ArrayList<Course>();
    }

    public void setLeaderList(List<Member> list) {
        mLeaderList.clear();
        mLeaderList.addAll(list);
    }

    public List<Member> getLeaderList() {
        return mLeaderList;
    }

    public Member getLeader(long id) {
        for (Member item : mLeaderList) {
            if (item.mId == id) {
                return item;
            }
        }
        return null;
    }

    public void setGenerationList(List<Generation> list) {
        mGenerationList.clear();
        mGenerationList.addAll(list);
    }

    public List<Generation> getGenerationList() {
        return mGenerationList;
    }

    public void setCourseTypeList(List<CourseType> list) {
        mCourseTypeList.clear();
        mCourseTypeList.addAll(list);
    }

    public List<CourseType> getCourseTypeList() {
        return mCourseTypeList;
    }

    public void setCourseList(List<Course> list) {
        mCourseList.clear();
        mCourseList.addAll(list);
    }

    public List<Course> getCourseList() {
        return mCourseList;
    }

    public void setToken(String token) {
        mToken = token;
    }

    public String getToken() {
        return mToken;
    }
}
