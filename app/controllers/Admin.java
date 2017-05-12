package controllers;

//import java.util.ArrayList;
import java.util.List;
import models.Todo;

//import models.Playlist;
//import models.Song;
//import play.Logger;
import play.mvc.Controller;

import play.Logger;
import play.mvc.Controller;
import java.util.ArrayList;


public class Admin extends Controller {
	public static void index()
	{
		//Logger.info("Rendering Admin");
		
		//TODO- get list of all songs, and send them to the view
		 List<Todo> todolist = Todo.findAll();
		
		render ("admin.html", todolist);
	}

}
