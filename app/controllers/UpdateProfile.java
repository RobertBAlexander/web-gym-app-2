package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

/**
 * Created by Robert Alexander on 12/05/2017.
 */
public class UpdateProfile extends Controller
{
    /**
     * This method renders the updateProfile.htmll page.
     */
    public static void index()
    {
        Logger.info("Rendering Update Profile");
        Member member = Accounts.getLoggedInMember();
        List<Assessment> assessmentlist = member.assessmentlist;
        render("updateProfile.html", member, assessmentlist);
    }

    /**
     * This method calls on the set firstname method in member
     * @param firstname the first name of the member
     */
    public static void setFName(String firstname)
    {
        Member member =Accounts.getLoggedInMember();
        member.setFirstName(firstname);
        member.save();
        Logger.info("Updating First Name" + member.getFirstname() + member.getLastname());
        redirect("/profile");
    }

    /**
     * This method calls on the set lastname method in member
     * @param lastname the first name of the member
     */
    public static void setLName(String lastname)
    {
        Member member =Accounts.getLoggedInMember();
        member.setLastName(lastname);
        member.save();
        Logger.info("Updating Last Name" + member.getFirstname() + member.getLastname());
        redirect("/profile");
    }

    /**
     * This method calls on the set email method in member
     * @param email the first name of the member
     */
    public static void setMemberEmail(String email)
    {
        Member member =Accounts.getLoggedInMember();
        member.setEmail(email);
        member.save();
        Logger.info("Updating Email of" + member.getFirstname() + member.getLastname());
        redirect("/profile");
    }

    /**
     * This method calls on the set address method in member
     * @param address the first name of the member
     */
    public static void setNewAddress(String address)
    {
        Member member =Accounts.getLoggedInMember();
        member.setAddress(address);
        member.save();
        Logger.info("Updating New Address of" + member.getFirstname() + member.getLastname());
        redirect("/profile");
    }

    /**
     * This method calls on the set gender method in member
     * @param gender the first name of the member
     */
    public static void setChangeGender(String gender)
    {
        Member member =Accounts.getLoggedInMember();
        member.setGender(gender);
        member.save();
        Logger.info("Changing gender of" + member.getFirstname() + member.getLastname());
        redirect("/profile");
    }

    /**
     * This method calls on the set height method in member
     * @param height the first name of the member
     */
    public static void setNewHeight(double height)
    {
        Member member =Accounts.getLoggedInMember();
        member.setHeight(height);
        member.save();
        Logger.info("Updating new height of" + member.getFirstname() + member.getLastname());
        redirect("/profile");
    }

    /**
     * This method calls on the set startingWeight method in member
     * @param startingWeight the first name of the member
     */
    public static void setAlterStartingWeight(double startingWeight)
    {
        Member member =Accounts.getLoggedInMember();
        member.setStartingWeight(startingWeight);
        member.save();
        Logger.info("Altering Starting Weight of" + member.getFirstname() + member.getLastname());
        redirect("/profile");
    }
}
