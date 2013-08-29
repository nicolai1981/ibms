package com.nicoinc.system.ibms.command;

import java.text.ParseException;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nicoinc.system.ibms.command.RequestResult.CODE;
import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.http.HttpRequest;
import com.nicoinc.system.ibms.model.Generation;
import com.nicoinc.system.ibms.model.Member;

public class GenerationGetMemberList extends Command {
    public static final String GENERATION_LIST = "GENERATION_LIST";
    private HttpRequest mHttpRequest;

    public GenerationGetMemberList(Generation generation, CommandListener listener) {
        super(COMMAND.GET_GENERATION_MEMBER_LIST);
        addListener(listener);
        mHttpRequest = new HttpRequest(WEB_URL + "getGenerationMemberList.php", mResult);
        mHttpRequest.addParam("ID", String.valueOf(generation.mId));
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

            if (!root.has("MEMBER_LIST")) {
                return;
            }

            ArrayList<Member> memberList = new ArrayList<Member>();
            JsonArray jsonList = root.get("MEMBER_LIST").getAsJsonArray();
            for (int i = 0; i < jsonList.size(); i++) {
                JsonObject item = jsonList.get(i).getAsJsonObject();
                Member member = new Member();

                if (!item.has("ID")) {
                    continue;
                }
                member.mId = item.get("ID").getAsLong();

                if (!item.has("NAME")) {
                    continue;
                }
                member.mName = item.get("NAME").getAsString();

                if (!item.has("IS_LEADER")) {
                    continue;
                }
                member.mIsLeader = item.get("IS_LEADER").getAsInt() == 1;

                if (!item.has("END_DATE")) {
                    continue;
                }
                String endDate = item.get("END_DATE").getAsString();
                if (!endDate.equals("0000-00-00")) {
                    try {
                        member.mEndDate = sDateFormatter.parse(endDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        continue;
                    }
                }

                if (!item.has("ENTER_DATE")) {
                    continue;
                }
                String enterDate = item.get("ENTER_DATE").getAsString();
                if (!enterDate.equals("0000-00-00")) {
                    try {
                        member.mEnterDate = sDateFormatter.parse(enterDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        continue;
                    }
                }

                memberList.add(member);
            }

            mResult.setData(GENERATION_LIST, memberList);
            mResult.setCode(CODE.OK);
        }
    }
}
