# 最小生成树

特点:

+ 没有环
+ 任意两个点连通



Kruskal算法：

```java
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 克鲁斯卡尔最小生成树
 */
public class Kruskal {

    /*-------------并查集-------------------*/
    private int[] fm;
    private int[] size;

    private void makeSet(int n) {
        fm = new int[n + 1];
        size = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            fm[i] = i;
            size[i] = 1;
        }
    }

    private int find(int node) {
        int fa = fm[node];
        if (fa != node) {
            fa = find(fa);
        }
        fm[node] = fa;
        return fa;
    }


    private boolean isSameSet(int a, int b) {
        return find(a) == find(b);
    }

    private void union(int a, int b) {
        if (a < 0 || b < 0) return;
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) {
            int sizeA = size[a];
            int sizeB = size[b];
            if (sizeA > sizeB) {
                fm[fb] = fa;
                size[fa] = sizeA + sizeB;
            } else {
                fm[fa] = fb;
                size[fb] = sizeA + sizeB;
            }
        }
    }

    /**
     * @param n
     * @param pairs
     * @param weights
     * @return
     */
    private List<int[]> kruskal(int n, int[][] pairs, int[] weights) {
        List<int[]> ret = new ArrayList<>();
        makeSet(n);
        List<int[]> sides = new ArrayList<>();
        for (int i = 0; i < pairs.length; i++) {
            int[] sideWithWeight = {pairs[i][0], pairs[i][1], weights[i]};
            sides.add(sideWithWeight);
        }
        //按照权重升序排列
        sides.sort(Comparator.comparingInt(o -> o[2]));
        for (int[] side : sides) {
            //如果添加该行形成环
            int a = side[0], b = side[1];
            if (!isSameSet(a, b)) {
                union(a, b);
                ret.add(side);
            }
        }
        return ret;
    }
}
```



Prim算法：

```java
import java.lang.*;

class Prim {
    // Number of vertices in the graph
    private int V;

    // A utility function to find the vertex with minimum key
    // value, from the set of vertices not yet included in MST
    int minKey(int[] key, Boolean[] mstSet) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }


    // Function to construct and print MST for a graph represented
    // using adjacency matrix representation
    void primMST(int[][] graph) {
        V = graph.length;

        // Array to store constructed MST
        int[] parent = new int[V];

        // Key values used to pick minimum weight edge in cut
        int[] key = new int[V];

        // To represent set of vertices included in MST
        Boolean[] mstSet = new Boolean[V];

        // Initialize all keys as INFINITE
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Always include first 1st vertex in MST.
        key[0] = 0; // Make key 0 so that this vertex is
        // picked as first vertex
        parent[0] = -1; // First node is always root of MST

        // The MST will have V vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick thd minimum key vertex from the set of vertices
            // not yet included in MST
            int u = minKey(key, mstSet);

            // Add the picked vertex to the MST Set
            mstSet[u] = true;

            // Update key value and parent index of the adjacent
            // vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < V; v++)

                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }

        // print the constructed MST
        printMST(parent, graph);
    }
    
    
    // A utility function to print the constructed MST stored in
    // parent[]
    void printMST(int[] parent, int[][] graph) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++)
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
    }

    public static void main(String[] args) {
        /* Let us create the following graph
        2 3
        (0)--(1)--(2)
        | / \ |
        6| 8/ \5 |7
        | /     \ |
        (3)-------(4)
            9         */
        Prim t = new Prim();
        int graph[][] = new int[][]{
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}};

        // Print the solution
        t.primMST(graph);
    }
}
```

```python
    def prim(self,graph: List[List[int]]):
        #求距离mst最近的点
        def minKey(key,mstSet,V):
            minV=float('inf')
            minIndex=-1
            for v in range(0,V):
                if( not mstSet[v] and key[v]<minV ):
                    minV=key[v]
                    minIndex=v
            return minIndex
        V=len(graph)
        parent=[0]*V
        key=[float('inf')]*V
        key[0]=0
        parent[0]=-1
        mstSet=[False]*V
        #最小生成树边数是总点数减一
        for cnt in range (0,V-1):
            u=minKey(key,mstSet,V)
            mstSet[u]=True
            for v in range (0,V):
                if (graph[u][v] and not mstSet[v] and graph[u][v]<key[v]):
                    parent[v]=u
                    key[v]=graph[u][v]
        return parent
```

