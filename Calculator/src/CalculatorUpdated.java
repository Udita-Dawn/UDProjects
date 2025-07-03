import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CalculatorUpdated {
    class Addition {
        public static double add(double a, double b) {
            return a + b;
        }
    }
    class Subtraction {
        public static double subtract(double a, double b) {
            return a - b;
        }
    }
    class Multiplication {
        public static double multiple(double a, double b) {
            return a * b;
        }
    }
    class Division {
        public static double division(double a, double b) {
            return a / b;
        }
    }
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
                        }else {
                                textField.setText("Enter a number first");// Operator pressed first
                        }
                        }
                    else if (command.equals("=")&& !operator[0].isEmpty()) {
                        if (!textField.getText().isEmpty()) {
                            double num2 = Double.parseDouble(textField.getText());
                            double result = 0;

                            switch (operator[0]) {
                                case "+":
                                    result = Addition.add(num1[0], num2);
                                    break;
                                case "-":
                                    result = Subtraction.subtract(num1[0], num2);
                                    break;
                                case "*":
                                    result = Multiplication.multiple(num1[0], num2);
                                    break;
                                case "/":
                                    if (num2 != 0) {
                                        result = Division.division(num1[0], num2);
                                    } else {
                                        textField.setText("Error");
                                        return;
                                    }
                                    break;
                                default:
                                    textField.setText("Error");
                                    return;
                            }
                            //textField.setText(String.valueOf(result));
                            textField.setText(num1[0] + operator[0] + num2 + "=" + result);
                        }
                    } else if (command.equals("C")) {
                        textField.setText("");
                        num1[0] = 0;
                        operator[0] = "";
                    } else {
                        // Invalid input
                        textField.setText("Invalid =");
                    }
                }


            });

        }
        frame.add(buttonPanel, BorderLayout.CENTER);


// Make the frame visible
        frame.setVisible(true);

    }
    }