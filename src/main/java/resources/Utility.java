package resources;

public class Utility {
    private static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for(int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }
    public static ListNode stringToCircularList(String input, int pos) {
        int[] array = stringToIntegerArray(input);

        if(pos < 0) return stringToListNode(input);
        ListNode dummyNode = new ListNode(0);
        ListNode ptr = dummyNode;
        ListNode target = dummyNode;
        int count = 0;
        for(int item: array){
            ptr.next = new ListNode(item);
            if(count == pos)
                target = ptr.next;
            ptr = ptr.next;
            ++count;
        }
        ptr.next = target;
        ptr = dummyNode.next;
        dummyNode = null;
        return ptr;
    }

    ListNode reverseList(ListNode head) {
        ListNode currentPtr = head;
        ListNode q = null;
        ListNode r = null;

        while(currentPtr!=null){
            r = q;
            q = currentPtr;
            currentPtr = currentPtr.next;
            q.next = r;
        }
        head = q;
        return head;
    }

    // excerpted from LeetCode
    public static String listNodeToString(ListNode node) {
        StringBuilder out = new StringBuilder();
        String token = "";
        out.append("[");
        while (node != null && node.next != null) {
            out.append(token).append(node.val);
            token = ",";
            node = node.next;
        }

        if (node != null) {
            out.append(node.val);
        }
        out.append("]");
        return out.toString();
    }

}

