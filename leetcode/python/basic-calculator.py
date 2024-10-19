"""
The calculator input is assumed to be valid.

precedence from highest to lowest:

digits
parentheses
add | subtract
"""


def calc_digits(index, string):
    if '0' <= string[index] <= '9':
        num = ""
        while index < len(string) and '0' <= string[index] <= '9':
            num += string[index]
            index += 1
        return int(num), index
    else:
        return None, index


def calc_parentheses(index, string):
    value, index = calc_digits(index, string)
    if value != None:
        return value, index

    assert string[index] == '('
    value, index = calc_expression(index + 1, string)
    assert string[index] == ')'
    return value, index + 1


def calc_unary(index, string):
    if string[index] == '-':
        value, index = calc_unary(index + 1, string)
        return -value, index

    if string[index] == '+':
        value, index = calc_unary(index + 1, string)
        return value, index

    return calc_parentheses(index, string)


def calc_add(index, string):
    value, index = calc_unary(index, string)

    while index < len(string) and (string[index] == '+' or string[index] == '-'):
        operation = string[index]
        second_value, index = calc_unary(index + 1, string)
        value = value + second_value if operation == "+" else value - second_value

    return value, index


def calc_expression(index, string):
    return calc_add(index, string)


def calc(string: str):
    return calc_expression(0, string.replace(' ', ''))[0]


assert calc("0") == 0
assert calc("10") == 10
assert calc("10 + 1") == 11
assert calc("10 - 1") == 9
assert calc("1+1+1") == 3
assert calc("1-(     -2)") == 3
assert calc("(1 + 2) - 3") == 0
assert calc("(2 - 1) + 3") == 4
assert calc("(1+(4+5+2)-3)+(6+8)") == 23
