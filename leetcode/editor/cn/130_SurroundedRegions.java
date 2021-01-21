package leetcode.editor.cn;
//surrounded-regions
//给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。 
//
// 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。 
//
// 示例: 
//
// X X X X
//X O O X
//X X O X
//X O X X
// 
//
// 运行你的函数后，矩阵变为： 
//
// X X X X
//X X X X
//X X X X
//X O X X
// 
//
// 解释: 
//
// 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被
//填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 457 👎 0

public class SurroundedRegions{
    public static void main(String[] args) {
        Solution solution = new SurroundedRegions().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //深度优先算法 + 递归：
    int m,n;
    public void solve(char[][] board) {
        m = board.length;
        if(m==0)
            return;
        n = board[0].length;

        //1、从边界的O开始遍历，将边界的O，与边界相邻的O，标记为A
        for(int r = 0 ; r < m ; r++){
            dfs(board,r,0); //第一列
            dfs(board,r,n-1); //最后一列
        }
        for(int c = 0 ; c < n ; c++){
            dfs(board,0,c); //第一行
            dfs(board,m-1,c); //最后一行
        }

        //2、全部标记完后，遍历整个board，将O改为X（此时的O是被X包围的O），将A改回O
        for(int r = 0 ; r < m ; r++){
            for(int c = 0 ; c < n ; c++){
                if(board[r][c] == 'O'){ //此时的O是被X包围的O
                    board[r][c] = 'X';
                }else if(board[r][c] == 'A'){ //此时的A是边界上的O或未被包围的O
                    board[r][c] = 'O';
                }
            }
        }

    }
    private void dfs(char[][] board , int r , int c){ //调用dfs函数的语句，是从边界开始传入
        //if(!(0<=r && r<board.length && 0<=c && c<board[0].length) || board[r][c] != 'O')
        if(r<0 || r>=m || c<0 || c>=n || board[r][c] != 'O')
            return;

        board[r][c] = 'A';
        dfs(board,r+1,c);
        dfs(board,r-1,c);
        dfs(board,r,c+1);
        dfs(board,r,c-1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}