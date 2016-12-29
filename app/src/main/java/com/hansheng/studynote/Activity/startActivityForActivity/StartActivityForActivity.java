package com.hansheng.studynote.Activity.startActivityForActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-12-5.
 * 执行startActivityForResult，没等到被调用的 Activity 返回，onActivityResult() 就被执行了。
 * 找了很久，终于通过小道消息得知，这与 Activity 的加载模式（launchMode）有关，该属性可以在 AndroidManifest.xml 中设置。
 * 原先将其设为 singleInstance，经测试，所有需要传递或接收的 Activity 不允许设置该属性，或只能设为标准模式，
 * 否则系统将在 startActivityForResult() 后直接调用 onActivityResult()。
 * 2、两个activity传递数据和返回数据时，请求方的onActivityResult始终无响应，通过debug调试模式也没见调用该方法。查看了各种配置和程序代码，
 * 均未发现有错误之处。后来仔细阅读API说明，恍然大悟，原来是调用startActivityForResult的参数问题，即调用时这样：
 * startActivityForResult(intent, 0);
 * 是第二个参数的问题，该参数必须大于0才能在返回值，并激活onActivityResult方法。
 * 我最开始是用的一个activity默认的常量：RESULT_OK，跟踪了代码后发现，该常量的值为-1，当然没法激活 onActivityResult方法了，
 * 随后随便修改为一个大于0的整数，程序即通跑成功。
 * startActivityForResult(intent, 1); //这样就行了
 * API描述：
 *
 * @requestCode If >= 0, this code will be returned in  onActivityResult() when the activity exits.
 * 待跳转的Activity启动模式错误：
 * standard与singletop模式是会在跳转后的Activity finish后执行onActivityResult，而singletask和singleinstance模式是在
 * startActivityforresult后立即执行onActivityResult；
 */

public class StartActivityForActivity extends AppCompatActivity {
    private static final String TAG = "StartActivity";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String result = data.getExtras().getString("result");//得到新Activity 关闭后返回的数据
        Log.d(TAG, result);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_main);
    }

    public void Result(View view) {
        //得到新打开Activity关闭后返回的数据
        //第二个参数为请求码，可以根据业务需求自己编号
        startActivityForResult(new Intent(StartActivityForActivity.this, OtherActivity.class), 1);
    }

}
