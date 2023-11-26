package ms.mmontilla.registry.exception;

import ms.mmontilla.registry.user.presentation.dto.Error;
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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        ObjectError objectError = ex.getBindingResult().getAllErrors().get(0);
        return new ResponseEntity<Object>(new Error(objectError.getDefaultMessage()),
                                         headers,
                                         HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers,
                                                                   HttpStatus status,
                                                                   WebRequest request) {
        return new ResponseEntity<Object>(new Error("No existe contenido en el recurso solicitado"),
                headers,
                HttpStatus.NO_CONTENT);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {
        return new ResponseEntity<Object>(new Error("Ocurrio un inconveniente procesando la solicitud"),
                headers,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {
            IndexOutOfBoundsException.class,
            NullPointerException.class,
            Exception.class
    })
    public ResponseEntity<Object> handleAnyException(Exception ex) {
        return new ResponseEntity<Object>(new Error("Ocurrio un inconveniente procesando la soilicitud"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
