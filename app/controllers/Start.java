package controllers;

import play.Logger;
import play.mvc.Controller;

/**
 * Created by Robert Alexander on 18/05/2017.
 */
public class Start extends Controller
{
  /**
   * This method renders the landing page(start.html) of the site.
   */
  public static void index() {
    Logger.info("Rendering Start");
    render ("start.html");
  }
}
