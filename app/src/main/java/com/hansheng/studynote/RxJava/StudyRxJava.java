package com.hansheng.studynote.RxJava;


import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by hansheng on 2016/6/30.
 */
public class StudyRxJava {

    public static void main(String[] args) {
        study();
        study1();
        study2();
        study3();
        study4();
        study5();

    }

    public static void study() {
        Observable<String> myObserable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello world 1");
                subscriber.onCompleted();
            }
        });


        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };
        myObserable.subscribe(mySubscriber);
    }

    public static void study1() {
        Observable<String> myObservable1 = Observable.just("hello world 2");
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        };
        myObservable1.subscribe(onNextAction);
    }

    public static void study2() {
        Observable.just("hello world 3")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });
    }
    public static void study3(){
        Observable.just("hello world 4")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s+" hansheng";
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });
    }

    public static void study4(){
        Observable.just("hello world 5")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer.toString());
                    }
                });
    }
    public static void study5(){
        Observable.just("hello world 6")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                })
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return integer.toString();
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });
    }

}
