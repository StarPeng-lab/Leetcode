package leetcode.editor.cn;
//middle-of-the-linked-list
//给定一个头结点为 head 的非空单链表，返回链表的中间结点。 
//
// 如果有两个中间结点，则返回第二个中间结点。 
//
// 
//
// 示例 1： 
//
// 
//输入：[1,2,3,4,5]
//输出：此列表中的结点 3 (序列化形式：[3,4,5])
//返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
//注意，我们返回了一个 ListNode 类型的对象 ans，这样：
//ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = 
//NULL.
// 
//
// 示例 2： 
//
// 
//输入：[1,2,3,4,5,6]
//输出：此列表中的结点 4 (序列化形式：[4,5,6])
//由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
// 
//
// 
//
// 提示： 
//
// 
// 给定链表的结点数介于 1 和 100 之间。 
// 
// Related Topics 链表 
// 👍 295 👎 0

public class MiddleOfTheLinkedList{
    public static void main(String[] args) {
        Solution solution = new MiddleOfTheLinkedList().new Solution();
        
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
    public ListNode middleNode(ListNode head) {
        ListNode node = head;
        int size = 0;
        while (node != null) { //记录链表长度，从0开始，有几个节点就size为几
            size++;
            node = node.next;
        }

        node = head; //由于node一开始就赋值为head,因此for循环从 i=1 开始
        for (int i = 1; i <= size / 2; i++) { //无论size为奇数、偶数，都是从1循环到size/2，取到节点
            node = node.next;
        }
        return node;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}