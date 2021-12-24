[TOC]

# kettle入门

## kettle简介

### 企业数据仓库模型

企业数据仓库模型主要包含：数据仓库构建、数据仓库管理、数据分析。

### ETL简介

ETL（Extract-Transform-Load的缩写，即数据抽取、转换、装载的过程），对于企业或行业应用来说，我们经常会遇到各种数据的处理，转换，迁移，所以了解并掌握一种etl工具的使用，必不可少，这里我要学习的ETL工具是Kettle！

### Kettle简介

1. Kettle是一款国外开源的ETL工具，纯java编写，可以在Window、Linux、Unix上运行，绿色无需安装，数据抽取高效稳定。
2. Kettle 中文名称叫水壶，该项目的主程序员MATT 希望把各种数据放到一个壶里，然后以一种指定的格式流出。
3. Kettle这个ETL工具集，它允许你管理来自不同数据库的数据，通过提供一个图形化的用户环境来描述你想做什么，而不是你想怎么做。
4. Kettle中有两种脚本文件，transformation和job，transformation完成针对数据的基础转换，job则完成整个工作流的控制。
5. Kettle(现在已经更名为PDI，Pentaho Data Integration-Pentaho数据集成)。

### Kettle的结构

![image-20211221163607888](kettle.assets/image-20211221163607888.png)

**Kettle的结构-Spoon和Data Integration Server**

- Spoon是构建ETL Jobs和Transformations的工具。Spoon以拖拽的方式图形化设计，能够通过spoon调用专用的数据集成引擎或者集群。

- Data Integration Server是一个专用的ETL Server，它的主要功能有：

![image-20211221163748076](kettle.assets/image-20211221163748076.png)

**Kettle的结构-Enterprise Console**

- Enterprise Console（企业控制台）提供了一个小型的客户端，用于管理Pentaho Data Integration企业版的部署。
- 包括企业版本的证书管理、监控和控制远程Pentaho Data Integration服务器上的活动、分析已登记的作业和转换的动态绩效。

### kettle的核心组件

![image-20211221164211593](kettle.assets/image-20211221164211593.png)

### Kettle概念模型

![image-20211221164243850](kettle.assets/image-20211221164243850.png)

- Kettle的执行分为两个层次：Job（作业）和Transformation（转换）。

## Kettle下载

下载网址：https://community.hitachivantara.com/docs/DOC-1009855

![image-20211221164354175](kettle.assets/image-20211221164354175.png)

### Kettle目录文件

![image-20211221164430458](kettle.assets/image-20211221164430458.png)

classes:生命周期监听、注册表扩展、日志的配置文件
Data Integration.app:数据集成应用
Data Service JDBC Driver: JDBC驱动程序的数据服务
docs:文档
launcher:Kettle的启动配置
lib:支持库 jar包
libswt：Kettle图形库jar
plugins：插件
pwd：kettle集群配置文件
samples：自带例子
simple-jndi:jndi连接配置
system:系统目录
ui:软件界面

![image-20211221164443826](kettle.assets/image-20211221164443826.png)

Carte.bat/Carte.sh:启动集群命令
Encr.bat/encr.sh：kettle提供的加密算法

Import.bat/import.sh：导入命令


Kitchen.bat/kitchen:运行Job的命令

Pan.bat/pan.sh:运行转换的命令

![image-20211221164500425](kettle.assets/image-20211221164500425.png)

set-pentaho-env.bat/set-pentaho-env.sh：设置环境变量脚本

Spoon.bat/spoon.sh：启动KettleUI界面

SpoonDebug.bat/spoonDebug.sh：以Debug的方式运行Kettle

## Kettle部署

### 安装JDK

- 由于Kettle是Java语言开发的，该软件的允许需要Java运行环境的依赖。
- 需要先安装JDK,准备好Java软件的运行环境。

### 配置环境变量

JAVA_HOME：JDK的安装目录

KETTLE_HOME:kettle的解压目录

![image-20211221165032948](kettle.assets/image-20211221165032948.png)



![image-20211221165212373](kettle.assets/image-20211221165212373.png)

### 运行

![image-20211221165240434](kettle.assets/image-20211221165240434.png)

![image-20211221165244429](kettle.assets/image-20211221165244429.png)

## Kettle界面简介

### 工具栏  

![image-20211221165354831](kettle.assets/image-20211221165354831.png)

### 工具图标

![image-20211221165401584](kettle.assets/image-20211221165401584.png)

### 树形列表

![image-20211221165409172](kettle.assets/image-20211221165409172.png)

### 工作区

![image-20211221165415078](kettle.assets/image-20211221165415078.png)

## Kettle快速体验

任务：把数据从CSV文件复制到Excel文件

分析：

![image-20211221165634949](kettle.assets/image-20211221165634949.png)

转换控件：

![image-20211221165722251](kettle.assets/image-20211221165722251.png)

CSV文件输入：

![image-20211221165743680](kettle.assets/image-20211221165743680.png)

Excel输出：

![image-20211221165755661](kettle.assets/image-20211221165755661.png)

转换文件：

![image-20211221165845410](kettle.assets/image-20211221165845410.png)

运行：

![image-20211221165906485](kettle.assets/image-20211221165906485.png)

## 执行结果

### 日志  

![image-20211221170345304](kettle.assets/image-20211221170345304.png)

### 步骤度量 

![image-20211221170404850](kettle.assets/image-20211221170404850.png)

### Metrics

中文意思：度量、指标

![image-20211221170510011](kettle.assets/image-20211221170510011.png)

### Preview data

![image-20211221170715424](kettle.assets/image-20211221170715424.png)

## Kettle核心概念

### 可视化编程

- Kettle可以被归类为可视化编程语言(Visula Programming Languages,VPL),因为Kettle可以使用图形化的方式定义复杂的ETL程序和工作流。
- Kettle里的图就是转换和作业。
- 可视化编程一直是Kettle里的核心概念，它可以让你快速构建复杂的ETL作业和减低维护工作量。它通过隐藏很多技术细节，使IT领域更贴近于商务领域。

### 转换

- 转换(transaformation)是ETL解决方案中最主要的部分，它处理抽取、转换、加载各种对数据行的操作。
- 转换包含一个或多个步骤(step)，如读取文件、过滤数据行、数据清洗或将数据加载到数据库。
- 转换里的步骤通过跳(hop)来连接，跳定义一个单向通道，允许数据从一个步骤向另一个步骤流动。
- 在Kettle里，数据的单位是行，数据流就是数据行从一个步骤到另一个步骤的移动。
- 数据流有的时候也被称之为记录流。

## 步骤

- 步骤（控件）是转换里的基本的组成部分。

