package it.aruba.pdf_manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import it.aruba.pdf_manager.beans.ApiError;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({ Throwable.class })
    public ResponseEntity<ApiError> handlerValidationException2(Throwable e) {
        logger.error(e.getMessage(), e);
        return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
