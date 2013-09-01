package com.nicoinc.system.ibms.command;

import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.http.HttpRequest;
import com.nicoinc.system.ibms.model.Generation;

public class GenerationUpdate extends Command {
    private HttpRequest mHttpRequest;

    public GenerationUpdate(Generation generation, CommandListener listener) {
        super(COMMAND.GENERATION_UPDATE);
        addListener(listener);
        mHttpRequest = new HttpRequest(WEB_URL + "generation_update.php", mResult);
        mHttpRequest.addParam("ID", String.valueOf(generation.mId));
        mHttpRequest.addParam("NAME", generation.mName);
        mHttpRequest.addParam("LEADER_ID", String.valueOf(generation.mLeaderId));
        mHttpRequest.addParam("START_DATE", sDateFormatter.format(generation.mStartDate));
        if (generation.mEndDate.getTime() == 0) {
            mHttpRequest.addParam("END_DATE", "0000-00-00");
        } else {
            mHttpRequest.addParam("END_DATE", sDateFormatter.format(generation.mEndDate));
        }
    }

    @Override
    public void doRun() {
        mHttpRequest.start();
    }
}
