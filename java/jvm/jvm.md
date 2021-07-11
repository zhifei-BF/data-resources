# JDK体系结构

![https://note.youdao.com/yws/public/resource/ad3d29fc27ff8bd44e9a2448d3e2706d/xmlnote/1B22B118287143CAB49C70316F2C336C/94563](jvm.assets/clipboard-1625908908092.png)

JRE称为JAVA运行时环境，包含JAVA的核心类库。

JDK = JRE + JAVA开发工具集

JRE = JVM + JAVA核心类库

# JAVA语言的跨平台特性

![https://note.youdao.com/yws/public/resource/ad3d29fc27ff8bd44e9a2448d3e2706d/xmlnote/638573187FDE4B019673D284B0EBE450/94569](jvm.assets/clipboard-1625909045042.png)

通过JVM将字节码文件转成对应系统的机器码，然后由对应系统的CPU进行执行。

# JVM位置

**JVM**是运行在操作系统之上的，它与硬件没有直接的交互。

![在这里插入图片描述](jvm.assets/20200716231457129.png)

# JVM整体结构及内存模型

![在这里插入图片描述](jvm.assets/20200716231525683.png)

方法区：存储已被虚拟机加载的类元数据信息(元空间)、常量、静态变量。

堆：存放对象实例，几乎所有的对象实例都在这里分配内存

虚拟机栈：虚拟机栈描述的是Java方法执行的内存模型：每个方法被执行的时候都会同时创建一个栈帧（Stack Frame）用于存储局部变量表、操作数栈、动态链接、方法出口等信息。

​	局部变量表：用于存储局部变量及对应的基本类型的值或者复杂类型的地址值。

​	操作数栈：用于存储操作数。

​	方法出口：就是回到上一个调用该方法的地方。

程序计数器：当前线程所执行的字节码的行号指示器，每个线程都有一个。

本地方法栈：本地方法栈则是为虚拟机使用到的Native方法服务，调用底层C或者C++编写的程序。

# 类加载器ClassLoader

负责加载class文件，class文件在文件开头有特定的文件标识（以cafe babe 开头）。

![1625909976800](jvm.assets/1625909976800.png)

对于这种字节码文件我们可以通过`javap -c xxx.class`进行反汇编，变为JAVA指令。

```java
C:\Users\holle>javap
用法: javap <options> <classes>
其中, 可能的选项包括:
  -help  --help  -?        输出此用法消息
  -version                 版本信息
  -v  -verbose             输出附加信息
  -l                       输出行号和本地变量表
  -public                  仅显示公共类和成员
  -protected               显示受保护的/公共类和成员
  -package                 显示程序包/受保护的/公共类
                           和成员 (默认)
  -p  -private             显示所有类和成员
  -c                       对代码进行反汇编
  -s                       输出内部类型签名
  -sysinfo                 显示正在处理的类的
                           系统信息 (路径, 大小, 日期, MD5 散列)
  -constants               显示最终常量
  -classpath <path>        指定查找用户类文件的位置
  -cp <path>               指定查找用户类文件的位置
  -bootclasspath <path>    覆盖引导类文件的位置

C:\Users\holle>
```

ClassLoader只负责class文件的加载，至于它是否可以运行，则由Execution Engine决定。

![在这里插入图片描述](jvm.assets/20200716231546287.png)

类加载器分为四种：前三种为虚拟机自带的加载器。

- 
  启动类加载器（又叫跟类加载器）（Bootstrap）C++

  负责加载$JAVA_HOME中jre/lib/rt.jar等jar包里面的class，由C++实现，不是ClassLoader子类

- 扩展类加载器（Extension）Java

  负责加载java平台中**扩展功能**的一些jar包，包括$JAVA_HOME中jre/lib/*.jar或-Djava.ext.dirs指定目录下的jar包

- 应用程序类加载器（AppClassLoader）Java

  也叫系统类加载器，负责加载**classpath**中指定的jar包及目录中class

- 用户自定义加载器 Java.lang.ClassLoader的子类，用户可以定制类的加载方式

工作过程：

1、当AppClassLoader加载一个class时，它首先不会自己去尝试加载这个类，而是把类加载请求委派给父类加载器ExtClassLoader去完成。
2、当ExtClassLoader加载一个class时，它首先也不会自己去尝试加载这个类，而是把类加载请求委派给BootStrapClassLoader去完成。
3、如果BootStrapClassLoader加载失败（例如在$JAVA_HOME/jre/lib里未查找到该class），会使用ExtClassLoader来尝试加载；
4、若ExtClassLoader也加载失败，则会使用AppClassLoader来加载
5、如果AppClassLoader也加载失败，则会报出异常ClassNotFoundException

其实这就是所谓的**双亲委派模型**。简单来说：如果一个类加载器收到了类加载的请求，它首先不会自己去尝试加载这个类，而是把**请求委托给父加载器去完成，依次向上**。

好处：**防止内存中出现多份同样的字节码**(安全性角度)

写段儿代码演示类加载器：

```java
public class Demo {

    public Demo() {
        super();
    }

    public static void main(String[] args) {
        Object obj = new Object();
        String s = new String();
        Demo demo = new Demo();
        System.out.println(obj.getClass().getClassLoader());
        System.out.println(s.getClass().getClassLoader());
        System.out.println(demo.getClass().getClassLoader().getParent().getParent());
        System.out.println(demo.getClass().getClassLoader().getParent());
        System.out.println(demo.getClass().getClassLoader());
    }
}
```

打印控制台中的sun.misc.Launcher，是一个java虚拟机的入口应用。

# 双亲委派模式

类加载为什么要使用双亲委派模式，有没有什么场景是打破了这个模式？

双亲委派模型的重要用途是为了解决类载入过程中的安全问题。

1. 假设有一个开发者自己编写了一个名为java.lang.Object的类，想借此欺骗JVM。现在他要使用自定义ClassLoader来加载自己编写的java.lang.Object类。

2. 然而幸运的是，双亲委派模型是不会让他成功。因为JVM会优先在BootstrapClassLoader的路径下找到java.lang.Object类，并载入它。

   

Java的类加载是否一定遵循双亲委派模型？

1. 在实际开发中，我们可以通过自定义ClassLoader，并重写父类的loadClass方法，来打破这一机制。

2. SPI就是打破了双亲委派机制的（SPI：服务提供发现）

# 执行引擎Execution Engine

又叫字节码执行引擎，**Execution Engine**执行引擎负责解释命令，提交操作系统执行。

# 本地接口Native Interface

​	本地接口的作用是融合不同的编程语言为 Java 所用，它的初衷是融合 C/C++程序，Java 诞生的时候是 C/C++横行的时候，要想立足，必须有调用 C/C++程序，于是就在内存中专门开辟了一块区域处理标记为native的代码，它的具体做法是 Native Method Stack中登记 native方法，在Execution Engine 执行时加载native libraies。

​	目前该方法使用的越来越少了，除非是与硬件有关的应用，比如通过Java程序驱动打印机或者Java系统管理生产设备，在企业级应用中已经比较少见。因为现在的异构领域间的通信很发达，比如可以使用 Socket通信，也可以使用Web Service等等，不多做介绍。

# Native Method Stack

​	它的具体做法是Native Method Stack中登记native方法，在Execution Engine 执行时加载本地方法库。

# PC寄存器

​	每个线程都有一个**程序计数器**，是**线程私有的**，就是一个指针，指向方法区中的方法字节码（**用来存储指向下一条指令的地址**，即 将要执行的指令代码），由执行引擎读取下一条指令，是一个非常小的内存空间，几乎可以忽略不记。

# Method Area方法区

​	方法区是被所有线程共享，所有字段和方法字节码，以及一些特殊方法如构造函数，接口代码也在此定义。简单说，所有定义的方法的信息都保存在该区域，**此区属于共享区间**。

**静态变量+常量+类信息(构造方法/接口定义)+运行时常量池**存在方法区中。

But  **实例变量存在堆内存**中,和方法区无关

# stack栈

​	栈也叫栈内存，又叫线程栈，**主管Java程序的运行，是在线程创建时创建**，它的生命期是跟随线程的生命期，线程结束栈内存也就释放，**对于栈来说不存在垃圾回收问题**，只要线程一结束该栈就Over，生命周期和线程一致，是线程私有的。**8种基本类型的变量+对象的引用变量+实例方法**都是在函数的栈内存中分配。

​	线程运行过程中，只有一个栈帧是处于活跃状态，称为“当前活跃栈帧”，当前活动栈帧始终是虚拟机栈的栈顶元素。

​	通过jstack工具查看线程状态。

**栈存储什么?**

栈帧中主要保存3 类数据：

- 本地变量（Local Variables）：输入参数和输出参数以及方法内的变量。
- 栈操作（Operand Stack）：记录出栈、入栈的操作。
- 栈帧数据（Frame Data）：包括类文件、方法等等。

**栈运行原理：**

栈中的数据都是以栈帧（Stack Frame）的格式存在，栈帧是一个内存区块，是一个数据集，是一个有关方法(Method)和运行期数据的数据集，当一个方法A被调用时就产生了一个栈帧 F1，并被压入到栈中，

A方法又调用了 B方法，于是产生栈帧 F2 也被压入栈，

B方法又调用了 C方法，于是产生栈帧 F3 也被压入栈，

……

执行完毕后，先弹出F3栈帧，再弹出F2栈帧，再弹出F1栈帧……

遵循“先进后出”或者“后进先出”原则。
![在这里插入图片描述](jvm.assets/20200716231626280.png)

图示在一个栈中有两个栈帧：

栈帧 2是最先被调用的方法，先入栈，

然后方法 2 又调用了方法1，栈帧 1处于栈顶的位置，

栈帧 2 处于栈底，执行完毕后，依次弹出栈帧 1和栈帧 2，

线程结束，栈释放。

每执行一个方法都会产生一个栈帧，保存到栈(后进先出)的顶部，顶部栈就是当前的方法，该方法执行完毕
后会自动将此栈帧出栈。

常见问题栈溢出：Exception in thread “main” java.lang.StackOverflowError

通常出现在递归调用时。



请解释StackOverflowError和OutOfMemeryError的区别？

通过之前的分析可以发现，实际上每一块内存中都会存在有一部分的可变伸缩区，其基本流程为：如果空间内存不足，在可变范围之内扩大内存空间，当一段时间之后发现内存充足，会缩小内存空间。

永久代（JDK1.8后消失了）

虽然java的版本是JDK1.8，但是javaEE的版本还是jdk1.7，永久代存在于堆内存之中。

元空间

元空间在jdk1.8之后才有的，其功能实际上和永久代没区别，唯一的区别在于永久代使用的是JVM的堆内存空间，元空间使用的是物理内存，所以元空间的大小受本地内存影响，一般默认在2M左右。

范例：设置一些参数，让元空间出错

java -XX：MetaspaceSize =1m





stackOverflow错误，permgen space错误。

​	stackoverflow错误主要出现：在虚拟机栈中（线程请求的栈深度大于虚拟机栈所允许的最大深度）

​	permgen space错误（针对jdk1.7及之前版本）：1. 大量加载class文件 2. 常量池内存溢出

# 堆

堆栈方法区的关系：

![在这里插入图片描述](jvm.assets/20200716231652803.png)

**HotSpot**是使用指针的方式来访问对象：

- Java堆中会存放访问**类元数据**的地址，
- reference存储的就是对象的地址

三种JVM：

•Sun公司的**HotSpot**

•BEA公司的**JRockit**

•IBM公司的**J9 VM**



**在minor gc过程中对象挪动后，引用如何修改？**

对象在堆内部挪动的过程其实是复制，原有区域对象还在，一般不直接清理，JVM内部清理过程只是将对象分配指针移动到区域的头位置即可，比如扫描s0区域，扫到gcroot引用的非垃圾对象是将这些对象**复制**到s1或老年代，最后扫描完了将s0区域的对象分配指针移动到区域的起始位置即可，s0区域之前对象并不直接清理，当有新对象分配了，原有区域里的对象也就被清除了。

minor gc在根扫描过程中会记录所有被扫描到的对象引用(在年轻代这些引用很少，因为大部分都是垃圾对象不会扫描到)，如果引用的对象被复制到新地址了，最后会一并更新引用指向新地址。

**StackOverflowError**示例：

```java
// JVM设置  -Xss128k(默认1M)
public class StackOverflowTest {
    
    static int count = 0;
    
    static void redo() {
        count++;
        redo();
    }

    public static void main(String[] args) {
        try {
            redo();
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println(count);
        }
    }
}

运行结果：
java.lang.StackOverflowError
	at com.tuling.jvm.StackOverflowTest.redo(StackOverflowTest.java:12)
	at com.tuling.jvm.StackOverflowTest.redo(StackOverflowTest.java:13)
	at com.tuling.jvm.StackOverflowTest.redo(StackOverflowTest.java:13)
   ......
```

**结论：**

-Xss设置越小count值越小，说明一个线程栈里能分配的栈帧就越少，但是对JVM整体来说能开启的线程数会更多

# 堆体系概述

Java7之前

Heap 堆：一个JVM实例只存在一个堆内存，堆内存的大小是可以调节的。类加载器读取了类文件后，需要把类、方法、常变量放到堆内存中，保存所有引用类型的真实信息，以方便执行器执行，堆内存逻辑上分为三部分：

Young Generation Space 新生区 Young/New

Tenure generation space 养老区 Old/Tenure

Permanent Space 永久区 Perm

也称为：新生代（年轻代）、老年代、永久代（持久代）。
其中**JVM堆分为新生代和老年代**

![在这里插入图片描述](jvm.assets/20200716231721525.png)

#  新生代

​	新生区是类的诞生、成长、消亡的区域，一个类在这里产生，应用，最后被垃圾回收器收集，结束生命。新生区又分为两部分： 伊甸区（Eden space）和幸存者区（Survivor pace） ，所有的类都是在伊甸区被new出来的。幸存区有两个： 0区（Survivor 0 space）和1区（Survivor 1 space）。当伊甸园的空间用完时，程序又需要创建对象，JVM的垃圾回收器将对伊甸园区进行垃圾回收(Minor GC)，将伊甸园区中的不再被其他对象所引用的对象进行销毁。然后将伊甸园中的剩余对象移动到幸存 0区。若幸存 0区也满了，再对该区进行垃圾回收，然后移动到 1 区。那如果1 区也满了呢？再次垃圾回收，满足条件后再移动到养老区。若养老区也满了，那么这个时候将产生MajorGC（FullGC），进行养老区的内存清理。若养老区执行了Full GC之后发现依然无法进行对象的保存，就会产生OOM异常“OutOfMemoryError”。

如果出现java.lang.OutOfMemoryError: Java heap space异常，说明Java虚拟机的堆内存不够。原因有二：

（1）Java虚拟机的堆内存设置不够，可以通过参数-Xms、-Xmx来调整。

（2）代码中创建了大量大对象，并且长时间不能被垃圾收集器收集（存在被引用）。


# 老年代

​	经历多次GC仍然存在的对象（默认是15次），老年代的对象比较稳定，不会频繁的GC。



JVM年轻代到年老代的晋升过程的判断条件是什么？

1. 部分对象会在Form和To区域中复制来复制去，如此交换15次（由JVM参数MaxTenuringThreshold决定，这个参数默认是15），最终如果还是存活，就存入到老年代。
2. 如果对象的大小大于Eden的二分之一会直接分配到old，如果old也分配不下，会做一次majorGC，如果小于Eden的一半但是没有足够的空间，就进行minorGC也就是新生代GC。
3. minorGC后，survivor仍然放不下，则存到老年代。
4. 动态年龄判断，大于等于某个年龄的对象超过了survivor空间一半，大于等于某个年龄的对象直接进入到老年代。



# 永久代

​	永久存储区是一个常驻内存区域，用于存放JDK自身所携带的 Class,Interface 的元数据，也就是说它存储的是运行环境必须的类信息，被装载进此区域的数据是不会被垃圾回收器回收掉的，关闭 JVM 才会释放此区域所占用的内存。

​	 如果出现java.lang.OutOfMemoryError: PermGen space，说明是Java虚拟机对永久代Perm内存设置不够。一般出现这种情况，都是程序启动需要加载大量的第三方jar包。例如：在一个Tomcat下部署了太多的应用。或者大量动态反射生成的类不断被加载，最终导致Perm区被占满。

Jdk1.6及之前： 有永久代, 常量池1.6在方法区

Jdk1.7： 有永久代，但已经逐步“去永久代”，常量池1.7在堆

Jdk1.8及之后： 无永久代，常量池1.8在元空间（Metaspace）

​	实际而言，方法区（Method Area）和堆一样，是各个线程共享的内存区域，它用于存储虚拟机加载的：类信息+普通常量+静态常量+编译器编译后的代码等等，虽然JVM规范将方法区描述为堆的一个逻辑部分，但它却还有一个别名叫做Non-Heap(非堆)，目的就是要和堆分开。

​	对于HotSpot虚拟机，很多开发者习惯将方法区称之为“永久代(Parmanent Gen)” ，但严格本质上说两者不同，或者说使用永久代来实现方法区而已，永久代是方法区(相当于是一个接口interface)的一个实现，jdk1.7的版本中，已经将原本放在永久代的字符串常量池移走。

 常量池（Constant Pool）是方法区的一部分，Class文件除了有类的版本、字段、方法、接口等描述信息外，还有一项信息就是常量池，这部分内容将在类加载后进入方法区的运行时常量池中存放。



JVM出现fullGC很频繁，怎么去线上排查问题？

这题就依据fullGC的触发条件来做：

1. 如果有perm gen的话（jdk1.8就没了），要给perm gen分配空间，但没有足够的空间时，会触发full gc。
2. 所以看看是不是perm gen区的值设置得太小了。
3. System.gc（）方法的调用，这个一般没人会调用。
4. 当统计得到的Minor GC晋升到旧生代的平均大小大于老年代的剩余空间，则会触发full gc（这就可以从多个角度上看了）。
5. 是不是频繁创建了大对象（也有可能eden区设置过小）（大对象直接分配在老年代中，导致老年代空间不足--->从而频繁GC）
6. 是不是老年代的空间设置过小了（Minor GC几个对象就大于老年代的剩余空间了）

# 堆参数调优入门

**均以JDK1.8+HotSpot为例**

当young gen中的eden区分配满的时候触发minor gc（新生代的空间不够放的时候）

jdk1.7：

![在这里插入图片描述](jvm.assets/20200716231753348.png)

jdk1.8：

![在这里插入图片描述](jvm.assets/2020071623180865.png)

# JAVA VisualVM使用

通过`jvisualvm`命令就可以查看当前正在运行的java相关进程，可以观察伊甸区，老年区的内存情况，来对程序进行优化。

```java
C:\Users\holle>jvisualvm

C:\Users\holle>

The launcher has determined that the parent process has a console and will reuse it for its own console output.
Closing the console will result in termination of the running program.
Use '--console suppress' to suppress console output.
Use '--console new' to create a separate console window.
```

## jvisualvm安装Visual GC插件

给jdk自带的jvisualvm安装Visual GC插件，遇到We're sorry the java.net site has closed（我们很抱歉java.net网站已经关闭） 

1、找到新的更新地址 

visualvm新访问地址：https://visualvm.github.io/index.html

进入“Plugins”，找到对应自己JDK版本的更新地址

![1625975243628](jvm.assets/1625975243628.png)

2、进入jvisualvm的插件管理 

"工具" - "插件" 

在"设置"中修改url地址为刚才我们在github上找到的对应我们JDK版本的地址 

![1625975285973](jvm.assets/1625975285973.png)

修改成功后，可用插件即可刷新出来 

3、安装VisualGC插件 

![1625975306817](jvm.assets/1625975306817.png)

4、重启即可看到VisualGC

![1625975339022](jvm.assets/1625975339022.png)

一：整个区域分为三部分：spaces、graphs、histogram 

1，spaces区域：代表虚拟机内存分布情况。从图中可以看出，虚拟机被分为Perm、Old、Eden、S0、S1 

注意：如果对每个区域基本概念不是很熟悉的可以先了解下java虚拟机运行时数据区这篇文字。 

1.1）perm：英文叫做Permanent Generation，我们称之为永久代。(根据深入java虚拟机作者说明，这里说法不是不是很正确，因为hotspot虚拟机的设计团队选择把GC分代收集扩展至此而已，正确的应该叫做方法区或者非堆)。 

1.1.1）通过VM Args:-XX:PermSize=128m -XX:MaxPermSize=256m 设置初始值与最大值

1.2）heap：java堆(java heap)。它包括老年代(图中Old区域)和新生代(图中Eden/S0/S1三个统称新生代，分为Eden区和两个Survivor区域)，他们默认是8:1分配内存 

1.2.1）通过VM Args:-xms512m -Xmx512m -XX:+HeapDumpOnOutofMemoryError -Xmn100m -XX:SurvivorRatio=8 设置初始堆内存、最大堆内存、内存异常打印dump、新生代内存、新生代内存分配比例(8:1:1)，因为Heap分为新生代跟老年代，所以512M-100M=412M，老年代就是412M(初始内存跟最大内存最好相等，防止内存不够时扩充内存或者Full GC，导致性能降低) 

2，Graphs区域：内存使用详细介绍 

2.1）Compile Time(编译时间)：6368compiles 表示编译总数，4.407s表示编译累计时间。一个脉冲表示一次JIT编译，窄脉冲表示持续时间短，宽脉冲表示持续时间长。 

2.2）Class Loader Time(类加载时间): 20869loaded表示加载类数量, 139 unloaded表示卸载的类数量，40.630s表示类加载花费的时间 

2.3）GC Time(GC Time)：2392collections表示垃圾收集的总次数，37.454s表示垃圾收集花费的时间，last cause表示最近垃圾收集的原因 

2.4）Eden Space(Eden 区)：括号内的31.500M表示最大容量，9.750M表示当前容量，后面的4.362M表示当前使用情况，2313collections表示垃圾收集次数，8.458s表示垃圾收集花费时间 

2.5）Survivor 0/Survivor 1(S0和S1区)：括号内的3.938M表示最大容量，1.188M表示当前容量，之后的值是当前使用情况 

2.6）Old Gen(老年代)：括号内的472.625M表示最大容量，145.031M表示当前容量，之后的87.031表示当前使用情况，79collections表示垃圾收集次数 ，28.996s表示垃圾收集花费时间 

2.7）Perm Gen(永久代)：括号内的256.000M表示最大容量，105.250M表示当前容量，之后的105.032M表示当前使用情况 

3，Histogram区域：survivor区域参数跟年龄柱状图

3.1）Tenuring Threshold：表示新生代年龄大于当前值则进入老年代 

3.2）Max Tenuring Threshold：表示新生代最大年龄值。 

3.3）Tenuring Threshold与Max Tenuring Threshold区别：Max Tenuring Threshold是一个最大限定，所有的新生代年龄都不能超过当前值，而Tenuring Threshold是个动态计算出来的临时值，一般情况与Max Tenuring Threshold相等，如果在Suivivor空间中，相同年龄所有对象大小的总和大于Survivor空间的一半，则年龄大于或者等于该年龄的对象就都可以直接进入老年代(如果计算出来年龄段是5，则Tenuring Threshold=5，age>=5的Suivivor对象都符合要求)，它才是新生代是否进入老年代判断的依据。

3.4）Desired Survivor Size：Survivor空间大小验证阙值(默认是survivor空间的一 

半)，用于Tenuring Threshold判断对象是否提前进入老年代。 

3.5）Current Survivor Size：当前survivor空间大小 

3.6）histogram柱状图：表示年龄段对象的存储柱状图 

3.7）如果显示指定-XX:+UseParallelGC --新生代并行、老年代串行收集器 ，则histogram柱状图不支持当前收集器 。

引用： 

http://www.oracle.com/technetwork/java/visualgc-136680.html 

http://www.oracle.com/technetwork/java/javase/tech/vmoptions-jsp-140102.html

# 常用JVM参数

怎么对jvm进行调优？通过**参数配置**

| 参数                      | 备注                                                         |
| ------------------------- | ------------------------------------------------------------ |
| -Xss                      | 等价于-XX：ThreadStackSize，单个线程栈空间大小，默认一般为512k-1024k，通过jinfo查看为0时，表示使用默认值。 |
| -Xms                      | 初始堆大小。只要启动，就占用的堆大小，默认是内存的1/64       |
| -Xmx                      | 最大堆大小。默认是内存的1/4                                  |
| -Xmn                      | 新生区堆大小                                                 |
| -XX:+PrintGCDetails       | 输出详细的GC处理日志                                         |
| -XX：MetaspaceSize        | 设置元空间触发Fullgc的初始阈值(元空间无固定初始大小)，（默认21M左右，可以设置大一些），达到该值就会触发full gc进行类型卸载， 同时收集器会对该值进行调整： 如果释放了大量的空间， 就适当降低该值； 如果释放了很少的空间， 那么在不超过-XX：MaxMetaspaceSize（如果设置了的话） 的情况下， 适当提高该值。这个跟早期jdk版本的**-XX:PermSize**参数意思不一样，-**XX:PermSize**代表永久代的初始容量。元空间的本质和永久代类似，都是对JVM规范中方法区的实现，不过元空间与永久代的最大区别在于：元空间不在虚拟机中，而是使用本地内存，因此，默认情况下，元空间大小仅受本地内存大小限制。 |
| -XX：MaxMetaspaceSize     | 设置最大元空间大小，默认是-1，即不限制，或者说只受限于本地内存大小。 |
| -XX：SurvivorRatio        | 调整Eden中survivor区比例，默认-XX：SurvivorRatio=8（8：1：1），调整为-XX：SurvivorRatio=4（4：1：1），一般使用默认值。 |
| -XX：NewSize              | 设置新生代初始大小                                           |
| -XX：NewRatio             | 调整新生代与老年代的比例，默认为2（新生代为1，老年代为2，年轻代占整个堆的1/3），调整为-XX：NewRatio=4表示（新生代为1，老年代为4，年轻代占堆的1/5），一般使用默认值。 |
| -XX：MaxTenuringThreshold | 设置垃圾的最大年龄（经历多少次垃圾回收进入老年代），默认15（15次垃圾回收后依旧存活的对象进入老年代），JDK1.8设置必须0<-XX：MaxTenuringThreshold <15 |

由于调整元空间的大小需要Full GC，这是非常昂贵的操作，如果应用在启动的时候发生大量Full GC，通常都是由于永久代或元空间发生了大小调整，基于这种情况，一般建议在JVM参数中将MetaspaceSize和MaxMetaspaceSize设置成一样的值，并设置得比初始值要大，对于8G物理内存的机器来说，一般我会将这两个值都设置为256M。

java代码查看jvm堆的默认值大小：

```java
Runtime.getRuntime().maxMemory()   // 堆的最大值，默认是内存的1/4
Runtime.getRuntime().totalMemory()  // 堆的当前总大小，默认是内存的1/64
```

Spring Boot程序的JVM参数设置格式(Tomcat启动直接加在bin目录下catalina.sh文件里)：

```
java -Xms2048M -Xmx2048M -Xmn1024M -Xss512K -XX:MetaspaceSize=256M -XX:MaxMetaspaceSize=256M -jar microservice-eureka-server.jar
```



# 怎么设置JVM参数

程序运行时，可以给该程序设置jvm参数，不同的工具设置方式不同。

如果是命令行运行：

```java
java -Xmx50m -Xms10m HeapDemo
```

eclipse运行的设置方式如下：

![在这里插入图片描述](jvm.assets/20200716231835217.png)

![在这里插入图片描述](jvm.assets/20200716232023959.png)

idea运行时设置方式如下：

![在这里插入图片描述](jvm.assets/20200716232045912.png)

![在这里插入图片描述](jvm.assets/2020071623210015.png)

# 查看堆内存详情

```java
public class Demo2 {
    public static void main(String[] args) {

        System.out.print("最大堆大小：");
        System.out.println(Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");
        System.out.print("当前堆大小：");
        System.out.println(Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");
        System.out.println("==================================================");

        byte[] b = null;
        for (int i = 0; i < 10; i++) {
            b = new byte[1 * 1024 * 1024];
        }
    }
}
```

执行前配置参数：-Xmx50m -Xms30m -XX:+PrintGCDetails

执行：看到如下信息

![img](jvm.assets/20200716232124451.png)

新生代和老年代的堆大小之和是Runtime.getRuntime().totalMemory()

# 内存溢出和内存泄露

内存泄露的原因：对象是可达的（一直被引用），但是对象不会被使用。

内存溢出的原因：

 	1. 内存泄露导致栈堆内存不断增大，从而引发内存溢出。
 	2. 大量的jar，class文件加载，装载类的空间不够，溢出。
 	3. 操作大量的对象导致堆内存空间已经用满了，溢出。
 	4. nio直接操作内存，内存过大导致溢出。

解决：

 	1. 查看程序是否存在内存泄露的问题。
 	2. 设置参数加大空间
 	3. 代码中是否存在死循环或循环产生过多重复的对象实体
 	4. 查看是否使用了nio直接操作内存。

# GC演示

```java
public class HeapDemo {

    public static void main(String args[]) {

        System.out.println("=====================Begin=========================");
        System.out.print("最大堆大小：Xmx=");
        System.out.println(Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");

        System.out.print("剩余堆大小：free mem=");
        System.out.println(Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M");

        System.out.print("当前堆大小：total mem=");
        System.out.println(Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");

        System.out.println("==================First Allocated===================");
        byte[] b1 = new byte[5 * 1024 * 1024];
        System.out.println("5MB array allocated");

        System.out.print("剩余堆大小：free mem=");
        System.out.println(Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M");

        System.out.print("当前堆大小：total mem=");
        System.out.println(Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");

        System.out.println("=================Second Allocated===================");
        byte[] b2 = new byte[10 * 1024 * 1024];
        System.out.println("10MB array allocated");

        System.out.print("剩余堆大小：free mem=");
        System.out.println(Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M");

        System.out.print("当前堆大小：total mem=");
        System.out.println(Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");

        System.out.println("=====================OOM=========================");
        System.out.println("OOM!!!");
        System.gc();
        byte[] b3 = new byte[40 * 1024 * 1024];
    }
}
```

jvm参数设置成最大堆内存100M，当前堆内存10M：-Xmx100m -Xms10m -XX:+PrintGCDetails

再次运行，可以看到minor GC和full GC日志：

![在这里插入图片描述](jvm.assets/20200716232218461.png)

# OOM演示

把上面案例中的jvm参数改成最大堆内存设置成50M，当前堆内存设置成10M，执行测试： -Xmx50m -Xms10m

```java
=====================Begin=========================
最大堆大小：Xmx=44.5M
剩余堆大小：free mem=8.186859130859375M
当前堆大小：total mem=9.5M
=================First Allocated=====================
5MB array allocated
剩余堆大小：free mem=3.1868438720703125M
当前堆大小：total mem=9.5M
================Second Allocated====================
10MB array allocated
剩余堆大小：free mem=3.68682861328125M
当前堆大小：total mem=20.0M
=====================OOM=========================
OOM!!!
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at com.atguigu.demo.HeapDemo.main(HeapDemo.java:40)
```

实际开发中怎么定位这种错误信息？MAT工具

# MAT工具

![在这里插入图片描述](jvm.assets/20200716232244959.png)

安装方式：eclipse插件市场下载

![在这里插入图片描述](jvm.assets/20200716232301767.png)

# MAT工具的使用

运行参数：-Xmx30m -Xms10m -XX:+HeapDumpOnOutOfMemoryError

![在这里插入图片描述](jvm.assets/20200716232321262.png)

重新刷新项目：看到dump文件

![在这里插入图片描述](jvm.assets/20200716232334777.png)

打开：

![在这里插入图片描述](jvm.assets/20200716232354892.png)

# idea分析dump文件

![在这里插入图片描述](jvm.assets/2020071623241714.png)

把上例中运行参数改成：

```java
-Xmx50m -Xms10m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\tmp 
```

-XX:HeapDumpPath：生成dump文件路径。

再次执行：生成C:\tmp\java_pid20328.hprof文件

![在这里插入图片描述](jvm.assets/20200716232441160.png)

![在这里插入图片描述](jvm.assets/2020071623245541.png)

生成的这个文件怎么打开？jdk自带了该类型文件的解读工具：**jvisualvm.exe**

![在这里插入图片描述](jvm.assets/20200716232512724.png)

双击打开：

![在这里插入图片描述](jvm.assets/20200716232529480.png)

文件–>装入–>选择要打开的文件即可

![在这里插入图片描述](jvm.assets/20200716232543983.png)

装入后：

![在这里插入图片描述](jvm.assets/20200716232559350.png)

# GC垃圾回收

面试题：

- JVM内存模型以及分区，需要详细到每个区放什么
- 堆里面的分区：Eden，survival from to，老年代，各自的特点。
- GC的三种收集方法：标记清除、标记整理、复制算法的原理与特点，分别用在什么地方
- Minor GC与Full GC分别在什么时候发生

JVM垃圾判定算法：（对象已死？）

​	引用计数法(Reference-Counting)
​	可达性分析算法（根搜索算法）

GC垃圾回收主要有四大算法：（怎么找到已死对象并清除？）

​	复制算法(Copying)
​	标记清除(Mark-Sweep)
​	标记压缩(Mark-Compact)，又称标记整理
​	分代收集算法(Generational-Collection)

YGC和FGC是什么？

YGC：对新生代堆进行gc。频率比较高，因为大部分对象的存货寿命较短，在新生代里被回收。性能耗费较小。

FGC：全堆范围内的gc。默认堆空间使用到达80%（可调整）的时候会触发fgc。以我们生产环境为例，一般比较少会触发fgc，有时10天或者一周左右会有一次。

什么时候执行YGC和FGC?

1. a.eden空间不足，执行young gc
2. b.old空间不足，perm空间不足，调用方法System.gc（），ygc时的悲观策略，dump.live的内存信息时（jmap -dump:live),都会执行full gc



GC的回收流程是怎样的？

1. 对于整个的GC流程里面，那么最需要处理的就是新生代和老年代的内存清理操作，而元空间（永久代）都不在GC范围内
2. 当现在有一个新的对象产生，那么对象一定需要内存空间，平均每个栈内存存4k，每个堆内存存8k，那么对象一定需要进行堆空间的申请。
3. 首先会判断Eden区是否有内存空间，如果此时有内存空间，则直接将新对象保存在伊甸园区。
4. 但是如果此时在伊甸园区内存不足，那么会自动执行一个Minor GC操作，将伊甸园区的无用内存空间进行清理，Minor GC的清理范围只在Eden园区，清理之后会继续判断Eden园区的内存空间是否充足？如果内存空间充足，则将新的对象直接在Eden园区进行空间分配。
5. 如果执行Minor GC之后发现伊甸园区的内存空间依然不足，那么这个时候会执行存活区的判断，如果存活区有剩余空间，则将Eden园区部分活跃对象保存到存活区，那么随后继续判断Eden园区的内存空间是否充足，如果充足则将新的对象直接在Eden园区进行空间分配。
6. 此时如果存活区没有内存空间，则继续判断老年区。则将部分存活对象保存到老年区，而后存活区将有空余空间。
7. 如果这个时候老年代也满了，那么这个时候产生Major GC（Full GC），那么这个时候将进行老年代的清理。
8. 如果老年代执行Full GC之后，无法进行对象的保存，则会产生OOM异常，OutOfMemoryError异常。

# STW

Java中Stop-The-World机制简称STW，是在执行垃圾收集算法时，java应用程序的其他所有线程都被挂起（除了垃圾收集帮助器之外）。Java中一种全局暂停现象，全局停顿，所有Java代码停止，native代码可以执行，但不能与JVM交互；这些现象多半是由于gc引起。

GC时的Stop the World(STW)是大家最大的敌人。但可能很多人还不清楚，除了GC，JVM下还会发生停顿现象。

# GC的作用区域

JVM结构图：

![在这里插入图片描述](jvm.assets/20200716232622217.png)

堆内存结构：

![在这里插入图片描述](jvm.assets/20200716232636425.png)

GC的特点：

- 次数上频繁收集Young区
- 次数上较少收集Old区
- 基本不动Perm区

# JVM对象创建与内存分配机制深度剖析

**对象的创建**

对象创建的主要流程:

![https://note.youdao.com/yws/public/resource/0e14c4e1fa9ee6b3fda6da53fd20a04d/xmlnote/F0DA643481A54571A854C95BC8F25763/103841](jvm.assets/clipboard-1625987559168.png)

**1.类加载检查**

  虚拟机遇到一条new指令时，首先将去检查这个指令的参数是否能在常量池中定位到一个类的符号引用，并且检查这个符号引用代表的类是否已被加载、解析和初始化过。如果没有，那必须先执行相应的类加载过程。

  new指令对应到语言层面上讲是，new关键词、对象克隆、对象序列化等。

**2.分配内存**

  在类加载检查通过后，接下来虚拟机将为新生对象分配内存。对象所需内存的大小在类 加载完成后便可完全确定，为对象分配空间的任务等同于把 一块确定大小的内存从Java堆中划分出来。

这个步骤有两个问题：

1.如何划分内存。

2.在并发情况下， 可能出现正在给对象A分配内存，指针还没来得及修改，对象B又同时使用了原来的指针来分配内存的情况。

**划分内存的方法：**

- “指针碰撞”（Bump the Pointer）(默认用指针碰撞)

如果Java堆中内存是绝对规整的，所有用过的内存都放在一边，空闲的内存放在另一边，中间放着一个指针作为分界点的指示器，那所分配内存就仅仅是把那个指针向空闲空间那边挪动一段与对象大小相等的距离。

- “空闲列表”（Free List）

如果Java堆中的内存并不是规整的，已使用的内存和空 闲的内存相互交错，那就没有办法简单地进行指针碰撞了，虚拟机就必须维护一个列表，记 录上哪些内存块是可用的，在分配的时候从列表中找到一块足够大的空间划分给对象实例， 并更新列表上的记录

**解决并发问题的方法：**

- CAS（compare and swap）

虚拟机采用CAS配上失败重试的方式保证更新操作的原子性来对分配内存空间的动作进行同步处理。

- 本地线程分配缓冲（Thread Local Allocation Buffer,TLAB）

把内存分配的动作按照线程划分在不同的空间之中进行，即每个线程在Java堆中预先分配一小块内存。通过-XX:+/-UseTLAB参数来设定虚拟机是否使用TLAB(JVM会默认开启-XX:+UseTLAB)，-XX:TLABSize 指定TLAB大小。

**3.初始化零值**

内存分配完成后，虚拟机需要将分配到的内存空间都初始化为零值（不包括对象头）， 如果使用TLAB，这一工作过程也可以提前至TLAB分配时进行。这一步操作保证了对象的实例字段在Java代码中可以不赋初始值就直接使用，程序能访问到这些字段的数据类型所对应的零值。

**4.设置对象头**

初始化零值之后，虚拟机要对对象进行必要的设置，例如这个对象是哪个类的实例、如何才能找到类的元数据信息、对象的哈希码、对象的GC分代年龄等信息。这些信息存放在对象的对象头Object Header之中。

在HotSpot虚拟机中，对象在内存中存储的布局可以分为3块区域：对象头（Header）、 实例数据（Instance Data）和对齐填充（Padding）。 HotSpot虚拟机的对象头包括两部分信息，第一部分用于存储对象自身的运行时数据， 如哈希码（HashCode）、GC分代年龄、锁状态标志、线程持有的锁、偏向线程ID、偏向时 间戳等。对象头的另外一部分是类型指针，即对象指向它的类元数据的指针，虚拟机通过这个指针来确定这个对象是哪个类的实例。

**32位对象头**

![https://note.youdao.com/yws/public/resource/0e14c4e1fa9ee6b3fda6da53fd20a04d/xmlnote/581E9FED09B2473C9EF7EB6021BB3872/95007](jvm.assets/clipboard-1625988231574.png)

**64位对象头**

![https://note.youdao.com/yws/public/resource/0e14c4e1fa9ee6b3fda6da53fd20a04d/xmlnote/326F197DC4D5453090AEDD2ACB1A5E8E/100689](jvm.assets/clipboard-1625988374509.png)

对象头在hotspot的C++源码markOop.hpp文件里的注释如下：

```c++
// Bit-format of an object header (most significant first, big endian layout below):
//
//  32 bits:
//  --------
//             hash:25 ------------>| age:4    biased_lock:1 lock:2 (normal object)
//             JavaThread*:23 epoch:2 age:4    biased_lock:1 lock:2 (biased object)
//             size:32 ------------------------------------------>| (CMS free block)
//             PromotedObject*:29 ---------->| promo_bits:3 ----->| (CMS promoted object)
//
//  64 bits:
//  --------
//  unused:25 hash:31 -->| unused:1   age:4    biased_lock:1 lock:2 (normal object)
//  JavaThread*:54 epoch:2 unused:1   age:4    biased_lock:1 lock:2 (biased object)
//  PromotedObject*:61 --------------------->| promo_bits:3 ----->| (CMS promoted object)
//  size:64 ----------------------------------------------------->| (CMS free block)
//
//  unused:25 hash:31 -->| cms_free:1 age:4    biased_lock:1 lock:2 (COOPs && normal object)
//  JavaThread*:54 epoch:2 cms_free:1 age:4    biased_lock:1 lock:2 (COOPs && biased object)
//  narrowOop:32 unused:24 cms_free:1 unused:4 promo_bits:3 ----->| (COOPs && CMS promoted object)
//  unused:21 size:35 -->| cms_free:1 unused:7 ------------------>| (COOPs && CMS free block)
```

**5.执行方法**

  执行方法，即对象按照程序员的意愿进行初始化。对应到语言层面上讲，就是为属性赋值（注意，这与上面的赋零值不同，这是由程序员赋的值），和执行构造方法。

对象半初始化问题

**对象大小与指针压缩**

对象大小可以用jol-core包查看，引入依赖

```java
<dependency>
    <groupId>org.openjdk.jol</groupId>
    <artifactId>jol-core</artifactId>
    <version>0.9</version>
</dependency>
```

```java
import org.openjdk.jol.info.ClassLayout;

/**
 * 计算对象大小
 */
public class JOLSample {

    public static void main(String[] args) {
        ClassLayout layout = ClassLayout.parseInstance(new Object());
        System.out.println(layout.toPrintable());

        System.out.println();
        ClassLayout layout1 = ClassLayout.parseInstance(new int[]{});
        System.out.println(layout1.toPrintable());

        System.out.println();
        ClassLayout layout2 = ClassLayout.parseInstance(new A());
        System.out.println(layout2.toPrintable());
    }

    // -XX:+UseCompressedOops           默认开启的压缩所有指针
    // -XX:+UseCompressedClassPointers  默认开启的压缩对象头里的类型指针Klass Pointer
    // Oops : Ordinary Object Pointers
    public static class A {
                       //8B mark word
                       //4B Klass Pointer   如果关闭压缩-XX:-UseCompressedClassPointers或-XX:-UseCompressedOops，则占用8B
        int id;        //4B
        String name;   //4B  如果关闭压缩-XX:-UseCompressedOops，则占用8B
        byte b;        //1B 
        Object o;      //4B  如果关闭压缩-XX:-UseCompressedOops，则占用8B
    }
}


运行结果：
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)    //mark word
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)    //mark word     
      8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)    //Klass Pointer
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total


