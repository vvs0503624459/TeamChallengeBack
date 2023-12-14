package team.challenge.MobileStore.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<ApiError> handleModelAlreadyExistException(ModelAlreadyExistException e){
        logger.error("Handle ModelAlreadyExistException. Message = {}", e.getMessage());
        return new ResponseEntity<>(new ApiError(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleModelNotFoundException(ModelNotFoundException e){
        logger.error("Handle ModelNotFoundException. Message = {}", e.getMessage());
        return new ResponseEntity<>(new ApiError(e.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ApiError> handelAuthException(AuthException e){
        logger.error("Handle AuthException. Message = {}", e.getMessage());
        return new ResponseEntity<>(new ApiError(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
