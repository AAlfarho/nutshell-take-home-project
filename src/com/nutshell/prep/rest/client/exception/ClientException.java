package com.nutshell.prep.rest.client.exception;

import com.nutshell.prep.exception.GenericAbstractException;
import com.nutshell.prep.resource.ErrorSeverity;
import com.nutshell.prep.rest.client.resource.ClientErrorCode;

/**
 * Created by alfaro on 5/2/17.
 */
public class ClientException extends GenericAbstractException{
    private ClientErrorCode errorCode;

    public ClientException(ClientErrorCode errorCode){
        super(errorCode);
        this.errorCode = errorCode;
    }

    public ClientException(ClientErrorCode errorCode, Throwable cause){
        super (errorCode, cause);
        this.errorCode = errorCode;
    }


    @Override
    public ErrorSeverity getErrorSeverity() {
        return errorCode.getSeverity();
    }

    @Override
    public String getErrorMessage() {
        return errorCode.toString();
    }
}