[I object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           6d 01 00 f8 (01101101 00000001 00000000 11111000) (-134217363)
     12     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     16     0    int [I.<elements>                             N/A
Instance size: 16 bytes
Space losses: 0 bytes internal + 0 bytes external = 0 bytes total


com.tuling.jvm.JOLSample$A object internals:
 OFFSET  SIZE               TYPE DESCRIPTION                               VALUE
      0     4                    (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4                    (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4                    (object header)                           61 cc 00 f8 (01100001 11001100 00000000 11111000) (-134165407)
     12     4                int A.id                                      0
     16     1               byte A.b                                       0
     17     3                    (alignment/padding gap)                  
     20     4   java.lang.String A.name                                    null
     24     4   java.lang.Object A.o                                       null
     28     4                    (loss due to the next object alignment)
Instance size: 32 bytes
Space losses: 3 bytes internal + 4 bytes external = 7 bytes total
```

什么是java对象的**指针压缩**？

1.jdk1.6 update14开始，在64bit操作系统中，JVM支持指针压缩

2.jvm配置参数:UseCompressedOops，compressed--压缩、oop(ordinary object pointer)--对象指针

3.启用指针压缩:-XX:+UseCompressedOops(**默认开启**)，禁止指针压缩:-XX:-UseCompressedOops



为什么要进行指针压缩？

1.在64位平台的HotSpot中使用32位指针(实际存储用64位)，内存使用会多出1.5倍左右，使用较大指针在主内存和缓存之间移动数据，**占用较大宽带，同时GC也会承受较大压力**

2.为了减少64位平台下内存的消耗，启用指针压缩功能

3.在jvm中，32位地址最大支持4G内存(2的32次方)，可以通过对对象指针的存入**堆内存**时压缩编码、取出到**cpu寄存器**后解码方式进行优化(对象指针在堆中是32位，在寄存器中是35位，2的35次方=32G)，使得jvm只用32位地址就可以支持更大的内存配置(小于等于32G)

4.堆内存小于4G时，不需要启用指针压缩，jvm会直接去除高32位地址，即使用低虚拟地址空间

5.堆内存大于32G时，压缩指针会失效，会强制使用64位(即8字节)来对java对象寻址，这就会出现1的问题，所以堆内存不要大于32G为好



**关于对齐填充：**对于大部分处理器，对象以8字节整数倍来对齐填充都是最高效的存取方式。



**对象内存分配**

**对象内存分配流程图**

![img](jvm.assets/327202027172.png)

**对象栈上分配**

我们通过JVM内存分配可以知道JAVA中的对象都是在堆上进行分配，当对象没有被引用的时候，需要依靠GC进行回收内存，如果对象数量较多的时候，会给GC带来较大压力，也间接影响了应用的性能。为了减少临时对象在堆内分配的数量，JVM通过**逃逸分析**确定该对象不会被外部访问。如果不会逃逸可以将该对象在**栈上分配**内存，这样该对象所占用的内存空间就可以随栈帧出栈而销毁，就减轻了垃圾回收的压力。

**对象逃逸分析**：就是分析对象动态作用域，当一个对象在方法中被定义后，它可能被外部方法所引用，例如作为调用参数传递到其他地方中。

```java
public User test1() {
   User user = new User();
   user.setId(1);
   user.setName("zhuge");
   //TODO 保存到数据库
   return user;
}

public void test2() {
   User user = new User();
   user.setId(1);
   user.setName("zhuge");
   //TODO 保存到数据库
}
```

很显然test1方法中的user对象被返回了，这个对象的作用域范围不确定，test2方法中的user对象我们可以确定当方法结束这个对象就可以认为是无效对象了，对于这样的对象我们其实可以将其分配在栈内存里，让其在方法结束时跟随栈内存一起被回收掉。

JVM对于这种情况可以通过开启逃逸分析参数(-XX:+DoEscapeAnalysis)来优化对象内存分配位置，使其通过**标量替换**优先分配在栈上(**栈上分配**)，**JDK7之后默认开启逃逸分析**，如果要关闭使用参数(-XX:-DoEscapeAnalysis)

**标量替换：**通过逃逸分析确定该对象不会被外部访问，并且对象可以被进一步分解时，**JVM不会创建该对象**，而是将该对象成员变量分解若干个被这个方法使用的成员变量所代替，这些代替的成员变量在栈帧或寄存器上分配空间，这样就不会因为没有一大块连续空间导致对象内存不够分配。开启标量替换参数(-XX:+EliminateAllocations)，**JDK7之后默认开启**。

**标量与聚合量：**标量即不可被进一步分解的量，而JAVA的基本数据类型就是标量（如：int，long等基本数据类型以及reference类型等），标量的对立就是可以被进一步分解的量，而这种量称之为聚合量。而在JAVA中对象就是可以被进一步分解的聚合量。

**栈上分配示例：**

```
/**
 * 栈上分配，标量替换
 * 代码调用了1亿次alloc()，如果是分配到堆上，大概需要1GB以上堆空间，如果堆空间小于该值，必然会触发GC。
 * 
 * 使用如下参数不会发生GC
 * -Xmx15m -Xms15m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
 * 使用如下参数都会发生大量GC
 * -Xmx15m -Xms15m -XX:-DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
 * -Xmx15m -Xms15m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-EliminateAllocations
 */
public class AllotOnStack {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static void alloc() {
        User user = new User();
        user.setId(1);
        user.setName("zhuge");
    }
}
```

**结论：**栈上分配依赖于逃逸分析和标量替换

**对象在Eden区分配**

大多数情况下，对象在新生代中 Eden 区分配。当 Eden 区没有足够空间进行分配时，虚拟机将发起一次Minor GC。我们来进行实际测试一下。

在测试之前我们先来看看 **Minor GC和Full GC 有什么不同呢？**

- **Minor GC/Young GC**：指发生新生代的的垃圾收集动作，Minor GC非常频繁，回收速度一般也比较快。
- **Major GC/Full GC**：一般会回收老年代 ，年轻代，方法区的垃圾，Major GC的速度一般会比Minor GC的慢10倍以上。

**Eden与Survivor区默认8:1:1**

大量的对象被分配在eden区，eden区满了后会触发minor gc，可能会有99%以上的对象成为垃圾被回收掉，剩余存活的对象会被挪到为空的那块survivor区，下一次eden区满了后又会触发minor gc，把eden区和survivor区垃圾对象回收，把剩余存活的对象一次性挪动到另外一块为空的survivor区，因为新生代的对象都是朝生夕死的，存活时间很短，所以JVM默认的8:1:1的比例是很合适的，**让eden区尽量的大，survivor区够用即可，**

JVM默认有这个参数-XX:+UseAdaptiveSizePolicy(默认开启)，会导致这个8:1:1比例自动变化，如果不想这个比例有变化可以设置参数-XX:-UseAdaptiveSizePolicy

**示例：**

```java
//添加运行JVM参数： -XX:+PrintGCDetails
public class GCTest {
   public static void main(String[] args) throws InterruptedException {
      byte[] allocation1, allocation2/*, allocation3, allocation4, allocation5, allocation6*/;
      allocation1 = new byte[60000*1024];

      //allocation2 = new byte[8000*1024];

      /*allocation3 = new byte[1000*1024];
     allocation4 = new byte[1000*1024];
     allocation5 = new byte[1000*1024];
     allocation6 = new byte[1000*1024];*/
   }
}

运行结果：
Heap
 PSYoungGen      total 76288K, used 65536K [0x000000076b400000, 0x0000000770900000, 0x00000007c0000000)
  eden space 65536K, 100% used [0x000000076b400000,0x000000076f400000,0x000000076f400000)
  from space 10752K, 0% used [0x000000076fe80000,0x000000076fe80000,0x0000000770900000)
  to   space 10752K, 0% used [0x000000076f400000,0x000000076f400000,0x000000076fe80000)
 ParOldGen       total 175104K, used 0K [0x00000006c1c00000, 0x00000006cc700000, 0x000000076b400000)
  object space 175104K, 0% used [0x00000006c1c00000,0x00000006c1c00000,0x00000006cc700000)
 Metaspace       used 3342K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 361K, capacity 388K, committed 512K, reserved 1048576K
```

我们可以看出eden区内存几乎已经被分配完全（即使程序什么也不做，新生代也会使用至少几M内存）。**假如我们再为allocation2分配内存会出现什么情况呢？**

```java
//添加运行JVM参数： -XX:+PrintGCDetails
public class GCTest {
   public static void main(String[] args) throws InterruptedException {
      byte[] allocation1, allocation2/*, allocation3, allocation4, allocation5, allocation6*/;
      allocation1 = new byte[60000*1024];

      allocation2 = new byte[8000*1024];

      /*allocation3 = new byte[1000*1024];
      allocation4 = new byte[1000*1024];
      allocation5 = new byte[1000*1024];
      allocation6 = new byte[1000*1024];*/
   }
}

运行结果：
[GC (Allocation Failure) [PSYoungGen: 65253K->936K(76288K)] 65253K->60944K(251392K), 0.0279083 secs] [Times: user=0.13 sys=0.02, real=0.03 secs] 
Heap
 PSYoungGen      total 76288K, used 9591K [0x000000076b400000, 0x0000000774900000, 0x00000007c0000000)
  eden space 65536K, 13% used [0x000000076b400000,0x000000076bc73ef8,0x000000076f400000)
  from space 10752K, 8% used [0x000000076f400000,0x000000076f4ea020,0x000000076fe80000)
  to   space 10752K, 0% used [0x0000000773e80000,0x0000000773e80000,0x0000000774900000)
 ParOldGen       total 175104K, used 60008K [0x00000006c1c00000, 0x00000006cc700000, 0x000000076b400000)
  object space 175104K, 34% used [0x00000006c1c00000,0x00000006c569a010,0x00000006cc700000)
 Metaspace       used 3342K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 361K, capacity 388K, committed 512K, reserved 1048576K
```

**简单解释一下为什么会出现这种情况：** 因为给allocation2分配内存的时候eden区内存几乎已经被分配完了，我们刚刚讲了当Eden区没有足够空间进行分配时，虚拟机将发起一次Minor GC，GC期间虚拟机又发现allocation1无法存入Survior空间，所以只好把新生代的对象**提前转移到老年代**中去，老年代上的空间足够存放allocation1，所以不会出现Full GC。执行Minor GC后，后面分配的对象如果能够存在eden区的话，还是会在eden区分配内存。可以执行如下代码验证：

```java
public class GCTest {
   public static void main(String[] args) throws InterruptedException {
      byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6;
      allocation1 = new byte[60000*1024];

      allocation2 = new byte[8000*1024];

      allocation3 = new byte[1000*1024];
      allocation4 = new byte[1000*1024];
      allocation5 = new byte[1000*1024];
      allocation6 = new byte[1000*1024];
   }
}

运行结果：
[GC (Allocation Failure) [PSYoungGen: 65253K->952K(76288K)] 65253K->60960K(251392K), 0.0311467 secs] [Times: user=0.08 sys=0.02, real=0.03 secs] 
Heap
 PSYoungGen      total 76288K, used 13878K [0x000000076b400000, 0x0000000774900000, 0x00000007c0000000)
  eden space 65536K, 19% used [0x000000076b400000,0x000000076c09fb68,0x000000076f400000)
  from space 10752K, 8% used [0x000000076f400000,0x000000076f4ee030,0x000000076fe80000)
  to   space 10752K, 0% used [0x0000000773e80000,0x0000000773e80000,0x0000000774900000)
 ParOldGen       total 175104K, used 60008K [0x00000006c1c00000, 0x00000006cc700000, 0x000000076b400000)
  object space 175104K, 34% used [0x00000006c1c00000,0x00000006c569a010,0x00000006cc700000)
 Metaspace       used 3343K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 361K, capacity 388K, committed 512K, reserved 1048576K
```

**大对象直接进入老年代**

大对象就是需要大量连续内存空间的对象（比如：字符串、数组）。JVM参数 -XX:PretenureSizeThreshold 可以设置大对象的大小，如果对象超过设置大小会直接进入老年代，不会进入年轻代，这个参数只在 Serial 和ParNew两个收集器下有效。

比如设置JVM参数：-XX:PretenureSizeThreshold=1000000 (单位是字节)  -XX:+UseSerialGC  ，再执行下上面的第一个程序会发现大对象直接进了老年代

**为什么要这样呢？**

为了避免为大对象分配内存时的复制操作而降低效率。

**长期存活的对象将进入老年代**

既然虚拟机采用了分代收集的思想来管理内存，那么内存回收时就必须能识别哪些对象应放在新生代，哪些对象应放在老年代中。为了做到这一点，虚拟机给每个对象一个对象年龄（Age）计数器。

如果对象在 Eden 出生并经过第一次 Minor GC 后仍然能够存活，并且能被 Survivor 容纳的话，将被移动到 Survivor 空间中，并将对象年龄设为1。对象在 Survivor 中每熬过一次 MinorGC，年龄就增加1岁，当它的年龄增加到一定程度（默认为15岁，CMS收集器默认6岁，不同的垃圾收集器会略微有点不同），就会被晋升到老年代中。对象晋升到老年代的年龄阈值，可以通过参数 **-XX:MaxTenuringThreshold** 来设置。

**对象动态年龄判断**

当前放对象的Survivor区域里(其中一块区域，放对象的那块s区)，一批对象的总大小大于这块Survivor区域内存大小的50%(-XX:TargetSurvivorRatio可以指定)，那么此时**大于等于**这批对象年龄最大值的对象，就可以**直接进入老年代**了，例如Survivor区域里现在有一批对象，年龄1+年龄2+年龄n的多个年龄对象总和超过了Survivor区域的50%，此时就会把年龄n(含)以上的对象都放入老年代。这个规则其实是希望那些可能是长期存活的对象，尽早进入老年代。**对象动态年龄判断机制一般是在minor gc之后触发的。**

**老年代空间分配担保机制**

年轻代每次**minor gc**之前JVM都会计算下老年代**剩余可用空间**

如果这个可用空间小于年轻代里现有的所有对象大小之和(**包括垃圾对象**)

就会看一个“-XX:-HandlePromotionFailure”(jdk1.8默认就设置了)的参数是否设置了

如果有这个参数，就会看看老年代的可用内存大小，是否大于之前每一次minor gc后进入老年代的对象的**平均大小**。

如果上一步结果是小于或者之前说的参数没有设置，那么就会触发一次Full gc，对老年代和年轻代一起回收一次垃圾，如果回收完还是没有足够空间存放新的对象就会发生"OOM"

当然，如果minor gc之后剩余存活的需要挪动到老年代的对象大小还是大于老年代可用空间，那么也会触发full gc，full gc完之后如果还是没有空间放minor gc之后的存活对象，则也会发生“OOM”

![https://note.youdao.com/yws/public/resource/0e14c4e1fa9ee6b3fda6da53fd20a04d/xmlnote/0D278F77B62A48E3985D27019964A6F8/95124](jvm.assets/clipboard-1625990473863.png)

**对象内存回收**

堆中几乎放着所有的对象实例，对堆垃圾回收前的第一步就是要判断哪些对象已经死亡（即不能再被任何途径使用的对象）。

# 垃圾判定

## 引用计数法(Reference-Counting)

引用计数算法是通过判断对象的引用数量来决定对象是否可以被回收。

给对象中添加一个引用计数器，每当有一个地方引用它时，计数器值就加1；当引用失效时，计数器值就减1；任何时刻计数器为0的对象就是不可能再被使用的。

优点：

​	简单，高效，现在的objective-c、python等用的就是这种算法。

缺点：

​	引用和去引用伴随着加减算法，影响性能

​	很难处理循环引用，相互引用的两个对象则无法释放。

因此目前主流的Java虚拟机都摒弃掉了这种算法。

 所谓对象之间的相互引用问题，如下面代码所示：除了对象objA 和 objB 相互引用着对方之外，这两个对象之间再无任何引用。但是他们因为互相引用对方，导致它们的引用计数器都不为0，于是引用计数算法无法通知 GC 回收器回收他们。

```java
public class ReferenceCountingGc {
   Object instance = null;

   public static void main(String[] args) {
      ReferenceCountingGc objA = new ReferenceCountingGc();
      ReferenceCountingGc objB = new ReferenceCountingGc();
      objA.instance = objB;
      objB.instance = objA;
      objA = null;
      objB = null;
   }
}
```



## 可达性分析算法

​	这个算法的基本思想就是通过一系列的称为 **“GC Roots”** 的对象作为起点，从这些节点开始向下搜索，节点所走过的路径称为引用链，当一个对象到 GC Roots 没有任何引用链相连的话，则证明此对象是不可用的。

![img](jvm.assets/20200716232702775.png)

在Java语言中，可以作为GC Roots的对象包括下面几种：

- 虚拟机栈（栈帧中的本地变量表）中的引用对象。
- 方法区中的类静态属性引用的对象。
- 方法区中的常量引用的对象。
- 本地方法栈中JNI（Native方法）的引用对象

真正标记以为对象为可回收状态至少要标记两次。

**finalize()方法最终判定对象是否存活**

即使在可达性分析算法中不可达的对象，也并非是“非死不可”的，这时候它们暂时处于“缓刑”阶段，要真正宣告一个对象死亡，至少要经历再次标记过程。

**标记的前提是对象在进行可达性分析后发现没有与GC Roots相连接的引用链。**

**1. 第一次标记并进行一次筛选。**

筛选的条件是此对象是否有必要执行finalize()方法。

当对象没有覆盖finalize方法，对象将直接被回收。

**2. 第二次标记**

如果这个对象覆盖了finalize方法，finalize方法是对象脱逃死亡命运的最后一次机会，如果对象要在finalize()中成功拯救自己，只要重新与引用链上的任何的一个对象建立关联即可，譬如把自己赋值给某个类变量或对象的成员变量，那在第二次标记时它将移除出“即将回收”的集合。如果对象这时候还没逃脱，那基本上它就真的被回收了。

注意：一个对象的finalize()方法只会被执行一次，也就是说通过调用finalize方法自我救命的机会就一次。

示例代码：

```java
public class OOMTest {

   public static void main(String[] args) {
      List<Object> list = new ArrayList<>();
      int i = 0;
      int j = 0;
      while (true) {
         list.add(new User(i++, UUID.randomUUID().toString()));
         new User(j--, UUID.randomUUID().toString());
      }
   }
}


//User类需要重写finalize方法
@Override
protected void finalize() throws Throwable {
    OOMTest.list.add(this);
    System.out.println("关闭资源，userid=" + id + "即将被回收");
}
```

finalize()方法的运行代价高昂， 不确定性大， 无法保证各个对象的调用顺序， 如今已被官方明确声明为不推荐使用的语法。 有些资料描述它适合做“关闭外部资源”之类的清理性工作， 这完全是对finalize()方法用途的一种自我安慰。 finalize()能做的所有工作， 使用try-finally或者其他方式都可以做得更好、更及时， 所以建议大家完全可以忘掉Java语言里面的这个方法。

**如何判断一个类是无用的类**

方法区主要回收的是无用的类，那么如何判断一个类是无用的类呢？

类需要同时满足下面3个条件才能算是 **“无用的类”** ：

- 该类所有的对象实例都已经被回收，也就是 Java 堆中不存在该类的任何实例。
- 加载该类的 ClassLoader 已经被回收。
- 该类对应的 java.lang.Class 对象没有在任何地方被引用，无法在任何地方通过反射访问该类的方法。

# 四种引用

平时只会用到强引用和软引用。

强引用：

 类似于 Object obj = new Object(); 只要强引用还存在，垃圾收集器永远不会回收掉被引用的对象。

软引用：

 SoftReference 类实现软引用。在系统要发生内存溢出异常之前，才会将这些对象列进回收范围之中进行二次回收。如果这次回收还没有足够的内存，才会抛出内存溢出异常。软引用可用来实现内存敏感的高速缓存。

弱引用：

 WeakReference 类实现弱引用。对象只能生存到下一次垃圾收集之前。在垃圾收集器工作时，无论内存是否足够都会回收掉只被弱引用关联的对象。

虚引用（幽灵引用，幻影引用）：

 PhantomReference 类实现虚引用。无法通过虚引用获取一个对象的实例，为一个对象设置虚引用关联的唯一目的就是能在这个对象被收集器回收时收到一个系统通知。

# 垃圾回收算法

​	在介绍JVM垃圾回收算法前，先介绍一个概念：Stop-the-World

​	Stop-the-world意味着 JVM由于要执行GC而停止了应用程序的执行，并且这种情形会在任何一种GC算法中发生。当Stop-the-world发生时，除了GC所需的线程以外，所有线程都处于等待状态直到GC任务完成。事实上，GC优化很多时候就是指减少Stop-the-world发生的时间，从而使系统具有高吞吐 、低停顿的特点。

## 复制算法(Copying)

​	该算法将内存平均分成两部分，然后每次只使用其中的一部分，当这部分内存满的时候，将内存中所有存活的对象复制到另一个内存中，然后将之前的内存清空，只使用这部分内存，循环下去。

![在这里插入图片描述](jvm.assets/20200716233729519.gif)

优点：

​	实现简单
​	不产生内存碎片

缺点：

​	将内存缩小为原来的一半，浪费了一半的内存空间，代价太高；如果不想浪费一半的空间，就需要有额外的空间进行分配担保，以应对被使用的内存中所有对象都100%存活的极端情况，所以在老年代一般不能直接选用这种算法。

​	如果对象的存活率很高，我们可以极端一点，假设是100%存活，那么我们需要将所有对象都复制一遍，并将所有引用地址重置一遍。复制这一工作所花费的时间，在对象存活率达到一定程度时，将会变的不可忽视。 所以从以上描述不难看出，复制算法要想使用，最起码对象的存活率要非常低才行，而且最重要的是，我们必须要克服50%内存的浪费。

**年轻代中使用的是Minor GC，这种GC算法采用的是复制算法(Copying)。**

​	HotSpot JVM把年轻代分为了三部分：1个Eden区和2个Survivor区（分别叫from和to）。默认比例为8:1:1,一般情况下，新创建的对象都会被分配到Eden区。因为年轻代中的对象基本都是朝生夕死的(90%以上)，所以在年轻代的垃圾回收算法使用的是复制算法。

​	在GC开始的时候，对象只会存在于Eden区和名为“From”的Survivor区，Survivor区“To”是空的。紧接着进行GC，Eden区中所有存活的对象都会被复制到“To”，而在“From”区中，仍存活的对象会根据他们的年龄值来决定去向。对象在Survivor区中每熬过一次Minor GC，年龄就会增加1岁。年龄达到一定值(年龄阈值，可以通过-XX:MaxTenuringThreshold来设置)的对象会被移动到年老代中，没有达到阈值的对象会被复制到“To”区域。经过这次GC后，Eden区和From区已经被清空。这个时候，“From”和“To”会交换他们的角色，也就是新的“To”就是上次GC前的“From”，新的“From”就是上次GC前的“To”。不管怎样，都会保证名为To的Survivor区域是空的。Minor GC会一直重复这样的过程，直到“To”区被填满，“To”区被填满之后，会将所有对象移动到年老代中。

![在这里插入图片描述](jvm.assets/20200716233050504.png)

​	因为Eden区对象一般存活率较低，一般的，使用两块10%的内存作为空闲和活动区间，而另外80%的内存，则是用来给新建对象分配内存的。一旦发生GC，将10%的from活动区间与另外80%中存活的eden对象转移到10%的to空闲区间，接下来，将之前90%的内存全部释放，以此类推。

## 标记清除(Mark-Sweep)

“标记-清除”(Mark Sweep)算法是几种GC算法中最基础的算法，是因为后续的收集算法都是基于这种思路并对其不足进行改进而得到的。正如名字一样，算法分为2个阶段：

1. 标记出需要回收的对象，使用的标记算法均为**可达性分析算法**。
2. 回收被标记的对象。

![在这里插入图片描述](jvm.assets/20200716233655129.gif)

缺点：

- 效率问题（两次遍历）
- 空间问题（标记清除后会产生大量不连续的碎片。JVM就不得不维持一个内存的空闲列表，这又是一种开销。而且在分配数组对象的时候，寻找连续的内存空间会不太好找。）

## 标记压缩(Mark-Compact)

​	标记压缩法（标记-整理法）是标记-清除法的一个改进版。同样，在标记阶段，该算法也将所有对象标记为存活和死亡两种状态；不同的是，在第二个阶段，该算法并没有直接对死亡的对象进行清理，而是通过**所有存活对像都向一端移动，然后直接清除边界以外的内存。**

![在这里插入图片描述](jvm.assets/20200716233710915.gif)

优点：

​	标记/整理算法不仅可以弥补标记/清除算法当中，内存区域分散的缺点，也消除了复制算法当中，内存减半的高额代价。

缺点：

​	 如果存活的对象过多，整理阶段将会执行较多复制操作，导致算法效率降低。

**老年代一般是由标记清除或者是标记清除与标记整理的混合实现。**

![在这里插入图片描述](jvm.assets/20200716233144377.png)

## 分代收集算法(Generational-Collection)

内存效率：复制算法>标记清除算法>标记整理算法（此处的效率只是简单的对比时间复杂度，实际情况不一定如此）。
内存整齐度：复制算法=标记整理算法>标记清除算法。
内存利用率：标记整理算法=标记清除算法>复制算法。

可以看出，效率上来说，复制算法是当之无愧的老大，但是却浪费了太多内存，而为了尽量兼顾上面所提到的三个指标，标记/整理算法相对来说更平滑一些，但效率上依然不尽如人意，它比复制算法多了一个标记的阶段，又比标记/清除多了一个整理内存的过程

难道就没有一种最优算法吗？

回答：无，没有最好的算法，只有最合适的算法。==========>分代收集算法。

分代回收算法实际上是把复制算法和标记整理法的结合，并不是真正一个新的算法，一般分为：老年代（Old Generation）和新生代（Young Generation），老年代就是很少垃圾需要进行回收的，新生代就是有很多的内存空间需要回收，所以不同代就采用不同的回收算法，以此来达到高效的回收算法。

年轻代(Young Gen)

​	 年轻代特点是区域相对老年代较小，对像存活率低。

 	这种情况复制算法的回收整理，速度是最快的。复制算法的效率只和当前存活对象大小有关，因而很适用于年轻代的回收。而复制算法内存利用率不高的问题，通过hotspot中的两个survivor的设计得到缓解。

老年代(Tenure Gen)

​	老年代的特点是区域较大，对像存活率高。

​	这种情况，存在大量存活率高的对像，复制算法明显变得不合适。一般是由标记清除或者是标记清除与标记整理的混合实现。

# 垃圾收集器（了解）

如果说收集算法是内存回收的方法论，垃圾收集器就是内存回收的具体实现。

## Serial收集器

​	串行收集器是最古老，最稳定以及效率高的收集器，可能会产生较长的停顿，只使用一个线程去回收。新生代、老年代使用串行回收；新生代复制算法、老年代标记-压缩；垃圾收集的过程中会Stop The World（服务暂停）

```
参数控制： -XX:+UseSerialGC 串行收集器
```

![在这里插入图片描述](jvm.assets/20200716233216410.png)

## ParNew收集器

ParNew收集器其实就是Serial收集器的多线程版本。新生代并行，老年代串行；新生代复制算法、老年代标记-压缩

参数控制：

`-XX:+UseParNewGC` ParNew收集器
`-XX:ParallelGCThreads` 限制线程数量

![在这里插入图片描述](jvm.assets/20200716233235555.png)

## Parallel收集器

​	Parallel Scavenge收集器类似ParNew收集器，Parallel收集器更关注系统的吞吐量。可以通过参数来打开自适应调节策略，虚拟机会根据当前系统的运行情况收集性能监控信息，动态调整这些参数以提供最合适的停顿时间或最大的吞吐量；也可以通过参数控制GC的时间不大于多少毫秒或者比例；新生代复制算法、老年代标记-压缩

参数控制： `-XX:+UseParallelGC `使用Parallel收集器+ 老年代串行

## Parallel Old 收集器

Parallel Old是Parallel Scavenge收集器的老年代版本，使用多线程和“标记－整理”算法。这个收集器是在JDK 1.6中才开始提供

参数控制： `-XX:+UseParallelOldGC` 使用Parallel收集器+ 老年代并行

## CMS收集器

​	CMS（Concurrent Mark Sweep）收集器是一种以获取最短回收停顿时间为目标的收集器。它需要消耗额外的CPU和内存资源，在CPU和内存资源紧张，CPU较少时，会加重系统负担。CMS无法处理浮动垃圾。CMS的“标记清楚”算法，会导致大量空间碎片的产生。目前很大一部分的Java应用都集中在互联网站或B/S系统的服务端上，这类应用尤其重视服务的响应速度，希望系统停顿时间最短，以给用户带来较好的体验。

​	从名字（包含“Mark Sweep”）上就可以看出CMS收集器是基于“标记-清除”算法实现的，它的运作过程相对于前面几种收集器来说要更复杂一些，整个过程分为4个步骤，包括：

​	初始标记（CMS initial mark）
​	并发标记（CMS concurrent mark）
​	重新标记（CMS remark）
​	并发清除（CMS concurrent sweep）

​	其中初始标记、重新标记这两个步骤仍然需要“Stop The World”。初始标记仅仅只是标记一下GC Roots能直接关联到的对象，速度很快，并发标记阶段就是进行GC Roots Tracing的过程，而重新标记阶段则是为了修正并发标记期间，因用户程序继续运作而导致标记产生变动的那一部分对象的标记记录，这个阶段的停顿时间一般会比初始标记阶段稍长一些，但远比并发标记的时间短。

​	由于整个过程中耗时最长的并发标记和并发清除过程中，收集器线程都可以与用户线程一起工作，所以总体上来说，CMS收集器的内存回收过程是与用户线程一起并发地执行。老年代收集器（新生代使用ParNew）

优点: 并发收集、低停顿
缺点: 产生大量空间碎片、并发阶段会降低吞吐量

参数控制：

`-XX:+UseConcMarkSweepGC` 使用CMS收集器
`-XX:+ UseCMSCompactAtFullCollection` Full GC后，进行一次碎片整理；整理过程是独占的，会引起停顿时间变长
`-XX:+CMSFullGCsBeforeCompaction` 设置进行几次Full GC后，进行一次碎片整理
`-XX:ParallelCMSThreads` 设定CMS的线程数量（一般情况约等于可用CPU数量）

![在这里插入图片描述](jvm.assets/20200716233301779.png)

## G1收集器

G1（Garbage -First）是目前技术发展的最前沿成果之一，HotSpot开发团队赋予它的使命是未来可以替换掉JDK1.5中发布的CMS收集器。与CMS收集器相比G1收集器有以下特点：

1. 空间整合，G1收集器采用标记整理算法，不会产生内存空间碎片。分配大对象时不会因为无法找到连续空间而提前触发下一次GC。
2. 可预测停顿，这是G1的另一大优势，降低停顿时间是G1和CMS的共同关注点，但G1除了追求低停顿外，还能建立可预测的停顿时间模型，能让使用者明确指定在一个长度为N毫秒的时间片段内，消耗在垃圾收集上的时间不得超过N毫秒，这几乎已经是实时Java（RTSJ）的垃圾收集器的特征了。

上面提到的垃圾收集器，收集的范围都是整个新生代或者老年代，而G1不再是这样。使用G1收集器时，Java堆的内存布局与其他收集器有很大差别，它将整个Java堆划分为多个大小相等的独立区域（Region），虽然还保留有新生代和老年代的概念，但新生代和老年代不再是物理隔阂了，它们都是一部分（可以不连续）Region的集合。


![在这里插入图片描述](jvm.assets/20200716233323914.png)

G1的新生代收集跟ParNew类似，当新生代占用达到一定比例的时候，开始触发收集。和CMS类似，G1收集器收集老年代对象会有短暂停顿。

收集步骤：

1、标记阶段，首先初始标记(Initial-Mark),这个阶段是停顿的(Stop the World Event)，并且会触发一次普通Mintor GC。对应GC log:GC pause (young) (inital-mark)

2、Root Region Scanning，程序运行过程中会回收survivor区(存活到老年代)，这一过程必须在young GC之前完成。

3、Concurrent Marking，在整个堆中进行并发标记(和应用程序并发执行)，此过程可能被young GC中断。在并发标记阶段，若发现区域对象中的所有对象都是垃圾，那个这个区域会被立即回收(图中打X)。同时，并发标记过程中，会计算每个区域的对象活性(区域中存活对象的比例)。

![在这里插入图片描述](jvm.assets/2020071623334027.png)

4、Remark, 再标记，会有短暂停顿(STW)。再标记阶段是用来收集 并发标记阶段 产生新的垃圾(并发阶段和应用程序一同运行)；G1中采用了比CMS更快的初始快照算法:snapshot-at-the-beginning (SATB)。

5、Copy/Clean up，多线程清除失活对象，会有STW。G1将回收区域的存活对象拷贝到新区域，清除Remember Sets，并发清空回收区域并把它返回到空闲区域链表中。


![在这里插入图片描述](jvm.assets/20200716233359673.png)

6、复制/清除过程后。回收区域的活性对象已经被集中回收到深蓝色和深绿色区域。

![在这里插入图片描述](jvm.assets/20200716233415572.png)

# 类的实例化顺序

1. 父类静态成员和静态初始化块，按在代码中出现的顺序依次执行
2. 子类静态成员和静态初始化块，按在代码中出现的顺序依次执行
3. 父类实例成员和实例初始化块，按在代码中出现的顺序依次执行
4. 父类构造方法
5. 子类实例成员和实例初始化块，按在代码中出现的顺序依次执行
6. 子类构造方法

# 从JDK源码级别彻底剖析JVM类加载机制

**类加载运行全过程**

当我们用java命令运行某个类的main函数启动程序时，首先需要通过**类加载器**把主类加载到JVM。

```
package com.tuling.jvm;

public class Math {
    public static final int initData = 666;
    public static User user = new User();

    public int compute() {  //一个方法对应一块栈帧内存区域
        int a = 1;
        int b = 2;
        int c = (a + b) * 10;
        return c;
    }

    public static void main(String[] args) {
        Math math = new Math();
        math.compute();
    }

}
```

**通过Java命令执行代码的大体流程如下：**

![img](jvm.assets/clipboard.png)

其中loadClass的类加载过程有如下几步：

**加载 >> 验证 >> 准备 >> 解析 >> 初始化 >>** 使用 >> 卸载

- 加载：在硬盘上查找并通过IO读入字节码文件，使用到类时才会加载，例如调用类的main()方法，new对象等等，在加载阶段会在内存中生成一个**代表这个类的java.lang.Class对象**，作为方法区这个类的各种数据的访问入口
- 验证：校验字节码文件的正确性
- 准备：给类的静态变量分配内存，并赋予默认值
- 解析：将**符号引用**替换为直接引用，该阶段会把一些静态方法(符号引用，比如main()方法)替换为指向数据所存内存的指针或句柄等(直接引用)，这是所谓的**静态链接**过程(类加载期间完成)，**动态链接**是在程序运行期间完成的将符号引用替换为直接引用，下节课会讲到动态链接
- **初始化**：对类的静态变量初始化为指定的值，执行静态代码块

![https://note.youdao.com/yws/public/resource/35faf7c95e69943cdbff4642fcfd5318/xmlnote/8ED5F11F99ED4C29BBBFCBB9C874F20A/102279](jvm.assets/clipboard-1625837478979.png)

类被加载到方法区中后主要包含 **运行时常量池、类型信息、字段信息、方法信息、类加载器的引用、对应class实例的引用**等信息。

**类加载器的引用**：这个类到类加载器实例的引用

**对应class实例的引用**：类加载器在加载类信息放到方法区中后，会创建一个对应的Class 类型的对象实例放到堆(Heap)中, 作为开发人员访问方法区中类定义的入口和切入点。

**注意，**主类在运行过程中如果使用到其它类，会逐步加载这些类。

jar包或war包里的类不是一次性全部加载的，是使用到时才加载。

```java
public class TestDynamicLoad {

    static {
        System.out.println("*************load TestDynamicLoad************");
    }

    public static void main(String[] args) {
        new A();
        System.out.println("*************load test************");
        B b = null;  //B不会加载，除非这里执行 new B()
    }
}

class A {
    static {
        System.out.println("*************load A************");
    }

    public A() {
        System.out.println("*************initial A************");
    }
}

class B {
    static {
        System.out.println("*************load B************");
    }

    public B() {
        System.out.println("*************initial B************");
    }
}

运行结果：
*************load TestDynamicLoad************
*************load A************
*************initial A************
*************load test************
```

**类加载器和双亲委派机制**

上面的类加载过程主要是通过类加载器来实现的，Java里有如下几种类加载器



- 引导类加载器：负责加载支撑JVM运行的位于JRE的lib目录下的核心类库，比如rt.jar、charsets.jar等
- 扩展类加载器：负责加载支撑JVM运行的位于JRE的lib目录下的ext扩展目录中的JAR类包
- 应用程序类加载器：负责加载ClassPath路径下的类包，主要就是加载你自己写的那些类
- 自定义加载器：负责加载用户自定义路径下的类包

看一个**类加载器**示例：

```java
public class TestJDKClassLoader {

    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(com.sun.crypto.provider.DESKeyFactory.class.getClassLoader().getClass().getName());
        System.out.println(TestJDKClassLoader.class.getClassLoader().getClass().getName());

        System.out.println();
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassloader = appClassLoader.getParent();
        ClassLoader bootstrapLoader = extClassloader.getParent();
        System.out.println("the bootstrapLoader : " + bootstrapLoader);
        System.out.println("the extClassloader : " + extClassloader);
        System.out.println("the appClassLoader : " + appClassLoader);

        System.out.println();
        System.out.println("bootstrapLoader加载以下文件：");
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i]);
        }

        System.out.println();
        System.out.println("extClassloader加载以下文件：");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println();
        System.out.println("appClassLoader加载以下文件：");
        System.out.println(System.getProperty("java.class.path"));

    }
}

