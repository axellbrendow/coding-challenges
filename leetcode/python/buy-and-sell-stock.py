def get_max_profit(prices):
    def get_max_profit_rec(i, bought_value, bought):
        if i >= len(prices):
            return 0
        
        if bought:
            return max(
                prices[i] - bought_value,
                get_max_profit_rec(i + 1, bought_value, True)
            )
        else:
            return max(
                get_max_profit_rec(i + 1, prices[i], True),
                get_max_profit_rec(i + 1, 0, False),
            )

    return get_max_profit_rec(0, 0, False)

assert get_max_profit([]) == 0
assert get_max_profit([1]) == 0
assert get_max_profit([1, 2]) == 1
assert get_max_profit([2, 1]) == 0
assert get_max_profit([5, 9, 4, 10]) == 6
