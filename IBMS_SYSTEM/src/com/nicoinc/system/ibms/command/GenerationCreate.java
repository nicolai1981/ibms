package com.nicoinc.system.ibms.command;

import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.model.Generation;

public class GenerationCreate extends Command {
    public GenerationCreate(Generation generation, CommandListener listener) {
        super(COMMAND.GENERATION_CREATE, "generation_create.php");
        addListener(listener);

        mHttpRequest.addParam("NAME", generation.mName);
        mHttpRequest.addParam("LEADER_ID", String.valueOf(generation.mLeaderId));
        mHttpRequest.addParam("START_DATE", sDateFormatter.format(generation.mStartDate));
    }
}
