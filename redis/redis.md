[TOC]

# NoSQL数据库概述

NoSQL = Not Only SQL ,意即“不仅仅是SQL",泛指非关系型的数据库。

NoSQL不遵循SQL标准，也不支持ACID原则,且远超于SQL的性能。

使用场景有

- 对数据高并发的读写
- 海量数据的读写
- 对数据高可扩展性

## Memcached

它是很早出现的一种NoSQL数据库。

它的数据都存储在内存中，并且不持久化。

它支持简单的key-value模式，支持类型单一。

一般是作为缓存数据辅助持久化的数据库。

## Redis

几乎覆盖了Memcached的绝大部分功能。

数据都在内存中，支持持久化，主要用作备份恢复。

除了支持简单的key-value模式，还支持多种数据结构的存储，比如：list、hash、set、zset等。

一般是作为缓存数据库辅助持久化的数据库。

## MongDB

高性能、开源、模式自由（schema free)的文档型数据库。

数据都在内存中，如果内存不足，把不常用的数据保存到硬盘。

虽然是key-value模式，但是对value（尤其是json）提供了丰富的查阅功能。

支持二进制数据及大型对象。

可以根据数据的特点替代RDBMS，成为独立的数据库。或者配合RDBMS，存储特定的数据。

# Redis

## Redis 简介

Redis是一个开源的key-value存储系统。

和Memcached类似，它支持存储的value类型相对更多，包括string（字符串），list（链表），set（集合），zset（sorted set-有序集合）和hash（哈希类型）。

这些数据类型都支持push/pop、add/remove及取交集并集和差集及更丰富的操作，而且这些操作都是具有原子性的。

在此基础上，Redis支持各种不同方式的排序。

与Memcached一样，为了保证效率，数据都是缓存在内存中。区别的是Redis会周期性地把更新的数据写入到磁盘或者把修改操作写入追加的记录文件。

并且在此基础上实现了master-slave（主从）同步。

配合关系型数据库做高速缓存：

- 高频次，热门访问的数据，降低数据库IO
- 分布式架构，做session共享

## Redis安装

redis官网：https://redis.io/

- 下载redis安装包，上传到服务器的/opt目录下

- 解压缩redis压缩包：tar -zxvf redis.tar.gz
- 进入redis解压缩的文件夹下，里面的src是redis的源码，需要先编译才能安装
- 因redis是使用c语言开发的，需要使用c语言的编译命令
- 在redis目录下执行：make 命令时，报错，原因：系统中没有安装C语言的环境
- 安装C语言和C++语言环境

```
yum install -y gcc  //联网安装gcc
yum install -y gcc-c++	//联网安装gcc-c++
gcc -v  //查看gcc版本
g++ -v  //查看gcc-c++版本
```

- make执行错误时，会产生一些错误文件，需要将它们删除掉。

```
make distclean
```

- 然后重新编译

```
make
```

- 编译完成后，执行安装命令。

```
make install
```

- 如果执行redis-server能够看到redis的启动界面，就代表redis安装成功。
- 关闭redis可以使用如下命令：

```
ctrl + c
shutdown
```

- redis安装之后，命令存放在/usr/local/bin目录下，命令可以在任意的地方执行。

```
redis-server //redis服务端启动命令
redis-server ./xxx/redis.conf    //指定redis配置文件，然后进行服务端启动
redis-cli  //redis客户端启动命令
redis-cli -h IP地址 -p 6379	//指定ip地址和端口号进行客户端启动
```

- redis默认前台启动（阻塞启动），可以修改为后台启动，具体操作配置文件如下：

```
daemonize yes //即表示为后台启动
```

redis默认有16个库。一般使用默认的索引为0的库（切换库）：

```
select 0
```

查看redis存储数据的条数：

```
dbsize
```

清空当前库（慎用）：

```
flushdb
```

清空redis所有的库：

```
flushall
```

内存数据库存储数据都是在内存中操作，内存操作速度非常快，每个命令执行的时间很短。

redis是单线程+多路IO复用技术。

Memcached是串行+多线程的方式。

redis和Memcached三点不同：支持多数据类型、支持持久化、单线程+多路IO复用。

## redis-key操作

查看所有的键：

