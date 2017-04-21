#SoapProject
==============================
------------------------------
这是对于ksoap2-android的二次封装库
soap-util是这个项目的源码，soap-example是这个项目的使用实例

##导入
1)首先是引用ksoap2-android的jar包，这个在soap-example下的libs下有，你可以下载下来使用
2)将soap-util依赖上，你可以使用项目依赖，同时你将项目打出jar包使用，相应的jar包在soap-example下的libs下存在

##使用
1)
```Java
    //没有特殊情况尽量保持一个SoapClient
    private SoapClient mSoapClient = new SoapClient();
```
* 异步调用
```Java
public void getSupportCity(String cityName, Callback callback) {
        SoapRequest request = new SoapRequest.Builder().endPoint("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx")
                .methodName("getSupportCity")
                .soapAction("http://WebXml.com.cn/" + "getSupportCity")
                .addParam("byProvinceName", cityName)
                .nameSpace("http://WebXml.com.cn/")
                .setVersion(SoapEnvelope.VER11)
                .setDotNet(true)
                .build();
        mSoapClient.newCall(request).enqueue(callback);
    }
```
* 同步调用
```Java
public SoapEnvelope getSupportCity(String cityName) {
        SoapRequest request = new SoapRequest.Builder().endPoint("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx")
                .methodName("getSupportCity")
                .soapAction("http://WebXml.com.cn/" + "getSupportCity")
                .addParam("byProvinceName", cityName)
                .nameSpace("http://WebXml.com.cn/")
                .setVersion(SoapEnvelope.VER11)
                .setDotNet(true)
                .build();
        return mSoapClient.newCall(request).execute();
    }
```

