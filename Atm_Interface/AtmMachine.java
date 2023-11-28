import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

class AtmMachine extends JFrame {
    private BankAccount userAccount;

    private JTextField amountField;
    private JTextArea outputArea;

    public AtmMachine(BankAccount account) {
        this.userAccount = account;

        setTitle("ATM Machine");
        setSize(574, 362);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createComponents();
        setVisible(true);
    }

    private void createComponents() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(64, 128, 128));
        panel.setForeground(new Color(64, 128, 128));
        panel.setLayout(null);

        amountField = new JTextField(10);
        amountField.setFont(new Font("Poppins", Font.BOLD, 13));
        JLabel amountLabel = new JLabel("Enter Amount:");
        amountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        amountLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
        amountLabel.setForeground(new Color(255, 255, 255));
        amountLabel.setFont(new Font("Poppins", Font.BOLD, 17));
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(new Font("Poppins", Font.BOLD, 12));
        withdrawButton.setForeground(new Color(64, 128, 128));
        withdrawButton.setBackground(new Color(255, 255, 255));
        JButton depositButton = new JButton("Deposit");
        depositButton.setFont(new Font("Poppins", Font.BOLD, 13));
        depositButton.setForeground(new Color(64, 128, 128));
        depositButton.setBackground(new Color(255, 255, 255));
        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.setFont(new Font("Poppins", Font.BOLD, 12));
        checkBalanceButton.setForeground(new Color(64, 128, 128));
        checkBalanceButton.setBackground(new Color(255, 255, 255));
        outputArea = new JTextArea(10, 30);
        outputArea.setWrapStyleWord(true);
        outputArea.setFont(new Font("Poppins", Font.BOLD, 15));
        outputArea.setForeground(new Color(0, 0, 0));
        outputArea.setBackground(UIManager.getColor("CheckBox.light"));
        outputArea.setEnabled(false);
        outputArea.setEditable(false);
        outputArea.setTabSize(15);

        amountLabel.setBounds(190, 55, 168, 37);
        amountField.setBounds(209, 120, 131, 30);
        withdrawButton.setBounds(46, 120, 116, 30);
        depositButton.setBounds(227, 175, 100, 30);
        checkBalanceButton.setBounds(386, 120, 135, 30);
        outputArea.setBounds(105, 229, 354, 37);

        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(checkBalanceButton);
        panel.add(outputArea);

        withdrawButton.addActionListener(e -> performWithdraw());
        depositButton.addActionListener(e -> performDeposit());
        checkBalanceButton.addActionListener(e -> displayBalance());

        getContentPane().add(panel);
    }


    private void performWithdraw() {
        String amountText = amountField.getText();

        if (amountText.isEmpty()) {
            outputArea.setText("Please enter a valid amount.");
            JOptionPane.showMessageDialog(null, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);
            boolean success = userAccount.withdraw(amount);

            if (success) {
                outputArea.setText("Withdrawal successful. New balance: $" + userAccount.getBalance());
                amountField.setText("");

            } else {
                outputArea.setText("Withdrawal failed. Insufficient funds.");
                JOptionPane.showMessageDialog(null, "Withdrawal failed. Insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);

            }
        } catch (NumberFormatException e) {
            outputArea.setText("Invalid input. Please enter a valid number.");
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void performDeposit() {
        String amountText = amountField.getText();

        if (amountText.isEmpty()) {
            outputArea.setText("Please enter a valid amount.");
            JOptionPane.showMessageDialog(null, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);
            userAccount.deposit(amount);
            outputArea.setText("Deposit successful. New balance: $" + userAccount.getBalance());
            amountField.setText("");
        } catch (NumberFormatException e) {
            outputArea.setText("Invalid input. Please enter a valid number.");
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void displayBalance() {
        outputArea.setText("Current balance: $" + userAccount.getBalance());
        amountField.setText("");
    }
}