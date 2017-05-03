package com.nutshell.prep.exception;

import com.nutshell.prep.resource.ErrorCode;
import com.nutshell.prep.resource.ErrorSeverity;

/**
 * Created by alfaro on 5/2/17.
 */
public abstract class GenericAbstractException extends Exception{

    public GenericAbstractException(Throwable cause){
        this(ErrorCode.UNKNOWN_ERR, cause);
    }

    public GenericAbstractException(Enum errorCode){
        super(errorCode.toString());
    }

    public GenericAbstractException(Enum errorCode, Throwable cause){
        super(errorCode.toString(),cause);
    }

    public abstract ErrorSeverity getErrorSeverity();
    public abstract String getErrorMessage();
}
