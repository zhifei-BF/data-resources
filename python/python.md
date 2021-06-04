# python

查看 Python 版本:

```python
python -V
```

## 第一个 Python3.x 程序

\1. 对于大多数程序语言，第一个入门编程代码便是"Hello World！"，以下代码为使用 Python 输出"Hello World！"：

```python
#!/usr/bin/python3
print("Hello, World!")
```

- `#!/usr/bin/python` ：指定用什么解释器运行脚本以及解释器所在的位置，如果解释器没有装在/usr/bin/目录，改成其所在目录就行了，或者更通用的方法是：
  ​`#!/usr/bin/env python`​。

\2. 你可以将以上代码保存在 `hello.py` 文件中并使用 python 命令执行该脚本文件。

```python
python hello.py
```

\3. 以上命令输出结果为：

```
Hello, World!
```

# Python3 安装

- Python 官网：https://www.python.org/

- Python 文档下载地址：https://www.python.org/doc/

**Unix & Linux 平台安装 Python:**

以下为在 Unix & Linux 平台上安装 Python 的简单步骤：

- 打开 WEB 浏览器访问 https://www.python.org/downloads/source/
- 选择适用于 Unix/Linux 的源码压缩包。
- 下载及解压压缩包 `Python-3.x.x.tgz`，`3.x.x `为你下载的对应版本号。
- 如果你需要自定义一些选项修改 Modules/Setup

```python
# tar -zxvf Python-3.8.5.tgz
# cd Python-3.8.5
# ./configure
# make && make install
```

检查 Python3 是否正常可用：

```python
# python3 -V
Python 3.8.5
```

**Windows 平台安装 Python:**

以下为在 Windows 平台上安装 Python 的简单步骤：

- 打开 WEB 浏览器访问https://www.python.org/downloads/windows/
- 在下载列表中选择 Windows 平台安装包，包格式为：`python-3.8.5-amd64.exe` 文件 ，` -3.8.5` 为你要安装的版本号，`-amd64`为你系统的版本。
- 下载后，双击下载包，进入 Python 安装向导，安装非常简单，你只需要使用默认的设置一直点击"下一步"直到安装完成即可。

```
Windows x86-64 为 64 位版本
Windows x86 为 32 位版本
```

**MAC 平台安装 Python:**

MAC 系统一般都自带有 Python2.7 版本 的环境，你也可以在链接 https://www.python.org/downloads/mac-osx/ 上下载最新版安装。

**集成开发环境（IDE：Integrated Development Environment）: PyCharm**