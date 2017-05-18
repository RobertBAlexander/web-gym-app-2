package controllers;

//import java.util.ArrayList;
import java.util.List;

import models.Assessment;

//import models.Playlist;
//import models.Song;
//import play.Logger;
import play.mvc.Controller;

import play.Logger;
import play.mvc.Controller;
import java.util.ArrayList;

/**
 * Redundant class that may not be removed due to issues with backup files and deployment conflict.
 */
         public class Admin extends Controller {
 	public static void index()
 	{
        		//Logger.info("Rendering Admin");


        List<Assessment> assessmentlist = Assessment.findAll();

        render ("admin.html",assessmentlist);
        	}

         }