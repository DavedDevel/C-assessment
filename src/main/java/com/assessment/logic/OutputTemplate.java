package com.assessment.logic;

import com.assessment.model.Accuracy;
import org.apache.commons.lang3.tuple.Triple;

import java.util.List;

/**
 * This interface defines a downstream method to send out the resulting comparison of contact book.
 */
public interface OutputTemplate {

    void outputStream(List<Triple<String, String, Accuracy>> result);

}
