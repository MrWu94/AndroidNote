![](http://upload-images.jianshu.io/upload_images/1990324-3b80c743e03d15c0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##Linux
1、Host配置
```
cd ../..etc
sudo vim hosts
```
接着进入vim编辑界面，按insert键进入编辑模式，对hosts进行编辑，编辑完毕，按esc键， 退出编辑模式，键入:**wq**保存更改即可.

查看linux位数：
```
getconf LONG_BIT或者uname -a
```

刷机
```
adb reboot edl
```

2、.生成SSH KEY
```
ssh-keygen -t rsa -C "xxx@xx.com"
```
-t:指定秘钥类型，默认rsa -C:提供一个注释，不写默认是主机名 接着依次是文件名，密码，重复密码，这里没特别需要直接回车就好。 接着键入:**cd .ssh**来到ssh目录下，接着vim打开id_rsa.pub，复制SSH KEY贴 到[Git](http://lib.csdn.net/base/git)服务器的SSH Key即可。
3、adb服务不启动
找不到设备，terminal键入adb devices会出现xxx no permission的状况 
cd 来到adb所在的目录下，依次键入
```
sudo ./adb kill-server
sudo ./adb start-server
```
有时可能需要两次，一次不行试多次，直到出现：
```
daemon not running. starting it now on port 5037 *
daemon started successfully *
```
然后键入：adb devices，此时就可以看到设备就处于device状态了

4、unbutu安装主题
```
sudo add-apt-repository ppa:noobslab/themes
sudo apt-get update
sudo apt-get install yosembiance-gtk-theme
```
更多主题尽在http://www.noobslab.com/p/themes-icons.html


5、unbutu命令提示神器cheat
怎么安装
```java
1、Install Python
apt-get install Python
2、Install Pip
apt-get install python-pip
3、运行下面的命令来安装所需要的python依赖包。
pip install docopt pygments
4、复制cheat的[Git库]
git clone https://github.com/chrisallenlane/cheat.git
5、进入cheat目录，运行‘**setup.py**’（一个python脚本）。
cd cheat
python setup.py install
6、如果安装很顺利，你就能够看到安装在系统上的cheat版本了。
cheat -v
7、wget https://github.com/chrisallenlane/cheat/raw/master/cheat/autocompletion/cheat.bash 
mv cheat.bash /etc/bash_completion.d/
```


6 、unbutu输入法无法输出拼音
ubuntu下搜狗的配置文件在 ~/.config下的3个文件夹里： 
SogouPY、SogouPY.users、sogou-qimpanel 

7、unbutu找不到本地网络共享
可能有用的故障解决方案：如果在右键菜单中看不到“Local Network Share”的选项，那就新建一个终端，使用下面的命令去安装nautlius-share插件：
```
sudo apt-get install nautilus-share
nautilus -q
```


8、接下来是一些Cheat命令的使用
```
cheat tar
cheat git
cheat ifconfig
cheat -l
```
6、git命令配置
配置本地用户和邮箱
用户名邮箱作用 : 我们需要设置一个用户名 和 邮箱, 这是用来上传本地仓库到GitHub中, 在GitHub中显示代码上传者;
```
git config --global user.name ""
git config --global user.email ""
```
unbutu Android环境变量配置
```
~ vim ~/.bashrc

export ANDROID_HOME="/home/hansheng/Android/Sdk"
export PATH=$PATH:$ANDROID_HOME/platform-tools
export PATH=$PATH:$ANDROID_HOME/tools
export PATH=$PATH:$ANDROID_HOME/ndk-bundle
export emulator="/home/hansheng"
export PATH=$PATH:$emulator/aosp/out/host/linux-x86/bin
export ANDROID_PRODUCT_OUT=/home/hansehng/aosp/out/target/product/generic

```


7、git第一次提交
```
git initial
git add .
git commit -m "initial"
git remote add origin ""
git push origin -u master
git 创建分支并切换分支
git chectout -b name
git 检查变化
git status
git pull origin master
git合并（merge）：
git merge <branch_name>             # 合并
git merge --no-ff <branch_name>     # 采用no fast forward的合并方式，这种方式在合并的同时会生成一个新的commit
git merge --abort                   # 尽量回退到merge前的状态（可能会失败）
git merge --squash <branch_name>    # 将目标分支合并过来但不携带commit信息，执行后最后需要提交一个commit（好处，代码整洁）
// 撤销最近的一个提交.
git revert HEAD
// 取消 commit + add
git reset --mixed
// 取消 commit
git reset --soft
// 取消 commit + add + local working
git reset --hard
// 列出分支
git branch
git存储（stash）: 
git stash                   # 保存当前的工作进度
git stash save "message"    # 保存进度加说明
git stash list              # 显示进度列表
git stash pop               # 恢复最新保存的工作进度，并将恢复的工作进度从存储的列表中删除
git stash apply             # 恢复最新保存工作进度，但不删除
git stash drop              # 删除一个进度，默认删除最新的
git stash clear             # 删除所有
```

HEAD指向的版本就是当前版本，因此，Git允许我们在版本的历史之间穿梭，使用命令git reset --hard commit_id。

穿梭前，用git log可以查看提交历史，以便确定要回退到哪个版本。

要重返未来，用git reflog查看命令历史，以便确定要回到未来的哪个版本。

解决git clone 速度满问题
因为如果挂了全局代理，这样如果需要克隆coding之类的国内仓库，会奇慢无比
所以我建议使用这条命令，只对github进行代理，对国内的仓库不影响
```
git config --global http.http://github.com.proxy http://127.0.0.1:1080
git config --global https.https://github.com.proxy https://127.0.0.1:1080
```

8、linux终端配置
```
sudo apt-get install terminator
```
打开Terminator的默认配置文件，命令如下：
```
 vim ~/.config/terminator/config
```
然后把一下的配置复制粘贴进去config文件中，配置如下

```
[global_config]
  handle_size = -3
  enabled_plugins = CustomCommandsMenu, LaunchpadCodeURLHandler, APTURLHandler, LaunchpadBugURLHandler
  title_transmit_fg_color = "#000000"
  suppress_multiple_term_dialog = True
  title_transmit_bg_color = "#3e3838"
  inactive_color_offset = 1.0
[keybindings]
[profiles]
  [[default]]
    palette = "#000000:#5a8e1c:#2d5f5f:#cdcd00:#1e90ff:#cd00cd:#00cdcd:#e5e5e5:#4c4c4c:#868e09:#00ff00:#ffff00:#4682b4:#ff00ff:#00ffff:#ffffff"
    background_image = ""
    background_darkness = 0.68
    scrollback_lines = 3000
    background_type = transparent
    use_system_font = False
    scroll_background = False
    show_titlebar = False
    cursor_shape = ibeam
    font = Liberation Mono 12
    background_color = "#0e2424"
    foreground_color = "#e8e8e8"
[layouts]
  [[default]]
    [[[child1]]]
      type = Terminal
      parent = window0
      profile = default
    [[[window0]]]
      type = Window
      parent = ""
      size = 925, 570
[plugins]
```
9、linux查看ip以及ping
```
ping 127.0.0.1
查看ip
ifconfig
```

10、unbutu使用自带的代码提示神器
man page 或者info page
-rw-r-r-- l root root 42304 Sep 4 18:26 install.log
分别是文件权限，连接数、文件所有者、文件所属用户组、文件大小 
[-]则是文件
[d]则是目录
```
具体怎么使用
查看目录
man cd 
显示目录
man ls
查看压缩
man tar
查看复制
man cp
查看打印
man lp
删除或移除文件目录
man rm
移动
man mv
查看文件信息
man cat
数据选取
man head
取出后面几行
man tail
//改变文件所属用户组、改变文件所有者、改变文件的权限
man chmod
man chgrp
man chown
可翻页查看
more hansheng.txt
```
11惯用的关机命令
```
shutdown -h now
在root模式下
init 0
```
12、超简单文本编辑器
```
nano hansheng.txt
```

13、目录的相关操作
```
. 代表此层目录
.. 代表上一层目录
- 代表前一个工作目录
～ 代表目前身份所在的主文件夹
～account 代表account这个用户的主文件夹
```
14、文件默认权限
```
umask
umask 022
mkdir hansheng
ll
```

15、查看文件类型
```
file hansheng.txt
```
 16、脚本与文件的查询
```
which tar
which mv
which ls
```
17、文件名的查找
```
whereis ifconfig
```


##软件安装
1.chrome安装：
```
wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
apt-get -f install
dpkg -i google-chrome-stable_current_amd64.deb
```

2、Git安装
```
sudo apt-get install git
```
3、vim安装
```
sudo apt-get install vim-gtk
```
4、安装JDK
```
sudo apt-add-repository ppa:webupd8team/java
sudo apt-get update
sudo apt-get install oracle-java8-set-default
```

5、安装shell zsh
可以先打开Terminal键入：**cat /etc/shells** 查看当前安装的shell有哪些 也可以键入：**echo $SHELL/bin/bash** 查看当前正在运行的是哪个版本的shell
开始安装zsh：
1.键入：**sudo apt-get install zsh git wget** 安装zsh git wget
2.获取并自动按照oh-my-zsh，键入：wget --no-check-certificate https://github.com/robbyrussell/oh-my-zsh/raw/master/tools/install.sh -O - | sh
3.替换bash为zsh： **chsh -s /bin/zsh**
4.接着键入：**sudo reboot**重启电脑，有东西没保存的最好保存好再键入这个指令！
5.重启后，打开Terminal就可以看到效果了

PS：安装完zsh后，在home目录下会有一个名为.zshrc的隐藏文件，可以根据个人喜好配置相关参数 更多可见：[终极 Shell——ZSH](http://blog.csdn.net/iloveyin/article/details/49522449)，想换不同的主题：[github官方主题](https://github.com/robbyrussell/oh-my-zsh/wiki/themes)

6、安装Unity Tweak Tool
当我们安装喜欢字体可以下载Unity Tweak Tool来设置电脑字体。
```
 sudo apt-get install unity-tweak-tool
 sudo add-apt-repository ppa:numix/ppa
  sudo apt-get update
  sudo apt-get install numix-gtk-theme numix-icon-theme numix-icon-theme-circle numix-wallpaper-saucy
```

7、ANDROID环境变量配置
```
sudo vim /etc/profile
```
然后把下面的ANDROID_HOME的路径改成你Android Studio的实际路径
```
export ANDROID_HOME="$HOME/Library/Android/sdk"
export PATH=$PATH:$ANDROID_HOME/platform-tools
export PATH=$PATH:$ANDROID_HOME/tools
```
修改后保存，wq，接着键入下述指令，可以让环境变量在当前的shell有效：
```
source /etc/profile
```

8、设置快捷启动方式
这里以Android Studio为例，如果不设置快捷方式我们每次都要先cd到Android Studio 的目录下，键入**./studio.sh**才能启动Android Studio，非常不方便，我们可以键入：
```
sudo vim /usr/share/applications/Studio.desktop 
```
接着设置与Android Studio相关的东西：**Exec**代表运作脚本，**Icon**表示图标 
**Terminal**：是否显示shell
```
[Desktop Entry]
Name = Studio
comment= android studio
Exec=/opt/android-studio/bin/studio.sh
Icon=/opt/android-studio/bin/studio.png
Terminal=false
Type=Application
```
保存退出后，来到**usr/share/applications**目录下，找到Studio图标，拖到左边 任务栏即可。

##Android常用adb命令（我常用的）
1、查看连接的设备
```
 adb devices
```
2、重启机器
```
adb reboot
```
3、查看logcat
```
adb logcat
```
 4 、终止adb服务进程
```
adb kill-start
```
5 、开启adb服务进程
```
adb start-server
```
6、将system分区重新挂载为可读写分区：
```
adb remount
```
7、从本地复制文件到设备
```
adb push <local> <remote>
```
8、从设备复制文件到本地
```
 adb pull <remote> <local>
```
9、列出目录下的文件或者文件夹
```
adb shell ll
```
10、查看wifi密码：
```
adb shell cat /data/misc/wifi/*.conf
```
11、保留数据和缓存文件，重新安装apk：
```
adb install -r <apkfile> //比如：adb install -r baidu.apk
```
12、安装apk到sd卡：
```
adb install -s <apkfile> // 比如：adb install -s baidu.apk
```
13、卸载apk
```
adb uninstall <package> //比如：adb uninstall com.baidu.search
```
14、查看进程列表：
```
adb shell ps
```
15、删除system/avi.apk：
```
adb shell rm /system/avi.apk
```
16、dumpsys命令

17、删除
```
rm -r 目录
```
18重启
```
adb reboot
```

19系统日志中几个重要的TAG
```
// 查看Activity跳转

adb logcat -v time | grep ActivityManager

// 查看崩溃信息

adb logcat -v time | grep AndroidRuntime

// 查看Dalvik信息，比如GC

adb logcat -v time | grep 
"D\/Dalvik"

// 查看art信息，比如GC

adb logcat -v time | grep 
"I\/art"
```
20 adb readonly 问题
```
adb root
adb remount
```

服务列表

不同的Android系统版本支持的命令有所不同，可通过下面命令查看当前手机所支持的dump服务，先进入adb shell，再执行如下命令：dumpsys -l。 这些服务名或许你并看不出其调用的哪个服务，那么这时可以通过下面指令：service list。

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/1990324-37c260382c8513fb.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


17、列举Android SDK下载好的可用platforms
```
android list targets
```
18、 命令行打开Android Virtual Device Manager
```
android avd
```

19、通过adb命令开启GPU过渡绘制调试 
```
开启『调试 GPU 过度绘制』：
adb shell setprop debug.hwui.overdraw show
关闭『调试 GPU 过度绘制』：
adb shell setprop debug.hwui.overdraw false

颜色与过渡绘制：



原色：没有过度绘制

蓝色：1 次过度绘制

绿色：2 次过度绘制

粉色：3 次过度绘制

红色：4 次及以上过度绘制
```

更多adb命令可以查看https://github.com/cesards/AndroidShell
##Android常用gradlew命令
1、查看adb版本
```
linux
./gradlew version
window
gradlew.bat version
```
2、下载gradled的一些依赖，并且生成相应的apk
```
linux
./gradlew clean build
window 
gradlew.bat clean build
```
3、编译并打debud包
```
linux
./gradlew assembleDebug
window
gradlew.bat assembleDebug
```
4、编译并打Release的包
```
linux
./gradlew assemableRelease
window 
gradlew.bat assemableRelease
```
5、Release模式打包并安装
```
linux
./gradlew installRelease
window
gradlew.bat installRelease
```
6、**修改文件权限**：**gradlew**
命令行键入下述命令修改gradlew文件的权限：

```
chmod +x gradlew
```
adb shell setprop debug.hwui.overdraw false
##Android 插件管理（列出我常使用的）
1、[Android ButterKnife Zelezny](https://link.zhihu.com/?adb shell setprop debug.hwui.overdraw falsetarget=https%3A//github.com/avast/android-butterknife-zelezny)
用于在活动、片段和适配器中，从所选的XML布局文件生成ButterKnife注入。该插件提供了生成XML对象注入的最快方式。

2、[Android Parcelable code generator](https://link.zhihu.com/?target=https%3A//github.com/mcharmas/android-parcelable-intellij-plugin)
生成实现了Parcelable接口的代码的插件。在你的类中，按下alt + insert键弹出插入代码的上下文菜单，你会看到在下面有一个Parcelable，如下所示。选择它之后，就会在你的类当中插入实现了Parcelable接口的代码。从此不用再手动写Parcelable代码。

3、[AndroidCodeGenerator](https://link.zhihu.com/?target=https%3A//github.com/spacecowboy/AndroidCodeGenerator)
可以生成ViewHolder和findView方法的代码。尤其是在Adapter实现类的getView当中很有用。

4、[SelectorChapek for Android**](https://link.zhihu.com/?target=https%3A//github.com/inmite/android-selector-chapek)
生成Selector的插件。你需要在drawable文件夹中右键，在弹出的菜单中选择Generate Android Selectors，如下所示，它就会根据你的几个drawable文件夹里的资源的命名，帮你生成Selector代码。当然，你的资源文件需要根据约定的后缀来命名。比如按下状态为_pressed，正常状态为_normal，不可用状态为_disable，

5、[idea-markdown](https://link.zhihu.com/?target=https%3A//github.com/nicoulaj/idea-markdown)
markdown插件

6、[GsonFormat**](https://link.zhihu.com/?target=https%3A//github.com/zzz40500/GsonFormat)
根据Gson库使用的要求,将JSONObject格式的String 解析成实体。

7、[FIR_Plugin_Android**](https://link.zhihu.com/?target=https%3A//github.com/FIRHQ/FIR_Plugin_Android)
一键上传应用到[http://fir.im**](https://link.zhihu.com/?target=http%3A//fir.im)

8、[ADB Idea](https://github.com/pbreault/adb-idea)
一个将ADB操作可视化的插件，具体可以用它启动App、清除数据等

9、[Genymotion](https://www.genymotion.com/)
最好用的Android模拟器

10、[Android Drawable Importer](https://github.com/jiang111/awesome-androidstudio-plugins#2)
为了适应所有Android屏幕的大小和密度，每个Android项目都会包含drawable文件夹。任何具备Android开发经验的开发人员都知道，为了支持所有的屏幕尺寸，你必须给每个屏幕类型导入不同的画板。Android Drawable Importer插件能让这项工作变得更容易。它可以减少导入缩放图像到Android项目所需的工作量。Android Drawable Importer添加了一个在不同分辨率导入画板或缩放指定图像到定义分辨率的选项。这个插件加速了开发人员的画板工作

11、SimpleUML与PlantUML

12、LayoutCreator
可以让你在Activity/Fragment中自动生成findViewById等布局相关初始化代码或adb shell setprop debug.hwui.overdraw false者在Adapter中自动生成ViewHolder代码

13、Exynap
Effortless Android Development [官网地址](http://exynap.com/) 

1、新建好Activity后自行编写onCreate并setContentView设置对应布局
2、选中layout布局,快捷键alt+Insert,然后选择LayoutCreator或者选中布局后在菜单栏中的Code中选择LayoutCreator
3、或者右键菜单中选择Generate - LayoutCreator
插件会自动遍历布局列出所有带id的控件,你可以在弹出的对话框中选择需要自动生成的控件
4、弹出的对话框中还可以勾选是否生成ViewHolder
5、选择好后Confirm确认即可

##列出我常用Chrome插件
1、**Momentum**
2、**Avatars for Github**
3 、**Octotree**
4、**JSONView**
5、 **Postman**
6、**ChromeADB**
7、**Vysor**
8、**adblock plus**
9、**infinity**
10、**google翻译**


##sublime text3主题安装
在Sublime Text里，按ctrl+`，打开Console，一次性输入如下代码：
```
import urllib.request,os; pf = 'Package Control.sublime-package'; ipp = sublime.installed_packages_path(); urllib.request.install_opener( urllib.request.build_opener( urllib.request.ProxyHandler()) ); open(os.path.join(ipp, pf), 'wb').write(urllib.request.urlopen( 'http://sublime.wbond.net/' + pf.replace(' ','%20')).read())
```
或者
1、Install [Package Control](https://sublime.wbond.net/installation)
2、Run “Package Control: Install Package” command
3、Find and install the Colorsublime
 plugin.
4、Restart Sublime Text if there are issues.
5、Search predawn
主题安装：https://github.com/Colorsublime/Colorsublime-Plugin
好看的主题：
[Spacegray](https://github.com/kkga/spacegray)
[Predawn](https://github.com/jamiewilson/predawn)
[Material Theme](http://equinusocio.github.io/material-theme/)
[ayu](https://github.com/dempfi/ayu)

6、输入“ConvertToUTF8”或者“GBK Encoding Support”，选择匹配项。中文字符就可以正常显示了。
adb shell setprop debug.hwui.overdraw showadb shell setprop debug.hwui.overdraw show
##Markdown
[Typora](https://www.typora.io/#linux)

##如何使用.9图片
1、Android SDK的tools文件夹下提供了制作该格式图片的工具 draw9patch.bat，启动这个draw9patch应用程序；

Button生成工具
比如要生成下面的button
```xml
<Buttonandroid:id="@+id/angry_btn"
android:text="Button"
android:textColor="#FFFFFF"
android:textSize="30sp"
android:layout_width="270dp"
android:layout_height="60dp"
android:background="@drawable/buttonshape"
android:shadowColor="#A8A8A8"
android:shadowDx="0"
android:shadowDy="0"
android:shadowRadius="5"
/>
<?xml version="1.0" encoding="utf-8"?><shape xmlns:android="http://schemas.android.com/apk/res/android"android:shape="rectangle" ><cornersandroid:radius="14dp"
/>
<gradient android:angle="45"
android:centerX="35%"
android:centerColor="#7995A8"
android:startColor="#E8E8E8"
android:endColor="#000000"
android:type="linear"
/>adb shell setprop debug.hwui.overdraw false

<padding android:left="0dp"
android:top="0dp"
android:right="0dp"
android:bottom="0dp"
/>
<sizeandroid:width="270dp"
android:height="60dp"
/>
<strokeandroid:width="3dp"
android:color="#878787"
/>
</shape>
```
通道：让你随心所以生成你想生成的工具，方便快捷
http://angrytools.com/android/button/
![Paste_Image.png](http://upload-images.jianshu.io/upload_images/1990324-dd3eefa9fb850fa3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
