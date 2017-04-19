package com.liushuai.network;

import org.ksoap2.SoapEnvelope;

/**
 * Created by Panda on 2017/3/6.
 */

public interface Callback {
    void onResponse(SoapEnvelope envelope);

    void onFailure(Object o);
}