- 快速入门的案例中就存在两个步骤，“CSV文件输入”和“Excel输出”。

- 一个步骤有如下几个关键特性：
  ①步骤需要有一个名字，这个名字在转换范围内唯一。
  ②每个步骤都会读、写数据行(唯一例外是“生成记录”步骤，该步骤只写数据)。
  ③步骤将数据写到与之相连的一个或多个输出跳，再传送到跳的另一端的步骤。
  ④大多数的步骤都可以有多个输出跳。一个步骤的数据发送可以被被设置为分发和复制，分发是目标步骤轮流接收记录，复制是所有的记录被同时发送到所有的目标步骤。

![image-20211221171600299](kettle.assets/image-20211221171600299.png)

![image-20211221171630707](kettle.assets/image-20211221171630707.png)

### 跳

![image-20211221171700502](kettle.assets/image-20211221171700502.png)

- 跳就是步骤之间带箭头的连线，跳定义了步骤之间的数据通路。

- 跳实际上是两个步骤之间的被称之为行集的数据行缓存（行集的大小可以在转换的设置里定义）。

  ![image-20211221171853310](kettle.assets/image-20211221171853310.png)

- 当行集满了，向行集写数据的步骤将停止写入，直到行集里又有了空间。

- 当行集空了，从行集读取数据的步骤停止读取，直到行集里又有可读的数据行。

### 数据行

#### 数据类型

- 数据以数据行的形式沿着步骤移动。一个数据行是零到多个字段的集合，字段包含下面几种数据类型。
  ①String:字符类型数据
  ②Number:双精度浮点数。
  ③Integer:带符号长整型（64位）。
  ④BigNumber:任意精度数据。
  ⑤Date:带毫秒精度的日期时间值。
  ⑥Boolean:取值为true和false的布尔值。
  ⑦Binary:二进制字段可以包含图像、声音、视频及其他类型的二进制数据。

![image-20211221173202739](kettle.assets/image-20211221173202739.png)

#### 元数据

每个步骤在输出数据行时都有对字段的描述，这种描述就是数据行的元数据。
通常包含下面一些信息。
①名称：行里的字段名应用是唯一的。
②数据类型：字段的数据类型。
③格式：数据显示的方式，如Integer的#、0.00。
④长度：字符串的长度或者BigNumber类型的长度。
⑤精度：BigNumber数据类型的十进制精度。
⑥货币符号：￥
⑦小数点符号:十进制数据的小数点格式。不同文化背景下小数点符号是不同的，一般是点（.）或逗号（，）。
⑧分组符号：数值类型数据的分组符号，不同文化背景下数字里的分组符号也是不同的，一般是点（.）或逗号（，）或单引号（’）

![image-20211221173131539](kettle.assets/image-20211221173131539.png)

### 并行

- 跳的这种基于行集缓存的规则允许每个步骤都是由一个独立的线程运行，这样并发程度最高。这一规则也允许数据以最小消耗内存的数据流的方式来处理。在数据仓库里，我们经常要处理大量数据，所以这种并发低消耗内存的方式也是ETL工具的核心需求。
- 对于kettle的转换，不可能定义一个执行顺序，因为所有步骤都以并发方式执行：当转换启动后，所有步骤都同时启动，从它们的输入跳中读取数据，并把处理过的数据写到输入跳，直到输入跳里不再有数据，就中止步骤的运行。当所有的步骤都中止了，整个转换就中止了。 （要与数据流向区分开）

- 如果你想要一个任务沿着指定的顺序执行，那么就要使用后面所讲的“作业”！

# kettle输入控件

## CSV文件输入

### 输入简介

![image-20211221173522310](kettle.assets/image-20211221173522310.png)

![image-20211221173535787](kettle.assets/image-20211221173535787.png)

### CSV文件输入

- CSV文件是一种带有固定格式的文本文件。

![image-20211221173559674](kettle.assets/image-20211221173559674.png)

![image-20211221173603029](kettle.assets/image-20211221173603029.png)

![image-20211221173632848](kettle.assets/image-20211221173632848.png)

![image-20211221173812938](kettle.assets/image-20211221173812938.png)

![image-20211221173826339](kettle.assets/image-20211221173826339.png)

![image-20211221173839999](kettle.assets/image-20211221173839999.png)

![image-20211221173959226](kettle.assets/image-20211221173959226.png)

![image-20211221174011386](kettle.assets/image-20211221174011386.png)

![image-20211221174023147](kettle.assets/image-20211221174023147.png)

![image-20211221174037703](kettle.assets/image-20211221174037703.png)

![image-20211221174050152](kettle.assets/image-20211221174050152.png)

## 文本文件输入

- 提取日志信息的数据是开发常见的操作，日志信息基本都是文本类型。

![image-20211221180041532](kettle.assets/image-20211221180041532.png)

![image-20211221180206317](kettle.assets/image-20211221180206317.png)

![image-20211221180221563](kettle.assets/image-20211221180221563.png)

![image-20211221180230875](kettle.assets/image-20211221180230875.png)

任务：把文本文件的数据复制到excel文件。

## Excel输入

微软的Excel目前有两种后缀名的文件分别为：xls和xlsx。
xls:2007年之前
xlsx:2007年之后

![image-20211222093615705](kettle.assets/image-20211222093615705.png)

![image-20211222093633049](kettle.assets/image-20211222093633049.png)

![image-20211222093644289](kettle.assets/image-20211222093644289.png)

![image-20211222093657063](kettle.assets/image-20211222093657063.png)

任务：把Excel文件的数据复制到Excel文件

分析：

- 输入：Excel输入
- 输出：Excel输出

![image-20211222093852605](kettle.assets/image-20211222093852605.png)

## 多文件合并

- 数据往往也是以多个文件的形式出现，有的数据还会分散在多个子文件夹。
- 所以合并数据也是开发中非常常见的操作。

![image-20211222102054772](kettle.assets/image-20211222102054772.png)

任务：读取input目录下以04多文件合并开头的所有Excel数据合并到一个Excel。

分析：

- Excel输入
- Excel输出

## Get data from XML

### xml

- XML 指可扩展标记语言（EXtensible Markup Language）, XML 被设计用来传输和存储数据。

```xml
<messages>
	<note id="501">
		<to>George</to>
		<from>John</from>
		<heading>Reminder</heading>
		<body>Dont't forget the meeting!</body>
	</note>
	<note id="502">
		<to>John</to>
		<from>George</from>
		<heading>Re: Reminder</heading>
		<body>I will not</body>
	</note>
</messages>
```

### XPath

- XPath即为XML路径语言（XML Path Language），它是一种用来确定XML文档中某部分位置的语言。
- XPath基于XML的树状结构，提供在数据结构树中找寻节点的能力。

