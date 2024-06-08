public class NumberPattern {
    public static void main(String[] args) {
        printPattern();
    }

    static void printPattern() {
        int counter = 1;

        // Outer loop for rows
        for (int i = 1; i <= 4; i++) {
            // Inner loop for columns
            for (int j = 1; j <= i; j++) {
                System.out.print(counter + " ");
                counter += 4; // Increment by 4 for the next number
            }
            System.out.println(); // Move to the next line after each row
        }
    }
}
