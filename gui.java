import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 class LoanCalculatorFrame extends Frame {

    private TextField loanAmountField, interestRateField, loanTermField, resultField;
    private double monthlyPayment; // Declare as an instance variable

    public LoanCalculatorFrame() {
        setTitle("Loan Calculator");
        setSize(400, 200);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        setLayout(new FlowLayout());

        loanAmountField = new TextField(10);
        interestRateField = new TextField(10);
        loanTermField = new TextField(10);
        resultField = new TextField(10);
        resultField.setEditable(false);

        add(new Label("Loan Amount:"));
        add(loanAmountField);

        add(new Label("Annual Interest Rate (%):"));
        add(interestRateField);

        add(new Label("Loan Term (years):"));
        add(loanTermField);

        Button calculateButton = new Button("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateLoan();
                // Print monthly payment to console
               
            }
        });

        add(calculateButton);

        add(new Label("Monthly Payment:"));
        add(resultField);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    private void calculateLoan() {
    try {
        double loanAmount = Double.parseDouble(loanAmountField.getText());
        double annualInterestRate = Double.parseDouble(interestRateField.getText());
        int loanTermInYears = Integer.parseInt(loanTermField.getText());

        double monthlyInterestRate = annualInterestRate / 100 / 12;
        int numberOfPayments = loanTermInYears * 12;

        if (1 + monthlyInterestRate != 1) {
            monthlyPayment =
                    (loanAmount * monthlyInterestRate) /
                            (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));
        } else {
            // Handle the case where the denominator is zero (prevent division by zero)
            monthlyPayment = Double.POSITIVE_INFINITY;
        }

        resultField.setText(String.format("%.2f", monthlyPayment));

        // Print monthly payment to console
        System.out.println("Monthly Payment: $" + monthlyPayment);
    } catch (NumberFormatException ex) {
        ex.printStackTrace();
        // Handle the exception as needed
    }
}


    public static void main(String[] args) {
        LoanCalculatorFrame loanCalculatorFrame = new LoanCalculatorFrame();
        loanCalculatorFrame.setVisible(true);
    }
}
