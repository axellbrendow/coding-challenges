"""
MAX_INT = 2^31 - 1 = 2147483647
MIN_INT = -2^31    = -2147483648

x = 2147483647
str(x) = "2147483647"
str(x)[::-1] = "7463847412"
if the str above have the same length as MAX_INT,
then i'll go digit by digit checking that the digits
are smaller or equal than the digits of MAX_INT.
If one digit is smaller then I can break the loop
because the entire number will be.

x = -2147483648
str(x) = "-2147483648"
str(x)[::-1][:-1] = "8463847412"
if the str above have the same length as MIN_INT,
then i'll go digit by digit checking that the digits
are smaller or equal than the digits of MIN_INT.
If one digit is smaller then I can break the loop
because the entire number will be.
"""


def reverse(x: int) -> int:  # x = -2_147_483_412
    MAX_INT = "2147483647"
    MIN_INT = "2147483648"
    #          2147483412

    x_str = str(x) if x >= 0 else str(x)[1:]  # "2147483412"
    reversed_x_str = x_str[::-1]  # "2143847412"

    result = int(reversed_x_str) if x >= 0 \
        else -int(reversed_x_str)  # -2143847412

    if -1_000_000_002 <= x <= 1_000_000_002:
        return result
    elif x >= 0:
        for i in range(len(MAX_INT)):  # i = 0, len(MAX_INT) = 10
            if int(reversed_x_str[i]) < int(MAX_INT[i]):
                break
            if int(reversed_x_str[i]) > int(MAX_INT[i]):
                return 0
        return result
    else:
        for i in range(len(MIN_INT)):  # i = 0, len(MIN_INT) = 10
            if int(reversed_x_str[i]) < int(MIN_INT[i]):
                break
            if int(reversed_x_str[i]) > int(MIN_INT[i]):
                return 0
        return result


assert reverse(0) == 0
assert reverse(1) == 1
assert reverse(-1) == -1
assert reverse(-12) == -21
assert reverse(1_000_000_002) == 2_000_000_001
assert reverse(1_000_000_102) == 2_010_000_001
assert reverse(1_463_847_412) == 2_147_483_641
assert reverse(-2_147_483_412) == -2_143_847_412
