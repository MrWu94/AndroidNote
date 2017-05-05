package com.hansheng.studynote.http.OkHttp.recipes;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/**
 * Created by hansheng on 2016/7/2.
 */
public class Authenticate {
    private  final OkHttpClient client;
    public Authenticate() {
        client=new OkHttpClient.Builder()
                .authenticator(new Authenticator() {
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                        System.out.println("Authentication for response"+response);
                        System.out.println("challenges:"+response.challenges());
                        String credential= Credentials.basic("jesse","password1");
                        return response.request().newBuilder()
                                .header("Authorization", credential)
                                .build();

                    }
                })
                .build();
    }

    public  void run() throws IOException {
        Request request=new Request.Builder()
                .url("http://publicobject.com/secrets/hellosecret.txt")
                .build();
        Response response=client.newCall(request).execute();
        System.out.println(response.body().toString());
    }
    public static void main(String... args) throws IOException {
        new Authenticate().run();
    }
}
