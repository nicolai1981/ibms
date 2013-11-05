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

public class GenerationGetList extends Command {

    public GenerationGetList(CommandListener listener) {
        super(COMMAND.GENERATION_GET_LIST, "generation_getList.php");
        addListener(listener);
    }

    @Override
    public void doRun() {
        super.doRun();

        if (mResult.getCode() == CODE.OK) {
            mResult.setCode(CODE.SERVER_ERROR);
            JsonObject root = mResult.getJSON();
            if (!root.has("GENERATION_LIST")) {
                return;
            }

            ArrayList<Generation> generationAllList = new ArrayList<Generation>();
            ArrayList<Generation> generationActivatedList = new ArrayList<Generation>();

            JsonArray jsonList = root.get("GENERATION_LIST").getAsJsonArray();
            for (int i = 0; i < jsonList.size(); i++) {
                JsonObject item = jsonList.get(i).getAsJsonObject();
                Generation generation = new Generation();

                if (!item.has("ID")) {
                    continue;
                }
                generation.mId = item.get("ID").getAsLong();

                if (!item.has("NAME")) {
                    continue;
                }
                generation.mName = item.get("NAME").getAsString();

                if (!item.has("LEADER_ID")) {
                    continue;
                }
                generation.mLeaderId = item.get("LEADER_ID").getAsLong();

                if (!item.has("START_DATE")) {
                    continue;
                }
                try {
                    generation.mStartDate = sDateFormatter.parse(item.get("START_DATE").getAsString());
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
                        generation.mEndDate = sDateFormatter.parse(endDate);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    continue;
                }

                generationAllList.add(generation);
                if (generation.mEndDate.getTime() == 0) {
                    generationActivatedList.add(generation);
                }
            }

            Application.getInstance().setGenerationAllList(generationAllList);
            Application.getInstance().setGenerationActivatedList(generationActivatedList);

            // Set leader for generation
            List<Member> leaderList = Application.getInstance().getLeaderAllList();
            for (Generation generation : generationAllList) {
                for (Member leader : leaderList) {
                    if (generation.mLeaderId == leader.mId) {
                        generation.mLeaderName = leader.mName;
                        break;
                    }
                }
            }

            // Set generation for all members
            List<Member> memberList = Application.getInstance().getMemberAllList();
            for (Member member : memberList) {
                for (Generation generation : generationAllList) {
                    if (member.mGenerationId == generation.mId) {
                        member.mGenerationName = generation.mName;
                        break;
                    }
                }
            }

            mResult.setCode(CODE.OK);
        }
    }
}
