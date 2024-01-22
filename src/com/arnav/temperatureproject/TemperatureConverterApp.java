package com.arnav.temperatureproject;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TemperatureConverterApp extends JFrame {
    private JTextField inputField;
    private JRadioButton celsiusToFahrenheitRadioButton;
    private JRadioButton fahrenheitToCelsiusRadioButton;
    private JButton convertButton;
    private JLabel resultLabel;

    public TemperatureConverterApp() {
        setTitle("Temperature Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        inputField = new JTextField();
        panel.add(inputField);

        celsiusToFahrenheitRadioButton = new JRadioButton("Celsius to Fahrenheit");
        fahrenheitToCelsiusRadioButton = new JRadioButton("Fahrenheit to Celsius");
        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(celsiusToFahrenheitRadioButton);
        radioButtonGroup.add(fahrenheitToCelsiusRadioButton);

        panel.add(celsiusToFahrenheitRadioButton);
        panel.add(fahrenheitToCelsiusRadioButton);

        convertButton = new JButton("Convert");
        panel.add(convertButton);

        resultLabel = new JLabel();
        panel.add(resultLabel);

        add(panel);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });
    }

    private void convertTemperature() {
        String inputText = inputField.getText();
        try {
            double temperature = Double.parseDouble(inputText);
            double result;
            String unit;

            if (celsiusToFahrenheitRadioButton.isSelected()) {
                result = (temperature * 9 / 5) + 32;
                unit = "Fahrenheit";
            } else {
                result = (temperature - 32) * 5 / 9;
                unit = "Celsius";
            }

            resultLabel.setText("Result: " + result + " " + unit);

            // Check for boiling and freezing points
            if ((celsiusToFahrenheitRadioButton.isSelected() && temperature > 100) ||
                (fahrenheitToCelsiusRadioButton.isSelected() && temperature > 212)) {
                getContentPane().setBackground(Color.RED);
            } else if ((celsiusToFahrenheitRadioButton.isSelected() && temperature < 0) ||
                (fahrenheitToCelsiusRadioButton.isSelected() && temperature < 32)) {
                getContentPane().setBackground(Color.BLUE);
            } else {
                getContentPane().setBackground(null); // Reset background color
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Error: Enter a valid number");
            getContentPane().setBackground(null); // Reset background color
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TemperatureConverterApp().setVisible(true);
            }
        });
    }
}
