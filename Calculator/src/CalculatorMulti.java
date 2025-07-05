import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CalculatorMulti {
//static inner classes for mathematical calculations
    private static class Addition {
        private static double add(double a, double b) {
            return a + b;
        }
    }
    private static class Subtraction {
        private static double subtract(double a, double b) {
            return a - b;
        }
    }
    private static class Multiplication {
        private static double multiple(double a, double b) {
            return a * b;
        }
    }
    private static class Division {
        private static double division(double a, double b) {
            return a / b;
        }
    }
//flag to handle Exception Division by zero
    private static boolean evaluationError = false;


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
            buttonPanel.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String command = e.getActionCommand();

                    if (command.matches("\\d")) {
                        // Always allow digits
                        textField.setText(textField.getText() + command);
                    } else if ("+-*/".contains(command)) {
                        String currentText = textField.getText();
                        if (currentText.isEmpty()) {
                            textField.setText("Error: Start with a number");
                        }
                        else {
                            textField.setText(currentText + command);
                        }
                    } else if (command.equals("=")) {
                        String expression = textField.getText();
                            double result = evaluateExpression(expression);
                            if (evaluationError) {
                                textField.setText("Error: Division by zero");
                            }
                            else if (result == (long) result) {
                                textField.setText(String.valueOf((long) result)); // Show as integer
                            } else {
                                textField.setText(String.valueOf(result)); // Show as decimal
                            }
                        } else if (command.equals("C")) {
                        textField.setText("");
                    }
                }

                private double evaluateExpression(String expr) {

                    String[] tokens = expr.split("(?<=[-+*/])|(?=[-+*/])");
                    double result = Double.parseDouble(tokens[0]);

                    for (int i = 1; i < tokens.length; i += 2) {
                        String operator = tokens[i];
                        double nextNumber = Double.parseDouble(tokens[i + 1]);

                        switch (operator) {
                            case "+":
                                result = Addition.add(result, nextNumber);
                                break;
                            case "-":
                                result = Subtraction.subtract(result, nextNumber);
                                break;
                            case "*":
                                result = Multiplication.multiple(result, nextNumber);
                                break;
                            case "/":
                                if (nextNumber == 0) {
                                    evaluationError = true;
                                    return 0;
                                }
                                result = Division.division(result, nextNumber);
                                break;
                            }
                    }
                    return result;
                }
            });
                    frame.add(buttonPanel, BorderLayout.CENTER);
// Make the frame visible
                    frame.setVisible(true);
                }
            }
        }

