package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;



/**
 * Created by Robert Alexander on 18/05/2017.
 */

public class About extends Controller
{
  /**
   * This method renders the about.html page
   *
   */
  public static void index() {
    Logger.info("Rendering about");
    render ("about.html");
  }
}
