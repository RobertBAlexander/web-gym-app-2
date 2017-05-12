package controllers;

import models.Todo;
import models.Member;
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
	    List<Todo> todolist = member.todolist;
	    render("dashboard.html", member, todolist);
	  }
  
  public static void addTodo(String title)
  {
	  Member member = Accounts.getLoggedInMember();
	    Todo todo = new Todo(title);
	    member.todolist.add(todo);
	    member.save();
	    Logger.info("Adding Todo" + title);
	    redirect("/dashboard");
  }
  
  public static void deleteTodo(Long id, Long todoid)
  {
    Member member = Member.findById(id);
    Todo todo = Todo.findById(todoid);
    member.todolist.remove(todo);
    member.save();
    todo.delete();
    Logger.info("Deleting " + todo.title);
    redirect("/dashboard");
  }
  
 
}
