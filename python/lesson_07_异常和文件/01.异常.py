print('hello')
try:
    print(10 / 0)
except:
    print("哈哈哈，出错了")
else:
    print("程序正常执行没有错误")
print('你好')


# 抛出异常ZeroDivisionError: division by zero
# print(10/0)

def fn():
    print('Hello fn()')
    print(10 / 0)


def fn2():
    print('Hello fn2()')
    fn()


def fn3():
    print('Hello fn3()')
    fn2()


fn3()
