package hundred0;

import java.util.ArrayList;
import java.util.List;

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
}
