package com.nutshell.prep.rest.client.util;

import com.nutshell.prep.rest.client.People;

import java.time.LocalDate;
import java.util.Comparator;

/**
 * Created by alfaro on 5/2/17.
 */
public class PeopleComparator implements Comparator<People> {
    @Override
    public int compare(People o1, People o2) {

        //we multiply by -1 because we want the most recent date first.
        return o1.getSignup_date().compareTo(o2.getSignup_date()) * -1;
    }
}
