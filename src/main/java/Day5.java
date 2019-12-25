import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.*;

public class Day5 {
    public static void main(String[] args) {
        Solution5 s = new Solution5();
        long now = System.currentTimeMillis();

//        int i = s.firstMissingPositive(new int[]{0, 0, 1, 1, 2, 2, 4});
//        System.out.println(i);
//        double v = s.myPow(2, 3);
//        System.out.println(v);
//        boolean aa = s.isMatch("", "?*");
//        System.out.println(aa);
//        List<List<Integer>> permute = s.permute(new int[]{1,2,3});
//        List<List<Integer>> lists = s.permuteUnique(new int[]{1, 1, 2});
//        System.out.println(new Gson().toJson(lists));

//        System.out.println(Integer.toBinaryString((1 << 31) - 1));
//        System.out.println(Integer.MAX_VALUE);
//        List<List<String>> lists = s.groupAnagrams(new String[]{"boo", "bob", "obb", "", ""});
//        System.out.println(new Gson().toJson(lists));
//        String multiply = s.multiply("123", "456");
//        String s1 = s.addString("4565", "5");
//        System.out.println(multiply);

//        int trap = s.trap(new int[]{2,1,0,2});
//        System.out.println(trap);
        int jump = s.jump(new int[]{5,6,4,4,6,9,4,4,7,4,4,8,2,6,8,1,5,9,6,5,2,7,9,7,9,6,9,4,1,6,8,8,4,4,2,0,3,8,5});
        System.out.println(jump);


        System.out.println("TIME: " + (System.currentTimeMillis() - now) + " ms");
    }
}

