import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Example {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Simple Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());

        // Create text field for input/output
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.BOLD, 20));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(textField, BorderLayout.NORTH);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));

        // Button labels
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        // Add buttons to the panel
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            buttonPanel.add(button);

            // Add action listener to buttons
            button.addActionListener(new ActionListener() {
                String operator = "";
                double num1 = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    String command = e.getActionCommand();

                    if (command.matches("\\d")) { // If it's a number
                        textField.setText(textField.getText() + command);
                    } else if (command.equals("C")) { // Clear
                        textField.setText("");
                        operator = "";
                        num1 = 0;
                    } else if (command.equals("=")) { // Calculate result
                        double num2 = Double.parseDouble(textField.getText());
                        double result = 0;

                        switch (operator) {
                            case "+" -> result = num1 + num2;
                            case "-" -> result = num1 - num2;
                            case "*" -> result = num1 * num2;
                            case "/" -> result = num1 / num2;
                        }

                        textField.setText(String.valueOf(result));
                        operator = "";
                    } else { // Operator
                        operator = command;
                        num1 = Double.parseDouble(textField.getText());
                        textField.setText("");
                    }
                }
            });
        }

        frame.add(buttonPanel, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }
}