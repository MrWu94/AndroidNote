package com.hansheng.studynote.Retrofit;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by hansheng on 2016/7/1.
 * 在adapt Call中，具体的调用了Call execute()，execute()是同步的，enqueue()是异步的。因为RxJava已经切换了线程，所以这里用同步方法execute()。
 */
public class SampleSerive {

    public static final String API_URL="http://api.github.com";

    public static class Contributor{
        public final String login;
        public final int contributions;

        public Contributor(String login, int contributions) {
            this.login = login;
            this.contributions = contributions;
        }
    }

    public interface Github{
       @GET("/repos/{owner}/{repo}/contributors")
        Call<List<Contributor>> contributors(
               @Path("owner") String owner,
               @Path("repo") String repo
       );
    }
    public static void main(String... args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Github github=retrofit.create(Github.class);

        Call<List<Contributor>> call=github.contributors("square","retrofit");

        List<Contributor> contributors=call.execute().body();

        for(Contributor contributor:contributors){
            System.out.println(contributor.login+ " (" + contributor.contributions + ")");
        }



    }
}
