package br.com.calto.awpag.api.exceptionhandler;

import br.com.calto.awpag.domain.exception.DomainException;
import java.net.URI;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  private final MessageSource messageSource;

  @SuppressWarnings("null")
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
    MethodArgumentNotValidException ex,
    HttpHeaders headers,
    HttpStatusCode status,
    WebRequest request
  ) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(status);
    problemDetail.setTitle("One or more fields are invalid");
    problemDetail.setType(URI.create("http://calto.com/errors/fields-invalid"));
    var fields = ex
      .getBindingResult()
      .getAllErrors()
      .stream()
      .collect(
        Collectors.toMap(
          errorField -> ((FieldError) errorField).getField(),
          errorMessage ->
            messageSource.getMessage(
              errorMessage,
              LocaleContextHolder.getLocale()
            )
        )
      );

    problemDetail.setProperty("fields", fields);
    return super.handleExceptionInternal(
      ex,
      problemDetail,
      headers,
      status,
      request
    );
  }

  @SuppressWarnings("null")
  @ExceptionHandler(DomainException.class)
  public ProblemDetail handleDomain(DomainException e) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(
      HttpStatus.BAD_REQUEST
    );
    problemDetail.setTitle(e.getMessage());
    problemDetail.setType(URI.create("http://calto.com/errors/domain-error"));

    return problemDetail;
  }

  @SuppressWarnings("null")
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ProblemDetail handleDataIntegrityViolation(
    DataIntegrityViolationException e
  ) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
    problemDetail.setTitle("Resource in use");
    problemDetail.setType(
      URI.create("http://calto.com/errors/resource-in-use")
    );

    return problemDetail;
  }
}
