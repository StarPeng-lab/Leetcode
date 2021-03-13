package leetcode.editor.cn;
//li-wu-de-zui-da-jie-zhi-lcof
//在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直
//到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？ 
//
// 示例 1:
// 输入: 
//[
//  [1,3,1],
//  [1,5,1],
//  [4,2,1]
//]
//输出: 12
//解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物 
//
// 提示：
// 0 < grid.length <= 200 
// 0 < grid[0].length <= 200 
// 
// Related Topics 动态规划 
// 👍 113 👎 0

public class LiWuDeZuiDaJieZhiLcof{
    public static void main(String[] args) {
        Solution solution = new LiWuDeZuiDaJieZhiLcof().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxValue(int[][] grid) {
        //方法一：定义一个dp数组，存放每一格的最大价值（由于只能向下向右，因此每次判断哪个方向的价值大，用较大的+当前格子=当前格子的最大价值）
        /*int rows = grid.length;
        int cols = grid[0].length;

        int[][] dp = new int[rows][cols];
        dp[0][0] = grid[0][0];

        for(int j = 1; j < cols; j++) // 初始化第一行
            dp[0][j] = dp[0][j-1] + grid[0][j];
        for(int i = 1; i < rows; i++) // 初始化第一列
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        for(int i = 1; i < rows; i++)
            for(int j = 1; j < cols; j++)
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
        return dp[rows-1][cols-1];*/

        //方法二：直接在grid数组上进行修改
        int rows = grid.length, cols = grid[0].length;
        for(int j = 1; j < cols; j++) // 初始化第一行
            grid[0][j] += grid[0][j - 1];
        for(int i = 1; i < rows; i++) // 初始化第一列
            grid[i][0] += grid[i - 1][0];
        for(int i = 1; i < rows; i++)
            for(int j = 1; j < cols; j++)
                grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
        return grid[rows - 1][cols - 1];

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}