运行结果：
null
sun.misc.Launcher$ExtClassLoader
sun.misc.Launcher$AppClassLoader

the bootstrapLoader : null
the extClassloader : sun.misc.Launcher$ExtClassLoader@3764951d
the appClassLoader : sun.misc.Launcher$AppClassLoader@14dad5dc

bootstrapLoader加载以下文件：
file:/D:/dev/Java/jdk1.8.0_45/jre/lib/resources.jar
file:/D:/dev/Java/jdk1.8.0_45/jre/lib/rt.jar
file:/D:/dev/Java/jdk1.8.0_45/jre/lib/sunrsasign.jar
file:/D:/dev/Java/jdk1.8.0_45/jre/lib/jsse.jar
file:/D:/dev/Java/jdk1.8.0_45/jre/lib/jce.jar
file:/D:/dev/Java/jdk1.8.0_45/jre/lib/charsets.jar
file:/D:/dev/Java/jdk1.8.0_45/jre/lib/jfr.jar
file:/D:/dev/Java/jdk1.8.0_45/jre/classes

extClassloader加载以下文件：
D:\dev\Java\jdk1.8.0_45\jre\lib\ext;C:\Windows\Sun\Java\lib\ext

appClassLoader加载以下文件：
D:\dev\Java\jdk1.8.0_45\jre\lib\charsets.jar;D:\dev\Java\jdk1.8.0_45\jre\lib\deploy.jar;D:\dev\Java\jdk1.8.0_45\jre\lib\ext\access-bridge-64.jar;D:\dev\Java\jdk1.8.0_45\jre\lib\ext\cldrdata.jar;D:\dev\Java\jdk1.8.0_45\jre\lib\ext\dnsns.jar;D:\dev\Java\jdk1.8.0_45\jre\lib\ext\jaccess.jar;D:\dev\Java\jdk1.8.0_45\jre\lib\ext\jfxrt.jar;D:\dev\Java\jdk1.8.0_45\jre\lib\ext\localedata.jar;D:\dev\Java\jdk1.8.0_45\jre\lib\ext\nashorn.jar;D:\dev\Java\jdk1.8.0_45\jre\lib\ext\sunec.jar;D:\dev\Java\jdk1.8.0_45\jre\lib\ext\sunjce_provider.jar;D:\dev\Java\jdk1.8.0_45\jre\lib\ext\sunmscapi.jar;D:\dev\Java\jdk1.8.0_45\jre\lib\ext\sunpkcs11.jar;D:\dev\Java\jdk1.8.0_45\jre\lib\ext\zipfs.jar;D:\dev\Java\jdk1.8.0_45\jre\lib\javaws.jar;D:\dev\Java\jdk1.8.0_45\jre\lib\jce.jar;D:\dev\Java\jdk1.8.0_45\jre\lib\jfr.jar;D:\dev\Java\jdk1.8.0_45\jre\lib\jfxswt.jar;D:\dev\Java\jdk1.8.0_45\jre\lib\jsse.jar;D:\dev\Java\jdk1.8.0_45\jre\lib\management-agent.jar;D:\dev\Java\jdk1.8.0_45\jre\lib\plugin.jar;D:\dev\Java\jdk1.8.0_45\jre\lib\resources.jar;D:\dev\Java\jdk1.8.0_45\jre\lib\rt.jar;D:\ideaProjects\project-all\target\classes;C:\Users\zhuge\.m2\repository\org\apache\zookeeper\zookeeper\3.4.12\zookeeper-3.4.12.jar;C:\Users\zhuge\.m2\repository\org\slf4j\slf4j-api\1.7.25\slf4j-api-1.7.25.jar;C:\Users\zhuge\.m2\repository\org\slf4j\slf4j-log4j12\1.7.25\slf4j-log4j12-1.7.25.jar;C:\Users\zhuge\.m2\repository\log4j\log4j\1.2.17\log4j-1.2.17.jar;C:\Users\zhuge\.m2\repository\jline\jline\0.9.94\jline-0.9.94.jar;C:\Users\zhuge\.m2\repository\org\apache\yetus\audience-annotations\0.5.0\audience-annotations-0.5.0.jar;C:\Users\zhuge\.m2\repository\io\netty\netty\3.10.6.Final\netty-3.10.6.Final.jar;C:\Users\zhuge\.m2\repository\com\google\guava\guava\22.0\guava-22.0.jar;C:\Users\zhuge\.m2\repository\com\google\code\findbugs\jsr305\1.3.9\jsr305-1.3.9.jar;C:\Users\zhuge\.m2\repository\com\google\errorprone\error_prone_annotations\2.0.18\error_prone_annotations-2.0.18.jar;C:\Users\zhuge\.m2\repository\com\google\j2objc\j2objc-annotations\1.1\j2objc-annotations-1.1.jar;C:\Users\zhuge\.m2\repository\org\codehaus\mojo\animal-sniffer-annotations\1.14\animal-sniffer-annotations-1.14.jar;D:\dev\IntelliJ IDEA 2018.3.2\lib\idea_rt.jar

