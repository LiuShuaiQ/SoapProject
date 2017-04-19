package liushuai.com.soapproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.liushuai.network.Callback;
import com.liushuai.tool.SoapEnvelopeUtil;

import org.ksoap2.SoapEnvelope;

public class MainActivity extends AppCompatActivity implements Callback {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SoapUtil.getInstance().getSupportCity("山东", this);
    }

    @Override
    public void onResponse(SoapEnvelope envelope) {
        Log.d(TAG, "result:--->" + envelope.bodyIn.toString());
        String text = SoapEnvelopeUtil.getTextFromResponse(envelope);
        Log.d(TAG, "result-text:--->" + text);
    }

    @Override
    public void onFailure(Object o) {

    }
}
