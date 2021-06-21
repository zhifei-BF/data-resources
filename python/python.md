# Python 入门

## 什么是计算机语言

```
计算机就是一台用来计算的机器，人让计算机干什么计算机就得干什么！
需要通过计算机的语言来控制计算机（编程语言）！
计算机语言其实和人类的语言没有本质的区别，不同点就是交流的主体不同！
计算机语言发展经历了三个阶段：
    机器语言
        - 机器语言通过二进制编码来编写程序
        - 执行效率好，编写起来太麻烦

    符号语言（汇编）
        - 使用符号来代替机器码
        - 编写程序时，不需要使用二进制，而是直接编写符号
        - 编写完成后，需要将符号转换为机器码，然后再由计算机执行
            符号转换为机器码的过程称为汇编
        - 将机器码转换为符号的过程，称为反汇编  
        - 汇编语言一般只适用于某些硬件，兼容性比较差  

    高级语言
        - 高级语言的语法基本和现在英语语法类似，并且和硬件的关系没有那么紧密了
        - 也就是说我们通过高级语言开发程序可以在不同的硬件系统中执行
        - 并且高级语言学习起来也更加的容易，现在我们知道的语言基本都是高级语言
        - C、C++、C#、Java、JavaScript、Python 。。。
```

## 编译型语言和解释型语言

```
计算机只能识别二进制编码（机器码），所以任何的语言在交由计算机执行时必须要先转换为机器码，
    也就是像 print('hello') 必须要转换为类似 1010101 这样的机器码   

根据转换时机的不同，语言分成了两大类：
    编译型语言
        - C语言
        - 编译型语言，会在代码执行前将代码编译为机器码，然后将机器码交由计算机执行
        - a(源码) --编译--> b(编译后的机器码)
        - 特点：
            执行速度特别快
            跨平台性比较差

    解释型语言 
        - Python JS Java
        - 解释型语言，不会在执行前对代码进行编译，而是在执行的同时一边执行一边编译
        - a（源码）--解释器--> 解释执行  
        - 特点：
            执行速度比较慢
            跨平台性比较好   
```

## Python的介绍

```
Python是解释型语言

Python（英国发音：/ˈpaɪθən/ 美国发音：/ˈpaɪθɑːn/），是一种广泛使用的高级编程语言，属于通用型编程语言，由吉多·范罗苏姆创造，第一版发布于1991年。可以视之为一种改良（加入一些其他编程语言的优点，如面向对象）的LISP。作为一种解释型语言，Python的设计哲学强调代码的可读性和简洁的语法（尤其是使用空格缩进划分代码块，而非使用大括号或者关键词）。相比于C++或Java，Python让开发者能够用更少的代码表达想法。不管是小型还是大型程序，该语言都试图让程序的结构清晰明了。 

Life is short you need Python （人生苦短，我用Python）    

Python的用途：
    WEB应用
        Facebook 豆瓣 。。。
    爬虫程序
    科学计算
    自动化运维
    大数据（数据清洗）
    云计算
    桌面软件/游戏
    人工智能
    。。。   
```

## Python3 安装

- Python 官网：https://www.python.org/
- Python 文档下载地址：https://www.python.org/doc/

**Unix & Linux 平台安装 Python:**

以下为在 Unix & Linux 平台上安装 Python 的简单步骤：

- 打开 WEB 浏览器访问 https://www.python.org/downloads/source/
- 选择适用于 Unix/Linux 的源码压缩包。
- 下载及解压压缩包 `Python-3.x.x.tgz`，`3.x.x `为你下载的对应版本号。
- 如果你需要自定义一些选项修改 Modules/Setup

```python
# tar -zxvf Python-3.8.5.tgz
# cd Python-3.8.5
# ./configure
# make && make install
```

检查 Python3 是否正常可用：

```python
# python3 -V
Python 3.8.5
```

查看 Python 版本:

```python
python -V
```

**Windows 平台安装 Python:**

以下为在 Windows 平台上安装 Python 的简单步骤：

- 打开 WEB 浏览器访问https://www.python.org/downloads/windows/
- 在下载列表中选择 Windows 平台安装包，包格式为：`python-3.8.5-amd64.exe` 文件 ，` -3.8.5` 为你要安装的版本号，`-amd64`为你系统的版本。
- 下载后，双击下载包，进入 Python 安装向导，安装非常简单，你只需要使用默认的设置一直点击"下一步"直到安装完成即可。

```
Windows x86-64 为 64 位版本
Windows x86 为 32 位版本
```

**MAC 平台安装 Python:**

MAC 系统一般都自带有 Python2.7 版本 的环境，你也可以在链接 https://www.python.org/downloads/mac-osx/ 上下载最新版安装。

**集成开发环境（IDE：Integrated Development Environment）: PyCharm**

## Python开发环境搭建

```
开发环境搭建就是安装Python的解释器
Python的解释器分类：
    CPython（官方）
        用c语言编写的Python解释器
    PyPy
        用Python语言编写的Python解释器
    IronPython
        用.net编写的Python解释器
    Jython
        用Java编写的Python解释器

步骤：
    1.下载安装包 python-3.6.5.exe（版本之间互不兼容，在3.x开发的代码不能在2.x上运行，反之也是）
        - 3.x
        - 2.x    
    2.安装（傻瓜式安装） 
    3.打开命令行窗口，输入python 出现如下内容
        Python 3.6.5 (v3.6.5:f59c0932b4, Mar 28 2018, 16:07:46) [MSC v.1900 32 bit (Intel)] on win32
        Type "help", "copyright", "credits" or "license" for more information.
        >>>    
```

<font color="red">注意：需要将python路径添加到path环境变量中。</font>

## Python的交互界面

