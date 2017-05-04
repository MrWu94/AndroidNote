刚开始看到dagger2，真的是一脸懵逼，有什么作用，依赖注入是什么鬼，怎么注入。看了大量的文章，终于明白了，我决定写好这篇文章，对我对他人看了之后有种恍然大悟，原来就是这么回事？也并没有那么难。
先讲解几个概念方便下面的理解 
### 依赖
如果在Class A中，有Class B的实例，则称Class A对Class B有一个依赖。例如下面类Human中用到一个Father对象，我们就说human对类Father有一个依赖。
```java
public class Human{
     Father father;
     public Human(){
              father=new Father();
     }
}
```
仔细看这段代码我们会发现存在一些问题：
（1）如果现在要改变father生成方式，如需要用new Father(String name)初始化father,需要修改Human代码；
（2）如果想测试不同Father对象对Human的影响很困难，因为father的初始化被写死在了Human的构造函数中；
（3）如果new Father()过程非常缓慢，单侧时我们希望已经初始化好的father()对象Mock点这个过程也很困难那。
### 依赖注入
上面将依赖在构造函数中直接初始化是一种硬编码方式，所有还有初始化方式
```java
public class Human{
    Father father;
    public Human(Father father){
            this.father=father;    
    }
}
```
上面代码中，我们将father对象作为构造函数的一个参数传入。**在调用Human的构造方法之前外部就已经初始化的Father对象，像这种非自己主动初始化依赖，而通过外部来传入依赖的方式，我们就成为依赖**。
（1）解耦，将依赖之间解耦
（2）因为已经解耦，所以方便做单元测试
### Java的依赖注入
依赖注入的实现由多种途径，而在Java中，使用注解是常用的。通过字段的声明前添加@Inject注解进行标记，来实现依赖**对象**（实例化的对象）的自动注入。

###dagger2中Inject，Component，Module,Provides是什么
依赖注入：就是目标类（目标类需要进行依赖初始化的类）中所依赖的其他的类的初始化的过程，不是通过硬编码（new Father()）的方式创建，而是通过Component（搭桥）可以把**其他的类**已经**初始化好的实例**自动注入到目标类中。

![da.png](http://upload-images.jianshu.io/upload_images/1990324-f04f0967948d5cb9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

目标类想要初始化自己依赖的其他类（father=new Father()不要硬编码，而是@Inject Father father这样初始化）
+ 用Inject注解目标类中**其他类**（Father()）
+ 用Inject注解其他类（Fathe类）的构造函数
Component就像是桥梁，把**其他类**构造好的实例对象注入到目标类，来初始化目标类的依赖。
### Module是怎么回事
用@Module注解标注，主要用来提供有其他类中构造函数中有参数。无参构造函数其实用@Inject就ok了。
@Inject是无法标注第三位类库，系统类，以及接口。
@Provide其实正是解决那么有参构造函数提供依赖，当目标类用Inject注解属性后，Component首先会先提供参数依赖，然后才是**其他类**提供依赖。

讲了那么多废话，实战还是比较重要
```java
public class MainActivity extends AppCompatActivity implements MainContract.View {
    private MainPresenter mainPresenter;
    ...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化presenter 将view传递给presenter
        mainPresenter = new MainPresenter(this);
        //调用Presenter方法加载数据
         mainPresenter.loadData();

         ...
    }

}

public class MainPresenter {
    //MainContract是个接口，View是他的内部接口，这里看做View接口即可
    private MainContract.View mView;

    MainPresenter(MainContract.View view) {
        mView = view;
    }

    public void loadData() {
        //调用model层方法，加载数据
        ...
        //回调方法成功时
        mView.updateUI();
    }
```
这里Activity与presenter仅仅耦合在了一起，当需要改变presenter的构造方式时，需要修改这里的代码
如果是依赖注入的话
```java

public class MainPresenter {
    //MainContract是个接口，View是他的内部接口，这里看做View接口即可
    private MainContract.View mView;

    @Inject
    MainPresenter(MainContract.View view) {
        mView = view;
    }    
    public void loadData() {
        //调用model层方法，加载数据
        ...
        //回调方法成功时
        mView.updateUI();
    }

@Module
public class MainModule {
    private final MainContract.View mView;

    public MainModule(MainContract.View view) {
        mView = view;
    }

    @Provides
    MainView provideMainView() {
        return mView;
    }
}

@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
```
我们先看到MainActivity里的代码，之前是直接声明MainPresenter,现在在声明的基础上加了一个@Inject,表明MainPresenter是需要注入到MainActivity中，即MainActivity依赖于MainPresenter，这里要注意的是，使用@Inject时，不能使用private修饰符类的成员属性。

然而，他们之间并不会凭空建立起联系，因此我们想到，肯定需要一个桥梁，将他们连接在一起，也就是下面介绍的Component

Component是一个接口或者抽象类，用@Component注解标注（这里先不管括号里的modules），我们在这个接口中定义了一个inject()方法,参数是Mainactivity。然后rebuild一下项目，会生成一个以Dagger为前缀的Component类，这里是DaggerMainComponent，然后在MainActivity里完成下面代码.
```java
DaggerMainComponent.builder() 
                           .mainModule(new MainModule(this)) 
                           .build() 
                           .inject(this);
```
此时Component就将@Inject注解的mainPresenter与其构造函数联系了起来。此时，看到这里，如果是初学者的话，一定会非常迷惑，究竟是怎么建立起联系的，实例化过程在哪？别急，后面会讲解这个过程原理的。

此时我们已经完成了presenter的注入过程，但是我们发现还有一个MainModule类，这个类是做什么的？MainModlue是一个注解类，用@Module注解标注，主要用来提供依赖。等等，刚才通过@Inject就可以完成依赖，为什么这里还要用到Module类来提供依赖？之所以有Module类主要是为了提供那些没有构造函数的类的依赖，这些类无法用@Inject标注，比如第三方类库，系统类，以及上面示例的View接口。

我们在MainModule类里声明了MainContract.View成员属性，在构造方法里将外界传进来的view赋值给mView，并通过一个@Provides标注的以provide开头的方法，将这个view返回，这个以provide开头的方法就是提供依赖的，我们可以创建多个方法来提供不同的依赖。那么这个类究竟是怎么作用的？可以想到上面提到的@Component注解括号里的东西了。就是下面这个
```java
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
```
所以Module要发挥作用，还是要依靠于Component类，一个Component类可以包含多个Module类，用来提供依赖。我们接着看下面这段代码：
```
DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
```
这里通过new MainModule(this)将view传递到MainModule里，然后MainModule里的provideMainView()方法返回这个View，当去实例化MainPresenter时，发现构造函数有个参数，此时会在Module里查找提供这个依赖的方法，将该View传递进去，这样就完成了presenter里View的注入。

最后，来个实战一点的，欢迎start**[Movie](https://github.com/fuqinwu/Movie)**

![](http://upload-images.jianshu.io/upload_images/1990324-b014471e578cefa5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
