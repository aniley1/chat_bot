import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RuleBasedChatbot {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Rule-Based Chatbot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);

        JTextField inputField = new JTextField();
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userQuery = inputField.getText().toLowerCase();
                String botResponse = getBotResponse(userQuery);
                chatArea.append("User: " + userQuery + "\n");
                chatArea.append("Chatbot: " + botResponse + "\n");
                inputField.setText("");

                if (userQuery.contains("goodbye") || userQuery.contains("bye")) {
                    chatArea.append("Chatbot: Goodbye! Have a great day!\n");
                }
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(chatArea), BorderLayout.CENTER);
        panel.add(inputField, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static String getBotResponse(String userQuery) {
        if (userQuery.contains("hello") || userQuery.contains("hi")) {
            return "Hi there! How can I help you?";
        } else if (userQuery.contains("how are you")) {
            return "I'm just a computer program, so I don't have feelings, but thanks for asking!";
        } else if (userQuery.contains("weather")) {
            return "I'm sorry, I can't provide real-time weather information.";
        } else if (userQuery.contains("time")) {
            return "I don't have access to the current time right now.";
        } else if (userQuery.contains("calculate")) {
            try {
                String expression = userQuery.replace("calculate", "").trim();
                double result = evaluateExpression(expression);
                return "The result is: " + result;
            } catch (Exception e) {
                return "Sorry, I couldn't calculate that. Error: " + e.getMessage();
            }
        } else {
            return "I'm not sure how to respond to that. Please ask me something else.";
        }
    }

    public static double evaluateExpression(String expression) {
        try {
            String[] tokens = expression.split(" ");
            double operand1 = Double.parseDouble(tokens[0]);
            String operator = tokens[1];
            double operand2 = Double.parseDouble(tokens[2]);

            double result = 0;

            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    if (operand2 != 0) {
                        result = operand1 / operand2;
                    } else {
                        throw new ArithmeticException("Division by zero");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operator");
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
