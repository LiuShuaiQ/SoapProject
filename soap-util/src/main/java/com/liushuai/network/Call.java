package com.liushuai.network;

import org.ksoap2.SoapEnvelope;

/**
 * Created by Panda on 2017/3/6.
 */

public interface Call {
    SoapEnvelope execute();

    void enqueue(Callback callback);
}
