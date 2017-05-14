package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
  public List<Assessment> assessmentlist = new ArrayList<Assessment>();

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









}
