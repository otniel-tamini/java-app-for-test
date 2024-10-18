package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ClockFrame extends JFrame {
    public ClockFrame() {
        setTitle("Rolex Style Clock");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ClockPanel clockPanel = new ClockPanel();
        add(clockPanel, BorderLayout.CENTER);

        // Bouton de retour au menu principal
        JButton backButton = new JButton("Retour au menu principal");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainFrame().setVisible(true);
                dispose();
            }
        });

        // Panneau pour le bouton
        JPanel panel = new JPanel();
        panel.add(backButton);

        add(panel, BorderLayout.SOUTH);

        // Timer to update every second
        Timer timer = new Timer(1000, e -> clockPanel.repaint());
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClockFrame().setVisible(true));
    }
}

class ClockPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(centerX, centerY) - 10;

        // Draw clock face
        g2.setColor(Color.BLACK);
        g2.fillOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
        g2.setColor(Color.ORANGE);
        g2.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);

        // Draw hour markers
        for (int i = 0; i < 12; i++) {
            int angle = i * 30;
            int x1 = (int) (centerX + radius * 0.85 * Math.sin(Math.toRadians(angle)));
            int y1 = (int) (centerY - radius * 0.85 * Math.cos(Math.toRadians(angle)));
            int x2 = (int) (centerX + radius * 0.75 * Math.sin(Math.toRadians(angle)));
            int y2 = (int) (centerY - radius * 0.75 * Math.cos(Math.toRadians(angle)));
            g2.drawLine(x1, y1, x2, y2);
        }

        // Get current time
        LocalTime time = LocalTime.now();

        // Draw hour hand
        int hourAngle = (time.getHour() % 12) * 30 + time.getMinute() / 2;
        drawHand(g2, centerX, centerY, hourAngle, radius * 0.5, 8);

        // Draw minute hand
        int minuteAngle = time.getMinute() * 6;
        drawHand(g2, centerX, centerY, minuteAngle, radius * 0.75, 6);

        // Draw second hand
        int secondAngle = time.getSecond() * 6;
        drawHand(g2, centerX, centerY, secondAngle, radius * 0.85, 2);
    }

    private void drawHand(Graphics2D g2, int x, int y, int angle, double length, int width) {
        g2.setStroke(new BasicStroke(width));
        int xEnd = (int) (x + length * Math.sin(Math.toRadians(angle)));
        int yEnd = (int) (y - length * Math.cos(Math.toRadians(angle)));
        g2.drawLine(x, y, xEnd, yEnd);
    }
}
