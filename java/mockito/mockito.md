# Mockito

项目当中如果要用到Mockito的话，需要加入Junit和Mockito的架包。Mockito是依赖于Junit的。

测试时，一般测试的类会加上下面注解：

```java
@RunWith(MockitoJunitRunner.class)
public class xxxx{}
```

或者

```java
@Mock
private AccountDao accountDao;

@Before
public void init(){
    MockitoAnnotations.init(this);
}
```



一般进行某个单元测试前需要Mock它必要的数据。

```java
@Before
public void setUp(){
    this.accountDao = Mockito.mock(AccountDao.class);
    this.request = Mockito.mock(HttpServletRequest.class);
    this.accountLoginController = new AccountLoginController(accountDao);
}
```

`when().thenReturn();`用于调用某个方法直接让他返回什么数据。

`assertThat().equalTo();`期望调用某个方法返回的结果是什么。 使方法调用返回期望的值。也被称为stubbing。

# 模拟对象

模拟对象，又叫Mock对象，是通过虚拟对象来代替真实对象。

```
//模拟LinkedList的一个对象
LinkedList mockedList = mock(LinkedList.class)
```

注意：如果调用mock对象的方法，会返回null，因为还没有对方法调用的返回值做模拟，需要通过when(方法调用).thenReturn(返回值)来对返回值做模拟。

同时你也可以对模拟方法调用抛出异常，通过when(方法调用).thenThrow(异常类型)来进行抛出。

没有返回值类型的方法也可以模拟异常抛出：

```java
doThrow(new RuntimeException()).when(mockedList).clear()
```

# 模拟调用方法时的参数匹配

```java
// anyInt()匹配任何int参数，这意味着参数为任意值，其返回值均是element  
when(mockedList.get(anyInt())).thenReturn("element");   
  
// 此时打印是element   
System.out.println(mockedList.get(999)); 
```

# 模拟方法调用次数

```java
// 调用add一次   
mockedList.add("once");   
  
// 下面两个写法验证效果一样，均验证add方法是否被调用了一次  
verify(mockedList).add("once");   
verify(mockedList, times(1)).add("once");  
```

还可以通过atLeast(int i)和atMost(int i)来替代time(int i)来验证被调用的次数最小值和最大值。

