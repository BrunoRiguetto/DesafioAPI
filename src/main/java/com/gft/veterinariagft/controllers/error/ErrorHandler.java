package com.gft.veterinariagft.controllers.error;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gft.veterinariagft.DTOs.error.ErroDetalhes;
import com.gft.veterinariagft.exceptions.DataIntegrityViolentionException;
import com.gft.veterinariagft.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ErrorHandler {
	
	@Autowired
	private MessageSource messageSource;
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request) {
        ErroDetalhes detalhes = new ErroDetalhes(0, 0, rnfe);
        detalhes.setTimeStamp(new Date().getTime());
        detalhes.setStatus(HttpStatus.NOT_FOUND.value());
        detalhes.setTitulo("Recurso não encontrado");
        detalhes.setDetalhes(rnfe.getMessage());

        return new ResponseEntity<>(detalhes, null, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException rnfe, HttpServletRequest request){
    	 List<ErroDetalhes> listaErrorDetalhes = new ArrayList<>();
    	 List<FieldError> fieldErrors = rnfe.getBindingResult().getFieldErrors();
    	 
    	 fieldErrors.forEach(
    			 e -> {
    				String detalheString = messageSource.getMessage(e, LocaleContextHolder.getLocale()); 
    				ErroDetalhes detalhes = new ErroDetalhes(0, 0, rnfe);
    				detalhes.setTitulo(e.getField());
    				detalhes.setDetalhes(detalheString);
    				detalhes.setTimeStamp(new Date().getTime());
    		        detalhes.setStatus( HttpStatus.BAD_REQUEST.value());
    				listaErrorDetalhes.add(detalhes);
    				
  			 });
    	 
    	 return new ResponseEntity<>(listaErrorDetalhes, null, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationExceptionn(AuthenticationException rnfe, HttpServletRequest request) {
        ErroDetalhes detalhes = new ErroDetalhes(0, 0, rnfe);
        detalhes.setTimeStamp(new Date().getTime());
        detalhes.setStatus(HttpStatus.UNAUTHORIZED.value());
        detalhes.setTitulo("Acesso não autorizado");
        detalhes.setDetalhes(rnfe.getMessage());

        return new ResponseEntity<>(detalhes, null, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(DataIntegrityViolentionException.class)
    public ResponseEntity<?>dataIntegrEntity (DataIntegrityViolentionException rnfe, HttpServletRequest request) {
        ErroDetalhes detalhes = new ErroDetalhes(0, 0, rnfe);
        detalhes.setTimeStamp(new Date().getTime());
        detalhes.setStatus(HttpStatus.FORBIDDEN.value());
        detalhes.setTitulo("Recurso com vinculos existentes");
        detalhes.setDetalhes(rnfe.getMessage());

        return new ResponseEntity<>(detalhes, null, HttpStatus.FORBIDDEN);
    }
}
