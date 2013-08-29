package com.nicoinc.system.ibms.command;

public interface CommandListener {
    void onCommandFinished(final RequestResult result);
}
