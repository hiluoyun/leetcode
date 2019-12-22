import com.google.gson.Gson;

import java.util.*;

public class Day4 {
    public static void main(String[] args) {
        Solution4 s = new Solution4();
//        List<Integer> barfoothefoobarman = s.findSubstring("a", new String[]{"a"});
//        System.out.println(new Gson().toJson(barfoothefoobarman));
//        System.out.println("aaa".indexOf("aa", 1));
//        int[][] arr = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
//        s.rotate(arr);
//        System.out.println(new Gson().toJson(arr));
//        int[] search = s.searchRange(new int[]{1,8,8, 8, 8,9}, 8);
//        System.out.println(new Gson().toJson(search));

//        String s1 = s.countAndSay(5);
//        System.out.println(s1);
        List<List<Integer>> lists = s.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8);
        System.out.println(new Gson().toJson(lists));
    }
}

class Solution4 {

    Set<List<Integer>> resSet = null;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> list = new ArrayList<>();
        resSet = new HashSet<>();
        fuc(candidates, target, 0, list, 0);
        return new ArrayList<>(resSet);
    }

    private void fuc(int[] candidates, int target, int sum, List<Integer> list, int index){
        if (sum > target) return;
        if (sum == target) {
            List<Integer> dst = new ArrayList<>(list);
            Collections.sort(dst);
            resSet.add(dst);
            return;
        }
        for (int i = 0; i < candidates.length; i++) {
            list.add(index, candidates[i]);
            fuc(candidates, target, sum + candidates[i], list, index + 1);
            list.remove(index);
        }
    }

    Set<List<Integer>> resSet2 = null;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> list = new ArrayList<>();
        resSet2 = new HashSet<>();
        fuc2(candidates, target, 0, list, 0, -1);
        return new ArrayList<>(resSet2);
    }

    private void fuc2(int[] candidates, int target, int sum, List<Integer> list, int index, int cur){
        if (sum > target) return;
        if (sum == target) {
            List<Integer> dst = new ArrayList<>(list);
            Collections.sort(dst);
            resSet2.add(dst);
            return;
        }
        for (int i = cur + 1; i < candidates.length; i++) {
            list.add(index, candidates[i]);
            fuc2(candidates, target, sum + candidates[i], list, index + 1, i);
            list.remove(index);
        }
    }

    private static Set<String> arrangeList = null;
    public List<Integer> findSubstring1(String s, String[] words) {
        arrangeList = new HashSet<String>();
        int length = s.length();
        if (s == null || length == 0 || words.length == 0) return Collections.emptyList();
        Set<Integer> res = new HashSet<Integer>();
        arrange(words, 0, words.length);
        for (String s1 : arrangeList) {
            int i = s.indexOf(s1);
            while (i >= 0 && i <= length - s1.length()){
                res.add(i);
                i = s.indexOf(s1, i + 1);
            }
        }
        return new ArrayList<Integer>(res);
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words.length == 0) return res;
        Map<String, Integer> compareMap = new HashMap<>();
        for (String word : words) {
            if (compareMap.containsKey(word)){
                compareMap.put(word, compareMap.get(word) + 1);
            }else {
                compareMap.put(word, 1);
            }
        }
        int len = words[0].length();
        for (int i = 0; i <= s.length() - len * words.length; i++) {
            int num = 0;
            Map<String, Integer> dstMap = new HashMap<>();
            while (num < words.length){
                String substring = s.substring(i + num * len, i + len * num + len);
                Integer count = dstMap.getOrDefault(substring, 0);
                dstMap.put(substring, count + 1);
                if (count + 1 > compareMap.getOrDefault(substring, 0)){
                    break;
                }
                num ++;
            }
            if (num == words.length){
                res.add(i);
            }
        }
        return res;
    }

    private void arrange(String[] words, int index, int length){

        if(index >= length-1)
        {
            StringBuilder sb = new StringBuilder();
            for (String word : words) {
                sb.append(word);
            }
            arrangeList.add(sb.toString());
        }
        else
        {
            for(int i = index;i<length;i++)
            {
                swap(words,index,i);
                arrange(words,index+1,length);//递归
                swap(words,index,i);
            }
        }
    }

    private void swap(String[] words, int m, int n){
        if (m == n) return;
        String t = words[m];
        words[m] = words[n];
        words[n] = t;
    }

    public void Test(){
        String str[] = { "A", "B", "C", "D", "E" };

        int nCnt = str.length;

        int nBit = (0xFFFFFFFF >>> (32 - nCnt));

        for (int i = 1; i <= nBit; i++) {
            for (int j = 0; j < nCnt; j++) {
                if ((i << (31 - j)) >> 31 == -1) {
                    System.out.print(str[j]);
                }
            }
            System.out.println("");
        }
    }

    public void rotate(int[][] matrix) {
        int length = matrix.length;
        int len = length - 1;
        for (int i = 0; i <= len / 2; i++) {
            for (int j = i; j < len - i; j++) {
                int[] t = new int[3];
                int x = i, y = j;
                for (int k = 0; k < 4; k++) {
                    int s = 0;
                    if (k == 0){
                        s = x + len > len ? len - x : x + len;
                        t[k] = matrix[y][s];
                        matrix[y][s] = matrix[i][j];
                    }else if (k == 1){
                        s = x + len > len ? len - x : x + len;
                        t[k] = matrix[y][s];
                        matrix[y][s] = t[k-1];
                    } else if (k == 2){
                        s = Math.abs(x - len);
                        t[k] = matrix[y][s];
                        matrix[y][s] = t[k - 1];
                    } else {
                        s = Math.abs(len - x);
                        matrix[y][s] = t[k - 1];
                    }
                    x = y;
                    y = s;
                }
                matrix[i][j] = t[2];

            }
        }
    }

    public void nextPermutation(int[] nums) {
        int len = nums.length;
        if (len == 0) return;
        for (int i = len - 1; i >= 0; i--) {
            if (i == 0) {
                Arrays.sort(nums);
                return;
            } else {
                if (nums[i] > nums[i - 1]) {
                    Arrays.sort(nums, i, len);
                    for (int j = i; j < len; j++) {
                        if (nums[j] > nums[i - 1]) {
                            int temp = nums[j];
                            nums[j] = nums[i - 1];
                            nums[i - 1] = temp;
                            return;
                        }
                    }
                }
            }
        }
    }

    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int p = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i+1]){
                p = i;
                break;
            }
        }
        int res = Arrays.binarySearch(nums, 0, p + 1, target);
        if (res < 0){
            int i = - (res + 1);
            if (i == 0){
                int m = Arrays.binarySearch(nums, p + 1, nums.length, target);
                if (m >= 0) res = m;
            }
        }
        return res < 0 ? -1 : res;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums.length == 0) return res;
        int i = Arrays.binarySearch(nums, target);
        int p1 = i, p2 = i;
        if (i >= 0){
            int low = i, high = i;
            while (low > 0){
                if(nums[--low] == target) p1 = low;
                else break;
            }
            while (high < nums.length - 1){
                if (nums[++high] == target)p2 = high;
                else break;
            }
        }else {
            p1 = -1; p2 = -1;
        }
        return new int[]{p1, p2};
    }

    public boolean isValidSudoku(char[][] board) {
        int[] row = new int[9];
        int[] column = new int[9];
        int[] gird = new int[9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.'){
                    //row
                    int tRow = row[i] ^ (1 << (board[i][j] - 1));
                    if (tRow > row[i]){
                        row[i] = tRow;
                    }else {
                        return false;
                    }
                    //column
                    int tColumn = column[j] ^ (1 << (board[i][j] - 1));
                    if (tColumn > column[j]){
                        column[j] = tColumn;
                    }else {
                        return false;
                    }
                    //gird
                    int index = i / 3 * 3 + j / 3;
                    int tGird = gird[index] ^ (1 << (board[i][j] - 1));
                    if (tGird > gird[index]) gird[index] = tGird;
                    else return false;
                }
            }
        }
        return true;
    }

    private static String[] strs = new String[30];
    public String countAndSay(int n) {
        if (strs[n] != null) return strs[n];
        if (n == 1) return strs[n] = "1";
        String str = countAndSay(n - 1);
        char[] chars = str.toCharArray();

        char ch = chars[0];
        int num = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == ch){
                num += 1;
            }else {
                sb.append(num).append(ch);
                ch = chars[i];
                num = 1;
            }
        }
        sb.append(num).append(ch);
         return strs[n] = sb.toString();
    }

}