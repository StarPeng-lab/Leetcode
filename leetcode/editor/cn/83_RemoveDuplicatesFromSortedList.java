package leetcode.editor.cn;
//remove-duplicates-from-sorted-list
//给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。 
//
// 示例 1: 
//
// 输入: 1->1->2
//输出: 1->2
// 
//
// 示例 2: 
//
// 输入: 1->1->2->3->3
//输出: 1->2->3 
// Related Topics 链表 
// 👍 442 👎 0

public class RemoveDuplicatesFromSortedList{
    public static void main(String[] args) {
        Solution solution = new RemoveDuplicatesFromSortedList().new Solution();
        
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
    public ListNode deleteDuplicates(ListNode head) {
        //方法一：prev在外循环，curr在内循环（太耗时了）
        /*
        ListNode prev = head;
        while(prev != null){
            int val = prev.val;
            ListNode curr = prev;
            while(curr.next != null){
                if(curr.next.val == val){
                    curr.next = curr.next.next;
                }else{
                    curr = curr.next;
                }
            }
            prev = prev.next;
        }
        return head;*/

        //方法二：直接法（ 时间复杂度O(n) ）
        ListNode node = head;
        while(node != null && node.next != null){ //由于是排序链表，因此无需考虑1,2,1,2的情况
            if(node.val == node.next.val){
                node.next = node.next.next;
            }else{
                node = node.next;
            }
        }
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}