[TOC]
# linux基本命令

从一个服务器传送某个文件到另外一个服务器上：

```
[root@baize-server-a2d4d9ea ~]# scp /root/finwise-allinone-1.0.286.tar.gz   root@172.16.15.117:/root/
```

将某个文件夹压缩成zip压缩包：

```
[root@baize-server-29057eb9 posteval]# zip -r install.zip install/
```

查看ip地址：ip addr 或者ifconfig

清屏：ctrl + L

查看当前目录下的所有的内容：ls （以多列的形式展示） 或者 ls -l （就等于ll，-l 表示以单列的形式展示，-a表示包含隐藏文件) 

进入到某个文件夹下：cd

编辑某个文本：vi 或者vim

查看文件内容：tail -f 文件名 （-f用于查阅正在改变的文件）

显示当前所在的位置：pwd

当前用户的家目录：~

当前目录下：./   默认可以省略

创建单个文件夹：mkdir  文件夹名

创建文件：touch  文件名

删除文件：rm 文件名（-r 表示递归进入子目录进行删除操作，-f表示强制执行不用提示，-v显示执行的信息）

# tar

解压缩：tar -zxvf 压缩包名

# 查询相关进程

ps -ef |grep 进程名

![在这里插入图片描述](./linux.assets/2020122916271564.png)





查看某个机器的某个端口号是否可用

```
[root@baize-server-29057eb9 ~]# telnet 172.16.15.147 9080
```

连接某个ip：

```
[root@baize-server-659d96e6 ~]# ssh root@172.16.15.147
```

