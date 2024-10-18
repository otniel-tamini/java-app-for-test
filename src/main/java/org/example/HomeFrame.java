package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeFrame extends JFrame {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskField;

    public HomeFrame() {
        setTitle("Page d'accueil - To-Do List");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Couleurs et polices
        Color backgroundColor = new Color(60, 63, 65);
        Color textColor = new Color(187, 187, 187);
        Font font = new Font("SansSerif", Font.PLAIN, 14);

        // Panneau principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Liste de tâches
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setBackground(new Color(43, 43, 43));
        taskList.setForeground(textColor);
        taskList.setFont(font);
        taskList.setSelectionBackground(new Color(75, 110, 175));
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Panneau d'entrée des tâches
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(backgroundColor);
        taskField = new JTextField();
        taskField.setBackground(new Color(69, 73, 74));
        taskField.setForeground(textColor);
        taskField.setFont(font);
        JButton addButton = new JButton("Ajouter tâche");
        addButton.setFont(font);
        addButton.setBackground(new Color(69, 73, 74));
        addButton.setForeground(textColor);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = taskField.getText();
                if (!task.isEmpty()) {
                    taskListModel.addElement(task);
                    taskField.setText("");
                }
            }
        });

        inputPanel.add(taskField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        // Ajouter composants au panneau principal
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        // Ajouter panneau principal à la fenêtre
        getContentPane().add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomeFrame().setVisible(true));
    }
}
