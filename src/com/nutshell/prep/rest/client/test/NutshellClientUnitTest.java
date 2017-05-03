package com.nutshell.prep.rest.client.test;

import com.nutshell.prep.rest.client.People;
import com.nutshell.prep.rest.client.driver.NutshellClient;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alfaro on 5/3/17.
 */
public class NutshellClientUnitTest {
    People dan = new People("Dan Bush", "danbush@example.com", "2010-05-28");
    People matt = new People("Matt Jackson", "mattjackson@example.com", "2009-04-24");
    People guy = new People("Guy Adams", "guyadams@example.com", "2000-01-24");

    People ian = new People("Ian Garfield", "iangarfield@example.com", "2014-11-27");
    People andy = new People("Andy Monroe", "andymonroe@example.com", "2011-7-23");

    @Test
    public void testFirstPageOnlyRetrieval(){
        NutshellClient client = NutshellClient.getInstance();
        List<People> latestRegisteredPeoplePage1 = client.getLatestRegisteredPeople(5,1);


        List<People> expectedResultForPageOne = new LinkedList<>();



        expectedResultForPageOne.add(dan);
        expectedResultForPageOne.add(matt);
        expectedResultForPageOne.add(guy);

        Assert.assertEquals(expectedResultForPageOne, latestRegisteredPeoplePage1);
        client.destroy();


    }

    @Test
    public void testUptToSecondPage(){
        NutshellClient client = NutshellClient.getInstance();
        List<People> latestRegisteredPeoplePage2 = client.getLatestRegisteredPeople(5,2);
        List<People> expectedResultUpToTwo = new LinkedList<>();
        expectedResultUpToTwo.add(ian);
        expectedResultUpToTwo.add(andy);
        expectedResultUpToTwo.add(dan);
        expectedResultUpToTwo.add(matt);
        expectedResultUpToTwo.add(guy);
        Assert.assertEquals(expectedResultUpToTwo, latestRegisteredPeoplePage2);
    }
}
