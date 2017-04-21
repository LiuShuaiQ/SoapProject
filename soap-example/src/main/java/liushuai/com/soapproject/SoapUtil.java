package liushuai.com.soapproject;

import com.liushuai.network.Callback;
import com.liushuai.network.SoapClient;
import com.liushuai.network.SoapRequest;
import com.liushuai.tool.SoapEnvelopeUtil;

import org.ksoap2.SoapEnvelope;

/**
 * Created by LiuShuai on 2017/3/6.
 */

public class SoapUtil {
    private static final String TAG = "SoapUtil";
    private static SoapUtil mInstance;
    private SoapClient mSoapClient;

    public static final String mWeatherEndPoint = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";

    public static final String mNameSpace = "http://WebXml.com.cn/";
    public int mSOAPVersion = SoapEnvelope.VER11;

    private SoapUtil() {
        mSoapClient = new SoapClient();
        //设置是否是调试模式
        mSoapClient.setDebug(true);
    }

    public static synchronized SoapUtil getInstance() {
        if (mInstance == null) {
            mInstance = new SoapUtil();
        }
        return mInstance;
    }


    /**
     * 异步调用
     *
     * @param cityName
     * @param callback
     */
    public void getSupportCity(String cityName, Callback callback) {
        SoapRequest request = new SoapRequest.Builder().endPoint(mWeatherEndPoint)
                .methodName("getSupportCity")
                .soapAction(mNameSpace + "getSupportCity")
                .addParam("byProvinceName", cityName)
                .nameSpace(mNameSpace)
                .setVersion(mSOAPVersion)
                .setDotNet(true)
                .build();
        mSoapClient.newCall(request).enqueue(callback);
    }

    /**
     * 同步调用
     *
     * @param cityName
     * @return
     */
    public SoapEnvelope getSupportCity(String cityName) {
        SoapRequest request = new SoapRequest.Builder().endPoint(mWeatherEndPoint)
                .methodName("getSupportCity")
                .soapAction(mNameSpace + "getSupportCity")
                .addParam("byProvinceName", cityName)
                .nameSpace(mNameSpace)
                .setVersion(mSOAPVersion)
                .setDotNet(true)
                .build();
        return mSoapClient.newCall(request).execute();
    }

}