class Solution5 {
    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);
        int index = Arrays.binarySearch(nums, 1);
        int res = 1;
        boolean f = false;
        if (index >= 0){
            int r = 1;
            int i = index + 1;
            for (; i < nums.length; i++) {
                if (nums[i] == r + 1) r += 1;
                else if (nums[i] > r + 1){
                    res = r + 1;
                    f = true;
                    break;
                }
            }
            if (!f){
                res = r + 1;
            }
        }
        return res;
    }

    public double myPow(double x, int n) {
        if (n == 0) return 1.0;
        if (x == 1) return x;
        if (x == 0 ){
            if (n < 0) return Double.POSITIVE_INFINITY;
            else return x;
        }
        long ln = n;
        boolean opr = false;
        if (ln < 0){
            ln = -ln;
            opr = true;
        }
        double sum = 1.0;
        for (long i = ln; i > 0; i /= 2) {
            if (i % 2 != 0){
                sum *= x;
            }
            x *= x;
        }
        return opr ? 1 / sum : sum;
    }

    private static int sLen = 0;
    private static int pLen = 0;
    private static String src = null;
    private static String pat = null;
    private static Boolean[][] dp = null;
    public boolean isMatch(String s, String p) {
        sLen = s.length();
        pLen = p.length();
        src = s;
        pat = p;
        dp = new Boolean[sLen + 1][pLen + 1];
        return check(0, 0);
    }

    public boolean check(int m, int n){
        if (dp[m][n] != null) return dp[m][n];
        if (m >= sLen  && n >= pLen) return dp[m][n] = true;
        if (m < sLen && n >= pLen) return dp[m][n] = false;
        if (m >= sLen){
            if (pat.charAt(n) != '*') return dp[m][n] = false;
            return  dp[m][n] = check(m, n + 1);
        }else {
            if (pat.charAt(n) == '?'){
                return dp[m][n] = check(m + 1, n + 1);
            }else if(pat.charAt(n) == '*'){
                for (int i = m; i <= sLen; i++) {
                    if (check(i, n + 1)) return dp[m][n] = true;
                }
            }else {
                if (src.charAt(m) == pat.charAt(n)){
                    return dp[m][n] = check(m + 1, n + 1);
                }else return dp[m][n] = false;
            }
        }
        return dp[m][n] = false;
    }

    private static List<List<Integer>> resPermute = null;
    public List<List<Integer>> permute(int[] nums) {
        resPermute = new ArrayList<>();
        arrange(nums, 0, nums.length);
        return resPermute;
    }
    private void arrange(int[] nums, int index, int length){
        if(index >= length - 1)
        {
            List<Integer> collect = new ArrayList<>();
            for (int num : nums) {
                collect.add(num);
            }

            resPermute.add(collect);
        } else {
            for(int i = index; i < length; i++)
            {
                swap(nums, index, i);
                arrange(nums,index + 1, length);
                swap(nums, index, i);
            }
        }
    }

    private void swap(int[] words, int m, int n){
        if (m == n) return;
        int t = words[m];
        words[m] = words[n];
        words[n] = t;
    }

    private void arrange2(int[] nums, int index, int length){
        if(index >= length - 1)
        {
            List<Integer> collect = new ArrayList<>();
            for (int num : nums) {
                collect.add(num);
            }

            resPU.add(collect);
        } else {
            for(int i = index; i < length; i++)
            {
                swap(nums, index, i);
                arrange2(nums,index + 1, length);
                swap(nums, index, i);
            }
        }
    }

    private static Set<List<Integer>> resPU = null;
    public List<List<Integer>> permuteUnique(int[] nums) {
        resPU = new HashSet<>();
        arrange2(nums, 0, nums.length);
        return new ArrayList<>(resPU);
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = "";
            if (str != null && str.length() > 0){
                char[] chars = str.toCharArray();
                Arrays.sort(chars);
                key = new String(chars);
            }

            List<String> list = map.get(key);
            if (list == null) list = new ArrayList<>();
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) return "0";
        int len1 = num1.length();
        int len2 = num2.length();
        StringBuilder[] sb = new StringBuilder[len1];
        StringBuilder suffix = new StringBuilder();
        for (int i = len1 - 1; i >= 0; i--) {
            char ch = num1.charAt(i);
            int step = 0;
            sb[i] = new StringBuilder();
            if (i < len1 - 1) suffix.append("0");
            sb[i].append(suffix);
            for (int j = len2 - 1; j >= 0; j--) {
                int t = (ch - '0') * (num2.charAt(j) - '0') + step;
                step = t / 10;
                int n = t % 10;
                sb[i].insert(0, n);
            }
            if (step > 0) sb[i].insert(0, step);
        }

        StringBuilder merge = merge(sb, 0, len1 - 1);
        return merge.toString();
    }

    public StringBuilder merge(StringBuilder[] lists, int left, int right){
        if (left == right) return lists[left];
        int mid = left + ((right - left) >> 1);
        StringBuilder a = merge(lists, left, mid);
        StringBuilder b = merge(lists, mid + 1, right);
        return addString(a.toString(), b.toString());
    }

    public StringBuilder addString(String a, String b){
        int len1 = a.length();
        int len2 = b.length();
        int step = 0;
        StringBuilder res = new StringBuilder();
        for (int i = len1 - 1, j = len2 - 1; i >= 0 && j >= 0; i--, j--) {
            char ch1 = a.charAt(i);
            char ch2 = b.charAt(j);
            int sum = ch1 - '0' + ch2 - '0' + step;
            step = sum / 10;
            int n = sum % 10;
            res.insert(0, n);
        }
        StringBuilder prefix = new StringBuilder();
        if (len1 > len2){
            prefix.append(a, 0, len1 - len2);
        }else if (len2 > len1) {
            prefix.append(b, 0, len2 - len1);
        }
        if (step > 0){
            prefix = addString(String.valueOf(step), prefix.toString());
        }
        res.insert(0, prefix);
        return res;
    }

    public int trap(int[] height) {
        int length = height.length;
        if (length < 3) return 0;
        int t = 0;
        int res = 0;
        int i = 0, j = 1;
        for (; j < length; j++) {
            if (j - i  <= 1){
                if (height[j] > height[i]) {
                    i = j;
                }else {
                    t += height[j];
                }
            }else {
                if (height[j] < height[i]){
                    t += height[j];
                }else{
                    res += (j - i - 1) * height[i] - t;
                    t = 0;
                    i = j;
                }
            }
        }

        t = 0;
        for (int k = length - 1, h = k - 1; h >= i; h--) {
            if (k - h  <= 1){
                if (height[h] > height[k]) {
                    k = h;
                }else {
                    t += height[h];
                }
            }else {
                if (height[h] < height[k]){
                    t += height[h];
                }else{
                    res += (k - h - 1) * height[k] - t;
                    t = 0;
                    k = h;
                }
            }
        }
        return res;
    }

    private static int jumpLen = 0;
    private static int jumpRes = 0;
    public int jump(int[] nums) {
        if (nums.length <= 1) return 0;
        jumpLen = nums.length;
        jumpRes = nums.length - 1;
        dfs(nums, 0, 0);

        return jumpRes;
    }

    private void dfs(int[] nums, int res, int m){
        if (m == jumpLen - 1){
            jumpRes = res;
            return;
        }
        if (m + nums[m] >= jumpLen - 1) {
            jumpRes = res + 1;
            return;
        }
        int t = m + nums[m] + nums[m + nums[m]];
        int index = nums[m];
        for (int i = nums[m] - 1; i > 0; i--) {
            if (t >= jumpLen - 1) break;
            int tmp = m + i + nums[m + i];
            if (tmp > t){
                t = tmp;
                index = i;
            }
        }
        dfs(nums, res + 1, m + index);
    }
}
