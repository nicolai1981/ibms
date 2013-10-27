package com.nicoinc.system.ibms.command;

import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.nicoinc.system.ibms.command.RequestResult.COMMAND;
import com.nicoinc.system.ibms.http.HttpRequest;

public abstract class Command extends Thread {
    protected static final String WEB_URL = "http://www.rjvalentesdedavi.com.br/ibms_system/";
    protected static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    private final Set<CommandListener> mListeners = new CopyOnWriteArraySet<CommandListener>();
    protected final RequestResult mResult;
    protected final HttpRequest mHttpRequest;
    private static String sToken;

    public Command(COMMAND commandId, String url) {
        mResult = new RequestResult(commandId);
        if (url == null) {
            mHttpRequest = null;
        } else {
            mHttpRequest = new HttpRequest(WEB_URL + url, mResult);
        }
    }

    public final void addListener(final CommandListener listener) {
        if (listener != null) {
            mListeners.add(listener);
        }
    }

    public final void removeListener(final CommandListener listener) {
        mListeners.remove(listener);
    }

    private final void notifyListeners() {
        for (CommandListener listener : mListeners) {
            listener.onCommandFinished(mResult);
        }
    }

    @Override
    public final void run() {
        try {
            doRun();
        } finally {
            notifyListeners();
        }
    }

    public void doRun() {
        if (mHttpRequest != null) {
            mHttpRequest.addParam("TOKEN", sToken);
            mHttpRequest.start();
        }
    }

    public static void setToken(String token) {
        sToken = token;
    }
}
