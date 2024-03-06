/**
* Author: William Holt
* Project Name: Project4 - InvalidTime Class
* Date: 05mar2024
* Description: The InvalidTime class extends the Exception class, specifically designed to handle exceptions related to invalid time input in the Project4 application. 
* It stores an error message and optionally the invalid time value that triggered the exception. This class is utilized within the Time class to validate time inputs 
* and ensure they conform to the expected format and logical constraints.
*/


public class InvalidTime extends Exception {
    private String invalidTimeValue;

    public InvalidTime(String message) {
        super(message);
    }

    public InvalidTime(String message, String timeValue) {
        super(message);
        this.invalidTimeValue = timeValue;
    }

    public String getInvalidTimeValue() {
        return invalidTimeValue;
    }
}
