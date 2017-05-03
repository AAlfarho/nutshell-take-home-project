package com.nutshell.prep.flattener.resource;

import com.nutshell.prep.resource.ErrorSeverity;

import java.util.ResourceBundle;

/**
 * Created by alfaro on 4/28/17.
 */
public enum FlattenerErrorCode {

    //Input related errors 10001
    UNBALANCED_BRACKETS_ERR(10001, ErrorSeverity.FATAL),

    UNKNOWN_ERR(19999, ErrorSeverity.FATAL);

    private final int errorCode;
    private ErrorSeverity severity;
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("com.nutshell.prep.flattener.resource.FlattenerErrorCodeResID");

    FlattenerErrorCode(int errorCode, ErrorSeverity severity){
        this.errorCode = errorCode;
        this.severity = severity;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public ErrorSeverity getSeverity() {
        return severity;
    }

    public String toString(){
        return resourceBundle.getString(this.name());
    }
}



