package leetcode;

import java.util.Stack;

public class Solutions {
    public static void main(String[] args) {
        new Solutions().process();
    }

    private void process() {
        System.out.println(isValid("{}"));
        System.out.println(isValid("{}()[]"));
        System.out.println(isValid("{]"));
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            else {
                if (stack.empty()) {
                    return false;
                }
                Character peek = stack.peek();
                if ((peek == '(' && c == ')') || (peek == '[' && c == ']') || (peek == '{' && c == '}')) {
                    stack.pop();
                }
                else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
