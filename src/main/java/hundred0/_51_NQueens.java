package hundred0;

import util.Unsolved;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Unsolved
public class _51_NQueens {

    /**
     * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack
     * each other.
     *
     *
     *
     * Given an integer n, return all distinct solutions to the n-queens puzzle.
     *
     * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both
     * indicate a queen and an empty space respectively.
     *
     * Example:
     *
     * Input: 4
     * Output: [
     *  [".Q..",  // Solution 1
     *   "...Q",
     *   "Q...",
     *   "..Q."],
     *
     *  ["..Q.",  // Solution 2
     *   "Q...",
     *   "...Q",
     *   ".Q.."]
     * ]
     * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
     */

    public static List<List<String>> solveNQueens(int n) {
        //todo;
        List<List<String>> result = new ArrayList<>();
        char[][] table = new char[n][n];
        travelTable(table, 0, result);
        return result;
    }

    public static boolean travelTable(char[][] table, int row, List<List<String>> result) {
        if (row == table.length) {
            List<String> solution = new ArrayList<>();
            for (int i = 0; i < table.length; i++) {
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < table.length; j++) {
                    line.append(table[i][j]=='Q'?'Q':'.');
                }
                solution.add(line.toString());
            }
            result.add(solution);
            return true;
        }
        for (int i = 0; i < table.length; i++) {
            if (isValid(table, row, i)) {
                table[row][i] = 'Q';
                travelTable(table, row+1, result);
                table[row][i] = '.';
            } else {
                if (i == table.length-1) {
                    return false;
                }
            }
        }
        return false;
    }


    public static boolean isValid(char[][] table, int row, int col){
        for (int i = 1; i <= (row<col?col:row); i++) {
            if (row-i>=0 && table[row-i][col] == 'Q') {
                return false;
            }
            if (col-i>0 && table[row][col-i] == 'Q') {
                return false;
            }
            if (col-i>=0 && row-i>=0 && table[row-i][col-i] == 'Q') {
                return false;
            }
            if (col+i<table.length && row-i>=0 && table[row-i][col+i] == 'Q') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<List<String>> result =  solveNQueens(4);
        for (List<String> list: result) {
            for (String str: list) {
                System.out.println(str);
            }
            System.out.println();
        }
    }


    // S1: Recursive
    // Time: O(N!)
    // Rank: 99.67%
    public List<List<String>> solveNQueens1(int n) {
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
        List<List<String>> ans = new ArrayList<>();
        helper(board, 0, new boolean[3][n * 2], ans);
        return ans;
    }

    void helper(char[][] board, int i, boolean[][] placed, List<List<String>> ans) {
        if (i == board.length) {
            List<String> rows = new ArrayList<>();
            for (char[] row : board) {
                rows.add(new String(row));
            }
            ans.add(rows);
            return;
        }
        for (int j = 0, n = board.length; j < n; j ++) {
            if (!placed[0][j] && !placed[1][i + j] && !placed[2][i - j + n]) {
                board[i][j] = 'Q';
                placed[0][j] = true;
                placed[1][i + j] = true;
                placed[2][i - j + n] = true;
                helper(board, i + 1, placed, ans);

                board[i][j] = '.';
                placed[0][j] = false;
                placed[1][i + j] = false;
                placed[2][i - j + n] = false;
            }
        }
    }

    // if (i == board.length) {
    //     ans.add(new ArrayList<>());
    //     for (char[] row : board) {
    //         ans.get(ans.size() - 1).add(new String(row));
    //     }
    //     return;
    // }

    // Rank: 93.57%
    public List<List<String>> solveNQueens11(int n) {
        List<List<String>> ans = new ArrayList<>();
        helper(0, new ArrayList<>(), new boolean[3][n * 2], ans);
        return ans;
    }

    void helper(int i, List<String> rows, boolean[][] placed, List<List<String>> ans) {
        int n = placed[0].length / 2;
        if (i == n) {
            ans.add(new ArrayList<>(rows));
            return;
        }
        for (int j = 0; j < n; j ++) {
            if (!placed[0][j] && !placed[1][i + j] && !placed[2][i - j + n]) {
                char[] row = new char[n];
                Arrays.fill(row, '.');
                row[j] = 'Q';
                rows.add(new String(row));
                placed[0][j] = true;
                placed[1][i + j] = true;
                placed[2][i - j + n] = true;
                helper(i + 1, rows, placed, ans);

                rows.remove(rows.size() - 1);
                placed[0][j] = false;
                placed[1][i + j] = false;
                placed[2][i - j + n] = false;
            }
        }
    }


    // S2: Recursive + BitMask
    // Rank: 100%
    /* TODO-Steps:
            i = 0, cols = 00000000, diag = 00000000, adiag = 00000000, available = 00001111, lowest = 00000001 -> available = 00001110, j = 0
            i = 1, cols = 00000001, diag = 00000000, adiag = 00000010, available = 00001100, lowest = 00000100 -> available = 00001000, j = 2
            i = 1, cols = 00000001, diag = 00000000, adiag = 00000010, available = 00001000, lowest = 00001000 -> available = 00000000, j = 3
            i = 2, cols = 00001001, diag = 00000100, adiag = 00010100, available = 00000010, lowest = 00000010 -> available = 00000000, j = 1
            i = 0, cols = 00000000, diag = 00000000, adiag = 00000000, available = 00001110, lowest = 00000010 -> available = 00001100, j = 1
            i = 1, cols = 00000010, diag = 00000001, adiag = 00000100, available = 00001000, lowest = 00001000 -> available = 00000000, j = 3
            i = 2, cols = 00001010, diag = 00000100, adiag = 00011000, available = 00000001, lowest = 00000001 -> available = 00000000, j = 0
            i = 3, cols = 00001011, diag = 00000010, adiag = 00110010, available = 00000100, lowest = 00000100 -> available = 00000000, j = 2
            i = 0, cols = 00000000, diag = 00000000, adiag = 00000000, available = 00001100, lowest = 00000100 -> available = 00001000, j = 2
            i = 1, cols = 00000100, diag = 00000010, adiag = 00001000, available = 00000001, lowest = 00000001 -> available = 00000000, j = 0
            i = 2, cols = 00000101, diag = 00000001, adiag = 00010010, available = 00001000, lowest = 00001000 -> available = 00000000, j = 3
            i = 3, cols = 00001101, diag = 00000100, adiag = 00110100, available = 00000010, lowest = 00000010 -> available = 00000000, j = 1
            i = 0, cols = 00000000, diag = 00000000, adiag = 00000000, available = 00001000, lowest = 00001000 -> available = 00000000, j = 3
            i = 1, cols = 00001000, diag = 00000100, adiag = 00010000, available = 00000011, lowest = 00000001 -> available = 00000010, j = 0
            i = 2, cols = 00001001, diag = 00000010, adiag = 00100010, available = 00000100, lowest = 00000100 -> available = 00000000, j = 2
            i = 1, cols = 00001000, diag = 00000100, adiag = 00010000, available = 00000010, lowest = 00000010 -> available = 00000000, j = 1
    */
    public List<List<String>> solveNQueens111(int n) {
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
        List<List<String>> ans = new ArrayList<>();
        helper(board, 0, 0, 0, 0, ans);
        return ans;
    }

    private void helper(char[][] board, int i, int cols, int diag, int adiag, List<List<String>> ans) {
        if (i == board.length) {
            List<String> rows = new ArrayList<>();
            for (char[] row : board) {
                rows.add(String.valueOf(row));
            }
            ans.add(rows);
            return;
        }
        int n = board.length, available = ((1 << n) - 1) & (~(cols | diag | adiag));
        while (available != 0) {
            int lowest = available & -available;        // rightmost 1
            // System.out.printf("i = %d, cols = %s, diag = %s, adiag = %s, available = %s, lowest = %s", i,
            // bin(n, cols), bin(n, diag), bin(n, adiag), bin(n, available), bin(n, lowest));
            available &= available - 1;
            int j = Integer.bitCount(lowest - 1);
            // System.out.printf(" -> available = %s, j = %d\n", bin(n, available), j);
            board[i][j] = 'Q';
            helper(board, i + 1, cols ^ lowest, (diag | lowest) >> 1, (adiag | lowest) << 1, ans);
            board[i][j] = '.';
        }
    }

    String bin(int n, int val) {
        return String.format("%" + n * 2 + "s", Integer.toBinaryString(val)).replace(" ", "0");
    }

    private List<List<String>> res;
    char[][] board;
    private int n;

    // Rank: 100%
    public List<List<String>> solveNQueens21(int n) {
        this.n = n;
        res = new ArrayList<>();
        board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        backtrack(0, 0, 0, 0);

        return res;
    }

    private void backtrack(int row, int column, int left, int right) {
        if (row == n) {
            List<String> strBoard = new ArrayList<>();
            for (char[] boardRow : board) {
                strBoard.add(String.valueOf(boardRow));
            }
            res.add(strBoard);
            return;
        }
        // column | left | right 表示所有放了皇后的位置
        // ~(column | left | right) 表示所有可以放皇后的位置，当时因为高位都变成了1，需要用 mask 抹去
        // (1 << n) - 1 表示抹去高位 1 的 mask
        int available = ((1 << n) - 1) & (~(column | left | right));
        while (available != 0) {
            // 取最低位 1 放皇后
            int lowest = available & -available;
            // 抹去最后一位 1
            available &= available - 1;
            // 计算最低位 1 所在的位数
            int col = Integer.bitCount(lowest - 1);
            board[row][col] = 'Q';
            backtrack(row + 1, column ^ lowest, (left | lowest) << 1, (right | lowest) >> 1);
            board[row][col] = '.';
        }

    }
}