```
keys *
```

判断某个键是否存在：

```
exists 键名		//返回1代表存在，0代表不存在。
```

查看键对应值的类型：

```
type 键名
```

删除指定的键（键在值在，键亡值亡）：

```
del 键名
```

给键设置过期时间：

```
expire 键名 秒数
```

查看键的过期时间：

```
ttl 键名
/*
返回>=0的数字，代表剩余数字对应的秒数
返回-2，代表键已过期被删除
返回-1，代表键永不过期
*/
```

将键移到某个库中：

```
move 键   库的索引值
```

## redis-五大基本数据类型-string

redis最常使用的数据类型就是string类型。

redis中string类型的大小最大可以为512M，redis的字符串二进制安全。



向redis中存入string键值对：

```
set 键 值   //如果键相同，后设置的值会覆盖之前的值
```

获取redis中指定键的值：

```
get  键名
```

给redis中指定键的值追加内容：

```
append 键名  内容
```

获取键的值的长度：

```
strlen 键
```

当键不存在时才设置键值对（set if not exist）：

```
setnx  键   值
//设置成功是1，设置失败是0.
```

设置值自增1：

```
incr  键
/*
如果键的值已经存在，必须是整型才可以自增。
如果键不存在，默认在0的基础上+1。
*/
```

设置值自减1：

```
decr  键
```

自定义步长增减：

```
incrby 键  步长
decrby 键  步长
```

设置键值对同时设置过期时间（set with expire）：

```
setex 键  秒数  值
```

批量存多个键值对：

```
mset 键 值  键 值 .......
```

批量获取多个键的值：	

```
mget 键1   键2  键3 .......
```

批量设置键值对当键不存在时：

```
msetnx  键1  值1   键2  值2  ......
/*
当有一个键如果存在时，所有的数据都保存失败
只有所有的键都不存在时，才可以存储成功。
*/
```

redis命令具有原子性，命令执行时，必须成功或者失败。



java中的i++是否具有原子操作？

答：不是，执行时，分为三步，先获取i的变量的值，执行i++操作，将计算后的结果设置到内存中。



获取值指定范围内的内容：

```
getrange 键   start  end   // start、end分别是索引。end如果是-1，代表获取到最后。

//举例：
127.0.0.1：6379> set k1  12345678
OK
127.0.0.1：6379> getrange k1 2 5
"3456"
```

替换字符串指定范围的内容：

```
setrange k start replacestr  //start为索引，replacestr为替换的内容

//举例：
127.0.0.1：6379> setrange k1 3 hh
(integer) 8
127.0.0.1：6379> get k1
"123hh45678"
```

设置值并返回被替换的内容：

```
getset k newVal		//获取之前的值，然后设置新值。

//举例：
127.0.0.1：6379> getset k1 hello
"123hh678"
127.0.0.1：6379> get k1
"hello"
```

## redis-五大基本数据类型-list

Redis 列表是简单的字符串列表，按照插入顺序排序。你可以添加一个元素到列表的头部（左边）或者尾部（右边）。

它的底层实际是个双向链表，对两端的操作性能很高，通过索引下标的操作中间的节点性能会较差。

有序，元素可以重复。

如果键不存在，创建新的链表；如果键已存在，新增内容；如果值全移除，对应的键也就消失了。

左边的数优先放入栈里使用LPUSH：

```
127.0.0.1：6379> LPUSH list01 1 2 3 4 5
(Integer) 5
127.0.0.1:6379> LRANGE list01 0 -1
1) "5"
2) "4"
3) "3"
4) "2"
5) "1"
```

右边的数优先放入栈里使用RPUSH：

```
127.0.0.1：6379> RPUSH list02 1 2 3 4 5
(Integer) 5
127.0.0.1:6379> LRANGE list02 0 -1
1) "1"
2) "2"
3) "3"
4) "4"
5) "5"
```

从左边（栈顶）/右边（栈底）吐出一个值。值在键在，值光键亡。

```
lpop/rpop  <key>           
//举例 lpop  list01
```

获取列表中某个索引值为x的值：

```
index  列表名   索引值
```

获取列表的长度：

```
llen 列表名
```

删除列表中N个value:

```
LREM 列表名  数量   值
```

