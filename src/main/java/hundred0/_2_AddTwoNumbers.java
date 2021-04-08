package hundred0;

public class _2_AddTwoNumbers {

    /**
     * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in
     * reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
     *
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     *
     * Example:
     *
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     * Explanation: 342 + 465 = 807.
     */

     public static class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
     }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode cursor = root;
        int inc = 0;
        while (true) {
            int sum = 0;
            if (l1 !=null || l2 !=null) {
                sum = (l1!=null?l1.val:0) + (l2!=null?l2.val:0) + inc;
                ListNode node = new ListNode(sum%10);
                cursor.next = node;
                cursor = node;
                inc = sum/10;
                l1 = l1==null?null:l1.next;
                l2 = l2==null?null:l2.next;
                continue;
            } else if (l1 == null && l2 == null) {
                if (inc>0) {
                    ListNode node = new ListNode(inc);
                    cursor.next = node;
                }
                break;
            }
        }
        return root.next;
    }

    public static void main(String[] args) {
        ListNode a1 = new ListNode(2);
        ListNode a2 = new ListNode(4);  a1.next = a2;
        ListNode a3 = new ListNode(3);  a2.next = a3;

        ListNode b1 = new ListNode(5);
        ListNode b2 = new ListNode(6); b1.next = b2;
        ListNode b3 = new ListNode(4); b2.next = b3;

        addTwoNumbers(a1, b1);
    }
}
