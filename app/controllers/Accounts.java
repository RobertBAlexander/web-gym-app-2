package controllers;

import play.Logger;
import play.mvc.Controller;

import models.Member;
import models.Trainer;

import java.util.List;

/**
 * Created by Robert Alexander on 18/05/2017.
 */
public class Accounts extends Controller
{
    /**
     * This method renders the signup.html page
     *
     */
    public static void signup()
	{
		render("signup.html");
	}

    /**
     * This method renders the login.html page
     *
     */
	public static void login()
    {
    	render("login.html");
    }

    /**
     * This method registers a new member through the signup.html page. It sends that member to the arraylist of
     * members that all trainers have access to.
     * @param firstname The first name of the member.
     * @param lastname
     * @param email
     * @param password
     * @param address
     * @param gender
     * @param height
     * @param startingWeight
     */
    public static void register(String firstname, String lastname, String email, String password, String address, String gender,
                                double height, double startingWeight)
    {
      Logger.info("Registering new user " + email);
      Member member = new Member(firstname, lastname, email, password, address, gender,
        height, startingWeight);
      member.save();
      //redirect to main(start)page.
      redirect("/");
    }

    /**
     * This method checks if the e-mail and password combination match those of a member or a trainer.
     * If they match a member it sets their memberid as the current session and redirects them to the member
     * dashboard. If they match a trainer it sets their trainerid as the current session and redirect
     * them to the trainer dashboard. If the combination does not match either member or trainer, it redirects
     * (or reloads) to the login page.
     *
     * @param email
     * @param password
     */
    public static void authenticate(String email, String password)
    {
      Logger.info("Attempting to authenticate with " + email + ":" + password);
      Member member = Member.findByEmail(email);
      Trainer trainer = Trainer.findByEmail(email);
      if ((member != null) && (member.checkPassword(password) == true)) {
        Logger.info("Authentication successful");
        session.put("logged_in_Memberid", member.id);
        redirect ("/dashboard");
      }
      if ((trainer != null) && (trainer.checkPassword(password) == true)) {
          Logger.info("Authentication successful");
          session.put("logged_in_Trainerid", trainer.id);
          redirect ("/trainer/dashboard");
        }
      else {
        Logger.info("Authentication failed");
        redirect("/login");
      }
    }

    /**
     * Logs the member or trainer out of the current session.
     */
    public static void logout()
    {
      session.clear();
      redirect ("/");
    }

    /**
     * Returns the currently logged in member. If there is no logged in member they are re-directed to the login page
     * @return currently logged in member
     */
    public static Member getLoggedInMember()
    {
      Member member = null;
      if (session.contains("logged_in_Memberid")) {
        String memberId = session.get("logged_in_Memberid");
        member = Member.findById(Long.parseLong(memberId));
      } else {
        login();
      }
      return member;
    }
}
