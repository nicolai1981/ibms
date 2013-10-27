package com.nicoinc.system.ibms.command;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nicoinc.system.ibms.command.RequestResult.CODE;
import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.model.Member;

public class UserGetList extends Command {

    public UserGetList(CommandListener listener) {
        super(COMMAND.USER_GET_LIST, "user_getList.php");
        addListener(listener);
    }

    @Override
    public void doRun() {
        super.doRun();

        if (mResult.getCode() == CODE.OK) {
            mResult.setCode(CODE.SERVER_ERROR);
            JsonObject root = mResult.getJSON();
            if (!root.has("USER_LIST")) {
                return;
            }

            ArrayList<Member> memberList = new ArrayList<Member>();
            JsonArray jsonList = root.get("USER_LIST").getAsJsonArray();
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

                memberList.add(member);
            }

            mResult.setData("USER_LIST", memberList);
            mResult.setCode(CODE.OK);
        }
    }
}
