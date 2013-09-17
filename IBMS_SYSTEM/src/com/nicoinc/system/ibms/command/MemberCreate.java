package com.nicoinc.system.ibms.command;

import java.util.Calendar;

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
        mHttpRequest.addParam("BLOOD_TYPE", String.valueOf(member.mBloodType));

        mHttpRequest.addParam("RG", member.mRG);
        mHttpRequest.addParam("CPF", member.mCPF);
        mHttpRequest.addParam("MOBILE", member.mMobile);
        mHttpRequest.addParam("PHONE_HOME", member.mPhoneHome);
        mHttpRequest.addParam("PHONE_WORK", member.mPhoneWork);
        mHttpRequest.addParam("EMAIL", member.mEmail);
        mHttpRequest.addParam("ADDRESS", member.mAddress);
        mHttpRequest.addParam("ADDRESS_MORE", member.mAddressMore);
        mHttpRequest.addParam("DISTRICT", member.mDistrict);
        mHttpRequest.addParam("CITY", member.mCity);
        mHttpRequest.addParam("ZIP", member.mZIP);

        mHttpRequest.addParam("CREATE_DATE", sDateFormatter.format(Calendar.getInstance().getTime()));
        if (member.mStartDate.getTime() == 0) {
            mHttpRequest.addParam("START_DATE", "0000-00-00");
        } else {
            mHttpRequest.addParam("START_DATE", sDateFormatter.format(member.mStartDate));
        }
        mHttpRequest.addParam("START_TYPE", String.valueOf(member.mStartType));
        mHttpRequest.addParam("LEADER_ID", String.valueOf(member.mLeaderId));
        mHttpRequest.addParam("GENERATION_ID", String.valueOf(member.mGenerationId));
        mHttpRequest.addParam("IS_LEADER", String.valueOf(member.mIsLeader ? "1" : "0"));
    }

    @Override
    public void doRun() {
        mHttpRequest.start();
    }
}
