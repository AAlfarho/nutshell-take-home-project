package com.nutshell.prep.resource;

import java.util.ListResourceBundle;

/**
 * Created by alfaro on 5/2/17.
 */
public class ErrorCodeResID extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                //TODO:We should have title/cause/action, for now lets proceed with just title.
                {ErrorCode.NULL_VALUE_ERR.name(), "Invalid value, null found."},
                {ErrorCode.UNKNOWN_ERR.name(),"Unknown error."}
        };
    }
}
