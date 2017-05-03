package com.nutshell.prep.flattener.util;

import com.nutshell.prep.exception.GenericAbstractException;
import com.nutshell.prep.exception.GenericException;
import com.nutshell.prep.flattener.exception.FlattenerException;
import com.nutshell.prep.resource.ErrorCode;
import com.nutshell.prep.flattener.resource.FlattenerErrorCode;

/**
 * Created by alfaro on 4/26/17.
 */
public class StringUtil {

    private int bracketBalance = 0;

    public static String removeBrackets(String inputToProcess){
        String resultString = null;
        if(inputToProcess != null){
            StringBuilder bracketFreeString = new StringBuilder();
            for(char currentInputChar : inputToProcess.toCharArray()){
                if(currentInputChar != '[' && currentInputChar != ']'){
                    bracketFreeString.append(currentInputChar);
                }
            }
            if(bracketFreeString.length() > 0){
                resultString = bracketFreeString.toString();
            }
        }
        return resultString;
    }

    public  Node<String> buildTree(String inputToProcess) throws GenericAbstractException{
        this.bracketBalance = 0;
        Node<String> rootNode = new Node<>();
        int currentInputIndex = 0;
        if(inputToProcess != null){
            buildTree(removeRootBrackets(inputToProcess), currentInputIndex, rootNode);
        } else {
            throw new GenericException(ErrorCode.NULL_VALUE_ERR);
        }
        if(bracketBalance != 0){
            throw new FlattenerException(FlattenerErrorCode.UNBALANCED_BRACKETS_ERR);
        }

        return rootNode;
    }

    private int buildTree(String nestedArrays, int currentInputIndex, Node<String> currentRoot) throws FlattenerException {
        StringBuilder nodeValue = new StringBuilder();
        while (currentInputIndex < nestedArrays.length()) {
            char currentInputChar = nestedArrays.charAt(currentInputIndex++);
            if (currentInputChar == '[') {
                bracketBalance++;
                Node<String> childNode = verifyAndAddChildNode(nodeValue, currentRoot);
                currentInputIndex = buildTree(nestedArrays, currentInputIndex, childNode);
                currentRoot.addChildren(childNode);
            } else if (currentInputChar == ']'){
                bracketBalance--;
                verifyAndAddChildNode(nodeValue, currentRoot);
                break;
            }
            else if (currentInputChar == ',') {
                verifyAndAddChildNode(nodeValue, currentRoot);
            }
            else{
                nodeValue.append(currentInputChar);
            }

        }

        if(nodeValue.length() > 0){
            currentRoot.addChildren(new Node<String>(nodeValue.toString()));
        }

        return currentInputIndex;
    }

    private String removeRootBrackets(String inputString) throws FlattenerException, GenericException {
        if(inputString != null){
            if(inputString.charAt(0) == '[' &&
                    inputString.charAt(inputString.length()-1) == ']'){
                inputString =  inputString.substring(1, inputString.length() - 1);
            } else {
                throw new FlattenerException(FlattenerErrorCode.UNBALANCED_BRACKETS_ERR);
            }
        } else {
            throw new GenericException(ErrorCode.NULL_VALUE_ERR);
        }
        return inputString;
    }
    private Node<String> verifyAndAddChildNode(StringBuilder nodeValue, Node<String> parentNode){
        if(nodeValue.length() > 0) {
            Node<String> childNode = new Node<>(nodeValue.toString());
            parentNode.addChildren(childNode);
            nodeValue.setLength(0);
            return childNode;
        }

        return new Node<String>();
    }


}

class TEST {
    public static void main(String[] args) throws GenericAbstractException {

       // Node<String> dafuq = new StringUtil().buildTree("[10,1,[[2,234,234,234,234,[2]],[3]],4,[5]]]]]");
        //Node<String> nothin = new StringUtil().buildTree("[ , , [[ , , , , ,[ ]],[ ]], ,[ ]]");
        //Node<String> dafuq = buildList("[10,1,[[2,[2]],[3]],4,[5]]");
        Node<String> dafuq = new StringUtil().buildTree("[[1,[[2]],[3]],4]");
        System.out.println();
    }

}
