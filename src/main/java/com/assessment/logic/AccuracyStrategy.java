package com.assessment.logic;

import com.assessment.model.Contact;

/**
 * This class defines accuracy interface.
 *
 */
public interface AccuracyStrategy {

    double evaluateSimilarity(Contact c1, Contact c2);

}
