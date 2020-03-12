import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author: mosaic on 2020/1/14 >_< .
 */
public class Day7 {
    public static void main(String[] args) {
        Solution7 s = new Solution7();
//        int i = s.maxSubArray(new int[]{3,2,-3,-1,1,-3,1,-1});
//        System.out.println(i);
//        List<List<String>> lists = s.solveNQueens(4);
//        System.out.println(new Gson().toJson(lists));
        int i = s.totalNQueens(4);
        System.out.println(i);
    }
}

class Solution7 {
    public int maxSubArray(int[] nums) {
        if(nums.length == 0) return 0;
        int max = nums[0];
        int sum = max;
        for (int i = 1; i < nums.length; i++) {
            int t = nums[i];
            int tm = sum;
            if (sum > 0){
                sum += t;
            }else {
                sum = t;
            }

            if (t > 0){
                if (tm >= 0){
                    if (tm + t > max) max = tm + t;
                }else {
                    if (t > max) max = t;
                }
            }else {
                if (t > max) max = t;
            }
        }
        return max;
    }


    private static int row = 0;
    private static int column = 0;
    private static int line1 = 0, line2 = 0;
    private static int num = 0;
    private static int[] flag;
    char[] chArr = null;
    List<List<String>> res = null;
    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>(n);
        row = 0; column = 0; line1 = 0; line2 = 0; num = n;
        flag = new int[n];
        chArr = new char[n];
        Arrays.fill(chArr, '.');
        dpFunc(1, 1, 0);
        return res;
    }

    private  boolean dpFunc(int m, int n, int count){
        if (count >= num){
            StringBuilder[] sb = new StringBuilder[num];
            ArrayList<String> strings = new ArrayList<>(num);
            for (int i = 0; i < num; i++) {
                sb[i] = new StringBuilder(new String(chArr));
                sb[i].setCharAt(flag[i] - 1, 'Q');
                strings.add(sb[i].toString());
            }
            res.add(strings);
            return true;
        }
        if (count < m - 1) return false;
        if (m > num || n > num) return false;
        int ttr = row, ttc = column, ttl1 = line1, ttl2 = line2;
        int tr = row | (1 << m - 1);
        int tc = column | (1 << n - 1);
        int tl1 = line1 | (1 << (n - m + num - 1));
        int tl2 = line2 | (1 << (m + n - 2));
        if (tr > row && tc > column && tl1 > line1 && tl2 > line2){
            row = tr; column = tc; line1 = tl1; line2 = tl2;
            flag[m - 1] = n;
            dpFunc(m + 1, 1, count + 1);
            row = ttr; column = ttc; line1 = ttl1; line2 = ttl2;
            flag[m - 1] = 0;
        }
        dpFunc(m, n + 1, count);
        return false;
    }

    private static int totalQ = 0;
    public int totalNQueens(int n) {
        row = 0; column = 0; line1 = 0; line2 = 0; num = n; totalQ = 0;
        search(1, 1, 0);
        return totalQ;
    }

    private void search(int m, int n, int count){
        if (count >= num){
            totalQ += 1;
            return;
        }
        if (count < m - 1) return;
        if (m > num || n > num) return;
        int ttr = row, ttc = column, ttl1 = line1, ttl2 = line2;
        int tr = row | (1 << m - 1);
        int tc = column | (1 << n - 1);
        int tl1 = line1 | (1 << (n - m + num - 1));
        int tl2 = line2 | (1 << (m + n - 2));
        if (tr > row && tc > column && tl1 > line1 && tl2 > line2){
            row = tr; column = tc; line1 = tl1; line2 = tl2;
            search(m + 1, 1, count + 1);
            row = ttr; column = ttc; line1 = ttl1; line2 = ttl2;
        }
        search(m, n + 1, count);
    }
}