- 选取节点 XPath 使用路径表达式在 XML 文档中选取节点。节点是通过沿着路径或者 step 来选取的。

- 下面列出了最有用的路径表达式：

| 表达式   | 描述                                                       |
| -------- | ---------------------------------------------------------- |
| nodename | 选取此节点的所有子节点                                     |
| /        | 从根节点选取                                               |
| //       | 从匹配选择的当前节点选择文档中的节点，而不考虑它们的位置。 |
| .        | 选取当前节点                                               |
| ..       | 选取当前节点的父节点                                       |
| @        | 选取属性                                                   |

**XPath-路径表达式-示例**

| 路径表达式     | 结果                                                         |
| -------------- | ------------------------------------------------------------ |
| bookstore      | 选取bookstore元素的所有子节点                                |
| /bookstore     | 选取根元素bookstore。注释：假如路径起始于正斜杠（/），则此路径始终代表到某元素的绝对路径！ |
| bookstore/book | 选取属于bookstore的子元素的所有book元素                      |
| //book         | 选取所有book子元素，而不管它们在文档中的位置                 |
| bookstore/book | 选择属于bookstore元素的后代的所有book元素，而不管它们位于bookstore之下的什么位置。 |
| //@lang        | 选取名为lang的所有属性。                                     |

### Get data from XML

![image-20211222111407469](kettle.assets/image-20211222111407469.png)

![image-20211222111418087](kettle.assets/image-20211222111418087.png)

![image-20211222111427412](kettle.assets/image-20211222111427412.png)

![image-20211222111437825](kettle.assets/image-20211222111437825.png)

![image-20211222111446010](kettle.assets/image-20211222111446010.png)

![image-20211222111457044](kettle.assets/image-20211222111457044.png)

![image-20211222111506683](kettle.assets/image-20211222111506683.png)

任务：从xml文件中提取testDescription、rowID、v1、v2数据保存到excel中

循环读取的路径/AllRows/Rows/Row

分析：

- 输入：Get data from XML
- 输出：Excel输出

![image-20211222111611482](kettle.assets/image-20211222111611482.png)

## JSON Input

### JSON

- JSON(JavaScript Object Notation, JS 对象简谱) 是一种轻量级的数据交换格式。
- JSON核心概念：数组、对象、属性
  - 数组：[ ]
  - 对象：{ }
  - 属性：key:value

### JSONPath

- JSONPath类似于XPath在xml文档中的定位，JsonPath表达式通常是用来路径检索或设置Json的。
- 其表达式可以接受“dot–notation”（点记法）和“bracket–notation”（括号记法）格式

- 点记法：$.store.book[0].title
- 括号记法：$ [ ‘store’ ] [ ‘book’ ] [0] [ ‘title’ ]

**JSONPath-操作符**

![image-20211222112121845](kettle.assets/image-20211222112121845.png)

![image-20211222112157620](kettle.assets/image-20211222112157620.png)

![image-20211222112223051](kettle.assets/image-20211222112223051.png)

### JSON Input

![image-20211222112301555](kettle.assets/image-20211222112301555.png)

![image-20211222134610186](kettle.assets/image-20211222134610186.png)

任务：从json文件提取id、field和value数据到excel

分析：

- 输入：JSON Input （两次）
- 输出：Excel输出

![image-20211222134721480](kettle.assets/image-20211222134721480.png)

## 生成记录

### 静态数据

- 数据仓库中绝大多数的数据都是业务系统生成的动态数据，但是其中一部分维度数据不是动态的，比如：日期维度。
- 静态维度数据就可以提前生成。

![image-20211222135837757](kettle.assets/image-20211222135837757.png)

![image-20211222135921194](kettle.assets/image-20211222135921194.png)

任务：往excel文件中插入1000条记录：id为1，name为itheima，age为18

分析：

- 输入：生成记录
- 输出：Excel输出

![image-20211222140003381](kettle.assets/image-20211222140003381.png)

## 表输入

### 数据库驱动

- 数据库驱动是不同数据库开发商（比如oracle mysql等）为了某一种开发语言环境（比如java）能够实现统一的数据库调用而开发的一个程序，他的作用相当于一个翻译人员。

![image-20211222140252089](kettle.assets/image-20211222140252089.png)

### MySQL和Navicat for MySQL

- 根据MySQL数据库不同的版本，下载对应的Java驱动程序。

**MySQL驱动下载**：https://dev.mysql.com/downloads/connector/j/

![image-20211222140435819](kettle.assets/image-20211222140435819.png)

![image-20211222140501731](kettle.assets/image-20211222140501731.png)

### 表输入

![image-20211222140633097](kettle.assets/image-20211222140633097.png)

![image-20211222140648818](kettle.assets/image-20211222140648818.png)

![image-20211222140659743](kettle.assets/image-20211222140659743.png)

![image-20211222140709829](kettle.assets/image-20211222140709829.png)

![image-20211222140721132](kettle.assets/image-20211222140721132.png)

![image-20211222140741575](kettle.assets/image-20211222140741575.png)

![image-20211222140805871](kettle.assets/image-20211222140805871.png)

任务：从MySql数据库的mysql库的user表获取所有数据插入到Excel文件

分析：

- 输入：表输入
- 输出：Excel输出

![image-20211222140845408](kettle.assets/image-20211222140845408.png)

# kettle输出控件

## Excel输出

### 输出

![image-20211222141631501](kettle.assets/image-20211222141631501.png)

- 输出是转换里面的第二个分类。
- 输出属于ETL的L，L就是Load加载。

###  Excel

- 微软的Excel目前有两种后缀名的文件分别为：xls和xlsx。
  - xls:2007年之前
  - xlsx:2007年之后

### Excel输出

![image-20211222142001582](kettle.assets/image-20211222142001582.png)

![image-20211222142027505](kettle.assets/image-20211222142027505.png)

![image-20211222142047395](kettle.assets/image-20211222142047395.png)

任务：从mysql数据库的mysql库的user表读取数据插入到excel的.xls和.xlsx的文件中

分析：

- 输入：表输入
- 输出：Excel输出和Microsoft Excel输出

![image-20211222142122306](kettle.assets/image-20211222142122306.png)

## 文本文件输出

- 数据操作常见的格式是：TXT和CSV。

![image-20211222143105829](kettle.assets/image-20211222143105829.png)

任务：从mysql数据库的mysql库的user表读取数据插入到.txt和.csv的文件中

分析：

- 输入：表输入
- 输出：文本文件输出

![image-20211222143202296](kettle.assets/image-20211222143202296.png)

## SQL文件输出

- SQL文件输出可以导出数据库表的结构和数据。

![image-20211222144049578](kettle.assets/image-20211222144049578.png)

