/**
 * 001. Two Sum —— 解法一：暴力双层循环
 * 难度：Easy
 * 链接：https://leetcode.cn/problems/two-sum/
 *
 * 思路：枚举所有数对 (i, j) 且 j > i，检查 nums[i] + nums[j] 是否等于 target。
 * 找到第一组就可以返回。原来用标签 break outer 跳出两层循环也是对的，
 * 但这里改成 return 更直接 —— 方法一返回，循环自然就结束了。
 *
 * 复杂度：时间 O(n^2)，空间 O(1)
 *
 * 说明：这是我第一次自己写出来的版本，保留下来做对比。
 * 这题面试的标准答案是哈希表一次遍历（O(n)），见 SolutionHashMap.java。
 */
class SolutionBruteForce {

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }
        }

        // 题目保证有解，正常不会走到这里
        throw new IllegalArgumentException("no solution");
    }

    public static void main(String[] args) {
        SolutionBruteForce s = new SolutionBruteForce();

        int[] r1 = s.twoSum(new int[] { 2, 7, 11, 15 }, 9);
        System.out.println(r1[0] + ", " + r1[1] + "  (期望 0, 1)");

        int[] r2 = s.twoSum(new int[] { 3, 2, 4 }, 6);
        System.out.println(r2[0] + ", " + r2[1] + "  (期望 1, 2)");

        int[] r3 = s.twoSum(new int[] { 3, 3 }, 6);
        System.out.println(r3[0] + ", " + r3[1] + "  (期望 0, 1)");
    }
}
