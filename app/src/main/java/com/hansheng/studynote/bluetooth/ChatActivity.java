package com.hansheng.studynote.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 2016/7/21.
 */
public class ChatActivity extends AppCompatActivity implements View.OnClickListener{



    private BluetoothDevice device;
    private ChatClient client;
    private EditText edit;
    private ArrayAdapter<String> adapter;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    adapter.add(msg.obj.toString());
                    break;
            }
        }
    };
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth_chat);
        device = getIntent().getParcelableExtra("device");
        if (TextUtils.isEmpty(device.getName())) {
            setTitle("没有名字");
        } else {
            setTitle(device.getName());
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        client = SocketThread.getClient(device);
        if (client == null){
            Toast.makeText(this, "此设备不支持聊天", Toast.LENGTH_SHORT).show();
            finish();
        }
        edit = ((EditText) findViewById(R.id.chat_edit));
        findViewById(R.id.chat_send).setOnClickListener(this);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        ListView listView = (ListView) findViewById(R.id.chat_list);
        listView.setAdapter(adapter);
        name = BluetoothAdapter.getDefaultAdapter().getName();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chat_send:
                String s = edit.getText().toString();
                if (!TextUtils.isEmpty(s)) {
                    client.send(s);
                    adapter.add(name + ":" + s);
                    edit.setText("");
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        client.unregister(handler);
    }
}
