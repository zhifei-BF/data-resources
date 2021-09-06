# 也可以自定义异常类，只需要创建一个类继承Exception即可
class CustomException(Exception):
    pass


def add(a, b):
    if a < 0 or b < 0:
        # raise用于向外部抛出异常，后边可以跟一个异常类，或异常类的实例
        # raise Exception

        # 抛出异常的目的，告诉调用者这里调用时出现问题，希望你自己处理一下
        # raise Exception('自定义的异常错误哦')

        # raise CustomException('自定义异常')

        # 也可以通过if else来代替异常的处理
        return None
    r = a + b
    return r


print(add(-123, 456))
