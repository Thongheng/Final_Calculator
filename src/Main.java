import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    private final JTextField firstNumberField;
    private final JTextField secondNumberField;
    private final JLabel resultField;

    public Main(){
        setTitle("Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLayout(new BorderLayout(10, 10));

        // Input component
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        firstNumberField = new JTextField();
        secondNumberField = new JTextField();
        resultField = new JLabel();
        inputPanel.add(new JLabel("First Number:"));
        inputPanel.add(firstNumberField);
        inputPanel.add(new JLabel("Second Number:"));
        inputPanel.add(secondNumberField);
        inputPanel.add(new JLabel("Result:"));
        inputPanel.add(resultField);

        // Buttons component
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        String[] buttons = {"+", "-", "x", "/", "%", "Clear"};
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setActionCommand(text);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
//        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String firstNumber = firstNumberField.getText();
            String secondNumber = secondNumberField.getText();
            double n1 = Double.parseDouble(firstNumber);
            double n2 = Double.parseDouble(secondNumber);
            double r = 0;

            String operator = e.getActionCommand();
            switch (operator) {
                case "+" -> r = n1 + n2;
                case "-" -> r = n1 - n2;
                case "x" -> r = n1 * n2;
                case "/" -> {
                    if (n2 == 0) {
                        resultField.setText("Error: Division by zero");
                        return;
                    } else {
                        r = n1 / n2;
                    }
                }
                case "%" -> r = n1 % n2;
                case "Clear" -> {
                    firstNumberField.setText("");
                    secondNumberField.setText("");
                    resultField.setText("");
                    return;
                }
                default -> throw new IllegalStateException("Unexpected value: " + operator);
            }
            resultField.setText(String.valueOf(r));

        } catch (NumberFormatException ex) {
            resultField.setText("Error: Invalid Number");
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
