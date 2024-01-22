package com.arnav.temp;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TemperatureConverterApp extends JFrame {
    private JTextField inputField;
    private JSlider temperatureSlider;
    private JRadioButton celsiusToFahrenheitRadioButton;
    private JRadioButton fahrenheitToCelsiusRadioButton;
    private JButton convertButton;
    private JLabel resultLabel;
    private JPanel contentPanel;  // A separate panel to change its background color

    public TemperatureConverterApp() {
        setTitle("Temperature Converter");
        setSize(700, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        inputField = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPanel.add(inputField, gbc);

        temperatureSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        temperatureSlider.setMajorTickSpacing(10);
        temperatureSlider.setMinorTickSpacing(1);
        temperatureSlider.setPaintTicks(true);
        temperatureSlider.setPaintLabels(true);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        contentPanel.add(temperatureSlider, gbc);

        JPanel radioButtonsPanel = new JPanel();
        radioButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        celsiusToFahrenheitRadioButton = new JRadioButton("Celsius to Fahrenheit");
        fahrenheitToCelsiusRadioButton = new JRadioButton("Fahrenheit to Celsius");
        
        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(celsiusToFahrenheitRadioButton);
        radioButtonGroup.add(fahrenheitToCelsiusRadioButton);
        
        celsiusToFahrenheitRadioButton.setSelected(true); // Set default selection
        temperatureSlider.setValue(0); // Set default temperature to 0°C
        inputField.setText("0"); 
        radioButtonsPanel.add(celsiusToFahrenheitRadioButton);
        radioButtonsPanel.add(fahrenheitToCelsiusRadioButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        contentPanel.add(radioButtonsPanel, gbc);

        convertButton = new JButton("Convert");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        contentPanel.add(convertButton, gbc);

        resultLabel = new JLabel();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        contentPanel.add(resultLabel, gbc);

        // Add the content panel to the JFrame's content pane
        getContentPane().add(contentPanel);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });

        temperatureSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                inputField.setText(Integer.toString(temperatureSlider.getValue()));
                convertTemperature();
            }
        });
    }

	private void convertTemperature() {
		//int temperature = temperatureSlider.getValue();
		String inputText = inputField.getText();
		try {
			double temperature = Double.parseDouble(inputText);
			Double result = null;
			String unit = null;

			if (celsiusToFahrenheitRadioButton.isSelected()) {
				result = (double) ((temperature * 9 / 5) + 32);
				result = Math.ceil(result);
				unit = "Fahrenheit";
			} else if (fahrenheitToCelsiusRadioButton.isSelected()) {
				result = (double) ((temperature - 32) * 5 / 9);
				result = Math.ceil(result);
				unit = "Celsius";
			}

			resultLabel.setText("Result: " + result + " " + unit);

			// Check for boiling and freezing points
			if ((celsiusToFahrenheitRadioButton.isSelected() && temperature >= 100)
					|| (fahrenheitToCelsiusRadioButton.isSelected() && temperature >= 212)) {
				contentPanel.setBackground(Color.RED);
			} else if ((celsiusToFahrenheitRadioButton.isSelected() && temperature <= 0)
					|| (fahrenheitToCelsiusRadioButton.isSelected() && temperature <= 32)) {
				contentPanel.setBackground(Color.BLUE);
			} else {
				contentPanel.setBackground(null); // Reset background color
			}
		} catch (NumberFormatException ex) {
			resultLabel.setText("Error: Enter a valid number");
			contentPanel.setBackground(null); // Reset background color
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
