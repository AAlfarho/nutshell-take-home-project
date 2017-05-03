# Synopsis
This mini project gives solution to Nutshell Developer Take Home Project 
https://github.com/nutshellcrm/join-the-team/blob/master/developer-questions.md

It was interesting how deep down the rabbit whole you could go implementing this mini project.

#Flattener

When going through the flattener I though that for example ruby already has that functionality, but on Java, we could either 
have as arguments:

- Object [] : with this approach we need simply to iterate over an object and check if the object was an
        array instance. However the construction of the proper Object[] is quite tricky. for example:
        
        [[1,[[2]],[3]],4] -> = new Object[]{ new Object[]{1, new Object[]{new Object[]{2}}, new Object[]{3}},4};
        
- String : if the representation was done by strings, we basically just needed to remove all '[' ']' and split the
resulting string and we would have a flattened array. However going the rabbit hole again, I though it could be cool to
have a tree like representation, meaning, we process the string and deliver a root Node from a Tree ADT.

FlattenerUnitTest is the place to go for examples on how to use the Flattener class.

##Dependencies
Flattenner uses only JUnit (junit-4.12.jar) and Hamcrest(hamcrest-core-1.3.jar) for its unit testing purposes.



#REST API Client
For this example we basically just used a javax client to access 'http://join.nutshell.com/people/x/',
translate the JSON response into a List of People and we just keep going until an empty array is returned.
Then we just need to iterate through the whole collection and filter out all registered users whit null emails and
add them into a Priority Queue that we will later use to retrieve the last user registered.
Examples on the usage of NutshellClient can be found under NutshellClientUnitTest.



##Dependencies
Used JBoss project RESTEasy (http://download.jboss.org/resteasy/resteasy-jaxrs-3.1.2.Final-all.zip)

#Overall
The tests itself are quite easy to follow, and the code here presented might look way to much for what it achieves,
however it is coded the way it would be for an enterprise app, ie.
- We have enums for error codes.
- A fast approach to internationalization (still more to be done, check the TODOs).
- A minimal exception handling manager utility (ExceptionManager class).
- Code structure split by functionality.

In the end even there are more things to cover and polish, this would be the first skeleton on which we could continue
building.


#TODO's
- Implement maven or gradle for automatic build.
- Comment classes so that we can autogenerate the help pages automatically.
- Implement resource bundle parameter substitution.
- Avoid the use of java.util.Date in the People class, funny enough, I just read 
https://codeblog.jonskeet.uk/2017/04/23/all-about-java-util-date/ so using a different class for time/date will be nice.






