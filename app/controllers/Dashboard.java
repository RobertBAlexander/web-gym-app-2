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
	    List<Assessment> assessmentlist = member.assessmentlist;
	    render("dashboard.html", member, assessmentlist);
	  }

    public static void trainerIndex()
    {
        Logger.info("Rendering Dashboard");
        Trainer trainer = TrainerAccounts.getLoggedInTrainer();
        List<Member> memberlist = trainer.memberlist;
        render("trainerdashboard.html", trainer, memberlist);
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
  public static void deleteAssessment(Long id, Long assessmentid)
  {
    Member member = Member.findById(id);
    Assessment assessment = Assessment.findById(assessmentid);
    member.assessmentlist.remove(assessment);
    member.save();
    assessment.delete();
    Logger.info("Deleting " + assessment.weight);//weight is listed temporarily until date is sorted
    redirect("/dashboard");
  }


  public static void addAssessment(double weight, double chest, double thigh, double upperArm, double waist, double hips)
  {
      Member member =Accounts.getLoggedInMember();
        Assessment assessment = new Assessment(weight, chest, thigh, upperArm, waist, hips);
        member.assessmentlist.add(assessment);
        member.save();
        Logger.info("Adding Assessment" + "Assessment NUMBER OR DATE GOES HERE");
        redirect("/dashboard");
  }
  
 
}
