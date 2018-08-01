public class MergeKSortedList {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {val = x; }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        ListNode root = new ListNode(Integer.MIN_VALUE);
        root.next = lists[0];
        for (int i = 1; i < lists.length; i++) {
            if (lists[i] == null) continue;
            ListNode cursor = root.next;
            ListNode prevCursor = root;
            ListNode walker = lists[i];
            ListNode nextWalker = walker.next;
            do {
                if (walker == null) break;
                if (cursor == null || walker.val <= cursor.val) {
                    prevCursor.next = walker;
                    walker.next = cursor;
                    prevCursor = walker;
                } else {
                    while (cursor != null && cursor.val <= walker.val) {
                        cursor = cursor.next;
                        prevCursor = prevCursor.next;
                    }
                    nextWalker = walker.next;
                    walker.next = cursor;
                    prevCursor.next = walker;
                    prevCursor = prevCursor.next;
                }
                walker = nextWalker;
                nextWalker = walker == null?null:walker.next;

            } while (true);

        }
        return root.next;
    }

    //@Test
    public void testMerge() {
        ListNode a1 = new ListNode(2);
        ListNode a2 = new ListNode(4); a1.next = a2;
        ListNode a3 = new ListNode(5); a2.next = a3;
        ListNode a4 = new ListNode(7); a3.next = a4;
        ListNode a5 = new ListNode(8); a4.next = a5;

        ListNode b1 = new ListNode(1);
        ListNode b2 = new ListNode(2); b1.next = b2;
        ListNode b3 = new ListNode(4); b2.next = b3;
        ListNode b4 = new ListNode(7); b3.next = b4;
        ListNode b5 = new ListNode(9); b4.next = b5;

        ListNode c1 = new ListNode(3);
        ListNode c2 = new ListNode(5); c1.next = c2;
        ListNode c3 = new ListNode(5); c2.next = c3;
        ListNode c4 = new ListNode(6); c3.next = c4;
        ListNode c5 = new ListNode(9); c4.next = c5;

        ListNode[] param = {a1, b1, c1};
        ListNode node = mergeKLists(param);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }

    }

   // @Test
    public void testMerge2() {
        ListNode a1 = null;
        ListNode b1 = new ListNode(2);
        ListNode[] param = {a1, b1};
        ListNode result = mergeKLists(param);
        while (result!=null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
