import com.google.gson.Gson;

import java.util.*;

public class Day2 {
    public static void main(String[] args) {
        Solution2 s = new Solution2();
//        int[] arr = {1,2,3,4};
//        int i = s.maxArea(arr);
//        System.out.println(i);
//        String s1 = s.intToRoman(3973);
////        System.out.println(s1);
//        int mmmcmlxxiii = s.romanToInt("MMMCMLXXIII");
//        System.out.println(mmmcmlxxiii);
//        String s1 = s.longestCommonPrefix(new String[]{"1222222222", "13"});
//        System.out.println(s1);
//        System.out.println("12".substring(0,2));
//        List<String> strings = s.letterCombinations("");
//        System.out.println(strings);
//        ListNode listNode = new ListNode(1);
//        listNode.next = new ListNode(2);
//        listNode.next.next = new ListNode(3);
//        listNode.next.next.next = new ListNode(4);
//        System.out.println(new Gson().toJson(listNode));
//        ListNode listNode1 = s.removeNthFromEnd(listNode, 4);
//        System.out.println(new Gson().toJson(listNode1));
//        boolean valid = s.isValid("{{([][])}}");
//        System.out.println(valid);
        List<List<Integer>> lists = s.fourSum(new int[]{-1, 0, 1, 2, -1, -4}, 2);
        System.out.println(new Gson().toJson(lists));
//        int i = Arrays.binarySearch(new int[]{0, 1, 2, 5 }, 3);
//        System.out.println(i);
//        int i = s.threeSumClosest(new int[]{1, 2, 5, 10, 11}, 12);
//        System.out.println(i);
    }
}

class Solution2 {
    private static int len = 0;
    private static int[] num;
    public int maxArea(int[] height) {
        len = height.length;
        num = height;
        return fuc(0, len - 1);
    }

    private int fuc(int m, int n){
        if (m >= len || n < 0) return 0;
//        if (dp[m][n] != null) return dp[m][n];
        if (m >= n) return 0;
        int s = Math.min(num[m], num[n]) * (n - m);
//        dp[m][n] = s;
        if (num[m] > num[n]){
            return Math.max(s, fuc(m, n - 1));
        }else {
            return Math.max(s, fuc(m + 1, n));
        }
    }

    public String intToRoman(int num) {
        String[] values = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int[] index = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (; num > 0 && i < index.length; i++) {
            int key = index[i];
            if (num >= key){
                sb.append(values[i]);
                num -= key;
                if (i % 4 == 0) i -= 1;
            }
        }
        return sb.toString();
    }

    public int romanToInt(String s) {
//        String[] values = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
//        int[] index = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
//        char[] v = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
//        int[] key = {1000,500,100,50,10,5,1};
        s += "#";
        char[] chars = s.toCharArray();
        int i = 0;
        int res = 0;
        boolean f = false;
        while (i < chars.length){
            char ch = chars[i++];
            switch (ch){
                case 'M': res += 1000;
                    break;
                case 'D': res += 500;
                    break;
                case 'C': ch = chars[i++];
                    if (ch == 'M'){
                        res +=  900;
                    }else if (ch == 'D'){
                        res += 400;
                    }else if (ch == '#'){
                        res += 100;
                        f = true;
                    }else {
                        res += 100;
                        i--;
                    }
                    break;
                case 'L': res += 50;
                    break;
                case 'X': ch = chars[i++];
                    if (ch == 'C'){
                        res += 90;
                    }else if (ch == 'L'){
                        res += 40;
                    }else if (ch == '#'){
                        res += 10;
                        f = true;
                    }else {
                        res += 10;
                        i--;
                    }
                    break;
                case 'V': res += 5;
                    break;
                case 'I': ch = chars[i++];
                    if (ch == 'X'){
                        res += 9;
                    }else if (ch == 'V'){
                        res += 4;
                    }else if (ch == '#'){
                        res += 1;
                        f = true;
                    }else {
                        res += 1;
                        i--;//back
                    }
                    break;
                case '#':
                    f = true; break;
            }
            if(f) break;
        }
        return res;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length <= 0) return "";
        int length = strs[0].length();
        boolean f = false;
        int index = length - 1;
        Character ch = null;
        int tag = 0;
        for (int j = 0; j < length; j++) {
            ch = strs[0].charAt(j);
            tag = 1;
            for (int i = 1; i < strs.length; i++) {
                if (j < strs[i].length()){
                    if (ch == strs[i].charAt(j)){
                        tag += 1;
                    }else {
                        f = true;
                        index = j;
                        break;
                    }
                }else {
                    f = true;
                    index = j - 1;
                    break;
                }
            }
            if (tag < strs.length){
                index = j - 1;
                break;
            }
            if (f) break;
        }

