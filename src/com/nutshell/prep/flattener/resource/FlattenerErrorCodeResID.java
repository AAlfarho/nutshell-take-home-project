package com.nutshell.prep.flattener.resource;

import java.util.ListResourceBundle;

/**
 * Created by alfaro on 5/1/17.
 */
public class FlattenerErrorCodeResID extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                //TODO:We should have title/cause/action, for now lets proceed with just title.
                {FlattenerErrorCode.UNBALANCED_BRACKETS_ERR.name(), "Invalid input, unbalanced brackets."},
        };
    }
}
