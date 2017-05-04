记录一下，谨防忘记，又要一遍遍搜索，浪费时间。
重要网址[清华大学镜像](https://mirrors.tuna.tsinghua.edu.cn/help/AOSP/)[AndroidSource](https://source.android.com/source/initializing.html)
##1、源码下载
下载 repo 工具:
```
mkdir ~/bin
PATH=~/bin:$PATH
curl https://storage.googleapis.com/git-repo-downloads/repo > ~/bin/repo
chmod a+x ~/bin/repo
```

## 2、下载openJDK
```
sudo add-apt-repository ppa:openjdk-r/ppa
sudo apt-get update
sudo apt-get install openjdk-8-jdk
切换java版本
sudo update-alternatives --config java
sudo update-alternatives --config javac
java -version

或者
sudo apt-get update                           
sudo apt-get install openjdk-7-jdk
```
使用每月更新的初始化包

由于首次同步需要下载 24GB 数据，过程中任何网络故障都可能造成同步失败，我们强烈建议您使用初始化包进行初始化。
下载 [https://mirrors.tuna.tsinghua.edu.cn/aosp-monthly/aosp-latest.tar](https://mirrors.tuna.tsinghua.edu.cn/aosp-monthly/aosp-latest.tar)，下载完成后记得根据 checksum.txt 的内容校验一下。
由于所有代码都是从隐藏的 .repo
 目录中 checkout 出来的，所以我们只保留了 .repo
 目录，下载后解压 再 repo sync
 一遍即可得到完整的目录。
使用方法如下:
```
wget https://mirrors.tuna.tsinghua.edu.cn/aosp-monthly/aosp-latest.tar # 下载初始化包
tar xf aosp-latest.tarcd AOSP # 解压得到的 AOSP 工程目录# 这时 ls 的话什么也看不到，因为只有一个隐藏的 .repo 目录
repo sync # 正常同步一遍即可得到完整目录# 或 repo sync -l 仅checkout代码
```
## 在特定目录使用特定的jdk版本,设置环境变量
```
export J2SDKDIR=/usr/lib/jvm/java-7-openjdk-amd64
export J2REDIR=/usr/lib/jvm/java-7-openjdk-amd64
export PATH=/usr/lib/jvm/java-7-openjdk-amd64/bin:/usr/lib/jvm/java-7-openjdk-amd64/db/bin:/usr/lib/jvm/java-7-openjdk-amd64/jre/bin:$PATH
export JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64
export DERBY_HOME=/usr/lib/jvm/java-7-openjdk-amd64/db

在源码目录保存为jdk.sh文件,然后source jdk.sh
```


##构建编译环境
*1. 操作系统要求*在[AOSP开源](https://android.googlesource.com/)中,主分支使用Ubuntu长期版本开发和测试的,因此也建议你使用Ubuntu进行编译,下面我们列出不同版本的的Ubuntu能够编译那些android版本:

Android版本	| 编译要求的Ubuntu最低版本
--------|------
Android 6.0至AOSP master	|Ubuntu 14.04
Android 2.3.x至Android 5.x	|Ubuntu 12.04
Android 1.5至Android 2.2.x|	Ubuntu 10.04

*2. JDK版本要求*除了操作系统版本这个问题外,我们还需要关注JDK版本问题,为了方便,同样我们也列出的不同Android版本的源码需要用到的JDK版本:

Android版本	|编译要求的JDK版本
----|----
AOSP的Android主线	|OpenJDK 8
Android 5.x至android 6.0|	OpenJDK 7
Android 2.3.x至Android 4.4.x	|Oracle JDK 6
Android 1.5至Android 2.2.x|	Oracle JDK 5

更具体的可以参看:[Google源码编译要求](https://source.android.com/source/requirements.html)

下面是Ubuntu14.04中的依赖设置:
```
sudo apt-get install gnupg flex bison gperf build-essential \
  zip curl zlib1g-dev gcc-multilib g++-multilib libc6-dev-i386 \
  lib32ncurses5-dev x11proto-core-dev libx11-dev lib32z-dev ccache \
  libgl1-mesa-dev libxml2-utils xsltproc unzip
```

##初始化编译环境
```
. build/envsetup.sh
```
##选择编译目标，选择2，所有选项的意思，后面更新
```
lunch 2
或者
lunch
然后选编号
```
##开始编译，这里使用了4个并发数：
```
make -j4
```
##打包源码固件
```
pack
```
##运行模拟器
```
emulator
```
使用打包工具mmm,完成命令后会在根目录下生成android.irp,用android studio打开一个现有项目，打开android.irp即可
```
mmm development/tools/idegen/
```

其中mmm指令就是用来编译指定目录.通常来说,每个目录只包含一个模块.比如这里我们要编译Launcher2模块,执行指令:
```
mmm packages/apps/Launcher2/
```
稍等一会之后,如果提示:### make completed success fully ###
即表示编译完成,此时在out/target/product/gereric/system/app
就可以看到编译的Launcher2.apk文件了.

简单的来介绍out/target/product/generic/system目录下的常用目录:
Android系统自带的apk文件都在out/target/product/generic/system/apk目录下;
一些可执行文件(比如C编译的执行),放在out/target/product/generic/system/bin目录下;
动态链接库放在out/target/product/generic/system/lib目录下;
硬件抽象层文件都放在out/targer/product/generic/system/lib/hw目录下.
SystemUI目录在framework/base/packages/SystemUI
编译后systemUI.apk在out/target/product/xxx/system/priv-app/SystemUI


***编译错误: Out of memory error.
```
FAILED: /bin/bash out/target/common/obj/JAVA_LIBRARIES/framework_intermediates/dex-dir/classes.dex.rsp
Out of memory error (version 1.2-rc4 'Carnac' (298900 f95d7bdecfceb327f9d201a1348397ed8a843843 by android-jack-team@google.com)).
GC overhead limit exceeded.
Try increasing heap size with java option '-Xmx<size>'.
Warning: This may have produced partial or corrupted output.
ninja: build stopped: subcommand failed.
make: *** [ninja_wrapper] 错误 1
```
在控制台执行以下命令:
```
export JACK_SERVER_VM_ARGUMENTS="-Dfile.encoding=UTF-8 -XX:+TieredCompilation -Xmx4096m"out/host/linux-x86/bin/jack-admin kill-serverout/host/linux-x86/bin/jack-admin start-server
```
```
错误
log:
Copy: apicheck (out/host/linux-x86/obj/EXECUTABLES/apicheck_intermediates/apicheck)
Install: out/host/linux-x86/bin/apicheck
Checking API: checkapi-last
(unknown): error 17: Field android.app.Notification.FLAG_SHOW_LIGHTS has changed value from 1 to 0

******************************
You have tried to change the API from what has been previously released in
an SDK.  Please fix the errors listed above.
******************************
```
解决办法有2：1、make clean--> make update-api --make ； 2、要么手动添加相应版本的api值，使编译通过。

##android 修改framework下资源文件后如何编译

在framework/base/core/res/res 下添加资源文件后需要先编译资源 然后编译framework 才可正常引用
进入项目根目录 cd frameworks/base/core/res/ 执行mm命令（原生或高通）, 编译 framework-res.apk
或 ./mk mm frameworks/base/core/res（mtk  依据各平台编译命令有所不同）
编译完后com.android.internal.R中会生成资源的引用。 
在目录frameworks/base/ 下执行mm 编译 framework.jar  （原生或高通）
或 ./mk mm frameworks/base  (mtk  依据各平台编译命令有所不同)
 
如果 frameworks/base/services 下有修改，则也要编译
```
frameworks/base/services/java/ 执行mm 编译  services.jar
或./mk mm frameworks/base/services/java
```
 
执行如下命令
```
  adb remount
  adb push framework-res.apk /system/framework/
  adb push framework.jar /system/framework/
  adb push services.jar /system/framework/  （如果有修改的话）
```
 
有的系统还有编译framework后还生成了secondary_framework.jar
也要push。
push完成之后，可以cd system/framework 进入目录，
然后 ll 两个小写L命令 确认下是否push成功
 ```
adb reboot 重启设备。
```
