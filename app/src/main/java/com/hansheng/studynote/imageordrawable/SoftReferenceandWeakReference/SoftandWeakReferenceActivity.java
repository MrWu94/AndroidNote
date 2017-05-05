package com.hansheng.studynote.imageordrawable.SoftReferenceandWeakReference;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.hansheng.studynote.activity.BaseActivity;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mrwu on 2017/1/8.
 * 软引用和弱引用。
 * 如果一个对象只具有软引用，那么如果内存空间足够，垃圾回收器就不会回收它；如果内存空间不足了，
 * 就会回收这些对象的内存。只要垃圾回收器没有回收它，该对象就可以被程序使用。软引用可用来实现内存敏感的高速缓存。
 * 软引用可以和一个引用队列（ReferenceQueue）联合使用，
 * 如果软引用所引用的对象被垃圾回收，Java虚拟机就会把这个软引用加入到与之关联的引用队列中。
 * 如果一个对象只具有弱引用，那么在垃圾回收器线程扫描的过程中，一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，
 * 都会回收它的内存。不过，由于垃圾回收器是一个优先级很低的线程，因此不一定会很快发现那些只具有弱引用的对象。
 * 弱引用也可以和一个引用队列（ReferenceQueue）联合使用，
 * 如果弱引用所引用的对象被垃圾回收，Java虚拟机就会把这个弱引用加入到与之关联的引用队列中。
 * <p>
 * 弱引用与软引用的根本区别在于：只具有弱引用的对象拥有更短暂的生命周期，可能随时被回收。
 * 而只具有软引用的对象只有当内存不够的时候才被回收，在内存足够的时候，通常不被回收。
 * <p>
 * 到底什么时候使用软引用，什么时候使用弱引用呢？
 * 个人认为，如果只是想避免OutOfMemory异常的发生，则可以使用软引用。如果对于应用的性能更在意，想尽快回收一些占用内存比较大的对象，
 * 则可以使用弱引用。
 * 还有就是可以根据对象是否经常使用来判断。如果该对象可能会经常使用的，就尽量用软引用。如果该对象不被使用的可能性更大些，
 * 就可以用弱引用
 */

public class SoftandWeakReferenceActivity extends BaseActivity {

    private Map<String, SoftReference<Bitmap>> imageCache = new HashMap<String, SoftReference<Bitmap>>();

    @Override
    protected int initContentView() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    //再来定义一个方法，保存Bitmap的软引用到HashMap。


    public void addToBitmapCache(String path) {
        // 强引用的Bitmap对象
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        // 软引用的Bitmap对象
        SoftReference<Bitmap> softBitmap = new SoftReference<Bitmap>(bitmap);
        // 添加该对象到Map中使其缓存
        imageCache.put(path, softBitmap);
    }

    // 获取的时候，可以通过SoftReference的get()方法得到Bitmap对象。
    public Bitmap getBitmapByPath(String path) {
        // 从缓存中取软引用的Bitmap对象
        SoftReference<Bitmap> softBitmap = imageCache.get(path);
        // 判断是否存在软引用
        if (softBitmap == null) {
            return null;
        }
        // 取出Bitmap对象，如果由于内存不足Bitmap被回收，将取得空
        Bitmap bitmap = softBitmap.get();
        return bitmap;
    }

}
