package com.nicoinc.system.ibms.command;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;

public class RequestResult {
    public enum COMMAND {
        // Member
        MEMBER_CREATE,
        MEMBER_GET_LIST,

        // Generation
        GENERATION_CREATE,
        GENERATION_UPDATE,
        GENERATION_GET_LIST,
        GENERATION_GET_MEMBER_LIST,

        // Course type
        COURSE_TYPE_CREATE,
        COURSE_TYPE_UPDATE,
        COURSE_TYPE_GET_LIST,
        COURSE_TYPE_COURSE_LIST,

        // COURSE
        COURSE_CREATE,
        COURSE_UPDATE,
        COURSE_GET_LIST,
        COURSE_GET_SUBSCRIBE_LIST,
        COURSE_SAVE_TEACHER_LIST,
        COURSE_CREATE_SUBSCRIBE,
        COURSE_UPDATE_SUBSCRIBE,
    };

    public enum CODE {
        OK,
        WITHOUT_CONNECTION,
        SERVER_ERROR,
        UNKNOWN,
        
    };

    private static final String JSON_DATA = "JSON_DATA";
    private static final String COMMAND_ID = "COMMAND_ID";
    private static final String CODE_ID = "CODE_ID";

    private Map<String, Object> mData = new HashMap<String, Object>();

    public RequestResult(COMMAND command) {
        mData = new HashMap<String, Object>();
        mData.put(COMMAND_ID, command);
        mData.put(CODE_ID, CODE.UNKNOWN);
    }

    public COMMAND getCommand() {
        return (COMMAND) mData.get(COMMAND_ID);
    }

    public void setCode(CODE code) {
        mData.put(CODE_ID, code);
    }

    public CODE getCode() {
        return (CODE) mData.get(CODE_ID);
    }

    public void setJSON(JsonObject json) {
        mData.put(JSON_DATA, json);
    }

    public JsonObject getJSON() {
        return (JsonObject) mData.get(JSON_DATA);
    }

    public void setData(String key, Object data) {
        mData.put(key, data);
    }

    public Object getData(String key) {
        return mData.get(key);
    }
}
