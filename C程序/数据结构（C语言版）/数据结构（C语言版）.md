# 数据结构基本概念

数据结构的三要素：逻辑结构、存储结构（物理结构）、数据的运算。



数据的逻辑结构包含：线性结构、树形结构、图状结构（网状结构）、集合。

数据的存储结构（物理结构）包含：顺序结构、链式结构、索引结构、散列结构。

- 链式结构、索引结构、散列结构可都称为 非顺序结构。



【概念】顺序结构：把逻辑上相邻的元素存储在物理位置上也相邻的存储单元中，元素之间的关系由存储单元的邻接关系来体现。

【概念】链式结构：逻辑上相邻的元素在物理位置上可以不相邻，借助指示元素存储地址的指针来表示元素之间的逻辑关系。

【概念】索引存储：在存储元素信息的同时，还建立附加的索引表。索引表中的每项称为索引项。索引项的一般形式是：（关键字、地址）

【概念】散列存储：根据元素的关键字直接计算出该元素的存储地址，又称为哈希（hash）存储。

# 算法的基本概念

算法的基本概念包含三部分：什么是算法？算法的五个特征   以及   “好”算法的特征。

程序=数据结构+算法

【概念】有穷性：一个算法必须总在执行有穷之后结束，且每一步都可在有穷时间内完成。注：算法必须是有穷的，而程序可以是无穷的。

【概念】确定性：算法中每条指令必须有确切的含义，对于相同的输入只能得出相同的输出。

【概念】可行性：算法中描述的操作都可以通过已实现的基本运算执行有限次来实现。

【概念】输入：一个算法有零个或多个输入，这些输入取自于某个特定的对象的集合。

【概念】输出：一个算法有一个或多个输出，这些输出是与输入有着某种特定关系的量。



【概念】好算法的特性：

1. 正确性。算法应能正确地解决求解问题。
2. 可读性。算法应具有良好的可读性，以帮助人们理解。
3. 健壮性。输入非法数据时，算法能适当地做出反应或进行处理，而不会产生莫名其妙的输出结果。
4. 高效率与低存储量需求。

# 算法的评价

算法效率的度量：时间复杂度、空间复杂度。

【概念】

- 程序性能：运行一个程序所需要的内存大小和时间。
- 空间复杂度：运行完一个程序所需要的临时内存大小。
- 时间复杂度：运行完程序所需要的时间。

【概念】如何估算算法时间复杂度？一个算法由控制结构（顺序、分支和循环三种）和基本结构构成，则算法时间取决于两者的综合效果。

【概念】

- 算法的执行时间 = 基本操作（i）的执行次数 ✖ 基本操作（i）的执行时间。
- 算法的执行时间与基本操作执行次数之和成正比。
- 通常，我们只关注起决定性作用的基本操作，一般是最深层循环内的语句。
- 因此，近似地：算法的时间复杂度用该算法中起决定性作用的基本操作的执行次数估算。

```c
int aFunc(void){
    printf("Hello,World!\n"); //需要执行1次
    return 0; //需要执行1次
}
```

```c
int aFunc(int n){
    printf("Hello,World!\n"); //需要执行1次
    for（int i= 0; i < n; i++){	//需要执行（n+1）次
        printf("Hello,World!\n") //需要执行n次
    }
    return 0; //需要执行1次
}
```

【公式】

1. 加法规则 T(n) = T1(n) + T2(n) = O(f(n)) + O(g(n)) = O(max(f(n),g(n)))
2. 乘法规则 T(n) = T1(n) * T2(n) = O(f(n)) * O(g(n)) = O(f(n) * g(n))

【概念】常见的渐进时间复杂度

- O(1) < O(logn) < O(n) < O(nlogn) < O(n的平方) < O(n的立方) < O(2的n次方) < O(n!) < O(n的n次方)

```c
x++； //时间复杂度就是O(1)
```

```c
for(i=0;i<n;i++) //O(n)
```

```c
for(i=0;i<n;i++){
    for(j=0;j<n;j++){ 	//O(n的平方)
    }
}
```

```c
i=1;	//语句1
while(i<=n)
	i=i*2;	//语句2
	
//设语句2的执行次数是T(n)，则有2的T(n)次方 小于等于 n，即T(n) 小于等于 log以2为底的n 小于等于 c*log以2为底的n，c为正常数。所以它的时间复杂度为O(log以2为底的n)
```

【概念】

- 最坏时间复杂度：最坏情况下算法的时间复杂度。（一般考虑最坏情况下）
- 平均时间复杂度：所有输入示例等概率出现的情况下，算法的期望运行时间。
- 最好时间复杂度：最好情况下算法的时间复杂度。



