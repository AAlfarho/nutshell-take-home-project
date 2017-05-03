package com.nutshell.prep.exception;

import com.nutshell.prep.resource.ErrorCode;
import com.nutshell.prep.resource.ErrorSeverity;

/**
 * Created by alfaro on 5/2/17.
 */
public class GenericException extends GenericAbstractException {
    private ErrorCode errorCode;

    public GenericException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }

    public GenericException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
    }

    public ErrorSeverity getErrorSeverity(){
        return errorCode.getSeverity();
    }

    public String getErrorMessage(){
        return errorCode.toString();
    }

}
