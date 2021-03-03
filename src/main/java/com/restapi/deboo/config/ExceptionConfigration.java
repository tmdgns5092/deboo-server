package com.restapi.deboo.config;

import com.restapi.deboo.vo.Message;
import com.restapi.deboo.vo.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class ExceptionConfigration {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();

        logger.error(errorMessage);
        Message message = new Message();
        message.setStatus(StatusEnum.BAD_REQUEST);
        message.setMessage("실패 코드");
        message.setData(errorMessage);

        return new ResponseEntity(message, HttpStatus.OK);
//        printExceptionMessage(errorMessage);
//        return new ResponseEntity<>(new BaseResult.Normal(INVALID_PARAMETER), HttpStatus.BAD_REQUEST);
    }
}
