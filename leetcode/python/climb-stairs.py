def climb_stairs(N, possible_steps):
    cache = {}

    def climb_stairs_rec(step):
        if step > N: return 0
        if step == N: return 1
        if step in cache: return cache[step]
        total = 0
        for possible_step in possible_steps:
            total += climb_stairs_rec(step + possible_step)
        cache[step] = total
        return total

    return climb_stairs_rec(0)

assert climb_stairs(1, [2]) == 0
assert climb_stairs(1, [1,2]) == 1
assert climb_stairs(2, [1,2]) == 2
assert climb_stairs(4, [1,2]) == 5
