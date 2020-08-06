# 深度遍历
graph = {
    "A": ["B", "C"],
    "B": ["A", "C", "D"],
    "C": ["A", "B", "D", "E"],
    "D": ["B", "C", "E", "F"],
    "E": ["C", "D"],
    "F": ["D"]
}


def DFS(graph, s):
    queue = []  # 使用动态数组作栈
    queue.append(s)
    seen = set()
    seen.add(s)
    parent = {s: None} # <子节点:父节点>
    while (len(queue) > 0):
        vertex = queue.pop()
        nodes = graph[vertex]
        for w in nodes:
            if w not in seen:
                parent[w] = vertex
                queue.append(w)
                seen.add(w)
        print(vertex)
    return parent

if __name__ == "__main__":
    print (DFS(graph,"A"))

