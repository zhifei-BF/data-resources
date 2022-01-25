# C Hello World 实例

```c
#include <stdio.h>
 
int main()
{
   /* 我的第一个 C 程序 */
   printf("Hello, World! \n");
   
   return 0;
}
```

1. 程序的第一行 *#include <stdio.h>* 是预处理器指令，告诉 C 编译器在实际编译之前要包含 stdio.h 文件。
2. 下一行 *int main()* 是主函数，程序从这里开始执行。
3. 下一行 /*...*/ 将会被编译器忽略，这里放置程序的注释内容。它们被称为程序的注释。
4. 下一行 *printf(...)* 是 C 中另一个可用的函数，会在屏幕上显示消息 "Hello, World!"。
5. 下一行 **return 0;** 终止 main() 函数，并返回值 0。

# C 基本语法

## C 的令牌（Token）

C 程序由各种令牌组成，令牌可以是关键字、标识符、常量、字符串值，或者是一个符号。例如，下面的 C 语句包括五个令牌：

```c
printf("Hello, World! \n");
```

这五个令牌分别是：

```c
printf
(
"Hello, World! \n"
)
;
```

## 分号 ;

在 C 程序中，分号是语句结束符。也就是说，每个语句必须以分号结束。它表明一个逻辑实体的结束。

例如，下面是两个不同的语句：

```c
printf("Hello, World! \n");
return 0;
```

## 注释

C 语言有两种注释方式：

```c
// 单行注释
```

以 **//** 开始的单行注释，这种注释可以单独占一行。

```c
/* 单行注释 */
/* 
 多行注释
 多行注释
 多行注释
 */
```

