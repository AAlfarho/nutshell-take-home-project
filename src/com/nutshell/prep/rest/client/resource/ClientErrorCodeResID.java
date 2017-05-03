package com.nutshell.prep.rest.client.resource;

import java.util.ListResourceBundle;

/**
 * Created by alfaro on 5/2/17.
 */
public class ClientErrorCodeResID extends ListResourceBundle{
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                //TODO:We should have title/cause/action, for now lets proceed with just title.
                {ClientErrorCode.NOT_FOUND_ERR.name(), "Resource was not found."},
        };
    }
}
