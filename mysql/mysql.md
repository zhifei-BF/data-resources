# MySQL

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

显示所有的表：

```mysql
show tables;
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

创建数据库并设置字符集为UTF8：

```mysql
create DATABASE database_name CHARACTER set utf8 COLLATE utf8_general_ci;
```

