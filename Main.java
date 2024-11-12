/*
*
*@author Samet Senturk
*@version 2.3
*@date 11-12-2024
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main extends JFrame {
    private JTextField firstNameField, surnameField, balanceField;
    private JTextArea displayArea;
    private double balance = 0.0;
    private boolean isStarted = false;

    public Main() {
        setTitle("ATM Application");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Kullanıcı giriş paneli
        JPanel userPanel = new JPanel(new GridLayout(3, 2));
        userPanel.add(new JLabel("Name:"));
        firstNameField = new JTextField();
        userPanel.add(firstNameField);

        userPanel.add(new JLabel("Starting Balance:"));
        balanceField = new JTextField();
        userPanel.add(balanceField);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new StartButtonListener());
        userPanel.add(startButton);

        JButton ExitButton = new JButton("Exit");
        ExitButton.addActionListener(new ExitButtonListener());
        userPanel.add(ExitButton);

        add(userPanel, BorderLayout.NORTH);

        // İşlem seçenekleri paneli
        JPanel actionPanel = new JPanel(new GridLayout(2, 2));

        JButton balanceButton = new JButton("Check Balance");
        balanceButton.addActionListener(e -> displayBalance());
        actionPanel.add(balanceButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(e -> withdraw());
        actionPanel.add(withdrawButton);

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(e -> deposit());
        actionPanel.add(depositButton);

        JButton billButton = new JButton("Pay Bill");
        billButton.addActionListener(e -> payBill());
        actionPanel.add(billButton);

        add(actionPanel, BorderLayout.CENTER);

        // Ekran mesaj alanı
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setLineWrap(true);
        displayArea.setWrapStyleWord(true);
        add(new JScrollPane(displayArea), BorderLayout.SOUTH);
    }

    private class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String password = JOptionPane.showInputDialog(null, "Enter Password:");

            // Şifre doğrulaması
            if (isValidPassword(password)) {
                try {
                    String firstName = firstNameField.getText().trim();

                    if (firstName.isEmpty()) {
                        displayMessage("ERROR: Name required.\n", Color.RED);
                        return;
                    }

                    balance = Double.parseDouble(balanceField.getText().trim());
                    if (balance < 0) {
                        displayMessage("ERROR: Invalid Balance\n", Color.RED);
                        return;
                    }

                    isStarted = true;
                    displayMessage("Welcome, " + firstName + "\n", Color.BLACK);
                } catch (NumberFormatException ex) {
                    displayMessage("ERROR: Invalid balance input.\n", Color.RED);
                }
            } else {
                displayMessage("ERROR: Invalid password format.\n", Color.RED);
            }
        }
    }

    private class ExitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent d) {
            JOptionPane.showMessageDialog(null, "Exiting..");
        }
    }

    private void displayBalance() {
        if (checkStarted()) {
            JOptionPane.showMessageDialog(null, "Your Balance is " + balance);
        }
    }

    private void withdraw() {
        if (checkStarted()) {
            String amountString = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
            try {
                double amount = Double.parseDouble(amountString);
                if (amount <= 0 || amount > balance) {
                    displayMessage("ERROR: Invalid withdrawal amount.\n", Color.RED);
                } else {
                    balance -= amount;
                    displayMessage("Withdrawal successful. New balance: $" + balance + "\n", Color.BLACK);
                }
            } catch (NumberFormatException ex) {
                displayMessage("ERROR: Invalid withdrawal amount.\n", Color.RED);
            }
        }
    }

    private void deposit() {
        if (checkStarted()) {
            String amountString = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
            try {
                double amount = Double.parseDouble(amountString);
                if (amount > 0) {
                    balance += amount;
                    displayMessage("Deposit successful. New balance: $" + balance + "\n", Color.BLACK);
                } else {
                    displayMessage("ERROR: Invalid deposit amount.\n", Color.RED);
                }
            } catch (NumberFormatException ex) {
                displayMessage("ERROR: Invalid deposit amount.\n", Color.RED);
            }
        }
    }

    private void payBill() {
        if (checkStarted()) {
            String[] bills = {"Electricity", "Water", "Internet"};
            int choice = JOptionPane.showOptionDialog(this, "Select a bill to pay:",
                    "Bill Payment", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, bills, bills[0]);

            if (choice != -1) {
                double billAmount = Math.round((0.01 + Math.random() * 99.9) * 100.0) / 100.0;
                String billType = bills[choice];

                int confirm = JOptionPane.showConfirmDialog(this,
                        "Your " + billType + " bill is $" + billAmount + ". Do you wish to pay?",
                        "Confirm Payment", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    if (balance >= billAmount) {
                        balance -= billAmount;
                        displayMessage("Payment successful for " + billType + ". New balance: $" + balance + "\n", Color.BLACK);
                    } else {
                        displayMessage("Insufficient funds to pay the " + billType + " bill.\n", Color.RED);
                    }
                }
            }
        }
    }

    private boolean checkStarted() {
        if (!isStarted) {
            displayMessage("ERROR: Please start the session first.\n", Color.RED);
            return false;
        }
        return true;
    }

    private void displayMessage(String message, Color color) {
        displayArea.setForeground(color);
        displayArea.append(message);
    }

    // Şifre doğrulama fonksiyonu
    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[A-Z])(?=(.*\\d){2,}).{5,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setVisible(true);
        });
    }
}
