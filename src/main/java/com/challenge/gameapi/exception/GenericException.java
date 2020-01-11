package com.challenge.gameapi.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenericException extends RuntimeException {
    GenericException(String message) {
        super(message);
    }
}
