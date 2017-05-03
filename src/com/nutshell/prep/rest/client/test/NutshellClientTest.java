package com.nutshell.prep.rest.client.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Created by alfaro on 5/3/17.
 */
public class NutshellClientTest {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(NutshellClientUnitTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}
