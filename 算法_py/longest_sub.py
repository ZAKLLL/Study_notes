# 给定一个数组 {3, 1, 2, 1} 和一个数字k。求这个数组的一个最长连续子数组，这个最长连续子数组中所有数字的必须小于或等于k。
# 滑动窗口，窗口的长度只会增加不会减少。


def longet_sub(arr, k):
    temp = [0, 0]
    l = 0
    r = 1
    while(r <= len(arr)):
        print(temp)
        sum = 0
        for i in range(l, r):
            sum += arr[i]
        print(sum)
        if sum <= k:
            if temp[1]-temp[0] <= r-l:
                temp[0] = l
                temp[1] = r
        else:
            l += 1
        r += 1
    return arr[temp[0]:temp[1]]

if __name__ == "__main__":
    arr = [1, 1, 1, 9, 9, 1, 1, 1, 1, 1]
    print(longet_sub(arr, 3))
