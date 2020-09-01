
# 筛选法求小于N的质数

N = int(input('n'))

is_prime = [True] * (N)

is_prime[0] = False
is_prime[1] = False

for i in range(2, N):
    if(is_prime[i]):
        for j in range(i*2, N, i):
            is_prime[j] = False


count = 0

for i in range(1, N):
    if(is_prime[i]):
        count += 1
print(count)
