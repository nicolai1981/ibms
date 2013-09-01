package com.nicoinc.system.ibms.command;

import java.text.ParseException;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nicoinc.system.ibms.command.RequestResult.CODE;
import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.http.HttpRequest;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.Generation;

public class GenerationGetList extends Command {

    public GenerationGetList(CommandListener listener) {
        super(COMMAND.GENERATION_GET_LIST);
        addListener(listener);
    }

    @Override
    public void doRun() {
        new HttpRequest(WEB_URL + "generation_getList.php", mResult).start();
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

            if (!root.has("GENERATION_LIST")) {
                return;
            }

            ArrayList<Generation> generationList = new ArrayList<Generation>();
            JsonArray jsonList = root.get("GENERATION_LIST").getAsJsonArray();
            for (int i=0; i < jsonList.size(); i++) {
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

                generationList.add(generation);
            }

            Application.getInstance().setGenerationList(generationList);
            mResult.setCode(CODE.OK);
        }
    }
}
