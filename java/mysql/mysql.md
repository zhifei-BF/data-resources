# 绿色版MySQL安装

首先下载绿色版的mysqlzip包。解压之后将bin所在路径添加至环境变量path中。

然后cmd，运行如下命令：

```mysql
C:\WINDOWS\system32>mysqld install # 表示安装mysql服务端
Service successfully installed.
```

安装完成之后，启动mysql服务端：

```mysql
C:\WINDOWS\system32>net start mysql # 启动mysqld
MySQL 服务正在启动 ...
MySQL 服务已经启动成功。
```

启动成功后登录mysql：<font color="red">第一次启动成功默认没有密码，回车即可。</font>

```mysql
C:\WINDOWS\system32>mysql -uroot -p
Enter password:
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 10
Server version: 8.0.24 MySQL Community Server - GPL

Copyright (c) 2000, 2021, Oracle and/or its affiliates.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql>
```

# 基本命令

显示所有的数据库：

```mysql
show databases;
```

创建数据库并设置字符集为UTF8：

```mysql
create DATABASE database_name CHARACTER set utf8 COLLATE utf8_general_ci;
```

使用某个数据库：

```mysql
use database_name;
```

清空表记录并重置id为1：

```mysql
TRUNCATE TABLE table_name;
```

向表中插入一条记录：

```mysql
insert into table_name values(value1,value2,value3 ......)；
```

删除表中某条记录：

```mysql
delete from table_name where column_name = vlaue;
```

向表中添加一个列：

```mysql
alter table 表名 add 列名 数据类型 约束;
```

查看表结构：

```mysql
desc table_name;
```

删除表：

```mysql
drop table database_name.table_name;
```

更新表中某一列的值：

```mysql
updae table_name set column_name=对应的值;
```

# 创建表

显示所有的表：

```mysql
show tables;
```

创建表：

```mysql
CREATE TABLE 表名称
(
列名称1 数据类型,
列名称2 数据类型,
列名称3 数据类型,
....
)
```

# 函数用法

## case-when-then

1. 简单case函数

```sql
case sex when '1' then '男' when '2' then '女’ else '其他' end
```

2. case搜索函数

```sql
case when sex = '1' then '男'  when sex = '2' then '女' else '其他' end 
```

## decode()函数

```sql
Select decode（columnname，值1,翻译值1,值2,翻译值2,...值n,翻译值n,缺省值）
 
From talbename
 
Where …
```

其中：columnname为要选择的table中所定义的column；

　　 缺省值可以是你要选择的column name本身，也可以是你想定义的其他值，比如Other等；

主要作用：相当于IF语句， 将查询结果翻译成其他值。（即以其他形式表现出来）。

