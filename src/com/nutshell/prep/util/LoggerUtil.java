package com.nutshell.prep.util;

import com.nutshell.prep.resource.ErrorCode;
import com.nutshell.prep.resource.ErrorSeverity;

import java.util.logging.Level;

/**
 * Created by alfaro on 5/2/17.
 */
public class LoggerUtil {
    public static Level getLogginLevel(ErrorSeverity errorSeverity){
        Level level = Level.INFO;
        switch (errorSeverity) {
            case WARN:
                level = Level.WARNING;
                break;
            case FATAL:
                level = Level.SEVERE;
                break;
        }

        return level;
    }
}
