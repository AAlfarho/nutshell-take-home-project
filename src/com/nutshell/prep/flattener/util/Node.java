package com.nutshell.prep.flattener.util;

import java.util.LinkedList;

/**
 * Created by alfaro on 4/26/17.
 */
public class Node<T> {
    private T value;
    private LinkedList<Node> children;

    public Node(){

    }

    public Node(T value){
        this.value = value;
    }
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public LinkedList<Node> getChildren() {
        return children;
    }

    public void setChildren(LinkedList<Node> children) {
        this.children = children;
    }

    public void addChildren(Node<T> child){
        if(children == null){
            this.children = new LinkedList<>();
        }

        this.children.add(child);
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEquals = false;
        if(obj instanceof Node){
            Node<T> nodeObj = (Node<T>)obj;
            if(this.getValue() == null ? nodeObj.getValue() == null : this.getValue().equals(nodeObj.getValue())){
                isEquals = this.getChildren() == null ? nodeObj.getChildren() == null : this.getChildren().equals(nodeObj.getChildren());
            }
        }

        return isEquals;
    }
}