任务：获取mysql库的user表的结构和数据的SQL文件

分析：

- 输入：表输入
- 输出：SQL文件输出

![image-20211222144135626](kettle.assets/image-20211222144135626.png)

## 表输出

- 表输出就是把数据写入到指定的表！

![image-20211222152047163](kettle.assets/image-20211222152047163.png)

![image-20211222152109874](kettle.assets/image-20211222152109874.png)

![image-20211222152119239](kettle.assets/image-20211222152119239.png)

![image-20211222152227739](kettle.assets/image-20211222152227739.png)

任务：从excel中读取id,name,age字段的数据，并写入到mysql数据库的04table-output表中

分析：

- 输入：Excel输入
- 输出：表输出

![image-20211222152304568](kettle.assets/image-20211222152304568.png)

## 更新

- 更新就是把数据库已经存在的记录与数据流里面的记录进行比对，如果不同就进行更新。
- 注意：如果记录不存在，则会出现错误！

![image-20211222153601742](kettle.assets/image-20211222153601742.png)

![image-20211222153908291](kettle.assets/image-20211222153908291.png)

![image-20211222153919642](kettle.assets/image-20211222153919642.png)

![image-20211222153932195](kettle.assets/image-20211222153932195.png)

任务：从Excel读取数据，并把数据更新到test数据库的04table-output中

分析：

- 输入：Excel输入
- 输出：更新

![image-20211222154018272](kettle.assets/image-20211222154018272.png)

## 插入更新

- 插入更新就是把数据库已经存在的记录与数据流里面的记录进行比对，如果不同就进行更新。
- 如果记录不存在，则会插入数据！

![image-20211222154935432](kettle.assets/image-20211222154935432.png)

![image-20211222154947099](kettle.assets/image-20211222154947099.png)

![image-20211222154955913](kettle.assets/image-20211222154955913.png)

任务：从excel中读取id,name,age字段的数据，并插入更新到mysql数据库的04table-output表中

分析：

- 输入：Excel输入
- 输出：插入更新

![image-20211222155043344](kettle.assets/image-20211222155043344.png)

## 删除

### 自定义常量数据

- 自定义常量数据就是生成key-value形式的常量数据。

![image-20211222155323566](kettle.assets/image-20211222155323566.png)

![image-20211222155338742](kettle.assets/image-20211222155338742.png)

### 删除

- 删除就是删除数据库表中指定条件的数据。

![image-20211222155413576](kettle.assets/image-20211222155413576.png)

![image-20211222155425776](kettle.assets/image-20211222155425776.png)

任务：从mysql数据库04table-output表中删除指定id为1的数据

分析：

- 输入：自定义常量数据
- 输出：删除

![image-20211222155501344](kettle.assets/image-20211222155501344.png)

# kettle转换控件

## Concat fields

### 转换

![image-20211222160349334](kettle.assets/image-20211222160349334.png)

- 转换属于ETL的T，T就是Transform清洗、转换。
- ETL三个部分中，T花费时间最长,是“一般情况下这部分工作量是整个ETL的2/3。

### Concat fields

- Concat fields就是多个字段连接起来形成一个新的字段。

![image-20211222160920043](kettle.assets/image-20211222160920043.png)

![image-20211222160932013](kettle.assets/image-20211222160932013.png)

![image-20211222160942080](kettle.assets/image-20211222160942080.png)

任务：从Excel中获取FirstName和LastName并把FirstName和LastName连接起来，输出到Excel。

分析：

- 输入：表输入
- 转换：Concat fields
- 输出：Microsoft Excel输出

![image-20211222161026923](kettle.assets/image-20211222161026923.png)

## 值映射

- 值映射就是把字段的一个值映射成其他的值。

- 在数据质量规范上使用非常多，比如很多系统对应性别gender字段的定义不同。
  系统1：1 男、2女
  系统2：f 男、m 女
  数据仓库统一为：female 男、male女

![image-20211222162346779](kettle.assets/image-20211222162346779.png)

![image-20211222162356645](kettle.assets/image-20211222162356645.png)

![image-20211222162407726](kettle.assets/image-20211222162407726.png)

![image-20211222162421739](kettle.assets/image-20211222162421739.png)

任务：从Excel中读取数据，并把gender里面的f和m转换为female和male,写入到Excel文件。

分析：

- 输入：Excel输入
- 转换：值映射
- 输出：Microsoft Excel输出

![image-20211222162501949](kettle.assets/image-20211222162501949.png)

## 增加常量

- 增加常量就是在本身的数据流里面添加一列数据，该列的数据都是相同的值。

![image-20211222162636526](kettle.assets/image-20211222162636526.png)

任务：从Excel读取数据，增加一个新列language值为en，把数据保存在Excel中。

分析：

- 输入：Excel输入
- 转换：增加常量
- 输出：Microsoft Excel输出

![image-20211222162724185](kettle.assets/image-20211222162724185.png)

## 增加序列

- 增加序列是给数据流添加一个序列字段。

![image-20211222163524085](kettle.assets/image-20211222163524085.png)

![image-20211222163536235](kettle.assets/image-20211222163536235.png)

![image-20211222163544957](kettle.assets/image-20211222163544957.png)

![image-20211222163554167](kettle.assets/image-20211222163554167.png)

任务：从Excel读取数据，并添加序列，把数据保存到Excel。

分析：

- 输入：Excel输入
- 转换：增加序列
- 输出：Microsoft Excel输出

![image-20211222163633261](kettle.assets/image-20211222163633261.png)

## 字段选择

- 字段选择是从数据流中选择字段、改变名称、修改数据类型。

![image-20211222164906456](kettle.assets/image-20211222164906456.png)

![image-20211222164917094](kettle.assets/image-20211222164917094.png)

![image-20211222164929116](kettle.assets/image-20211222164929116.png)

任务：从Excel读取数据，移除language和country，并把phone列名该为telphone，id列名改为key，把gender列名该为sex。

分析：

- 输入：Excel输入
- 转换：字段选择
- 输出：Microsoft Excel输出

![image-20211222165014746](kettle.assets/image-20211222165014746.png)

## 计算器

计算器是一个函数集合来创建新的字段，还可以设置字段是否移除（临时字段）。

![image-20211222170645418](kettle.assets/image-20211222170645418.png)

任务：从Excel中读取数据，生成name,quarter,week_of_day,account列，把数据存在到Excel中间中。

分析：

- 输入：Excel输入
- 转换：计算器
- 输出：Microsoft Excel输出

![image-20211222170741585](kettle.assets/image-20211222170741585.png)

## 字符串-剪切-操作-替换

### 剪切字符串

- 剪切字符串是指定输入流字段裁剪的位置剪切出新的字段。

