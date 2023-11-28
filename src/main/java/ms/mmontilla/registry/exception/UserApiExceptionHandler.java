package ms.mmontilla.registry.exception;

import ms.mmontilla.registry.user.domain.exception.InvalidPasswordFormatException;
import ms.mmontilla.registry.user.presentation.dto.Error;
import ms.mmontilla.registry.user.repository.exception.DataSourceTierException;
import ms.mmontilla.registry.user.repository.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${app.user.validations.regexp.message}")
    private String passwordRegexpMessage;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        ObjectError objectError = ex.getBindingResult().getAllErrors().get(0);
        return new ResponseEntity(new Error(objectError.getDefaultMessage()),
                headers,
                HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers,
                                                                   HttpStatus status,
                                                                   WebRequest request) {
        return new ResponseEntity<>(new Error("No existe contenido en el recurso solicitado"),
                headers,
                HttpStatus.NO_CONTENT);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {
        return new ResponseEntity<>(new Error("Ocurrio un inconveniente procesando la solicitud"),
                headers,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {
            DataSourceTierException.class
    })
    public ResponseEntity<Object> handleDataSourceException(Exception ex) {
        return new ResponseEntity<>(new Error("Ocurrio un inconveniente en la data"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {
            UserAlreadyExistsException.class
    })
    public ResponseEntity<Object> handleUserAlreadyExistsException(Exception ex) {
        return new ResponseEntity<>(new Error("El usuario que esta tratando de crear ya existe"),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {
            InvalidPasswordFormatException.class
    })
    public ResponseEntity<Object> handleInvalidPasswordFormatException(Exception ex) {
        return new ResponseEntity<>(new Error(passwordRegexpMessage),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {
            IndexOutOfBoundsException.class,
            NullPointerException.class,
            Exception.class
    })
    public ResponseEntity<Object> handleAnyException(Exception ex) {
        return new ResponseEntity<>(new Error("Ocurrio un inconveniente procesando la soilicitud"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
