package utils;

import models.*;

/**
 * Created by Robert Alexander on 29/04/2017.
 *
 * This utility class is used within the application to analyse and calculate data for other classes in the application.
 *
 * The class uses the following public static methods:
 * calculateBMI(prompt, prompt)         : Returns the BMI value of a member during a particular assessment based
 *                                        on their height and weight.
 * determineBMICategory(prompt)         : Returns the BMI category of amember, based on their BMI value.
 * convertHeightMetersToInches(prompt)  : Returns a member's height in inches, based on their height in meters.
 * convertWeightKGToPounds(prompt)      : Returns a member's weight in pounds , based on their weight in kg.
 * isIdealBodyWeight(prompt, prompt)    : Checks if a member fits into the devine method of ideal body weight
 *                                        during a particular assessment based on their height and weight.
 * toTwoDecimalPlaces(prompt)           : Converts a 'double' prompt into a double that only goes to two decimal places.
 *
 */
public class Analytics {

    /**
     * This method calcuates the BMI value for the member.
     *
     * The formula used for BMI is weight divided by the square of the height.
     *
     * @param member
     * @param assessment
     * @return the BMI value for the member. The number returned is reduced to two decimal places.
     */
    public static double calculateBMI (Member member, Assessment assessment)
    {
        if (member.getHeight() <= 0)
            return 0;
        else
        {
            return toTwoDecimalPlaces(assessment.getWeight() / (member.getHeight() * member.getHeight()));
        }
    }

    /**
     * This method determines the BMI category that the member belongs to.
     *
     *
     * The category is determined by the magnitude of the members BMI according to the following:
     *
     *      BMI less than 15 (exclusive)                        is VERY SEVERELY UNDERWEIGHT
     *      BMI between 15   (inclusive) and 16 (exclusive)     is SEVERELY UNDERWEIGHT
     *      BMI between 16   (inclusive) and18.5(exclusive)     is UNDERWEIGHT
     *      BMI between 18.5 (inclusive) and 25 (exclusive)     is NORMAL
     *      BMI between 25   (inclusive) and 30 (exclusive)     is OVERWEIGHT
     *      BMI between 30   (inclusive) and 35 (exclusive)     is MODERATELY OBESE
     *      BMI between 35   (inclusive) and 40 (exclusive)     is SEVERELY OBESE
     *      BMI above   40   (inclusive)                        is VERY SEVERELY OBESE
     * @param bmiValue
     * @return The BMI category is returned in the following format: "\"NORMAL\""
     */
    public static String determineBMICategory (double bmiValue)
    {

        if (bmiValue < 15)
        {
            return "\"VERY SEVERELY UNDERWEIGHT\"";
        }
        else if (bmiValue < 16)
        {
            return "\"SEVERELY UNDERWEIGHT\"";
        }
        else if (bmiValue < 18.5)
        {
            return "\"UNDERWEIGHT\"";
        }
        else if (bmiValue < 25)
        {
            return "\"NORMAL\"";
        }
        else if (bmiValue < 30)
        {
            return "\"OVERWEIGHT\"";
        }
        else if (bmiValue < 35)
        {
            return "\"MODERATELY OBESE\"";
        }
        else if (bmiValue < 40)
        {
            return "\"SEVERELY OBESE\"";
        }
        else return "\"VERY SEVERELY OBESE\"";
    }

    /**
     * This method returns the member height converted from meters to inches.
     *
     * @return member height converted from meters to inches using the formula: meters
     * multiplied by 39.37. The number returned is truncated to two decimal places.
     */
    public static double convertHeightMetersToInches(Member member)
    {
        double convertedHeight = (member.getHeight() * 39.37);
        return toTwoDecimalPlaces(convertedHeight);
    }


    public static double convertWeightKGtoPounds(Assessment assessment)
    {
        double convertedWeight = (assessment.getWeight() * 2.2);
        return convertedWeight;
    }
    /**
     * This method returns a boolean to indicate if the member has an idea body weight based on the Devine formula.
     *
     * For men an ideal weight is: 50kg + 2.3kg for each inch over 5 feet.
     * For women an ideal weight is: 45.5kg + 2.3kg for each inch over 5 feet.
     * If no gender is specified, default to female calculation.
     * @param member
     * @param assessment
     * @return Returns true if the result of Devine formula is within 2kgs of (inclusive) of the
     * starting weight. False if outside this range.
     */
    public static boolean isIdealBodyWeight (Member member, Assessment assessment)
    {
        double fiveFeet = 60.0;
        double idealBodyWeight;

        double inches = convertHeightMetersToInches(member);

        if (inches <= fiveFeet)
        {
            if (member.getGender().equals("M"))
            {
                idealBodyWeight = 50;
            }
            else
            {
                idealBodyWeight = 45.5;
            }
        }
        else
        {
            if (member.getGender().equals("M"))
            {
                idealBodyWeight = 50 + ((inches - fiveFeet) * 2.3);
            }
            else
            {
                idealBodyWeight = 45.5 + ((inches - fiveFeet) * 2.3);
            }
        }
        return ( (idealBodyWeight <= (assessment.getWeight() + 2.0)) && (idealBodyWeight >= (assessment.getWeight() - 2.0)));
    }

    /**
     * This is a private helper method. It ensures that all double data returned from this class
     * is restricted to two decimal places. Note: this method does not  round.
     * @param num
     * @return This takes in a double and returns one that only goes to two decimal places.
     */
    private static double toTwoDecimalPlaces ( double num)
    {
        return (int)(num * 100) / 100.0;
    }

}
