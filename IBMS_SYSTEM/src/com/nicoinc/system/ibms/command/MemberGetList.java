package com.nicoinc.system.ibms.command;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nicoinc.system.ibms.command.RequestResult.CODE;
import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.Generation;
import com.nicoinc.system.ibms.model.Member;

public class MemberGetList extends Command {

    public MemberGetList(CommandListener listener) {
        super(COMMAND.MEMBER_GET_LIST, "member_getList.php");
        addListener(listener);
    }

    @Override
    public void doRun() {
        super.doRun();

        if (mResult.getCode() == CODE.OK) {
            mResult.setCode(CODE.SERVER_ERROR);
            JsonObject root = mResult.getJSON();
            if (!root.has("MEMBER_LIST")) {
                return;
            }

            ArrayList<Member> memberList = new ArrayList<Member>();
            ArrayList<Member> leaderList = new ArrayList<Member>();
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

                if (!item.has("END_DATE")) {
                    continue;
                }
                try {
                    String endDate = item.get("END_DATE").getAsString();
                    if (!endDate.equals("0000-00-00")) {
                        member.mEndDate = sDateFormatter.parse(endDate);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    continue;
                }

                if (!item.has("IS_LEADER")) {
                    continue;
                }
                member.mIsLeader = (item.get("IS_LEADER").getAsInt() == 1);

                if (!item.has("FK_GENERATION")) {
                    continue;
                }
                member.mGenerationId = item.get("FK_GENERATION").getAsLong();

                memberList.add(member);
                if (member.mIsLeader && (member.mEndDate.getTime() == 0)) {
                    leaderList.add(member);
                }
            }

            Application.getInstance().setMemberList(memberList);
            Application.getInstance().setLeaderList(leaderList);

            // Set leader and generation information
            List<Generation> generation = Application.getInstance().getGenerationList();
            List<Member> leader = Application.getInstance().getLeaderList();

            for (Member member : Application.getInstance().getMemberList()) {
                if (member.mLeaderId != 0) {
                    for (Member l : leader) {
                        if (member.mLeaderId == l.mId) {
                            member.mLeaderName = l.mName;
                            break;
                        }
                    }
                }
                if (member.mGenerationId != 0) {
                    for (Generation g : generation) {
                        if (member.mGenerationId == g.mId) {
                            member.mGenerationName = g.mName;
                            break;
                        }
                    }
                }
            }

            mResult.setCode(CODE.OK);
        }
    }
}
