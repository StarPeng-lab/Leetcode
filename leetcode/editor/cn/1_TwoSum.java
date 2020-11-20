package leetcode.editor.cn;
//two-sum
//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
// 示例:
// 给定 nums = [2, 7, 11, 15], target = 9
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// Related Topics 数组 哈希表 
// 👍 9598 👎 0

public class TwoSum{
    public static void main(String[] args) {
        Solution solution = new TwoSum().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] sum = new int[2];
        //方式一：暴力求解
        /*
        for(int i = 0 ; i < nums.length ; i++){
            for(int j = i+1; j < nums.length ; j++){
                if(target == nums[i]+nums[j]){
                    sum[0] = i;
                    sum[1] = j;
                }
            }
        }
        return sum;
        */

        //方式二：两遍Hash
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        //由于return语句在里层，可能没有return，因此需要抛出异常，否则提交不成功
        throw new IllegalArgumentException("No two sum solution");

        //方式三：一遍Hash
        /*Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");*/

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}