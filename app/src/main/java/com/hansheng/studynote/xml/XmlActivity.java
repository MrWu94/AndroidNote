package com.hansheng.studynote.xml;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hansheng.studynote.R;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by hansheng on 2016/6/26.
 */
public class XmlActivity extends AppCompatActivity {

    private static final String TAG = "XML";

    private BookParser parser;
    private List<Book> books;
    private  Button readBtn;
    private Button writeBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xml_layout);
        readBtn = (Button) findViewById(R.id.readBtn);
        writeBtn = (Button) findViewById(R.id.writeBtn);

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputStream is=getAssets().open("books.xml");
                    parser=new PullBookParser();
                    try {
                        books=parser.parse(is);
                        for (Book book:books){
                            Log.i(TAG,book.toString());

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String xml=parser.serialize(books);
                    FileOutputStream fos=openFileOutput("books.xml", Context.MODE_APPEND);
                    fos.write(xml.getBytes("UTF-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
