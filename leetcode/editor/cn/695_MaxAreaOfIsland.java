package leetcode.editor.cn;
//max-area-of-island
//给定一个包含了一些 0 和 1 的非空二维数组 grid 。 
//
// 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 
//0（代表水）包围着。 
//
// 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。) 
//
// 
//
// 示例 1: 
//
// [[0,0,1,0,0,0,0,1,0,0,0,0,0],
// [0,0,0,0,0,0,0,1,1,1,0,0,0],
// [0,1,1,0,1,0,0,0,0,0,0,0,0],
// [0,1,0,0,1,1,0,0,1,0,1,0,0],
// [0,1,0,0,1,1,0,0,1,1,1,0,0],
// [0,0,0,0,0,0,0,0,0,0,1,0,0],
// [0,0,0,0,0,0,0,1,1,1,0,0,0],
// [0,0,0,0,0,0,0,1,1,0,0,0,0]]
// 
//
// 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。 
//
// 示例 2: 
//
// [[0,0,0,0,0,0,0,0]] 
//
// 对于上面这个给定的矩阵, 返回 0。 
//
// 
//
// 注意: 给定的矩阵grid 的长度和宽度都不超过 50。 
// Related Topics 深度优先搜索 数组 
// 👍 414 👎 0

public class MaxAreaOfIsland{
    public static void main(String[] args) {
        Solution solution = new MaxAreaOfIsland().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        for(int r = 0 ; r < grid.length ; r++){
            for(int c = 0 ; c < grid[0].length ; c++){
                if(grid[r][c] == 1){
                    int a = area(grid,r,c); //将岛屿的r,c传入dfs，开始遍历
                    res = Math.max(res,a);
                }
            }
        }
        return res;
    }
    private int area(int[][] grid , int r , int c){
        if(!inArea(grid , r , c))
            return 0;

        if(grid[r][c] != 1)
            return 0;

        grid[r][c] = 2;
        return 1+
                area(grid,r+1,c)+
                area(grid,r,c-1)+
                area(grid,r-1,c)+
                area(grid,r,c+1);
    }
    private boolean inArea(int[][] grid, int r , int c){
        return 0 <= r && r < grid.length && 0 <= c && c < grid[0].length;
    }

   /* 深度优先遍历dfs：遍历网格图的基本框架
    private void dfs(int[][] grid, int r, int c){
        if(!inArea(grid,r,c)) //如果不在grid区域里
            return;

        if(grid[r][c] != 1) //如果不是岛屿
            return;

        grid[r][c] = 2; //标记这个土地被访问过
        dfs(grid,r-1,c); //访问上
        dfs(gird,r,c-1); //访问左
        dfs(grid,r+1,c); //访问下
        dfs(grid,r,c+1); //访问右
    }

     private boolean inArea(int[][] grid, int r,int c){ //判断(r,c)是否在网格中
        return 0 <= r && r < grid.length && 0 <= c && c < grid[0].length; //行坐标r,列坐标c
    }
    */
}
//leetcode submit region end(Prohibit modification and deletion)

}