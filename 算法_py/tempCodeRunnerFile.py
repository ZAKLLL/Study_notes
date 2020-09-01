import time


class Solution:
    def findRadius(self, houses, heaters):
        houses.sort()
        heaters.sort()
        heaters.insert(0, float('inf'))
        heaters.append(float('inf'))
        i = 1
        ret = 0
        for h in houses:
            while i < len(heaters)-1 and h > heaters[i]:
                i += 1
            res = max(ret, min(heaters[i]-h, h-heaters[i-1]))
        return ret

    def solve(self, n, a):
        ret = 0
        stack = []
        for i in range(n):