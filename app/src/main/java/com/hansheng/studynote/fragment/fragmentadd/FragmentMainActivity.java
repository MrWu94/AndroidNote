package com.hansheng.studynote.fragment.fragmentadd;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Window;

import com.hansheng.studynote.R;
import com.orhanobut.hawk.Hawk;

import hugo.weaving.DebugLog;
import timber.log.Timber;

/**
 * Created by hansheng on 16-12-2.
 * 1、为什么需要判null呢？
 * 主要是因为，当Activity因为配置发生改变（屏幕旋转）或者内存不足被系统杀死，造成重新创建时，我们的fragment会被保存下来，
 * 但是会创建新的FragmentManager，
 * 新的FragmentManager会首先会去获取保存下来的fragment队列，重建fragment队列，从而恢复之前的状态。
 * <p>
 * 2、add(R.id.id_fragment_container,mContentFragment)中的布局的id有何作用？
 * 一方面呢，是告知FragmentManager，此fragment的位置；另一方面是此fragment的唯一标识；
 * 就像我们上面通过fm.findFragmentById(R.id.id_fragment_container)查找~~
 */

public class FragmentMainActivity extends Activity {

    private FragmentOne fragmentOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.frag_main);
        Timber.tag("LifeCycles");
        Timber.d("Activity Created");

        timeHawkInit();
        timeHawkPut();
        timeHawkGet();
        timeHawkContains();
        timeHawkCount();
        timeHawkDelete();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        fragmentOne = (FragmentOne) fm.findFragmentById(R.id.id_content);
        if (fragmentOne == null) {
            tx.add(R.id.id_content, new FragmentOne(), "ONE");
            tx.commit();
        }
    }
    @DebugLog
    private void timeHawkDelete() {
        Hawk.delete("name");
        System.out.println("");
    }
    @DebugLog
    private void timeHawkCount() {
        System.out.println("count"+Hawk.count());
    }

    @DebugLog
    private void timeHawkContains() {
        System.out.println("contains"+Hawk.contains("name"));
    }
    @DebugLog
    private void timeHawkGet() {
        Hawk.get("name");
    }
    @DebugLog
    private void timeHawkPut() {
        Hawk.put("name","hansheng");
    }
    @DebugLog
    private void timeHawkInit() {
        long startTime= System.currentTimeMillis();
        Hawk.init(this).build();
        long endTime= System.currentTimeMillis();
        System.out.println("Hawk.init: " + (endTime - startTime) + "ms");
    }

}
