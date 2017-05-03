package com.nutshell.prep.exception;

import com.nutshell.prep.resource.ErrorCode;
import com.nutshell.prep.resource.ErrorSeverity;
import com.nutshell.prep.util.LoggerUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by alfaro on 5/2/17.
 */
public class ExceptionManager {
    private static Logger logger = Logger.getLogger(ExceptionManager.class.getName());

    public static void handleException(GenericAbstractException ex){
        if(ex != null){
            logger.log(LoggerUtil.getLogginLevel(ex.getErrorSeverity()), ex.getErrorMessage());
            handleException(ex.getErrorSeverity());
        } else {
            logger.log(Level.WARNING, ErrorCode.NULL_VALUE_ERR.toString());
        }
    }

    public static void handleException(ErrorSeverity errorSeverity){
        if(errorSeverity != null){
            if(errorSeverity == ErrorSeverity.FATAL){
                //shutdown the app
                //TODO: this should include pre shutdown hook actions, like saving state, cleanup, etc.,
                // most likely this shutdown should be propagated so that the driver
                //itself cleans anything that would require cleanup.
                System.exit(1);
            }
        } else {
            logger.log(Level.WARNING, ErrorCode.NULL_VALUE_ERR.toString());
        }

    }
}
