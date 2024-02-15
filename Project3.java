import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project3 extends JFrame {
    private JTextField distanceField, gasolineCostField, gasMileageField, hotelCostField, foodCostField, daysField, attractionsCostField;
    private JComboBox<String> distanceUnits, gasolineCostUnits, gasMileageUnits;
    private JLabel totalCostLabel;
    
    public Project3() {
        createGUI();
    }
    
    private void createGUI() {
        setTitle("Road Trip Cost Estimator");
        setLayout(new GridLayout(0, 2));
        
        distanceField = new JTextField("0"); // Set default value to "0"
        gasolineCostField = new JTextField("0"); // Set default value to "0"
        gasMileageField = new JTextField("0"); // Set default value to "0"
        hotelCostField = new JTextField("0"); // Set default value to "0"
        foodCostField = new JTextField("0"); // Set default value to "0"
        daysField = new JTextField("0"); // Set default value to "0"
        attractionsCostField = new JTextField("0"); // Set default value to "0"

        distanceUnits = new JComboBox<>(new String[]{"Miles", "Kilometers"});
        gasolineCostUnits = new JComboBox<>(new String[]{"Dollars per Gallon", "Dollars per Liter"});
        gasMileageUnits = new JComboBox<>(new String[]{"Miles per Gallon", "Kilometers per Liter"});

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> calculateTotalCost());

        addComponents("Distance:", distanceField, distanceUnits);
        addComponents("Gasoline Cost:", gasolineCostField, gasolineCostUnits);
        addComponents("Gas Mileage:", gasMileageField, gasMileageUnits);
        addComponents("Hotel Cost:", hotelCostField, null);
        addComponents("Food Cost:", foodCostField, null);
        addComponents("Number of Days:", daysField, null);
        addComponents("Attractions Cost:", attractionsCostField, null);

        add(calculateButton);

        totalCostLabel = new JLabel("Total Cost: ");
        add(totalCostLabel);
        
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private void addComponents(String label, JTextField textField, JComboBox<String> comboBox) {
        add(new JLabel(label));
        add(textField);
        if (comboBox != null) {
            add(comboBox);
        }
    }
    
    private void calculateTotalCost() {
        try {
            double distance = Double.parseDouble(distanceField.getText());
            double gasolineCost = Double.parseDouble(gasolineCostField.getText());
            double gasMileage = Double.parseDouble(gasMileageField.getText());
            double hotelCost = Double.parseDouble(hotelCostField.getText());
            double foodCost = Double.parseDouble(foodCostField.getText());
            int days = Integer.parseInt(daysField.getText());
            double attractionsCost = Double.parseDouble(attractionsCostField.getText());

            // Determine unit flags based on user selection
            boolean isDistanceInMiles = distanceUnits.getSelectedItem().equals("Miles");
            boolean isGasolineCostPerGallon = gasolineCostUnits.getSelectedItem().equals("Dollars per Gallon");
            boolean isGasMileageInMpg = gasMileageUnits.getSelectedItem().equals("Miles per Gallon");

            // Print statements to check input retrieval (optional, for debugging)
            System.out.println("Distance: " + distance);
            System.out.println("Gasoline Cost: " + gasolineCost);
            System.out.println("Gas Mileage: " + gasMileage);
            System.out.println("Hotel Cost: " + hotelCost);
            System.out.println("Food Cost: " + foodCost);
            System.out.println("Days: " + days);
            System.out.println("Attractions Cost: " + attractionsCost);

            // Call the method to calculate and display total cost
            calculateAndDisplayTotalCost(
                distance, gasolineCost, gasMileage, hotelCost, foodCost, days, attractionsCost,
                isDistanceInMiles, isGasolineCostPerGallon, isGasMileageInMpg
            );
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void calculateAndDisplayTotalCost(double distance, double gasolineCost, double gasMileage, double hotelCost, double foodCost, int days, double attractionsCost, boolean isDistanceInMiles, boolean isGasolineCostPerGallon, boolean isGasMileageInMpg) {
    	TripCost tripCost = new TripCost(
    		    distance, gasolineCost, gasMileage, hotelCost, foodCost, days, attractionsCost,
    		    isDistanceInMiles, isGasolineCostPerGallon, isGasMileageInMpg
    		);
        double totalCost = tripCost.calculateTotalCost(); // Make sure this method exists in TripCost
        
        totalCostLabel.setText("Total Cost: $" + String.format("%.2f", totalCost));
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Project3::new);
    }
}