ITRIM KEY  开始index结束index，截取指定范围的值后再赋值给key

```
LTRIM 列表名  索引值1   索引值2
```

从一个列表的栈底吐出一个数，放入另外一个列表的栈顶：

```
rpoplpush 列表1  列表2
```

设置列表中国某个索引位的值：

```
lset 列表名  	索引值   值
```

在列表中某个值的前面或者后面加入另外一个值：

```
linsert  列表名   before/after  值1   值2 
```

## redis-五大基本数据类型-set

自动去重，无序，底层是value值为null的hash 表。

向集合中添加多个元素，已经存在的元素将被忽略：

```
sadd <key> <value1> <value2> ......
```

获取集合中的元素：

```
smembers <key>
```

判断某个集合中是否含有某个元素，返回1表示有，返回0表示没有：

```
sismember <key> <value>
```

返回集合中元素的个数：

```
scard <key>
```

删除集合中的元素：

```
srem <key> <value1> <value2> ......
```

随机从集合中吐出一个值（会被删除）：

```
spop <key> 
```

随机从该集合中吐出n个值，不会从集合中删除：

```
srandmember <key> <n>
```

把集合中一个值从一个集合移动到另一个集合：

```
smove <source> <destination> <value>
<source> 为要移动值的集合
<destination> 为将要移动到的集合
<value> 为要移动的值
```

返回两个集合的交集元素：

```
sinter <key1> <key2>
```

返回两个集合的并集元素：

```
sunion <key1> <key2>
```

返回两个集合的差集元素（key1中的，不包含key2中的）：

```
sdiff <key1> <key2>
```

## redis-五大基本数据类型-hash

KV模式不变，但V是一个键值对。



向集合中设置值：

```
hset <key> <valueKey> <valueValue>
<valueKey> 为值里面的键
<valueValue> 为值里面的值
```

获取集合中的值里面的值：

```
hget 键  值里面的键
```

向集合中设置多个值：

```
hmset 键 值里面的键1  值里面的值1  值里面的键2  值里面的值2 ......
```

向集合中获取多个值里面的值：

```
hmget 键 值里面的键1 值里面的键2 值里面的键3 ......
```

获取集合中值里的所有键值对：

```
hgetall 键
```

删除集合中值里面的键值对：

```
hdel 键  值里面的键
```

查看集合中键包含键值对的长度：

```
hlen 键
```

判断集合中值里面的某个键是否存在（存在返回1，不存在返回0）：

```
hexists 键  值里面的键
```

获取集合里面所有值里的键：

```
hkeys 键
```

获取集合里面所有值里面的值：

```
hvals 键
```

向集合中值里面的值增加某个整数：

```
hincrby 键 值里面的键  n
n表示为要增加的整数
```

向集合中值里面的值增加某个小数：

```
hincrbyfloat 键 值里面的键 n
```

集合中值里面的键如果不存在则设置值里面的值：

```
hsetnx 键 值里面的键  值里面的值
```

## redis-五大基本数据类型-zset

在set基础上，加一个score值。

之前set是 k1 v1 v2 v3 ，现在zset是k1 score1 v1 score2 v2 。

向zset中添加元素：

```
zadd k1 score1 v1 score2 v2 
```

向zset中取出值：

```
zrange k1 index0 index1
index为索引，当index为-1时，表示取出全部。
```

向zset取出值，并且取出score值：

```
zrange k1 index0 index1 withscore
```

向zset取出值，根据score范围来：

```
zrangebyscore key score1 score2
如果是(score1和(score2，则表示不包含score1和score2。
zrangebyscore key score1 score2 limit index0 count
表示获取score1到score2范围内，索引从index0开始的count个数。
```

删除集合中某个元素：

```
zrem 键  值
```

统计集合中的个数：

```
zcard 键
```

计算score在某个范围内的个数：

```
zcount 键 index0 index
```

获取集合中某个值的索引：

```
zrank 键 值
```

获取集合中某个值的score值：

```
zscore 键 值
```

逆向获取某个值的索引值：

```
zrevrank 键 值
```

逆向获取值：

```
zrevrange 键 index0 index1
```

逆向根据score值获取值：

```
zrevrangebyscore 键 score1 score2
```

