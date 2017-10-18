http://yifeng.studio/2016/10/13/android-tools-attributes/

tools:context

这个属性用在layout文件的根元素上，指明与当前layout相关联的Activity，从而在预览时使用Activity的主题（theme一般定义在
Manifest文件中并且与activities联系在一起，而非layout）。可以使用Activity的全名，也可以利用manifest中定义的包名作为前缀：

```java
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    tools:context=".MainActivity"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

</RelativeLayout>
```

布局预览
这里也分为两种，一种是替换标准的android命名空间的控件固有属性，举个例子：

```java
<TextView
	xmlns:tools="http://schemas.android.com/tools"
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	tools:text="Samples"/>

	tools:layout
    这个属性主要用于标签中，指定预览时用的layout布局文件内容：

    <fragment
    	android:layout_width="match_parent"
    	android:layout_height="match_parent"
    	tools:layout="@layout/fragment_content"/>

    	tools:listitem／listheader／listfooter
        顾名思义，这三个属性可用于诸如ListView、GridView、ExpandableListView等AdapterView的子类中，实现内容布局的预览。注意：经实践，在Android Studio中已经无法达到这些元素的内容预览效果，但tools:listitem属性可以用在RecyclerView中，比如：

        <android.support.v7.widget.RecyclerView
        	android:layout_width="match_parent"
        	android:layout_height="match_parent"
        	tools:listitem="@android:layout/simple_list_item_checked"/>


        	一般的控件属性以“android:”开头。新增一个同样的属性，改为以“tools:”开头。
            例如：一个TextView控件，需要设置默认状态为不可见。

            <TextView
                        android:text="Name"
                        android:visiblity="gone"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content" />
            但是你想预览它，又担心改为android:visiblity="visible"之后忘了改回来，直接提交了。现在你不必担心，新增一个同样名称的属性，以“tools:”开头，就可以了。

            <TextView
                        android:text="Name"
                        android:visiblity="gone"
                        tools:visiblity="visible"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content" />
```


