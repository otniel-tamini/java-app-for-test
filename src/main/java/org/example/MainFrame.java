package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Application Multifonctionnelle");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Couleurs et polices
        Color backgroundColor = new Color(44, 62, 80);
        Color textColor = new Color(236, 240, 241);
        Font font = new Font("SansSerif", Font.BOLD, 18);

        // Panneau principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(backgroundColor);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        // Boutons personnalisés
        JButton clockButton = new JButton("🕒 Horloge");
        JButton stopwatchButton = new JButton("⏱ Chronomètre");
        JButton calculatorButton = new JButton("🧮 Calculatrice");

        clockButton.setFont(font);
        stopwatchButton.setFont(font);
        calculatorButton.setFont(font);

        clockButton.setBackground(new Color(52, 152, 219));
        stopwatchButton.setBackground(new Color(46, 204, 113));
        calculatorButton.setBackground(new Color(231, 76, 60));

        clockButton.setForeground(textColor);
        stopwatchButton.setForeground(textColor);
        calculatorButton.setForeground(textColor);

        clockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClockFrame().setVisible(true);
                dispose();
            }
        });

        stopwatchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StopwatchFrame().setVisible(true);
                dispose();
            }
        });

        calculatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CalculatorFrame().setVisible(true);
                dispose();
            }
        });

        // Ajout des boutons au panneau principal
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(clockButton, c);

        c.gridx = 1;
        mainPanel.add(stopwatchButton, c);

        c.gridx = 2;
        mainPanel.add(calculatorButton, c);

        getContentPane().add(mainPanel);
    }
}
