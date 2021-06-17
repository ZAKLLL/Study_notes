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

+ git reset:

   + git reset –-soft: 回退到某个版本，只回退了commit的信息，不会恢复到index file一级。如果还要提交，直接commit即可；
   + git reset -–hard: 彻底回退到某个版本，本地的源码也会变为上一个版本的内容，撤销的commit中所包含的更改被冲掉；

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

+ git 合并多次commit:

  ```git
  git rebase -i HEAD~<n>
  表明合并当前节点过去n个节点，合并为倒数第n个节点。就可以变为一次commit
  ```


+ 将fork的仓库进行私有化:

  + Create a bare clone of the repository.

    ```shell
    $ git clone --bare https://github.com/exampleuser/old-repository.git
    ```

  + Mirror-push to the new repository.

    ```shell
    $ cd old-repository.git
    $ git push --mirror https://github.com/exampleuser/new-repository.git
    ```

  + Remove the temporary local repository you created earlier.

    ```shell
    $ cd ..
    $ rm -rf old-repository.git
    ```





## git cherry-pick

+ `git cherry-pick`命令的作用，就是将指定的提交（commit）应用于其他分支。

  > ```bash
  > $ git cherry-pick <commitHash>
  > ```

  上面命令就会将指定的提交`commitHash`，应用于当前分支。这会在当前分支产生一个新的提交，当然它们的哈希值会不一样。

  举例来说，代码仓库有`master`和`feature`两个分支。

  > ```bash
  >     a - b - c - d   Master
  >          \
  >            e - f - g Feature
  > ```

  现在将提交`f`应用到`master`分支。

  > ```bash
  > # 切换到 master 分支
  > $ git checkout master
  > 
  > # Cherry pick 操作
  > $ git cherry-pick f
  > ```

  上面的操作完成以后，代码库就变成了下面的样子。

  > ```bash
  >     a - b - c - d - f   Master
  >          \
  >            e - f - g Feature
  > ```

  从上面可以看到，`master`分支的末尾增加了一个提交`f`。

  `git cherry-pick`命令的参数，不一定是提交的哈希值，分支名也是可以的，表示转移该分支的最新提交。

  > ```bash
  > $ git cherry-pick feature
  > ```

  上面代码表示将`feature`分支的最近一次提交，转移到当前分支。

  ## 二、转移多个提交

  Cherry pick 支持一次转移多个提交。

  > ```bash
  > $ git cherry-pick <HashA> <HashB>
  > ```

  上面的命令将 A 和 B 两个提交应用到当前分支。这会在当前分支生成两个对应的新提交。

  如果想要转移一系列的连续提交，可以使用下面的简便语法。

  > ```bash
  > $ git cherry-pick A..B 
  > ```

  上面的命令可以转移从 A 到 B 的所有提交。它们必须按照正确的顺序放置：提交 A 必须早于提交 B，否则命令将失败，但不会报错。

  注意，使用上面的命令，提交 A 将不会包含在 Cherry pick 中。如果要包含提交 A，可以使用下面的语法。

  > ```bash
  > $ git cherry-pick A^..B 
  > ```

  ## 三、配置项

  `git cherry-pick`命令的常用配置项如下。

  **（1）`-e`，`--edit`**

  打开外部编辑器，编辑提交信息。

  **（2）`-n`，`--no-commit`**

  只更新工作区和暂存区，不产生新的提交。

  **（3）`-x`**

  在提交信息的末尾追加一行`(cherry picked from commit ...)`，方便以后查到这个提交是如何产生的。

  **（4）`-s`，`--signoff`**

  在提交信息的末尾追加一行操作者的签名，表示是谁进行了这个操作。

  **（5）`-m parent-number`，`--mainline parent-number`**

  如果原始提交是一个合并节点，来自于两个分支的合并，那么 Cherry pick 默认将失败，因为它不知道应该采用哪个分支的代码变动。

  `-m`配置项告诉 Git，应该采用哪个分支的变动。它的参数`parent-number`是一个从`1`开始的整数，代表原始提交的父分支编号。

  > ```bash
  > $ git cherry-pick -m 1 <commitHash>
  > ```

  上面命令表示，Cherry pick 采用提交`commitHash`来自编号1的父分支的变动。

  一般来说，1号父分支是接受变动的分支（the branch being merged into），2号父分支是作为变动来源的分支（the branch being merged from）。

  ## 四、代码冲突

  如果操作过程中发生代码冲突，Cherry pick 会停下来，让用户决定如何继续操作。

  **（1）`--continue`**

  用户解决代码冲突后，第一步将修改的文件重新加入暂存区（`git add .`），第二步使用下面的命令，让 Cherry pick 过程继续执行。

  > ```bash
  > $ git cherry-pick --continue
  > ```

  **（2）`--abort`**

  发生代码冲突后，放弃合并，回到操作前的样子。

  **（3）`--quit`**

  发生代码冲突后，退出 Cherry pick，但是不回到操作前的样子。

  ## 五、转移到另一个代码库

  Cherry pick 也支持转移另一个代码库的提交，方法是先将该库加为远程仓库。

  > ```bash
  > $ git remote add target git://gitUrl
  > ```

  上面命令添加了一个远程仓库`target`。

  然后，将远程代码抓取到本地。

  > ```bash
  > $ git fetch target
  > ```

  上面命令将远程代码仓库抓取到本地。

  接着，检查一下要从远程仓库转移的提交，获取它的哈希值。

  > ```bash
  > $ git log target/master
  > ```

  最后，使用`git cherry-pick`命令转移提交。

  > ```bash
  > $ git cherry-pick <commitHash>
  > ```



