# Ant安装

Ant是一个构建工具。

官网：http://ant.apache.org/

从官网上下载ant包，解压放入一个目录中。

创建一个环境变量：命名为 ANT_HOME，将 Apache Ant 的批处理文件的路径添加到 PATH 环境变量里。

验证Ant是否安装成功：

```windows
> C:\>ant -version
Apache Ant(TM) version 1.8.2 compiled on December 20 2010
```

# Ant构建文件

一般来说，Ant 的构建文件默认为 build.xml，放在项目顶层目录中。然而，并没有限制构建文件必须命名为 bulid.xml，也并不限制放在项目顶层目录中。你可以将构建文件命名为其他名字，也可以将它放在项目的其他地方。

对于下面的练习，创建一个文件命名为 build.xml 的文件，存储在你电脑的任意地方 ，并包含一下的内容：

```xml
<?xml version="1.0"?>
   <project name="Hello World Project" default="info">

   <target name="info">
      <echo>Hello World - Welcome to Apache Ant!</echo>
   </target>

</project>
```

注意到上面的练习中，在 xml 文件的声明前面没有任何空行或者空格。如果你在写 xml 文件的声明时加入了空行或者空格，执行 ant -build 操作时，将会出现下面的错误信息：

```
The processing instruction target matching "[xX][mM][lL]" is not allowed.
```

错误信息的意思是：处理指令目标匹配 `"[xX][mM][lL]"` 不被允许。所有的构建文件需要包含项目元素 (project 标签) 和至少一个目标元素 (target 标签)。

构建文件的项目元素 有 3 个属性：

| 属性               | 描述                                                         |
| ------------------ | ------------------------------------------------------------ |
| 项目名 (name)      | 表示项目的名称。（可选）                                     |
| 默认 (default)     | 表示构建脚本默认运行的目标，即制定默认的 target。一个项目 (project) 可以包含多个目标 (target)。（必须） |
| 基准目录 (basedir) | 表示当该属性没有指定时，使用 Ant 的构件文件的父目录作为基准目录。（可选） |

一个目标 (target) 是**一系列你想运行的任务 (tasks)**，运行时看成一个单元。在我们的例子中，我们用一个简单的目标来为用户提供一个有信息的消息。

目标和目标之间可以有依赖关系。举个例子，一个部署 (deploy) 目标可能依赖于封装 (package) 目标，而这个封装目标可能又依赖于编译 (compile) 目标等。依赖关系被表示成依赖属性 (depends)。例如：

```xml
<target name="deploy" depends="package">
  ....
</target>

<target name="package" depends="clean,compile">
  ....
</target>

<target name="clean" >
  ....
</target>

<target name="compile" >
  ....
</target>
```

构建文件的目标元素有以下属性：

| 属性               | 描述                                                         |
| :----------------- | :----------------------------------------------------------- |
| 目标名 (name)      | 表示目标的名称。（必须）                                     |
| 依赖 (depends)     | 用于描述 target 之间的依赖关系，若与多个 target 存在依赖关系时，需要以“,”间隔。Ant 会依照 depends 属性中 target 出现的顺序依次执行每个 target。被依赖的 target 会先执行。（可选） |
| 描述 (description) | 关于 target 功能的简单描述。（可选）                         |
| 如果 (if)          | 用于验证指定的属性是否存在，若不存在，所在 target 将不会被执行。（可选） |
| 除非 (unless)      | 该属性的功能与 if 属性的功能正好相反，它也用于验证指定的属性是否存在，若不存在，所在 target 将会被执行。（可选） |

在上面的例子中 echo 任务主要负责打印消息。在我们的例子中，执行 echo 任务后，打印出 “hello world” 消息。

为了运行 ant 的构建文件，打开命令提示符并导航到 build.xml 建立的文件夹。输入 ant info 命令或者 ant 命令。这两种命令都可以运行，因为 info 是构建文件的默认目标。你讲会看到下面的输出信息：