**/\* \*/** 这种格式的注释可以单行或多行。

您不能在注释内嵌套注释，注释也不能出现在字符串或字符值中。

## 标识符

C 标识符是用来标识变量、函数，或任何其他用户自定义项目的名称。一个标识符以字母 A-Z 或 a-z 或下划线 _ 开始，后跟零个或多个字母、下划线和数字（0-9）。

C 标识符内不允许出现标点字符，比如 @、$ 和 %。C 是**区分大小写**的编程语言。因此，在 C 中，*Manpower* 和 *manpower* 是两个不同的标识符。下面列出几个有效的标识符：

```c
mohd       zara    abc   move_name  a_123
myname50   _temp   j     a23b9      retVal
```

## 关键字

下表列出了 C 中的保留字。这些保留字不能作为常量名、变量名或其他标识符名称。

| 关键字   | 说明                                                         |
| :------- | :----------------------------------------------------------- |
| auto     | 声明自动变量                                                 |
| break    | 跳出当前循环                                                 |
| case     | 开关语句分支                                                 |
| char     | 声明字符型变量或函数返回值类型                               |
| const    | 定义常量，如果一个变量被 const 修饰，那么它的值就不能再被改变 |
| continue | 结束当前循环，开始下一轮循环                                 |
| default  | 开关语句中的"其它"分支                                       |
| do       | 循环语句的循环体                                             |
| double   | 声明双精度浮点型变量或函数返回值类型                         |
| else     | 条件语句否定分支（与 if 连用）                               |
| enum     | 声明枚举类型                                                 |
| extern   | 声明变量或函数是在其它文件或本文件的其他位置定义             |
| float    | 声明浮点型变量或函数返回值类型                               |
| for      | 一种循环语句                                                 |
| goto     | 无条件跳转语句                                               |
| if       | 条件语句                                                     |
| int      | 声明整型变量或函数                                           |
| long     | 声明长整型变量或函数返回值类型                               |
| register | 声明寄存器变量                                               |
| return   | 子程序返回语句（可以带参数，也可不带参数）                   |
| short    | 声明短整型变量或函数                                         |
| signed   | 声明有符号类型变量或函数                                     |
| sizeof   | 计算数据类型或变量长度（即所占字节数）                       |
| static   | 声明静态变量                                                 |
| struct   | 声明结构体类型                                               |
| switch   | 用于开关语句                                                 |
| typedef  | 用以给数据类型取别名                                         |
| unsigned | 声明无符号类型变量或函数                                     |
| union    | 声明共用体类型                                               |
| void     | 声明函数无返回值或无参数，声明无类型指针                     |
| volatile | 说明变量在程序执行中可被隐含地改变                           |
| while    | 循环语句的循环条件                                           |

C99 新增关键字

| `_Bool` | `_Complex` | `_Imaginary` | `inline` | `restrict` |
| ------- | ---------- | ------------ | -------- | ---------- |

C11 新增关键字

| `_Alignas`       | `_Alignof`      | `_Atomic` | `_Generic` | `_Noreturn` |
| ---------------- | --------------- | --------- | ---------- | ----------- |
| `_Static_assert` | `_Thread_local` |           |            |             |

## C 中的空格

只包含空格的行，被称为空白行，可能带有注释，C 编译器会完全忽略它。

在 C 中，空格用于描述空白符、制表符、换行符和注释。空格分隔语句的各个部分，让编译器能识别语句中的某个元素（比如 int）在哪里结束，下一个元素在哪里开始。因此，在下面的语句中：

```c
int age;
```

在这里，int 和 age 之间必须至少有一个空格字符（通常是一个空白符），这样编译器才能够区分它们。另一方面，在下面的语句中：

```c
fruit = apples + oranges;   // 获取水果的总数
```

fruit 和 =，或者 = 和 apples 之间的空格字符不是必需的，但是为了增强可读性，您可以根据需要适当增加一些空格。

# C 数据类型

# 枚举

在没有使用枚举之前，我们可以通过宏来定义。

```c
#include <stdio.h>

#define Mon 1
#define Tues 2
#define Wed 3
#define Thurs 4
#define Fri 5
#define Sat 6
#define Sun 7

int main(int argc, char** argv) {
	int day;
	scanf("%d",&day);
	switch(day){
		case Mon: puts("Monday");break;
		case Tues: puts("Tuesday");break;
		case Wed: puts("Wednesday");break;
		case Thurs: puts("Thursday");break;
		case Fri: puts("Friday");break;
		case Sat: puts("Saturday");break;
		case Sun: puts("Sunday");break;
		default: puts("Error!");
	}
	return 0;
}
```

`#define`命令虽然能解决问题，但也带来了不小的副作用，导致宏名过多，代码松散，看起来总有点不舒服。



枚举类型的定义形式为：

```c
enum typeName{ valueName1, valueName2, valueName3, ...... };
```

`enum`是一个新的关键字，专门用来定义枚举类型，这也是它在C语言中的唯一用途；`typeName`是枚举类型的名字；`valueName1, valueName2, valueName3, ......`是每个值对应的名字的列表。注意最后的`;`不能少。

例如，列出一个星期有几天：

```c
enum week{ Mon, Tues, Wed, Thurs, Fri, Sat, Sun };
```

可以看到，我们仅仅给出了名字，却没有给出名字对应的值，这是因为枚举值默认从 0 开始，往后逐个加 1（递增）；也就是说，week 中的 Mon、Tues ...... Sun 对应的值分别为 0、1 ...... 6。

我们也可以给每个名字都指定一个值：

```c
enum week{ Mon = 1, Tues = 2, Wed = 3, Thurs = 4, Fri = 5, Sat = 6, Sun = 7 };
```

更为简单的方法是只给第一个名字指定值：

```c
enum week{ Mon = 1, Tues, Wed, Thurs, Fri, Sat, Sun };
```

这样枚举值就从 1 开始递增，跟上面的写法是等效的。



枚举是一种类型，通过它可以定义枚举变量：

```c
enum week a, b, c;
```

也可以在定义枚举类型的同时定义变量：

```c
enum week{ Mon = 1, Tues, Wed, Thurs, Fri, Sat, Sun } a, b, c;
```

有了枚举变量，就可以把列表中的值赋给它：

```c
enum week{ Mon = 1, Tues, Wed, Thurs, Fri, Sat, Sun };
enum week a = Mon, b = Wed, c = Sat;
```

或者：

```c
enum week{ Mon = 1, Tues, Wed, Thurs, Fri, Sat, Sun } a = Mon, b = Wed, c = Sat;
```

【示例】判断用户输入的是星期几。

```c
#include <stdio.h>

int main(int argc, char** argv) {
	enum week{
		Mon = 1,
		Tues,
		Wed,
		Thurs,
		Fri,
		Sat,
		Sun
	} day ;
	scanf("%d",&day);
	switch(day){
		case Mon: puts("Monday");break;
		case Tues: puts("Tuesday");break;
		case Wed: puts("Wednesday");break;
		case Thurs: puts("Thursday");break;
		case Fri: puts("Friday");break;
		case Sat: puts("Saturday");break;
		case Sun: puts("Sunday");break;
		default: puts("Error!");
	}
	return 0;
}
```

需要注意的两点是：
1) 枚举列表中的 Mon、Tues、Wed 这些标识符的作用范围是全局的（严格来说是 main() 函数内部），不能再定义与它们名字相同的变量。

2) Mon、Tues、Wed 等都是常量，不能对它们赋值，只能将它们的值赋给其他的变量。

枚举和宏其实非常类似：宏在预处理阶段将名字替换成对应的值，枚举在编译阶段将名字替换成对应的值。我们可以将枚举理解为编译阶段的宏。

对于上面的代码，在编译的某个时刻会变成类似下面的样子：

```c
#include <stdio.h>
int main(){
    enum week{ Mon = 1, Tues, Wed, Thurs, Fri, Sat, Sun } day;
    scanf("%d", &day);
    switch(day){
        case 1: puts("Monday"); break;
        case 2: puts("Tuesday"); break;
        case 3: puts("Wednesday"); break;
        case 4: puts("Thursday"); break;
        case 5: puts("Friday"); break;
        case 6: puts("Saturday"); break;
        case 7: puts("Sunday"); break;
        default: puts("Error!");
    }
    return 0;
}
```

Mon、Tues、Wed 这些名字都被替换成了对应的数字。这意味着，Mon、Tues、Wed 等都不是变量，它们不占用数据区（常量区、全局数据区、栈区和堆区）的内存，而是直接被编译到命令里面，放到代码区，所以不能用`&`取得它们的地址。这就是枚举的本质。

我们在《[C语言switch case语句](http://c.biancheng.net/view/1808.html)》一节中讲过，case 关键字后面必须是一个整数，或者是结果为整数的表达式，但不能包含任何变量，正是由于 Mon、Tues、Wed 这些名字最终会被替换成一个整数，所以它们才能放在 case 后面。

枚举类型变量需要存放的是一个整数，我猜测它的长度和 int 应该相同，下面来验证一下：

```c
#include <stdio.h>
int main(){
    enum week{ Mon = 1, Tues, Wed, Thurs, Fri, Sat, Sun } day = Mon;
    //sizeof 获取对应类型所占用的字节数
    printf("%d, %d, %d, %d, %d\n", sizeof(enum week), sizeof(day), sizeof(Mon), sizeof(Wed), sizeof(int) );
    return 0;
}
```

运行结果：
4, 4, 4, 4, 4

# 文件

