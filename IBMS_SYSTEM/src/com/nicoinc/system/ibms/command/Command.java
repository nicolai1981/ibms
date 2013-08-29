package com.nicoinc.system.ibms.command;

import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.nicoinc.system.ibms.command.RequestResult.COMMAND;

public abstract class Command extends Thread {
    protected static final String WEB_URL = "http://www.rjvalentesdedavi.com.br/ibms_system/";
    protected static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    private final Set<CommandListener> listeners = new CopyOnWriteArraySet<CommandListener>();
    protected final RequestResult mResult;

    public Command(COMMAND commandId) {
        mResult = new RequestResult(commandId);
    }

    public final void addListener(final CommandListener listener) {
        if (listener != null) {
            listeners.add(listener);
        }
    }

    public final void removeListener(final CommandListener listener) {
        listeners.remove(listener);
    }

    private final void notifyListeners() {
        for (CommandListener listener : listeners) {
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

    public abstract void doRun();
}
