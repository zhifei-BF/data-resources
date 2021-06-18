# Javax

@Column 可标注在bean上，内部包含多个属性。

```java
    /**
     * 标签名
     */
    @Column(name = "name", unique = true)
    @ColumnComment(value = "标签名")
    private String name;
```

@Enumerated用于插入数据库，有两种方式：1，按照枚举的索引值插入进去。2.枚举对象作为字符串插入。

```java
public enum EnumType {
    /** Persist enumerated type property or field as an integer. */
    ORDINAL,

    /** Persist enumerated type property or field as a string. */
    STRING
}

```



```java
    /**
     * 可见范围
     */
    @Column(name = "visible")
    @Enumerated(EnumType.STRING)
    @ColumnComment(value = "可见范围")
    private PrincipalType visible;
```

@Id标记为主键。

```java
@Id
private Long id;
```