# 函数

## SIGN函数

取数字n的符号,大于0返回1, 小于0返回-1, 等于0返回0

```sql
SELECT
	sign( 100 ),
	sign( - 100 ),
	sign( 0 ) 
FROM
	dual;-- 1,-1,0
```

## TO_DSINTERVAL函数

这个函数，可以进行时间的计算，包括天，时，分，秒的计算。

```sql
SELECT
	* 
FROM
	"WP_INFO" 
WHERE
	STARTTIME >= ( SYSDATE - TO_DSINTERVAL( '0 1:00:00' ) ) 
	AND STARTTIME < SYSDATE
```

## TRUNC函数

TRUNC（number,num_digits）
number需要截尾取整的数字。
num_digits用于指定取整精度的数字。num_digits的默认值为 0。如果num_digits为正数，则截取小数点后num_digits位；如果为负数，则先保留整数部分，然后从个位开始向前数，并将遇到的数字都变为0。
TRUNC()函数在截取时不进行四舍五入，直接截取。

针对数字的案例，如：

```sql
select trunc(123.458) from dual --123

select trunc(123.458,0) from dual --123

select trunc(123.458,1) from dual --123.4

select trunc(123.458,-1) from dual --120

select trunc(123.458,-4) from dual --0

select trunc(123.458,4) from dual --123.458

select trunc(123) from dual --123

select trunc(123,1) from dual --123

select trunc(123,-1) from dual --120
```

针对日期的案例，如：

```sql
select trunc(sysdate) from dual --2017/6/13  返回当天的日期

select trunc(sysdate,'yyyy') from dual   --2017/1/1  返回当年第一天.

select trunc(sysdate,'mm') from dual  --2017/6/1  返回当月第一天.

select trunc(sysdate,'d') from dual  --2017/6/11 返回当前星期的第一天(以周日为第一天).

select trunc(sysdate,'dd') from dual  --2017/6/13  返回当前年月日

select trunc(sysdate,'hh') from dual  --2017/6/13 13:00:00  返回当前小时

select trunc(sysdate,'mi') from dual  --2017/6/13 13:06:00  返回当前分钟
```

## CONCAT()函数

CONCAT()函数在Oracle中可以用于将两个字符串连接在一起 .

```SQL
SELECT CONCAT(CONCAT('A', 'B'),'C')
FROM dual;
-- Result: 'ABC'
```

连接单引号

```sql
-- 如果字符串内部包含单引号，我们需要多写一个单引号。
SELECT CONCAT('Let''s', ' learn Oracle')
FROM dual;
-- Result: Let's learn Oracle
```

## TO_DATE()函数

```sql
select TO_DATE('2021-12-31 23:59:59', 'yyyy/mm/dd hh24:mi:ss') from dual;
```

## TO_CHAR()函数

转成字符

```sql
SELECT
	to_char( SYSDATE, 'ddd/ww/w/d/hh/hh24/mi/ss/q/yyyy' ) 
FROM
	dual;
```

| 格式 | 含义             |
| ---- | ---------------- |
| ddd  | 一年的第几天     |
| ww   | 一年的第几个星期 |
| w    | 当月的第几个星期 |
| d    | 这一周的礼拜几   |
| hh   | 小时（12）       |
| hh24 | 小时（24）       |
| mi   | 分钟             |
| ss   | 秒               |
| q    | 季               |
| yyyy | 年               |

## NLS_UPPER()函数

将字母转成大写

```sql
SELECT
	NLS_UPPER( 'abfs234  -=d' ) 
FROM
	dual;-- ABFS234  -=D
```

## NLS_LOWER()函数

将字母转成小写

```SQL
SELECT
	NLS_LOWER( 'AsdSDFSD234' ) 
FROM
	dual;-- asdsdfsd234
```

