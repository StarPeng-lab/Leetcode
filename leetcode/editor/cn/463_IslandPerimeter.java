package leetcode.editor.cn;
//island-perimeter
//给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。 
//
// 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。 
//
// 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿
//的周长。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
//输出：16
//解释：它的周长是上面图片中的 16 个黄色的边 
//
// 示例 2： 
//
// 
//输入：grid = [[1]]
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：grid = [[1,0]]
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// row == grid.length 
// col == grid[i].length 
// 1 <= row, col <= 100 
// grid[i][j] 为 0 或 1 
// 
// Related Topics 哈希表 
// 👍 366 👎 0

public class IslandPerimeter{
    public static void main(String[] args) {
        Solution solution = new IslandPerimeter().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //方法一：迭代
    //对于一个陆地格子的每条边，它被算作岛屿的周长当且仅当这条边为网格的边界或者相邻的另一个格子为水域。
    // 因此，我们可以遍历每个陆地格子，看其四个方向是否为边界或者水域，
    // 如果是，将这条边的贡献（即 1）加入答案 len 中即可
    public int islandPerimeter(int[][] grid){
        int[] dx = new int[]{-1,1,0,0};
        int[] dy = new int[]{0,0,-1,1};

        int m = grid.length;
        int n = grid[0].length;
        int len = 0;
        for(int r = 0 ; r < m ; r++){
            for(int c = 0 ; c < n ; c++){
                if(grid[r][c] == 1){
                    for(int i = 0 ; i < 4 ; i++){
                        int x = r+dx[i];
                        int y = c+dy[i];
                        if(x<0 || x>=m || y<0 || y>=n ||grid[x][y]==0){
                            len += 1;
                        }
                    }
                }
            }
        }
        return len;
    }

    //方法二：深度优先算法dfs，递归
    //此时遍历的方式可扩展至统计多个岛屿各自的周长。
    // 需要注意的是为了防止陆地格子在深度优先搜索中被重复遍历导致死循环，我们需要将遍历过的陆地格子标记为已经遍历过
    /*public int islandPerimeter(int[][] grid) {
        for(int r = 0 ; r < grid.length ; r++){
            for(int c = 0 ; c < grid[0].length ; c++){
                if(grid[r][c] == 1)
                    return dfs(grid,r,c);
            }
        }
        return 0;
    }

    private int dfs(int[][] grid , int r , int c){
        if(!inArea(grid,r,c)) //当网格超出边界时，经过了一条 靠近边界的边
            return 1;

        if(grid[r][c] == 0) //当网格的周围是海洋时，经过了一条 靠近海洋的边
            return 1;

        if(grid[r][c] != 1) //当网格遍历的不是岛屿时，结束这个函数（理解为，函数因为「当前格子是已遍历的陆地格子」返回，和周长没关系）
            return 0;

        grid[r][c] = 2;
        return dfs(grid,r+1,c)+
                dfs(grid,r,c-1)+
                dfs(grid,r-1,c)+
                dfs(grid,r,c+1);
    }

    private boolean inArea(int[][] grid , int r , int c){
        return 0<=r && r<grid.length && 0<=c && c<grid[0].length;
    }*/
}
//leetcode submit region end(Prohibit modification and deletion)

}