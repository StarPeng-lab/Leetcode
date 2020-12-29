package leetcode.editor.cn;
//remove-linked-list-elements
//删除链表中等于给定值 val 的所有节点。 
//
// 示例: 
//
// 输入: 1->2->6->3->4->5->6, val = 6
//输出: 1->2->3->4->5
// 
// Related Topics 链表 
// 👍 504 👎 0

public class RemoveLinkedListElements{
    public static void main(String[] args) {
        Solution solution = new RemoveLinkedListElements().new Solution();
        
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
    //删除步骤：找到该节点的前一个节点，进行利用前驱节点进行删除操作
    public ListNode removeElements(ListNode head, int val) {
        //时间复杂度：O(N)，只遍历了一次。
        //空间复杂度：O(1)。 \mathcal{O}(1)

       //方法一：利用哨兵节点做伪头
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        //设定当前节点和前驱节点
        ListNode prev = sentinel;
        ListNode curr = head;

        while(curr != null){
            if(curr.val == val){
                prev.next = curr.next; // prev.next = prev.next.next;
            }else{
                prev = curr;
            }
            curr = curr.next;
        }
        return sentinel.next;

        //方法一：创建一个虚拟头结点
        /*
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        ListNode prev = sentinel;
        while(prev.next != null){
            if(prev.next.val == val){
                prev.next = prev.next.next;
            }else{
                prev = prev.next;
            }
        }
        return sentinel.next;*/

        //方法二：删除头结点时另做考虑（由于头结点没有前一个结点）
        /*
        while(head != null && head.val == val){
            head = head.next;
        }
        if(head == null)
            return head;

        ListNode prev = head;
        while(prev.next != null){
            if(prev.next.val == val){
                prev.next = prev.next.next;
            }else{
                prev = prev.next;
            }
        }
        return head;*/

        //方法三：递归
        /*
        if(head == null)
            return null;
        head.next = removeElements(head.next,val); //开始递归
        if(head.val == val){
            return head.next;
        }else{
            return head;
        }*/

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}