```

**类加载器初始化过程：**

参见类运行加载全过程图可知其中会创建JVM启动器实例sun.misc.Launcher。

在Launcher构造方法内部，其创建了两个类加载器，分别是sun.misc.Launcher.ExtClassLoader(扩展类加载器)和sun.misc.Launcher.AppClassLoader(应用类加载器)。

JVM默认使用Launcher的getClassLoader()方法返回的类加载器AppClassLoader的实例加载我们的应用程序。

```java
//Launcher的构造方法
public Launcher() {
    Launcher.ExtClassLoader var1;
    try {
        //构造扩展类加载器，在构造的过程中将其父加载器设置为null
        var1 = Launcher.ExtClassLoader.getExtClassLoader();
    } catch (IOException var10) {
        throw new InternalError("Could not create extension class loader", var10);
    }

    try {
        //构造应用类加载器，在构造的过程中将其父加载器设置为ExtClassLoader，
        //Launcher的loader属性值是AppClassLoader，我们一般都是用这个类加载器来加载我们自己写的应用程序
        this.loader = Launcher.AppClassLoader.getAppClassLoader(var1);
    } catch (IOException var9) {
        throw new InternalError("Could not create application class loader", var9);
    }

    Thread.currentThread().setContextClassLoader(this.loader);
    String var2 = System.getProperty("java.security.manager");
    。。。 。。。 //省略一些不需关注代码

}
```

**双亲委派机制**

JVM类加载器是有亲子层级结构的，如下图

![https://note.youdao.com/yws/public/resource/35faf7c95e69943cdbff4642fcfd5318/xmlnote/6AE6F2BBE1B6449D90CAFDB5E2E9AAE2/102278](jvm.assets/clipboard-1625838110269.png)

这里类加载其实就有一个**双亲委派机制**，加载某个类时会先委托父加载器寻找目标类，找不到再委托上层父加载器加载，如果所有父加载器在自己的加载类路径下都找不到目标类，则在自己的类加载路径中查找并载入目标类。

比如我们的Math类，最先会找应用程序类加载器加载，应用程序类加载器会先委托扩展类加载器加载，扩展类加载器再委托引导类加载器，顶层引导类加载器在自己的类加载路径里找了半天没找到Math类，则向下退回加载Math类的请求，扩展类加载器收到回复就自己加载，在自己的类加载路径里找了半天也没找到Math类，又向下退回Math类的加载请求给应用程序类加载器，应用程序类加载器于是在自己的类加载路径里找Math类，结果找到了就自己加载了。。

**双亲委派机制说简单点就是，先找父亲加载，不行再由儿子自己加载**

我们来看下应用程序类加载器AppClassLoader加载类的双亲委派机制源码，AppClassLoader的loadClass方法最终会调用其父类ClassLoader的loadClass方法，该方法的大体逻辑如下：

1. 首先，检查一下指定名称的类是否已经加载过，如果加载过了，就不需要再加载，直接返回。
2. 如果此类没有加载过，那么，再判断一下是否有父加载器；如果有父加载器，则由父加载器加载（即调用parent.loadClass(name, false);）.或者是调用bootstrap类加载器来加载。
3. 如果父加载器及bootstrap类加载器都没有找到指定的类，那么调用当前类加载器的findClass方法来完成类加载。

```java
//ClassLoader的loadClass方法，里面实现了双亲委派机制
protected Class<?> loadClass(String name, boolean resolve)
    throws ClassNotFoundException
{
    synchronized (getClassLoadingLock(name)) {
        // 检查当前类加载器是否已经加载了该类
        Class<?> c = findLoadedClass(name);
        if (c == null) {
            long t0 = System.nanoTime();
            try {
                if (parent != null) {  //如果当前加载器父加载器不为空则委托父加载器加载该类
                    c = parent.loadClass(name, false);
                } else {  //如果当前加载器父加载器为空则委托引导类加载器加载该类
                    c = findBootstrapClassOrNull(name);
                }
            } catch (ClassNotFoundException e) {
                // ClassNotFoundException thrown if class not found
                // from the non-null parent class loader
            }

            if (c == null) {
                // If still not found, then invoke findClass in order
                // to find the class.
                long t1 = System.nanoTime();
                //都会调用URLClassLoader的findClass方法在加载器的类路径里查找并加载该类
                c = findClass(name);

                // this is the defining class loader; record the stats
                sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                sun.misc.PerfCounter.getFindClasses().increment();
            }
        }
        if (resolve) {  //不会执行
            resolveClass(c);
        }
        return c;
    }
}
```

**为什么要设计双亲委派机制？**

- 沙箱安全机制：自己写的java.lang.String.class类不会被加载，这样便可以防止核心API库被随意篡改
- 避免类的重复加载：当父亲已经加载了该类时，就没有必要子ClassLoader再加载一次，保证**被加载类的唯一性**

看一个类加载示例：

```java
package java.lang;

