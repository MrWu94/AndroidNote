首先添加依赖
```java
dependencies {  
    // Retrofit & OkHttp
    compile 'com.squareup.retrofit2:retrofit:2.0.0'
    compile 'com.squareup.retrofit2:converter-gson:2.0.0'
    compile 'com.squareup.retrofit2:converter-gson:2.0.0' 
}
```
创建Retrofit实例
```java
public class ServiceGenerator {

    public static final String API_BASE_URL = "http://your.api-base.url";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
```

```java
public interface GitHubClient {  
    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributors(
        @Path("owner") String owner,
        @Path("repo") String repo
    );
}

static class Contributor {  
    String login;
    int contributions;
}
```
API请求示例
```java
public static void main(String... args) {  
    // Create a very simple REST adapter which points the GitHub API endpoint.
    GitHubClient client = ServiceGenerator.createService(GitHubClient.class);

    // Fetch and print a list of the contributors to this library.
    Call<List<Contributor>> call =
        client.contributors("fs_opensource", "android-boilerplate");

    try {
        List<Contributor> contributors = call.execute().body();
    } catch (IOException e) {
        // handle errors
    }

    for (Contributor contributor : contributors) {
        System.out.println(
                contributor.login + " (" + contributor.contributions + ")");
    }
}
```
（Query Parameters）遍历参数
```
https://api.example.com/tasks?id=123 
 
public interface TaskService{
      @GET("/tasks")Call<Task> getTask(@Query("id") long taskId);
}
```
Multiple Query Parameters()
```
https://api.example.com/tasks?id=123&id=124&id=125 
public interface TaskService{
      @Get("/tasks")Call<List<Task>> getTask(@Query("id") List<Long> taskIds);
}
```
