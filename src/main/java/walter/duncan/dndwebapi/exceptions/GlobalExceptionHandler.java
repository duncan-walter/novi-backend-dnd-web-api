package walter.duncan.dndwebapi.exceptions;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tools.jackson.databind.exc.MismatchedInputException;

@ControllerAdvice
public class GlobalExceptionHandler {
    // Spring boot wraps the MismatchedInputException in a HttpMessageNotReadableException exception. This took some time to figure out...
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<@NonNull ProblemDetail> typeMismatchExceptionHandler(HttpMessageNotReadableException exception) {
        var problemDetail = this.createProblemDetailBase(HttpStatus.BAD_REQUEST);
        problemDetail.setType(URI.create("urn:dnd:api:problem:type-mismatch"));

        if (exception.getMostSpecificCause() instanceof MismatchedInputException mismatchedInputException) {
            problemDetail.setDetail(this.getTypeMismatchMessage(mismatchedInputException));
        } else {
            return this.uncaughtExceptionHandler(exception);
        }

        return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<@NonNull ProblemDetail> resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
        var problemDetail = this.createProblemDetailBase(HttpStatus.NOT_FOUND);
        problemDetail.setType(URI.create("urn:dnd:api:problem:resource:not-found"));
        problemDetail.setDetail(exception.getMessage());

        return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
    }

    @ExceptionHandler(value = BusinessRuleViolationException.class)
    public ResponseEntity<@NonNull ProblemDetail> businessRuleViolationExceptionHandler(BusinessRuleViolationException exception) {
        var problemDetail = this.createProblemDetailBase(HttpStatus.UNPROCESSABLE_CONTENT);
        problemDetail.setType(URI.create("urn:dnd:api:problem:business-rule-violation"));
        problemDetail.setDetail("A business rule was violated.");

        Map<String, String> businessRuleErrors = new HashMap<>();
        businessRuleErrors.put(exception.getViolation().toString(), exception.getMessage());

        /* TODO: Look into collecting multiple business rule violations during one response.
            At the moment this can't be achieved yet since a BusinessRuleViolationException is thrown when it is found.
            Perhaps this can be fixed with the a notification collector?
         */
        Map<String, Map<String, String>> errors = new HashMap<>();
        errors.put("businessRuleViolation", businessRuleErrors);
        problemDetail.setProperty("errors", errors);

        return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<@NonNull ProblemDetail> uncaughtExceptionHandler(RuntimeException exception) {
        // TODO: Log exception stacktrace and other details somewhere so that the application can be improved upon.

        var problemDetail = this.createProblemDetailBase(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setType(URI.create("urn:dnd:api:problem:internal-server-error"));
        problemDetail.setDetail("An internal server error occurred while processing your request. Please contact support if the issue persists.");

        return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
    }

    private String getTypeMismatchMessage(MismatchedInputException exception) {
        String field = exception.getPath().isEmpty()
                ? "unknown"
                : exception.getPath().getFirst().getPropertyName();

        String expectedType = exception.getTargetType() != null
                ? exception.getTargetType().getSimpleName()
                : "unknown";

        return String.format("Field '%s' must be of type %s.", field, expectedType);
    }

    private ProblemDetail createProblemDetailBase(HttpStatus httpStatus) {
        var problemDetail = ProblemDetail.forStatus(httpStatus);
        problemDetail.setTitle(httpStatus.getReasonPhrase());
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }
}
