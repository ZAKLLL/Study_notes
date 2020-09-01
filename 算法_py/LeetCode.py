import time
from typing import *
class Solution:
    def getWinner(self, arr: List[int], k: int) -> int:
        maxWin = 0
        preV = 0
        while maxWin != k:
            i = 0 if arr[0] > arr[1] else 1
            if arr[i] == preV:
                maxWin += 1
            else:
                preV = arr[i]
                maxWin = 1
            a = 1 ^ i
            t = arr[a]
            arr.pop(a)
            arr.append(t)
        return preV


if __name__ == "__main__":
    a = Solution().getWinner([2, 1, 3, 5, 4, 6, 7], 2)
    print(a)