        return strs[0].substring(0, index+1);
    }



    public List<String> letterCombinations(String digits) {
        String[] values = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> res = new ArrayList<String>();
        if (digits.length() <= 0) return res;
        //init lsb
        String str = values[digits.charAt(0) - '0' - 2];
        for (int i = 0; i < str.length(); i++) {
            res.add(String.valueOf(str.charAt(i)));
        }

        int length = digits.length();
        for (int i = 1; i < length; i++) {
            str = values[digits.charAt(i) - '0' - 2];
            int size = res.size();
            List<String> lsb = res;
            res = new ArrayList<String>();
            for (int k = 0; k < size; k ++) {
                for (int j = 0; j < str.length(); j++) {
                    res.add(lsb.get(k) + str.charAt(j));
                }
            }

        }
        return res;
    }


    public ListNode removeNthFromEnd(ListNode head, int n) {
        //快慢双指针
        ListNode p1 = head,p2 = null;
        ListNode tmp = head;
        while (n > 0 && p1 != null){
            p1 = p1.next;
            n--;
        }
        while (p1 != null){
            p1 = p1.next;
            p2 = tmp;
            tmp = tmp.next;
        }
        if (p2 != null){
            p2.next = tmp.next;
        }else {
            head = head.next;
        }
        return head;
    }

    public boolean isValid(String s) {
        boolean res = true;
        Stack<Character> stack = new Stack<Character>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') stack.push(c);
            else if (c == ')' || c == '}' || c == ']'){
                if (stack.empty()){
                    res = false;
                    break;
                }else {
                    Character pop = stack.pop();
                    if (!((c == ')' && pop == '(') || ( c == '}' && pop == '{') || (c == ']' && pop == '['))){
                        res = false;
                        break;
                    }
                }
            }else {
                res = false;
            }
        }
        return stack.empty() && res;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> res = new HashSet<List<Integer>>();
        if (nums.length < 3) return new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        List<Integer> ln = null;
        int length = nums.length;
        for (int i = 0;  i < length - 2; i++) {
            if (nums[i] > 0) break;
            for (int j = i + 1; j < length - 1; j++) {
                int sum2 = nums[i] + nums[j];
                if (sum2 > 0 && nums[j] > 0) break;
                int b = Arrays.binarySearch(nums, j + 1, length, -sum2);
                if (b >= 0){
                    res.add(Arrays.asList(nums[i], nums[j], nums[b]));
                }
            }
        }

        return new ArrayList<List<Integer>>(res);
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> res = new HashSet<List<Integer>>();
        if (nums.length < 3) return new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        List<Integer> ln = null;
        int length = nums.length;
        for (int i = 0;  i < length - 3; i++) {
            for (int k = i + 1; k < length -2; k++) {
                for (int j = k + 1; j < length - 1; j++) {
                    int sum3 = nums[i] + nums[k] + nums[j];
                    int b = Arrays.binarySearch(nums, j + 1, length, target - sum3);
                    if (b >= 0){
                        res.add(Arrays.asList(nums[i],nums[k], nums[j], nums[b]));
                    }
                }
            }
        }

        return new ArrayList<List<Integer>>(res);
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int length = nums.length;
        Integer result = null;
        for (int i = 0;  i < length - 2; i++) {
            for (int j = i + 1; j < length - 1; j++) {
                int res = 0;
                int sum2 = nums[i] + nums[j];
                int b = Arrays.binarySearch(nums, j + 1, length, target - sum2);
                if (b >= 0){
                    return target;
                }else {
                    int ci = -(b + 1);
                    if (ci > j + 1){
                        if (ci < length){
                            res = sum2 + nums[ci];
                            int res2 = sum2 + nums[ci - 1];
                            if (target - res2 < res - target) res = res2;
                        }else {
                            res = sum2 + nums[ci - 1];
                        }
                    }else {
                        res = sum2 + nums[j + 1];
                    }
                }
                if (result == null){
                    result = res;
                }else {
                    if (Math.abs(result - target) > Math.abs(res - target)){
                        result = res;
                    }
                }

            }
        }
        return result;
    }

}


class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
}
