import java.awt.*;
import javax.swing.*;

public class swingTrafficLight extends JFrame {
    private JCheckBox rushHourCheckBox;
    private JTextField durationField;
    private JButton startButton;
    private JLabel statusLabel;
    private JLabel cycleLabel;
    private TrafficLightPanel lightPanel;

    private Timer timer;
    private int secondElapsed = 0;
    private int totalTime = 0;

    public swingTrafficLight() {
        setTitle("Traffic Light Simulator");
        setSize(380, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Input controls panel
        JPanel controlPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Simulation Settings"));

        rushHourCheckBox = new JCheckBox("Rush Hour");
        durationField = new JTextField("60");
        startButton = new JButton("Start Simulation");

        controlPanel.add(new JLabel("Rush Hour Mode:"));
        controlPanel.add(rushHourCheckBox);
        controlPanel.add(new JLabel("Duration (seconds):"));
        controlPanel.add(durationField);
        controlPanel.add(new JLabel(""));
        controlPanel.add(startButton);

        // Status displays
        statusLabel = new JLabel("Status: Ready", SwingConstants.CENTER);
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

        cycleLabel = new JLabel("Completed Cycles: 0 | Elapsed: 0s", SwingConstants.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        infoPanel.add(statusLabel);
        infoPanel.add(cycleLabel);

        // Custom drawn traffic light graphics panel
        lightPanel = new TrafficLightPanel();

        add(controlPanel, BorderLayout.NORTH);
        add(lightPanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.SOUTH);

        startButton.addActionListener(e -> startSimulation());
    }

    private void startSimulation() {
        try {
            totalTime = Integer.parseInt(durationField.getText().trim());
            if (totalTime <= 0) {
                JOptionPane.showMessageDialog(this, "Duration must be positive!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        boolean isRushTime = rushHourCheckBox.isSelected();
        secondElapsed = 0;
        rushHourCheckBox.setEnabled(false);
        startButton.setEnabled(false);

        final int greenDuration = isRushTime ? 45 : 30;
        final int amberDuration = 5;
        final int redDuration = 25;
        final int cycleDuration = greenDuration + amberDuration + redDuration;

        timer = new Timer(1000, e -> {
            secondElapsed++;

            if (secondElapsed > totalTime) {
                timer.stop();
                statusLabel.setText("Status: Simulation Complete");
                lightPanel.setActiveLight("OFF");
                rushHourCheckBox.setEnabled(true);
                startButton.setEnabled(true);
                return;
            }

            int position = (secondElapsed - 1) % cycleDuration;
            String color;
            int remaining;

            if (position < greenDuration) {
                color = "GREEN";
                remaining = greenDuration - position;
            } else if (position < greenDuration + amberDuration) {
                color = "AMBER";
                remaining = (greenDuration + amberDuration) - position;
            } else {
                color = "RED";
                remaining = cycleDuration - position;
            }

            lightPanel.setActiveLight(color);
            statusLabel.setText(String.format("Second %d/%d: %s (%ds left)", secondElapsed, totalTime, color, remaining));

            int completeCycles = secondElapsed / cycleDuration;
            cycleLabel.setText(String.format("Completed Cycles: %d | Elapsed: %ds", completeCycles, secondElapsed));
        });

        timer.start();
    }

    // Custom JPanel for rendering the traffic light visual
    private static class TrafficLightPanel extends JPanel {
        private String activeLight = "OFF";

        public void setActiveLight(String light) {
            this.activeLight = light;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = 110;
            int height = 270;
            int x = (getWidth() - width) / 2;
            int y = (getHeight() - height) / 2;

            // Draw light casing
            g2d.setColor(Color.DARK_GRAY);
            g2d.fillRoundRect(x, y, width, height, 20, 20);

            int size = 70;
            int lightX = x + (width - size) / 2;

            // Red light
            g2d.setColor(activeLight.equals("RED") ? Color.RED : new Color(70, 0, 0));
            g2d.fillOval(lightX, y + 15, size, size);

            // Amber light
            g2d.setColor(activeLight.equals("AMBER") ? Color.ORANGE : new Color(70, 50, 0));
            g2d.fillOval(lightX, y + 100, size, size);

            // Green light
            g2d.setColor(activeLight.equals("GREEN") ? Color.GREEN : new Color(0, 70, 0));
            g2d.fillOval(lightX, y + 185, size, size);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new swingTrafficLight().setVisible(true));
    }
}