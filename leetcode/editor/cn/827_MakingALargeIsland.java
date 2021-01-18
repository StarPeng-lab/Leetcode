package leetcode.editor.cn;
//making-a-large-island
//在二维地图上， 0代表海洋， 1代表陆地，我们最多只能将一格 0 海洋变成 1变成陆地。 
//
// 进行填海之后，地图上最大的岛屿面积是多少？（上、下、左、右四个方向相连的 1 可形成岛屿） 
//
// 示例 1: 
//
// 
//输入: [[1, 0], [0, 1]]
//输出: 3
//解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
// 
//
// 示例 2: 
//
// 
//输入: [[1, 1], [1, 0]]
//输出: 4
//解释: 将一格0变成1，岛屿的面积扩大为 4。 
//
// 示例 3: 
//
// 
//输入: [[1, 1], [1, 1]]
//输出: 4
//解释: 没有0可以让我们变成1，面积依然为 4。 
//
// 说明: 
//
// 
// 1 <= grid.length = grid[0].length <= 50 
// 0 <= grid[i][j] <= 1 
// 
// Related Topics 深度优先搜索 
// 👍 70 👎 0

public class MakingALargeIsland{
    public static void main(String[] args) {
        Solution solution = new MakingALargeIsland().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //大致思路：先DFS遍历一遍陆地，标记上所有岛屿的面积；再DFS一遍海洋，搜索哪个海洋格子相邻的两个岛屿面积最大，面积=两个岛屿面积+1，因此要记录每个岛屿的面积
    //问题：如果直接在岛屿上标记面积，就无法区分一个海洋格子相邻的两个 7 是不是来自同一个岛屿；
    //解决：不能直接在方格中标记岛屿的面积，而应该标记岛屿的索引（下标），另外用一个数组num记录每个岛屿的面积，我们用HashMap代替数组
    //实际上是对网格做了两遍 DFS：第一遍 DFS 遍历陆地格子，计算每个岛屿的面积并标记岛屿；第二遍 DFS 遍历海洋格子，观察每个海洋格子相邻的陆地格子。
    public int largestIsland(int[][] grid) {
        int index=2;
        int res=0;
        HashMap<Integer,Integer> map = new HashMap<>(); //记录不同的岛屿，(索引,面积)，索引从2开始
        for(int r=0 ; r<grid.length ; r++){
            for(int c=0 ; c<grid[0].length ; c++){
                if(grid[r][c] == 1){ //1、遍历陆地
                    int a = area(grid,r,c,index); //计算每个岛屿的面积，并做在岛屿上标记index
                    map.put(index,a); //第index个岛屿的面积为a
                    index++;
                    res = Math.max(res,a);
                }
            }
        }

        if(res == 0)
            return 1; //如果没有陆地，那么填一个海洋

        for(int r=0 ; r<grid.length ; r++){
            for(int c=0 ; c<grid[0].length ; c++){
                if(grid[r][c] == 0){ //2、遍历海洋
                    HashSet<Integer> set = seaNeighbour(grid,r,c);
                    if(set.size() < 1){ //说明这个海洋格子周围没有陆地
                        continue;
                    }
                    int seaRes = 1; //填的海算作1
                    for(Integer i : set){
                        seaRes += map.get(i); //计算每个海洋被填海后，连通的岛屿面积
                    }
                    res = Math.max(res,seaRes); //取max
                }
            }
        }

        return res;
    }

    private int area(int[][] grid , int r , int c , int index){ //计算以grid[r][c]开始的第index个岛屿的面积res
        if(!inArea(grid, r, c))
            return 0;
        if(grid[r][c] != 1)
            return 0;

        grid[r][c] = index; //标记遍历过的陆地，是第index个岛屿
        return 1+
                area(grid,r+1,c,index)+
                area(grid,r,c-1,index)+
                area(grid,r-1,c,index)+
                area(grid,r,c+1,index); //每遍历到一个格子，就把面积加一
    }

    private HashSet<Integer> seaNeighbour(int[][] grid , int r , int c){ //观察海洋上下左右的陆地格子，用HashSet存储陆地的索引，由于是HashSet，可以去重，不必再判断是否隔着同一片陆地
        HashSet<Integer> set = new HashSet<>();
        if(inArea(grid,r+1,c) && grid[r+1][c] != 0) // 0是海洋，上
            set.add(grid[r+1][c]);
        if(inArea(grid,r,c-1) &&grid[r][c-1] != 0) // 左
            set.add(grid[r][c-1]);
        if(inArea(grid,r-1,c) &&grid[r-1][c] != 0) //下
            set.add(grid[r-1][c]);
        if(inArea(grid,r,c+1) &&grid[r][c+1] != 0) //右
            set.add(grid[r][c+1]);

        return set;
    }

    private boolean inArea(int[][] grid, int r , int c){
        return 0<=r && r<grid.length && 0<=c && c<grid[0].length;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}