![image-20211222170942396](kettle.assets/image-20211222170942396.png)

### 字符串替换

- 字符串替换是指定搜索内容和替换内容，如果输入流的字段匹配上搜索内容就进行替换生成新字段。

![image-20211222171024496](kettle.assets/image-20211222171024496.png)

### 字符串操作

- 字符串操作是去除字符串两端的空格和大小写切换，并生成新的字段。

![image-20211222172003342](kettle.assets/image-20211222172003342.png)

任务：从Excel中读取数据，获取title的首位字符，生成title_begin字段，把description中的1111替换为itheima生成desc字段，去除author两边的空格，字符变大写，生成author_update，保存到Excel。

分析：

- 输入：Excel输入
- 转换：剪切字符串、字符串替换、字符串操作
- 输出：Microsoft Excel输出

![image-20211222172133377](kettle.assets/image-20211222172133377.png)

## 去除重复记录+排序记录

### 去除重复记录

- 去除重复记录是去除数据流里面相同的数据行。
- 注意：必须先对数据流进行排序！

![image-20211222173231166](kettle.assets/image-20211222173231166.png)

### 排序记录

- 排序记录是按照指定的字段的升序或降序对数据流排序。

![image-20211222173508142](kettle.assets/image-20211222173508142.png)

![image-20211222173522183](kettle.assets/image-20211222173522183.png)

任务：从Excel中读取数据，去除重复的数据，并保存到Excel。

分析：

- 输入：Excel输入
- 转换：排序记录、去除重复记录
- 输出：Microsoft Excel输出

![image-20211222173609851](kettle.assets/image-20211222173609851.png)

## 唯一行（哈希值）

- 唯一行（哈希值）就是删除数据流重复的行（不包含排序）。
- 注意：唯一行（哈希值）和（排序记录+去除重复记录）效果一样的，但是实现的原理不同！
- 唯一行（哈希值）执行的效率会高一些！

![image-20211222174654706](kettle.assets/image-20211222174654706.png)

任务：从Excel中读取数据，去除重复的数据，并保存到Excel。

分析：

- 输入：Excel输入
- 转换：唯一行（哈希值）
- 输出：Microsoft Excel输出

![image-20211222174752284](kettle.assets/image-20211222174752284.png)

## 拆分字段

- 拆分字段是把字段按照分隔符拆分成两个或多个字段。
- 注意：拆分字段后，原字段就不存在于数据流中！

![image-20211222175551761](kettle.assets/image-20211222175551761.png)

![image-20211222175603107](kettle.assets/image-20211222175603107.png)

![image-20211222175612149](kettle.assets/image-20211222175612149.png)

任务：从Excel读取数据，把name拆分为FirstName和LastName,并保存数据到Excel。

分析：

- 输入：Excel输入
- 转换：拆分字段
- 输出：Microsoft Excel输出

![image-20211222175646422](kettle.assets/image-20211222175646422.png)

## 列拆分为多行

- 列拆分为多行就是把指定分隔符的字段进行拆分为多行。

![image-20211223094430198](kettle.assets/image-20211223094430198.png)

![image-20211223094539012](kettle.assets/image-20211223094539012.png)

任务：从csv读取数据，把hobby列拆分为多行，并保存在Excel。

分析：

- 输入：CSV文件输入
- 转换：列拆分为多行
- 输出：Microsoft Excel输出

![image-20211223094630339](kettle.assets/image-20211223094630339.png)

## 列转行

- 列转行就是如果数据一列有相同的值，按照指定的字段，把多行数据转换为一行数据。
- 去除一些原来的列名，把一列数据变为字段。
- 注意：列转行之前数据流必须进行排序！

![image-20211223102117655](kettle.assets/image-20211223102117655.png)

![image-20211223102407636](kettle.assets/image-20211223102407636.png)

任务：从Excel中读取数据，按照姓名进行分组，把星期、工作小时从列转为行，并保存在Excel中。

分析：

- 输入：Excel输入
- 转换：排序、列转行
- 输出：Microsoft Excel输出

![image-20211223102505693](kettle.assets/image-20211223102505693.png)

## 行转列

- 行转列就是把数据字段的字段名转换为一列，把数据行变为数据列。

![image-20211223102727084](kettle.assets/image-20211223102727084.png)

![image-20211223103554732](kettle.assets/image-20211223103554732.png)

任务：从Excel读取数据，把星期工作小时行转为星期列和工作小时列，把数据保存到Excel。

分析：

- 输入：Excel输入
- 转换：行转列
- 输出：Microsoft Excel输出

![image-20211223103659562](kettle.assets/image-20211223103659562.png)

## 行扁平化

- 行扁平化就是把同一组的多行数据合并成为一行。
- 注意：
  1. 只有数据流的同类数据数据行记录一致的情况才可使用！
  2. 数据流必须进行排序，否则结果会不正确！

![image-20211223104251666](kettle.assets/image-20211223104251666.png)

![image-20211223104412162](kettle.assets/image-20211223104412162.png)

任务：从Excel读取数据，把数据进行行扁平化处理，存储在Excel。

分析：

- 输入：Excel输入
- 转换：排序记录、行扁平化
- 输出：Microsoft Excel输出

![image-20211223104457501](kettle.assets/image-20211223104457501.png)

# kettle应用控件

## 替换NULL值

### 应用

![image-20211223110206079](kettle.assets/image-20211223110206079.png)

- 应用是转换里面的第五个分类。
- 应用都是一些工具类。

### 替换NULL值

- 替换NULL值就是把null转换为其它的值。
- NULL值不好进行数据分析

![image-20211223110329212](kettle.assets/image-20211223110329212.png)

任务：从Excel中读取数据，age列的空值值使用28来替换，并保存在Excel。

分析：

- 输入：Excel输入
- 应用：替换NULL值
- 输出：Microsoft Excel输出

![image-20211223110426465](kettle.assets/image-20211223110426465.png)

## 写日志

- 写日志主要是在调试的时候使用，把日志信息打印到日志窗口。

![image-20211223110748647](kettle.assets/image-20211223110748647.png)

![image-20211223110855956](kettle.assets/image-20211223110855956.png)

任务：从Excel读取数据，向日志输出窗口打印出所有的数据信息。

分析：

- 输入：Excel输入
- 应用：写日志

![image-20211223110946788](kettle.assets/image-20211223110946788.png)

## 发送邮件

- 发送邮件就是执行成功、失败、其它某种情景给相关人员发送邮件。
  
- 注意：
  1. 只有企业邮箱才可以！个人邮箱不行！
  2. 并且需要在邮件设置中开通客户端授权码！

![image-20211223111716446](kettle.assets/image-20211223111716446.png)

