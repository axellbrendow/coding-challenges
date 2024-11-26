namespace CSharp;

public class MaxAreaOfIsland
{
    private const int Water = 0;
    private const int Island = 1;

    private static readonly int[][] Directions = new int[][] {
        [0, 1], // RIGHT
        [1, 0], // DOWN
        [0, -1], // LEFT
        [-1, 0], // UP
    };

    private static int maxAreaOfIsland(int[][] grid, int nlines, int ncols, bool[,] visited, int i, int j) {
        if (!(0 <= i && i < nlines && 0 <= j && j < ncols)) return 0;
        if (grid[i][j] == Water || visited[i,j]) return 0;
        visited[i,j] = true;
        int maxArea = 1;
        foreach (int[] direction in Directions)
            maxArea += maxAreaOfIsland(grid, nlines, ncols, visited, i + direction[0], j + direction[1]);
        return maxArea;
    }
    
    public static int maxAreaOfIsland(int[][] grid) {
        int nlines = grid.Length, ncols = grid[0].Length, maxArea = 0;
        bool[,] visited = new bool[nlines,ncols];
        for (int i = 0; i < nlines; i++)
        for (int j = 0; j < ncols; j++)
            if (grid[i][j] == Island && !visited[i,j]) maxArea = Math.Max(
                maxArea,
                maxAreaOfIsland(grid, nlines, ncols, visited, i, j)
            );
        return maxArea;
    }
}