【概念】空间复杂度：

- 空间复杂度是算法所需存储空间的量度。
- 所需存储空间包括：
  1. 存储程序本身所占用的存储空间。
  2. 程序中的输入和输出数据所占用的存储空间。
  3. 程序在运行过程中临时占用的存储空间。
- 一般情况下，1，2与算法本身是无关的。
- 因此，空间复杂度重点讨论的是运行过程中临时占用的存储空间。
- 若临时空间相对于输入数据量是常数，则称此算法为原地工作或就地工作，即空间复杂度为O(1).

# 线性表

线性表包含两部分：顺序存储和链式存储。

- 顺序存储包含顺序表。

- 链式存储包含单链表、双链表、循环链表和静态链表。

**线性表的定义：**

（1）n（>=0）个数据元素的有限序列，记作（a1，.......，ai，ai+1，.....，an），其中ai是线性表中的数据元素，n是表的长度（n=0为空表，即表中不包含任何元素）。

（2）ai是线性表中的“第i个”元素线性表中的位序。（注意：位序从1开始数组下标是从0开始）

**逻辑特征（n>0）：**

存在唯一的一个被称做“第一个”的数据元素。（如a1）就是表头元素。

存在唯一的一个被称做“最后一个”的数据元素。（如an）就是表尾元素。

除第一个数据元素外，其他元素均只有一个直接前驱。

除最后一个数据元素外，其他元素均只有一个直接后继。

**物理特征：**

元素的数据类型是相同的。

元素的个数是有限的。

**线性表的基本操作：**

```c
InitList(&L) //构造一个空的线性表L
DestroyList(&L) //销毁L
ListEmpty(L) //判断L是否空
ListLength(L) //求L的长度
PriorElem(L,cur_e,&pre_e) //求前驱的值
NextElem(L,cur_e,&next_e) //求后继的值
GetElem(L,i,&e) //取i位置数据元素的值
LocateElem(L,e,equal()) //在线性表中查找e
ListTraverse(L,visit()) //遍历线性表
ClearList(&L) //将L置为空表
ListInsert(&L,i,e) //在i位置插入值为e的数据元素
ListDelete(&L,i,e) //删除i位置的数据元素
```

# 顺序表

优点：可随机存取，存储密度高

缺点：要求大片连续空间，改变容量不方便

【概念】线性表：

线性表是具有相同数据类型的n（n>=0）个数据元素的有限序列。（每个元素占一样的空间）

【概念】顺序表：

用顺序存储的方式实现线性表顺序存储。把逻辑上相邻的元素存储在物理位置上也相邻的存储单元中，元素之间的关系由存储单元的邻接关系来体现。



如何知道一个数据元素大小？

C语言 sizeof（ElemType）



【概念】静态分配（缺点：没有反映元素个数n和表的内在联系）

```C
#define MAX 100
ElemType elem[MAX]; //表
int n; //数据元素个数n<=MAX
```

用结构体将顺序表的相关信息封装起来

```c
#define MAX 100 //最大元素个数
typedef struct
{
    ElemType elem[MAX];
    int length; //元素个数，即表长
}Sqlist;
```

【概念】动态存储

```c
#define LIST_INIT_SIZE 100 //存储空间的初始分配量
#define LISTINCREMENT 10 //分配增量
typedef struct
{
    ElemType *elem; //存储区域的基址
    int length; //当前表的长度
    int listsize； //当前已分配的存储容量
}Sqlist； //顺序表类型
```

注意事项：

1. 动态申请和释放内存空间

2. C——malloc（申请）、free（释放）函数

   malloc函数返回一个指针，需要强制转型为你定义的数据元素类型指针。

   malloc函数的参数，指明要分配多大的连续内存空间。

   L.data = （ElemType *) malloc(sizeof(ElemType) * InitSize)

3. C++ ——new、delete关键字

**顺序表的基本操作**

【概念】初始化顺序表InitList_Sq(&L)

操作结果：构造一个空的顺序表L

```c
Status InitList_Sq(SqList &L) //构造一个空的顺序表L
{
    L.elem = (ElemType*)malloc(LIST_INIT_SIZE * sizeof(ElemType));
    if(!L.elem) exit(OVERFLOW); //存储空间分配失败
    L.length=0; L.listsize=LIST_INIT_SIZE;
    return OK;
} //InitList_Sq
//本算法的时间复杂度为O(1)
```

【概念】求表的长度：L.length

【概念】销毁顺序表DestroyList_Sq(&L)释放L占用的内存空间。

