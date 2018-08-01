public class MagicSquaresInGrid {

    /**
     * A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each row, column, and
     * both diagonals all have the same sum.
     *
     * Given an grid of integers, how many 3 x 3 "magic square" subgrids are there?  (Each subgrid is contiguous).
     *
     *
     *
     * Example 1:
     *
     * Input: [[4,3,8,4],
     *         [9,5,1,9],
     *         [2,7,6,2]]
     * Output: 1
     * Explanation:
     * The following subgrid is a 3 x 3 magic square:
     * 438
     * 951
     * 276
     *
     * while this one is not:
     * 384
     * 519
     * 762
     *
     * In total, there is only one magic square inside the given grid.
     * Note:
     *
     * 1 <= grid.length <= 10
     * 1 <= grid[0].length <= 10
     * 0 <= grid[i][j] <= 15
     * @param grid
     * @return
     */

    public int numMagicSquaresInside(int[][] grid) {
        if (grid.length < 3 ) return 0;
        if (grid[0].length < 3) return 0;
        int count = 0;
        for (int i = 1; i < grid.length-1; i++){
            for (int j = 1; j < grid[0].length -1; j++) {
                if (grid[i][j] != 5) continue;
                if (isMagicSquare(grid[i-1][j-1], grid[i-1][j], grid[i-1][j+1], grid[i][j-1], grid[i][j], grid[i][j+1], grid[i+1][j-1], grid[i+1][j], grid[i+1][j+1])) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isMagicSquare(int lu, int mu, int ru, int lm, int mm, int rm, int ld, int md, int rd){
        int sum = lu + mu + ru + lm + mm + rm + ld + md + rd;
        int[] count = new int[16];
        count[lu] ++;
        count[mu] ++;
        count[ru] ++;
        count[lm] ++;
        count[mm] ++;
        count[rm] ++;
        count[ld] ++;
        count[md] ++;
        count[rd] ++;
        for (int i = 1; i <=9 ; i++) {
            if (count[i]!=1) {
                return false;
            }
        }
        if ((lu+mu+ru) != sum/3) return false;
        if ((lm+mm+rm) != sum/3) return false;
        if ((ld+md+rd) != sum/3) return false;
        if ((lu+lm+ld) != sum/3) return false;
        if ((mu+mm+md) != sum/3) return false;
        if ((ru+rm+rd) != sum/3) return false;
        if ((lu+mm+rd) != sum/3) return false;
        if ((ld+mm+ru) != sum/3) return false;
        return true;
    }

}
