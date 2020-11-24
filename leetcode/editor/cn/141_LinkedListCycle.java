package leetcode.editor.cn;
//linked-list-cycle
//给定一个链表，判断链表中是否有环。 
//
// 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的
//位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。 
//
// 如果链表中存在环，则返回 true 。 否则，返回 false 。 
//

// 进阶：
// 你能用 O(1)（即，常量）内存解决此问题吗？ 
//
// 示例 1：
// 输入：head = [3,2,0,-4], pos = 1
//输出：true
//解释：链表中有一个环，其尾部连接到第二个节点。
//
// 示例 2：
// 输入：head = [1,2], pos = 0
//输出：true
//解释：链表中有一个环，其尾部连接到第一个节点。
//
// 示例 3：
// 输入：head = [1], pos = -1
//输出：false
//解释：链表中没有环。
//
// 提示： 
//
// 链表中节点的数目范围是 [0, 104] 
// -105 <= Node.val <= 105 
// pos 为 -1 或者链表中的一个 有效索引 。 
// 
// Related Topics 链表 双指针 
// 👍 854 👎 0

public class LinkedListCycle{
    public static void main(String[] args) {
        Solution solution = new LinkedListCycle().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        //采用快慢指针

        if(head == null || head.next == null)
            return false;

        ListNode slow = head ;
        ListNode fast = head.next ; // 1、快指针一开始就设置为，比慢指针多走一步，一开始就没有相遇
        while(fast != null && fast.next != null){ //2、循环结束的两个条件缺一不可，为了避免空指针异常；选用快指针为结束条件，因为快指针走得快

            if(slow == fast) //相遇，这是个环
                return true;

            slow = slow.next; //n表示快慢指针相差的步数；每循环一次，slow+1，fast+2 ，此时快慢指针距离n=n+1-2=n-1
            fast = fast.next.next; //这样就表明每一次while循环都是在使 快慢指针 的距离缩短1步，因此 快慢指针一定会相遇
                                   //有环的情况下，为了使快指针不要跨的步数直接超过了慢指针，而不是二者相遇，因此采用slow+1,fast+2的策略
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}