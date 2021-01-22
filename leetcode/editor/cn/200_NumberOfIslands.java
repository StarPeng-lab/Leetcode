package leetcode.editor.cn;
//number-of-islands
//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 946 👎 0

public class NumberOfIslands{
    public static void main(String[] args) {
        Solution solution = new NumberOfIslands().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //方法一：深度优先遍历+递归
    //为了求出岛屿的数量，我们可以扫描整个二维网格。如果一个位置为 1，则以其为起始节点开始进行深度优先搜索。
    // 在深度优先搜索的过程中，每个搜索到的 1 都会被重新标记为 2。
    //最终岛屿的数量就是我们进行深度优先搜索的次数
   /* public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int index = 0; //记录岛屿个数
        for(int r=0 ; r<m ; r++){
            for(int c=0 ; c<n ; c++){
                if(grid[r][c] == '1'){
                    dfs(grid,r,c);
                    index++;
                }
            }
        }
        return index;
    }
    private void dfs(char[][] grid, int r, int c){
        if(!inArea(grid,r,c))
            return;
        if(grid[r][c] != '1')
            return;
        grid[r][c] = '2'; //标记此岛屿已被遍历
        dfs(grid,r,c+1);
        dfs(grid,r,c-1);
        dfs(grid,r+1,c);
        dfs(grid,r-1,c);
    }
    private boolean inArea(char[][] grid, int r, int c){
        return 0<=r && r<grid.length && 0<=c && c<grid[0].length;
    }*/

    //方法二：广度优先遍历+迭代（借助队列，以grid[r][c]为起点遍历整个岛屿），耗时更长
    //也可以使用广度优先搜索代替深度优先搜索。
    //为了求出岛屿的数量，我们可以扫描整个二维网格。如果一个位置为 1，则将其加入队列，开始进行广度优先搜索。
    // 在广度优先搜索的过程中，每个搜索到的 1 都会被重新标记为 2。直到队列为空，一个岛屿搜索结束。
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dx = new int[]{0,0,1,-1};
        int[] dy = new int[]{1,-1,0,0};
        int index = 0; //记录岛屿个数
        for(int r=0 ; r<m ; r++){
            for(int c=0 ; c<n ; c++){
                if(grid[r][c] == '1'){
                    grid[r][c] = '2';

                    Queue<Integer> queue = new LinkedList<>();
                    queue.offer(r*n+c);
                    while(!queue.isEmpty()){
                        int value = queue.poll();
                        int x = value/n; //r
                        int y = value%n; //c
                        //可以用if单个判断上下左右，也可以用for一起判断，运行结果显示，for耗时短一些
                      /*if(x+1 < m && grid[x+1][y] == '1'){ //上
                          queue.add((x+1)*n+y);
                          grid[x+1][y] = '2';
                      }
                      if(x-1 >= 0 && grid[x-1][y] == '1'){ //下
                          queue.add((x-1)*n+y);
                          grid[x-1][y] = '2';
                      }
                      if(y+1 < n && grid[x][y+1] == '1'){ //右
                          queue.add(x*n+y+1);
                          grid[x][y+1] = '2';
                      }
                      if(y-1 >= 0 && grid[x][y-1] == '1'){ //左
                          queue.add(x*n+y-1);
                          grid[x][y-1] = '2';
                      }*/
                        for(int i=0 ; i<4 ; i++){ //遍历grid[x][y]的上下左右，并把为'1'的格子加入队列
                            int mx = x + dx[i];
                            int my = y + dy[i];
                            if(mx<0 || my<0 || mx>=m || my>=n || grid[mx][my]!='1')
                                continue;
                            queue.offer(mx*n+my);
                            grid[mx][my] = '2';
                        }
                    }

                   /* 不能直接用下面的for循环，需要借助队列；因为for循环只能循环到上下左右，再远一点只会被当成另一个岛屿重新遍历
                   for(int i=0 ; i<4 ; i++){
                       int x = r + dx[i];
                       int y = c + dy[i];
                       if(x<0 || y<0 || x>=m || y>=n || grid[x][y]!='1')
                            continue;
                       grid[x][y] = '2';
                   }*/

                    index++;
                }
            }
        }
        return index;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}