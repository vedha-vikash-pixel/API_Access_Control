package com.vikash.API_Access_Control.Advice;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vikash.API_Access_Control.Utils.AuthenticationExceptionResponse;
import com.vikash.API_Access_Control.Utils.AuthenticationResponse;
import com.vikash.API_Access_Control.Utils.ForbiddenResponse;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class APIControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<AuthenticationExceptionResponse> processUnmergeException(final MethodArgumentNotValidException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new AuthenticationExceptionResponse(ex.getBindingResult().getFieldError().getDefaultMessage()));
	}
    
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ForbiddenResponse> handleAccessDeniedException(AccessDeniedException ex) {
    	return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ForbiddenResponse("This Page is Restricted to this user"));
    }
}
