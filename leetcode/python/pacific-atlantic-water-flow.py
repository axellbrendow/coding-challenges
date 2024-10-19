def get_cells_that_flow_to_the_oceans(heights):
    if not heights or len(heights) == 0 or len(heights[0]) == 0: return []

    nlines = len(heights)
    ncolumns = len(heights[0])
    reacheable_from_pacific = set()
    reacheable_from_atlantic = set()
    directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]

    def dfs(i, j, reacheable):
        if (i, j) in reacheable: return

        reacheable.add((i, j))

        for direction in directions:
            next_i = i + direction[0]
            next_j = j + direction[1]
            if (
                0 <= next_i < nlines
                and 0 <= next_j < ncolumns
                and heights[i][j] <= heights[next_i][next_j]
            ):
                dfs(next_i, next_j, reacheable)

    for j in range(ncolumns):
        dfs(0, j, reacheable_from_pacific)
        dfs(nlines - 1, j, reacheable_from_atlantic)

    for i in range(nlines):
        dfs(i, 0, reacheable_from_pacific)
        dfs(i, ncolumns - 1, reacheable_from_atlantic)

    return list(reacheable_from_atlantic & reacheable_from_pacific)

assert sorted(get_cells_that_flow_to_the_oceans(
    [
        [1, 1],
    ]
)) == [(0,0),(0,1)]

assert sorted(get_cells_that_flow_to_the_oceans(
    [
        [1, 2, 3],
        [1, 10, 3],
        [9, 1, 1],
    ]
)) == [(0,2),(1,1),(1,2),(2,0)]