public class String {
    public static void main(String[] args) {
        System.out.println("**************My String Class**************");
    }
}

运行结果：
错误: 在类 java.lang.String 中找不到 main 方法, 请将 main 方法定义为:
   public static void main(String[] args)
否则 JavaFX 应用程序类必须扩展javafx.application.Application
```

**全盘负责委托机制**

**“全盘负责”是指当一个ClassLoder装载一个类时，除非显示的使用另外一个ClassLoder，该类所依赖及引用的类也由这个ClassLoder载入。**



**自定义类加载器示例：**

自定义类加载器只需要继承 java.lang.ClassLoader 类，该类有两个核心方法，一个是loadClass(String, boolean)，实现了**双亲委派机制**，还有一个方法是findClass，默认实现是空方法，所以我们自定义类加载器主要是重写findClass方法。

```java
public class MyClassLoaderTest {
    static class MyClassLoader extends ClassLoader {
        private String classPath;

        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        private byte[] loadByte(String name) throws Exception {
            name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classPath + "/" + name
                    + ".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;
        }

        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadByte(name);
                //defineClass将一个字节数组转为Class对象，这个字节数组是class文件读取后最终的字节数组。
                return defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }

    }

    public static void main(String args[]) throws Exception {
        //初始化自定义类加载器，会先初始化父类ClassLoader，其中会把自定义类加载器的父加载器设置为应用程序类加载器AppClassLoader
        MyClassLoader classLoader = new MyClassLoader("D:/test");
        //D盘创建 test/com/tuling/jvm 几级目录，将User类的复制类User1.class丢入该目录
        Class clazz = classLoader.loadClass("com.tuling.jvm.User1");
        Object obj = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("sout", null);
        method.invoke(obj, null);
        System.out.println(clazz.getClassLoader().getClass().getName());
    }
}

