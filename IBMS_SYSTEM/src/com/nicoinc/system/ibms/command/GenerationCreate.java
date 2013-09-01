package com.nicoinc.system.ibms.command;

import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.http.HttpRequest;
import com.nicoinc.system.ibms.model.Generation;

public class GenerationCreate extends Command {
    private HttpRequest mHttpRequest;

    public GenerationCreate(Generation generation, CommandListener listener) {
        super(COMMAND.GENERATION_CREATE);
        addListener(listener);
        mHttpRequest = new HttpRequest(WEB_URL + "generation_create.php", mResult);
        mHttpRequest.addParam("NAME", generation.mName);
        mHttpRequest.addParam("LEADER_ID", String.valueOf(generation.mLeaderId));
        mHttpRequest.addParam("START_DATE", sDateFormatter.format(generation.mStartDate));
    }

    @Override
    public void doRun() {
        mHttpRequest.start();
    }
}
