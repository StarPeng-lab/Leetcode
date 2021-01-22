package leetcode.editor.cn;
//as-far-from-land-as-possible
//你现在手里有一份大小为 N x N 的 网格 grid，上面的每个 单元格 都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地，请你找出一个海洋单
//元格，这个海洋单元格到离它最近的陆地单元格的距离是最大的。 
//
// 我们这里说的距离是「曼哈顿距离」（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个单元格之间的距离是 |x0 - 
//x1| + |y0 - y1| 。 
//
// 如果网格上只有陆地或者海洋，请返回 -1。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：[[1,0,1],[0,0,0],[1,0,1]]
//输出：2
//解释： 
//海洋单元格 (1, 1) 和所有陆地单元格之间的距离都达到最大，最大距离为 2。
// 
//
// 示例 2： 
//
// 
//
// 输入：[[1,0,0],[0,0,0],[0,0,0]]
//输出：4
//解释： 
//海洋单元格 (2, 2) 和所有陆地单元格之间的距离都达到最大，最大距离为 4。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= grid.length == grid[0].length <= 100 
// grid[i][j] 不是 0 就是 1 
// 
// Related Topics 广度优先搜索 图 
// 👍 176 👎 0

public class AsFarFromLandAsPossible{
    public static void main(String[] args) {
        Solution solution = new AsFarFromLandAsPossible().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //这道题要找的是距离陆地最远的海洋格子。假设网格中只有一个陆地格子，我们可以从这个陆地格子出发做层序遍历，直到所有格子都遍历完。
    // 最终遍历了几层，海洋格子的最远距离就是几
    //有多个陆地格子时,一种方法是将每个陆地格子都作为起点做一次层序遍历，但是这样的时间开销太大；
    //可以利用 BFS，以多个格子同时作为起点。把所有的陆地格子同时放入初始队列（多源BFS），然后开始层序遍历
    public int maxDistance(int[][] grid) {
        int distance = -1 ; //第1层都是陆地格子
        int n = grid.length;
        Queue<int[]> queue = new LinkedList<>();

        for(int i=0 ; i<n; i++){
            for(int j=0 ; j<n; j++){
                if(grid[i][j] == 1) //把所有陆地格子放入队列
                    queue.offer(new int[]{i,j});
            }
        }

        if(queue.isEmpty() || queue.size() == n*n){ //没有陆地格子/全是陆地格子
            return -1;
        }

        int[][] direct = new int[][]{
                {0,1},{0,-1},{1,0},{-1,0}
        };
        while(!queue.isEmpty()){
            distance++; //层数+1
            int size = queue.size(); //离陆地格子第distance层的海洋格子数（第1层都是陆地格子）
            for(int i = 0 ; i < size ; i++){
                int[] addr = queue.poll();
                int r = addr[0];
                int c = addr[1];

                for(int[] d : direct){
                    int x = r + d[0];
                    int y = c + d[1];
                    if(x>=0 && y>=0 && x<n && y<n && grid[x][y] == 0){ //如果在网格中，并且是海洋格子
                        grid[x][y] = 2; //将海洋格子重新标记为2
                        queue.offer(new int[]{x,y});
                    }
                }
            }
        }
        return distance;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}