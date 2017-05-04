MVP模式的作用

+ 分离了视图逻辑和业务逻辑，降低了耦合
+ Activity只处理生命周期的任务，代码变得更加简洁
+ 视图逻辑和业务逻辑分别抽象到了View和Presenter的接口中去，提高代码的可阅读性
+ Presenter被抽象成接口，可以有多种具体的实现，所以方便进行单元测试
+ 把业务逻辑抽到Presenter中去，避免后台线程引用着Activity导致Activity的资源无法被系统回收+ 从而引起内存泄露和OOM
其中最重要的有三点：

Activity 代码变得更加简洁
相信很多人阅读代码的时候，都是从Activity开始的，对着一个1000+行代码的Activity，看了都觉得难受。

使用MVP之后，Activity就能瘦身许多了，基本上只有FindView、SetListener以及Init的代码。其他的就是对Presenter的调用，还有对View接口的实现。这种情形下阅读代码就容易多了，而且你只要看Presenter的接口，就能明白这个模块都有哪些业务，很快就能定位到具体代码。Activity变得容易看懂，容易维护，以后要调整业务、删减功能也就变得简单许多。

MVP模式的使用
![](http://upload-images.jianshu.io/upload_images/1990324-05520aa842cd5ef9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

上面一张简单的MVP模式的UML图，从图中可以看出，使用MVP，至少需要经历以下步骤：

1、创建IPresenter接口，把所有业务逻辑的接口都放在这里，并创建它的实现PresenterCompl（在这里可以方便地查看业务功能，由于接口可以有多种实现所以也方便写单元测试）
2、创建IView接口，把所有视图逻辑的接口都放在这里，其实现类是当前的Activity/Fragment
3、由UML图可以看出，Activity里包含了一个IPresenter，而PresenterCompl里又包含了一个IView并且依赖了Model。Activity里只保留对IPresenter的调用，其它工作全部留到PresenterCompl中实现
4、Model并不是必须有的，但是一定会有View和Presenter

![](http://upload-images.jianshu.io/upload_images/1990324-d447a528696c760e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**什么是MVP架构**
MVP就是Model-View-Presenter，MVP是从经典的模式MVC演变而来，它们的基本思想有相通的地方：Controller/Presenter负责逻辑的处理，Model提供数据，View负责显示。作为一种新的模式，MVP与MVC有着一个重大的区别：在MVP中View并不直接使用Model，它们之间的通信是通过Presenter (MVC中的Controller)来进行的，所有的交互都发生在Presenter内部，而在MVC中View会直接从Model中读取数据而不是通过 Controller。

![](http://upload-images.jianshu.io/upload_images/1990324-eabeb6975a5ca46e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


**MVP架构：**
View： 对应于Activity，负责View的绘制以及与用户交互
Model： 依然是业务逻辑和实体模型
Presenter： 负责完成View于Model间的交互


![](http://upload-images.jianshu.io/upload_images/1990324-4b246bfe3139bb91.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

+ View不直接与Model交互，而是通过与Presenter交互来与Model间接交互。
+ Presenter与View的交互是通过接口来进行的。
+ 通常View与Presenter是一对一的，但复杂的View可能绑定多个Presenter来处理逻辑。
**1、Model层**
首先是一个javabean User实体类
```java
public class User {    
    private String name;    
    private String id;    
    private String sex;    
    private String age;    
    public String getName() {        
        return name;
    }    
    
    public void setName(String name) {        
        this.name = name;
    }    
    public String getId() {        
        return id;
    }    
    public void setId(String id) {        
        this.id = id;
    }    
    public String getSex() {        
        return sex;
    }    
    public void setSex(String sex) {        
        this.sex = sex;
    }    
    public String getAge() {        
        return age;
    }    
    public void setAge(String age) {        
        this.age = age;
    }
}
```

Model层抽象接口实现：
```java
public class GetUserInfo implements IGetUser {    
    @Override
    public void getUserInfo(final int id, final OnUserInfoListener listener) {        
        // 模拟子线程耗时操作
        new Thread() {            
            @Override
            public void run() {                
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }                // 模拟信息获取成功
                if (id == 1) {
                    User user = new User();
                    user.setName("非著名程序员");
                    user.setAge("26");
                    user.setSex("男");
                    user.setId("1");
                    listener.getUserInfoSuccess(user);
                } else {
                    listener.getUserInfoFailed();
                }
            }
        }.start();
    }

}
```
Model层抽象接口
```java
public interface IGetUser {    
   public void getUserInfo(int id, OnUserInfoListener listener);
}


public interface OnUserInfoListener {    
    void getUserInfoSuccess(User user);   
     
    void getUserInfoFailed();
}
```
**2、View层**
我们都知道Presenter与View交互是通过接口，所以我们需要定义一个IShowUserView的接口，这个接口封装的方法基本上都跟视图展示有关。
```
public interface IShowUserView {    
    void showLoading();    
    void hideLoading();    
    void toMainActivity(User user);    
    void showFailedError();
}
```
**3、Presenter层**
Presenter是Model和View之间交互的桥梁，里面有一些业务逻辑的操作。
```
public class UserInfoPresenter {    
    private IGetUser iGetUser;    
    private IShowUserView iShowUserView;    
    private Handler mHandler = new Handler();    
    public UserInfoPresenter(IShowUserView iShowUserView) {        
        this.iShowUserView = iShowUserView;        
        this.iGetUser = new GetUserInfo();
    }    
    
    public void getUserInfoToShow(int id) {
        iShowUserView.showLoading();
        iGetUser.getUserInfo(id, new OnUserInfoListener() {            @Override
            public void getUserInfoSuccess(final User user) {                // 需要在UI线程执行
                mHandler.post(new Runnable() {                    @Override
                    public void run() {
                        iShowUserView.toMainActivity(user);
                        iShowUserView.hideLoading();
                    }
                });
            }            @Override
            public void getUserInfoFailed() {
                mHandler.post(new Runnable() {                    @Override
                    public void run() {
                        iShowUserView.showFailedError();
                        iShowUserView.hideLoading();
                    }
                });
            }
        });
    }

}
```
**4、Activity中的调用**
```java
public class MainActivity extends Activity implements IShowUserView {    
    private Button btn;    
    private TextView name_tv, age_tv, sex_tv;    
    private ProgressDialog pd = null;    
    
    private UserInfoPresenter userInfoPresenter;    
    @Override
    protected void onCreate(Bundle savedInstanceState) {        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userInfoPresenter = new UserInfoPresenter(this);
        btn = (Button) findViewById(R.id.btn);
        name_tv = (TextView) findViewById(R.id.name_tv);
        age_tv = (TextView) findViewById(R.id.age_tv);
        sex_tv = (TextView) findViewById(R.id.sex_tv);
        pd = new ProgressDialog(this);
        pd.setMessage("正在加载……");

        btn.setOnClickListener(new View.OnClickListener() {            @Override
            public void onClick(View v) {
                userInfoPresenter.getUserInfoToShow(1);
            }
        });
    }    
    
    @Override
    public void showLoading() {
        pd.show();
    }    
    
    @Override
    public void hideLoading() {
        pd.cancel();
    }   
    
    @Override
    public void toMainActivity(User user) {
        name_tv.setText(user.getName());
        age_tv.setText(user.getAge());
        sex_tv.setText(user.getSex());
    }    
    
    @Override
    public void showFailedError() {
        Toast.makeText(this, "获取信息有误", Toast.LENGTH_SHORT).show();
    }

}
```
