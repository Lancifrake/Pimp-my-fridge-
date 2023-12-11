package View;

import javax.swing.*;
import java.awt.*;

public class Values extends JFrame {

    private JLabel humidityLabel;
    private JLabel indoorTemperatureLabel;
    private JLabel outdoorTemperatureLabel;
    private JLabel dewPointLabel;

    public Values() {
        setTitle("Donnés de la carte Arduino");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Créer un panel principal avec BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.RED);

        // Charger l'image depuis le chemin spécifié (ajustez le chemin selon votre besoin)
        ImageIcon backgroundImage = new ImageIcon("C:/Users/lanci/Desktop/Livrables_PMF_Groupe2/Projet Cindy/ProjetPMF/pmf1.jpg");

        // Créer un JLabel pour afficher l'image
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());

        // Ajouter l'image en tant que fond au panel principal
        mainPanel.add(backgroundLabel, BorderLayout.CENTER);

        JLabel welcomeLabel = new JLabel("VOICI LES VALEURS RECUPEREES PAR L'ARDUINO");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 25));
        welcomeLabel.setVerticalAlignment(JLabel.CENTER);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Créer un panel pour contenir les labels de données
        JPanel dataPanel = new JPanel(new GridBagLayout());
        dataPanel.setOpaque(false); // Rendre le panel transparent

        // Initialiser les labels de données
        humidityLabel = createLabel("Humidité intérieure : ");
        indoorTemperatureLabel = createLabel("Temperature intérieure : ");
        outdoorTemperatureLabel = createLabel("Temperature extérieure: ");
        dewPointLabel = createLabel("Point de rosée: ");

        // Ajouter les labels au panel de données avec GridBagLayout
        addLabelToDataPanel(dataPanel, humidityLabel, 0, 0);
        addLabelToDataPanel(dataPanel, indoorTemperatureLabel, 0, 1);
        addLabelToDataPanel(dataPanel, outdoorTemperatureLabel, 0, 2);
        addLabelToDataPanel(dataPanel, dewPointLabel, 0, 3);

        // Ajouter le panel de données au panel principal
        mainPanel.add(dataPanel, BorderLayout.SOUTH);

        // Définir le panel principal comme contenu de la fenêtre
        setContentPane(mainPanel);

        // Centrer la fenêtre sur l'écran
        setLocationRelativeTo(null);

        // Rendre la fenêtre visible
        setVisible(true);
    }

    private JLabel createLabel(String labelText) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        return label;
    }

    private void addLabelToDataPanel(JPanel panel, JLabel label, int gridx, int gridy) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(label, gbc);
    }

    public void updateHumidityLabel(String value) {
        humidityLabel.setText("Humidité intérieure : " + value);
    }

    public void updateIndoorTemperatureLabel(String value) {
        indoorTemperatureLabel.setText("Temperature intérieure : " + value);
    }

    public void updateOutdoorTemperatureLabel(String value) {
        outdoorTemperatureLabel.setText("Temperature extérieure: " + value);
    }

    public void updateDewPointLabel(String value) {
        dewPointLabel.setText("Point de rosée: " + value);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Values app = new Values();
        });
    }

    public void updateChartData(float humidityValue, float temperatureint, float teemperatureext, float point) {
    }
}
