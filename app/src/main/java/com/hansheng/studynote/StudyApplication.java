package com.hansheng.studynote;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.avos.avoscloud.AVOSCloud;
import com.facebook.stetho.Stetho;
import com.hansheng.greendao.DaoMaster;
import com.hansheng.greendao.DaoSession;
import com.hansheng.hanshenghttpclient.net.HanShengClientConfig;
import com.hansheng.hanshenghttpclient.net.HanShengHttpClient;
import com.hansheng.studynote.SQLDataBase.SQLiteDBMultiTbl.data.DBHelper;
import com.hansheng.studynote.SQLDataBase.SQLiteDBMultiTbl.data.DatabaseManager;
import com.hansheng.studynote.loader.ImageLoaderUtil.UniversalAndroidImageLoader;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import timber.log.Timber;

/**
 * Created by hansheng on 2016/6/29.
 */
public class StudyApplication extends Application {

    public DaoSession daoSession;
    public SQLiteDatabase db;
    public DaoMaster.DevOpenHelper helper;
    public DaoMaster daoMaster;

    private static DBHelper dbHelper;

    private static Context context;

    public static RefWatcher getRefWatcher(Context context) {
        StudyApplication application = (StudyApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;


    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Stetho.initializeWithDefaults(this);

        refWatcher = LeakCanary.install(this);

        dbHelper = new DBHelper();
        DatabaseManager.initializeInstance(dbHelper);


        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this, "0HlFN3BDPl3lkWoJe3moMBA1-gzGzoHsz", "9EO3QcsyIyo6Sc4hkBHbnEaS");

////        LayoutManager.init(this);
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
        initTimber();
        setupDatabase();

        HanShengClientConfig clientConfig = new HanShengClientConfig(getApplicationContext());
        HanShengHttpClient.init(clientConfig);


//        //创建默认的ImageLoader配置参数
//        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
//                .writeDebugLogs() //打印log信息
//                .build();
//
//
//        ImageLoader.getInstance().init(configuration);

        UniversalAndroidImageLoader.init(this);

//        File cacheDir = StorageUtils.getCacheDirectory(this);
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
//                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
//                .threadPoolSize(3) // default
//                .threadPriority(Thread.NORM_PRIORITY - 1) // default
//                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
//                .denyCacheImageMultipleSizesInMemory()
//                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
//                .memoryCacheSize(2 * 1024 * 1024)
//                .memoryCacheSizePercentage(13) // default
//                .diskCacheSize(50 * 1024 * 1024)
//                .diskCacheFileCount(100)
//                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
//                .imageDownloader(new BaseImageDownloader(this)) // default
//                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
//                .writeDebugLogs()
//                .build();


    }


    private void initTimber() {
        Timber.uprootAll();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void setupDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        helper = new DaoMaster.DevOpenHelper(this, Constants.DB_NAME, null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
