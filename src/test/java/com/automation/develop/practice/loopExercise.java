package com.automation.develop.practice;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class loopExercise {
    public static void main(String[] args) {

        String expected = "ABCD";

        List<String> myListText = new ArrayList<String>();
        myListText.add("ABCD");
        myListText.add("PQRS");
        myListText.add("MNL");

        for (int i=0; i<myListText.size();i++){
            if(myListText.get(i).equals(expected)) {
                Assert.assertEquals(myListText.get(i), expected );
                System.out.println("Expected and Actual matched");
            }
        }



//        for (int i=0;i<myListText.size();i++){
//
//
//        }
    }
}
