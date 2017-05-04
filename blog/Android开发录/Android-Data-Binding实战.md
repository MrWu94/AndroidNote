本文介绍使用Android Data Binding技术，结合NBA API，在RecycleView中展示NBA列表。

![](http://upload-images.jianshu.io/upload_images/1990324-897954f7f066e08a.gif?imageMogr2/auto-orient/strip)

代码实现
我根据MVVM(Model-View-ViewModel的顺序介绍代码实现。
Model层
Model层我定义了一个**Nba**类，要获得通知UI更新的功能，需要继承**BaseObservable**类；如果要能被View绑定，需要在get方法上添加**@Bindable**标注，同时在set方法中要调用*notifyPropertyChanged(BR.title)*方法通知View更新。

BR类会根据 **@Bindable** 标注自动生成
```java
public class Nba extends BaseObservable {

    private String contentType;
    private String description;
    private String title;
    private int articleId;
    private String contentSourceName;
    private String articleUrl;
    private String type;
    private String sourceType;
    private List<String> imgUrlList;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }
    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getContentSourceName() {
        return contentSourceName;
    }

    public void setContentSourceName(String contentSourceName) {
        this.contentSourceName = contentSourceName;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }
    @Bindable
    public List<String> getImgUrlList() {
        return imgUrlList;
    }

    public void setImgUrlList(List<String> imgUrlList) {
        this.imgUrlList = imgUrlList;
        notifyPropertyChanged(BR.imgUrlList);
    }
    ....

}

```
View层
需要在布局文件中使用**layout**作为最外层布局，同时在其中的**data**区域中声明一个nba变量，并指定类型为我们在Model中定义的**Nba**类。
在View中使用@{Nba.title}这样的表达式, 将变量进行绑定。
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">
    <data>
        <variable
            name="nba"
            type="shineloading.hansheng.com.nbadatabinding.model.Nba" />
    </data>
    <android.support.v7.widget.CardView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginBottom="@dimen/card_margin"
        android:layout_marginLeft="@dimen/card_margin"
        android:layout_marginRight="@dimen/card_margin"
        android:clickable="true">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:gravity="center_vertical"
            android:orientation="horizontal"
            android:padding="12dp">
            <ImageView
                android:id="@+id/ivNews"
                android:layout_width="86dp"
                android:layout_height="60dp"
                android:scaleType="centerCrop" />
            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:layout_marginLeft="8dp"
                android:orientation="vertical">

                <TextView
                    android:id="@+id/tvTitle"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:singleLine="true"
                    android:text="@{nba.title}"
                    android:textAppearance="@style/TextAppearance.AppCompat.Title"
                    android:textColor="@color/primary_text"
                    android:textSize="@dimen/sn_16sp" />

                <TextView
                    android:id="@+id/tvDesc"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_marginTop="6dp"
                    android:maxLines="2"
                    android:text="@{nba.description}"
                    android:textColor="@color/secondary_text"
                    android:textSize="@dimen/sn_12sp" />
            </LinearLayout>
        </LinearLayout>
    </android.support.v7.widget.CardView>
</layout>
```
ViewModel层
定义好了Model和View之后，需要把两者连起来，当Model的数据变化后，自动更新View。
Android Data Binding中的ViewModel是根据layout自动生成的Binding类，如果layout的名称是*nba_item.xml*，生成的Binding类名称就是NbaItemBinding。

+ 创建Binding类在RecyclerView的Adapter的onCreateViewHolder中创建Binding类
```java
NbaItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),  R.layout.nba_item, parent, false);
```
设置变量值在onBindViewHolder中设置layout中定义的movie变量值
```java
 Nba mnba= nba.get(position);
 holder.binding.setVariable(shineloading.hansheng.com.nbadatabinding.BR.nba, mnba);
  holder.binding.executePendingBindings();
```
显示图片使用Glide显示图片，ivNews是NbaItemBinding根据ImageView的id自动生成的
```java
  Glide.with(NbaActivity.this)
                        .load(img.get(0))
                        .fitCenter()
                        .into(holder.binding.ivNews);
```

Github
代码已经发布到Github，源码地址：**[NBADataBinding](https://github.com/fuqinwu/NBADataBinding)**
