package com.liushuai.network;

/**
 * Created by Panda on 2017/3/6.
 */

public final class SoapClient {
    private Dispatcher mDispatcher;

    public SoapClient() {
        mDispatcher = new Dispatcher();
    }

    public Dispatcher getDispatcher() {
        return mDispatcher;
    }

    public SoapCall newCall(SoapRequest request) {
        return new SoapCall(request, this);
    }
}
