package com.nicoinc.system.ibms.command;

import java.text.ParseException;

import com.google.gson.JsonObject;
import com.nicoinc.system.ibms.command.RequestResult.CODE;
import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.http.HttpRequest;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.Generation;
import com.nicoinc.system.ibms.model.Member;

public class MemberGetMember extends Command {
    private HttpRequest mHttpRequest;

    public MemberGetMember(long id, CommandListener listener) {
        super(COMMAND.MEMBER_GET_MEMBER);
        addListener(listener);

        mHttpRequest = new HttpRequest(WEB_URL + "member_getMember.php", mResult);
        mHttpRequest.addParam("ID", String.valueOf(id));
    }

    @Override
    public void doRun() {
        mHttpRequest.start();

        if (mResult.getCode() == CODE.OK) {
            mResult.setCode(CODE.SERVER_ERROR);
            JsonObject root = mResult.getJSON();
            Member member = new Member();

            if (!root.has("ID")) {
                return;
            }
            member.mId = root.get("ID").getAsLong();

            if (!root.has("NAME")) {
                return;
            }
            member.mName = root.get("NAME").getAsString();

            if (!root.has("BIRTHDAY")) {
                return;
            }
            try {
                String stringDate = root.get("BIRTHDAY").getAsString();
                if (!stringDate.equals("0000-00-00")) {
                    member.mBirthday = sDateFormatter.parse(stringDate);
                }
            } catch (ParseException e) {
                e.printStackTrace();
                return;
            }

            if (!root.has("GENDER")) {
                return;
            }
            member.mGender = root.get("GENDER").getAsInt();

            if (!root.has("MARITIAL_STATUS")) {
                return;
            }
            member.mMaritialStatus = root.get("MARITIAL_STATUS").getAsInt();

            if (!root.has("BLOOD_TYPE")) {
                return;
            }
            member.mBloodType = root.get("BLOOD_TYPE").getAsInt();

            if (!root.has("RG")) {
                return;
            }
            member.mRG = root.get("RG").getAsString();

            if (!root.has("CPF")) {
                return;
            }
            member.mCPF = root.get("CPF").getAsString();

            if (!root.has("MOBILE")) {
                return;
            }
            member.mMobile = root.get("MOBILE").getAsString();

            if (!root.has("PHONE_HOME")) {
                return;
            }
            member.mPhoneHome = root.get("PHONE_HOME").getAsString();

            if (!root.has("PHONE_WORK")) {
                return;
            }
            member.mPhoneWork = root.get("PHONE_WORK").getAsString();

            if (!root.has("EMAIL")) {
                return;
            }
            member.mEmail = root.get("EMAIL").getAsString();

            if (!root.has("ADDRESS")) {
                return;
            }
            member.mAddress = root.get("ADDRESS").getAsString();

            if (!root.has("ADDRESS_MORE")) {
                return;
            }
            member.mAddressMore = root.get("ADDRESS_MORE").getAsString();

            if (!root.has("DISTRICT")) {
                return;
            }
            member.mDistrict = root.get("DISTRICT").getAsString();

            if (!root.has("CITY")) {
                return;
            }
            member.mCity = root.get("CITY").getAsString();

            if (!root.has("ZIP")) {
                return;
            }
            member.mZIP = root.get("ZIP").getAsString();

            if (!root.has("CREATE_DATE")) {
                return;
            }
            try {
                String stringDate = root.get("CREATE_DATE").getAsString();
                if (!stringDate.equals("0000-00-00")) {
                    member.mCreateDate = sDateFormatter.parse(stringDate);
                }
            } catch (ParseException e) {
                e.printStackTrace();
                return;
            }

            if (!root.has("START_DATE")) {
                return;
            }
            try {
                String stringDate = root.get("START_DATE").getAsString();
                if (!stringDate.equals("0000-00-00")) {
                    member.mStartDate = sDateFormatter.parse(stringDate);
                }
            } catch (ParseException e) {
                e.printStackTrace();
                return;
            }

            if (!root.has("END_DATE")) {
                return;
            }
            try {
                String endDate = root.get("END_DATE").getAsString();
                if (!endDate.equals("0000-00-00")) {
                    member.mEndDate = sDateFormatter.parse(endDate);
                }
            } catch (ParseException e) {
                e.printStackTrace();
                return;
            }

            if (!root.has("START_TYPE")) {
                return;
            }
            member.mStartType = root.get("START_TYPE").getAsInt();

            if (!root.has("IS_LEADER")) {
                return;
            }
            member.mIsLeader = (root.get("IS_LEADER").getAsInt() == 1);

            if (!root.has("FK_GENERATION")) {
                return;
            }
            member.mGenerationId = root.get("FK_GENERATION").getAsLong();
            member.mGenerationName = "-";
            for (Generation generation : Application.getInstance().getGenerationList()) {
                if (member.mGenerationId == generation.mId) {
                    member.mGenerationName = generation.mName;
                    break;
                }
            }

            if (!root.has("LEADER_ID")) {
                return;
            }
            member.mLeaderId = root.get("LEADER_ID").getAsLong();
            member.mLeaderName = "-";
            for (Member leader : Application.getInstance().getMemberList()) {
                if (member.mLeaderId == leader.mId) {
                    member.mLeaderName = leader.mName;
                    break;
                }
            }

            mResult.setData("MEMBER", member);
            mResult.setCode(CODE.OK);
        }
    }
}
