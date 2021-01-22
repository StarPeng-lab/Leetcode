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
      //方法一：深度优先遍历+递归
      //1、
      public int maxAreaOfIsland(int[][] grid) {
          int res = 0;
          for(int r = 0 ; r < grid.length ; r++){
              for(int c = 0 ; c < grid[0].length ; c++){
                  if(grid[r][c] == 1){
                      int ans = area(grid,r,c); //将岛屿的r,c传入dfs，开始遍历
                      res = Math.max(res,ans);
                  }
              }
          }
          return res;
      }
      private int area(int[][] grid , int r , int c){ //每遍历到一个格子，就把面积加一；
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

    //2、
      /*public int maxAreaOfIsland(int[][] grid) {
          int ans = 0;
          for (int r = 0; r != grid.length; ++r) {
              for (int c = 0; c != grid[0].length; ++c) {
                  ans = Math.max(ans, dfs(grid, r, c));
              }
          }
          return ans;
      }

      public int dfs(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r == grid.length || c == grid[0].length || grid[r][c] != 1) {
            return 0;
        }
        grid[r][c] = 0; //可以标记为0，也可以标记为2
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        int ans = 1; //递归时，由1开始；即遍历到一个格子，面积ans+1
        for (int i = 0; i != 4; ++i) {
            int x = r + dx[i];
            int y = c + dy[i];
            ans += dfs(grid, x, y);
        }
        return ans;
    }*/

    //3、深度优先遍历+栈
    //前面的1、2都是通过函数的调用来表示接下来想要遍历哪些土地，让下一层函数来访问这些土地。
    // 而3是把接下来想要遍历的土地放在栈里，然后在取出这些土地的时候访问它们。
    //访问每一片土地时，我们将对围绕它四个方向进行探索，找到还未访问的土地，加入到栈 stack 中；
    //另外，只要栈 stack 不为空，就说明我们还有土地待访问，那么就从栈中取出一个元素并访问。
    /*public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        for (int r = 0; r != grid.length; ++r) {
            for (int c = 0; c != grid[0].length; ++c) {
                if(grid[r][c] == 1){

                    int ans = 0;
                    Deque<Integer> stack1 = new LinkedList<Integer>();
                    Deque<Integer> stack2 = new LinkedList<Integer>();
                    stack1.push(r);
                    stack2.push(c);
                    while (!stack1.isEmpty()) {
                        int x = stack1.pop();
                        int y = stack2.pop();

                        if (x < 0 || y < 0 || x == grid.length || y == grid[0].length || grid[x][y] != 1) {
                            continue;
                        }

                        ans++;
                        grid[x][y] = 0; //将遍历到的格子重新标记为0

                        int[] dx = {0, 0, 1, -1};
                        int[] dy = {1, -1, 0, 0};
                        for (int i = 0; i != 4; ++i) { //遍历上下左右
                            int mx = x + dx[i];
                            int my = y + dy[i];
                            stack1.push(mx);
                            stack2.push(my);
                        }
                    }
                    res = Math.max(res, ans);

                }
            }
        }
        return res;
    }*/


    //方法二：广度优先遍历+迭代（借助队列遍历完整个岛屿），耗时更久
    //栈改为队列，每次从队首取出土地，并将接下来想要遍历的土地放在队尾，就实现了广度优先搜索算法
    /*public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dx = new int[]{0,0,1,-1};
        int[] dy = new int[]{1,-1,0,0};
        int res = 0;
        int ans = 0;
        for(int r = 0 ; r < m ; r++){
            for(int c = 0 ; c < n ; c++){
                if(grid[r][c] == 1){
                    ans++; //每遍历到一个格子，面积+1
                    Queue<Integer> queue = new LinkedList<>();
                    queue.offer(r*n+c);
                    grid[r][c] = 2;
                    while(!queue.isEmpty()){ //以grid[r][c]为起点，遍历所在的整个岛屿（利用队列，先遍历上下左右，再把上下左右中为1的格子加入队列，继续遍历它们的上下左右，直到队列为空）
                        int value = queue.poll();
                        int x = value / n; //r
                        int y = value % n; //c
                        for(int i = 0 ; i < 4 ; i++){
                            int mx = x + dx[i];
                            int my = y + dy[i];
                            if(mx<0 || my<0 || mx>=m || my>=n || grid[mx][my]!=1)
                                continue;
                            ans++;
                            grid[mx][my] = 2;
                            queue.offer(mx*n+my);
                        }
                    }
                }
                res = Math.max(res,ans);
                ans = 0;
            }
        }
        return res;
    }*/
}
//leetcode submit region end(Prohibit modification and deletion)

}