package controllers;

//import models.Todo;
import models.Assessment;
import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;
import java.util.List;
import java.util.ArrayList;

public class Dashboard extends Controller
{
	 public static void index()
	  {
	    Logger.info("Rendering Dashboard");
	    Member member = Accounts.getLoggedInMember();
	    List<Assessment> assessmentlist = new ArrayList<>(member.assessmentlist);
	    render("dashboard.html", member, assessmentlist);
	  }

    public static void trainerIndex()
    {
        Logger.info("Rendering Dashboard");
        Trainer trainer = TrainerAccounts.getLoggedInTrainer();
        List<Member> memberlist = Member.findAll();
        render("trainerdashboard.html", trainer, memberlist);
    }

    public static void viewMember(Long memberid)
    {
        Logger.info("Rendering Dashboard");
        Trainer trainer = TrainerAccounts.getLoggedInTrainer();
        Member member = Member.findById(memberid);
        List<Assessment> assessmentlist = member.assessmentlist;

        render("viewmember.html", trainer, member, assessmentlist);
    }

    public static void deleteMember(Long memberid)
    {
        Trainer trainer = TrainerAccounts.getLoggedInTrainer();
        List<Member> memberlist = trainer.memberlist;
        Member member = Member.findById(memberid);
        trainer.memberlist.remove(member);
        trainer.save();
        member.delete();
        Logger.info("Deleting " + memberid);//weight is listed temporarily until date is sorted
        redirect("/trainer/dashboard");
    }

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


  /*
  public static void addTodo(String title)
  {
	  Member member = Accounts.getLoggedInMember();
	    Todo todo = new Todo(title);
	    member.todolist.add(todo);
	    member.save();
	    Logger.info("Adding Todo" + title);
	    redirect("/dashboard");
  }
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


  public static void addAssessment(double weight, double chest, double thigh, double upperArm, double waist, double hips)
  {
      Member member =Accounts.getLoggedInMember();
      List<Assessment> assessmentlist = member.assessmentlist;
        Assessment assessment = new Assessment(weight, chest, thigh, upperArm, waist, hips);
        member.assessmentlist.add(assessment);
        member.save();
        assessment.save();
        Logger.info("Adding Assessment" + "Assessment NUMBER OR DATE GOES HERE");
        redirect("/dashboard");
  }
  
 
}
