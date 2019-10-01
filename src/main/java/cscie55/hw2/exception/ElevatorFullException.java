package cscie55.hw2.exception;

/**
 * @author Tofik Mussa
 * Custom Exception thrown when Elevator is out of capacity
 */
public class ElevatorFullException extends Exception {

    public ElevatorFullException(String message) {
        super(message);
    }
}
