/**
* Author: William Holt
* Project Name: Project4 - Time Class
* Date: 05mar2024
* Description: The Time class represents time in hours, minutes, and meridian (AM/PM) format,
* implementing Comparable for time comparison. It ensures the time is valid upon object creation,
* throwing an InvalidTime exception for any invalid input. This class supports comparison between
* Time objects and provides a string representation in standard time format. It's integral to Project4
* for handling and validating time data, especially when comparing time intervals or checking time inclusion within intervals.
*/


public class Time implements Comparable<Time> {
    private final int hours;
    private final int minutes;
    private final String meridian;

    public Time(int hours, int minutes, String meridian) throws InvalidTime {
        if (hours < 1 || hours > 12 || minutes < 0 || minutes > 59 || (!meridian.equals("AM") && !meridian.equals("PM"))) {
            throw new InvalidTime("Invalid time input.");
        }
        this.hours = hours;
        this.minutes = minutes;
        this.meridian = meridian;
    }

    public Time(String timeStr) throws InvalidTime {
        try {
            String[] parts = timeStr.split(" ");
            String[] timeParts = parts[0].split(":");
            this.hours = Integer.parseInt(timeParts[0]);
            this.minutes = Integer.parseInt(timeParts[1]);
            this.meridian = parts[1].toUpperCase();

            if (this.hours < 1 || this.hours > 12 || this.minutes < 0 || this.minutes > 59 || 
                (!this.meridian.equals("AM") && !this.meridian.equals("PM"))) {
                throw new InvalidTime("Invalid time input.");
            }
        } catch (Exception e) {
            throw new InvalidTime("Invalid time string format.");
        }
    }


    @Override
    public int compareTo(Time other) {
        if (!this.meridian.equals(other.meridian)) {
            return this.meridian.compareTo(other.meridian);
        }
        if (this.hours != other.hours) {
            return Integer.compare(this.hours, other.hours);
        }
        return Integer.compare(this.minutes, other.minutes);
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d %s", hours, minutes, meridian);
    }
}
