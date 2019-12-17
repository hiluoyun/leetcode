import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.List;

public class Day1 {

    public static void main(String[] args) {
        Solution s = new Solution();
//        int[] a = {2, 4, 5};
//        int[] b = {1, 4, 5};
//        ListNode s1 = null, l1 = null, s2 = null, l2 = null;
//        for (int i = 0; i < a.length; i++) {
//            if (i == 0){
//                s1 = l1 = new ListNode(a[i]);
//            }
//            else {
//                s1.next = new ListNode(a[i]);
//                s1 = s1.next;
//            }
//        }
//        for (int i = 0; i < b.length; i++) {
//            if (i == 0)
//            {
//                s2 = l2 = new ListNode(b[i]);
//            }
//            else {
//                s2.next = new ListNode(b[i]);
//                s2 = s2.next;
//            }
//        }
//        System.out.println(new Gson().toJson(l1));
//        System.out.println(new Gson().toJson(l2));
//        ListNode listNode = s.addTwoNumbers(l1, l2);
//        System.out.println(new Gson().toJson(listNode));
//        int ababcabvd = s.lengthOfLongestSubstring("dvdf");
//        System.out.println(ababcabvd);
//        int[] a1 = {1};
//        int[] a2 = {};
//        double medianSortedArrays = s.findMedianSortedArrays(a1, a2);
//        System.out.println(medianSortedArrays);
//        String babad = s.longestPalindrome("");
//        System.out.println(babad);
//        int reverse = s.reverse(Integer.MIN_VALUE);
//        System.out.println(reverse);
//        boolean aab = s.isMatch("bbbba", ".*a*a");
//        System.out.println(aab);
//        System.out.println("ab".charAt(2));
//        String convert = s.convert("", 2);
//        System.out.println(convert);
        String ss = "-31";
        int i = s.myAtoi(ss);
        System.out.println(i);
    }
}

class ListNode2 {
    int val;
    ListNode next;
    ListNode2(int x) { val = x; }
}

class Solution {


    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        StringBuilder s1 = new StringBuilder(), s2 = new StringBuilder();
        while (l1 != null){
            s1.append(l1.val);
            l1 = l1.next;
        }

        while (l2 != null){
            s2.append(l2.val);
            l2 = l2.next;
        }
        long a = Long.parseLong(s1.reverse().toString());
        long b = Long.parseLong(s2.reverse().toString());
        Long sum = a + b;
        char[] sumArr = new StringBuilder(sum.toString()).reverse().toString().toCharArray();

        ListNode res = null, ln = null;
        for (int i = 0; i < sumArr.length; i++) {
            int v = Integer.parseInt(String.valueOf(sumArr[i]));
            if (i == 0){
                ln = res = new ListNode(v);
            }else{
                ln.next = new ListNode(v);
                ln = ln.next;
            }
        }
        return res;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2){
        ListNode s1 = l1, s2 = l2;
        ListNode res = null, s0 = null;
        int left = 0;
        boolean f = true;
        while (s1 != null || s2 != null){
            int v1 = 0, v2 = 0;
            if (s1 != null) v1 = s1.val;
            if (s2 != null) v2 = s2.val;
            int sum = v1 + v2 + left;
            int a = sum % 10;
            left = sum / 10;
            if (f){
                s0 = res = new ListNode(a);
                f = false;
            }else{
                s0.next = new ListNode(a);
                s0 = s0.next;
            }
            if (s1 != null) s1 = s1.next;
            if (s2 != null) s2 = s2.next;
        }
        if (left != 0){
            s0.next = new ListNode(left);
        }
        return res;
    }

    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int length = 0;
        for (int i = 0; i < chars.length; i++) {
            sb.append(chars[i]);
            int len = 1;
            for (int j = i+1; j < chars.length; j++) {
                if (sb.toString().contains(String.valueOf(chars[j]))){
                    if (len > length) length = len;
                    sb.delete(0, sb.length());
                    break;
                }else{
                    sb.append(chars[j]);
                    len += 1;
                }
            }
            if (sb.length() > 0){
                if (len > length) length = len;
                break;
            }
        }
        return length;
    }

    public static int[] mergeSort(int[] nums, int l, int h) {
        if (l == h)
            return new int[] { nums[l] };

        int mid = l + (h - l) / 2;
        int[] leftArr = mergeSort(nums, l, mid); //左有序数组
        int[] rightArr = mergeSort(nums, mid + 1, h); //右有序数组
        int[] newNum = new int[leftArr.length + rightArr.length]; //新有序数组

        int m = 0, i = 0, j = 0;
        while (i < leftArr.length && j < rightArr.length) {
            newNum[m++] = leftArr[i] < rightArr[j] ? leftArr[i++] : rightArr[j++];
        }
        while (i < leftArr.length)
            newNum[m++] = leftArr[i++];
        while (j < rightArr.length)
            newNum[m++] = rightArr[j++];
        return newNum;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        int[] mArr = new int[len];

        int m = 0, i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            mArr[m++] = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
        }
        while (i < nums1.length)
            mArr[m++] = nums1[i++];
        while (j < nums2.length)
            mArr[m++] = nums2[j++];

