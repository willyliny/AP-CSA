public class GridPath {
    /** Initialized in the constructor with distinct values that never change */
    private int[][] grid;
    GridPath()
    {
        this.grid = new int[][]{
            {12, 3, 4, 13, 5},
            {11, 21, 2, 14, 16},
            {7, 8, 9, 15, 0},
            {10, 17, 20, 19, 1},
            {18, 22, 30, 25, 6}
        };
    }
    /**
     * Returns the Location representing a neighbor of the grid element at row and
     * col,
     * as described in part (a)
     * Preconditions: row is a valid row index and col is a valid column index in
     * grid.
     * row and col do not specify the element in the last row and last column of
     * grid.
     */
    public Location getNextLoc(int row, int col) {
        if(row == grid.length -1)
        {
            return new Location(row, col+1);
        }
        if(col == grid[0].length-1)
        {
            return new Location(row +1, col);
        }

        if(grid[row+1][col] < grid[row][col+1])
        {
            return new Location(row + 1, col);
        }
        
        
        return new Location(row , col+1);
    /* to be implemented in part (a) */ }

    /**
     * Computes and returns the sum of all values on a path through grid, as
     * described in
     * part (b)
     * Preconditions: row is a valid row index and col is a valid column index in
     * grid.
     * row and col do not specify the element in the last row and last column of
     * grid.
     */
    public int sumPath(int row, int col) {
        if(row == grid.length-1 && col == grid[0].length -1 )
        {
            return grid[row][col];
        }
        
        Location next = getNextLoc(row, col);


        return grid[row][col] + sumPath(next.getRow(), next.getCol());
        /* to be implemented in part (b) */ }
    // There may be instance variables, constructors, and methods that are not
    // shown.
}