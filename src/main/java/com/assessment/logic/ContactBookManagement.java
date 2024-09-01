package com.assessment.logic;

import com.assessment.model.Accuracy;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;


/**
 * This class implements the management of a contact book.
 */
public class ContactBookManagement {

    private ContactBookResource contactBookResource;
    private AccuracyStrategy accuracyStrategy;
    private List<Triple<String,String, Accuracy>> output = new ArrayList<>();


    public ContactBookManagement(ContactBookResource contactBookResource, AccuracyStrategy accuracyStrategy){
        this.contactBookResource = contactBookResource;
        this.accuracyStrategy = accuracyStrategy;
    }

    /**
     * This method returns potential duplicated contacts in a book.
     */
    public List<Triple<String, String, Accuracy>> findPotentialDuplication() {
        var contactBookList = contactBookResource.getContactBookResource();
        for (int i= 0; i < contactBookList.size(); i++) {
            for (int j= i+1; j < contactBookList.size(); j++) {
                var a = contactBookList.get(i);
                var b = contactBookList.get(j);
                if(a.equals(b)) {
                    output.add(Triple.of(a.id(), b.id(), Accuracy.HIGH));
                } else {
                    var score = accuracyStrategy.evaluateSimilarity(a, b);
                    var similarity = Accuracy.LOW;
                    if(score > 0.33 && score < 0.66 ) {
                        similarity = Accuracy.MEDIUM;
                    } else if (score >= 0.66 ) {
                        similarity = Accuracy.HIGH;
                    }
                    output.add(Triple.of(a.id(), b.id(), similarity));
                }
            }
        }
        return output;
    }

}
