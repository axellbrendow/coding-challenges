import math


def reverse(x: int) -> int:
    MAX_INT = int(2 ** 31 - 1)
    MIN_INT = int(-2 ** 31)
    MAX_INT_WITHOUT_LAST_DIGIT = MAX_INT // 10
    MIN_INT_WITHOUT_LAST_DIGIT = math.ceil(MIN_INT / 10)
    reversed_number = 0

    while x != 0:
        if (
            reversed_number > MAX_INT_WITHOUT_LAST_DIGIT
            or reversed_number < MIN_INT_WITHOUT_LAST_DIGIT
        ):
            return 0
        x_last_digit = x % 10 if x >= 0 or x % 10 == 0 else x % 10 - 10
        reversed_number = reversed_number * 10 + x_last_digit
        x = x // 10 if x >= 0 else math.ceil(x / 10)

    return reversed_number


assert reverse(0) == 0
assert reverse(1) == 1
assert reverse(-1) == -1
assert reverse(-12) == -21
assert reverse(1_000_000_002) == 2_000_000_001
assert reverse(1_000_000_102) == 2_010_000_001
assert reverse(1_463_847_412) == 2_147_483_641
assert reverse(-2_147_483_412) == -2_143_847_412
