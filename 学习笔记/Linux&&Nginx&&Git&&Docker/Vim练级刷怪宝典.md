1. **复制**

- 单行复制
  在命令模式下，将光标移动到将要复制的行处，按“`yy`”进行复制；
- 多行复制
  在命令模式下，将光标移动到将要复制的首行处，按“`nyy`”复制n行；其中n为1、2、3……

2.  **粘贴**

- 在命令模式下，将光标移动到将要粘贴的行处，按“`p`”进行粘贴

3. 移动光标

   h,j,k,l 上，下，左，右
   ctrl-e 移动页面
   ctrl-f 下翻一页
   ctrl-b 上翻一页
   ctrl-u 上翻半页
   ctrl-d 下翻半页
   w 跳到下一个字首，按标点或单词分割
   W 跳到下一个字首，按照blank(空格分割)
   e 跳到下一个字尾， 按单词分割(字母数字下划线)
   E 跳到下一个字尾， 按照blank(空格分割)
   b 跳到上一个字
   B 跳到上一个字，长跳
   0 跳至行首，不管有无缩进，就是跳到第0个字符
   ^ 跳至行首的第一个字符
   $ 跳至行尾

   g_到本行最后一个不是blank字符的位置

   gg 跳至文首
   G 调至文尾
   5gg/5G 调至第5行
   gd 跳至当前光标所在的变量的声明处
   fx 在当前行中找x字符，找到了就跳转至
   ; 重复上一个f命令，而不用重复的输入fx
   %: *匹配括号移动，包括* `(`*,* `{`*,* `[`*. （需要把光标先移到括号上）*

   v : 进入可视化光标选择
   gU : 变大写

   gu  : 变小写

    \* 和*#*:  匹配光标当前所在的单词，移动光标到下一个（或上一个）匹配单词（\*是下一个，#是上一个）*

4. **查找替换**

   /pattern 向后搜索字符串pattern
   ?pattern 向前搜索字符串pattern
   "\c" 忽略大小写
   "\C" 大小写敏感

   n 下一个匹配(如果是/搜索，则是向下的下一个，?搜索则是向上的下一个)
   N 上一个匹配(同上)
   :%s/old/new/g 搜索整个文件，将所有的old替换为new
   :%s/old/new/gc 搜索整个文件，将所有的old替换为new，每次都要你确认是否替换

5. **多行注释**

   先使用Ctrl+v (块选择)进入可视化光标模式，然后选择需要注释的行,大写i +需要注释的内容；

   ​	eg: Ctrl+ v + jjjjj + I +// + esc

6. 全局格式化：

   1. gg 跳转到第一行
   2. shift+v 转到可视模式(选中行)
   3. shift+g 全选
   4. 按下神奇的 =
   
7. **删除**：

8. 历史光标位置:

   1. 上次光标位置:ctrl + o 
   2. 下一个光标位置:ctrl + i