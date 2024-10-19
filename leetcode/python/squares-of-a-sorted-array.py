from typing import List


def sortedSquares(nums: List[int]) -> List[int]:
    stack = []
    output = []
    for num in nums:
        if num == 0:
            output.append(0)
        elif num < 0:
            stack.append(-num)
        else:
            while len(stack) > 0 and stack[-1] < num:
                output.append(stack[-1] * stack[-1])
                stack.pop()
            output.append(num * num)
    while len(stack) > 0:
        output.append(stack[-1] * stack[-1])
        stack.pop()
    return output


nums = [-4, -1, 0, 3, 10]
expectedOutput = [0, 1, 9, 16, 100]
assert sortedSquares(nums) == expectedOutput

nums = [-7, -3, 2, 3, 11]
expectedOutput = [4, 9, 9, 49, 121]
assert sortedSquares(nums) == expectedOutput
