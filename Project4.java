/**
* Author: William Holt
* Project Name: Project4
* Date: 05mar2024
* Description: This class creates a GUI application that allows users to input time intervals and a specific time to check against those intervals. 
* The application provides functionality to compare intervals and check if a given time falls within those intervals.
* It utilizes the Time and Interval classes to handle time data and interval logic, respectively.
*/


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Project4 extends JFrame {
    private JTextField interval1Start, interval1End, interval2Start, interval2End, timeInput;
    private JButton compareIntervalsBtn, checkTimeBtn;
    private JLabel resultLabel;

    public Project4() {
        createGUI();
    }

    private void createGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Main panel with BoxLayout for stacking elements vertically
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        // Panel for interval inputs with GridLayout
        JPanel intervalsPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        intervalsPanel.add(new JLabel("")); // Placeholder for grid alignment
        intervalsPanel.add(new JLabel("Start Time"));
        intervalsPanel.add(new JLabel("End Time"));
        
        interval1Start = new JTextField("10:30 AM");
        interval1End = new JTextField("12:30 PM");
        interval2Start = new JTextField("11:05 AM");
        interval2End = new JTextField("1:00 PM");
        
        intervalsPanel.add(new JLabel("Time Interval 1"));
        intervalsPanel.add(interval1Start);
        intervalsPanel.add(interval1End);
        intervalsPanel.add(new JLabel("Time Interval 2"));
        intervalsPanel.add(interval2Start);
        intervalsPanel.add(interval2End);
        
        // Add intervals panel to the main panel
        mainPanel.add(intervalsPanel);
        
        // Button for comparing intervals
        compareIntervalsBtn = new JButton("Compare Intervals");
        compareIntervalsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        compareIntervalsBtn.addActionListener(this::compareIntervalsAction);
        mainPanel.add(compareIntervalsBtn);
        
        // Panel for checking time
        JPanel checkTimePanel = new JPanel();
        checkTimePanel.setLayout(new BoxLayout(checkTimePanel, BoxLayout.X_AXIS));
        checkTimePanel.add(new JLabel("Time to Check"));
        timeInput = new JTextField(10);
        checkTimePanel.add(timeInput);
        
        // Button for checking time
        checkTimeBtn = new JButton("Check Time");
        checkTimeBtn.addActionListener(this::checkTimeAction);
        checkTimePanel.add(checkTimeBtn);
        
        // Add check time panel to the main panel
        mainPanel.add(checkTimePanel);
        
        // Label for displaying results
        resultLabel = new JLabel("Result will be shown here");
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(resultLabel);
        
        // Add main panel to the frame
        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }



    private void compareIntervalsAction(ActionEvent e) {
        try {
            Time start1 = new Time(interval1Start.getText());
            Time end1 = new Time(interval1End.getText());
            Interval<Time> interval1 = new Interval<>(start1, end1);
            
            Time start2 = new Time(interval2Start.getText());
            Time end2 = new Time(interval2End.getText());
            Interval<Time> interval2 = new Interval<>(start2, end2);
            
            if (interval1.subinterval(interval2)) {
                resultLabel.setText("Interval 1 is a sub-interval of interval 2");
            } else if (interval2.subinterval(interval1)) {
                resultLabel.setText("Interval 2 is a sub-interval of interval 1");
            } else if (interval1.overlaps(interval2)) {
                resultLabel.setText("The intervals overlap");
            } else {
                resultLabel.setText("The intervals are disjoint");
            }
        } catch (InvalidTime ex) {
            resultLabel.setText("Invalid time entered: " + ex.getMessage());
        }
    }

    private void checkTimeAction(ActionEvent e) {
        try {
            Time timeToCheck = new Time(timeInput.getText());
            Time start1 = new Time(interval1Start.getText());
            Time end1 = new Time(interval1End.getText());
            Interval<Time> interval1 = new Interval<>(start1, end1);
            
            Time start2 = new Time(interval2Start.getText());
            Time end2 = new Time(interval2End.getText());
            Interval<Time> interval2 = new Interval<>(start2, end2);
            
            boolean isInInterval1 = interval1.within(timeToCheck);
            boolean isInInterval2 = interval2.within(timeToCheck);
            
            if (isInInterval1 && isInInterval2) {
                resultLabel.setText("Both intervals contain the time " + timeToCheck);
            } else if (isInInterval1) {
                resultLabel.setText("Only interval 1 contains the time " + timeToCheck);
            } else if (isInInterval2) {
                resultLabel.setText("Only interval 2 contains the time " + timeToCheck);
            } else {
                resultLabel.setText("Neither interval contains the time " + timeToCheck);
            }
        } catch (InvalidTime ex) {
            resultLabel.setText("Invalid time entered: " + ex.getMessage());
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Project4().setVisible(true));
    }
}


