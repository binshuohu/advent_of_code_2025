import java.io.FileNotFoundException;
import java.util.List;

public class Day04 {
    private static final int[][] DIRS = {{1, 0}, {0, 1}, {1, 1}, {-1, 1}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}};

    private static final char PAPER_ROLL = '@';

    public static void main(String[] args) throws FileNotFoundException {
        String path = System.getProperty("user.home") + "/input.txt";
        List<String> lines = InputReader.read(path);
        char[][] grid = lines.stream().map(String::toCharArray).toArray(char[][]::new);
        System.out.println(part1(grid));
        System.out.println(part2(grid));
    }

    private static int part1(char[][] grid) {
        int result = 0;
        int ROWS = grid.length, COLS = grid[0].length;
        for (int r = 0; r < ROWS; ++r) {
            for (int c = 0; c < COLS; ++c) {
                if (grid[r][c] == PAPER_ROLL
                    && isAccessible(grid, r, c, ROWS, COLS)) {
                    result++;
                }
            }
        }
        return result;
    }

    private static int part2(char[][] grid) {
        int result = 0;
        int removed;
        while ((removed = sweep(grid)) > 0) {
            result += removed;
        }
        return result;
    }

    private static int sweep(char[][] grid) {
        int result = 0;
        int ROWS = grid.length, COLS = grid[0].length;
        for (int r = 0; r < ROWS; ++r) {
            for (int c = 0; c < COLS; ++c) {
                if (grid[r][c] == PAPER_ROLL
                    && isAccessible(grid, r, c, ROWS, COLS)) {
                    grid[r][c] = '.';
                    result++;
                }
            }
        }
        return result;
    }

    private static boolean isAccessible(char[][] grid, int r, int c, int ROWS, int COLS) {
        int result = 0;
        for (int[] dir: DIRS) {
            int nr = r + dir[0], nc = c + dir[1];
            if (nr < 0 || nr >= ROWS || nc < 0 || nc >= COLS) continue;
            if (grid[nr][nc] == PAPER_ROLL) result += 1;
        }
        return result < 4;
    }
}
