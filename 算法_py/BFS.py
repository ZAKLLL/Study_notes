# 广度遍历
graph = {
    "A": ["B", "C"],
    "B": ["A", "C", "D"],
    "C": ["A", "B", "D", "E"],
    "D": ["B", "C", "E", "F"],
    "E": ["C", "D"],
    "F": ["D"]
}


def BFS(graph, s):
    queue = []  # 使用动态数组作队列
    queue.append(s)
    seen = set()
    while (len(queue) > 0):
        vertex = queue.pop(0) # 每次取队列中的第一个再进行添加
        seen.add(s)
        nodes = graph[vertex]
        for w in nodes:
            if w not in seen:
                queue.append(w)
                seen.add(w)
        print(vertex)


BFS(graph, "A")