```c
void DestroyList_Sq(SqList &L)
{
    free(L.elem);
    L.elem=NULL;
    L.length=0;
    L.listsize=0;
}
```

【概念】判定是否为空表ListEmpty_Sq(L)

若L为空表，则返回1，否则返回0。

```c
int ListEmpty_Sq(SqList L){
    return (L.length == 0);
}
```

【概念】输出顺序表DispList_Sq(L)

当L不为空时，顺序显示L中各元素的值。

```c
Status DispList_Sq(SqList L)
{
    if(ListEmpty_Sq(L)) return ERROR;
    for(i=0;i<L.length;i++)
    	printf(L.elem[i]);
    return OK;
}
```

【概念】插入数据元素ListInsert_Sq(&L,i,e)

在顺序表L的第i个位置（1<=i<=L.length+1)前插入新元素e。

```c
Status ListInsert_Sq(SqList &L, int i, ElemType e)
{ //在顺序表L中第i个位置之前插入数据元素e
    if(i<1 || i > L.length+1) return ERROR; //i值不合法
    if(L.Length>=L.listsize){ //上溢时，增加空间
        newbase=(ElemType*)realloc(L.elem,(L.listsize+LISTINCREMENT)*sizeof(ElemType));
        if(!newbase) exit(OVERFLOW); //存储分配失败
        L.elem=newbase;
        L.listsize+=LISTINCREMENT;
    }
    for(j=L.length;j>=i;j--)
    	L.elem[j]=L.elem[j-1]; //元素后移（从最后一个元素开始）
    L.elem[i-1]=e;	//在位置i处插入元素e（注意位序与elem下标）
    ++L.length;	//顺序表长度增1
    return OK;
}
```

【概念】时间复杂度（关注最深层循环语句的执行次数与问题规模n的关系，问题规模n=L.length(表长)）

最好情况：新元素插入到表尾，不需要移动元素i=n+1，循环0次；

最好时间复杂度=O(1)

最坏情况：新元素插入到表头，需要将原有的n个元素全都向后移动i=1，循环n次；

最坏时间复杂度=O(n);

平均情况：假设新元素插入到任何一个位置的概率相同，则长度为n的线性表中插入一个结点时，所需节点的移动次数为：n/2

平均时间复杂度=O(n)；

【概念】删除数据元素ListDelete_Sq(&L,i,&e)

删除顺序表L中的第i（1<=i<=L.length）个元素。

```C
Status ListDelete_Sq(SqList &L, int i; ElemType &e)
{
    if(i<1 || i>L.length) return ERROR; //合法位置？
    e=L.elem[i-1];
    for(j=i;j<L.length;j++)
    	L.elem[j-1]=L.elem[j]; //元素前移（从第i+1个位置开始）
    --L.length;	//顺序表长度减1
    return OK;
}
```

【概念】时间复杂度（关注最深层循环语句的执行次数与问题规模n的关系，问题规模n=L.length(表长)）

最好情况：删除表尾元素，不需要移动其他元素i=n，循环0次；

最好时间复杂度=O(1)

最坏情况：删除表头元素，需要将后续的n-1个元素全都向前移动i=1，循环n-1次；

最坏时间复杂度=O(n);

平均情况：假设删除任何一个元素的概率相同，则长度为n的线性表中插入一个结点时，所需节点的移动次数为（n-1）/2

平均时间复杂度=O(n)

【概念】GetElem(L,i):按位查找操作。获取表L中第i个位置的元素的值。

```c
#define InitSize 10 //顺序表的初始长度
typedef struct{
    ElemType *data; //指示动态分配数组的指针
    int MaxSize; //顺序表的最大容量
    int length; //顺序表的当前长度
} SeqList；

ElemType GetElem(SeqList L,int i)
{
    return L.data[i-1];
}
```

时间复杂度：O(1) ,由于顺序表中的各个数据元素在内存中连续存放，因此可以根据起始地址和数据元素大小立即找到第i个元素——”随机存取“特性

【概念】LocateElem(L,e)：按值查找操作。在表L中查找具有给定关键字值的元素。

```c
#define InitSize 10 //顺序表的初始长度
typedef struct{
    ElemType *data; //指示动态分配数组的指针
    int MaxSize; //顺序表的最大容量
    int length; //顺序表的当前长度
} SeqList；

//在顺序表L中查找第一个元素值等于e的元素，并返回其位序
int LocateElem(SeqList L,ElemType e)
{
    for(int i = 0;i < L.length; i++)
    	if(L.data[i]==e)
    		return i+1;	//数组下标为i的元素值等于e，返回其位序i+1
    return 0;	//退出循环，说明查找失败
}
```

