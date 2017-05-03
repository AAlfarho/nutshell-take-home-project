package com.nutshell.prep.rest.client.resource;

import com.nutshell.prep.resource.ErrorSeverity;

import java.util.ResourceBundle;

/**
 * Created by alfaro on 5/2/17.
 */
public enum ClientErrorCode {
    NOT_FOUND_ERR(20001,ErrorSeverity.FATAL);


    private final int errorCode;
    private ErrorSeverity severity;
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("com.nutshell.prep.rest.client.resource.ClientErrorCodeResID");

    ClientErrorCode(int errorCode, ErrorSeverity severity){
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