运行结果：
=======自己的加载器加载类调用方法=======
com.tuling.jvm.MyClassLoaderTest$MyClassLoader
```

**打破双亲委派机制**

再来一个沙箱安全机制示例，尝试打破双亲委派机制，用自定义类加载器加载我们自己实现的 java.lang.String.class

```java
public class MyClassLoaderTest {
    static class MyClassLoader extends ClassLoader {
        private String classPath;

        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        private byte[] loadByte(String name) throws Exception {
            name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classPath + "/" + name
                    + ".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;

        }

        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadByte(name);
                return defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }

        /**
         * 重写类加载方法，实现自己的加载逻辑，不委派给双亲加载
         * @param name
         * @param resolve
         * @return
         * @throws ClassNotFoundException
         */
        protected Class<?> loadClass(String name, boolean resolve)
                throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);

                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    c = findClass(name);

                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        }
    }

    public static void main(String args[]) throws Exception {
        MyClassLoader classLoader = new MyClassLoader("D:/test");
        //尝试用自己改写类加载机制去加载自己写的java.lang.String.class
        Class clazz = classLoader.loadClass("java.lang.String");
        Object obj = clazz.newInstance();
        Method method= clazz.getDeclaredMethod("sout", null);
        method.invoke(obj, null);
        System.out.println(clazz.getClassLoader().getClass().getName());
    }
}

