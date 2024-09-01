package com.assessment.impl;

import com.assessment.logic.OutputTemplate;
import com.assessment.model.Accuracy;
import org.apache.commons.lang3.tuple.Triple;

import java.util.List;

/**
 * This class implements a downstream method to send out the resulting comparison of contact book.
 */
public class OutputPrinter implements OutputTemplate {

    /**
     * This method will send out the resulting comparison of contact book into an output stream.
     */
    public void outputStream(List<Triple<String, String, Accuracy>> accuracyList) {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("|  ContactID Source \t\t| ContactID Match \t\t| Accuracy \t\t|");
        System.out.println("--------------------------------------------------------------------");
        for( var t : accuracyList){
            System.out.println("| "+t.getLeft()+"\t\t\t| "+t.getMiddle()+"\t\t\t| "+t.getRight()+"\t\t\t|");
            System.out.println("--------------------------------------------------------------------");
        }
    }

}
