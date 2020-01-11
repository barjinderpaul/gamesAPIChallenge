package com.challenge.gameapi.message;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Getter
@Setter
public class ErrorMessage {
    private int errorCode;
    private String errorMessage;
    private String documentation;
    private HttpStatus httpStatus;

    public ErrorMessage(){

    }

    public ErrorMessage(int errorCode, String errorMessage, String documentation, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.documentation = documentation;
        this.httpStatus = httpStatus;
    }

}
