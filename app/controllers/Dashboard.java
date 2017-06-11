package controllers;

//import models.Todo;
import models.Assessment;
import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by Robert Alexander on 18/05/2017.
 */
public class Dashboard extends Controller
{
    /**
     * This method renders the members dashboard.html page, and stores that member, and the array list of assessments
     * associated with that member, for session and display on the page.
      */
    public static void index()
	  {
	    Logger.info("Rendering Dashboard");
	    Member member = Accounts.getLoggedInMember();
	    List<Assessment> assessmentlist = new ArrayList<Assessment>(member.assessmentlist);
	    render("dashboard.html", member, assessmentlist);
	  }

    /**
     * This method renders the trainers trainerdashboard.html page, and stores that trainer, and the array list of
     * all members, for session and display on the page.
     */
    public static void trainerIndex()
    {
        Logger.info("Rendering Dashboard");
        Trainer trainer = TrainerAccounts.getLoggedInTrainer();
        List<Member> memberlist = Member.findAll();
        render("trainerdashboard.html", trainer, memberlist);
    }

    /**
     * This method renders the trainers view of a specific member in a viewmember.html page, and stores that trainer
     * and the member, and an array list of assessments associated with that member, for session and display on the page.
     * @param memberid
     */
    public static void viewMember(Long memberid)
    {
        Logger.info("Rendering Dashboard");
        Trainer trainer = TrainerAccounts.getLoggedInTrainer();
        Member member = Member.findById(memberid);
        List<Assessment> assessmentlist = member.assessmentlist;
        render("viewmember.html", trainer, member, assessmentlist);
    }

    /**
     * This method deletes a specific member, and removes them from the array list of members. Trainer is then
     * redirected to the /trainer/dashboard route, to return to the page they were on, but with deleted member
     * no longer displaying.
     * @param memberid the id of the member to be deleted
     */
    public static void deleteMember(Long memberid)
    {
        Trainer trainer = TrainerAccounts.getLoggedInTrainer();
        List<Member> memberlist = trainer.memberlist;
        Member member = Member.findById(memberid);
        trainer.memberlist.remove(member);
        trainer.save();
        member.delete();
        Logger.info("Deleting " + memberid);
        redirect("/trainer/dashboard");
    }

    /**
     * Update the trainer comment on a specific assessment in this member's array list of assessments.
     * Trainer then has viewmember.html rendered again, with updated info.
     * @param memberid the id of the member who's page this is, and who holds this array list
     * @param assessmentid the id of the specific assessment to be updated with a comment
     * @param comment the comment to be added to the assessment
     */
    public static void updateComment(Long memberid, Long assessmentid, String comment)
    {
        Trainer trainer = TrainerAccounts.getLoggedInTrainer();
        List<Member> memberlist = trainer.memberlist;
        Member member = Member.findById(memberid);
        List<Assessment> assessmentlist = member.assessmentlist;
        Assessment setAssessment = Assessment.findById(assessmentid);
        setAssessment.setComment(comment);
        setAssessment.save();
        Logger.info("Updating comment " + setAssessment.comment);//comment is listed temporarily until date is sorted
        //redirect("/trainer/dashboard/viewmember/${memberid}/updatecomment/${assessmentid}");
        render("viewmember.html", trainer, member, assessmentlist, comment);
    }

    /**
     * As a trainer, delete a specific assessment from this member's array list of assessments, from the trainer
     * page viewing this member. Trainer then had viewmember.html rendered again, with updated info.
     *
     * @param memberid the id of the member who's assessment is to be deleted
     * @param assessmentid the id of the assessment to be deleted
     */
    public static void deleteMemberAssessment(Long memberid, Long assessmentid)
    {
        Trainer trainer = TrainerAccounts.getLoggedInTrainer();
        List<Member> memberlist = trainer.memberlist;
        Member member = Member.findById(memberid);
        List<Assessment> assessmentlist = member.assessmentlist;
        Assessment assessment = Assessment.findById(assessmentid);
        member.assessmentlist.remove(assessment);
        member.save();
        assessment.delete();
        Logger.info("Deleting ");//weight is listed temporarily until date is sorted
        //redirect("/trainer/dashboard/viewmember/{memberid}/deleteassessment/{assessmentid}");
        render("viewmember.html", trainer, member, assessmentlist);
    }

    /**
     * As a member, delete an assessment from the member's own array list, from the member's own dashboard.
     * Member then has dashboard.html rendered again, with updated info.
     * @param memberid the id of the member who's session this is
     * @param assessmentid the id of the assessment the member will delete
     */
  public static void deleteAssessment(Long memberid, Long assessmentid)
  {
    Member member = Member.findById(memberid);
    List<Assessment> assessmentlist = member.assessmentlist;
    Assessment assessment = Assessment.findById(assessmentid);
    member.assessmentlist.remove(assessment);
    member.save();
    assessment.delete();
    Logger.info("Deleting ");//weight is listed temporarily until date is sorted
      render("dashboard.html", member, assessmentlist);
  }

    /**
     * As a member, add a new assessment to the end of your array list of assessments. Redirect to dashboard.html through
     * /dashboard, with updated info.
     * @param date the new date of the member
     * @param weight the new weight of the member
     * @param chest the new chest measurement
     * @param thigh the new thigh measurement
     * @param upperArm the new upper arm measurement
     * @param waist the new waist measurement
     * @param hips the new hips measurement
     */
  public static void addAssessment(Date date, double weight, double chest, double thigh, double upperArm, double waist, double hips)
  {
      Member member =Accounts.getLoggedInMember();
      List<Assessment> assessmentlist = member.assessmentlist;
        Assessment assessment = new Assessment( date, weight, chest, thigh, upperArm, waist, hips);
        member.assessmentlist.add(assessment);
        member.save();
        assessment.save();
        Logger.info("Adding Assessment created on: " + date);
        redirect("/dashboard");
  }
  
 
}
