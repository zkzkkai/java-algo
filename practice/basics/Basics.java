import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Java 语法基础练习。
 *
 * 用法：打开注释掉的一段，逐段跑。别一次全跑完，一次只练一个主题。
 */
public class Basics {

    public static void main(String[] args) {
        // ===== 1. 数组与循环 =====
        int[] nums = { 5, 2, 9, 1 };
        int max = nums[0];
        for (int n : nums) {
            if (n > max) {
                max = n;
            }
        }
        System.out.println("最大值: " + max);

        // 排序（原数组会被改动）
        Arrays.sort(nums);
        System.out.println("排序后: " + Arrays.toString(nums));

        // ===== 2. String 的不可变性 =====
        String a = "hello";
        String b = "hello";
        String c = new String("hello");
        System.out.println("a == b        : " + (a == b));          // true，字符串常量池同一个对象
        System.out.println("a == c        : " + (a == c));          // false，new 出来的是新对象
        System.out.println("a.equals(c)   : " + a.equals(c));       // true，比较内容

        // ===== 3. 集合：List =====
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("apple");   // List 允许重复
        System.out.println("list: " + list);
        System.out.println("第一个元素: " + list.get(0));
        System.out.println("长度: " + list.size());

        // ===== 4. 集合：Map =====
        Map<String, Integer> count = new HashMap<>();
        for (String s : list) {
            // getOrDefault：key 不存在时返回默认值，省掉一次判空
            count.put(s, count.getOrDefault(s, 0) + 1);
        }
        System.out.println("计数: " + count);

        // 遍历 Map 的推荐写法
        for (Map.Entry<String, Integer> e : count.entrySet()) {
            System.out.println("  " + e.getKey() + " -> " + e.getValue());
        }

        // ===== 5. 自动装箱的坑 =====
        Integer x = 127;
        Integer y = 127;
        Integer p = 128;
        Integer q = 128;
        System.out.println("127 == 127 : " + (x == y));   // true，-128~127 走缓存
        System.out.println("128 == 128 : " + (p == q));   // false，超出缓存范围是新对象
        // 结论：Integer 比较大小永远用 .equals()
    }
}
