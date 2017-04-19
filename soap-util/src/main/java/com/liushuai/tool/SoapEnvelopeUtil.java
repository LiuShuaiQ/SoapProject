package com.liushuai.tool;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;

/**
 * Created by LiuShuai on 2017/4/13.
 */

public class SoapEnvelopeUtil {
    public static String getTextFromResponse(SoapEnvelope envelope) {
        if (envelope == null || envelope.bodyIn == null) {
            return null;
        }
        if (!(envelope.bodyIn instanceof SoapObject)) {
            return null;
        }
        if (((SoapObject) envelope.bodyIn).getPropertyCount() == 0){
            return null;
        }
        if (((SoapObject) envelope.bodyIn).getProperty(0) == null) {
            return null;
        }
        return ((SoapObject) envelope.bodyIn).getProperty(0).toString();
    }
}
