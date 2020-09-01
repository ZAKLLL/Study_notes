def swap(treeArr, max, i):
    temp = treeArr[max]
    treeArr[max] = treeArr[i]
    treeArr[i] = temp


# 使得某个节点大于等于它的子节点
def heapify(treeArr, n, i):
    if(i >= n):
        return
    # 左右节点
    c1 = 2*i+1
    c2 = 2*i+2

    max = i
    if(c1 < n and treeArr[c1] > treeArr[max]):
        max = c1
    if(c2 < n and treeArr[c2] > treeArr[max]):
        max = c2

    if(max != i):
        swap(treeArr, max, i)
        heapify(treeArr, n, max)


# 建立大顶推
def build_heap(treeArr, n):
    last_node = n-1
    # 父节点位置
    parent = (last_node-1)//2
    # 从最后一层的父节点开始做heapify,下面的满足了，上面的就满足
    for i in range(parent, -1, -1):
        heapify(treeArr, n, i)


# 大顶堆排序
def heap_sort(treeArr, n):
    build_heap(treeArr, n)
    for i in range(n-1, -1, -1):
        swap(treeArr, i, 0)
        heapify(treeArr, i, 0)


if __name__ == "__main__":
    treeArr = [4, 10, 15, 5, 1, 2, 34, 65, 123, 42]
    n = len(treeArr)
    heap_sort(treeArr, n)
    for i in treeArr:
        print(i)
