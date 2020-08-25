# Vim插件之属性目录NERDTree

## 1、下载安装

下载地址：

- 官网：http://www.vim.org/scripts/script.php?script_id=1658
- GitHib：https://github.com/scrooloose/nerdtree

下载并解压后，NERDTree目录下的所有内容解压复制到~/.vim文件夹中。

## 2、基本使用

  【命令】

在linux命令行界面，输入vim；输入：NERDTree ，回车。

  【配置】

在".vimrc"文件中增加下面配置代码：

> " 设置NerdTree
>
> map <F3> :NERDTreeMirror<CR>
>
> map <F3> :NERDTreeToggle<CR>

 

按F3即可显示或隐藏NerdTree区域了。

## 3、常用命令

   进入当前目录的树形界面，通过小键盘"上下"键，能移动选中的目录或文件。目录前面有"+"号，按Enter会展开目录，文件前面是"-"号，按Enter会在右侧窗口展现该文件的内容，并光标的焦点focus右侧。"ctr+w+h"光标focus左侧树形目录，"ctrl+w+l"光标focus右侧文件显示窗口。多次按"ctrl+w"，光标自动在左右侧窗口切换。光标focus左侧树形窗口，按"？"弹出NERDTree的帮助，再次按"？"关闭帮助显示。输入":q"回车，关闭光标所在窗口。

   NERDTree提供了丰富的键盘操作方式来浏览和打开文件，介绍一些常用的快捷键：

- 和编辑文件一样，通过h j k l移动光标定位
- 打开关闭文件或者目录，如果是文件的话，光标出现在打开的文件中
- go 效果同上，不过光标保持在文件目录里，类似预览文件内容的功能
- i和s可以水平分割或纵向分割窗口打开文件，前面加g类似go的功能
- t 在标签页中打开
- T 在后台标签页中打开
- p 到上层目录
- P 到根目录
- K 到同目录第一个节点
- J 到同目录最后一个节点
- m 显示文件系统菜单（添加、删除、移动操作）
- ? 帮助
- q 关闭