package vahak;

import java.util.Stack;

public class BalancingBracket {
    public static void main(String[] args) {
        new BalancingBracket().process();
    }

    private void process() {
        System.out.println(isBalancedBrackets("{[()]}") ? "YES" : "NO");
        System.out.println(isBalancedBrackets("{[(])}") ? "YES" : "NO");
    }

    private boolean isBalancedBrackets(String brackets) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < brackets.length(); i++) {
            char character = brackets.charAt(i);
            if (character == '{' || character == '[' || character == '(') {
                stack.push(character);
                continue;
            }
            if (stack.empty()) {
                return false;
            }
            Character peek = stack.peek();
            switch (character) {
                case ']':
                    if (peek != '[') {
                        return false;
                    }
                    stack.pop();
                    break;
                case '}':
                    if (peek != '{') {
                        return false;
                    }
                    stack.pop();
                    break;
                case ')':
                    if (peek != '(') {
                        return false;
                    }
                    stack.pop();
                    break;
                default:
                    return false;
            }
        }
        return stack.empty();
    }
}
