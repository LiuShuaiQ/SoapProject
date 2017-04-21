package com.liushuai.network;

import org.ksoap2.SoapEnvelope;

import java.util.HashMap;
import java.util.Map;

import okhttp3.HttpUrl;

/**
 * Created by LiuShuai on 2017/3/6.
 */

public class SoapRequest {

    private String endPoint;
    private String nameSpace;
    private String methodName;
    private String soapAction;
    private int version = SoapEnvelope.VER11;

    private boolean isDotNet;

    private Map<String, Object> mParamsMap;

    public SoapRequest(Builder builder) {
        this.endPoint = builder.endPoint;
        this.nameSpace = builder.nameSpace;
        this.methodName = builder.methodName;
        this.soapAction = builder.soapAction;
        this.mParamsMap = builder.mParamsMap;
        this.version = builder.version;
        this.isDotNet = builder.isDotNet;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setSoapAction(String soapAction) {
        this.soapAction = soapAction;
    }

    public void setParamsMap(Map<String, Object> paramsMap) {
        mParamsMap = paramsMap;
    }

    public void setDotNet(boolean dotNet) {
        isDotNet = dotNet;
    }

    public boolean isDotNet() {
        return isDotNet;
    }


    public String getEndPoint() {
        return endPoint;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getSoapAction() {
        return soapAction;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Map<String, Object> getParamsMap() {
        return mParamsMap;
    }

    public static class Builder {
        private String endPoint;
        private String nameSpace;
        private String methodName;
        private String soapAction;

        private int version = 0;

        private boolean isDotNet;

        private Map<String, Object> mParamsMap = new HashMap<String, Object>();

        public Builder endPoint(String endPoint) {
            this.endPoint = endPoint;
            return this;
        }

        public Builder nameSpace(String nameSpace) {
            this.nameSpace = nameSpace;
            return this;
        }

        public Builder methodName(String methodName) {
            this.methodName = methodName;
            return this;
        }

        public Builder soapAction(String soapAction) {
            this.soapAction = soapAction;
            return this;
        }

        public Builder setParams(Map<String, Object> params) {
            this.mParamsMap = params;
            return this;
        }

        public Builder addParam(String name, Object value) {
            mParamsMap.put(name, value);
            return this;
        }

        public Builder setVersion(int version) {
            this.version = version;
            return this;
        }

        public Builder setDotNet(boolean isDotNet) {
            this.isDotNet = isDotNet;
            return this;
        }

        public SoapRequest build() {
            if (endPoint == null) {
                throw new IllegalStateException("endPoint == null");
            }
            if (nameSpace == null) {
                throw new IllegalStateException("nameSpace == null");

            }
            if (methodName == null) {
                throw new IllegalStateException("methodName == null");

            }
            if (soapAction == null) {
                throw new IllegalStateException("soapAction == null");

            }
            if (version == 0) {
                version = SoapEnvelope.VER11;
            }

            return new SoapRequest(this);
        }

    }

}
