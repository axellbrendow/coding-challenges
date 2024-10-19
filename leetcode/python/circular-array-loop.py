from typing import List


"""
        0  1 2 3 4
nums = [2,-1,1,2,2]

0 -> 2 -> 3 -> 0

1 -> 0 (not valid)

4 -> 1 -> 0 (not valid)
"""


def circularArrayLoop(nums: List[int]) -> bool:
    global_visited = [0] * len(nums)
    local_visited = None

    def dfs(i, path_size):
        if local_visited[i] and path_size > 1:
            return True
        local_visited[i] = True
        global_visited[i] = True
        next_pos = (i + nums[i]) % len(nums)
        if (
            next_pos != i and (
                (nums[next_pos] > 0 and nums[i] > 0)
                or (nums[next_pos] < 0 and nums[i] < 0)
            )
        ):
            if dfs(next_pos, path_size + 1):
                return True
        return False

    for i in range(len(nums)):
        if not global_visited[i]:
            local_visited = [0] * len(nums)
            if dfs(i, 1):
                return True
    return False


assert circularArrayLoop([2, -1, 1, 2, 2]) == True
assert circularArrayLoop([-1, -2, -3, -4, -5, 6]) == False
assert circularArrayLoop([1, -1, 5, 1, 4]) == True
