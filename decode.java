import java.util.Scanner;
import java.util.Stack;

 class DecodeString {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();
        String decodedString = decode(input);
        System.out.println("Decoded String: " + decodedString);
    }

    public static String decode(String s) {
        Stack<String> strStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        StringBuilder result = new StringBuilder();
        int num = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (c == '[') {
                numStack.push(num);
                strStack.push(result.toString());
                num = 0;
                result = new StringBuilder();
            } else if (c == ']') {
                int repeatTimes = numStack.pop();
                StringBuilder repeatedStr = new StringBuilder();
                for (int i = 0; i < repeatTimes; i++) {
                    repeatedStr.append(result);
                }
                result = new StringBuilder(strStack.pop() + repeatedStr);
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}