【概念】时间复杂度

```c
//在顺序表L中查找第一个元素值等于e的元素，并返回其位序
int LocateElem(SeqList L,ElemType e)
{
    for(int i = 0;i < L.length; i++)
    	if(L.data[i]==e)
    		return i+1;	//数组下标为i的元素值等于e，返回其位序i+1
    return 0;	//退出循环，说明查找失败
}
```

最好情况：目标元素在表头

循环1次；最好时间复杂度=O(1)

最坏情况：目标元素在表尾

循环n次；最坏时间复杂度=O(n)

平均情况：假设目标元素出现在任何一个位置的概率相同，则在长度为n的线性表中查找值为e的元素所需的平均次数为：(n+1)/2

平均时间复杂度=O(n)

# 单链表

每个结点除了存放数据元素外，还要存储指向下一个节点的指针。

优点：不要求大片连续空间，改变容量方便

缺点：不可随机存取，要耗费一定空间存放指针

【概念】定义单链表

```c
typedef struct LNode{ //定义单链表节点类型
    ElemType data;	//数据域
    struct LNode *next;	//指针域
}LNode,*LinkList;

struct LNode *p=(struct LNode*)malloc(sizeof(struct LNode)) //增加一个新的结点：在内存中申请一个结点所需空间，并用指针p指向这个结点
```

typedef关键字——数据类型重命名

typedef <数据类型> <别名>

typedef   int    No_int;

int x=1;  等价于 No_int x = 1;

要表示一个单链表时，只需声明一个头指针L，指向单链表的第一个结点

Lnode *L；// 声明一个指向单链表第一个结点的指针

LinkList    L； //声明一个指向单链表第一个结点的指针

【概念】无头结点单链表

空表时，head为NULL。

第1个结点只能由head指针变量指向，其余结点是直接前驱结点的指针域所指向的结点。即....->next。

【概念】有头结点（便于插入/删除的实现）

空表时，head指向一个结点，即头结点。

除头结点外其余结点均是直接前驱结点的指针域所指向的结点。即....->next。

【概念】定义一个不带头结点的单链表

```c
typedef struct LNode{	//定义单链表结点类型
    ElemType data;	//每个节点存放一个数据元素
    struct LNode *next;	//指针指向下一个节点
}LNode, *LinkList;

//初始化一个空的单链表
bool InitList(LinkList &L){
    L = NULL; //空表，暂时还没有任何结点
    return true;
}

//判断单链表是否为空
bool Empty(LinkList L){
    if(L == NULL)
        return true;
    else
        return false;
}
```

【概念】定义一个带头结点的单链表

```c
typedef struct LNode{	//定义单链表结点类型
    ElemType data;	//每个节点存放一个数据元素
    struct LNode *next;	//指针指向下一个节点
}LNode, *LinkList;

//初始化一个单链表（带头结点）
bool InitList(LinkList &L){
    L = (LNode *)malloc(sizeof(LNode)) //分配一个头结点
    if(L==NULL)
        return false;
    L->next = NULL; //头结点之后暂时还没有节点
    return true;
}

//判断单链表是否为空（带头结点）
bool Empty(LinkList L){
    if(L -> next == NULL)
        return true;
    else
        return false;
}
```

【概念】单链表按位序插入（带头结点）

ListInsert（&L，i，e):插入操作。在表L中的第i个位置上插入指定元素e

```c
status ListInsert(LinkList &L,int i, ElemType e)
{	//将值为e的结点插入到第i个位置
    p=L;j=0;
    while(j < i-1 && p != NULL) //遍历到第i-1个结点或到表尾
    {
        j++;
        p=p->next; //遍历
    } // end of while
    if(p==NULL || j > i-1) return ERROR; //i>表长加1或i<1
    s=(LinkList)malloc(sizeof(LNode)); //创建新结点s
    if(s == NULL) exit(OVERFLOW); //存储分配失败
    s->data=e;	//将s的数据域置为e
    s->next=p->next;
    p->next=s;
    return OK；
}//ListInsert
```

最好时间复杂度T(n)=O(1)

最坏时间复杂度T(n)=O(n)

平均时间复杂度T(n)=O(n)

【概念】单链表按位序插入（不带头结点）

ListInsert（&L，i，e):插入操作。在表L中的第i个位置上插入指定元素e

