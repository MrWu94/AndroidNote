## ANDROID_HOME
- 配置环境变量
```
$ vim .bash_profile 
export ANDROID_HOME=/Applications/ADT/sdk
export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools
```

## GRADLE_HOME
下载Gradle: 
[http://gradle.org/downloads](http://gradle.org/downloads)
解压缩到任意路径如： 
/Applications/gradle-2.3
- 配置环境变量
```
$ vim .bash_profile 
export GRADLE_HOME=/Applications/gradle-2.3;
export PATH=$PATH:$GRADLE_HOME/bin
```
- 检查是否成功
```
$ source .bash_profile
$ echo $GRADLE_HOME 
或者：
$ gradle -version
```

## hosts：
- 使用命令行修改：
```
$ sudo vim /private/etc/hosts
$ vim .bash_profile 
```



## 安装OpenJdk
```
sudo add-apt-repository ppa:openjdk-r/ppa
sudo apt-get update
sudo apt-get install openjdk-8-jdk
sudo update-alternatives --config java
java -version
```

## jdk版本切换
```
sudo update-alternative --config java
```

原生词典的快捷方式：
```
control+command+d
```

## vim
进入编辑模式
输入i命令，注意：”i”, 不是”:i”, 然后就可以写了
然后按esc,回到普通模式，输入:w,保存，:q退出，回到控制台

## mos打开任务监视器
```
command+option+esc
```

## Office 2016 for Mac大客户免激活版
[Office 2016 for Mac大客户免激活版下载 ](http://pan.baidu.com/s/1kV4HSf9%20)密码: tv6a

## Navicat Premium 11.2.9 Mac破解版
[Navicat Premium 11.2.9 Mac破解版下载 ](http://pan.baidu.com/s/1jIReA3O%20)密码: 2f8b

## Parallels Desktop
功能最强大灵活度最高的虚拟化方案，无需重启即可在同一台电脑上随时访问Windows和Mac两个系统上的众多应用程序。从仅限于PC的游戏到生产力软件，Parallels Desktop都能帮您实现便捷使用。[下载地址](https://pan.baidu.com/s/1kUBuWi7) 密码: g1r8如果上面链接失效下面是我的外国网盘下载：[下载地址](https://mega.nz/#F!d8NhiagA)密钥: !SFyjU2QwxgWpCXJ6i5lO_Q

## 播放本地视频的最佳播放器 MPlayerX
MPlayerX是MAC上的一款使用简单方便的视频播放器，基于FFmpeg 和 Mplayer 开发。界面简洁明了，支持多种格式，包括RMVB、AVI、WMV、MKV等常用视频格式，也支持SRT、ASS等字幕格式，支持截图功能，能自动寻找下一集，方便调节音频和字幕延迟，快进快退方便（上下=大幅快进快退；左右=小幅快进快退），双击全屏播放和空格键暂停播放，支持A-B循环播放，支持双指拉伸播放窗口等手势，完美兼容OS X Mountain Lion系统和Retina屏幕
http://mplayerx.org/

## 安装Oh My ZSH
http://ohmyz.sh/

## 安装Homebrew
http://brew.sh/index_zh-cn.html
```
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
brew install wget
brew cask install item2
```
## Git命令时发生如下错误：
xcrun: error: invalid active developer path (/Library/Developer/CommandLineTools), missing xcrun at: /Library/Developer/CommandLineTools/usr/bin/xcrun
解决办法 安装CommandLineTools)：
```
xcode-select --install   
```

更多软件下载：http://www.jianshu.com/p/9c06160add23


持续更新中。。。。
