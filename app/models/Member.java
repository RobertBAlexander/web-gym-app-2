package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.lang.Math;

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
  //public List<Todo> todolist = new ArrayList<Todo>();
  public List<Assessment> assessmentlist = new ArrayList<>();

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

  public static Member findByEmail(String email) {
    return find("email", email).first();
  }

  public boolean checkPassword(String password) {
    return this.password.equals(password);
  }


  /**
   * This method adds an assessment to member. The current date, and the details of an assessment should
   * be passed in.
   *
   * @param assessment The assessment should contain current weight, chest, thigh, upperArm, waist, hips,
   *                   a comment by the trainer, and the trainer who performed the assessment.
   */
  public void addAssessment(Assessment assessment) {
    //TODO need to do code for getting the current date, and also need to figure out
    //how I'm referring to assessments so that I can call them here.
    //assessments.put(currentDate, assessmentName)
    //DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd HH:mm:ss");
    //Date currentDate = new Date();
    //date = dateFormat.format(currentDate);
    assessmentlist.add(assessment);
  }


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


  public String trend(Assessment assessment)
  {

    long assessmentId = assessment.getId();
    long preId = assessmentId - 1;
    //double previousAssessment = assessmentlist.get((int) Math.round(preId)).getWeight();
    double previousAssessment = 90;

    if (assessment.getWeight() > previousAssessment)
    {
      return "sadfat_pika.png";
    }
    if (assessment.getWeight() < previousAssessment)
    {
      return "happyhealth_pika.png";
    }
    else
    {
      return "nochange.jpg";
    }

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

  public void setHeight(double height)
  {
    this.height = height;
  }

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
