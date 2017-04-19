package com.liushuai.network;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by LiuShuai on 2017/3/6.
 */

public final class Dispatcher {

    private int maxRequest = 64;
    private Runnable idleCallback;

    private ExecutorService mExecutorService;

    private Deque<Call> runningSyncCalls = new ArrayDeque<Call>();
    private Deque<SoapCall.AsyncCall> readyAsyncCalls = new ArrayDeque<SoapCall.AsyncCall>();
    private Deque<SoapCall.AsyncCall> runningAsyncCalls = new ArrayDeque<SoapCall.AsyncCall>();

    public Dispatcher(ExecutorService executorService) {
        mExecutorService = executorService;
    }

    public Dispatcher() {
    }

    public synchronized ExecutorService executorService() {
        if (mExecutorService == null) {
            mExecutorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>());
        }
        return mExecutorService;
    }

    public synchronized void enqueue(SoapCall.AsyncCall call) {
        if (runningAsyncCalls.size() < maxRequest) {
            runningAsyncCalls.add(call);
            executorService().execute(call);
        } else {
            readyAsyncCalls.add(call);
        }
    }

    public synchronized void executed(Call call) {
        runningSyncCalls.add(call);
    }

    public void finished(SoapCall.AsyncCall call) {
        finished(runningAsyncCalls, call, true);
    }

    public void finished(Call call) {
        finished(runningSyncCalls, call, false);
    }

    private <T> void finished(Deque<T> calls, T call, boolean promoteCalls) {
        synchronized (this) {
            if (!calls.remove(call)) throw new AssertionError("Call wasn't in-flight!");
            if (promoteCalls)
                promoteCalls();
        }
    }

    private void promoteCalls() {
        if (runningAsyncCalls.size() >= maxRequest) return;
        if (readyAsyncCalls.isEmpty()) return;

        for (Iterator<SoapCall.AsyncCall> i = readyAsyncCalls.iterator(); i.hasNext(); ) {
            SoapCall.AsyncCall call = i.next();
            i.remove();
            runningAsyncCalls.add(call);
            executorService().execute(call);
            if (runningAsyncCalls.size() >= maxRequest) return;
        }
    }

}