![image-20211223111803743](kettle.assets/image-20211223111803743.png)

![image-20211223111826207](kettle.assets/image-20211223111826207.png)

![image-20211223111837804](kettle.assets/image-20211223111837804.png)

任务：发送一封邮件。

分析：

- 应用：发送邮件

![image-20211223111918448](kettle.assets/image-20211223111918448.png)

# kettle流程控件

## Switch-case

### 流程

![image-20211223112355531](kettle.assets/image-20211223112355531.png)

- 流程是转换里面的第六个分类。
- 流程主要用来控制数据流程和数据流向。

### Switch-case

- Switch/case让数据流从一路到多路。

![image-20211223112532959](kettle.assets/image-20211223112532959.png)

![image-20211223112726717](kettle.assets/image-20211223112726717.png)

任务：从Excel输入读取数据，按sex进行数据分类，把女性、男性、保密分别保存不同的Excel文件里面。

- 1表示男性
- 0表示女性
- 2表示保密

分析：

- 输入：Excel输入
- 流程：Switch/case
- 输出：Microsoft Excel输出

![image-20211223112853157](kettle.assets/image-20211223112853157.png)

## 过滤记录

- 过滤记录让数据流从一路到两路。

![image-20211223140154182](kettle.assets/image-20211223140154182.png)

![image-20211223140255763](kettle.assets/image-20211223140255763.png)

任务：从Excel读取数据，分离出code列为空的数据，分别保存到不同的Excel文件。

分析：

- 输入：Excel输入
- 流程：过滤记录
- 输出：Microsoft Excel输出

![image-20211223140346604](kettle.assets/image-20211223140346604.png)

## 空操作

- 空操作一般作为数据流的终点。（在kettle的sample中经常使用，但是实际开发中很少使用）

![image-20211223140919894](kettle.assets/image-20211223140919894.png)

任务：从Excel读取数据，分离code为空的数据，空数据不执行任何操作，不为空的数据保存到Excel。

分析：

- 输入：Excel输入
- 流程：过滤记录、空操作
- 输出：Microsoft Excel输出

![image-20211223140956714](kettle.assets/image-20211223140956714.png)

## 中止

- 中止是数据流的终点，如果有数据到这里，将会报错。
- 用来校验数据的时候使用。

任务：从Excel中读取数据，过滤去code列不为空的数据，不为空的数据保存在Excel，如果出现为空的数据就停止转换。

分析：

- 输入：Excel输入
- 流程：过滤记录、中止
- 输出：Microsoft Excel输出

![image-20211223142536274](kettle.assets/image-20211223142536274.png)

# kettle查询控件

## HTTP client

### 查询

![image-20211223142757446](kettle.assets/image-20211223142757446.png)

- 查询是转换里面的第七个分类。
- 查询是用来查询数据源里的数据并合并到主数据流中。

### HTTP client

- HTTP client是使用GET的方式提交请求，获取返回的页面内容。

![image-20211223144728861](kettle.assets/image-20211223144728861.png)

![image-20211223144715032](kettle.assets/image-20211223144715032.png)

**自定义常量数据**

- 自定义常量数据是用来生成一些不变的数据。

![image-20211223144812057](kettle.assets/image-20211223144812057.png)

![image-20211223144825459](kettle.assets/image-20211223144825459.png)

任务：

- 从网络上获取xml，解析出ProductID、ProductName、SupplierID、CategoryID，保存到Excel中
- 地址：http://services.odata.org/V3/Northwind/Northwind.svc/Products/

分析：

- 输入：Excel输入
- 查询：HTTP client
- 输入：Get data from XML
- 输出：Microsoft Excel输出

![image-20211223144933920](kettle.assets/image-20211223144933920.png)

## 数据库查询

- 数据库查询就是数据库里面的左连接。

- 左连接就是两张表执行左关联查询，把左边的表数据全部查询出来。

  ![image-20211223151325956](kettle.assets/image-20211223151325956.png)

任务：从employees表中读取数据，根据dep_id从departments表获取dep_name，保存到Excel中。

分析：

- 输入：表输入
- 查询：数据库查询
- 输出：Microsoft Excel输出

![image-20211223151414923](kettle.assets/image-20211223151414923.png)

MySQL表：

```sql
/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2019-01-21 10:31:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for employees
-- ----------------------------
DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `dep_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employees
-- ----------------------------
INSERT INTO `employees` VALUES ('1', '张三', '1');
INSERT INTO `employees` VALUES ('2', '李四', '2');
INSERT INTO `employees` VALUES ('3', '王五', '3');