//        System.out.println(new Gson().toJson(mArr));
        double res;
        int mid = len / 2;
        if (len % 2 == 0){
            res = (mArr[mid - 1] + mArr[mid]) * 1.0 / 2;
        }else{
            res = mArr[mid] * 1.0;
        }
        return res;
    }

    public String longestPalindrome(String s) {
        if (s.length() == 0) return s;
        char[] chars = s.toCharArray();
        int len = 0;
        int start = 0, end = 0;
        for (int i = 0; i < chars.length; i++) {
            for (int j = chars.length - 1; j >=0 ; j--) {
                boolean f = true;
                boolean in = false;
                if (chars[i] == chars[j]){
                    in = true;
                    int t1 = i+1, t2 = j-1;
                    while (t1 <= t2){
                        if (chars[t1++] != chars[t2--]){
                            f = false;
                            break;
                        }
                    }
                }
                if (in && f){
                    int l = j - i + 1;
                    if (l > len){
                        len = l;
                        start = i;
                        end = j;
                    }
                    break;
                }
            }

        }
        return s.substring(start, end+1);
    }

    public int reverse(int x) {
        int res;
        String s,rs;
        if (x > 0){
            s = String.valueOf(x);
        }else{
            s = String.valueOf(-x);
        }
        rs = new StringBuilder(s).reverse().toString();
        try {
            res = Integer.parseInt(rs);
        }catch (Exception e){
            res = 0;
        }
        if (x < 0){
            res = -res;
        }
        return res;
    }

    public boolean isMatch1(String s, String p) {
        char[] src = s.toCharArray();
        char[] pat = p.toCharArray();
        int j = 0;
        int m = 0;
        boolean star = false;
        for (int i = 0; i < src.length; i++) {
            for (; j < pat.length; ) {
                if (src[i] == pat[j]){
                    m += 1;
                    j += 1;
                    if (j < pat.length){
                        if (pat[j] == '*'){
                            char dst = src[i];
                            for (; i < src.length; i++) {
                                if (src[i] == dst){
                                    m++;
                                }else {
                                    i--;//回退
                                    break;
                                }
                            }
                            m -=1;
                            j++;
                        }
                    }
                    break;
                }else if (pat[j] == '.'){
                    m += 1;
                    j += 1;
                    if (j < pat.length){
                        if (pat[j] == '*'){
                            star = true;
                            j += 1;
                        }
                    }
                    break;
                } else if (star){
                    break;
                } else{
                    j += 1;
                    if (j < pat.length){
                        if (pat[j] != '*'){
                            return false;
                        }else{
                            j++;
                        }
                    }else{
                        return false;
                    }
                }
            }
            if (i == src.length - 1) break;
        }
        return true;

    }
    public static String src, pat;
    public static int sLen, pLen;
    public static Boolean[][] dp;

    public boolean isMatch(String s, String p){
        src = s;
        sLen = s.length();
        pLen = p.length();
        pat = p;
        dp = new Boolean[sLen + 1][pLen + 1];
        return fuc(0, 0);
    }

    public boolean fuc(int m, int n){
        if (dp[m][n] != null) return dp[m][n];
        if (m > sLen - 1 && n > pLen - 1) return dp[m][n] = true;
        if (m <= sLen - 1 && n > pLen - 1) return dp[m][n] = false;
        if (m > sLen - 1){
            if (pLen - n < 2) return dp[m][n] = false;
            if (pat.charAt(n + 1) != '*') return dp[m][n] = false;
            return fuc(m, n + 2);
        }else{
            if (pat.charAt(n) == '.'){
                if (pLen - n < 2){
                    return dp[m][n] = fuc(m + 1, n + 1);
                }else{
                    if (pat.charAt(n + 1) == '*'){
                        for (int i = m; i <= sLen; i++) {
                            if (fuc(i, n + 2)) return dp[m][n] = true;
                        }
                    }else {
                        return dp[m][n] = fuc(m + 1, n + 1);
                    }
                }
            }else if (src.charAt(m) == pat.charAt(n)){
                if (pLen - n < 2){
                    return dp[m][n] = fuc(m + 1, n + 1);
                }else {
                    if (pat.charAt(n + 1) == '*'){
                        char c = src.charAt(m);
                        int t = sLen;
                        for (int i = m; i < sLen; i++) {
                            if (src.charAt(i) != c){
                                t = i;
                                break;
                            }
                        }
                        for (int i = m; i <= t; i++) {
                            if (fuc(i, n + 2)) return dp[m][n] = true;
                        }
                    }else {
                        return dp[m][n] = fuc(m + 1, n + 1);
                    }
                }
            }else{
                if (pLen - n < 2) return dp[m][n] = false;
                if (pat.charAt(n + 1) != '*') return dp[m][n] = false;
                return dp[m][n] = fuc(m, n + 2);
            }
        }
        return dp[m][n] = false;
    }

    public String convert(String s, int numRows) {
        StringBuilder[] sb = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sb[i] = new StringBuilder();
        }
        char[] chars = s.toCharArray();
        int length = chars.length;
        int index = 0;
        while (index < length){
            for (int i = 0; i < numRows; i++) {
                if (index >= length) break;
                sb[i].append(chars[index++]);
            }
            for (int j = numRows - 2; j > 0 ; j--){
                if (index >= length) break;
                sb[j].append(chars[index++]);
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            res.append(sb[i]);
        }
        return res.toString();
    }

    public String convert1(String s, int numRows) {
        int length = s.length();
        int row = length / numRows + 1;
        if (numRows > 2){
            row =  (length / (numRows + numRows - 2) + 1) * (numRows - 1);
        }
        Character[][] chars = new Character[row][numRows+1];
        int i = 0, j, index = 0;
        while (index < length) {
            for (j = 0; j < numRows && index < length; j++) {
                chars[i][j] = s.charAt(index++);
            }
            if (numRows - 2 > 0){
                j = numRows - 1;
                for (int k = 0; k < numRows - 2 && index < length; k++){
                    i ++;
                    j --;
                    chars[i][j] = s.charAt(index++);
                }
            }
            i++;
        }
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < numRows; k++) {
            for (int l = 0; l < row; l++) {
                if (chars[l][k] != null) sb.append(chars[l][k]);
            }
        }
        return sb.toString();
    }

    public int myAtoi(String str){
        char[] chars = str.toCharArray();
        int length = chars.length;
        long num = 0;
        int state = 1, opr = 1;
        boolean f = false;
        for (int index = 0; index <= length; index++){
            char ch = ' ';
            if (index < length){
                ch = chars[index];
            }else {
                state = 4;
            }
            switch (state){
                case 1:
                    if (ch == '+' || ch == '-'){
                        opr = ch == '-' ? -1 : 1;
                        state = 2;
                    }else if (ch >= '0' && ch <= '9'){
                        num = num * 10 + (ch - '0');
                        state = 3;
                    }else if (ch == ' '){
                        state = 1;
                    }else {
                        state = 4;
                    }
                    break;
                case 2:
                case 3:
                    if (ch >= '0' && ch <= '9'){
                        num = num * 10 + (ch - '0');
                        long tmp = num * opr;
                        if (tmp > Integer.MAX_VALUE || tmp < Integer.MIN_VALUE){
                            state = 4;
                        }else {
                            state = 3;
                        }
                    }else {
                        state = 4;
                    }
                    break;
                case 4:
                    num = opr * num;
                    if (num > Integer.MAX_VALUE) num = Integer.MAX_VALUE;
                    if (num < Integer.MIN_VALUE) num = Integer.MIN_VALUE;
                    f = true;
                    break;
            }
            if (f) break;
        }

        return (int) num;
    }

    public int myAtoi1(String str) {
        if (str.length() <= 0) return 0;
        String replace = str.trim();
        if (replace.length() <= 0) return 0;
        char c = replace.charAt(0);
        boolean f = false;
        if ((c != '-' && c != '+') && (c < '0' || c > '9' )){
            return 0;
        }else {
            if (c == '+' || c == '-'){
                if (c == '-') f = true;
                if (replace.length() < 2) return 0;
                else{
                    char c1 = replace.charAt(1);
                    if (!Character.isDigit(c1)) return 0;
                }
            }
        }
        long res;
        String[] sp = replace.split("\\.");
        String sss = sp[0];
        String s1 = sss.replaceAll("(\\+|\\-)", " ").replaceAll("[a-zA-z]", " ");
        String[] split = s1.split(" ");
        String endStr = null;
        for (String s : split) {
            if (s.trim().length() > 0){
                endStr = s;
                break;
            }
        }
        if (endStr == null) return 0;
        try {
            String s = endStr.replaceAll("^(\\+|\\-)?(0+)", "").replaceAll("\\-", "");
            if (f) s = "-" + s;
            int e = s.length() > 18 ? 18 : s.length();
            res = Long.parseLong(s.substring(0, e));
            if (res > Integer.MAX_VALUE) res = Integer.MAX_VALUE;
            if (res < Integer.MIN_VALUE) res = Integer.MIN_VALUE;
        }catch (NumberFormatException e){
            res = 0;
        }catch (Exception e){
            res = 0;
        }
        return (int)res;
    }
}