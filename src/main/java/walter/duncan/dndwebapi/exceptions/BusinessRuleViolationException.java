package walter.duncan.dndwebapi.exceptions;

public class BusinessRuleViolationException extends RuntimeException {
    private final BusinessRuleViolation violation;

    public BusinessRuleViolationException(BusinessRuleViolation violation, String message) {
        super(message);
        this.violation = violation;
    }

    public BusinessRuleViolation getViolation() {
        return this.violation;
    }
}