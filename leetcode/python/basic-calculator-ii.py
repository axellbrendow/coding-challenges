"""
This calculator only works with numbers of one digit, this is just a simplification.
The calculator input is assumed to be valid.

precedence from highest to lowest:

digits
unary
multiply | divide
add | subtract
"""


import math


def calc_digits(index, string):
    if '0' <= string[index] <= '9':
        num = ""
        while index < len(string) and '0' <= string[index] <= '9':
            num += string[index]
            index += 1
        return int(num), index
    else:
        return None, index


def calc_unary(index, string):
    if string[index] == '-':
        value, index = calc_unary(index + 1, string)
        return -value, index

    if string[index] == '+':
        value, index = calc_unary(index + 1, string)
        return value, index

    return calc_digits(index, string)


def calc_multiply(index, string):
    value, index = calc_unary(index, string)

    while index < len(string) and (string[index] == '*' or string[index] == '/'):
        operation = string[index]
        second_value, index = calc_unary(index + 1, string)

        if operation == '*':
            value = value * second_value
        else:
            if (
                value < 0 and second_value >= 0
                or value >= 0 and second_value < 0
            ):
                value = math.ceil(value / second_value)
            else:
                value = value // second_value

    return value, index


def calc_add(index, string):
    value, index = calc_multiply(index, string)

    while index < len(string) and (string[index] == '+' or string[index] == '-'):
        operation = string[index]
        second_value, index = calc_multiply(index + 1, string)
        value = value + second_value if operation == "+" else value - second_value

    return value, index


def calc_expression(index, string):
    return calc_add(index, string)


def calc(string: str):
    return calc_expression(0, string.replace(' ', ''))[0]


assert calc("10 + 1") == 11
assert calc("10 - 1") == 9
assert calc("-10 - 1") == -11
assert calc("1+1+1") == 3
assert calc(" 3/2 ") == 1
assert calc(" 3+5 / 2 ") == 5
assert calc("3 + 2 * 2") == 7
assert calc("1 * 2 + 3") == 5
assert calc("1 + 2 * 3") == 7
