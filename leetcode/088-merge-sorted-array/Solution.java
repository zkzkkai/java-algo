import java.util.Arrays;

/**
 * 088. Merge Sorted Array
 * 难度：Easy
 * 链接：https://leetcode.cn/problems/merge-sorted-array/
 *
 * 思路：从后往前填。
 * nums1 的后半段是空的（用来容纳 nums2），所以从最大的元素开始比较，
 * 把较大的那个放到 nums1 的末尾，然后指针往前移。
 *
 * 为什么必须从后往前：如果从前往后写 nums1[k]，会把还没比较的 nums1 元素覆盖掉；
 * 从后往前写的位置，永远是还没被读到的空位，不会覆盖有效数据。
 *
 * 复杂度：时间 O(m + n)，空间 O(1)
 *
 * 易错点：
 * 1. 循环结束后如果 nums2 还有剩余，必须复制过去；nums1 有剩余则不用处理，
 *    因为它们本来就在正确位置上（这正是从后往前填带来的好处）。
 * 2. i 和 j 从 m-1、n-1 开始，不是从 length-1 开始 —— nums1 后半段是无效数据。
 */
class Solution {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;          // nums1 有效部分的最后一个元素
        int j = n - 1;          // nums2 的最后一个元素
        int k = m + n - 1;      // nums1 的最后一个位置

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        // 如果 nums2 还有剩余，直接复制到 nums1 前面
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }

        // 如果 nums1 还有剩余，不需要处理，因为它们本来就在正确位置
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        int[] nums1a = { 1, 2, 3, 0, 0, 0 };
        s.merge(nums1a, 3, new int[] { 2, 5, 6 }, 3);
        System.out.println(Arrays.toString(nums1a) + "  (期望 [1, 2, 2, 3, 5, 6])");

        int[] nums1b = { 1 };
        s.merge(nums1b, 1, new int[] {}, 0);
        System.out.println(Arrays.toString(nums1b) + "  (期望 [1])");

        int[] nums1c = { 0 };
        s.merge(nums1c, 0, new int[] { 1 }, 1);
        System.out.println(Arrays.toString(nums1c) + "  (期望 [1])");

        int[] nums1d = { 4, 5, 6, 0, 0, 0 };
        s.merge(nums1d, 3, new int[] { 1, 2, 3 }, 3);
        System.out.println(Arrays.toString(nums1d) + "  (期望 [1, 2, 3, 4, 5, 6])");
    }
}
