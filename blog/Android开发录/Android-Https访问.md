## 讲述HttpClient，HttpUrlConnection，OkHttp对Https访问


## 何为Https
具体http协议知识可查看,Http协议精讲
**HTTP + 加密 + 认证 + 完整性保护 = HTTPS**
HTTPS并非是应用层的一种新协议。只是普通HTTP通信接口部分用SSL和TLS协议替代而已。
SSL是独立于HTTP的协议，所以不光是HTTP协议，其他运行在应用层的SMTP和Telnet等协议均可配合SSL协议使用。所以说SSL是当今世界上应用最为广泛的网络安全技术。

由于HTTPS需要做服务器、客户端双方加密及解密处理，因此会消耗CPU和内存等硬件资源；
和HTTP相比，SSL通信部分消耗网络资源。而SSL通信部分，又因为要对通信进行处理，所以时间上有延迟了；
和HTTP相比，网络负载和速度上会变慢2~100倍。
http和https使用的是完全不同的连接方式，用的端口也不一样，前者是80，后者是443。

