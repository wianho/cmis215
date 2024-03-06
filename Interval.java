/**
* Author: William Holt
* Project Name: Project4 - Interval Class
* Date: 05mar2024
* Description: The Interval class represents a generic interval with start and end points,
* defined for any type that implements Comparable. It offers methods to check if an element
* is within the interval, if another interval is a subinterval, or if it overlaps with another interval.
* The class enforces the start to be less than or equal to the end upon creation and is immutable. It's a
* key component in Project4 for evaluating relationships between time intervals.
*/


public class Interval<T extends Comparable<T>> {
    private final T start;
    private final T end;

    public Interval(T start, T end) {
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException("Start cannot be greater than end.");
        }
        this.start = start;
        this.end = end;
    }

    public boolean within(T element) {
        return (element.compareTo(start) >= 0) && (element.compareTo(end) <= 0);
    }

    public boolean subinterval(Interval<T> other) {
        return (this.within(other.start) && this.within(other.end));
    }

    public boolean overlaps(Interval<T> other) {
        return (this.within(other.start) || this.within(other.end) || other.within(this.start) || other.within(this.end));
    }

    @Override
    public String toString() {
        return "Interval: [" + start + ", " + end + "]";
    }
}
