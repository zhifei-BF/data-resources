# shell概述

大数据程序员为什么要学习Shell呢？
1）需要看懂运维人员编写的Shell程序。
2）偶尔会编写一些简单Shell程序来管理集群、提高开发效率。

shell是一个命令行解释器，它接收应用程序/用户命令；然后调用操作系统内核。

![20210105220345920](shell.assets/20210105220345920.png)

- shell 还是一个功能相当强大的编程语言，易编写，易调试，灵活性强。

# shell解析器

（1）Linux提供的Shell解析器有：

```shell
[atguigu@hadoop101 ~]$ cat /etc/shells 
/bin/sh
/bin/bash
/sbin/nologin
/bin/dash
/bin/tcsh
/bin/csh
```

（2）bash和sh的关系（软连接）

```shell
[atguigu@hadoop101 bin]$ ll | grep bash
-rwxr-xr-x. 1 root root 941880 5月  11 2016 bash
lrwxrwxrwx. 1 root root      4 5月  27 2017 sh -> bash
```

（3）Centos默认的解析器是bash

```shell
[atguigu@hadoop102 bin]$ echo $SHELL
/bin/bash
```

# shell脚本入门

1．脚本格式
脚本以#!/bin/bash开头（指定解析器）

2．第一个Shell脚本：helloworld
（1）需求：创建一个Shell脚本，输出helloworld
（2）案例实操：

```shell
//touch命令用于创建一个文件
[atguigu@hadoop101 datas]$ touch helloworld.sh
[atguigu@hadoop101 datas]$ vi helloworld.sh

在helloworld.sh中输入如下内容
#!/bin/bash
echo "helloworld"
```

（3）脚本的常用执行方式
第一种：采用bash或sh+脚本的相对路径或绝对路径（不用赋予脚本+x权限）



sh+脚本的相对路径

```shell
[atguigu@hadoop101 datas]$ sh helloworld.sh 
Helloworld
```

sh+脚本的绝对路径

```shell
[atguigu@hadoop101 datas]$ sh /home/atguigu/datas/helloworld.sh 
helloworld
```

bash+脚本的相对路径

```shell
[atguigu@hadoop101 datas]$ bash helloworld.sh 
Helloworld
```

bash+脚本的绝对路径

```shell
[atguigu@hadoop101 datas]$ bash /home/atguigu/datas/helloworld.sh 
Helloworld
```

第二种：采用输入脚本的绝对路径或相对路径执行脚本（必须具有可执行权限+x）

（a）首先要赋予helloworld.sh 脚本的+x权限

```shell
//777 表示可读可写可执行
[atguigu@hadoop101 datas]$ chmod 777 helloworld.sh
```

（b）执行脚本

相对路径

```shell
[atguigu@hadoop101 datas]$ ./helloworld.sh 
Helloworld
```

绝对路径

```shell
[atguigu@hadoop101 datas]$ /home/atguigu/datas/helloworld.sh 
Helloworld
```

注意：第一种执行方法，本质是bash解析器帮你执行脚本，所以脚本本身不需要执行权限。第二种执行方法，本质是脚本需要自己执行，所以需要执行权限。

3．第二个Shell脚本：多命令处理

（1）需求： 在/home/atguigu/目录下创建一个banzhang.txt,在banzhang.txt文件中增加“I love cls”。
（2）案例实操：

```shell
[atguigu@hadoop101 datas]$ touch batch.sh
[atguigu@hadoop101 datas]$ vi batch.sh

在batch.sh中输入如下内容
#!/bin/bash

cd /home/atguigu
touch cls.txt
echo "I love cls" >>cls.txt
```

# Shell中的变量

系统变量：

1. 常用系统变量

```shell
 $HOME、$PWD、$SHELL、$USER等
```

2. 案例实操

（1）查看系统变量的值

```shell
[atguigu@hadoop101 datas]$ echo $HOME
/home/atguigu
```

（2）显示当前Shell中所有变量：set

```shell
[atguigu@hadoop101 datas]$ set
BASH=/bin/bash
BASH_ALIASES=()
BASH_ARGC=()
BASH_ARGV=()
```

自定义变量：

1．基本语法
（1）定义变量：变量=值
（2）撤销变量：unset 变量
（3）声明静态变量：readonly变量，注意：不能unset

2．变量定义规则
（1）变量名称可以由字母、数字和下划线组成，但是不能以数字开头，环境变量名建议大写。
（2）等号两侧不能有空格
（3）在bash中，变量默认类型都是字符串类型，无法直接进行数值运算。
（4）变量的值如果有空格，需要使用双引号或单引号括起来。

3．案例实操
（1）定义变量A

```shell
[atguigu@hadoop101 datas]$ A=5
[atguigu@hadoop101 datas]$ echo $A
5
```

（2）给变量A重新赋值

```shell
[atguigu@hadoop101 datas]$ A=8
[atguigu@hadoop101 datas]$ echo $A
8
```

（3）撤销变量A

```shell
[atguigu@hadoop101 datas]$ unset A
[atguigu@hadoop101 datas]$ echo $A
```

（4）声明静态的变量B=2，不能unset

```shell
[atguigu@hadoop101 datas]$ readonly B=2
[atguigu@hadoop101 datas]$ echo $B
2
[atguigu@hadoop101 datas]$ B=9
-bash: B: readonly variable
```

（5）在bash中，变量默认类型都是字符串类型，无法直接进行数值运算

```shell
[atguigu@hadoop102 ~]$ C=1+2
[atguigu@hadoop102 ~]$ echo $C
1+2
```

（6）变量的值如果有空格，需要使用双引号或单引号括起来