```cmd
C:\>ant
Buildfile: C:\build.xml

info: [echo] Hello World - Welcome to Apache Ant!

BUILD SUCCESSFUL
Total time: 0 seconds

C:\>
```

# Ant属性

Ant 构建文件是用 XML 编写的，它不能像你喜欢的编程语言那样去声明变量。然而，正如你可能已经想到的，如果允许 Ant 声明变量，如项目名称，项目源目录等，这将是非常有用的。

Ant 使用**属性 (property)** 元素来让你能够具体说明属性。这就允许这些属性能够在不同的构建和不同的环境下发生改变。

默认情况下，Ant 提供以下预定义的属性，这些属性都是可以在构建文件中使用的：

|            属性             |                             解释                             |
| :-------------------------: | :----------------------------------------------------------: |
|          ant.file           |                     该构建文件的完整地址                     |
|         ant.version         |                   安装的 Apache Ant 的版本                   |
|           basedir           | 构建文件的基目录的绝对路径，作为 **project** 元素的 **basedir** 属性 |
|      ant.java.version       |          Ant 使用的 JAVA 语言的软件开发工具包的版本          |
|      ant.project.name       |   项目的名字，具体声明为 **project** 元素的 **name** 属性    |
| ant.project.default-target  |                      当前项目的默认目标                      |
| ant.project.invoked-targets |            在当前项目中被调用的目标的逗号分隔列表            |
|        ant.core.lib         |                 Ant 的 jar 文件的完整的地址                  |
|          ant.home           |                       Ant 安装的主目录                       |
|       ant.library.dir       |        Ant 库文件的主目录，特别是 ANT_HOME/lib 文件夹        |

Ant 也确保系统属性在构建文件中可用，如 file.separator。

除了上述内容以外，用户也可以使用 property 元素定义一些额外的属性。下面的例子就演示了怎样去定义一个叫做 sitename 的属性：

```xml
<?xml version="1.0"?>
<project name="Hello World Project" default="info">

   <property name="sitename" value="www.tutorialspoint.com"/>
   <target name="info">
      <echo>Apache Ant version is {ant.version} - You are at{sitename} </echo>
   </target>

</project>
```

在上述的构建文件下运行 Ant 可以产生以下输出：

```cmd
C:\>ant
Buildfile: C:\build.xml

info: [echo] Apache Ant version is Apache Ant(TM) version 1.8.2  
      compiled on December 20 2010 - You are at www.tutorialspoint.com

BUILD SUCCESSFUL
Total time: 0 seconds

C:\>
```

# Ant属性文件

当你只需要对小部分属性进行设置时，可以选择直接在构建文件中设置。然而，对于大项目，最好将设置属性的信息存储在一个独立的文件中。

存储属性信息在一个独立的文件中将会提供以下好处：

- 它可以让您重复使用相同的构建文件，该文件在不同的执行环境中使用不同的属性设置。例如，构建属性文件在 DEV , TEST , 和 PROD 环境中可以独立地被维护。
- 当你事先不知道属性的值时（例如，在一个实际的环境中），这样处理是有益的。这样允许你在知道属性值后，在其他环境中执行生成 (build) 操作。

这里没有硬性规定，但是一般情况下，属性文件都被命名为 **build.properties**， 并且与 **build.xml** 存放在同一目录层。 你可以基于部署环境 ——比如： **build.properties.dev** 和 **build.properties.test** 创建多个 **build.properties** 文件。

在下面的例子中展示了 **build.xml** 文件和与之相联系的 **build.properties**文件：

## build.xml

```xml
<?xml version="1.0"?>
<project name="Hello World Project" default="info">

   <property file="build.properties"/>

   <target name="info">
      <echo>Apache Ant version is {ant.version} - You are at{sitename} </echo>
   </target>

</project>
```

## build.properties

```properties
# The Site Name
sitename=wiki.w3cschool.cn
buildversion=3.3.2
```

