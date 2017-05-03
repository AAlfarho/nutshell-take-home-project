package com.nutshell.prep.rest.client;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by alfaro on 5/2/17.
 */
public class People {
    private static Logger logger = Logger.getLogger(People.class.getName());
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private String name;
    private String email;
    private Date signup_date;

    public People(){

    }

    public People(String name, String email, String date){
        this.name = name;
        this.email = email;
        try {
            this.signup_date =  df.parse(date);
        } catch (ParseException e) {
            logger.log(Level.WARNING, "Invalid parse exception.", e);
        }
    }

    public People(String name, String email, Date date){
        this.name = name;
        this.email = email;
        this.signup_date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getSignup_date() {
        return signup_date;
    }

    public void setSignup_date(Date signup_date) {
        this.signup_date = signup_date;
    }

    public void setSignup_date(String signup_date) {
        try {
            this.signup_date = df.parse(signup_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String tName = this.getName();
        String tEmail = this.getEmail();
        Date tDate = this.getSignup_date();
        StringBuilder people = new StringBuilder();
        people.append(tName != null ? tName +" ":"");
        people.append(tEmail != null ? tEmail +" ":"null");
        people.append(tDate != null ? df.format(tDate):".");



        return people.toString();
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEquals = false;
        if(obj instanceof People){

            People peopleObj = (People) obj;
            String tName = this.getName();
            String oName = peopleObj.getName();
            String tEmail = this.getEmail();
            String oEmail = peopleObj.getEmail();
            Date tDate = this.getSignup_date();
            Date oDate = peopleObj.getSignup_date();

            isEquals = (tName == null ? oName == null : tName.equals(oName)) &&
                    (tEmail == null ? oEmail == null : tEmail.equals(oEmail)) &&
                    ((tDate == null ? oDate == null : tDate.equals(oDate)));
        }

        return isEquals;
    }
}
