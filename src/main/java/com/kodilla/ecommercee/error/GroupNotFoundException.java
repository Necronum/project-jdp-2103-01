package com.kodilla.ecommercee.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Group not found!")
public class GroupNotFoundException extends Exception {
    public GroupNotFoundException(String message) {
        super(message);
    }
}
