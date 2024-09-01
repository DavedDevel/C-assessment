package com.assessment.impl;

import com.assessment.logic.AccuracyStrategy;
import com.assessment.model.Contact;
import org.apache.commons.lang3.StringUtils;

/**
 * This class implements an accuracy strategy applying Levenshtein.
 *
 */
public class DistanceStrategy implements AccuracyStrategy {

    private static double EMAIL_WEIGHT = 0.2d;
    private static double ZIP_CODE_WEIGHT = 0.2d;
    private static double ADDRESS_WEIGHT = 0.2d;
    private static double FIRST_NAME_WEIGHT = 0.2d;
    private static double LAST_NAME_WEIGHT = 0.2d;

    /**
     * This method will ponder each value from contacts and return an score value.
     * @param c1
     * @param c2
     * @return
     */
    public double evaluateSimilarity(Contact c1, Contact c2) {
        var emailScore = getDistance(c1.email(), c2.email());
        var zipCodeScore = getDistance(c1.zipCode(), c2.zipCode());
        var addressScore = getDistance(c1.address(), c2.address());
        var firstNameScore = getDistance(c1.firstName(), c2.firstName());
        var lastNameScore = getDistance(c1.lastName(), c2.lastName());

        var normalizedEmailScore = normalizedDistance(c1.email(), c2.email(), emailScore);
        var normalizedZipCodeScore = normalizedDistance(c1.zipCode(), c2.zipCode(), zipCodeScore);
        var normalizedAddressScore = normalizedDistance(c1.address(), c2.address(), addressScore);
        var normalizedFirstNameScore = normalizedDistance(c1.firstName(), c2.firstName(), firstNameScore);
        var normalizedLastNameScore = normalizedDistance(c1.lastName(), c2.lastName(), lastNameScore);

        var distance = normalizedEmailScore * EMAIL_WEIGHT + normalizedZipCodeScore * ZIP_CODE_WEIGHT
                + normalizedAddressScore * ADDRESS_WEIGHT
                + normalizedFirstNameScore * FIRST_NAME_WEIGHT + normalizedLastNameScore * LAST_NAME_WEIGHT;
        return 1 - distance;
    }

    /**
     * Returns number of changes to get accuracy between two words based on Levenshtein algorithm
     */
    private int getDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;  // full insertion
                } else if (j == 0) {
                    dp[i][j] = i;  // full deletion
                } else {
                    int cost = s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1;
                    dp[i][j] = Math.min(
                            Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), // insertion or deletion
                            dp[i - 1][j - 1] + cost // substitution
                    );
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    /**
     * Returns a normalized distance from two words btw 0 to 1. 1 means maximum distance and 0 means they are equals.
     */
    private double normalizedDistance(String s1, String s2, int distance){
        if ( StringUtils.isEmpty(s1)) {
            return StringUtils.isEmpty(s2)? 0 : 1;
        }
        if ( StringUtils.isEmpty(s2)) {
            return StringUtils.isEmpty(s1)? 0 : 1;
        }
        double length =  s1.length() > s2.length() ? s1.length() : s2.length();
        return distance / length;
    }
}
