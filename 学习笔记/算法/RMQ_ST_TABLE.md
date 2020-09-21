# ST表

```java
public class StTable {
    int[][] f = new int[10000006][25];
    //源数组 从1开始
    int[] a;
    //数组长度
    int n = a.length - 1;
    int[] mn = new int[1000006];
    void rmq_init() {
        for (int i = 1; i <= n; i++) f[i][0] = a[i];

        for (int j = 1; (1 << j) <= n; j++) {
            for (int i = 1; i + (1 << j) - 1 <= n; i++) {
                f[i][j] = Math.min(f[i][j - 1], f[i + (1 << (j - 1))][j - 1]);
            }
        }
        for (int len = 1; len <= n; len++) {
            int k = 0;
            while ((1 << (k + 1)) <= len) {
                k++;
            }
            mn[len] = k;
        }
    }

    int rmq(int L, int R) {
        int k = mn[R - L + 1];
        return Math.min(f[L][k], f[R - (1 << k) + 1][k]);
    }

}
```

