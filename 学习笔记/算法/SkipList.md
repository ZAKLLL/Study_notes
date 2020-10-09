## SkipList 跳表介绍及代码实现

对于一个单链表，即使链表是有序的，如果我们想要在其中查找某个数据，也只能从头到尾遍历链表，这样效率自然就会很低，跳表就不一样了。跳表是一种可以用来快速查找的数据结构，有点类似于平衡树。它们都可以对元素进行快速的查找。但一个重要的区别是：对平衡树的插入和删除往往很可能导致平衡树进行一次全局的调整。而对跳表的插入和删除只需要对整个数据结构的局部进行操作即可。这样带来的好处是：在高并发的情况下，你会需要一个全局锁来保证整个平衡树的线程安全。而对于跳表，你只需要部分锁即可。这样，在高并发环境下，你就可以拥有更好的性能。而就查询的性能而言，跳表的时间复杂度也是 **O(logn)** 所以在并发数据结构中，JDK 使用跳表来实现一个 Map。

跳表的本质是同时维护了多个链表，并且链表是分层的，

![2级索引跳表](http://my-blog-to-use.oss-cn-beijing.aliyuncs.com/18-12-9/93666217.jpg)

最低层的链表维护了跳表内所有的元素，每上面一层链表都是下面一层的子集。

跳表内的所有链表的元素都是排序的。查找时，可以从顶级链表开始找。一旦发现被查找的元素大于当前链表中的取值，就会转入下一层链表继续找。这也就是说在查找过程中，搜索是跳跃式的。如上图所示，在跳表中查找元素18。

![在跳表中查找元素18](http://my-blog-to-use.oss-cn-beijing.aliyuncs.com/18-12-9/32005738.jpg)

查找18 的时候原来需要遍历 18 次，现在只需要 7 次即可。针对链表长度比较大的时候，构建索引查找效率的提升就会非常明显。

从上面很容易看出，**跳表是一种利用空间换时间的算法。**

使用跳表实现Map 和使用哈希算法实现Map的另外一个不同之处是：哈希并不会保存元素的顺序，而跳表内所有的元素都是排序的。因此在对跳表进行遍历时，你会得到一个有序的结果。所以，如果你的应用需要有序性，那么跳表就是你不二的选择。JDK 中实现这一数据结构的类是ConcurrentSkipListMap。

在这里提供一个简单的跳表算法实现，可用作跳表算法模板。



```java
package algorithm;

import java.util.Random;

/**
 * @author ZhangJiaKui
 * @classname SkipList
 * @description 跳表
 * @date 2020/10/9 15:55
 */
public class SkipList<T> {
    //跳表数据节点数量
    public int n;
    //height
    public int h;
    //head
    private SkipListEntry head;
    //tail
    private SkipListEntry tail;
    //生成randomLevel
    private final Random random;

    public SkipList() {
        //首尾Key设置为极小值极大值
        head = new SkipListEntry(Integer.MIN_VALUE, null);
        tail = new SkipListEntry(Integer.MAX_VALUE, null);
        head.right = tail;
        tail.left = head;
        n = 0;
        h = 0;
        random = new Random();
    }

    public SkipListEntry findEntry(int key) {
        SkipListEntry p = head;
        //跳到最后一层的目标key 的位置上(存在该key)或者key左边的位置上(不存在该key) 类比bisect.left
        while (true) {

            //找到比目标值大的节点
            while (p.right.key != Integer.MAX_VALUE && p.right.key < key) {
                p = p.right;
                //找到目标值
                if (p.key == key) {
                    return p;
                }
            }
            //跳到下一层
            if (p.down != null) {
                p = p.down;
            } else {
                break;
            }
        }
        return p;
    }

    /**
     * map.get()
     *
     * @param key
     * @return
     */
    public T get(int key) {
        SkipListEntry p = findEntry(key);
        if (p.key == key) {
            return p.value;
        }
        return null;
    }

    public void insert(int key, T value) {
        SkipListEntry p, q;
        //查找适合插入的位置
        p = findEntry(key);
        //存在该Key,更新键值对即可
        if (p.key == key) {
            p.value = value;
            return;
        }
        //如果不存在,则需要进行新增(在最后一层进行新增操作)
        q = new SkipListEntry(key, value);
        q.left = p;
        q.right = p.right;
        p.right.left = q;
        p.right = q;

        //最底一层
        int i = 0;

        //抛硬币决定是否向上层插入
        while (random.nextDouble() < 0.5) {
            if (i >= h) {
                addEmptyLevel();
            }
            //找到左边最近一个具有上级索引的entry
            while (p.up == null) {
                p = p.left;
            }
            //更新当前指针位置到上级索引
            p = p.up;
            //需要在上层节点 插入新增的节点作为索引
            SkipListEntry e = new SkipListEntry(key, value);
            //更新上层节点的连接情况
            e.left = p;
            e.right = p.right;
            e.down = q;

            p.right.left = e;
            p.right = e;
            q.up = e;

            //更新q的位置到e,此时p,q仍然相邻,p在q左边,继续循环判断是否需要继续在上方插入节点做索引
            q = e;
            //更新当前所在层数位置
            i++;
        }
        //更新链表长度
        n++;
    }

    /**
     * 在顶部添加一条新的索引
     */
    private void addEmptyLevel() {
        SkipListEntry nHead, nTail;
        nHead = new SkipListEntry(Integer.MIN_VALUE, null);
        nTail = new SkipListEntry(Integer.MAX_VALUE, null);
        nHead.right = nTail;
        nHead.down = head;

        nTail.left = nHead;
        nTail.down = tail;

        head.up = nHead;
        tail.up = nTail;

        head = nHead;
        tail = nTail;

        h++;
    }

    /**
     * 删除指定值
     *
     * @param key
     * @return
     */
    public T remove(int key) {
        SkipListEntry p, tmp;
        p = findEntry(key);
        if (p.key != key) {
            return null;
        }
        T oldValue = p.value;
		//向上迭代直到所有的entry都被删除
        while (p != null) {
            tmp = p.up;
            p.left.right = p.right;
            p.right.left = p.left;
            p = tmp;
        }
        return oldValue;
    }

    /**
     * 跳表节点
     */
    class SkipListEntry {
        Integer key;
        T value;
        SkipListEntry left;
        SkipListEntry right;
        SkipListEntry up;
        SkipListEntry down;

        public SkipListEntry(Integer key, T value) {
            this.key = key;
            this.value = value;
        }
    }
}
```