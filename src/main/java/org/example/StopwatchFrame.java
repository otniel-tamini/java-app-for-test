package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class StopwatchFrame extends JFrame {
    private JLabel timeLabel;
    private Timer timer;
    private long startTime, elapsedTime;
    private JButton startButton, stopButton, resetButton, bgButton;
    private BufferedImage backgroundImage;

    public StopwatchFrame() {
        setTitle("Modern Chronomètre");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Couleurs et polices
        Color textColor = new Color(236, 240, 241);
        Font font = new Font("SansSerif", Font.BOLD, 36);

        // Panneau principal
        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        // Label de temps
        timeLabel = new JLabel("00:00:00.000000000");
        timeLabel.setFont(font);
        timeLabel.setForeground(textColor);
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(timeLabel, BorderLayout.CENTER);

        // Panneau de boutons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        buttonPanel.setOpaque(false);

        startButton = createButton("⏵ Démarrer", textColor, font);
        stopButton = createButton("⏸ Arrêter", textColor, font);
        resetButton = createButton("🔄 Réinitialiser", textColor, font);
        bgButton = createButton("🖼 Changer fond", textColor, font);

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(bgButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        timer = new Timer(0, e -> {
            elapsedTime = System.nanoTime() - startTime;
            updateElapsedTime();
        });

        startButton.addActionListener(e -> {
            startTime = System.nanoTime() - elapsedTime;
            timer.start();
        });

        stopButton.addActionListener(e -> timer.stop());

        resetButton.addActionListener(e -> {
            timer.stop();
            elapsedTime = 0;
            updateElapsedTime();
        });

        bgButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    backgroundImage = ImageIO.read(selectedFile);
                    mainPanel.repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private JButton createButton(String text, Color foreground, Font font) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setForeground(foreground);
        button.setBackground(new Color(52, 152, 219));
        return button;
    }

    private void updateElapsedTime() {
        long hours = elapsedTime / 3600000000000L;
        long minutes = (elapsedTime % 3600000000000L) / 60000000000L;
        long seconds = (elapsedTime % 60000000000L) / 1000000000L;
        long milliseconds = (elapsedTime % 1000000000L) / 1000000L;
        long nanoseconds = elapsedTime % 1000000L;
        timeLabel.setText(String.format("%02d:%02d:%02d.%03d%06d", hours, minutes, seconds, milliseconds, nanoseconds));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StopwatchFrame().setVisible(true));
    }
}
