package com.hansheng.studynote.rxjava;


import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;

/**
 * Created by hansheng on 2016/6/30.
 */
public class StudyRxJava {
    /**
     * 操作符大致分为以下几种：
     * Creating Observables(Observable的创建操作符)，比如：Observable.create()、Observable.just()、Observable.from()等等；
     * Transforming Observables(Observable的转换操作符)，比如：observable.map()、observable.flatMap()、observable.buffer()等等；
     * Filtering Observables(Observable的过滤操作符)，比如：observable.filter()、observable.sample()、observable.take()等等；
     * Combining Observables(Observable的组合操作符)，比如：observable.join()、observable.merge()、observable.combineLatest()等等；
     * Error Handling Operators(Observable的错误处理操作符)，比如:observable.onErrorResumeNext()、observable.retry()等等；
     * Observable Utility Operators(Observable的功能性操作符)，比如：observable.subscribeOn()、observable.observeOn()、observable.delay()等等；
     * Conditional and Boolean Operators(Observable的条件操作符)，比如：observable.amb()、observable.contains()、observable.skipUntil()等等；
     * Mathematical and Aggregate Operators(Observable数学运算及聚合操作符)，比如：observable.count()、observable.reduce()、observable.concat()等等；
     * 其他如observable.toList()、observable.connect()、observable.publish()等等；
     * <p>
     * <p>
     * flatMap的作用就是对传入的对象进行处理，返回下一级所要的对象的Observable包装
     * FuncX和ActionX的区别。FuncX包装的是有返回值的方法，用于Observable的变换、组合等等；ActionX用于包装无返回值的方法，
     * 用于subscribe方法的闭包参数。Func1有两个入参，前者是原始的参数类型，后者是返回值类型；而Action1只有一个入参，就是传入的被消费的数据类型。
     * subscribeOn(Schedulers.io()).observeOn(AndroidScheduler.mainThread())是最常用的方式，后台读取数据，主线程更新界面。
     * subScribeOn指在哪个线程发射数据，observeOn是指在哪里消费数据
     * 那么到底map和flatMap有什么区别，或者说什么时候使用map什么时候使用flatMap呢？
     * flatMap() 和 map() 有一个相同点：它也是把传入的参数转化之后返回另一个对象。但需要注意，和 map() 不同的是，
     * flatMap() 中返回的是个 Observable 对象，并且这个 Observable 对象并不是被直接发送到了 Subscriber 的回调方法中。
     * 首先，如果你需要将一个类型的对象经过处理（非异步）直接转化成下一个类型，推荐用map，否则的话就用flatMap。
     * 其次，如果你需要在处理中加入容错的机制（特别是你自己封装基于RxJava的网络请求框架），推荐用flatMap。
     * flatMap是一个Observable的操作符，接受一个Func1闭包，这个闭包的第一个函数是待操作的上一个数据流中的数据类型，
     * 第二个是这个flatMap操作完成后返回的数据类型的被封装的Observable。说白了就是讲一个多级数列“拍扁”成了一个一级数列。
     * <p>
     * <p
     */

