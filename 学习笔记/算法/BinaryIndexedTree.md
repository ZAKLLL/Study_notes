# 树状数组

```java
public class BinaryIndexedTree {
    //树状数组
    private int[] treeArr;
    //原数组，初始化使用
    private int[] sur;

    public BinaryIndexedTree(int[] arr) {
        this.sur = arr;
        this.treeArr = new int[arr.length + 1];
        init();
    }


    private void init() {
        for (int i = 1; i < treeArr.length; i++) {
            int count = lowBit(i);
            int sum = 0;
            for (int j = i; j >= i + 1 - count; j--) {
                sum += sur[j - 1];
            }
            treeArr[i] = sum;
        }
    }

    //树状数组中的sum(l+1,r]
    public int query(int l, int r) {
        int sumR = 0, sumL = 0;
        while (r > 0) {
            sumR += treeArr[r];
            r -= lowBit(r);
        }
        while (l > 0) {
            sumL += treeArr[l];
            l -= lowBit(l);
        }
        return sumR - sumL;
    }

    //树状数组中的第i位
    public void add(int pos, int v) {
        for (int i = pos; i < treeArr.length; i += lowBit(i)) {
            treeArr[i] += v;
        }
    }

    public void update(int pos, int v) {
        for (int i = pos; i < treeArr.length; i += lowBit(i)) {
            treeArr[i] = v;
        }
    }

    private int lowBit(int x) {
        return x & (-x);
    }

}
```