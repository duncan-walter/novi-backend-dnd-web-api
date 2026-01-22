package walter.duncan.dndwebapi.exceptions;

import java.net.URI;
import java.time.Instant;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<@NonNull ProblemDetail> resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
        var problemDetail = this.createProblemDetailBase(HttpStatus.NOT_FOUND);
        problemDetail.setType(URI.create("urn:dnd:api:problem:resource:not-found"));
        problemDetail.setTitle("Resource Not Found");
        problemDetail.setDetail(exception.getMessage());

        return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<@NonNull ProblemDetail> uncaughtExceptionHandler(RuntimeException exception) {
        // TODO: Log exception stacktrace and other details somewhere so that the application can be improved upon.

        var problemDetail = this.createProblemDetailBase(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setType(URI.create("urn:vinyl-webshop:api:problem:internal-server-error"));
        problemDetail.setTitle("Internal Server Error");
        problemDetail.setDetail("An internal server error occurred while processing your request. Please contact support if the issue persists.");

        return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
    }

    private ProblemDetail createProblemDetailBase(HttpStatus httpStatus) {
        var problemDetail = ProblemDetail.forStatus(httpStatus);
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }
}
