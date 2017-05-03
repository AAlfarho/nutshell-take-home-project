## Synopsis
This mini project gives solution to Nutshell Developer Take Home Project 
https://github.com/nutshellcrm/join-the-team/blob/master/developer-questions.md

It was interesting how deep down the rabbit whole you could go implementing this mini project.

##Flattener

When going through the flattener I though that for example ruby already has that behavior, but on Java, we could either 
have as arguments:

- Object [] : with this approach we need simply to iterate over an object and check if the object was an
        array instance. However the construction of the proper Object[] is quite tricky. for example:
        
        [[1,[[2]],[3]],4] -> = new Object[]{ new Object[]{1, new Object[]{new Object[]{2}}, new Object[]{3}},4};
        
- String : if the representation was done by strings

    
   
If we went through the Object [] approach it as simply as iterating over an object and check if the object was an
array instance. However the construction of the proper Object[] is quite tricky. for example:
     
     - [[1,[[2]],[3]],4] -> = new Object[]{ new Object[]{1, new Object[]{new Object[]{2}}, new Object[]{3}},4};





