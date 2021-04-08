package hundred0;

import java.util.ArrayList;
import java.util.List;

public class _37_SudokuSolver {
    static int unsolved = 0;

    public static void solveSudoku(char[][] board) {
        unsolved = 0;
        for (int i = 0 ; i < 9; i ++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 46) {    //'.'
                    unsolved += 1;
                    List<Integer> possibleFigure = guess(board, i, j);
                    if (possibleFigure.size() == 1) {
                        board[i][j] = (char)(possibleFigure.get(0)+48);
                        unsolved -= 1;
                    } else {

                    }
                }
            }
        }
        while (unsolved > 0) {
            solveSudoku(board);
        }
    }

    public static List<Integer> guess(char[][] board, int r, int c) {
        List<Integer> result = new ArrayList<>();
        int[] row = new int[9];
        int[] col = new int[9];
        int[] grid = new int[9];
        for (int m = r-r%3; m < r/3*3+3; m++) {
            for (int n = c-c%3; n < c/3*3+3; n++) {
                if (board[m][n] != '.'){
                    grid[board[m][n]-'1'] = board[m][n]-'0';
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            if (board[r][i] != '.'){
                row[board[r][i]-'1'] = board[r][i]-'0';
            }
            if (board[i][c] != '.'){
                col[board[i][c]-'1'] = board[i][c]-'0';
            }
        }
        for (int i = 0; i < 9; i++) {
            if (row[i]==0 && col[i]==0 && grid[i]==0) {
                result.add(i+1);
            }
        }
        return result;
    }


    public static void solveSudoku2(char[][] board) {
        settleCell(board, 0);
    }

    public static boolean settleCell(char[][] board, int index) {
        if (index == 81) {
            return true;
        }
        int row = index/9;
        int col = index%9;
        if (board[row][col] != '.') return settleCell(board, index+1);
        if (board[row][col] == '.') {
            List<Integer> figs = guess(board, row, col);
            for (Integer f: figs) {
                board[row][col] = (char)(f+48);
                if (settleCell(board, index +1)){
                    return true;
                }
            }
            board[row][col] = '.';
        }
        return false;
    }

    public static boolean isValid(char[][] board, int i, int j, char c) {
        for (int m = i - i%3; m < i/3*3+3; m++) {
            for (int n = j = j%3; n < j/3*3+3; n++) {
                if (board[m][n] == c) return false;
            }
        }
        for (int m = 0; m < 9; m++) {
            if (board[i][m] == c) return false;
            if (board[m][j] == c) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        char[][] sudoku = new char[][] {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
/*        sudoku = new char[][]{
                {'.','.','9','7','4','8','.','.','.'},
                {'7','.','.','.','.','.','.','.','.'},
                {'.','2','.','1','.','9','.','.','.'},
                {'.','.','7','.','.','.','2','4','.'},
                {'.','6','4','.','1','.','5','9','.'},
                {'.','9','8','.','.','.','3','.','.'},
                {'.','.','.','8','.','3','.','2','.'},
                {'.','.','.','.','.','.','.','.','6'},
                {'.','.','.','2','7','5','9','.','.'}
        };*/
        solveSudoku2(sudoku);
        for (int i = 0; i < 9; i++) {
            for (int j =0; j< 9; j++) {
                System.out.printf(String.valueOf(sudoku[i][j]));
                if (j!=8) System.out.printf(",");
            }
            System.out.println("");
        }
    }
}
