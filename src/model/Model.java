package model;

import View.GraphFrame;
import View.Values;
import com.fazecast.jSerialComm.SerialPort;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Model extends JFrame {
    private List<Float> humidityValues = new ArrayList<>();
    private List<Float> temperatureintvalues = new ArrayList<>();
    private List<Float> temperatureextValues = new ArrayList<>();
    private List<Float> pointValues = new ArrayList<>();
    private JComboBox<String> portComboBox;
    private JButton refreshButton;
    public Values valuesInstance;
    private JButton connectButton;
    private JTextField temperatureField;
    private JLabel sentValueLabel;
    private SerialPort selectedPort;
    private JLabel humidityLabel;
    private JLabel indoorTemperatureLabel;
    private JLabel outdoorTemperatureLabel;
    private JLabel dewPointLabel;
    public GraphFrame graphFrame;
    private boolean isAlertShown = false;
    private boolean eleve = false;
    //private GraphFrame graphFrame;

    public Model() {
        setTitle("Application pour la gestion du Mini-frigo");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.black);

        JLabel welcomeLabel = new JLabel("BIENVENUE SUR NOTRE APPLICATION");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        welcomeLabel.setVerticalAlignment(JLabel.CENTER);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Color.black);

        JPanel portPanel = new JPanel(new GridBagLayout());
        portPanel.setBackground(Color.black);

        JLabel selectPortLabel = new JLabel("Choisis ton port:");
        selectPortLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        portComboBox = new JComboBox<>();
        portComboBox.setPreferredSize(new Dimension(200, 40));
        refreshButton = new JButton("Actualiser");
        refreshButton.setPreferredSize(new Dimension(100, 40));
        connectButton = new JButton("Login");
        connectButton.setPreferredSize(new Dimension(100, 40));
        // Ajouter un label pour afficher les valeurs envoyées
        sentValueLabel = new JLabel("Température de consigne: ");
        sentValueLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        // Ajouter un champ de texte pour la température
        temperatureField = new JTextField(10);
        temperatureField.setPreferredSize(new Dimension(100,40));


        GridBagConstraints gbcSelectPortLabel = new GridBagConstraints();
        gbcSelectPortLabel.gridx = 0;
        gbcSelectPortLabel.gridy = 0;
        gbcSelectPortLabel.weighty = 1;
        gbcSelectPortLabel.anchor = GridBagConstraints.EAST;
        gbcSelectPortLabel.insets = new Insets(0, 0, 10, 10);
        portPanel.add(selectPortLabel, gbcSelectPortLabel);

        GridBagConstraints gbcPortComboBox = new GridBagConstraints();
        gbcPortComboBox.gridx = 1;
        gbcPortComboBox.gridy = 0;
        gbcPortComboBox.weighty = 1;
        gbcPortComboBox.anchor = GridBagConstraints.WEST;
        gbcPortComboBox.insets = new Insets(0, 0, 10, 10);
        portPanel.add(portComboBox, gbcPortComboBox);

        GridBagConstraints gbcRefreshButton = new GridBagConstraints();
        gbcRefreshButton.gridx = 2;
        gbcRefreshButton.gridy = 0;
        gbcRefreshButton.anchor = GridBagConstraints.EAST;
        gbcRefreshButton.insets = new Insets(0, 0, 10, 10);
        portPanel.add(refreshButton, gbcRefreshButton);

        GridBagConstraints gbcConnectButton = new GridBagConstraints();
        gbcConnectButton.gridx = 3;
        gbcConnectButton.gridy = 0;
        gbcConnectButton.anchor = GridBagConstraints.WEST;
        gbcConnectButton.insets = new Insets(0, 0, 10, 10);
        portPanel.add(connectButton, gbcConnectButton);

        GridBagConstraints gbcSentValueLabel = new GridBagConstraints();
        gbcSentValueLabel.gridx = 0;
        gbcSentValueLabel.gridy = 2;
        gbcSentValueLabel.anchor = GridBagConstraints.EAST;
        gbcSentValueLabel.insets = new Insets(0, 0, 10, 10);
        portPanel.add(sentValueLabel, gbcSentValueLabel);

        GridBagConstraints gbcTemperatureField = new GridBagConstraints();
        gbcTemperatureField .gridx = 1;
        gbcTemperatureField .gridy = 2;
        gbcTemperatureField .anchor = GridBagConstraints.WEST;
        gbcTemperatureField .insets = new Insets(0, 0, 10, 10);
        portPanel.add(temperatureField, gbcTemperatureField );

        GridBagConstraints gbcPortPanel = new GridBagConstraints();
        gbcPortPanel.gridx = 0;
        gbcPortPanel.gridy = 0;
        gbcPortPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcPortPanel.weightx = 1.0;
        gbcPortPanel.insets = new Insets(0, 0, 10, 10);
        contentPanel.add(portPanel, gbcPortPanel);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        ImageIcon leftImageIcon = new ImageIcon("C:/Users/lanci/Desktop/Livrables_PMF_Groupe2/Projet Cindy/ProjetPMF/pm.jpg");
        JLabel leftImageLabel = new JLabel(leftImageIcon);

        ImageIcon leftdownImageIcon = new ImageIcon("C:/Users/lanci/Desktop/Livrables_PMF_Groupe2/Projet Cindy/ProjetPMF/pmf2.jpg");
        JLabel leftdownImageLabel = new JLabel(leftdownImageIcon);

        ImageIcon rightdownImageIcon = new ImageIcon("C:/Users/lanci/Desktop/Livrables_PMF_Groupe2/Projet Cindy/ProjetPMF/term.png");
        JLabel rightdownImageLabel = new JLabel(rightdownImageIcon);

        JPanel imagePanel = new JPanel(new GridBagLayout());
        imagePanel.setBackground(Color.black);

        GridBagConstraints gbcLeftImageLabel = new GridBagConstraints();
        gbcLeftImageLabel.gridx = 0;
        gbcLeftImageLabel.gridy = 0;
        gbcLeftImageLabel.anchor = GridBagConstraints.NORTHWEST;
        gbcLeftImageLabel.weighty = 1;
        imagePanel.add(leftImageLabel, gbcLeftImageLabel);

        GridBagConstraints gbcLeftdownImageLabel = new GridBagConstraints();
        gbcLeftdownImageLabel.gridx = 0;
        gbcLeftdownImageLabel.gridy = 1;
        gbcLeftdownImageLabel.anchor = GridBagConstraints.NORTHWEST;
        gbcLeftdownImageLabel.weighty = 1;
        imagePanel.add(leftdownImageLabel, gbcLeftdownImageLabel);

        GridBagConstraints gbcRightdownImageLabel = new GridBagConstraints();
        gbcRightdownImageLabel.gridx = 1;
        gbcRightdownImageLabel.gridy = 0;
        gbcRightdownImageLabel.anchor = GridBagConstraints.SOUTHEAST;
        gbcRightdownImageLabel.weighty = 1;
        imagePanel.add(rightdownImageLabel, gbcRightdownImageLabel);

        mainPanel.add(imagePanel, BorderLayout.WEST);

        add(mainPanel);


        refreshButton.addActionListener(e -> refreshPortList());

        connectButton.addActionListener(e -> connectToArduino());

        model.Model.SerialReader serialReader = new model.Model.SerialReader();
        Thread readerThread = new Thread(serialReader);
        readerThread.start();
        humidityLabel = new JLabel("Humidité intérieure: ");
        indoorTemperatureLabel = new JLabel("Température à l'intérieur du frigo: ");
        outdoorTemperatureLabel = new JLabel("Température à l'extérieure: ");
        dewPointLabel = new JLabel("Point de rosée: ");

        JPanel dataPanel = new JPanel(new GridLayout(4, 2));
        dataPanel.add(humidityLabel);
        dataPanel.add(indoorTemperatureLabel);
        dataPanel.add(outdoorTemperatureLabel);
        dataPanel.add(dewPointLabel);


        // mainPanel.add(dataPanel, BorderLayout.CENTER);
    }

    private void refreshPortList() {
        portComboBox.removeAllItems();
        SerialPort[] ports = SerialPort.getCommPorts();
        for (SerialPort port : ports) {
            portComboBox.addItem(port.getSystemPortName());
        }
    }

    private void connectToArduino() {
        String selectedPortName = (String) portComboBox.getSelectedItem();
        selectedPort = SerialPort.getCommPort(selectedPortName);

        if (!temperatureField.getText().isEmpty()) {
            try {
                double temperatureValue = Double.parseDouble(temperatureField.getText());

                selectedPort.setComPortParameters(9600, 8, 1, 0);
                selectedPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);

                if (selectedPort.openPort()) {
                    JOptionPane.showMessageDialog(this, "La connexion a été établie!", "Succès", JOptionPane.INFORMATION_MESSAGE);

                    try (OutputStream outputStream = selectedPort.getOutputStream()) {
                        outputStream.write((int) temperatureValue);
                        outputStream.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Impossible de se connecter au port série.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer une valeur numérique valide pour la température.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez entrer une valeur pour la température.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        if (selectedPort.openPort()) {
            JOptionPane.showMessageDialog(this, "La connexion a été établie!", "Succès", JOptionPane.INFORMATION_MESSAGE);
            model.Model.SerialReader serialReader = new model.Model.SerialReader();
            new Thread(serialReader).start();
        } else {
            JOptionPane.showMessageDialog(this, "Impossible de se connecter au port série.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        setVisible(true);
    }
    private class SerialReader implements Runnable {
        @Override
        public void run() {
            if (selectedPort == null) {
                System.out.println("Le port sélectionné n'est pas initialisé.");
                return;
            }

            try (InputStream inputStream = selectedPort.getInputStream();
                 Scanner scanner = new Scanner(inputStream)) {

                while (scanner.hasNextLine()) {
                    String receivedData = scanner.nextLine();
                    SwingUtilities.invokeLater(() -> processArduinoData(receivedData));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void showAlert(String message){
        SwingUtilities.invokeLater(() -> {
            //int result = JOptionPane.showConfirmDialog(this, message, "Alerte", JOptionPane.DEFAULT_OPTION);
            JOptionPane.showConfirmDialog(this, message, "Alerte", JOptionPane.DEFAULT_OPTION);
            //if (result == JOptionPane.OK_OPTION){
                //System.exit(0);
            //}
        });
    }
    private void high(String message){
        SwingUtilities.invokeLater(() -> {
            //int result = JOptionPane.showConfirmDialog(this, message, "Alerte", JOptionPane.DEFAULT_OPTION);
            JOptionPane.showConfirmDialog(this, message, "Alerte", JOptionPane.DEFAULT_OPTION);
            //if (result == JOptionPane.OK_OPTION){
                //System.exit(0);
            //}
        });
    }


    private void processArduinoData(String data) {
        String[] values = data.split(",");
        float humidityValue = 0;
        float temperatureint=0;
        float teemperatureext = 0;
        float point=0;
        if (values.length >= 2) {
            try {
                humidityValue = Float.parseFloat(values[0]);
                temperatureint = Float.parseFloat(values[1]);
                teemperatureext = Float.parseFloat(values[2]);
                point = Float.parseFloat(values[3]);

                humidityValues.add(humidityValue);
                System.out.println(humidityValues);
                temperatureintvalues.add(temperatureint);
                System.out.println(temperatureintvalues);
                temperatureextValues.add(teemperatureext);
                System.out.println(temperatureextValues);
                pointValues.add(point);
                System.out.println(pointValues);

                valuesInstance.updateHumidityLabel(String.valueOf(humidityValue));
                valuesInstance.updateIndoorTemperatureLabel(String.valueOf(temperatureint));
                valuesInstance.updateOutdoorTemperatureLabel(String.valueOf(teemperatureext));
                valuesInstance.updateDewPointLabel(String.valueOf(point));

                graphFrame.updateChartData(humidityValue, temperatureint, teemperatureext, point);


            } catch (NumberFormatException e) {
                System.err.println("Erreur de conversion de la valeur : " + data);
            }
        }
        // valuesInstance.updateHumidityLabel(String.valueOf(humidityValue));
        // valuesInstance.updateIndoorTemperatureLabel(String.valueOf(temperatureint));
        //valuesInstance.updateOutdoorTemperatureLabel(String.valueOf(teemperatureext));
        //valuesInstance.updateDewPointLabel(String.valueOf(point));
        valuesInstance.updateChartData(humidityValue, temperatureint, teemperatureext, point);
        // ..
        if (temperatureint < point && !isAlertShown){
            showAlert("ALERTE RISQUE DE CONDENSATION !");
            isAlertShown = true;
        } else if (temperatureint > point) {
            isAlertShown = false;
        }

        if (temperatureint >29 && !eleve){
            high("ALERTE TEMPERATURE ELEVE !");
            eleve = true;
        } else if (temperatureint <= 30.5) {
            eleve = false;
        }

    }

}
