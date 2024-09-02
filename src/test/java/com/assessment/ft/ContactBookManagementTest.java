package com.assessment.ft;

import com.assessment.impl.ContactBookFromCsv;
import com.assessment.impl.DistanceStrategy;
import com.assessment.logic.AccuracyStrategy;
import com.assessment.logic.ContactBookManagement;
import com.assessment.logic.ContactBookResource;
import com.assessment.model.Accuracy;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class ContactBookManagementTest {

    private static ContactBookResource contactBookResource;
    private static AccuracyStrategy accuracyStrategy;
    private static ContactBookManagement contactBookManagement;

    @BeforeAll
    static void setup(){
        accuracyStrategy = new DistanceStrategy();
        contactBookResource =  new ContactBookFromCsv();
        contactBookManagement = new ContactBookManagement(contactBookResource, accuracyStrategy);
    }

    @Test
    public void testEmptyContactBook(){
        //Given an empty contact book
        String emptySample = "emptyTest.csv";
        //When search similar contacts
        var result = contactBookManagement.findPotentialDuplication(emptySample);
        //Then should no found any match
        Assertions.assertTrue(Collections.emptyList().equals(result));
    }

    @Test
    public void testHighScoreInContactBook(){
        //Given a contact book
        String highScore = "highScore.csv";
        //When search similar contacts
        var result = contactBookManagement.findPotentialDuplication(highScore);
        //Then the contact book will have high score
        Assertions.assertTrue(!Collections.emptyList().equals(result));
        Assertions.assertTrue(result.stream().map(Triple::getRight).allMatch(accuracy->accuracy.equals(Accuracy.HIGH)));
    }

    @Test
    public void testLowScoreInContactBook(){
        //Given a contact book
        String highScore = "lowScore.csv";
        //When search similar contacts
        var result = contactBookManagement.findPotentialDuplication(highScore);
        //Then the contact book will have low score
        Assertions.assertTrue(!Collections.emptyList().equals(result));
        Assertions.assertTrue(result.stream().map(Triple::getRight).allMatch(accuracy->accuracy.equals(Accuracy.LOW)));
    }

    @Test
    public void testMediumScoreInContactBook(){
        //Given a contact book
        String highScore = "mediumScore.csv";
        //When search similar contacts
        var result = contactBookManagement.findPotentialDuplication(highScore);
        //Then the contact book will have low score
        Assertions.assertTrue(!Collections.emptyList().equals(result));
        Assertions.assertTrue(result.stream().map(Triple::getRight).allMatch(accuracy->accuracy.equals(Accuracy.MEDIUM)));
    }


}