```c
int ListInsert(LinkList &L,int i, ElemType e)
{
    if(i==1)
    {//对i为1的插入位置特殊处理
        s=(LinkList) malloc(sizeof(LNode));//建新结点s
        if(s==NULL) exit(OVERFLOW);
        s->data=e; s->next=L; //插入结点s
        L=s;	//修改头指针
    } // end of if
    else{ p=L;j=1;...... //与有头结点的类似}
    return OK;
}
```

【概念】单链表的删除（带头结点）

ListDelete(&L,i,&e):删除操作。删除表L中第i个位置的元素，并用e返回删除元素的值。

```c
int ListDelete(LinkList &L, int i, ElemType &e)
{	//设L带头结点
    j=0; p=L;
    while(j<i-1 && p->next) //遍历到第i-1个结点或遍历到表尾
    {
        j++;
        p=p->next;
    }
    if(!(p->next)||j>i-1) return ERROR;//位置不合理
    q=p->next;	//q指向要删除的结点
    e=q->data;	//用e将被删除元素带回到主调函数中
    p->next=q->next;	//从单链表中删除q结点
    free(q);	//释放q结点
    return OK;
}
```

【概念】单链表的查找

GetElem(L,i):按位查找操作。获取表L中第i个位置的元素的值。

LocateElem(L,e):按值查找操作。在表L中查找具有给定关键字值的元素。

【概念】单链表的按位查找

求表L中指定位置的某个数据元素GetElem(L,i,&e)

在单链表L中从头开始找到第i个结点，若存在第i个结点，则将其data域值赋给变量e。

```c
int GetElem(LinkList L, int i, ElemType &e)
{
    p=L->next;
    j=1;
    while(j<i && p!=NULL) //判断是否找到指定位置或者表尾
    {
        j++;
        p=p->next;
    }
    if(j>i || p==NULL) return 0; //不存在第i个结点
    // 存在第i个结点
    e=p->data;
    return 1;
}
```

时间复杂度为O(n)

【概念】单链表的按值查找

按元素值查找LocateElem(L,e)

在单链表L中从头开始找第1个值域与e相等的结点，若存在，则返回位置，否则返回0。

```c
int LocateElem(LinkList L,ElemType e)
{
    p=L->next;
    n=1;
    while(p!=NULL && p->data != e) //判断是否找到指定位置或者表尾
    {
        p=p->next;
        n++;
    }
    if(p==NULL) return 0;
    else return n;
}
```

时间复杂度为O(n)

【概念】单链表的建立

尾插法——新结点插入到最后一个结点之后。

1. 建立新结点s
2. 向新结点s中添入内容
3. 将新结点s链入链尾：r->next=s
4. 改变尾指针：r=s

```c
void CreateList_LR(LinkList &L, int n)
{	//尾插法创建
    L=(LinkList) malloc(sizeof(LNode));//创建头结点
    L->next=NULL;	//将头结点next域置空
    r=L;	//r始终指向尾结点
    for(i=0;i<n;i++)
    {
        s=(LinkList)malloc(sizeof(LNode));	//创建新结点
        scanf(&s->data);
        r->next=s;	//将s插入r之后
        r=s;	//r指向新尾结点
    }
    r->next=NULL;	//尾结点next域置为NULL
}
```

尾插法创建单链表的时间复杂度：T(n)=O(n)

【概念】单链表的建立

头插法——新结点插入到链表中第1个数据结点之前，即头结点之后。

1. 建立新节点s
2. 向新结点s中添入内容
3. 使新结点s的指针指向第1个数据结点：s->next=L->next
4. 改变头结点指针域：L->next=s

```c
void CreateList_LF(LinkList &L, int n)
{	//建立链表，含n个数据元素
    L = (LinkList)malloc(sizeof(LNode));	//创建头结点
    L->next=NULL;	//将头结点的next域置为空
    for(i=0;i<n;i++)
    {	//创建新结点s
        s=(LinkList)malloc(sizeof(LNode));
        scanf(&s->data);
        //将s插在第1个数据结点之前，即紧跟头结点之后
        s->next=L->next
        L->next=s;
    }
}
```

插入一个结点需时间为：O(1)

头插法创建单链表的时间复杂度:T(n)=O(n)

|        | 顺序表                          | 单链表                         |
| ------ | ------------------------------- | ------------------------------ |
| 特点   | 以地址相邻表示关系上相邻        | 用指针表示关系                 |
| 存储   | 连续的存储空间（静态/动态分配） | 存储空间可以不连续（动态分配） |
| 取元素 | O(1)随机访问                    | O(n)顺序访问                   |
| 定位   | O(n)                            | O(n)                           |
| 插入   | O(n)移动元素                    | O(n)寻找插入位置               |
| 删除   | O(n)移动元素                    | O(n)寻找删除位置               |

