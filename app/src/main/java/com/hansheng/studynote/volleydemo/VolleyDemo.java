package com.hansheng.studynote.volleydemo;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hansheng.studynote.R;
import com.hansheng.studynote.volleydemo.bean.Weather;
import com.hansheng.studynote.volleydemo.bean.WeatherInfo;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by hansheng on 2016/6/28.
 */
public class VolleyDemo extends AppCompatActivity {
    private final static String TAG="Volley";

    private ImageView imageView;

   private RequestQueue mQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView= (ImageView) findViewById(R.id.image1);
        mQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest("http://www.baidu.com", new Response.Listener<String>() {
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



        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest("http://m.weather.com.cn/data/101010100.html", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d(TAG,jsonObject.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG,volleyError.getMessage(),volleyError);
            }
        });
        ImageRequest imageRequest=
                new ImageRequest("http://tupian.enterdesk.com/2012/0615/gha/15/1337566102yPLeJh.jpg",
                        new Response.Listener<Bitmap>(){
            @Override
            public void onResponse(Bitmap bitmap) {

                    imageView.setImageBitmap(bitmap);
            }
        },0,0, Bitmap.Config.RGB_565, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        XMLRequest xmlRequest = new XMLRequest(
                "http://flash.weather.com.cn/wmaps/xml/china.xml",
                new Response.Listener<XmlPullParser>() {
                    @Override
                    public void onResponse(XmlPullParser response) {
                        try {
                            int eventType = response.getEventType();
                            while (eventType != XmlPullParser.END_DOCUMENT) {
                                switch (eventType) {
                                    case XmlPullParser.START_TAG:
                                        String nodeName = response.getName();
                                        if ("city".equals(nodeName)) {
                                            String pName = response.getAttributeValue(0);
                                            Log.d("TAG", "pName is " + pName);
                                        }
                                        break;
                                }
                                eventType = response.next();
                            }
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });

        GsonRequest<Weather> gsonRequest=
                new GsonRequest<Weather>("http://www.weather.com.cn/data/sk/101010100.html", Weather.class, new Response.Listener<Weather>() {
                    @Override
                    public void onResponse(Weather weather) {
                        WeatherInfo weatherInfo=weather.getWeatherinfo();
                        Log.d("TAG", "city is " + weatherInfo.getCity());
                        Log.d("TAG", "temp is " + weatherInfo.getTemp());
                        Log.d("TAG", "time is " + weatherInfo.getTime());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("TAG", volleyError.getMessage(), volleyError);
                    }
                });
        mQueue.add(gsonRequest);
//        mQueue.add(imageRequest);
//        mQueue.add(jsonObjectRequest);
//        mQueue.add(stringRequest);
    }
}
