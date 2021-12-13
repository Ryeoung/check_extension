package com.flow.extension.advice;

import com.flow.extension.enums.ResponseStatus;
import com.flow.extension.exceptions.ExtensionDuplicateException;
import com.flow.extension.exceptions.ExtensionNotFoundException;
import com.flow.extension.exceptions.MaxDataOfCustomExtensionException;
import com.flow.extension.exceptions.NoValueOfExtensionTypeException;
import com.flow.extension.response.ResponseMessage;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@RestControllerAdvice
public class ExtensionControllerAdvice {
    /**
     * @param request Http 요쳥 객체
     * @param exception 에러 객체
     * @return ResponseEntity<ResponseMessage>
     *     사용자 지정 에러 객체를 처리
     */
    @ExceptionHandler({ExtensionDuplicateException.class, ExtensionNotFoundException.class,
            NoValueOfExtensionTypeException.class, MaxDataOfCustomExtensionException.class})
    public ResponseEntity<ResponseMessage> handlerCustomException(HttpServletRequest request, Exception exception) {
        ResponseMessage rm = null;
        if( exception instanceof ExtensionDuplicateException) {
            rm = new ResponseMessage(ResponseStatus.EXTENSION_EXISIT);
        } else if(exception instanceof ExtensionNotFoundException) {
            rm = new ResponseMessage(ResponseStatus.EXTENSION_NOT_FOUND);
        } else if(exception instanceof NoValueOfExtensionTypeException) {
            rm = new ResponseMessage(ResponseStatus.NO_VALUE_OF_EXTENSION_TYPE);
        } else {
            rm = new ResponseMessage(ResponseStatus.MAX_DATA_OF_CUSTOM_ERROR);

        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(rm);
    }

    /**
     * @param request Http 요쳥 객체
     * @param exception 에러 객체
     * @return ResponseEntity<ResponseMessage>
     *     영속성 관련  에러 객체를 처리
     */
    @ExceptionHandler({PersistenceException.class})
    public ResponseEntity<ResponseMessage> handlerJpaException(HttpServletRequest request, Exception exception) {
        ResponseMessage rm = new ResponseMessage(ResponseStatus.DB_ACCESS_FAIL_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(rm);
    }

    /**
     * @param request Http 요쳥 객체
     * @param exception 에러 객체
     * @return ResponseEntity<ResponseMessage>
     *     타당성 검사 관련 에러 체리
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ResponseMessage> handlerValidateException(HttpServletRequest request, Exception exception) {
        ResponseMessage rm = new ResponseMessage(ResponseStatus.NO_VALID_ARGUMENT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rm);
    }
    /**
     * @param request Http 요쳥 객체
     * @param exception 에러 객체
     * @return ResponseEntity<ResponseMessage>
     *     나머지 Exception 에러처리
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage> handleException(HttpServletRequest request, Exception exception) {
        ResponseMessage rm = new ResponseMessage(ResponseStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(rm);
    }

}
