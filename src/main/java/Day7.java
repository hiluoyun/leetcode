import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Day7 {
    public static void main(String[] args) {
        Solution7 s = new Solution7();
        int r = s.lengthOfLastWord("Hello World 11");
        System.out.println(r);

    }
}

class Solution7 {
    public int lengthOfLastWord(String s) {
        s = " " + s;
        int max = 0;
        int length = s.length();
        int c = 0;
        for (int i = length - 1; i >= 0; i--) {
            if (s.charAt(i) != ' '){
                c += 1;
            }else {
                if (c != 0){
                    max = c;
                    break;
                }
            }
        }
        return max;
    }
}
