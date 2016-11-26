package com.hansheng.studynote.Http.HttpClient;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hansheng.studynote.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

/**
 * Created by hansheng on 16-11-26.
 */

public class HttpsGetActivity1 extends AppCompatActivity {
    private static final String TAG = "HttpsGetActivity1";
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_item);
        context=getApplicationContext();
        new Thread(new Runnable() {
            @Override
            public void run() {

                String mUrl="https://kyfw.12306.cn/otn/";
                InputStream ins = null;
                String result = "";
                try {
                    ins = context.getAssets().open("srca.cer"); //下载的证书放到项目中的assets目录中
                    CertificateFactory cerFactory = CertificateFactory
                            .getInstance("X.509");
                    Certificate cer = cerFactory.generateCertificate(ins);
                    KeyStore keyStore = KeyStore.getInstance("PKCS12", "BC");
                    keyStore.load(null, null);
                    keyStore.setCertificateEntry("trust", cer);

                    SSLSocketFactory socketFactory = new SSLSocketFactory(keyStore);
                    Scheme sch = new Scheme("https", socketFactory, 443);
                    HttpClient mHttpClient = new DefaultHttpClient();
                    mHttpClient.getConnectionManager().getSchemeRegistry()
                            .register(sch);

                    BufferedReader reader = null;
                    try {
                        Log.d(TAG, "executeGet is in,murl:" + mUrl);
                        HttpGet request = new HttpGet();
                        request.setURI(new URI(mUrl));
                        HttpResponse response = mHttpClient.execute(request);
                        if (response.getStatusLine().getStatusCode() != 200) {
                            request.abort();
                            System.out.println("result="+result);
                        }

                        reader = new BufferedReader(new InputStreamReader(response
                                .getEntity().getContent()));
                        StringBuffer buffer = new StringBuffer();
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            buffer.append(line);
                        }
                        result = buffer.toString();
                        Log.d(TAG, "mUrl=" + mUrl + "\nresult = " + result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (reader != null) {
                            reader.close();
                        }
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                } finally {
                    try {
                        if (ins != null)
                            ins.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("result=" + result);
            }
        }).start();
    }
}
