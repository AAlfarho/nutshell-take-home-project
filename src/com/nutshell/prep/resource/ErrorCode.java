package com.nutshell.prep.resource;

import java.util.ResourceBundle;

/**
 * Created by alfaro on 5/2/17.
 */
public enum ErrorCode {
    NULL_VALUE_ERR(0001, ErrorSeverity.FATAL),
    UNKNOWN_ERR(99999, ErrorSeverity.FATAL);


    private final int errorCode;
    private ErrorSeverity severity;
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("com.nutshell.prep.resource.ErrorCodeResID");

    ErrorCode(int errorCode, ErrorSeverity severity){
        this.errorCode = errorCode;
        this.severity = severity;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public ErrorSeverity getSeverity() {
        return severity;
    }

    @Override
    public String toString(){
        return resourceBundle.getString(this.name());
    }
}
