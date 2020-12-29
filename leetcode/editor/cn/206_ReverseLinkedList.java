package leetcode.editor.cn;
//reverse-linked-list
//反转一个单链表。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表 
// 👍 1349 👎 0

public class ReverseLinkedList{
    public static void main(String[] args) {
        Solution solution = new ReverseLinkedList().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        //方法一：用递归（可参考恋上数据结构4-8）
        //      if(head == null) return null;
        //      if(head.next == null) return head;
        /*
        if(head == null || head.next == null)
            return head;
        ListNode newHead = reverseList(head.next); //保留newHead，递归到最末尾时的末尾.next即为newHead
        head.next.next = head;   // head.next的next指回head
        head.next = null; // head.next置空
        return newHead; // 无论递归多少次，newHead都没有被改变，都是最末尾的末尾.next
        */

        //方法二：非递归，用迭代
        if(head == null || head.next == null)
            return head;
        ListNode newHead = null;
        while(head != null){
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}