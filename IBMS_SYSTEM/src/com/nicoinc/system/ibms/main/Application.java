package com.nicoinc.system.ibms.main;

import java.util.ArrayList;
import java.util.List;

import com.nicoinc.system.ibms.model.Course;
import com.nicoinc.system.ibms.model.CourseType;
import com.nicoinc.system.ibms.model.Generation;
import com.nicoinc.system.ibms.model.Member;

public class Application {
    private static Application sInstance = null;
    private List<Member> mMemberAllList;
    private List<Member> mMemberActivatedList;
    private List<Member> mLeaderAllList;
    private List<Member> mLeaderActivatedList;
    private List<Generation> mGenerationAllList;
    private List<Generation> mGenerationActivatedList;
    private List<CourseType> mCourseTypeAllList;
    private List<CourseType> mCourseTypeActivatedList;
    private List<Course> mCourseAllList;
    private List<Course> mCourseActivatedList;
    private String mToken="NONE";

    public static Application getInstance() {
        if (sInstance == null) {
            sInstance = new Application();
        }
        return sInstance;
    }

    private Application() {
        mLeaderAllList = new ArrayList<Member>();
        mLeaderActivatedList = new ArrayList<Member>();
        mMemberAllList = new ArrayList<Member>();
        mMemberActivatedList = new ArrayList<Member>();
        mGenerationAllList = new ArrayList<Generation>();
        mGenerationActivatedList = new ArrayList<Generation>();
        mCourseTypeAllList = new ArrayList<CourseType>();
        mCourseTypeActivatedList = new ArrayList<CourseType>();
        mCourseAllList = new ArrayList<Course>();
        mCourseActivatedList = new ArrayList<Course>();
    }

    public void setMemberAllList(List<Member> list) {
        mMemberAllList.clear();
        mMemberAllList.addAll(list);
    }

    public List<Member> getMemberAllList() {
        return mMemberAllList;
    }

    public void setMemberActivatedList(List<Member> list) {
        mMemberActivatedList.clear();
        mMemberActivatedList.addAll(list);
    }

    public List<Member> getMemberActivatedList() {
        return mMemberActivatedList;
    }

    public void setLeaderAllList(List<Member> list) {
        mLeaderAllList.clear();
        mLeaderAllList.addAll(list);
    }

    public List<Member> getLeaderAllList() {
        return mLeaderAllList;
    }

    public void setLeaderActivatedList(List<Member> list) {
        mLeaderActivatedList.clear();
        mLeaderActivatedList.addAll(list);
    }

    public List<Member> getLeaderActivatedList() {
        return mLeaderActivatedList;
    }

    public Member getLeader(long id) {
        for (Member item : mLeaderAllList) {
            if (item.mId == id) {
                return item;
            }
        }
        return null;
    }

    public List<Member> getGenerationMemberList(long id) {
        List<Member> list = new ArrayList<Member>();
        for (Member member : mMemberAllList) {
            if (member.mGenerationId == id) {
                list.add(member);
            }
        }
        return list;
    }

    public void setGenerationAllList(List<Generation> list) {
        mGenerationAllList.clear();
        mGenerationAllList.addAll(list);
    }

    public List<Generation> getGenerationAllList() {
        return mGenerationAllList;
    }

    public void setGenerationActivatedList(List<Generation> list) {
        mGenerationActivatedList.clear();
        mGenerationActivatedList.addAll(list);
    }

    public List<Generation> getGenerationActivatedList() {
        return mGenerationActivatedList;
    }

    public void setCourseTypeAllList(List<CourseType> list) {
        mCourseTypeAllList.clear();
        mCourseTypeAllList.addAll(list);
    }

    public List<CourseType> getCourseTypeAllList() {
        return mCourseTypeAllList;
    }

    public void setCourseTypeActivatedList(List<CourseType> list) {
        mCourseTypeActivatedList.clear();
        mCourseTypeActivatedList.addAll(list);
    }

    public List<CourseType> getCourseTypeActivatedList() {
        return mCourseTypeActivatedList;
    }

    public void setCourseAllList(List<Course> list) {
        mCourseAllList.clear();
        mCourseAllList.addAll(list);
    }

    public List<Course> getCourseAllList() {
        return mCourseAllList;
    }

    public void setCourseActivatedList(List<Course> list) {
        mCourseActivatedList.clear();
        mCourseActivatedList.addAll(list);
    }

    public List<Course> getCourseActivatedList() {
        return mCourseActivatedList;
    }

    public void setToken(String token) {
        mToken = token;
    }

    public String getToken() {
        return mToken;
    }
}
