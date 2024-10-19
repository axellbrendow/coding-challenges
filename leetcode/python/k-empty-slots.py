def find_day_with_two_blooming_flowers(flowers, k):
    days = [0] * len(flowers)
    for day in range(len(flowers)):
        days[flowers[day] - 1] = day

    start = 0
    end = k + 1
    i = 1
    result = len(flowers) + 1
    while i < len(flowers) - k:
        if i == end: result = min(result, max(days[start], days[end]) + 1)
        if not (days[i] > days[start] and days[i] > days[end]):
            start = i
            end = start + k + 1
        i += 1

    if end >= len(days):
        result = -1
    else:
        result = min(result, max(days[start], days[end]) + 1)
    return result

assert find_day_with_two_blooming_flowers([1, 2, 3], 1) == -1
assert find_day_with_two_blooming_flowers([1, 3, 2], 1) == 2
assert find_day_with_two_blooming_flowers([1, 3, 2, 4, 6, 5], 1) == 2
