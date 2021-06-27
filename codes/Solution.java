package codes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    // head node of linked list
    //  1  -> 2 -> 3 -> 4 -> 5


    //  Node
    // Delete a node from a linked list -> linked list, node

    Node head;

    public static void main(String[] args) {
        Node node = new Node(3);
        new Solution().process(node);
    }

    private void process(Node node) {
        char[] chars = new char[4];
        String a = Arrays.toString(chars);

        // 3
        //
        Node temp = node.next;
        node.val = temp.val;
        node.next = temp.next;
        temp=null;

        /*Node temp = head;
        Node prev = null;
        if (temp != null && temp.val == node.val) {
            head = temp.next;
            return;
        }
        while (temp != null && temp.val != node.val) {
            prev = temp;
            temp = temp.next;
        }
        if (temp == null) {
            return;
        }
        prev.next = temp.next;*/
    }

    private static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    private List<String> ans;

    public void parenthesisUtils(char[] str, int pos, int n, int open, int close) {
        if (close == n) {
            String a = Arrays.toString(str);
            ans.add(a);
        }
        else {
            if (open > close) {
                str[pos] = ')';
                parenthesisUtils(str, pos + 1, n, open, close + 1);
            }
            if (open < n) {
                str[pos] = '(';
                parenthesisUtils(str, pos + 1, n, open + 1, close);
            }
        }
    }

    private void produceParenthesis(char[] str, int n) {
        if (n > 0) {
            parenthesisUtils(str, 0, n, 0, 0);
        }
    }

    public List<String> generateParenthesis(int n) {
        ans = new ArrayList<>();
        char[] str = new char[2 * n];
        produceParenthesis(str, n);
        return ans;
    }
}