运行结果：
java.lang.SecurityException: Prohibited package name: java.lang
	at java.lang.ClassLoader.preDefineClass(ClassLoader.java:659)
	at java.lang.ClassLoader.defineClass(ClassLoader.java:758)
```

**Tomcat打破双亲委派机制**

以Tomcat类加载为例，Tomcat 如果使用默认的双亲委派类加载机制行不行？

我们思考一下：Tomcat是个web容器， 那么它要解决什么问题： 

\1. 一个web容器可能需要部署两个应用程序，不同的应用程序可能会**依赖同一个第三方类库的不同版本**，不能要求同一个类库在同一个服务器只有一份，因此要保证每个应用程序的类库都是独立的，保证相互隔离。 

\2. 部署在同一个web容器中**相同的类库相同的版本可以共享**。否则，如果服务器有10个应用程序，那么要有10份相同的类库加载进虚拟机。 

\3. **web容器也有自己依赖的类库，不能与应用程序的类库混淆**。基于安全考虑，应该让容器的类库和程序的类库隔离开来。 

\4. web容器要支持jsp的修改，我们知道，jsp 文件最终也是要编译成class文件才能在虚拟机中运行，但程序运行后修改jsp已经是司空见惯的事情， web容器需要支持 jsp 修改后不用重启。



再看看我们的问题：**Tomcat 如果使用默认的双亲委派类加载机制行不行？** 

答案是不行的。为什么？

第一个问题，如果使用默认的类加载器机制，那么是无法加载两个相同类库的不同版本的，默认的类加器是不管你是什么版本的，只在乎你的全限定类名，并且只有一份。

第二个问题，默认的类加载器是能够实现的，因为他的职责就是保证**唯一性**。

第三个问题和第一个问题一样。

我们再看第四个问题，我们想我们要怎么实现jsp文件的热加载，jsp 文件其实也就是class文件，那么如果修改了，但类名还是一样，类加载器会直接取方法区中已经存在的，修改后的jsp是不会重新加载的。那么怎么办呢？我们可以直接卸载掉这jsp文件的类加载器，所以你应该想到了，每个jsp文件对应一个唯一的类加载器，当一个jsp文件修改了，就直接卸载这个jsp类加载器。重新创建类加载器，重新加载jsp文件。

**Tomcat自定义加载器详解**

![https://note.youdao.com/yws/public/resource/35faf7c95e69943cdbff4642fcfd5318/xmlnote/B5AD127BCF044C05B44783F1D6DBC7E6/102281](jvm.assets/clipboard-1625838310123.png)

tomcat的几个主要类加载器：

- commonLoader：Tomcat最基本的类加载器，加载路径中的class可以被Tomcat容器本身以及各个Webapp访问；
- catalinaLoader：Tomcat容器私有的类加载器，加载路径中的class对于Webapp不可见；
- sharedLoader：各个Webapp共享的类加载器，加载路径中的class对于所有Webapp可见，但是对于Tomcat容器不可见；
- WebappClassLoader：各个Webapp私有的类加载器，加载路径中的class只对当前Webapp可见，比如加载war包里相关的类，每个war包应用都有自己的WebappClassLoader，实现相互隔离，比如不同war包应用引入了不同的spring版本，这样实现就能加载各自的spring版本；



从图中的委派关系中可以看出：

CommonClassLoader能加载的类都可以被CatalinaClassLoader和SharedClassLoader使用，从而实现了公有类库的共用，而CatalinaClassLoader和SharedClassLoader自己能加载的类则与对方相互隔离。

WebAppClassLoader可以使用SharedClassLoader加载到的类，但各个WebAppClassLoader实例之间相互隔离。

而JasperLoader的加载范围仅仅是这个JSP文件所编译出来的那一个.Class文件，它出现的目的就是为了被丢弃：当Web容器检测到JSP文件被修改时，会替换掉目前的JasperLoader的实例，并通过再建立一个新的Jsp类加载器来实现JSP文件的热加载功能。



tomcat 这种类加载机制违背了java 推荐的双亲委派模型了吗？答案是：违背了。 

很显然，tomcat 不是这样实现，tomcat 为了实现隔离性，没有遵守这个约定，**每个webappClassLoader加载自己的目录下的class文件，不会传递给父类加载器，打破了双亲委派机制**。



**模拟实现Tomcat的webappClassLoader加载自己war包应用内不同版本类实现相互共存与隔离**

```java
public class MyClassLoaderTest {
    static class MyClassLoader extends ClassLoader {
        private String classPath;

        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        private byte[] loadByte(String name) throws Exception {
            name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classPath + "/" + name
                    + ".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;

        }

        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadByte(name);
                return defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }

        /**
         * 重写类加载方法，实现自己的加载逻辑，不委派给双亲加载
         * @param name
         * @param resolve
         * @return
         * @throws ClassNotFoundException
         */
        protected Class<?> loadClass(String name, boolean resolve)
                throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);

                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();

                    //非自定义的类还是走双亲委派加载
                    if (!name.startsWith("com.tuling.jvm")){
                        c = this.getParent().loadClass(name);
                    }else{
                        c = findClass(name);
                    }

                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        }
    }

    public static void main(String args[]) throws Exception {
        MyClassLoader classLoader = new MyClassLoader("D:/test");
        Class clazz = classLoader.loadClass("com.tuling.jvm.User1");
        Object obj = clazz.newInstance();
        Method method= clazz.getDeclaredMethod("sout", null);
        method.invoke(obj, null);
        System.out.println(clazz.getClassLoader());
        
        System.out.println();
        MyClassLoader classLoader1 = new MyClassLoader("D:/test1");
        Class clazz1 = classLoader1.loadClass("com.tuling.jvm.User1");
        Object obj1 = clazz1.newInstance();
        Method method1= clazz1.getDeclaredMethod("sout", null);
        method1.invoke(obj1, null);
        System.out.println(clazz1.getClassLoader());
    }
}

