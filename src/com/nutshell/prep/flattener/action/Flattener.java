package com.nutshell.prep.flattener.action;

import com.nutshell.prep.exception.ExceptionManager;
import com.nutshell.prep.exception.GenericAbstractException;
import com.nutshell.prep.flattener.util.Node;
import com.nutshell.prep.flattener.util.StringUtil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alfaro on 5/2/17.
 */
public class Flattener {

    public Object[] flattenArrays(Object[] arrayToFlatten){
        Object[] arr = null;
        if(arrayToFlatten != null){
            List<Object> resultFlattenList = flattenArrayToList(arrayToFlatten);
            arr = new Object[resultFlattenList.size()];
            resultFlattenList.toArray(arr);
            System.out.println(Arrays.toString(arr));
        }
        return arr;
    }
    public String[] flattenArrayString(String arrayToFlatten){
        String[] flattenedArray = null;
        if(arrayToFlatten != null){
            String bracketsRemoved = StringUtil.removeBrackets(arrayToFlatten);
            if(bracketsRemoved != null && !bracketsRemoved.isEmpty()){
                flattenedArray = bracketsRemoved.split(",");
            }
        }
        return flattenedArray;

    }

    public List<Object> flattenArrayToList(Object[] arrayToFlatten){
        LinkedList<Object> flattenedList = new LinkedList<>();
        if(arrayToFlatten != null){
            for(Object topArrayElement : arrayToFlatten){
                if(topArrayElement instanceof Object[]){
                    flattenedList.addAll(flattenArrayToList((Object [])topArrayElement));
                } else {
                    flattenedList.add(topArrayElement);
                }
            }

        }
        return  flattenedList;
    }

    /**
     * Build a tree like structure from the nested array string.
     * @param inputString String with nested array like value.
     * @return {@link Node} structure with their respective children.
     */
    public Node<String> buildTreeFromNestedString(String inputString){
        Node<String> root = null;
        try {
            root = new StringUtil().buildTree(inputString);
        } catch (GenericAbstractException e) {
            ExceptionManager.handleException(e);
        }

        return root;
    }

}
