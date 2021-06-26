# git命令

下载项目：

```
git clone 项目地址
```

查看本地分支：

```
git branch
```

查看远程全部分支：

```
git branch -a 
```

新建分支：

```
git branch 分支名
```

将新建的分支推送到远端：

```
git push origin 分支名
```

合并某个分支：

```
git merge 分支名
```

推送代码：

```
git push 远程分支名
```

删除分支：

```
git branch -D 分支名
```

创建本地分支，连接到指定远程分支，并切换：

```
git checkout -b 本地分支  origin/远程分支
```

切换到指定的本地分支：

```
git checkout 本地分支
```



# github DNS污染

```
github.com //网站无法访问
raw.githubusercontent.com //图片无法显示
```

刷新本地DNS缓存：

```
ipconfig /flushdns
```

