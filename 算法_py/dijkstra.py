import heapq
import math
graph = {
    "A": {"B": 5, "C": 1},
    "B": {"A": 5, "C": 2},
    "C": {"A": 1, "B": 2, "D": 4},
    "D": {"B": 1, "C": 4, "E": 3, "F": 6},
    "E": {"C": 8, "D": 3},
    "F": {"D": 6}
}


def init_distance(graph, s):
    distance = {s: 0}
    for vertex in graph.keys():
        if vertex != s:
            distance[vertex] = math.inf
    return distance


# 从s点到图中每个点的最短距离
def dijkstra(graph, s):
    pqueue = []  # 权重高的排前面
    heapq.heappush(pqueue, (0, s))
    seen = set()
    parent = {s: None}
    distance = init_distance(graph, s)

    while(len(pqueue) > 0):
        pair = heapq.heappop(pqueue)
        dist = pair[0]  # 取出来的点到s的距离
        vertex = pair[1]
        seen.add(vertex)

        nodes = graph[vertex].keys()
        for w in nodes:
            if w not in seen:
                if dist+graph[vertex][w] < distance[w]: 
                    distance[w] = dist+graph[vertex][w]
                    heapq.heappush(pqueue, (dist+graph[vertex][w], w))
                    parent[w] = vertex
    return parent, distance


parent, distance = dijkstra(graph, "A")
print(parent)
print("------------")
print(distance)
