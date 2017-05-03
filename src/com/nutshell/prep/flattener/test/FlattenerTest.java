package com.nutshell.prep.flattener.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


/**
 * Created by alfaro on 4/25/17.
 */
public class FlattenerTest {
    public static void main(String[] args) {

        Result result = JUnitCore.runClasses(FlattenerUnitTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }

}
