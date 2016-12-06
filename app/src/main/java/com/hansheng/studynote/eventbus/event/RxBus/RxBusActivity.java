package com.hansheng.studynote.eventbus.event.RxBus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hansheng.studynote.R;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by hansheng on 16-12-6.
 */

public class RxBusActivity extends AppCompatActivity {

    private Subscription rxSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_layout);
       rxSubscription=RxBus.getDefault().toObservable(UserEvent.class)
                .subscribe(new Action1<UserEvent>() {
                               @Override
                               public void call(UserEvent userEvent) {
                                   long id = userEvent.getId();
                                   String name = userEvent.getName();
//                                   Toast.makeText(RxBusActivity.this, name, Toast.LENGTH_SHORT).show();

                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                // TODO: 处理异常
                            }
                        });

        RxBus.getDefault().toObservable(UserEvent.class)
                .subscribe(new RxBusSubscriber<UserEvent>() {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    protected void onEvent(UserEvent userEvent) {
                        String name = userEvent.getName();
                        Toast.makeText(RxBusActivity.this, name, Toast.LENGTH_SHORT).show();
                    }
                });

        RxSubscriptions.add(rxSubscription);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxSubscriptions.remove(rxSubscription);
//        if(!rxSubscription.isUnsubscribed()) {
//            rxSubscription.unsubscribe();
//        }
    }

    public void rxbus(View view) {
        RxBus.getDefault().post(new UserEvent(1, "hansheng"));
    }
}