```
当我们通过命令行来输入Python，所进入到的界面就是Python的交互界面，exit() 用于退出
结构：
    版本和版权声明：
    Python 3.6.5 (v3.6.5:f59c0932b4, Mar 28 2018, 16:07:46) [MSC v.1900 32 bit (Intel)] on win32
    Type "help", "copyright", "credits" or "license" for more information.

    命令提示符：
    >>>

    在命令提示符后可以直接输入Python的指令！输入完的指令将会被Python的解释器立即执行！

    安装Python的同时，会自动安装一个Python的开发工具IDLE，通过IDLE也可以进入到交互模式
    但是不同的是，在IDLE中可以通过TAB键来查看语句的提示。
    IDLE实际上就是一个交互界面，但是他可以有一些简单的提示，并且可以将代码保存

交互模式只能你输入一行代码，它就是执行一行，所以他并不适用于我们日常的开发！ 
    仅可以用来做一些日常的简单的测试！   

我们一般会将Python代码编写到一个py文件中，然后通过python指令来执行文件中的代码

练习：
    自己尝试创建一个py文件，并向文件中写入python打印语句（print...） 
        然后执行该文件。
    如果你的系统的扩展名无法修改，请尝试自行baidu！
```

## Python和Subline的整合

- 在Sublime中执行Python代码，ctrl + b 自动在Sublime内置的控制台中执行

  - 这种执行方式，在某些版本的Sublime中对中文支持不好，并且不能使用input()函数

- 使用SublimeREPL来运行python代码  

  - 安装完成，首选项-》设置快捷键，希望按f5则自动执行当前的Python代码

    ```Python
    { "keys": ["f5"], "caption": "SublimeREPL:Python","command": "run_existing_window_command", "args":{"id": "repl_python_run","file": "config/Python/Main.sublime-menu"}},
    ```

## 第一个 Python3.x 程序

\1. 对于大多数程序语言，第一个入门编程代码便是"Hello World！"，以下代码为使用 Python 输出"Hello World！"：

```python
#!/usr/bin/python3
print("Hello, World!")
```

- `#!/usr/bin/python` ：指定用什么解释器运行脚本以及解释器所在的位置，如果解释器没有装在/usr/bin/目录，改成其所在目录就行了，或者更通用的方法是：
  ​`#!/usr/bin/env python`​。

\2. 你可以将以上代码保存在 `hello.py` 文件中并使用 python 命令执行该脚本文件。

```python
python hello.py
```

\3. 以上命令输出结果为：

```
Hello, World!
```

## 几个概念

1. 表达式
   1. 表达式就是一个类似于数学公式的东西。
   2. 比如：10+5     8-4
   3. 表达式一般仅仅用来计算一些结果，不会对程序产生实质性的影响。
   4. 如果在交互模式中输入了一个表达式，解释器会自动将表达式的结果输出。
2. 语句
   1. 在程序中语句一般需要完成某种功能，比如打印信息、获取信息、为变量赋值。。。
   2. 比如：
      1. print()
      2. input()
      3. a = 10
   3.  语句的执行一般会对程序产生一定的影响
   4. 在交互模式中不一定会输出语句的执行结果
3. 程序（program)
   1. 程序就是由一条一条的语句和一条一条的表达式构成的。
4. 函数（function）
   1. 函数就是一种语句，函数专门用来完成特定的功能。
   2. 函数长得形如：xxx()
   3. 函数的分类：
      1. 内置函数
         1. 由Python解释器提供的函数，可以在Python中直接使用
      2. 自定义函数
         1. 由程序员自主的创建的函数
   4. 当我们需要完成某个功能时，就可以去调用内置函数，或者自定义函数
   5. 函数的两个要素：
      1. 参数
         1. （）中的内容就是函数的参数
         2. 函数中可以没有参数，也可以有多个参数，多个参数之间使用，隔开
      2. 返回值
         1. 返回值就是函数的返回结果，不是所有的函数都有返回值。

## 基本语法

1. 在Python中严格区分大小写。

2. Python中的每一行就是一条语句，每条语句以换行结束。
3. Python中每一行语句不要过长（规范中建议每行不要超过80个字符） ` "rulers":[80],`
4. 一条语句可以分多行编写，多行编写时语句后边以\结尾  。
5. Python是缩进严格的语言，所以在Python中不要随便写缩进 。
6. 在Python中使用#来表示注释，#后的内容都属于注释，注释的内容将会被解释器所忽略；我们可以通过注释来对程序进行解释说明，一定要养成良好的编写注释的习惯；注释要求简单明了，一般习惯上#后边会跟着一个空格。

## 字面量、变量、标识符

- Python中使用变量，不需要声明，直接为变量赋值即可。

- 字面量就是一个一个的值，比如：1，2，3，4，5，6，‘HELLO’
  - 字面量所表示的意思就是它的字面的值，在程序中可以直接使用字面量
- 变量（variable）变量可以用来保存字面量，并且变量中保存的字面量是不定的
  - 变量本身没有任何意思，它会根据不同的字面量表示不同的意思
- 一般我们在开发时，很少直接使用字面量，都是将字面量保存到变量中，通过变量来引用字面量。
- 在Python中所有可以自主命名的内容都属于标识符；比如：变量名、函数名、类名；
- 标识符必须遵循标识符的规范：
  - 标识符中可以含有数字、字母、_ ,但是不能使用数字开头。列子：a_1、 _a1、 _1a
  - 标识符不能是Python中的关键字和保留字；也不建议使用Python中的函数名作为标识符，因为这样会导致函数被覆盖。
  - 命名规范：在Python中注意遵循两种命名规范：
    - 下划线命名法：（所有字母小写，单词之间使用_分割）例如：max_length、min_length、hello_world、xxx_yyy_zzz
    - 帕斯卡命名法（大驼峰命名法）：（首字母大写，每个单词开头字母大写，其余字母小写）例如：MaxLength、MinLength、HelloWorld、XxxYyyZzz
