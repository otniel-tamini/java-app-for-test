package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorFrame extends JFrame {
    private JTextField displayField;
    private double result = 0;
    private String operator = "=";
    private boolean calculating = true;

    public CalculatorFrame() {
        setTitle("Calculatrice");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        displayField = new JTextField("0");
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setFont(new Font("SansSerif", Font.BOLD, 32));
        add(displayField, BorderLayout.NORTH);

        ActionListener insert = new InsertAction();
        ActionListener command = new CommandAction();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));
        addButton(panel, "7", insert);
        addButton(panel, "8", insert);
        addButton(panel, "9", insert);
        addButton(panel, "/", command);
        addButton(panel, "4", insert);
        addButton(panel, "5", insert);
        addButton(panel, "6", insert);
        addButton(panel, "*", command);
        addButton(panel, "1", insert);
        addButton(panel, "2", insert);
        addButton(panel, "3", insert);
        addButton(panel, "-", command);
        addButton(panel, "0", insert);
        addButton(panel, ".", insert);
        addButton(panel, "=", command);
        addButton(panel, "+", command);
        addButton(panel, "Accueil", new HomeAction()); // Ajout du bouton Accueil

        add(panel, BorderLayout.CENTER);
    }

    private void addButton(Container c, String title, ActionListener listener) {
        JButton button = new JButton(title);
        button.setFont(new Font("SansSerif", Font.PLAIN, 24));
        button.addActionListener(listener);
        c.add(button);
    }

    private class InsertAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            String input = event.getActionCommand();
            if (calculating) {
                displayField.setText(input);
                calculating = false;
            } else {
                displayField.setText(displayField.getText() + input);
            }
        }
    }

    private class CommandAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();
            if (calculating) {
                if (command.equals("-")) {
                    displayField.setText(command);
                    calculating = false;
                } else {
                    operator = command;
                }
            } else {
                calculate(Double.parseDouble(displayField.getText()));
                operator = command;
                calculating = true;
            }
        }
    }

    private void calculate(double x) {
        switch (operator) {
            case "+" -> result += x;
            case "-" -> result -= x;
            case "*" -> result *= x;
            case "/" -> result /= x;
            case "=" -> result = x;
        }
        displayField.setText("" + result);
    }

    private class HomeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            // Action à effectuer lors du clic sur le bouton Accueil

            // Par exemple, fermer la calculatrice
            dispose();
        }
    }
}