    public static void main(String[] args) {
//        study();
//        study1();
//        study2();
//        study3();
//        study4();
//        study5();
//        study6();
//        study7();
//        study8();
//        study9();
//        study10();
//        study11();
//        study12();
//        study13();
//        study14();
//        study15();
//        study16();
//        study17();
//        study21();
//        study22();
//        study23();
//        study24();
//        study25();
//        study26();
        study31();

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

    /**
     * just操作符
     * just操作符也是把其他类型的对象和数据类型转化成Observable，它和from操作符很像，只是方法的参数有所差别，
     */
    public static void study2() {
        Observable.just("hello world 3")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });
    }

    public static void study3() {
        Observable.just("hello world 4")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + " hansheng";
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });
    }

    /**
     * map操作符
     * <p>
     * map操作符是把源Observable产生的结果，通过映射规则转换成另一个结果集，并提交给订阅者进行处理
     */

    public static void study4() {
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

    public static void study5() {
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

    /**
     * 在使用create操作符时，最好要在回调的call函数中增加isUnsubscribed的判断，以便在subscriber在取消订阅时不会再执行call函数中相关代码逻辑，
     * 从而避免导致一些意想不到的错误出现；
     */
    public static void study6() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    for (int i = 1; i < 5; i++) {
                        subscriber.onNext(i);
                    }
                    subscriber.onCompleted();
                }
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("Error: " + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("Next: " + integer);
            }
        });
    }

    /**
     * from操作符
     * <p>
     * from操作符是把其他类型的对象和数据类型转化成Observable
     */

    public static void study7() {
        final Integer[] items = {0, 1, 2, 3, 4, 5};
        Observable.from(items)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("Error encountered: " + throwable.getMessage());
                    }
                }, new Action0() {

                    @Override
                    public void call() {
                        System.out.println("Sequence complete");
                    }
                });
    }

    /**
     * just操作符
     * <p>
     * just操作符也是把其他类型的对象和数据类型转化成Observable，它和from操作符很像，只是方法的参数有所差别，
     */
    public static void study8() {
        Observable.just(1, 2, 3, 4)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return (integer < 4);
                    }
                }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("Error: " + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("Next: " + integer);
            }
        });
    }

    /**
     * first操作符
     * first操作符是把源Observable产生的结果的第一个提交给订阅者，first操作符可以使用elementAt(0)和take(1)替代。
     */

    public static void study9() {
        Observable.just(1, 2, 3)
                .first()
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Sequence complete.");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.err.println("Error: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("Next: " + integer);

                    }
                });
    }

    public static void study10() {
        Observable.just("hello world 10 ")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(Integer.toString(integer));
            }
        });

    }

    /**
     * defer操作符
     * defer操作符是直到有订阅者订阅时，才通过Observable的工厂方法创建Observable并执行，defer操作符能够保证Observable的状态是最新的，
     */
    public static void study11() {
        final int i = 12;
        Observable justObservable = Observable.just(i);
        Observable deferObservable = Observable.defer(new Func0<Observable<Integer>>() {
            @Override
            public Observable<Integer> call() {
                return Observable.just(i);
            }
        });
        justObservable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                System.out.println("just result:" + o.toString());
            }
        });

        deferObservable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                System.out.println("defer result:" + o.toString());
            }
        });

    }

    /**
     * just操作符是在创建Observable就进行了赋值操作，而defer是在订阅者订阅时才创建Observable，此时才进行真正的赋值操作
     */
    public static void study12() {
        Observable.timer(2, 2, TimeUnit.SECONDS).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error:" + e.getMessage());
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("Next:" + aLong.toString());
            }
        });
    }

    public static void study13() {
        Observable.range(3, 10).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("Next:" + integer.toString());
            }
        });
    }

    /**
     * repeat/repeatWhen操作符
     * <p>
     * repeat操作符是对某一个Observable，重复产生多次结果
     */

    public static void study14() {
        Observable.range(3, 3).repeat(2).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error:" + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("Next:" + integer.toString());
            }
        });
    }

    /**
     * flatMap操作符
     * <p>
     * flatMap操作符是把Observable产生的结果转换成多个Observable，然后把这多个Observable“扁平化”成一个Observable，并依次提交产生的结果给订阅者。
     */

    public static void study15() {
        Observable.just(1, 2, 3).repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Void> observable) {
                return observable.zipWith(Observable.range(1, 3), new Func2<Void, Integer, Object>() {
                    @Override
                    public Object call(Void aVoid, Integer integer) {
                        return integer;
                    }
                }).flatMap(new Func1<Object, Observable<?>>() {
                    @Override
                    public Observable<?> call(Object o) {
                        //1秒钟重复一次
                        return Observable.timer(1, TimeUnit.SECONDS);
                    }
                });
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("Next:" + integer);
            }
        });

    }

    public static void study16() {
        //定义邮件内容
        final String[] mails = new String[]{"Here is an email!", "Another email!", "Yet another email!"};
        //每隔1秒就随机发布一封邮件
        Observable<String> endlessMail = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    if (subscriber.isUnsubscribed()) return;
                    Random random = new Random();
                    while (true) {
                        String mail = mails[random.nextInt(mails.length)];
                        subscriber.onNext(mail);
                        Thread.sleep(1000);
                    }
                } catch (Exception ex) {
                    subscriber.onError(ex);
                }
            }
        }).subscribeOn(Schedulers.io());
        //把上面产生的邮件内容缓存到列表中，并每隔3秒通知订阅者
        endlessMail.buffer(3, TimeUnit.SECONDS).subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> list) {

                System.out.println(String.format("You've got %d new messages!  Here they are!", list.size()));
                for (int i = 0; i < list.size(); i++)
                    System.out.println("**" + list.get(i).toString());
            }
        });

    }

    /**
     * map操作符是把源Observable产生的结果，通过映射规则转换成另一个结果集，并提交给订阅者进行处理。
     */
    public static void study17() {
        Observable.just(1, 2, 3, 4, 5, 6).map(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                //对源Observable产生的结果，都统一乘以3处理
                return integer * 3;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("next:" + integer);
            }
        });
    }

    public static void study18() {

        Observable.interval(1, TimeUnit.SECONDS).take(10).groupBy(new Func1<Long, Long>() {
            @Override
            public Long call(Long value) {
                //按照key为0,1,2分为3组
                return value % 3;
            }
        }).subscribe(new Action1<GroupedObservable<Long, Long>>() {
            @Override
            public void call(final GroupedObservable<Long, Long> result) {
                result.subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long value) {
                        System.out.println("key:" + result.getKey() + ", value:" + value);
                    }
                });
            }
        });
    }

    /**
     * scan操作符
     * <p>
     * scan操作符通过遍历源Observable产生的结果，依次对每一个结果项按照指定规则进行运算，计算后的结果作为下一个迭代项参数，每一次迭代项都会把计算结果输出给订阅者。
     */
    public static void study20() {
        Observable.just(1, 2, 3, 4, 5)
                .scan(new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer sum, Integer item) {
                        //参数sum就是上一次的计算结果
                        return sum + item;
                    }
                }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onNext(Integer item) {
                System.out.println("Next: " + item);
            }

            @Override
            public void onError(Throwable error) {
                System.err.println("Error: " + error.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }
        });
    }

    /**
     * *filter操作符
     * filter操作符是对源Observable产生的结果按照指定条件进行过滤，只有满足条件的结果才会提交给订阅者
     */
    public static void study21() {
        Observable.just(1, 2, 3, 4, 5)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer item) {
                        return (item < 4);
                    }
                }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onNext(Integer item) {
                System.out.println("Next: " + item);
            }

            @Override
            public void onError(Throwable error) {
                System.err.println("Error: " + error.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }
        });
    }

    /**
     * last操作符
     * last操作符把源Observable产生的结果的最后一个提交给订阅者，last操作符可以使用takeLast(1)替代。
     */
    public static void study22() {
        Observable.just(1, 2, 3)
                .last()
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer item) {
                        System.out.println("Next: " + item);
                    }

                    @Override
                    public void onError(Throwable error) {
                        System.err.println("Error: " + error.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("Sequence complete.");
                    }
                });
    }

    /**
     * skip操作符
     * <p>
     * skip操作符针对源Observable产生的结果，跳过前面n个不进行处理，而把后面的结果提交给订阅者处理
     */
    public static void study23() {
        Observable.just(1, 2, 3, 4, 5, 6, 7).skip(3)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer item) {
                        System.out.println("Next: " + item);
                    }

                    @Override
                    public void onError(Throwable error) {
                        System.err.println("Error: " + error.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("Sequence complete.");
                    }
                });
    }

    /**
     * skipLast操作符
     * skipLast操作符针对源Observable产生的结果，忽略Observable最后产生的n个结果，而把前面产生的结果提交给订阅者处理，
     */
    public static void study24() {
        Observable.just(1, 2, 3, 4, 5, 6, 7).skipLast(3)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer item) {
                        System.out.println("Next: " + item);
                    }

                    @Override
                    public void onError(Throwable error) {
                        System.err.println("Error: " + error.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("Sequence complete.");
                    }
                });
    }

    /**
     * take操作符
     * take操作符是把源Observable产生的结果，提取前面的n个提交给订阅者，而忽略后面的结果
     */
    public static void study25() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8)
                .take(4)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer item) {
                        System.out.println("Next: " + item);
                    }

                    @Override
                    public void onError(Throwable error) {
                        System.err.println("Error: " + error.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("Sequence complete.");
                    }
                });

    }

    /**
     * takeFirst操作符
     * <p>
     * takeFirst操作符类似于take操作符，同时也类似于first操作符，都是获取源Observable产生的结果列表中符合指定条件的前一个或多个，
     * 与first操作符不同的是，first操作符如果获取不到数据，则会抛出NoSuchElementException异常，而takeFirst则会返回一个空的Observable，
     * 该Observable只有onCompleted通知而没有onNext通知。
     */
    public static void study26() {
        Observable.just(1, 2, 3, 4, 5, 6, 7).takeFirst(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                //获取数值大于3的数据
                return integer > 3;
            }
        })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer item) {
                        System.out.println("Next: " + item);
                    }

                    @Override
                    public void onError(Throwable error) {
                        System.err.println("Error: " + error.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("Sequence complete.");
                    }
                });
    }

    /**
     * takeLast操作符
     * <p>
     * takeLast操作符是把源Observable产生的结果的后n项提交给订阅者，提交时机是Observable发布onCompleted通知之时。
     */
    public static void study27() {
        Observable.just(1, 2, 3, 4, 5, 6, 7).takeLast(2)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer item) {
                        System.out.println("Next: " + item);
                    }

                    @Override
                    public void onError(Throwable error) {
                        System.err.println("Error: " + error.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("Sequence complete.");
                    }
                });
    }

    /**
     * merge操作符
     * <p>
     * merge操作符是按照两个Observable提交结果的时间顺序，对Observable进行合并，如ObservableA每隔500毫秒产生数据为0,5,10,15,20；
     * 而ObservableB每隔500毫秒产生数据0,10,20,30,40，其中第一个数据延迟500毫秒产生，最后合并结果为：0,0,5,10,10,20,15,30,20,40
     */
    public static void study28() {
        //产生0,5,10,15,20数列
        Observable<Long> observable1 = Observable.timer(0, 1000, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return aLong * 5;
                    }
                }).take(5);

        //产生0,10,20,30,40数列
        Observable<Long> observable2 = Observable.timer(500, 1000, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return aLong * 10;
                    }
                }).take(5);

        Observable.merge(observable1, observable2)
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Sequence complete.");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.err.println("Error: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("Next:" + aLong);
                    }
                });
    }

    /**
     * startWith操作符
     * <p>
     * startWith操作符是在源Observable提交结果之前，插入指定的某些数据
     */
    public static void study29() {
        Observable.just(10, 20, 30).startWith(2, 3, 4).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("Error: " + e.getMessage());
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("Next:" + value);
            }
        });

    }

    /**
     * Scheduler线程切换
     这种场景经常会在“后台线程取数据，主线程展示”的模式中看见
     */

    public static void study30() {
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }

    /**
     *其实很简单:
     Observable.subscribe()方法可以返回一个Subscription的对象,即我们每次订阅都会返回.
     感觉Subscription就像一个订单,你下单了就会生成一个订单,而你也可以用这个订单取消订单.
     */
    public static void study31(){
        Subscription subscription=Observable.just("hello subscription")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });
        System.out.println(subscription.isUnsubscribed());
        subscription.unsubscribe();
        System.out.println(subscription.isUnsubscribed());
    }



}