## git rebase

分支开发完成后，很可能有一堆commit，但是合并到主干的时候，往往希望只有一个（或最多两三个）commit，这样不仅清晰，也容易管理。

那么，怎样才能将多个commit合并呢？这就要用到 git rebase 命令。

> ```bash
> $ git rebase -i origin/master
> ```

git rebase命令的i参数表示互动（interactive），这时git会打开一个互动界面，进行下一步操作。

下面采用[Tute Costa](https://robots.thoughtbot.com/git-interactive-rebase-squash-amend-rewriting-history)的例子，来解释怎么合并commit。

> ```bash
> pick 07c5abd Introduce OpenPGP and teach basic usage
> pick de9b1eb Fix PostChecker::Post#urls
> pick 3e7ee36 Hey kids, stop all the highlighting
> pick fa20af3 git interactive rebase, squash, amend
> 
> # Rebase 8db7e8b..fa20af3 onto 8db7e8b
> #
> # Commands:
> #  p, pick = use commit
> #  r, reword = use commit, but edit the commit message
> #  e, edit = use commit, but stop for amending
> #  s, squash = use commit, but meld into previous commit
> #  f, fixup = like "squash", but discard this commit's log message
> #  x, exec = run command (the rest of the line) using shell
> #
> # These lines can be re-ordered; they are executed from top to bottom.
> #
> # If you remove a line here THAT COMMIT WILL BE LOST.
> #
> # However, if you remove everything, the rebase will be aborted.
> #
> # Note that empty commits are commented out
> ```

上面的互动界面，先列出当前分支最新的4个commit（越下面越新）。每个commit前面有一个操作命令，默认是pick，表示该行commit被选中，要进行rebase操作。

4个commit的下面是一大堆注释，列出可以使用的命令。

> - pick：正常选中
> - reword：选中，并且修改提交信息；
> - edit：选中，rebase时会暂停，允许你修改这个commit（参考[这里](https://schacon.github.io/gitbook/4_interactive_rebasing.html)）
> - squash：选中，会将当前commit与上一个commit合并
> - fixup：与squash相同，但不会保存当前commit的提交信息
> - exec：执行其他shell命令

上面这6个命令当中，squash和fixup可以用来合并commit。先把需要合并的commit前面的动词，改成squash（或者s）。

> ```bash
> pick 07c5abd Introduce OpenPGP and teach basic usage
> s de9b1eb Fix PostChecker::Post#urls
> s 3e7ee36 Hey kids, stop all the highlighting
> pick fa20af3 git interactive rebase, squash, amend
> ```

这样一改，执行后，当前分支只会剩下两个commit。第二行和第三行的commit，都会合并到第一行的commit。提交信息会同时包含，这三个commit的提交信息。

> ```bash
> # This is a combination of 3 commits.
> # The first commit's message is:
> Introduce OpenPGP and teach basic usage
> 
> # This is the 2nd commit message:
> Fix PostChecker::Post#urls
> 
> # This is the 3rd commit message:
> Hey kids, stop all the highlighting
> ```

如果将第三行的squash命令改成fixup命令。

> ```bash
> pick 07c5abd Introduce OpenPGP and teach basic usage
> s de9b1eb Fix PostChecker::Post#urls
> f 3e7ee36 Hey kids, stop all the highlighting
> pick fa20af3 git interactive rebase, squash, amend
> ```

运行结果相同，还是会生成两个commit，第二行和第三行的commit，都合并到第一行的commit。但是，新的提交信息里面，第三行commit的提交信息，会被注释掉。

> ```bash
> # This is a combination of 3 commits.
> # The first commit's message is:
> Introduce OpenPGP and teach basic usage
> 
> # This is the 2nd commit message:
> Fix PostChecker::Post#urls
> 
> # This is the 3rd commit message:
> # Hey kids, stop all the highlighting
> ```

