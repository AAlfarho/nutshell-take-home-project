package com.nutshell.prep.rest.client.driver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nutshell.prep.exception.GenericAbstractException;
import com.nutshell.prep.exception.GenericException;
import com.nutshell.prep.resource.ErrorCode;
import com.nutshell.prep.rest.client.People;
import com.nutshell.prep.rest.client.exception.ClientException;
import com.nutshell.prep.rest.client.resource.ClientErrorCode;
import com.nutshell.prep.rest.client.util.PeopleComparator;
import com.nutshell.prep.util.LoggerUtil;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.logging.Logger;


/**
 * Created by alfaro on 5/2/17.
 */
public class NutshellClient {
    private static Logger logger = Logger.getLogger(NutshellClient.class.getName());
    private static NutshellClient nutshellClient;
    private static Client client;
    private static String NUTSHELL_BASE_URL = "http://join.nutshell.com/people/";

    private NutshellClient(){
        client = ClientBuilder.newClient();
    }

    public static NutshellClient getInstance(){
        if(nutshellClient == null){
            nutshellClient = new NutshellClient();
        }
        return nutshellClient;
    }

    public void destroy() {
        client.close();
    }

    public List<People> getPeopleOnPage(int page) throws GenericAbstractException {
        WebTarget webTarget = client.target(NUTSHELL_BASE_URL+page+"/");
        Response response = webTarget.request().accept(MediaType.APPLICATION_JSON).get();
        List<People> peopleList = new LinkedList<>();
        try {
        if(response != null){
            if(response.getStatus() == Response.Status.OK.getStatusCode()){
                    peopleList = response.readEntity(new GenericType<List<People>>() {});
            } else {
                throw new ClientException(ClientErrorCode.NOT_FOUND_ERR);
            }

        } else {
            throw new GenericException(ErrorCode.NULL_VALUE_ERR);
        }
        } finally {
            response.close();
        }
        return peopleList;
    }
    private List<People> getRegisteredPeopleUpToPage(Integer checkUntilPage){
        //iterate through all pages until an empty response.
        List<People> peopleAtPage = new LinkedList<>();
        List<People> peopleUntilPage = new LinkedList<>();

        int pageIndex = 1;
        do {
            try {
                peopleAtPage = getPeopleOnPage(pageIndex++);
                //people will never be null, but its always best to check.
                if(peopleAtPage != null){
                    peopleUntilPage.addAll(peopleAtPage);
                }
            } catch (GenericAbstractException e) {
                //No need to propagate the exception as the exception could mean we went a bit to further in the pages.
                logger.log(LoggerUtil.getLogginLevel(e.getErrorSeverity()), e.getErrorMessage());
            }
        }while (peopleAtPage != null && !peopleAtPage.isEmpty() && pageIndex <= checkUntilPage);

        return peopleUntilPage;
    }

    private List<People> getRegisteredPeopleUpToPage(){
        return getRegisteredPeopleUpToPage(Integer.MAX_VALUE);
    }

    public List<People> getLatestRegisteredPeople(int numberOfEntries){
        return getLatestRegisteredPeople(numberOfEntries, Integer.MAX_VALUE);
    }

    public List<People> getLatestRegisteredPeople(int numberOfEntries, int upToPage){
        List<People> lastRegisteredPeople = getLatestRegisteredPeople(getRegisteredPeopleUpToPage(upToPage), new PeopleComparator(), numberOfEntries);

        return lastRegisteredPeople;

    }

    public String getLatestRegisteredPeopleJSON(int numberOfEntries){
        return getLatestRegisteredPeopleJSON(numberOfEntries, Integer.MAX_VALUE);
    }

    public String getLatestRegisteredPeopleJSON(int numberOfEntries, int upToPage){
        List<People> lastRegisteredPeople = getLatestRegisteredPeople(getRegisteredPeopleUpToPage(upToPage), new PeopleComparator(), numberOfEntries);
        String jsonString = "";
        ObjectMapper objectMapper = new ObjectMapper();
        //this value is to that the json output looks nice
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        objectMapper.setDateFormat(df);
        try {
             jsonString = objectMapper.writeValueAsString(lastRegisteredPeople);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;

    }


    private List<People> getLatestRegisteredPeople(List<People> registeredPeople, Comparator<People> comparator, int entriesNumber){
        //default initial capacity is always 11.
        PriorityQueue<People> priorityQueue = new PriorityQueue(11, comparator);
        List<People> lastRegisteredPeople = new LinkedList<>();
        priorityQueue.addAll(registeredPeople);

        while(!priorityQueue.isEmpty() && lastRegisteredPeople.size() < entriesNumber){
            People people = priorityQueue.poll();
            if(people != null && people.getEmail() != null && !people.getEmail().isEmpty()){
                lastRegisteredPeople.add(people);
            }
        }

        return lastRegisteredPeople;
    }


    public static void main(String[] args) {
        List<People> lastRegisteredPeople = NutshellClient.getInstance().getLatestRegisteredPeople(5,1);
        System.out.println(NutshellClient.getInstance().getLatestRegisteredPeopleJSON(5,1));

    }
}