```

```sql
/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2019-01-21 10:30:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for departments
-- ----------------------------
DROP TABLE IF EXISTS `departments`;
CREATE TABLE `departments` (
  `id` int(11) DEFAULT NULL,
  `dep_name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of departments
-- ----------------------------
INSERT INTO `departments` VALUES ('1', '开发');
INSERT INTO `departments` VALUES ('2', '测试');
INSERT INTO `departments` VALUES ('3', '产品');
INSERT INTO `departments` VALUES ('4', '运维');

```

## 数据库连接

- 数据库连接可以执行两个数据库的查询，和单参数的表输入。

![image-20211223153048888](kettle.assets/image-20211223153048888.png)

任务：从departments表中读取数据，连接到另外一个数据库的employees，把数据保存到Excel。

分析：

- 输入：表输入
- 查询：数据库连接
- 输出：Microsoft Excel输出

![image-20211223153158074](kettle.assets/image-20211223153158074.png)

## 流查询

- 流查询在查询前把数据都加载到内存中，并且只能进行等值查询。

![image-20211223154617598](kettle.assets/image-20211223154617598.png)

任务：从Excel读取employess和departments的数据，根据dep_id来查询dep_name，把数据保存到Excel。

分析：

- 输入：Excel输入
- 查询：流查询
- 输出：Microsoft Excel输出

![image-20211223154710290](kettle.assets/image-20211223154710290.png)

# kettle连接控件

## 合并记录

### 连接

![image-20211223155118531](kettle.assets/image-20211223155118531.png)

- 连接是转换里面的第八个分类。
- 连接是结果集通过关键字进行连接。

### 合并记录

- 合并记录是用于将两个不同来源的数据合并，这两个来源的数据分别为旧数据和新数据，该步骤将旧数据和新数据按照指定的关键字匹配、比较、合并。
- 需要设置的参数：
  1. 旧数据来源：旧数据来源的步骤
  2. 新数据来源：新数据来源的步骤

- 标志字段：设置标志字段的名称，标志字段用于保存比较的结果，比较结果有下列几种。
  1. “identical” – 旧数据和新数据一样
  2. “changed” – 数据发生了变化;
  3. “new” – 新数据中有而旧数据中没有的记录
  4. “deleted” –旧数据中有而新数据中没有的记录
- 关键字段：用于定位两个数据源中的同一条记录。
- 比较字段：对于两个数据源中的同一条记录中，指定需要比较的字段。
  合并后的数据将包括旧数据来源和新数据来源里的所有数据，对于变化的数据，使用新数据代替旧数据，同时在结果里用一个标示字段，来指定新旧数据的比较结果。
- 注意：
  旧数据和新数据需要事先按照关键字段排序。
  旧数据和新数据要有相同的字段名称。

![image-20211223160457418](kettle.assets/image-20211223160457418.png)

任务：从Excel读取新数据和旧数据，合并数据，标记处new、delete、changed、identical,把数据保存到Excel。

分析：

- 输入：Excel输入
- 连接：合并记录
- 输出：Microsoft Excel输出

![image-20211223160546667](kettle.assets/image-20211223160546667.png)

## 记录关联（笛卡尔积）

- 记录关联就是对两个数据流进行笛卡尔积操作。

![image-20211223162235569](kettle.assets/image-20211223162235569.png)

![image-20211223162255352](kettle.assets/image-20211223162255352.png)

任务：从Excel读取两位和三位数，完成两位数和三位数的组合（笛卡尔积）,把结果保存在Excel。

分析：

- 输入：Excel输入
- 连接：记录关联
- 输出：Microsoft Excel输出

![image-20211223162338194](kettle.assets/image-20211223162338194.png)

## 记录集连接

- 记录集连接就像数据库的左连接、右连接、内连接、外连接。
- 注意：在进行记录集连接之前，应该要对记录集进行排序。

![image-20211223164136059](kettle.assets/image-20211223164136059.png)

![image-20211223164233257](kettle.assets/image-20211223164233257.png)

任务：从Excel中读取employees和departments数据，进行内关键，左关联，右关联，全关联，把数据保存到Excel。

分析：

- 输入：Excel输入
- 转换：排序记录
- 连接：记录集连接
- 输出：Microsoft Excel输出

![image-20211223164320368](kettle.assets/image-20211223164320368.png)

# kettle统计控件

## 分组

### 统计

![image-20211223164509945](kettle.assets/image-20211223164509945.png)

- 统计是提供数据的采样和统计功能。

### 分组

- 分组是按照某一个或某几个进行分组，同时可以将其余字段按照某种规则进行合并。
- 注意：
  分组之前数据应该进行排序！

![image-20211223165207655](kettle.assets/image-20211223165207655.png)

任务：从Excel读取数据，按照group进行分组统计，把结果保存到Excel。

分析：

- 输入：Excel输入
- 统计：分组
- 输出：Microsoft Excel输出

![image-20211223165255391](kettle.assets/image-20211223165255391.png)

# kettle映射控件

## 映射

### 映射

![image-20211223165632080](kettle.assets/image-20211223165632080.png)

- 映射是用来定义子转换，便于封装和重用。

### 映射（子转换）

- 映射（子转换）是用来配置子转换，对子转换进行调用的一个步骤。

![image-20211223170106966](kettle.assets/image-20211223170106966.png)

### 映射输入规范

- 映射输入规范是输入字段，由调用的转换输入。

![image-20211223170206300](kettle.assets/image-20211223170206300.png)

### 映射输出规范

- 映射输出规范是向调用的转换输出所有列，不做任何处理。

![image-20211223170221998](kettle.assets/image-20211223170221998.png)

任务：从t_orders表中获取数据，根据u_id查询t_users表，获取用户信息，并把数据保存到Excel。

分析：

![image-20211223170319674](kettle.assets/image-20211223170319674.png)

sql表：

```sql
/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2019-01-17 13:41:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_orders
-- ----------------------------
DROP TABLE IF EXISTS `t_orders`;
CREATE TABLE `t_orders` (
  `id` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `total_money` int(11) DEFAULT NULL,
  `u_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_orders
-- ----------------------------
INSERT INTO `t_orders` VALUES ('1', '2019-02-01 10:00:00', '5', '200', '1');
INSERT INTO `t_orders` VALUES ('2', '2019-02-02 10:00:00', '3', '100', '2');
INSERT INTO `t_orders` VALUES ('3', '2019-02-03 10:00:00', '4', '300', '3');
INSERT INTO `t_orders` VALUES ('4', '2019-02-04 10:00:00', '1', '400', '4');
INSERT INTO `t_orders` VALUES ('5', '2019-02-05 10:00:00', '2', '500', '5');
INSERT INTO `t_orders` VALUES ('6', '2019-02-06 10:00:00', '5', '100', '6');
INSERT INTO `t_orders` VALUES ('7', '2019-02-07 10:00:00', '4', '200', '7');
INSERT INTO `t_orders` VALUES ('8', '2019-02-08 10:00:00', '3', '300', '8');
INSERT INTO `t_orders` VALUES ('9', '2019-02-09 10:00:00', '2', '400', '9');
INSERT INTO `t_orders` VALUES ('10', '2019-02-10 10:00:00', '1', '500', '10');
INSERT INTO `t_orders` VALUES ('11', '2019-02-11 10:00:00', '5', '200', '11');
INSERT INTO `t_orders` VALUES ('12', '2019-02-12 10:00:00', '2', '100', '12');

```

```sql
/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2019-01-17 13:41:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_users
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users` (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `address` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_users
-- ----------------------------
INSERT INTO `t_users` VALUES ('1', 'zhangsan', '22', 'hunan', '18573117992', 'f');
INSERT INTO `t_users` VALUES ('2', 'lisi', '25', 'beijing', '18573117993', 'm');
INSERT INTO `t_users` VALUES ('3', 'wangwu', '28', 'neimenggu', '18573117991', 'f');
INSERT INTO `t_users` VALUES ('4', 'zhaoliu', '31', 'xingjiang', '18573117990', 'm');
INSERT INTO `t_users` VALUES ('5', 'sunqi', '34', 'zhejiang', '18573117998', 'm');
INSERT INTO `t_users` VALUES ('6', 'qianba', '37', 'guangzhou', '18573117997', 'f');
INSERT INTO `t_users` VALUES ('7', 'sanfeng', '40', 'shenzheng', '18573117996', 'f');
INSERT INTO `t_users` VALUES ('8', 'wuji', '43', 'nanchang', '18573117995', 'f');
INSERT INTO `t_users` VALUES ('9', 'zehua', '46', 'shanxi', '18573117994', 'f');
INSERT INTO `t_users` VALUES ('10', 'caorui', '49', 'guizhou', '18573117988', 'm');
INSERT INTO `t_users` VALUES ('11', 'gebi', '52', 'sanya', '18573117987', 'm');
INSERT INTO `t_users` VALUES ('12', 'laowang', '55', 'qingdao', '18573117980', 'm');

```

# kettle脚本控件

# kettle作业和参数

## 作业

### 作业简介

- 大多数ETL项目都需要完成各种各样的维护工作。
  
- 例如，如何传送文件；验证数据库表是否存在，等等。而这些操作都是按照一定顺序完成。因为转换以并行方式执行，就需要一个可以串行执行的作业来处理这些操作。
  
- 一个作业包含一个或者多个作业项，这些作业项以某种顺序来执行。作业执行顺序由作业项之间的跳（job hop）和每个作业项的执行结果来决定。

![image-20211224144547606](kettle.assets/image-20211224144547606.png)

### 作业项

- 作业项是作业的基本构成部分。如同转换的步骤，作业项也可以使用图标的方式图形化展示。
  
- 但是，如果你再仔细观察，还是会发现作业项有一些地方不同于步骤：
  在作业项之间可以传递一个结果对象（result object）。这个结果对象里面包含了数据行，它们不是以数据流的方式来传递的。而是等待一个作业项执行完了，再传递个下一个作业项。
  
- 因为作业顺序执行作业项，所以必须定义一个起点。有一个叫“开始”的作业项就定义了这个点。一个作业只能定一个开始作业项。

### 作业跳

作业的跳是作业项之间的连接线，他定义了作业的执行路径。作业里每个作业项的不同运行结果决定了做作业的不同执行路径。

①无条件执行：不论上一个作业项执行成功还是失败，下一个作业项都会执行。这是一种蓝色的连接线，上面有一个锁的图标。

②当运行结果为真时执行：当上一个作业项的执行结果为真时，执行下一个作业项。通常在需要无错误执行的情况下使用。这是一种绿色的连接线，上面有一个对钩号的图标。

③当运行结果为假时执行：当上一个作业项的执行结果为假或者没有成功执行是，执行下一个作业项。这是一种红色的连接线，上面有一个红色的停止图标。

在图标上单击就可以对跳进行设置。

![image-20211224145520028](kettle.assets/image-20211224145520028.png)

![image-20211224145537038](kettle.assets/image-20211224145537038.png)

任务：先从Excel读取数据，保存到Excel，再从文本文件中读取数据保存到Excel，如果产生错误就发送邮件，并且停止作业，如果成功发送成功邮件。

分析：

![image-20211224145619113](kettle.assets/image-20211224145619113.png)

## 参数

### 简介

- 对于ETL参数传递是一个很重要的环节，因为参数的传递会涉及到业务数据是如何抽取。
- 参数分为两种：全局参数和局部参数。

### 全局参数

- 全局参数定义是通过当前用户下.kettle文件夹中的kettle.properties文件来定义。
  
- 定义方式是采用键=值对方式来定，如：start_date=20130101
  
- 注：在配置全局变量时需要重启Kettle才会生效。

### 局部参数

- 局部参数变量是通过“Set Variables”与“Get Variables”方式来设置。
  
- 注：在“Set Variables”时在当前转换当中是不能马上使用，需要在作业中的下一步骤中使用。

### 参数的使用

- Kettle中参数使用方法有两种：一种是%%变量名%%，一种是${变量名}。
- 注：在SQL中使用变量时需要把“是否替换变量”勾选上，否则无法使变量生效。

任务：从kettle.properties文件中读取STARTROW和PAGESIZE参数，从t_orders表中获取数据数据，使用参数传递。

![image-20211224150524477](kettle.assets/image-20211224150524477.png)

```SQL
SELECT
	id,
	create_time,
	amount,
	total_money,
	u_id 
FROM
	t_orders 
WHERE
	$ { STSRTROW },%% PAGESIZE %%
```

分析：

- ①编辑.kettle目录下的kettle.properites文件，输入key=value
  输入：表输入
  输出：文本文件输出
- ②重启，运行看效果

![image-20211224150900830](kettle.assets/image-20211224150900830.png)

## 表输入参数传递-常量传递

- 常量传递就是先自定义常量数据，在表输入的SQL语句里面使用？来替换。
- ？号的替换顺序就是常量定义的顺序。

![image-20211224153427024](kettle.assets/image-20211224153427024.png)

任务：自定义常量数据id1=10和id2=3，从t_users表中获取数据，满足条件id<id1和id>id2，后续不要只想任何操作。

分析：

- 输入：自定义常量数据、表输入
- 流程：空操作

![image-20211224153524036](kettle.assets/image-20211224153524036.png)

## 表输入参数传递-变量传递-转换命名参数

- 转换命名参数就是在转换内部定义的变量，作用范围是在转换内部。
- 在转换的空白处右键，选择转换设置就可以看见。

![image-20211224155156612](kettle.assets/image-20211224155156612.png)

![image-20211224155302432](kettle.assets/image-20211224155302432.png)

任务：设置转换命名参数id1=10和id2=3，从t_users表中获取数据，满足条件id<id1和id>id2，后续不要只想任何操作。

分析：

- 输入：表输入
- 流程：空操作

![image-20211224155440431](kettle.assets/image-20211224155440431.png)

## 表输入参数传递-变量传递-转换内设置变量和获取变量

- 在转换里面有一个作业分类，里面有设置变量和获取变量的步骤。
- 注意：“获取变量”时在当前转换当中是不能马上使用，需要在作业中的下一步骤中使用！

![image-20211224164127053](kettle.assets/image-20211224164127053.png)

![image-20211224164139376](kettle.assets/image-20211224164139376.png)

![image-20211224164152494](kettle.assets/image-20211224164152494.png)

任务：在一个转换里面设置变量id1和id2，在另外一个转换里面获取变量id1和id2, 从t_users表中获取数据，满足条件id<id1和id>id2，把数据输出到Excel。

分析：

- 作业：start、转换

- 转换：
  1. 设置变量

  2. 获取变量、表输入、Excel输出

![image-20211224164938917](kettle.assets/image-20211224164938917.png)

## 表输入参数传递-变量传递-作业里设置变量

- 变量可以在转换里面设置，也可以在作业里面设置。

![image-20211224170226696](kettle.assets/image-20211224170226696.png)

![image-20211224170239440](kettle.assets/image-20211224170239440.png)

任务：在作业里面设置变量id1和id2，在转换以两种不同的方式进行变量替换：
 ①先获取变量，再进行步骤替换
②直接变量替换
都是从t_users表中获取数据，满足条件id<id1和id>id2，把数据输出到Excel。



分析：

1. 作业：start、设置变量、转换
2. 转换：
   	获取变量、表输入、Excel输出
   	表输入、Excel输出

![image-20211224170339313](kettle.assets/image-20211224170339313.png)

