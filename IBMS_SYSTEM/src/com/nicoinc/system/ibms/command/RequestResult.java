package com.nicoinc.system.ibms.command;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;

public class RequestResult {
    public enum COMMAND {
        GET_LEADER_LIST,

        GET_MEMBER_LIST,

        CREATE_GENERATION,
        UPDATE_GENERATION,
        GET_GENERATION_LIST,
        GET_GENERATION_MEMBER_LIST,

        CREATE_COURSE_TYPE,
        UPDATE_COURSE_TYPE,
        GET_COURSE_TYPE_LIST,
        GET_COURSE_TYPE_HISTORY_LIST,

        CREATE_COURSE,
        UPDATE_COURSE,
        SUBSCRIBE_COURSE,
        GET_COURSE_LIST,
        SAVE_COURSE_TEACHER_LIST,
        GET_COURSE_TEACHER_LIST,
        GET_COURSE_SUBSCRIBE_LIST,
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