- 如果使用不符合标准的标识符，将会报错SyntaxError: invalid syntax   

## 数据类型

### **数值**

- 数据类型指的就是变量的值的类型，也就是可以为变量赋哪些值 。
- 在Python，数值分为了三种：整数、浮点数（小数）、复数
- 在Python中，所有的整数都是int类型的。
  - Python中的整数的大小没有限制，可以是一个无限大的整数。
  - 如果数字的长度过大，可以使用下划线作为分隔符。
- 10进制的数字不能以0开头。
- 其他进制的整数，只要是数字打印时一定是以十进制的形式显示的
- 二进制以0b开头、八进制以0o开头、十六进制以0x开头。例如：`ob10`表示二进制的10，`0o10`表示八进制的10，`0x10`表示十六进制的10。
- 也可以通过运算符来对数字进行运算，并且可以保证整数运算的精确。
- 浮点数（小数），在Python中所有的小数都是float类型。
- 对浮点数进行运算时，可能会得到一个不精确的结果。

### **字符串（str）**

- 字符串用来表示一段文本信息，字符串是程序中使用的最多的数据类型。
- 在Python中，字符串需要使用引号引起来；不使用引号，则不是字符串；引号可以是单引号，也可以是双引号，但是注意不能混着用；并且相同的引号之间不能嵌套。
- 使用三重引号来表示一个长字符串 '''   """
  - 三重引号可以换行，并且会保留字符串中的格式。
- 字符串之间也可以进行加法运算，如果将两个字符串进行相加，则会自动将两个字符串拼接为一个。
- 格式化字符串就是指赋值。
- 在创建字符串时，可以在字符串中指定占位符；`%s`表示任意字符，`%f`表示浮点数占位符，`%d`表示整数占位符。

```python
b = 'Hello %s'%'孙悟空' 
b = 'hello %s 你好 %s'%('tom','孙悟空')
b = 'hello %3.5s'%'abcdefg' # %3.5s字符串的长度限制在3-5之间
b = 'hello %s'%123.456
b = 'hello %.2f'%123.456
b = 'hello %d'%123.95
b = '呵呵'
```

- 格式化字符串，可以通过在字符串前添加一个f来创建一个格式化字符串。
  - 在格式化字符串中可以直接嵌入变量。

```python
c = f'hello {a} {b}'
```

- 如果将字符串和数字（不能是小数）相乘，则解释器会将字符串重复指定的次数并返回。

### **转义字符**

- 可以使用\作为转义字符，通过转义字符，可以在字符串中使用一些特殊的内容；例如：`\'`表示`'`,`\"`表示`"`,`\t`表示制表符，`\n`表示换行符，`\\`表示反斜杠，`\uxxxx`表示Unicode编码。

### **布尔值(bool)和空值**

- 布尔值主要用来做逻辑判断；布尔值一共有两个True（真）和False（假）
- 布尔值实际上也属于整型，True就相当于1，False就相当于0，所以布尔值也能和整数进行运算。
- None（空值），专门用来表示不存在。

## 类型检查

- 通过类型检查，可以检查值（变量）的类型。
- type()用来检查值的类型
  - 该函数会将检查的结果作为返回值返回，可以通过变量来接收函数的返回值。

## 类型转换

- 所谓的类型转换，将一个类型的对象转换为其他对象
- 类型转换不是改变对象本身的类型，而是根据当前对象的值创建一个新对象
- 类型转换四个函数 int()  float()  str()   bool()
- int()可以用来将其他的对象转换为整型
  - 规则：
    - 布尔值：True -> 1     False -> 0
    - 浮点数：直接取整，省略小数点后的内容
    - 字符串：合法的整数字符串，直接转换为对应的数字；如果不是一个合法的整数字符串，则报错。
    - 对于其他不可转换为整型的字符串，直接抛出异常 ValueError
- float()和int()基本一致，不同的是它会将对象转换为浮点数。
- str()可以将对象转换为字符串
  - True -> 'True'
  - False -> 'False'
  - 123  -> '123'
- bool()可以将对象转换为布尔值，任何对象都可以转换为布尔值。
  - 规则：对于所有表示空性的对象都会转换为False，其余的转换为True；表示空性的有：0、None、”“。。。

## 运算符（操作符）

- 运算符可以对一个值或多个值进行运算或各种操作
- 比如 + 、-、= 都属于运算符
- 运算符的分类：
    1.算术运算符
    2.赋值运算符
    3.比较运算符（关系运算符）
    4.逻辑运算符
    5.条件运算符（三元运算符）   

### **算术运算符**

- +为加法运算符(如果是两个字符串之间进行加法运算，则会进行拼串操作)
- -为减法运算符
- *为乘法运算符（如果将字符串和数字相乘，则会对字符串进行复制操作，将字符串重复指定次数）
- /为除法运算符（运算时结果总会返回一个浮点类型）
- //为整除运算符（只会保留计算后的整数位，总会返回一个整型）
- **为幂运算，求一个值的几次幂
- %为取模（求两个数相除的余数）
- 在对浮点数做算术运算时，结果也会返回一个浮点数。

### **赋值运算符**

- = 可以将等号右侧的值赋值给等号左侧的变量

```python
# +=  a += 5 相当于 a = a + 5 
# -=  a -= 5 相当于 a = a - 5 
# *=  a *= 5 相当于 a = a * 5 
# **= a **= 5 相当于 a = a ** 5 
# /=  a /= 5 相当于 a = a / 5 
# //= a //= 5 相当于 a = a // 5 
# %=  a %= 5 相当于 a = a % 5 
```

### **关系运算符**

- 关系运算符用来比较两个值之间的关系，总会返回一个布尔值；如果关系成立，返回True，否则返回False。
  - `>`比较左侧值是否大于右侧值
  - `<`比较左侧值是否小于右侧值
  - `>=`比较左侧值是否大于等于右侧值
  - `<=`比较左侧值是否小于等于右侧值
  - `==`比较两个对象的值是否相等
  - `!=`比较两个对象的值是否不相等（注意：相等和不等比较的是对象的值，而不是id）
  - `is`比较两个对象是否是同一个对象，比较的是对象的id
  - `is not`比较两个对象是否不是同一个对象，比较的是对象的id
- 在Python中可以对两个字符串进行大于（等于）或小于（等于）的运算，当对字符串进行比较时，实际上比较的是字符串的Unicode编码，比较两个字符串的Unicode编码时，是逐位比较的；利用该特性可以对字符串按照字母顺序进行排序，但是对于中文来说意义不是特别大。
- 注意：如果不希望比较两个字符串的Unicode编码，则需要将其转换为数字然后再进行比较。

### **逻辑运算符**

- 逻辑运算符主要用来做一些逻辑判断
  - not 逻辑非
    - not可以对符号右侧的值进行非运算；对于布尔值，非运算会对其进行取反操作，True变False，False变True；对于非布尔值，非运算会先将其转换为布尔值，然后再取反。
  - and 逻辑与
    - and可以对符号两侧的值进行与运算；只有在符号两侧的值都为True时，才会返回True，只要有一个False就返回False；与运算是找False的，Python中的与运算是短路的与，如果第一个值是False，则不再看第二个值。
  - or 逻辑或
    - or 可以对符号两侧的值进行或运算；或运算两个值中只要有一个True，就会返回True；或运算是找True的，Python中的或运算是短路的或，如果第一个值返回True，则不再看第二个值。
- 非布尔值的与或运算：当我们对非布尔值进行与或运算时，Python会将其当作布尔值运算，最终返回原值。

### **条件运算符（三元运算符）**

- 语法：语句1   if   条件表达式  else  语句2
- 执行流程：
  - 条件运算符在执行时，会先对条件表达式进行求值判断，如果判断结果为True，则执行语句1，并返回执行结果；如果判断结果为False，则执行语句2，并返回执行结果。

```python
a = 30 
b = 50
max = a if a > b else b
print(max)
```

### **运算符的优先级**

- 和数学中一样，在Python运算也有优先级，比如先乘除，后加减。
- 运算符的优先级可以根据优先级的表格来查询，在表格中位置越靠下的运算符优先级越高，优先级越高的越优先计算；如果优先级一样则自左向右计算。

## 对象的结构

```
- 每个对象中都要保存三种数据
    - id（标识）
        > id用来标识对象的唯一性，每一个对象都有唯一的id
        > 对象的id就相当于人的身份证号一样
        > 可以通过id()函数来查看对象的id
        > id是由解析器生成的，在CPython中，id就是对象的内存地址
        > 对象一旦创建，则它的id永远不能再改变

    - type（类型）
        > 类型用来标识当前对象所属的类型
        > 比如：int str float bool 。。。
        > 类型决定了对象有哪些功能
        > 通过type()函数来查看对象的类型
        > Python是一门强类型的语言，对象一旦创建类型便不能修改
    - value（值）
        > 值就是对象中存储的具体的数据
        > 对于有些对象值是可以改变的
        > 对象分成两大类，可变对象 不可变对象
            可变对象的值可以改变
            不可变对象的值不能改变，之前学习的对象都是不可变对象
    - 参考 图2

练习：尝试自己画一下对象的内存结构。  
```

## 变量和对象

```
- 对象并没有直接存储到变量中，在Python中变量更像是给对象起了一个别名
- 变量中存储的不是对象的值，而是对象的id（内存地址），
    当我们使用变量时，实际上就是在通过对象id在查找对象
- 变量中保存的对象，只有在为变量重新赋值时才会改变
- 变量和变量之间是相互独立的，修改一个变量不会影响另一个变量

- 参考 图3
```

## 流程控制语句

### 条件判断语句（if语句）

- 语法: if表达式：代码块
- 执行的流程：if语句在执行时，会先对条件表达式进行求值判断，如果为True，则执行if后的语句；如果为False，则不执行。
- 默认情况下，if语句只会控制紧随其后的那条语句，如果希望if语句可以控制多条语句，则可以在if后跟着一个代码块。
- 代码块：
  - 代码块中保存着一组代码，同一个代码块中的代码，要么都执行要么都不执行。
  - 如果要编写代码块，语句就不能紧随在：后边，而是要写在下一行。
    - 代码块以缩进开始，直到代码恢复到之前的缩进级别时结束。

缩进有两种方式：

​	一种是使用tab键

​	一种是使用空格（四个）

在Python的官方文档中推荐我们使用空格来缩进。

Python代码中使用的缩进方式必须统一。

### input()函数

该函数用来获取用户的输入。

input()调用后，程序会立即暂停，等待用户输入，用户输入完内容以后，点击回车程序才会继续向下执行，用户输入完成以后，其所输入的内容会以返回值形式返回。

注意：input()的返回值是一个字符串。

input()函数中可以设置一个字符串作为参数，这个字符串将会作为提示文字显示。

```python
a = input("请输入任意内容：")
```

### if-else语句

语法：

```python
if 条件表达式：
	代码块
else:
	代码块
```

​	执行流程：if-else语句在执行时，先对if后的条件表达式进行求值判断，如果为True，则执行if后的代码块；如果为False，则执行else后的代码块。

### if-elif-else语句

语法：

```python
if 条件表达式：
	代码块
elif 条件表达式：
	代码块
elif 条件表达式：
	代码块
elif 条件表达式：
	代码块
else： 
	代码块
```

执行流程：if-elif-else语句在执行时，会自上向下依次对条件表达式进行求值判断，如果表达式的结果为True，则执行当前代码块，然后语句结束；如果表达式的结果为False，则继续向下判断，直到找到True为止；如果所有的表达式都是False，则执行else后的代码块。

if-elif-else中只会有一个代码块执行。

### 循环语句

循环语句可以使指定的代码块重复指定的次数。

循环语句分为两种：

​	while循环

​	for循环

对于while循环：

语法：

```python
while 条件表达式：
	代码块
else：
	代码块
```

执行流程：while语句在执行时，会先对while后的条件表达式进行求值判断，如果判断结果为True，则执行循环体（代码块），循环体执行完毕后，继续对条件表达式进行求值判断，以此类推，直到判断结果为False，则循环终止，如果循环有对应的else，则执行else后的代码块。

条件表达式恒为True的循环语句，称为死循环，它会一直运行。

循环的三个要件（表达式）

​	初始化表达式，通过初始化表达式初始化一个变量。

​	条件表达式，条件表达式用来设置循环执行的条件。	

​	更新表达式，修改初始化变量的值。

创建一个执行十次的循环：

```python
i = 0
while i < 10:
	i += 1
	print(i,"hell")
else:
	print("else中的代码块")
```

### break、continue、pass

break可以用来立即退出循环语句（包括else语句）

continue可以用来跳过当次循环。

break和continue都是只对离它最近的循环起作用。

pass是用来在判断或循环语句中占位的。

```python
i = 0 
if i < 5:
	pass
```

### 模块

模块，通过模块可以对python进行扩展。

```python
# 引入一个time模块，来统计程序执行的时间。
from time import * 
```

## 序列

### 列表

创建列表，通过[]来创建列表。

```python
my_list = [] # 创建了一个空列表
```

列表存储的数据，我们称为元素。

一个列表中可以存储多个元素，也可以在创建列表时，来指定列表中的元素。

```python
my_list = [10] # 创建了一个只包含一个元素的列表
```

当向列表中添加多个元素时，多个元素之间使用逗号隔开。

```python
my_list = [10,20,30,40,50]
```

列表中可以保存任意的对象。

```python
my_list = [10,"hello",True,None,[1,2,3]]
```

列表中的对象都会按照插入的顺序存储到列表中，我们可以通过索引（index)来获取列表中的元素；索引是元素在列表中的位置，列表中的每一个元素都有一个索引。索引是从0开始的整数，列表中第一个位置索引为0，第二个位置索引为1，以此类推。

```python
a = my_list[0] # 获取列表中第一个元素
```

通过len()函数，可以获取列表中元素的个数，也就是列表的长度。

```python
a = len(my_list)
```

### 切片

切片指从现有列表中，获取一个子列表。

创建一个列表，一般创建列表时，变量的名字会使用复数。

列表的索引可以是负数，如果索引是负数，则从后向前获取元素，-1表示倒数第一个，-2表示倒数第二个，以此类推。

```python
stus = ['孙悟空','猪八戒','沙和尚','唐僧','蜘蛛精','白骨精']
```

通过切片来获取指定的元素：

语法：列表[起始:结束]

通过切片获取元素时，会包括起始位置的元素，不会包括结束位置的元素。

做切片操作时，总会返回一个新的列表，不会影响原来的列表。

起始和结束位置的索引都可以省略不写。

如果省略结束位置的索引，则会一直截取到最后。

如果省略起始位置，则会从第一个元素开始截取。

如果起始位置和结束位置的索引都省略，相当于创建了一个列表的副本。

```python
# print(stus[1:])
# print(stus[:3])
# print(stus[:])
# print(stus)
```

语法：列表[起始:结束：步长]

步长表示，每次获取元素的间隔，默认值是1。

步长不能是0，但是可以是负数。如果为负数，则会从列表的后部向前边取元素。

### 通用操作

+可以将两个列表拼接为一个列表。

```python
my_list = [1,2,3] + [4,5,6]
```

*可以将列表重复指定的次数。

```python
my_list = [1,2,3] * 5
```

in可以检查指定元素是否存在于列表中。

not in 可以检查指定元素是否不存在于列表中。

```python
stus = ['孙悟空','猪八戒','沙和尚','唐僧','蜘蛛精','白骨精','沙和尚','沙和尚']
print('牛魔王' not in stus)
print('牛魔王' in stus)
```

max()和min()可以获取列表中的最大值和最小值。

```python
arr = [10,1,2,5,100,77]
print(min(arr) , max(arr))
```

index()获取指定元素在列表中的第一次出现时索引

```python
print(stus.index("沙和尚"))
```

index()的第二个参数，表示查找的起始位置，第三个参数，表示查找的结束位置;如果要获取列表中没有的元素，会抛出异常。

```python
print(stus.index("沙和尚",3,7))
```

count()统计指定元素在列表中出现的次数。

```python
print(stus.count("牛魔王"))
```

### 修改元素

1. 直接通过索引来修改元素，通过del来删除元素。

```python
stus = ['孙悟空','猪八戒','沙和尚','唐僧','蜘蛛精','白骨精']
stus[0] = 'sunwukong'
stus[2] = '哈哈'
# 通过del来删除元素
del stus[2] # 删除索引为2的元素
```

2. 通过切片来修改列表；在给切片进行赋值时，只能使用序列。

```python
stus[0:2] = ['牛魔王','红孩儿'] # 使用新的元素替换旧的元素
stus[0:0] = ['牛魔王'] # 向索引为0的位置插入元素
```

当设置了步长，序列中元素的个数必须和切片中元素的个数保持一致。

```python
stus[::2] = ['牛魔王','红孩儿','二郎神']
```

通过切片来删除元素。

```python
del stus[0:2]
del stus[::2]
stus[1:3] = []
```

以上操作，只适用于可变序列；不可变序列，无法通过索引来修改。

可以通过list()函数将其他的序列转换为list

```python
s = 'hello'
# s[0] = 'a' # 这样是不行的。
s = list(s)
print(s)
```

### 列表的方法

append()向列表的最后添加一个元素。

```python
stus.append('唐僧')
```

insert()向列表的指定位置插入一个元素。

参数：

 	1. 要插入的位置
 	2. 要插入的元素

```python
stus.insert(2,"唐僧")
```

extend()使用新的序列来扩展当前序列。

需要一个序列作为参数，它会将该序列中的元素添加到当前列表中。

```python
stus.expend(["白骨精","蜘蛛精"])
stus +=["白骨精","蜘蛛精"]
```

clear()清空序列。

```python
stus.clear()
```

pop()根据索引删除并返回被删除的元素。

```python
result = stus.pop(2)
result = stus.pop() # 删除最后一个元素
```

remove()删除指定值的元素，如果相同值的元素有多个，只会删除第一个。

```python
stus.remove("猪八戒")
# stus.remove() # 不能不带参数
```

reverse()用来反转列表。

```python
stus.reverse()
```

sort()用来对列表中的元素进行排序，默认是升序排列

如果需要降序排列，则需要传递一个reverse = True作为参数

```python
my_list = [10,1,20,3,4,5,0,-2]
my_list.sort(reverse=True)
```

### 遍历列表

遍历列表，指的就是将列表中的所有元素取出来。

通过使用for循环来遍历列表：

语法：

```python
for 变量 in 序列：
	代码块
```

for循环的代码块会执行多次，序列中有几个元素就会执行几次，每执行一次就会将序列中的一个元素赋值给变量，所以我们可以通过变量，来获取列表中的元素。

```python
for s in stus:
	print(s)
```

### range()

range()是一个函数，可以用来生成一个自然数的序列。

```python
r = range(5) # 生成一个这样的序列[0,1,2,3,4]
r = range(0,10,2)
r = range(10,0,-1)
```

该函数需要三个参数

 	1. 起始位置（可以省略，默认是0）
 	2. 结束位置
 	3. 步长（可以省略，默认是1）

## 元组（tuple）

元组是一个不可变的序列。

它的操作的方式基本上和列表是一致的。

所以在操作元组时，就把元组当成是一个不可变的列表就行了。

一般当我们希望数据不改变时，就使用元组，其余情况都使用列表。



创建元组使用()

```python
my_tuple = () # 创建了一个空元组
print(my_tuple,type(my_tuple)) # <class 'tuple'>
my_tuple = (1,2,3,4,5) # 创建了一个5个元素的元组
```

元组是不可变对象，不能尝试为元组中的元素重新赋值。

当元组不是空元组时，括号可以省略。

```python
my_tuple = 10,20,30,40
```

如果元组不是空元组，它里面至少要有一个,

```python
my_tuple = 40,
```

元组的解包（解构）

解包指就是将元组当中每一个元素都赋值给一个变量。

```python
a,b,c,d = my_tuple
```

利用元组的解包我们可以进行两值的互相交换。

```python
a = 100	
b = 300 
a , b = b , a 
```

在对一个元组进行解包时，变量的数量必须和元组中的元素数量一致。

也可以在变量前边添加一个*，这样变量将会获取元组中所有剩余的元素。

```python
a , b , *c = my_tuple
a , *b , c = my_tuple
*a , b , c = my_tuple
a , b , *c = [1,2,3,4,5,6,7]
a , b , *c = 'hello world'
```

不能同时出现两个或以上的*变量。

## 字典

字典使用{}来创建。

```python
d = {} # 创建一个空字典
```

创建一个保护有数据的字典。

语法：

​	{key:value,key:value,key:value}

字典的值可以是任意对象。字典的键可以是任意的不可变对象（int、str、bool、tuple。。。。），但是一般我们都会使用str。字典的键是不能重复的，如果出现重复的后边会替换到前边的。

```python
d = {'name':'孙悟空','age':18,'gender':'男','name':'sunwukong'}
```

我们可以根据键来获取值。

```python
print(d['name'],d['age'],d['gender'])
```

如果使用了字典中不存在的键，会报错。

```python
print(d['hello']) # KeyError:'hello'
```



使用dict()函数来创建字典。

每一个参数都是一个键值对，参数名就是键，参数名就是值（这种方式创建的字典，key都是字符串）

```python
d = dict(name='孙悟空',age=18,gender='男')
```

也可以将一个包含双值子序列的序列转换为字典。

双值序列，序列中只有两个值，[1,2] ('a',3) 'ab'

子序列，如果序列中的元素也是序列，那么我们就称这个元素为子序列。例如：[(1,2),(3,5)]

```python
d = dict([('name','孙悟空'),('age',18)])
```

len()获取字典中键值对的个数。

```python
print(len(d))
```

in  检查字典中是否包含指定的键。

not in 检查字典中是否不包含指定的键。

```python
print('hello' in d)
```

获取字典中的值，根据键来获取值。

```python
print(d['age'])
```

通过[]来获取值时，如果键不存在，会抛出异常KeyError。

get(key[,default]) 该方法用来根据键来获取字典中的值。如果获取的键在字典中不存在，会返回None。也可以指定一个默认值，来作为第二个参数，这样获取不到值时将会返回默认值。

<font color='red'>[,default]可有可无。</font>

```python
print(d.get('hello','默认值'))
```

### 修改字典

d[key] = value 如果key存在则覆盖，不存在则添加。

setdefault（key[,default])可以用来向字典中添加key-value。如果key已经存在于字典中，则返回key的值，不会对字典做任何操作。如果key不存在，则向字典中添加这个key，并设置value。

```python
result = d.setdefault('hello','猪八戒')
```

update([other]) 将其他的字典中的key-value添加到字典当中。如果有重复的key，则后边的会替换到当前的。

```python
d = {'a':1,'b':2,'c':3}
d2 = {'d':4,'e':5,'f':6,'a':7}
d.update(d2)
```

删除，可以使用del来删除字典中的key-value。

```python
del d['a']
```

popitem() 随机删除字典中的一个键值对，一般都会删除最后一个键值对。删除之后，它会将删除的key-value作为返回值返回。返回的是一个元组，元组中有两个元素，第一个元素是删除的key，第二个元素是删除的value。当使用popitem()删除一个空字典时，会抛出异常KeyError：'popitem(): dictionary is empty'

```python
result = d.popitem()
```

pop(key[,default]) 根据key删除字典中的key-value。会将被删除的value返回。如果删除不存在的key，会抛出异常。如果指定了默认值，再删除不存在的key时，不会报错，而是直接返回默认值。

```python
result = d.pop('d','这是默认值')
```

clear() 用来清空字典。

```python
d.clear()
```

copy() 该方法用于对字典进行浅复制。复制以后的对象，和原对象是独立，修改一个不会影响到另一个。

注意：浅复制会进行简单复制对象内部的值，如果值也是一个可变对象，这个可变对象不会被复制。

```python
d = {'a':1,'b':2,'c':3}
d2 = d.copy()
```

```python
d = {'a':{'name':'孙悟空','age':18},'b':2,'c':3}
d2 = d.copy()
d2['a']['name'] = '猪八戒'


print('d = ',d , id(d))
print('d2 = ',d2 , id(d2))
```

### 遍历字典

keys() 该方法会返回字典的所有的key。该方法会返回一个序列，序列中保存有字典的所有的键。

```python
d = {'name':'孙悟空','age':18,'gender':'男'}
result = d.keys()
```

```python
for k in d.keys():
	print(k,d[k])
```

values() 该方法会返回一个序列。序列中保存有字典的所有的值。

```python
for v in d.values():
	print(v)
```

items() 该方法会返回字典中所有的项。它会返回一个序列，序列中包含有双值子序列。双值分别是字典中的key和value。

```python
for k,v in d.items():
	print(k, '=', v)
```

```python
print(d.items())
```

## 集合

使用{} 来创建集合。

```python
s = {10,3,5,1,3,5,4,6,32,1,1,1} # <class 'set'>
# s = {[1,2,3],[4,6,7]} TypeError: unhashable type: 'list'
```

使用set()函数来创建集合。

```python
s = set() # 空集合
```

可以通过set()来将序列和字典转换为集合。

```python
s = set([1,2,3,4,5,1,3,2]) # set集合中的元素是不重复的。
s = set('hello')
s = set({'a':1,'b':2,'c':3}) # 使用set()将字典转换为集合时，只会包含字典中的键。
```

使用in 和not in来检查集合中的元素。

```python
print('c' in s)
```

使用len()来获取集合中元素的数量。

```python
print(len(s))
```

add() 向集合中添加元素。

```python
s.add(10)
s.add('a')
```

update() 将一个集合中的元素添加到当前集合中。update() 可以传递序列或字典作为参数，字典只会使用键。

pop() 随机删除并返回一个集合中的元素。

remove()删除集合中的指定元素。

clear()清空集合。

copy()对集合进行浅复制。

### 集合的运算

在对集合做运算时，不会影响原来的集合，而是返回一个运算结果。

```python
s = {1,2,3,4,5}
s2 = {3,4,5,6,7}
# & 交集运算
result = s & s2 # {3,4,5}
# | 并集运算
result = s | s2 # {1,2,3,4,5,6,7}
# - 差集
result = s - s2 # {1,2}
# ^ 异或集 获取只在一个集合中出现的元素
result = s ^ s2 # {1,2,6,7}
```

<= 检查一个集合是否是另一个集合的子集。如果a集合中的元素全部都在b集合中出现，那么a集合就是b集合的子集，b集合就是a集合的超集。

```
a = {1,2,3}
b = {1,2,3,4,5}
result = a <= b  # True 
```

< 检查一个集合是否是另一个集合的真子集。如果超集b中含有子集a中所有的元素，并且b中还含有a中没有的元素，则b是a的真超集，a是b的真子集。

```python
result = {1,2,3} < {1,2,3} # False
result = {1,2,3} < {1,2,3,4} # True 
```

`>=` 检查一个集合是否是另一个的超集。`>` 检查一个集合是否是另一个的真超集。

## 函数

```python
# 定义一个函数
def fn():
	print('这是我的第一个函数')
	print('hello')
	print('今天天气真不错')
# 打印fn
# print(fn) <function fn at 0x03D2B618>
# print(type(fn)) <class 'function'>	

# fn是函数对象  fn()调用函数
# print是函数对象 print()调用函数
# fn()
```

定义形参时，可以为形参指定默认值。指定了默认值以后，如果用户传递了参数则默认值没有任何作用。如果用户没传参数，则默认值生效。

```python
def fn(a = 5, b = 10, c= 20)
	print('a = ',a)
	print('b = ',b)
	print('c = ',c)
    
fn(1,2,3)
```

关键字参数，可以不按照形参定义的顺序去传递，而直接根据参数名去传递参数。

```python
fn(b = 1, c = 2, a = 3)
```

位置参数和关键字参数可以混合使用。混合使用时，必须位置参数写到前面。



函数在调用时，解析器不会检查实参的类型。实参可以传递任意类型的对象。



```python
def fn4(a):
    # 在函数中对形参进行重新赋值，不会影响其他的变量
    # a = 20
    # a是一个列表，尝试修改列表中的元素
    # 如果形参执行的是一个对象，当我们通过形参去修改对象时
    #   会影响到所有指向该对象的变量
    a[0] = 30
    print('a =',a,id(a))
```



不定长参数：在定义函数时，可以在形参前面加一个*，这样这个形参将会获取到所有的实参。它将会将所有的实参保存到一个元组中。

```python
# *a会接受所有的位置实参，并且会将这些实参统一保存到一个元组中（装包）
def fn(*a):
    print("a =",a,type(a))
```

```python
# fn(1,2,3,4,5)
# 带星号的形参只能有一个
# 带星号的参数，可以和其他参数配合使用
# 第一个参数给a，第二个参数给b，剩下的都保存到c的元组中
# def fn2(a,b,*c):
#     print('a =',a)
#     print('b =',b)
#     print('c =',c)
```

可变参数不是必须写在最后，但是注意，带*的参数后的所有参数，必须以关键字参数的形式传递。

*形参只能接收位置参数，而不能接收关键字参数。

**形参可以接收其他的关键字参数，它会将这些参数统一保存到一个字典中。字典的key就是参数的名字，字典的value就是参数的值。

**形参只能有一个，并且必须写在所有参数的最后。

```python
def fn3(b,c,**a):
	print('a =',a,type(a))
	print('b =',b)
	print('c =',c)
	
fn3(b = 1,d = 2,c = 3,e =10,f = 20)
```

传递实参时，也可以在序列类型的参数前添加*，这样它会自动将序列中的元素以此作为参数传递，这里要求序列中的元素的个数必须和形参的个数一致。

```python
# 参数的解包（拆包）
def fn4(a,b,c):
	print('a =',a)
    print('b =',b)
    print('c =',c)
    
# 创建一个元组
t = (10,20,30)

fn4(*t) 

# 创建一个字典
d = {'a':100,'b':200,'c':300}
# 通过 **来对一个字典进行解包操作
fn4(**d)
```

### 返回值

返回值，就是函数执行以后返回的结果，可以通过return来指定函数的返回值。可以直接使用函数的返回值，也可以通过一个变量来接收函数的返回值。

return后边跟什么值，函数就会返回什么值。return后面可以跟任何的对象，返回值甚至可以是一个函数。

如果仅仅写一个return或者不写return，则相当于return None。

在函数中，return后的代码都不会执行，return一旦执行，函数自动结束。

## 文档字符串

help()是Python中的内置函数。通过help()函数可以查询Python中的函数的用法。

语法：help(函数对象)

```python
help(print) # 获取print()函数的使用说明
```



文档字符串（doc str）

在定义函数时，可以在函数内部编写文档字符串，文档字符串就是函数的说明。当我们编写了文档字符串时，就可以通过help()函数来查看函数的说明。文档字符串非常简单，其实直接在函数的第一行写一个字符串就是文档字符串。

```python
def fn(a:int,b:bool,c:str='hello') -> int:
    '''
    这是一个文档字符串的示例

    函数的作用：。。。。。
    函数的参数：
        a，作用，类型，默认值。。。。
        b，作用，类型，默认值。。。。
        c，作用，类型，默认值。。。。
    '''
    return 10

help(fn)
```

## 作用域和命名空间

作用域（scope）指的是变量生效的区域。

```python
b = 20 # 全局变量

def fn():
    a = 10 # a定义在了函数内部，所以他的作用域就是函数内部，函数外部无法访问
    print('函数内部：','a =',a)
    print('函数内部：','b =',b)
```

在Python中一共有两种作用域：

​	全局作用域

​		全局作用域在程序执行时创建，在程序结束时销毁。

​		所有函数以外的区域都是全局作用域

​		在全局作用域中定义变量，都属于全局变量，全局变量可以在程序的任意位置被访问。

​	函数作用域

​		函数作用域在函数调用时创建，在调用结束时销毁。

​		函数每调用一次就会产生一个新的函数作用域。

​		在函数作用域中定义的变量，都是局部变量，它只能在函数内部被访问。

变量的查找

​	当我们使用变量时，会优先在当前作用域中寻找该变量，如果有则使用，如果没有则继续上一级作用域中寻找，如果有则使用，如果依然没有则继续上一级作用域寻找，以此类推，直到找到全局作用域，依然没有找到，则会抛出异常NameError：name 'a' is not dfined

```python
def fn3():
    # a = 10 # 在函数中为变量赋值时，默认都是为局部变量赋值
    # 如果希望在函数内部修改全局变量，则需要使用global关键字，来声明变量
    global a # 声明在函数内部的使用a是全局变量，此时再去修改a时，就是在修改全局的a
    a = 10 # 修改全局变量
    print('函数内部：','a =',a)
```



命名空间（namespace)指的是变量存储的位置，每一个变量都需要存储到指定的命名空间当中。每一个作用域都会有一个它对应的命名空间。全局命名空间，用来保存全局变量。函数命名空间用来保存函数中的变量。命名空间实际上就是一个字典，是一个专门用来存储变量的字典。

locals()用来获取当前作用域的命名空间。如果在全局作用域中调用locals()则获取全局命名空间，如果在函数作用域中调用locals()则获取函数命名空间。返回的是一个字典。

```python
scope = locals()
print(type(scope))
scope['c'] = 1000 # 向字典中添加key-value就相当于在全局中创建了一个变量（一般不建议这么做）
```

```python
def fn4():
    a = 10
    # scope = locals() # 在函数内部调用locals()会获取到函数的命名空间
    # scope['b'] = 20 # 可以通过scope来操作函数的命名空间，但是也是不建议这么做

    # globals() 函数可以用来在任意位置获取全局命名空间
    global_scope = globals()
    # print(global_scope['a'])
    global_scope['a'] = 30
    # print(scope)

fn4()   
```

## 递归