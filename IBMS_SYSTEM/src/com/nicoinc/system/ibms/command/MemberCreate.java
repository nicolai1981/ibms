package com.nicoinc.system.ibms.command;

import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.http.HttpRequest;
import com.nicoinc.system.ibms.model.Member;

public class MemberCreate extends Command {
    private HttpRequest mHttpRequest;

    public MemberCreate(Member member, CommandListener listener) {
        super(COMMAND.MEMBER_CREATE);
        addListener(listener);
        mHttpRequest = new HttpRequest(WEB_URL + "member_create.php", mResult);

        mHttpRequest.addParam("NAME", member.mName);
        mHttpRequest.addParam("BIRTHDAY", sDateFormatter.format(member.mBirthday));
        mHttpRequest.addParam("GENDER", String.valueOf(member.mGender));
        mHttpRequest.addParam("MARITIAL_STATUS", String.valueOf(member.mMaritialStatus));
        mHttpRequest.addParam("START_DATE", sDateFormatter.format(member.mStartDate));
        mHttpRequest.addParam("START_TYPE", String.valueOf(member.mStartType));
        mHttpRequest.addParam("LEADER_ID", String.valueOf(member.mLeaderId));
        mHttpRequest.addParam("GENERATION_ID", String.valueOf(member.mGenerationId));
    }

    @Override
    public void doRun() {
        mHttpRequest.start();
    }
}
