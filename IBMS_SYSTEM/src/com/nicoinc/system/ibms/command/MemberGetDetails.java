package com.nicoinc.system.ibms.command;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nicoinc.system.ibms.command.RequestResult.CODE;
import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.Course;
import com.nicoinc.system.ibms.model.CourseSubscribe;
import com.nicoinc.system.ibms.model.Generation;
import com.nicoinc.system.ibms.model.Member;
import com.nicoinc.system.ibms.model.MemberLeader;

public class MemberGetDetails extends Command {
    public MemberGetDetails(long id, CommandListener listener) {
        super(COMMAND.MEMBER_GET_DETAILS, "member_getDetails.php");
        addListener(listener);

        mHttpRequest.addParam("ID", String.valueOf(id));
    }

    @Override
    public void doRun() {
        super.doRun();

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
            for (Generation generation : Application.getInstance().getGenerationAllList()) {
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
            for (Member leader : Application.getInstance().getMemberAllList()) {
                if (member.mLeaderId == leader.mId) {
                    member.mLeaderName = leader.mName;
                    break;
                }
            }

            if (!root.has("FK_LEVEL")) {
                return;
            }
            member.mLevel = root.get("FK_LEVEL").getAsInt();

            mResult.setData("MEMBER", member);

            List<MemberLeader> leaderList = new ArrayList<MemberLeader>();
            if (!root.has("LEADER_LIST")) {
                return;
            }
            JsonArray jsonList = root.get("LEADER_LIST").getAsJsonArray();
            for (int i = 0; i < jsonList.size(); i++) {
                JsonObject item = jsonList.get(i).getAsJsonObject();
                MemberLeader memberLeader = new MemberLeader();

                memberLeader.mLeaderId = item.get("L_ID").getAsLong();
                Member leader = Application.getInstance().getLeader(memberLeader.mLeaderId);
                if (leader == null) {
                    memberLeader.mLeaderName = "NENHUM";
                } else {
                    memberLeader.mLeaderName = leader.mName;
                }
                try {
                    String stringDate = item.get("START_DATE").getAsString();
                    if (!stringDate.equals("0000-00-00")) {
                        memberLeader.mStartDate = sDateFormatter.parse(stringDate);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    return;
                }
                try {
                    String stringDate = item.get("END_DATE").getAsString();
                    if (!stringDate.equals("0000-00-00")) {
                        memberLeader.mEndDate = sDateFormatter.parse(stringDate);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    return;
                }

                leaderList.add(memberLeader);
            }

            mResult.setData("LEADER_LIST", leaderList);

            List<Course> courseList = new ArrayList<Course>();
            mResult.setData("COURSE_LIST", courseList);

            List<CourseSubscribe> subscriptionList = new ArrayList<CourseSubscribe>();
            mResult.setData("SUBSCRIPTION_LIST", subscriptionList);

            mResult.setCode(CODE.OK);
        }
    }
}
