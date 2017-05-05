package com.hansheng.studynote.http.HttpUrlConnection;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hansheng.studynote.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by wfq on 2016/11/27.
 */

public class HttpsUrlConnectionActivity1 extends AppCompatActivity {
    private Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);
        context=getApplicationContext();
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    // Load CAs from an InputStream
                    // (could be from a resource or ByteArrayInputStream or ...)

                    CertificateFactory cf = CertificateFactory.getInstance("X.509");
                    // From
                    // https://www.washington.edu/itconnect/security/ca/load-der.crt
                    InputStream caInput = context.getAssets().open("srca.cer");
                    Certificate ca;
                    try {
                        ca = cf.generateCertificate(caInput);
                        System.out.println("ca="
                                + ((X509Certificate) ca).getSubjectDN());
                    } finally {
                        caInput.close();
                    }

                    // Create a KeyStore containing our trusted CAs
                    String keyStoreType = KeyStore.getDefaultType();
                    KeyStore keyStore = KeyStore.getInstance(keyStoreType);
                    keyStore.load(null, null);
                    keyStore.setCertificateEntry("ca", ca);

                    // Create a TrustManager that trusts the CAs in our KeyStore
                    String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
                    TrustManagerFactory tmf = TrustManagerFactory
                            .getInstance(tmfAlgorithm);
                    tmf.init(keyStore);

                    // Create an SSLContext that uses our TrustManager
                    SSLContext context = SSLContext.getInstance("TLS");
                    context.init(null, tmf.getTrustManagers(), null);

                    URL url = new URL("https://kyfw.12306.cn/otn/");
                    HttpsURLConnection urlConnection = (HttpsURLConnection) url
                            .openConnection();

                    urlConnection.setSSLSocketFactory(context.getSocketFactory());
                    InputStream in = urlConnection.getInputStream();
                    // 取得输入流，并使用Reader读取
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(in));
                    System.out.println("=============================");
                    System.out.println("Contents of get request");
                    System.out.println("=============================");
                    String lines;
                    while ((lines = reader.readLine()) != null) {
                        System.out.println(lines);
                        // tv.setText(tv.getText().toString() + lines);
                    }
                    reader.close();
                    // 断开连接
                    urlConnection.disconnect();
                    System.out.println("=============================");
                    System.out.println("Contents of get request ends");
                    System.out.println("=============================");
                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (KeyManagementException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (KeyStoreException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (CertificateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


            }
        }).start();
    }
}
