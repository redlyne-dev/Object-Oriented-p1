/**
 * MatrixDemo.java
 * 2D arrays in Java are "arrays of arrays" — rows may even differ in length.
 * Demonstrates traversal, per-row aggregation, and transpose.
 */
public class MatrixDemo {
    public static void main(String[] args) {
        // Rectangular 2D array: 3 rows x 4 columns
        int[][] grid = {
            {1,  2,  3,  4},
            {5,  6,  7,  8},
            {9, 10, 11, 12}
        };

        printMatrix(grid);

        System.out.println("Row sums:");
        for (int r = 0; r < grid.length; r++) {
            int sum = 0;
            for (int c = 0; c < grid[r].length; c++) sum += grid[r][c];
            System.out.println("  Row " + r + ": " + sum);
        }

        // Transpose: rows <-> columns (dimensions swap)
        int[][] t = transpose(grid);
        System.out.println("Transposed (" + t.length + "x" + t[0].length + "):");
        printMatrix(t);

        // Jagged array: each row has its own length — perfectly legal
        int[][] jagged = {{1}, {2, 3}, {4, 5, 6}};
        System.out.println("Jagged row lengths:");
        for (int[] row : jagged) System.out.println("  length=" + row.length);
    }

    static void printMatrix(int[][] m) {
        for (int[] row : m) {
            for (int v : row) System.out.printf("%4d", v);
            System.out.println();
        }
    }

    static int[][] transpose(int[][] m) {
        int rows = m.length, cols = m[0].length;
        int[][] t = new int[cols][rows];     // dimensions swapped
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                t[c][r] = m[r][c];
        return t;
    }
}
