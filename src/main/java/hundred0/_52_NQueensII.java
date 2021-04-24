package hundred0;

import util.Unsolved;

/**
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 *
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 4
 * Output: 2
 * Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= n <= 9
 */
@Unsolved
public class _52_NQueensII {

    public int totalNQueens(int n) {
        return 0;
    }

    private int bruteforce(int n) {
        boolean[][] board = new boolean[n][n];
        return backtrack(board, 0, 0);
    }

    private int backtrack(boolean[][] board, int row, int col) {
        return 0;
    }

    private boolean isValid(boolean[][] board, int row, int col) {
        for (int i = 0; i < Math.min(row, col); i++) {

        }
        return true;
    }

}
