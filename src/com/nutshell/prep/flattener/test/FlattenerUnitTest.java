package com.nutshell.prep.flattener.test;

import com.nutshell.prep.flattener.action.Flattener;
import com.nutshell.prep.flattener.util.Node;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

/**
 * Created by alfaro on 4/25/17.
 */
public class FlattenerUnitTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    private Flattener flattener = new Flattener();

    //["one",2,["three", "four", ["five"], "six", "seven"]]
    private Object [] nestedMultiObjectArray = new Object[]{"one", 2, new Object[]{"three", "four", new Object[]{"five"}, 6, 7}};
    private Object [] expectedNestedMultiObjectArray = new Object[]{"one", 2, "three", "four", "five", 6, 7};

    //[[1,[[2]],[3]],4]
    private Object[] nestedIntegerArray = new Object[]{ new Object[]{1, new Object[]{new Object[]{2}}, new Object[]{3}},4};
    private Object[] expectedNestedIntegerArray = new Object[]{1, 2, 3, 4};
    private String nestedIntegerArrayString = "[[1,[[2]],[3]],4]";
    private Node<String> expectedNestedIntegerRoot = constructBasicTree();



    @Test
    public void testObjectArray(){
        Assert.assertArrayEquals(flattener.flattenArrays(nestedMultiObjectArray), expectedNestedMultiObjectArray);
        Assert.assertArrayEquals(flattener.flattenArrays(nestedIntegerArray), expectedNestedIntegerArray);
        Assert.assertThat(nestedIntegerArray, IsNot.not(IsEqual.equalTo(new Object[]{1,2,3})));
        Assert.assertThat(nestedIntegerArray, IsNot.not(IsEqual.equalTo(new Object[]{1,2,3,5})));

    }


    @Test
    public void testNullArray() {
        Object[] flattenedArray = flattener.flattenArrays(null);
        Assert.assertNull(flattenedArray);

    }

    @Test
    public void testEmptyArray() {
        Object[] flattenedArray = flattener.flattenArrays(new Object[0]);
        Assert.assertNotNull(flattenedArray);
        Assert.assertEquals(0, flattenedArray.length);
    }

    @Test
    public void testFlatArray(){
        Assert.assertArrayEquals(flattener.flattenArrays(new Object[]{1,2,3}), new Object[]{1,2,3});
    }

    @Test
    public void testTreeLikeNestedArrayString(){
        Node<String> successfulTree = flattener.buildTreeFromNestedString("[[1,[[2]],[3]],4]");
        Node<String> failTree = flattener.buildTreeFromNestedString("[[1,[[2]],[3]],5]");
        Assert.assertEquals(successfulTree, expectedNestedIntegerRoot);
        Assert.assertNotEquals(failTree, expectedNestedIntegerRoot);
    }

    @Test
    public  void testeUnbalancedBrackets(){
        exit.expectSystemExitWithStatus(1);
        flattener.buildTreeFromNestedString("[[1,[[2]],[3]],5]]]]");
    }

    @Test
    public void testNullStringNestedArrayString(){
        exit.expectSystemExitWithStatus(1);
        flattener.buildTreeFromNestedString(null);
    }

    private Node<String> constructBasicTree() {
        Node<String> expectedRootNode = new Node<>();
        Node<String> nodeOneRoot = new Node<>();

        Node<String> nodeOne = new Node<>("1");

        Node<String> nodeTwoUpperRoot = new Node<>();
        Node<String> nodeTwoRoot = new Node<>();
        nodeTwoUpperRoot.addChildren(nodeTwoRoot);
        nodeTwoRoot.addChildren(new Node<>("2"));

        Node<String> nodeThreeRoot = new Node<>();
        nodeThreeRoot.addChildren(new Node<>("3"));


        Node<String> nodeFour = new Node<>("4");

        nodeOneRoot.addChildren(nodeOne);

        nodeOneRoot.addChildren(nodeTwoUpperRoot);
        nodeOneRoot.addChildren(nodeThreeRoot);

        expectedRootNode.addChildren(nodeOneRoot);
        expectedRootNode.addChildren(nodeFour);

        return expectedRootNode;
    }



}
