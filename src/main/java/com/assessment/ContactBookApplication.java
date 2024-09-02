package com.assessment;


import com.assessment.impl.ContactBookFromCsv;
import com.assessment.impl.DistanceStrategy;
import com.assessment.impl.OutputPrinter;
import com.assessment.logic.ContactBookManagement;
import com.assessment.logic.OutputTemplate;

class ContactBookApplication {

    public static void main(String... args){

        ContactBookManagement contactBookManagement = new ContactBookManagement(new ContactBookFromCsv(), new DistanceStrategy());
        var list = contactBookManagement.findPotentialDuplication("sampleCode.csv");
        OutputTemplate outputTemplate = new OutputPrinter();
        outputTemplate.outputStream(list);

    }

}