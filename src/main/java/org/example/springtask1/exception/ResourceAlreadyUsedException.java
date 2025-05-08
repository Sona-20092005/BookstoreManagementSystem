package org.example.springtask1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyUsedException extends BaseException {

    public ResourceAlreadyUsedException(String message) {
        super(message);
    }
}
