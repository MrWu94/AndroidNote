package com.hansheng.studynote.volleydemo.volleryHttps;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.http.AndroidHttpClient;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.hansheng.studynote.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by wfq on 2016/11/27.
 */

public class VolleyHttpsActivity extends AppCompatActivity {
    private static final String TAG="VolleyHttpsActivity";
    private static final String DEFAULT_CACHE_DIR ="/DESKTOP" ;
    RequestQueue queue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);
        try {
            queue=createHttpsRequestQueue(this,null,getAssets().open("srca.cer"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringRequest stringRequest=new StringRequest("https://kyfw.12306.cn/otn/", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.d(TAG,s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG,volleyError.getMessage(),volleyError);

            }
        });
        queue.add(stringRequest);




    }

    private SSLSocketFactory getSocketFactory(InputStream...certificates)
    {
        try
        {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates)
            {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

                try
                {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e)
                {
                }
            }

            SSLContext sslContext = SSLContext.getInstance("TLS");

            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

            trustManagerFactory.init(keyStore);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());

            return sslContext.getSocketFactory();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public RequestQueue createHttpsRequestQueue(Context context, HttpStack stack, InputStream... certificates)
    {
        File cacheDir=new File(context.getCacheDir(),DEFAULT_CACHE_DIR);
        String userAgent="volley/0";

        try
        {
            String packageName=context.getPackageName();
            PackageInfo info=context.getPackageManager().getPackageInfo(packageName,0);
            userAgent=packageName+"/"+info.versionCode;
        }catch(PackageManager.NameNotFoundException ex)
        {
            ex.printStackTrace();
        }

        if(stack==null)
        {
            if(Build.VERSION.SDK_INT>=9)
            {
                stack=new HurlStack(null,getSocketFactory(certificates));
            }else{
                stack=new HttpClientStack(AndroidHttpClient.newInstance(userAgent));
            }
        }

        Network network=new BasicNetwork(stack);

        RequestQueue queue=new RequestQueue(new DiskBasedCache(cacheDir),network);
        queue.start();

        return queue;
    }
}
