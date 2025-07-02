import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
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
        final double[] num1 = {0};
        final String[] operator = {""};

        for (String text : buttons) {
            JButton button = new JButton(text);
            buttonPanel.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String command = e.getActionCommand();

                    if (command.matches("\\d")) { // If it's a digit
                        textField.setText(textField.getText() + command);
                    } else if ("+-*/".contains(command)) {
                        if (!textField.getText().isEmpty()) {
                            num1[0] = Double.parseDouble(textField.getText());
                            operator[0] = command;
                            textField.setText("");
                        }
                    } else if (command.equals("=")) {
                        if (!textField.getText().isEmpty()) {
                            double num2 = Double.parseDouble(textField.getText());
                            double result = 0;

                            switch (operator[0]) {
                                case "+": result = num1[0] + num2; break;
                                case "-": result = num1[0] - num2; break;
                                case "*": result = num1[0] * num2; break;
                                case "/":
                                    if (num2 != 0) {
                                        result = num1[0] / num2;
                                    } else {
                                        textField.setText("Error");
                                        return;
                                    }
                                    break;
                            }

                            //textField.setText(String.valueOf(result));
                            textField.setText(num1[0] + operator[0] + num2 + "=" + result);
                        }
                    } else if (command.equals("C")) {
                        textField.setText("");
                        num1[0] = 0;
                        operator[0] = "";
                    }
                }


            });

        }
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }
}