运行结果：
=======自己的加载器加载类调用方法=======
com.tuling.jvm.MyClassLoaderTest$MyClassLoader@266474c2

=======另外一个User1版本：自己的加载器加载类调用方法=======
com.tuling.jvm.MyClassLoaderTest$MyClassLoader@66d3c617
```

注意：同一个JVM内，两个相同包名和类名的类对象可以共存，因为他们的类加载器可以不一样，所以看两个类对象是否是同一个，除了看类的包名和类名是否都相同之外，还需要他们的类加载器也是同一个才能认为他们是同一个。



**模拟实现Tomcat的JasperLoader热加载**

原理：后台启动线程监听jsp文件变化，如果变化了找到该jsp对应的servlet类的加载器引用(gcroot)，重新生成新的**JasperLoader**加载器赋值给引用，然后加载新的jsp对应的servlet类，之前的那个加载器因为没有gcroot引用了，下一次gc的时候会被销毁。



附下User类的代码：

```java
package com.tuling.jvm;

public class User {

    private int id;
    private String name;
    
    public User() {
    }

    public User(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sout() {
        System.out.println("=======自己的加载器加载类调用方法=======");
    }
}
```

**补充：Hotspot源码JVM启动执行main方法流程**

![https://note.youdao.com/yws/public/resource/35faf7c95e69943cdbff4642fcfd5318/xmlnote/8471629E3920450596DBF4C8E141FD51/106918](jvm.assets/Hotspot源码JVM启动执行main方法流程.jpg)

# JVM指令手册

栈和局部变量操作 

将常量压入栈的指令 

aconst_null 将null对象引用压入栈 

iconst_m1 将int类型常量-1压入栈 

iconst_0 将int类型常量0压入栈 

iconst_1 将int类型常量1压入**操作数栈** 

iconst_2 将int类型常量2压入栈 

iconst_3 将int类型常量3压入栈 

iconst_4 将int类型常量4压入栈 

iconst_5 将int类型常量5压入栈 

lconst_0 将long类型常量0压入栈 

lconst_1 将long类型常量1压入栈 

fconst_0 将float类型常量0压入栈 

fconst_1 将float类型常量1压入栈 

dconst_0 将double类型常量0压入栈 

dconst_1 将double类型常量1压入栈 

bipush 将一个8位带符号整数压入栈 

sipush 将16位带符号整数压入栈 

ldc 把常量池中的项压入栈 

ldc_w 把常量池中的项压入栈（使用宽索引） 

ldc2_w 把常量池中long类型或者double类型的项压入栈（使用宽索引） 

从栈中的局部变量中装载值的指令 

iload 从局部变量中装载int类型值 

lload 从局部变量中装载long类型值 

fload 从局部变量中装载float类型值 

dload 从局部变量中装载double类型值 

aload 从局部变量中装载引用类型值（refernce） 

iload_0 从局部变量0中装载int类型值 

iload_1 从局部变量1中装载int类型值 

iload_2 从局部变量2中装载int类型值 

iload_3 从局部变量3中装载int类型值 

lload_0 从局部变量0中装载long类型值 

lload_1 从局部变量1中装载long类型值 

lload_2 从局部变量2中装载long类型值 

lload_3 从局部变量3中装载long类型值 

fload_0 从局部变量0中装载float类型值 

fload_1 从局部变量1中装载float类型值



fload_2 从局部变量2中装载float类型值 

fload_3 从局部变量3中装载float类型值 

dload_0 从局部变量0中装载double类型值 

dload_1 从局部变量1中装载double类型值 

dload_2 从局部变量2中装载double类型值 

dload_3 从局部变量3中装载double类型值 

aload_0 从局部变量0中装载引用类型值 

aload_1 从局部变量1中装载引用类型值 

aload_2 从局部变量2中装载引用类型值 

aload_3 从局部变量3中装载引用类型值 

iaload 从数组中装载int类型值 

laload 从数组中装载long类型值 

faload 从数组中装载float类型值 

daload 从数组中装载double类型值 

aaload 从数组中装载引用类型值 

baload 从数组中装载byte类型或boolean类型值 

caload 从数组中装载char类型值 

saload 从数组中装载short类型值 

将栈中的值存入局部变量的指令 

istore 将int类型值存入局部变量 

lstore 将long类型值存入局部变量 

fstore 将float类型值存入局部变量 

dstore 将double类型值存入局部变量 

astore 将将引用类型或returnAddress类型值存入局部变量 

istore_0 将int类型值存入局部变量0 

istore_1 将int类型值存入局部变量1 

istore_2 将int类型值存入局部变量2 

istore_3 将int类型值存入局部变量3 

lstore_0 将long类型值存入局部变量0 

lstore_1 将long类型值存入局部变量1 

lstore_2 将long类型值存入局部变量2 

lstore_3 将long类型值存入局部变量3 

fstore_0 将float类型值存入局部变量0 

fstore_1 将float类型值存入局部变量1 

fstore_2 将float类型值存入局部变量2 

fstore_3 将float类型值存入局部变量3 

dstore_0 将double类型值存入局部变量0 

dstore_1 将double类型值存入局部变量1



dstore_2 将double类型值存入局部变量2 

dstore_3 将double类型值存入局部变量3 

astore_0 将引用类型或returnAddress类型值存入局部变量0 

astore_1 将引用类型或returnAddress类型值存入局部变量1 

astore_2 将引用类型或returnAddress类型值存入局部变量2 

astore_3 将引用类型或returnAddress类型值存入局部变量3 

iastore 将int类型值存入数组中 

lastore 将long类型值存入数组中 

fastore 将float类型值存入数组中 

dastore 将double类型值存入数组中 

aastore 将引用类型值存入数组中 

bastore 将byte类型或者boolean类型值存入数组中 

castore 将char类型值存入数组中 

sastore 将short类型值存入数组中 

wide指令 

wide 使用附加字节扩展局部变量索引 

通用(无类型）栈操作 

nop 不做任何操作 

pop 弹出栈顶端一个字长的内容 

pop2 弹出栈顶端两个字长的内容 

dup 复制栈顶部一个字长内容 

dup_x1 复制栈顶部一个字长的内容，然后将复制内容及原来弹出的两个字长的内容压入 

栈

dup_x2 复制栈顶部一个字长的内容，然后将复制内容及原来弹出的三个字长的内容压入 

栈

dup2 复制栈顶部两个字长内容 

dup2_x1 复制栈顶部两个字长的内容，然后将复制内容及原来弹出的三个字长的内容压入 

栈

dup2_x2 复制栈顶部两个字长的内容，然后将复制内容及原来弹出的四个字长的内容压入 

栈

swap 交换栈顶部两个字长内容 

类型转换 

i2l 把int类型的数据转化为long类型 

i2f 把int类型的数据转化为float类型 

i2d 把int类型的数据转化为double类型 

l2i 把long类型的数据转化为int类型 

l2f 把long类型的数据转化为float类型 

l2d 把long类型的数据转化为double类型



f2i 把float类型的数据转化为int类型 

f2l 把float类型的数据转化为long类型 

f2d 把float类型的数据转化为double类型 

d2i 把double类型的数据转化为int类型 

d2l 把double类型的数据转化为long类型 

d2f 把double类型的数据转化为float类型 

i2b 把int类型的数据转化为byte类型 

i2c 把int类型的数据转化为char类型 

i2s 把int类型的数据转化为short类型 

整数运算 

iadd 执行int类型的加法 

ladd 执行long类型的加法 

isub 执行int类型的减法 

lsub 执行long类型的减法 

imul 执行int类型的乘法 

lmul 执行long类型的乘法 

idiv 执行int类型的除法 

ldiv 执行long类型的除法 

irem 计算int类型除法的余数 

lrem 计算long类型除法的余数 

ineg 对一个int类型值进行取反操作 

lneg 对一个long类型值进行取反操作 

iinc 把一个常量值加到一个int类型的局部变量上 

逻辑运算 

移位操作 

ishl 执行int类型的向左移位操作 

lshl 执行long类型的向左移位操作 

ishr 执行int类型的向右移位操作 

lshr 执行long类型的向右移位操作 

iushr 执行int类型的向右逻辑移位操作 

lushr 执行long类型的向右逻辑移位操作 

按位布尔运算 

iand 对int类型值进行“逻辑与”操作 

land 对long类型值进行“逻辑与”操作 

ior 对int类型值进行“逻辑或”操作 

lor 对long类型值进行“逻辑或”操作 

ixor 对int类型值进行“逻辑异或”操作 

lxor 对long类型值进行“逻辑异或”操作



浮点运算 

fadd 执行float类型的加法 

dadd 执行double类型的加法 

fsub 执行float类型的减法 

dsub 执行double类型的减法 

fmul 执行float类型的乘法 

dmul 执行double类型的乘法 

fdiv 执行float类型的除法 

ddiv 执行double类型的除法 

frem 计算float类型除法的余数 

drem 计算double类型除法的余数 

fneg 将一个float类型的数值取反 

dneg 将一个double类型的数值取反 

对象和数组 

对象操作指令 

new 创建一个新对象 

checkcast 确定对象为所给定的类型 

getfield 从对象中获取字段 

putfield 设置对象中字段的值 

getstatic 从类中获取静态字段 

putstatic 设置类中静态字段的值 

instanceof 判断对象是否为给定的类型 

数组操作指令 

newarray 分配数据成员类型为基本上数据类型的新数组 

anewarray 分配数据成员类型为引用类型的新数组 

arraylength 获取数组长度 

multianewarray 分配新的多维数组 

控制流 

条件分支指令 

ifeq 如果等于0，则跳转 

ifne 如果不等于0，则跳转 

iflt 如果小于0，则跳转 

ifge 如果大于等于0，则跳转 

ifgt 如果大于0，则跳转 

ifle 如果小于等于0，则跳转 

if_icmpcq 如果两个int值相等，则跳转 

if_icmpne 如果两个int类型值不相等，则跳转 

if_icmplt 如果一个int类型值小于另外一个int类型值，则跳转



if_icmpge 如果一个int类型值大于或者等于另外一个int类型值，则跳转 

if_icmpgt 如果一个int类型值大于另外一个int类型值，则跳转 

if_icmple 如果一个int类型值小于或者等于另外一个int类型值，则跳转 

ifnull 如果等于null，则跳转 

ifnonnull 如果不等于null，则跳转 

if_acmpeq 如果两个对象引用相等，则跳转 

if_acmpnc 如果两个对象引用不相等，则跳转 

比较指令 

lcmp 比较long类型值 

fcmpl 比较float类型值（当遇到NaN时，返回-1） 

fcmpg 比较float类型值（当遇到NaN时，返回1） 

dcmpl 比较double类型值（当遇到NaN时，返回-1） 

dcmpg 比较double类型值（当遇到NaN时，返回1） 

无条件转移指令 

goto 无条件跳转 

goto_w 无条件跳转（宽索引） 

表跳转指令 

tableswitch 通过索引访问跳转表，并跳转 

lookupswitch 通过键值匹配访问跳转表，并执行跳转操作 

异常

athrow 抛出异常或错误 

finally子句 

jsr 跳转到子例程 

jsr_w 跳转到子例程（宽索引） 

rct 从子例程返回 

方法调用与返回 

方法调用指令 

invokcvirtual 运行时按照对象的类来调用实例方法 

invokespecial 根据编译时类型来调用实例方法 

invokestatic 调用类（静态）方法 

invokcinterface 调用接口方法 

方法返回指令 

ireturn 从方法中返回int类型的数据 

lreturn 从方法中返回long类型的数据 

freturn 从方法中返回float类型的数据 

dreturn 从方法中返回double类型的数据 

areturn 从方法中返回引用类型的数据 

return 从方法中返回，返回值为void



线程同步 

montiorenter 进入并获取对象监视器 

monitorexit 释放并退出对象监视器 

JVM指令助记符 

变量到操作数栈：iload,iload_,lload,lload_,fload,fload_,dload,dload_,aload,aload_ 

操作数栈到变量： 

istore,istore_,lstore,lstore_,fstore,fstore_,dstore,dstor_,astore,astore_ 

常数到操作数栈： 

bipush,sipush,ldc,ldc_w,ldc2_w,aconst_null,iconst_ml,iconst_,lconst_,fconst_,dconst_ 

加：iadd,ladd,fadd,dadd 

减：isub,lsub,fsub,dsub 

乘：imul,lmul,fmul,dmul 

除：idiv,ldiv,fdiv,ddiv 

余数：irem,lrem,frem,drem 

取负：ineg,lneg,fneg,dneg 

移位：ishl,lshr,iushr,lshl,lshr,lushr 

按位或：ior,lor 

按位与：iand,land 

按位异或：ixor,lxor 

类型转换：i2l,i2f,i2d,l2f,l2d,f2d(放宽数值转换) 

i2b,i2c,i2s,l2i,f2i,f2l,d2i,d2l,d2f(缩窄数值转换) 

创建类实便：new 

创建新数组：newarray,anewarray,multianwarray 

访问类的域和类实例域：getfield,putfield,getstatic,putstatic 

把数据装载到操作数栈：baload,caload,saload,iaload,laload,faload,daload,aaload 

从操作数栈存存储到数组： 

bastore,castore,sastore,iastore,lastore,fastore,dastore,aastore 

获取数组长度：arraylength 

检相类实例或数组属性：instanceof,checkcast 

操作数栈管理：pop,pop2,dup,dup2,dup_xl,dup2_xl,dup_x2,dup2_x2,swap 

有条件转移：ifeq,iflt,ifle,ifne,ifgt,ifge,ifnull,ifnonnull,if_icmpeq,if_icmpene, 

if_icmplt,if_icmpgt,if_icmple,if_icmpge,if_acmpeq,if_acmpne,lcmp,fcmpl 

fcmpg,dcmpl,dcmpg 

复合条件转移：tableswitch,lookupswitch 

无条件转移：goto,goto_w,jsr,jsr_w,ret 

调度对象的实便方法：invokevirtual 

调用由接口实现的方法：invokeinterface 

调用需要特殊处理的实例方法：invokespecial



调用命名类中的静态方法：invokestatic 

方法返回：ireturn,lreturn,freturn,dreturn,areturn,return 

异常：athrow 

finally关键字的实现使用：jsr,jsr_w,ret