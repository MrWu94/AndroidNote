package com.hansheng.studynote.OkHttp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hansheng.studynote.R;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.http.FormUrlEncoded;

/**
 * Created by hansheng on 2016/7/4.
 */
public class OkHttpPostActivity extends AppCompatActivity{

    TextView output;
    Button bsend;
    EditText etName, etPass;
    final String url = "http://serviceapi.skholingua.com/open-feeds/display_received_params.php";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okhttp_post_layout);
        output = (TextView) findViewById(R.id.tvpost);
        etName = (EditText) findViewById(R.id.etName);
        etPass = (EditText) findViewById(R.id.etPass);
        bsend = (Button) findViewById(R.id.bSend);

        bsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=etName.getText().toString();
                String pass=etPass.getText().toString();
                OkHttp handler=new OkHttp(userName,pass);

                String result=null;

                try {
                    result=handler.execute(url).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                output.append(result + "\n");
            }
        });
    }

    class OkHttp extends AsyncTask<String,Void,String>{

        OkHttpClient client=new OkHttpClient();
        String userName, pass;

        public OkHttp(String userName, String pass) {
            this.userName = userName;
            this.pass = pass;
        }

        @Override
        protected String doInBackground(String... params) {
            RequestBody body = new FormBody.Builder()
                    .add("name",userName)
                    .add("pass",pass)
                    .build();
            Request request=new Request.Builder()
                    .url(params[0]).post(body).build();
            try {
                Response response=client.newCall(request).execute();
                if(!response.isSuccessful()){
                    return response.body().string();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
