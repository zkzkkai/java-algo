import java.util.HashMap;
import java.util.Map;

/**
 * 001. Two Sum
 * 难度：Easy
 * 链接：https://leetcode.cn/problems/two-sum/
 *
 * 思路：一次遍历，用哈希表记录「已见过的值 -> 下标」。
 * 遍历到 nums[i] 时，检查 target - nums[i] 是否已经出现过；
 * 出现过就直接返回两个下标。这样不用双重循环。
 *
 * 复杂度：时间 O(n)，空间 O(n)
 *
 * 易错点：
 * 1. 必须先把「当前值的补数」查完，再把当前值放进表里，
 *    否则同一个元素会被用两次。
 * 2. 返回的是下标数组，不是值。
 * 3. 这题用 HashMap 而不是 HashSet，因为要记下标。
 */
class SolutionHashMap {

    public int[] twoSum(int[] nums, int target) {
        // 值 -> 下标
        Map<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (seen.containsKey(need)) {
                return new int[] { seen.get(need), i };
            }
            seen.put(nums[i], i);
        }

        // 题目保证有解，正常不会走到这里
        throw new IllegalArgumentException("no solution");
    }

    public static void main(String[] args) {
        SolutionHashMap s = new SolutionHashMap();

        int[] r1 = s.twoSum(new int[] { 2, 7, 11, 15 }, 9);
        System.out.println(r1[0] + ", " + r1[1] + "  (期望 0, 1)");

        int[] r2 = s.twoSum(new int[] { 3, 2, 4 }, 6);
        System.out.println(r2[0] + ", " + r2[1] + "  (期望 1, 2)");

        int[] r3 = s.twoSum(new int[] { 3, 3 }, 6);
        System.out.println(r3[0] + ", " + r3[1] + "  (期望 0, 1)");
    }
}
