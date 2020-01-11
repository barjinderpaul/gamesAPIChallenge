package com.challenge.gameapi.message;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Getter
@Setter
public class SuccessMessage {
    private int successCode;
    private String successMessage;
    private HttpStatus httpStatus;

    public SuccessMessage() {

    }

    public SuccessMessage(int successCode, String successMessage, HttpStatus httpStatus) {
        this.successCode = successCode;
        this.successMessage = successMessage;
        this.httpStatus = httpStatus;
    }

}
