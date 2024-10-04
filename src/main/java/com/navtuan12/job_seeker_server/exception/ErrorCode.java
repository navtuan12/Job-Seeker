package com.navtuan12.job_seeker_server.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999,"Uncategorized error"),
    USER_EXISTED(1001, "User existed"),
    FIRSTNAME_INVALID(1002, "Please enter your first name"),
    LASTNAME_INVALID(1003, "Please enter your last name"),
    EMAIL_INVALID(1004, "Please enter a valid email"),
    ;
    private int code;
    private String message;
}
