package com.nicoinc.system.ibms.command;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nicoinc.system.ibms.command.RequestResult.CODE;
import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.http.HttpRequest;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.Member;

public class LeaderGetList extends Command {

    public LeaderGetList(CommandListener listener) {
        super(COMMAND.GET_LEADER_LIST);
        addListener(listener);
    }

    @Override
    public void doRun() {
        new HttpRequest(WEB_URL + "getLeaderList.php", mResult).start();
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

            if (!root.has("LEADER_LIST")) {
                return;
            }

            ArrayList<Member> leaderList = new ArrayList<Member>();
            JsonArray jsonList = root.get("LEADER_LIST").getAsJsonArray();
            for (int i = 0; i < jsonList.size(); i++) {
                JsonObject item = jsonList.get(i).getAsJsonObject();
                Member leader = new Member();

                if (!item.has("ID")) {
                    continue;
                }
                leader.mId = item.get("ID").getAsLong();

                if (!item.has("NAME")) {
                    continue;
                }
                leader.mName = item.get("NAME").getAsString();

                leaderList.add(leader);
            }

            Application.getInstance().setLeaderList(leaderList);
            mResult.setCode(CODE.OK);
        }
    }
}
