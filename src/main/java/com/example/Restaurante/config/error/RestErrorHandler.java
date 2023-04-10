package com.example.Restaurante.config.error;

import com.example.Restaurante.application.RestauranteApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@ControllerAdvice
public class RestErrorHandler {

    private static final Logger LOG = LoggerFactory.getLogger(RestauranteApplication.class);
    private final MessageSource messageSource;

    @Autowired
    public RestErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private ValidationErrorDTO processFieldError(List<FieldError> fieldErrors){
        ValidationErrorDTO dto = new ValidationErrorDTO();

        for(FieldError fieldError: fieldErrors){
            String localeErrorMessage = resolveLocalizedErrorMessage(fieldError);
            dto.addFieldError(fieldError.getField(), localeErrorMessage);
        }
        return dto;
    }

    private String resolveLocalizedErrorMessage(FieldError fieldError){
        Locale currentLocale = LocaleContextHolder.getLocale();
        String errorMessage = messageSource.getMessage(fieldError, currentLocale);

        if(errorMessage.equals(fieldError.getDefaultMessage())){
            String[] fieldErrorCodes = fieldError.getCodes();
            errorMessage = fieldErrorCodes[0];
        }
        return errorMessage;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ValidationErrorDTO handleValidationExceptions( MethodArgumentNotValidException ex) {
        LOG.info("Informacion de errores");
        LOG.error(ex.getMessage());
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        return processFieldError(fieldErrors);
    }

    @ExceptionHandler(RestException.class)
    @ResponseBody
    public RestExceptionInfo exceptionHandler(HttpServletResponse response, RestException ex) {
        LOG.error(ex.getInfo().name() + ": " + ex.getInfo().getMessage());
        String message = messageSource.getMessage(ex.getInfo().getMessage(), null, LocaleContextHolder.getLocale());
        response.setStatus(ex.getInfo().getHttpStatus().value());
        return new RestExceptionInfo(ex.getInfo().getHttpStatus(), ex.getInfo().getId(), message);
    }
}