```shell
[atguigu@hadoop102 ~]$ D=I love banzhang
-bash: world: command not found
[atguigu@hadoop102 ~]$ D="I love banzhang"
[atguigu@hadoop102 ~]$ echo $A
I love banzhang
```

（7）可把变量提升为全局环境变量，可供其他Shell程序使用
export 变量名

```shell
[atguigu@hadoop101 datas]$ vim helloworld.sh 

在helloworld.sh文件中增加echo $B
#!/bin/bash

echo "helloworld"
echo $B

[atguigu@hadoop101 datas]$ ./helloworld.sh 
Helloworld
```

发现并没有打印输出变量B的值。

```shell
[atguigu@hadoop101 datas]$ export B
[atguigu@hadoop101 datas]$ ./helloworld.sh 
helloworld
2
```

特殊变量：$n

1．基本语法
`$n` （功能描述：n为数字，`$0`代表该脚本名称，`$1-$9`代表第一到第九个参数，十以上的参数，十以上的参数需要用大括号包含，如${10}）
2．案例实操
（1）输出该脚本文件名称、输入参数1和输入参数2 的值

```shell
[atguigu@hadoop101 datas]$ touch parameter.sh 
[atguigu@hadoop101 datas]$ vim parameter.sh

#!/bin/bash
echo "$0  $1   $2"

[atguigu@hadoop101 datas]$ chmod 777 parameter.sh

[atguigu@hadoop101 datas]$ ./parameter.sh cls  xz
./parameter.sh  cls   xz
```

特殊变量：$#

1．基本语法
`$#` （功能描述：获取所有输入参数个数，常用于循环）。
2．案例实操
（1）获取输入参数的个数

```shell
[atguigu@hadoop101 datas]$ vim parameter.sh

#!/bin/bash
echo "$0  $1   $2"
echo $#

[atguigu@hadoop101 datas]$ chmod 777 parameter.sh

[atguigu@hadoop101 datas]$ ./parameter.sh cls  xz
parameter.sh cls xz 
2
```

特殊变量：$*、$@

1．基本语法
$* （功能描述：这个变量代表命令行中所有的参数，∗ 把 所 有 的 参 数 看 成 一 个 整 体 ） ‘ *把所有的参数看成一个整体） `∗把所有的参数看成一个整体）‘@` （功能描述：这个变量也代表命令行中所有的参数，不过$@把每个参数区分对待）
2．案例实操
（1）打印输入的所有参数

```shell
[atguigu@hadoop101 datas]$ vim parameter.sh

#!/bin/bash
echo "$0  $1   $2"
echo $#
echo $*
echo $@

[atguigu@hadoop101 datas]$ bash parameter.sh 1 2 3
parameter.sh  1   2
3
1 2 3
1 2 3
```

特殊变量：$?

1．基本语法
`$？` （功能描述：最后一次执行的命令的返回状态。如果这个变量的值为0，证明上一个命令正确执行；如果这个变量的值为非0（具体是哪个数，由命令自己来决定），则证明上一个命令执行不正确了。）
2．案例实操
（1）判断helloworld.sh脚本是否正确执行

```shell
[atguigu@hadoop101 datas]$ ./helloworld.sh 
hello world
[atguigu@hadoop101 datas]$ echo $?
0
```

# 运算符

1．基本语法
（1）`“$((运算式))”或“$[运算式]”`
（2）expr + , - , *, /, % 加，减，乘，除，取余
注意：expr运算符间要有空格

2．案例实操：
（1）计算3+2的值

```shell
[atguigu@hadoop101 datas]$ expr 2 + 3
5
```

（2）计算3-2的值

```shell
[atguigu@hadoop101 datas]$ expr 3 - 2 
1
```

（3）计算（2+3）X4的值
（a）expr一步完成计算

```shell
[atguigu@hadoop101 datas]$ expr `expr 2 + 3` \* 4
20
```

（b）采用$[运算式]方式

```shell
[atguigu@hadoop101 datas]# S=$[(2+3)*4]
[atguigu@hadoop101 datas]# echo $S
```

# 条件判断

1．基本语法
[ condition ]（注意condition前后要有空格）
注意：条件非空即为true，[ atguigu ]返回true，[] 返回false。

常用判断条件
（1）两个整数之间比较
= 字符串比较
-lt 小于（less than）
-le 小于等于（less equal）
-eq 等于（equal）
-gt 大于（greater than）
-ge 大于等于（greater equal）
-ne 不等于（Not equal）
（2）按照文件权限进行判断
-r 有读的权限（read）
-w 有写的权限（write）
-x 有执行的权限（execute）
（3）按照文件类型进行判断
-f 文件存在并且是一个常规的文件（file）
-e 文件存在（existence）
-d 文件存在并是一个目录（directory）
3．案例实操
（1）23是否大于等于22

```shell
[atguigu@hadoop101 datas]$ [ 23 -ge 22 ]
[atguigu@hadoop101 datas]$ echo $?
0
```

（2）helloworld.sh是否具有写权限

```shell
[atguigu@hadoop101 datas]$ [ -w helloworld.sh ]
[atguigu@hadoop101 datas]$ echo $?
0
```

（3）/home/atguigu/cls.txt目录中的文件是否存在

```shell
[atguigu@hadoop101 datas]$ [ -e /home/atguigu/cls.txt ]
[atguigu@hadoop101 datas]$ echo $?
1
```

（4）多条件判断（&& 表示前一条命令执行成功时，才执行后一条命令，|| 表示上一条命令执行失败后，才执行下一条命令）

```shell
[atguigu@hadoop101 ~]$ [ condition ] && echo OK || echo notok
OK
[atguigu@hadoop101 datas]$ [ condition ] && [ ] || echo notok
notok
```

# 流程控制（重点）

## if判断