注意到上面的练习中，**sitename** 是一个自定义属性，执行后映射到一个地址为 “wiki.w3cschool.cn” 的网站上。你可以用这种方式声明任意数量的属性。在上面的例子中，还有一个自定义属性 **buildversioin**，它表明了当前构建的版本号。

# Ant数据类型

Ant 提供一些预定义的数据类型。不要将术语“数据类型”和那些在编程语言中可用的数据类型相混淆，而是将他们视作一组已经在产品中配置好的服务。

下述的数据类型是由 Apache Ant 提供的。

## 文件集

文件集的数据类型代表了一个文件集合。它被当作一个过滤器，用来包括或移除匹配某种模式的文件。

例如，参考下面的代码。这里，src 属性指向项目的源文件夹。

文件集选择源文件夹中所有的 .java 文件，除了那些包含有 ‘Stub’ 单词的文件。能区分大小写的过滤器被应用到文件集上，这意味着名为 Samplestub.java 的文件将不会被排除在文件集之外。

```xml
<fileset dir="${src}" casesensitive="yes">
   <include name="/.java"/>
   <exclude name="/Stub"/>
</fileset>
```

## 模式集合

*一个模式集合指的是一种模式，基于这种模式，能够很容易地过滤文件或者文件夹。模式可以使用下述的元字符进行创建。*

- ***?** －仅匹配一个字符*
- *－匹配零个或者多个字符*
- ***－递归地匹配零个或者多个目录\***

*下面的例子演示了模式集合的使用。*

```xml
<patternset id="java.files.without.stubs">
   <include name="src//.java"/>
   <exclude name="src//Stub"/>
</patternset>
```

*该模式集合能够通过一个类似于下述的文件集进行重用：*

```xml
<fileset dir="${src}" casesensitive="yes">      
	<patternset refid="java.files.without.stubs"/>
</fileset>
```

## 文件列表

*文件列表数据类型与文件集相类似，除了以下几处不同：*

- *文件列表包含明确命名的文件的列表，同时其不支持通配符。*
- *文件列表数据类型能够被应用于现有的或者还不存在的文件中。*

*让我们来看一个下述的关于文件列表数据类型的例子。在这个例子中，属性 **webapp.src.folder** 指向该项目中的 Web 应用的源文件夹。*

```xml
<filelist id="config.files" dir="${webapp.src.folder}">       
	<file name="applicationConfig.xml"/>       
	<file name="faces-config.xml"/>       
	<file name="web.xml"/>       
	<file name="portlet.xml"/>
</filelist>
```

## 过滤器集合

*使用一个过滤器集合数据类型与拷贝任务，你可以在所有文件中使用一个替换值来替换掉一些与模式相匹配的文本。*

*一个常见的例子就是对一个已经发行的说明文件追加版本号，代码如下：*

```xml
<copy todir="{output.dir}">
   <fileset dir="{releasenotes.dir}" includes="/.txt"/>
   <filterset>
      <filter token="VERSION" value="${current.version}"/>
   </filterset>
</copy>
```

*在这段代码中：*

- *属性 **output.dir** 指向项目的输出文件夹。*
- *属性 **releasenotes.dir** 指向项目的发行说明文件夹。*
- *属性 **current.version** 指向项目的当前版本文件夹。*
- *拷贝任务，顾名思义，是用来将文件从一个地址拷贝到另一个地址。*

## 路径

***path** 数据类型通常被用来表示一个类路径。各个路径之间用分号或者冒号隔开。然而，这些字符在运行时被替代为执行系统的路径分隔符。*

*类路径被设置为项目中 jar 文件和类文件的列表，如下面例子所示：*

```xml
<path id="build.classpath.jar">
   <pathelement path="{env.J2EE_HOME}/{j2ee.jar}"/>
   <fileset dir="lib">
      <include name="*/.jar"/>
   </fileset>
</path>
```

在这段代码中：

- 属性 **env.J2EE_HOME** 指向环境变量 J2EE_HOME 。
- 属性 **j2ee.jar** 指向在 J2EE 基础文件夹下面的名为 J2EE jar 的文件。

