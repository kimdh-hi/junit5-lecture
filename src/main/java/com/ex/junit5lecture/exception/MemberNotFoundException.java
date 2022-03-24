package com.ex.junit5lecture.exception;

import org.omg.SendingContext.RunTime;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException(String message) {
        super(message);
    }
}
