# SoapProject


这是对于`ksoap2-android`的二次封装库
`soap-util`是这个项目的源码，soap-example是这个项目的使用实例
[博客链接](http://blog.csdn.net/LiuShuaiQ/article/details/70238810)

## 导入
1. 首先是引用`ksoap2-android`的`jar`包，这个在`soap-example`下的`libs`下有，你可以下载下来使用
2. 将`soap-util`依赖上，你可以使用项目依赖，同时你将项目打出jar包使用，相应的`jar`包在`soap-example`下的`libs`下存在

## 使用

```Java
    //没有特殊情况尽量保持一个SoapClient
    private SoapClient mSoapClient = new SoapClient();
     //设置是否是调试模式
    mSoapClient.setDebug(true);
```
- 异步调用
```Java
/*如果调用的是.net平台的WebService，请务必在构造SoapRequest的时候设置setDotNet(true)*/
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
- 同步调用
```Java
/*如果调用的是.net平台的WebService，请务必在构造SoapRequest的时候设置setDotNet(true)*/
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

