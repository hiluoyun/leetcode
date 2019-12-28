import com.google.gson.Gson;

import java.util.*;

public class Day6 {
    public static void main(String[] args) {
//        Trie trie = new Trie();
//        trie.insert("apc");
//        trie.insert("apcle");
//        trie.insert("zcc");
//        System.out.println(new Gson().toJson(trie));
//        boolean app = trie.search("apc");
//        System.out.println(app);
//        boolean app1 = trie.startsWith("apc");
//        System.out.println(app1);
//        boolean aaaa = trie.startsWith("aaaa");
//        System.out.println(aaaa);
        Solution6 s = new Solution6();
        int kthLargest = s.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2);
        System.out.println(kthLargest);
    }
}

class Solution6 {
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.offer(num);
            if (queue.size() > k){
                queue.poll();
            }
        }
        return queue.isEmpty() ? -1 : queue.poll();
    }
}

class Trie {
    char ch;
    int nextOrVal = 0;
    boolean isEnd = false;
    List<Trie> next = new ArrayList<>();

    /** Initialize your data structure here. */
    public Trie() {
    }

    public Trie(char ch, boolean isEnd){
        this.ch = ch;
        this.isEnd = isEnd;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word.length() == 0) return;
        Trie t = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int tmp = (1 << (c - 'a')) | t.nextOrVal;
            boolean isEnd = i == word.length() - 1;
            if (tmp > t.nextOrVal){
                Trie trie = new Trie(c, isEnd);
                t.nextOrVal = tmp;
                t.next.add(trie);
                t = trie;
            }else {
                for (int j = 0; j < t.next.size(); j++) {
                    Trie trie1 = t.next.get(j);
                    if (trie1.ch == c){
                        if (isEnd) trie1.isEnd = true;
                        t = trie1;
                        break;
                    }
                }
            }
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word.length() == 0) return false;
        return searchDfs(word, 0, this);
    }

    private boolean searchDfs(String word, int index, Trie root){
        if (index >= word.length() && root.isEnd) return true;
        if (index >= word.length()) return false;
        if (root == null || root.next.size() == 0) return false;
        char ch = word.charAt(index);
        int tag = -1;
        int size = root.next.size();
        for (int i = 0; i < size; i++) {
            if (root.next.get(i).ch == ch){
                tag = i;
                break;
            }
        }
        if (tag >= 0){
            return searchDfs(word, index + 1, root.next.get(tag));
        }
        return false;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix.length() == 0) return false;
        return startWithDfs(prefix, 0, this);
    }

    private boolean startWithDfs(String prefix, int index, Trie root){
        if (index >= prefix.length()) return true;
        if (root == null || root.next.size() == 0) return false;
        char ch = prefix.charAt(index);
        int tag = -1;
        int size = root.next.size();
        for (int i = 0; i < size; i++) {
            if (root.next.get(i).ch == ch){
                tag = i;
                break;
            }
        }
        if (tag >= 0){
           return startWithDfs(prefix, index + 1, root.next.get(tag));
        }
        return false;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
