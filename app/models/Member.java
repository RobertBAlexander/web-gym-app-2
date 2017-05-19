package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.lang.Math;

/**
 * Created by Robert Alexander on 16/05/2017.
 */
@Entity
public class Member extends Model {
  public String firstname;
  public String lastname;
  public String email;
  public String password;
  public String address;
  public String gender;
  public double height;
  public double startingWeight;


  @OneToMany(cascade = CascadeType.ALL)
  public List<Assessment> assessmentlist = new ArrayList<Assessment>();

  /**
   * This method creates a new member
   * @param firstname of new member
   * @param lastname of new member
   * @param email of new member
   * @param password of new member
   * @param address of new member
   * @param gender of new member
   * @param height of new member
   * @param startingWeight of new member
   */
  public Member(String firstname, String lastname, String email, String password, String address, String gender,
                double height, double startingWeight) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.password = password;
    this.address = address;
    this.gender = gender;
    this.height = height;
    this.startingWeight = startingWeight;
  }

  /**
   * find member by e-mail
   * @param email to search by
   * @return
   */
  public static Member findByEmail(String email) {
    return find("email", email).first();
  }

  /**
   * check the password is correct
   * @param password the password to check against
   * @return
   */
  public boolean checkPassword(String password) {
    return this.password.equals(password);
  }

  /**
   * The most recent assessment created in this member's array list of assessments
   * @return
   */
  public Assessment latestAssessment()
  {
    if (assessmentlist.size() > 0) {
      return assessmentlist.get(assessmentlist.size() - 1);
    }
    else
    {
      return null;
    }
  }

  /**
   * method to return what trend this assessment goes in versus the previous assessment. It checks which of the two
   * assessments is closer to the ideal BMI of 22. If the current one is closer, it is a good trend, if the previous
   * assessment is closer or equal it is a bad trend.
   * @param assessment the currnet (not previous) assessment that you are checking
   * @return
   */
  public String trend(Assessment assessment)
  {
    int assessmentIndex = assessmentlist.indexOf(assessment);
    double previousAssessment;
    if (assessmentIndex != 0) {
      previousAssessment = assessmentlist.get(assessmentIndex - 1).getWeight();
    }
    else {
      previousAssessment = getWeight();
    }
    double idealBMI = 22;
    double valueBMI = toTwoDecimalPlaces(previousAssessment / (getHeight() * getHeight()));
    double currentCompare;
    double previousCompare;

    previousCompare = Math.abs(valueBMI -idealBMI);
    currentCompare = Math.abs((assessment.getWeight() / (getHeight() * getHeight())) -idealBMI);

    //below full code should work to allow the nochange icon to appear when the two assessments are exactly even
    //however this has not worked, and I have had to remvoe the no change icon due ot lack of funtionality
    if (currentCompare > previousCompare)
    {
      return "angry_pika.jpg";
    }
    else
      //if (currentCompare < previousCompare)
    {
      return "happyhealth_pika.png";
    }
   // else
   // {
   //   return "nochange.jpg";
   // }
  }

  //********************************************************************************
  //  SETTERS
  //********************************************************************************



  /**
   * This method updates the email field.
   * @param email The Person's e-mail must contain an '@' symbol.
   */
  public void setEmail(String email) {
    String atSymbol = "@";
    if (email.contains(atSymbol))
    {
      this.email = email;
    }
    else
    {
      this.email = this.email;
    }
  }

  /**
   * This method updates the name field.
   * @param firstname The person's name should be no longer tha n30 characters. If
   *             the entered name exceeds 30 cahracters, the extra characters
   *             will be truncated and only the first 30 characters will be used.
   */
  public void setFirstName(String firstname) {

      this.firstname = firstname;
  }

  /**
   * This method updates the name field.
   * @param lastname The person's name should be no longer tha n30 characters. If
   *             the entered name exceeds 30 cahracters, the extra characters
   *             will be truncated and only the first 30 characters will be used.
   */
  public void setLastName(String lastname) {

    this.lastname = lastname;
  }

  /**
   * This method updates the address field.
   * @param address There is no validation on the member's address.
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * This method updates the gender field.
   * @param gender The member's gender can be either "M" or "F". lower case entries
   *               will be changed to upper case. If not specified, this will default
   *               to "Unspecified".
   */
  public void setGender(String gender) {
    if ((gender.toUpperCase().equals("MALE")) || (gender.toUpperCase().equals("FEMALE"))) {
      this.gender = gender.toUpperCase();
    }
    else
    {
      this.gender = "Unspecified";
    }
  }

  /**
   * method to change member height
   * @param height to change member height to
   */
  public void setHeight(double height)
  {
    this.height = height;
  }

  /**
   * method to change member starting weight
   * @param startingWeight to chagne member starting weight to
   */
  public void setStartingWeight(double startingWeight)
  {
    this.startingWeight = startingWeight;
  }


  //********************************************************************************
  //  GETTERS
  //********************************************************************************

  /**
   * Returns the e-mail of the Person.
   * @return the Person's e-mail
   */
  public String getEmail() {
    return email;
  }

  /**
   * Returns the name of the Person.
   * @return the Person's name
   */
  public String getFirstname() {
    return firstname;
  }

  /**
   * Returns the name of the Person.
   * @return the Person's name
   */
  public String getLastname() {
    return lastname;
  }

  /**
   * Returns the address of the Person.
   * @return the Person's address.
   */
  public String getAddress() {
    return address;
  }

  /**
   * Returns the gender of the Person.
   * @return the Person's gender.
   */
  public String getGender() {
    return gender;
  }

  /**
   * Returns the member's height.
   *
   * @return the member's height.
   */
  public double getHeight() {

    return height;
  }

  /**
   * Returns the member's starting weight.
   *
   * @return the member's starting weight.
   */
  public double getWeight() {
    return startingWeight;
  }


  //********************************************************************************
  //  ANALYTICS
  //********************************************************************************

  /**
   * This method calcuates the BMI value for the member.
   *
   * The formula used for BMI is weight divided by the square of the height.
   *
   * @return the BMI value for the member. The number returned is reduced to two decimal places.
   */
  public double calculateBMI ()
  {
    Assessment assessment = latestAssessment();
    double memberWeight;
    if (assessmentlist.size() > 0) {
      memberWeight = assessment.getWeight();
    }
    else
    {
      memberWeight = startingWeight;
    }
      if (getHeight() <= 0) {
        return 0;
      }
      else
        {
        return toTwoDecimalPlaces(memberWeight / (getHeight() * getHeight()));
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
  public String determineBMICategory (double bmiValue)
  {

      if (bmiValue < 15) {
        return "VERY SEVERELY UNDERWEIGHT";
      } else if (bmiValue < 16) {
        return "SEVERELY UNDERWEIGHT";
      } else if (bmiValue < 18.5) {
        return "UNDERWEIGHT";
      } else if (bmiValue < 25) {
        return "NORMAL";
      } else if (bmiValue < 30) {
        return "OVERWEIGHT";
      } else if (bmiValue < 35) {
        return "MODERATELY OBESE";
      } else if (bmiValue < 40) {
        return "SEVERELY OBESE";
      } else return "VERY SEVERELY OBESE";

  }

  /**
   * This method returns the member height converted from meters to inches.
   *
   * @return member height converted from meters to inches using the formula: meters
   * multiplied by 39.37. The number returned is truncated to two decimal places.
   */
  public double convertHeightMetersToInches()
  {
    double convertedHeight = (getHeight() * 39.37);
    return toTwoDecimalPlaces(convertedHeight);
  }

  /**
   * This method returns the member weight converted from kg to stone.
   *
   * @return member weight converted from kg to stone.
   */
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
   * @return Returns true if the result of Devine formula is within 2kgs of (inclusive) of the
   * starting weight. False if outside this range.
   */
  public String isIdealBodyWeight ()
  {
    Assessment assessment = latestAssessment();
    double fiveFeet = 60.0;
    double idealBodyWeight;
    double inches = convertHeightMetersToInches();
    double memberWeight;
    if (assessmentlist.size() > 0) {
      memberWeight = assessment.getWeight();
      }
    else
      {
        memberWeight = startingWeight;
      }
      if (inches <= fiveFeet) {
        if (getGender().equals("M")) {
          idealBodyWeight = 50;
        } else {
          idealBodyWeight = 45.5;
        }
      } else {
        if (getGender().equals("M")) {
          idealBodyWeight = 50 + ((inches - fiveFeet) * 2.3);
        } else {
          idealBodyWeight = 45.5 + ((inches - fiveFeet) * 2.3);
        }
      }
      if ((idealBodyWeight <= (memberWeight + 2.0)) && (idealBodyWeight >= (memberWeight - 2.0))) {
        return "green";
      } else {
        return "red";
      }
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
