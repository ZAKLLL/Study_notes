+ git 添加远程地址：

  + > git remote add origin [url]
  
+ git 查看远程地址

  + > git remote -v
    >
    > git remote show <name>

+ git 修改远程地址：

  + > git remote set-url origin [url]

+ git pull遇到错误：error: Your local changes to the following files would be overwritten by merge:

  ```shell
  git stash
  git pull origin master
  ```

  

+ git 新建并且切换到新分支：

  + git checkout -b branchName

+ 创建分支

  ```
  git branch develop
  ```

   

+ 查看本地分支：

  ```
  git branch
  ```

  

  注:名称前面加* 号的是当前的分支

 + 查看远程分支：

  加上-a参数可以查看远程分支，远程分支会用红色表示出来（如果你开了颜色支持的话）

  ```
  git branch -a
  ```

+ 切换分支

  ```
  git checkout branch_name
  ```

+ 删除本地分支

  ```
  git branch -d branch_name
  ```

+ 删除远程分支

  ```
  git branch -r -d origin/branch-name  
  git push origin :branch-name 
  ```

+ 如果远程新建了一个分支，本地没有该分支。

  可以利用 git checkout --track origin/branch_name ，这时本地会新建一个分支名叫 branch_name ，会自动跟踪远程的同名分支 branch_name。

  ```
  git checkout --track origin/branch_name
  ```

+ 如果本地新建了一个分支 branch_name，但是在远程没有。

  这时候 push 和 pull 指令就无法确定该跟踪谁，一般来说我们都会使其跟踪远程同名分支，所以可以利用 git push --set-upstream origin branch_name ，这样就可以自动在远程创建一个 branch_name 分支，然后本地分支会 track 该分支。后面再对该分支使用 push 和 pull 就自动同步。

  ```
  git push --set-upstream origin branch_name
  ```

+ 合并分支到master上

   首先切换到master分支上

  ```
  git  checkout master
  ```

  如果是多人开发的话 需要把远程master上的代码pull下来

  ```
  git pull origin master
  ```

  然后我们把dev分支的代码合并到master上

  ```
  git  merge dev
  ```

  然后查看状态

  ```
  git status
  ```

+ 远程仓库的重命名与移除

你可以运行 `git remote rename` 来修改一个远程仓库的简写名。 例如，想要将 `pb` 重命名为 `paul`，可以用 `git remote rename` 这样做：

```console
$ git remote rename pb paul
$ git remote
origin
paul
```

值得注意的是这同样也会修改你所有远程跟踪的分支名字。 那些过去引用 `pb/master` 的现在会引用 `paul/master`。

如果因为一些原因想要移除一个远程仓库——你已经从服务器上搬走了或不再想使用某一个特定的镜像了， 又或者某一个贡献者不再贡献了——可以使用 `git remote remove` 或 `git remote rm` ：

```console
$ git remote remove paul
$ git remote
origin
```

一旦你使用这种方式删除了一个远程仓库，那么所有和这个远程仓库相关的远程跟踪分支以及配置信息也会一起被删除。