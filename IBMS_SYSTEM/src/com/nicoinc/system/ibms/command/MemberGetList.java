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

            ArrayList<Member> memberAllList = new ArrayList<Member>();
            ArrayList<Member> memberActivatedList = new ArrayList<Member>();
            ArrayList<Member> leaderAllList = new ArrayList<Member>();
            ArrayList<Member> leaderActivatedList = new ArrayList<Member>();
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

                if (!item.has("GENDER")) {
                    continue;
                }
                member.mGender = item.get("GENDER").getAsInt();

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

                if (!item.has("FK_LEADER")) {
                    continue;
                }
                member.mLeaderId = item.get("FK_LEADER").getAsLong();

                memberAllList.add(member);
                if (member.mEndDate.getTime() == 0) {
                    memberActivatedList.add(member);
                }

                if (member.mIsLeader) {
                    leaderAllList.add(member);
                    if (member.mEndDate.getTime() == 0) {
                        leaderActivatedList.add(member);
                    }
                }
            }

            Application.getInstance().setMemberAllList(memberAllList);
            Application.getInstance().setMemberActivatedList(memberActivatedList);

            Application.getInstance().setLeaderAllList(leaderAllList);
            Application.getInstance().setLeaderActivatedList(leaderActivatedList);

            // Set leader for all generation
            List<Generation> generationList = Application.getInstance().getGenerationAllList();
            for (Generation generation : generationList) {
                if (generation.mId == 0) {
                    continue;
                }

                for (Member leader : leaderAllList) {
                    if (generation.mLeaderId == leader.mId) {
                        generation.mLeaderName = leader.mName;
                        break;
                    }
                }
            }

            // Set leader and generation information
            for (Member member : memberAllList) {
                if (member.mLeaderId != 0) {
                    for (Member leader : leaderAllList) {
                        if (member.mLeaderId == leader.mId) {
                            member.mLeaderName = leader.mName;
                            break;
                        }
                    }
                }

                if (member.mGenerationId != 0) {
                    for (Generation generation : generationList) {
                        if (member.mGenerationId == generation.mId) {
                            member.mGenerationName = generation.mName;
                            break;
                        }
                    }
                }
            }

            mResult.setCode(CODE.OK);
        }
    }
}
