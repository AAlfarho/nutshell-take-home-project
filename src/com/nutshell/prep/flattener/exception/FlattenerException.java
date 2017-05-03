package com.nutshell.prep.flattener.exception;


import com.nutshell.prep.exception.*;
import com.nutshell.prep.flattener.resource.FlattenerErrorCode;
import com.nutshell.prep.resource.ErrorSeverity;

/**
 * Created by alfaro on 5/1/17.
 */
public class FlattenerException extends GenericAbstractException {
    private FlattenerErrorCode errorCode;

    public FlattenerException(FlattenerErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }

    public FlattenerException(FlattenerErrorCode errorCode, Throwable cause) {
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
