package com.hansheng.studynote.Http.OkHttp.recipes;



import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hansheng on 2016/7/4.
 */
public class OkHttpDemo {
    private static OkHttpClient client=new OkHttpClient();

    public static void main(String... args) throws IOException {

            study2();
    }
    public static void study() throws IOException {
        HttpUrl.Builder urlbuilder=HttpUrl.parse("https://ajax.googleapis.com/ajax/services/search/images").newBuilder();
        urlbuilder.addQueryParameter("v","1.0");
        urlbuilder.addQueryParameter("q","android");
        urlbuilder.addQueryParameter("rsz","8");

        String url=urlbuilder.build().toString();
        Request request=new Request.Builder()
                .url(url)
                .build();
        Response response=client.newCall(request).execute();
        System.out.println(response.body().toString());

    }

    public static void study1(){
        Request request = new Request.Builder()
                .url("https://api.github.com/users/codepath")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                    String responseData = response.body().string();
                    System.out.println("owner="+responseData);
//                    JSONObject json = new JSONObject(responseData);
//                    final String owner = json.getString("name");
//                    System.out.println("owner="+owner);

            }
        });
    }
    static class GitUser {
        String name;
        String url;
        int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }


    public static void study2(){
        final Request request = new Request.Builder()
                .url("https://api.github.com/users/codepath")
                .build();
        // Create new gson object
        final Gson gson = new Gson();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                GitUser user=gson.fromJson(response.body().charStream(),GitUser.class);
                System.out.println("gituser="+user.getName());
            }
        });

    }
}
