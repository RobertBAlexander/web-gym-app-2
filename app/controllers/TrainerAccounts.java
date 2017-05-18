package controllers;

import models.Trainer;
import play.Logger;
import play.mvc.Controller;

/**
 * Created by Robert Alexander on 12/05/2017.
 */
public class TrainerAccounts extends Controller
{

    /**
     * The trainer landing page which can only be accesses through typing /trainer after the main page address.
     */
    public static void splashpage() {
        Logger.info("Rendering Trainer Start");
        render ("trainerstart.html");
    }

    /**
     * This method renders the trainers trainerlogin.html page.
     */
    public static void trainerLogin()
    {
        render("trainerlogin.html");
    }

    /**
     * This method authenticates the trainer on the trainer login page, and redirects to the trainerdashboard.html
     * storing the trainer for the session. If there is no match, user is sent back to the trainer login page.
     * @param email the e-mail of the trainer
     * @param password the password of the trainer
     */
    public static void trainerAuthenticate(String email, String password)
    {
        Logger.info("Attempting to authenticate with " + email + ":" + password);

        Trainer trainer = Trainer.findByEmail(email);
        if ((trainer != null) && (trainer.checkPassword(password) == true)) {
            Logger.info("Authentication successful");
            session.put("logged_in_Trainerid", trainer.id);
            redirect ("/trainer/dashboard");
        }

        else {
            Logger.info("Authentication failed");
            redirect("/trainer/login");
        }
    }

    /**
     * This method allows a trainer to logout, and redirects to the start page
     */
    public static void logout()
    {
        session.clear();
        redirect ("/");
    }

    /**
     * This method gets the logged in trainer and stores it for this session.
     * @return the logged in trainer
     */
    public static Trainer getLoggedInTrainer()
    {
        Trainer trainer = null;
        if (session.contains("logged_in_Trainerid")) {
            String trainerId = session.get("logged_in_Trainerid");
            trainer = Trainer.findById(Long.parseLong(trainerId));
        } else {
            trainerLogin();
        }
        return trainer;
    }


}
