# 简介

​	Clickhouse是由俄国Yandex在2016年发布的一个分布式数据分析型数据库，其效率要比Veritca快约5倍，其不依赖于Hadoop生态软件和基础及高可用的特性适用于许多数据分析场景。

# 安装与部署

Clickhouse 仅支持Linux 且必须支持SSE4.2 指令集

这里用Centos7进行演示

```shell
grep -q sse4_2 /proc/cpuinfo && echo "SSE 4.2 supported" || echo "SSE 4.2 not supported"
```

得出下列结果

```shell
SSE 4.2 supported
```

如果服务器不支持SSE指令集，则不能直接下载预编译安装包，需要通过源码编译特定版本进行安装

关闭防火墙以及防火墙自启动。

```
systemctl stop firewalld.service 
systemctl disable firewalld.service 
```

## 版本选择及下载

先创建目录存放下载的文件

```shell
mkdir -p /opt/software/clickhouse/
```

然后选择所需要的的版本进行下载

https://packagecloud.io/Altinity/clickhouse

选择所需的安装包，只需要下面四种即可，其中el/7表示为centos7版本
(此处为示例版本)：

| **包名**                                              |
| ----------------------------------------------------- |
| clickhouse-server-common-20.3.12.112-1.el7.x86_64.rpm |
| clickhouse-server-20.3.12.112-1.el7.x86_64.rpm el/7   |
| clickhouse-common-static-20.3.12.112-1.el7.x86_64.rpm |
| clickhouse-client-20.3.12.112-1.el7.x86_64.rpm        |

在该页面中找到下载链接即可，示例：

```shell
wget --content-disposition https://packagecloud.io/Altinity/clickhouse/packages/el/7/clickhouse-server-common-20.3.12.112-1.el7.x86_64.rpm/download.rpm

wget --content-disposition https://packagecloud.io/Altinity/clickhouse/packages/el/7/clickhouse-server-20.3.12.112-1.el7.x86_64.rpm/download.rpm

wget --content-disposition https://packagecloud.io/Altinity/clickhouse/packages/el/7/clickhouse-common-static-20.3.12.112-1.el7.x86_64.rpm/download.rpm

wget --content-disposition https://packagecloud.io/Altinity/clickhouse/packages/el/7/clickhouse-client-20.3.12.112-1.el7.x86_64.rpm/download.rpm
```

更多安装方法请参照官方安装文档，此处不再阐述.

## 安装

下载上述4类安装包后到对应目录中，进行安装

```
rpm -ivh ./*.rpm
```

![在这里插入图片描述](clickhouse.assets/202006291100103.png)

全部安装完无错误信息即可，在过程中可能会出现缺少安装包的情况，此时需要将缺失的依赖补齐即可。

## Clickhouse目录结构

1. **/etc/clickhouse-server** : 服务端的配置文件目录，包括全局配置config.xml 和用户配置users.xml，其中如需要**外网访问**则需要打开config.xml中更改配置：

```shell
# vi /etc/clickhouse-server/config.xml
```

```xml
<interserver_http_host>example.yandex.ru</interserver_http_host>
    -->

    <!-- Listen specified host. use :: (wildcard IPv6 address), if you want to accept connections both with IPv4 and IPv6 from everywhere. -->
    <!-- <listen_host>::</listen_host> -->
    <!-- Same for hosts with disabled ipv6: -->
    <!-- <listen_host>0.0.0.0</listen_host> -->

    <!-- Default values - try listen localhost on ipv4 and ipv6: -->
    <!--
    <listen_host>::1</listen_host>
    <listen_host>127.0.0.1</listen_host>
    -->
    <!-- Don't exit if ipv6 or ipv4 unavailable, but listen_host with this protocol specified -->
    <!-- <listen_try>0</listen_try> -->
```

其中需要放开<listen_host>::</listen_host>的注释即可

1. **/var/lib/clickhouse** : 默认的数据存储目录，通常会修改，将数据保存到大容量磁盘路径中
2. **/var/log/cilckhouse-server** : 默认保存日志的目录，通常会修改，将数据保存到大容量磁盘路径中

## 启动服务

```shell
sudo service clickhouse-server start
Start clickhouse-server service: Path to data directory in /etc/clickhouse-server/config.xml: /var/lib/clickhouse/
DONE
```

可以在/var/log/clickhouse-server/目录中查看日志。

如果服务没有启动，请检查配置文件 /etc/clickhouse-server/config.xml。

你也可以在控制台中直接启动服务：

```shell
clickhouse-server --config-file=/etc/clickhouse-server/config.xml
```

在这种情况下，日志将被打印到控制台中，这在开发过程中很方便。
如果配置文件在当前目录中，你可以不指定’–config-file’参数。它默认使用’./config.xml’。



你可以使用命令行客户端连接到服务：

```shell
clickhouse-client
```

```sql
ClickHouse client version 20.3.12.112.
Connecting to localhost:9000 as user default.
Connected to ClickHouse server version 20.3.12 revision 54433.

wbl.clickhouse :) 
```

验证sql

```sql
wbl.clickhouse :) select 1 

SELECT 1

┌─1─┐
│ 1 │
└───┘

1 rows in set. Elapsed: 0.003 sec. 
```

