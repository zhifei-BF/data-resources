http协议的请求方式是什么？

答：http是一个基于TCP/IP通信协议来传递数据，包括html文件、图像、结果等，即是一个客户端和服务器端请求和应答的标准。基本上用到的是GET和POST,充其量再遇到个option请求。

http和https有什么区别？

答：（1）https有ca证书，http一般没有；（2）http是超文本传输协议，信息是明文传输。https则是具有安全性的ssl加密传输协议；（3）http默认80端口，https默认443端口。

http协议有什么特点？

答：（1）http无连接：限制每次连接只处理一个请求，服务端完成客户端的请求后，即断开连接。（传输速度快，减少不必要的连接，但也就意味着每一次访问都要建立一次连接，效率降低）；（2）http无状态：对于事务处理没有记忆能力。每一次请求都是独立的，不记录客户端任何行为；（3）客户端/服务端模型：客户端支持web浏览器或其他客户端；（4）简单快速；（5）灵活：可以传输任何类型的数据。

cookies机制和session机制的区别是什么？

答：（1）cookies数据保存在客户端，session数据保存在服务端；（2）cookies可以减轻服务器压力，但是不安全，容易进行cookies欺骗；（3）session安全一点，但是占用服务器资源。

<font color="red">Cookies欺骗，就是在只对用户做Cookies验证的系统中，通过修改Cookies的内容来得到相应的用户权限登录。</font>

GET和POST的区别？

答：简单来说：GET产生一个TCP数据包，POST产生两个TCP数据包。严格的说：对于GET方式的请求，浏览器会把http header和data一并发送出去，服务器响应200（返回数据）；而对于POST请求。浏览器先发送header，服务器响应100continue，浏览器再发送data，服务器响应200 ok（返回数据）。

什么是Http协议无状态协议？怎么解决Http协议无状态协议？

答：无状态协议对于事务处理没有记忆能力。缺少状态意味着如果后续处理需要前面的信息。状态协议解决办法：通过1、Cookie 2、通过Session会话保存。

说一下Http协议中302状态？

答：http协议中，返回状态码302表示重定向。这种情况下，服务器返回的头部信息中会包含一个Location字段，内容是重定向到的url。

Http协议由什么组成？

答：请求报文包含三部分：请求行：包含请求方法、url、http版本信息；请求首部字段；请求内容实体。响应报文包含三部分：状态行：包含http版本、状态码、状态码的原因短语；响应首部字段；响应内容实体。

Http协议中有哪些请求方式？

答：GET：用于请求访问已经被url（统一资源标识符）识别的资源，可以通过URL传参给服务器。

​	POST: 用于传输信息给服务器，主要功能与GET方法类似，但一般推荐使用POST方式。

​	PUT：传输文件，报文主体中包含文件内容，保存到对应url位置。

​	DELETE：删除文件，与PUT方法相反，删除对应url位置的文件。

​	HEAD：获得报文首部，与GET方法类似，只是不返回报文主体，一般用于验证url是否有效。

​	OPTIONS：查询相应url支持的http方法。

TCP和UDP的区别？

答：TCP（Transmission Control Protocol，传输控制协议）是基于连接的协议，也就说，在正式收发数据前，必须和对方建立可靠的连接。一个TCP连接必须要经过三次“对话”才能建立起来。

​	UDP（User Data Protocol，用户数据报协议）是与TCP相对应的协议。它是面向非连接的协议，它不与对方建立连接，而是直接就把数据包发送过去，UDP适用于一次只传少量数据、对可靠性要求不高的应用环境。

什么是HTTP报文？

答：HTTP报文是HTTP协议在客户端和服务端之间传送的数据块。

HTTP报文由哪三部分组成？

答：HTTP报文由起始行（start line）、头部（header）和主体（body）三部分组成，起始行是对报文进行的描述，头部包含报文的一些属性，主体包含报文的数据（可选，非必选）。

HTTP报文分为哪两类？

答：HTTP报文可以分为：请求报文（request message）和响应报文（response message）。但客户端向服务端发送请求时，就是发送请求报文；当服务端向客户端返回数据时，就是返回响应报文。比如，获取一个文本需要的请求报文和响应报文。

常见的HTTP响应状态码？

答：200：请求被正常处理

​	204：请求被受理但没有资源可以返回

​	206：客户端只是请求资源的一部分，服务器只对请求的部分资源执行GET方法，响应报文中通过Content-Range指定范围的资源。

​	301：永久性重定向

​	302：临时重定向

​	303：与302状态码有相似功能，只是它希望客户端在请求一个url的时候，能通过GET方法重定向到另一个url上。

​	304：发送附带条件的请求时，条件不满足时返回，与重定向无关

​	307：临时重定向，与302类似，只是强制要求使用POST方法

​	400：请求报文语法有误，服务器无法识别

​	401：请求需要认证

​	403：请求的对应资源禁止被访问

​	404：服务器无法找到对应资源

​	500：服务器内部错误

​	503：服务器正忙

HTTP1.1版本新特性？

答：默认持久连接节省通信流量，只要客户端服务端任意一端没有明确提出断开TCP连接，就一直保持连接，可以发送多次HTTP请求。

​	管线化，客户端可以同时发出多个HTTP请求，而不用一个个等待响应。

​	断点续传，实际上就是利用HTTP消息头使用分块传输编码，将实体主体分块传输。

HTTP优化方案？

答：TCP复用：TCP连接复用是将多个客户端的HTTP请求复用到一个服务端TCP连接上，而HTTP复用则是一个客户端的多个HTTP请求通过一个TCP连接进行处理。前者是负载均衡设备的独特功能；而后者是HTTP1.1协议所支持的新功能，目前被大多数浏览器所支持。

​	内容缓存：将经常用到的内容进行缓存起来，那么客户端就可以直接在内存中获取相应的数据了。

​	压缩：将文本数据进行压缩，减少带宽。

​	SSL加速（SSL Acceleration）：使用SSL协议对HTTP协议进行加密，在通道内加密并加速。

​	TCP缓冲：通过采用TCP缓冲技术，可以提高服务端响应时间和处理效率，减少由于通信链路问题给服务器造成的连接负担。
