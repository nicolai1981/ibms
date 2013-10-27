package com.nicoinc.system.ibms.command;

import com.google.gson.JsonObject;
import com.nicoinc.system.ibms.command.RequestResult.CODE;
import com.nicoinc.system.ibms.command.RequestResult.COMMAND;

public class Login extends Command {
    public Login(long userId, String pwd, CommandListener listener) {
        super(COMMAND.LOGIN, "login.php");
        addListener(listener);
        mHttpRequest.addParam("USER_ID", String.valueOf(userId));
        mHttpRequest.addParam("PWD", pwd);
    }

    @Override
    public void doRun() {
        super.doRun();

        if (mResult.getCode() == CODE.OK) {
            mResult.setCode(CODE.SERVER_ERROR);
            JsonObject root = mResult.getJSON();
            if (!root.has("TOKEN")) {
                return;
            }
            mResult.setData("TOKEN", root.get("TOKEN").getAsString());

            if (!root.has("TYPE")) {
                return;
            }
            mResult.setData("TYPE", root.get("TYPE").getAsInt());

            mResult.setCode(CODE.OK);
        }
    }
}
