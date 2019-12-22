import com.google.gson.Gson;

import java.util.*;

public class Day3 {
    public static void main(String[] args) {
        Solution3 s = new Solution3();
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        ListNode listNode = s.mergeKLists(new ListNode[]{l1, l2});
        System.out.println(new Gson().toJson(listNode));
//        ListNode listNode = s.swapPairs(l1);
//        System.out.println(new Gson().toJson(listNode));
//        ListNode listNode1 = s.revList2(l1);
//        System.out.println(new Gson().toJson(listNode1));
//        List<String> strings = s.generateParenthesis(10);
//
//        System.out.println(new Gson().toJson(strings));
//        ListNode listNode = s.reverseKGroup(l1, 2);
//        System.out.println(new Gson().toJson(listNode));
//        int i = s.removeElement(new int[]{1, 2, 2, 2, 3, 3, 4}, 2);
//        System.out.println(i);
//        int i = s.strStr("hello", "ll");
//        System.out.println(i);
    }
}

class Solution3 {


    private static int num_;
    private static List<String> lst_;
    public List<String> generateParenthesis(int n) {
        num_ = n;
        lst_ = new ArrayList<String>();
        if ( n <= 0) return lst_;
        fuc(new char[n << 1],0, 0, 0);
        return lst_;
    }

    private void fuc(char[] chars, int left, int right, int d){
        if (left < right || left > num_) return;
        if (d == num_ << 1) {
            lst_.add(new String(chars));
            return;
        }
        chars[d] = '(';
        fuc(chars, left + 1, right, d + 1);
        chars[d] = ')';
        fuc(chars, left, right + 1, d + 1);
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length == 0) return null;
        return mergeSort(lists, 0, lists.length - 1);
    }

    public ListNode mergeKLists(ListNode[] lists){
        Queue<ListNode> queue= new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.val < o2.val) return -1;
                else if (o1.val == o2.val) return 0;
                else return 1;
            }
        });
        ListNode head = new ListNode(0);
        ListNode p = head;
        for (ListNode list : lists) {
            if (list != null) queue.add(list);
        }
        while (queue.size() > 0){
            p.next = queue.poll();
            p = p.next;
            if (p.next != null) queue.add(p.next);
        }
        return head.next;
    }

    public ListNode mergeSort(ListNode[] lists, int left, int right){
        if (left == right) return lists[left];
        int mid = left + ((right - left) >> 1);
        ListNode listNode1 = mergeSort(lists, left, mid);
        ListNode listNode2 = mergeSort(lists, mid + 1, right);
        return mergeTwoLists(listNode1, listNode2);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        ListNode lnd  = new ListNode(0);
        ListNode head = lnd;
        while (l1 != null && l2 != null){
            if (l1.val > l2.val){
                lnd.next = l2;
                l2 = l2.next;
            }else {
                lnd.next = l1;
                l1 = l1.next;
            }
            lnd = lnd.next;
        }
        if (l1 != null){
            lnd.next = l1;
        }
        if (l2 != null){
            lnd.next = l2;
        }
        return head.next;
    }

    public ListNode swapPairs(ListNode head) {
        ListNode t = null, h = new ListNode(0);
        h.next = head;
        ListNode f = h;
        while (head != null && head.next != null){
            t = head.next.next;
            head.next.next = head;
            f.next = head.next;
            head.next = t;
            f = head;
            head = t;
        }
        return h.next;
    }

    public ListNode revList(ListNode head){
        if (head == null || head.next == null) return head;
        ListNode t = revList(head.next);
        head.next.next = head;
        head.next = null;
        return t;
    }

    public ListNode revList2(ListNode head){
        if (head == null || head.next == null) return head;
        ListNode p = head, c = head.next, t = c.next;
        while (c != null){
            t = c.next;
            c.next = p;
            p = c;
            c = t;
        }
        head.next = null;
        return p;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1) return head;
        ListNode p = head, c = head.next, t, h = head, cln = head;
        ListNode father = new ListNode(0);
        father.next = head;
        boolean f = true;
        int count = 0;
        while (cln != null){
            cln = cln.next;
            count ++;
        }
        while (true){
            if (count >=k ){
                int cc = k;
                while (c != null && cc > 1){
                    t = c.next;
                    c.next = p;
                    p = c;
                    c = t;
                    cc--;
                }
                count -=k;
            }
            if (f){
                h = p;
                f = false;
            }
            father.next = p;
            head.next = c;
            father = head;
            head = p = c;
            if (c != null && c.next != null) c = c.next;
            else break;
        }
        return h;
    }

    public int removeElement(int[] nums, int val) {
        int m = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                if (m < i){
                    nums[m] = nums[i];
                }
                m++;
            }
        }
        System.out.println(new Gson().toJson(nums));
        return m;
    }

    public int strStr(String haystack, String needle) {
        int len1 = haystack.length();
        int len2 = needle.length();
        if (len1 < len2) return -1;
        if (len2 == 0) return 0;
        int index = -1;
        for (int i = 0; i <= len1 - len2; i++) {
            int c = 0;
            if (haystack.charAt(i) == needle.charAt(0)){
                c += 1;
                for (int j = 1; j < len2; j++) {
                    if (haystack.charAt(i  + j) == needle.charAt(j)){
                        c += 1;
                    }else {
                        break;
                    }
                }
            }
            if (c == len2){
                index = i;
                break;
            }
        }
        return index;
    }

    public int searchInsert(int[] nums, int target) {
        int i = Arrays.binarySearch(nums, target);
        if (i < 0) i = - (i + 1);
        return i;
    }
}
