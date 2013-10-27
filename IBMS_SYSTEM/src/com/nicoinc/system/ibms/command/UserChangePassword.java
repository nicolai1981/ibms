package com.nicoinc.system.ibms.command;

import com.nicoinc.system.ibms.command.RequestResult.COMMAND;

public class UserChangePassword extends Command {
    public UserChangePassword(long userId, String oldPwd, String newPwd, CommandListener listener) {
        super(COMMAND.USER_CHANGE_PASSWORD, "user_change_password.php");
        addListener(listener);

        mHttpRequest.addParam("USER_ID", String.valueOf(userId));
        mHttpRequest.addParam("OLD_PWD", oldPwd);
        mHttpRequest.addParam("NEW_PWD", newPwd);
    }
}
