package controllers;

import models.Trainer;
import play.Logger;
import play.mvc.Controller;

/**
 * Created by Robert Alexander on 12/05/2017.
 */
public class TrainerAccounts extends Controller
{

    public static void splashpage() {
        Logger.info("Rendering Trainer Start");
        render ("trainerstart.html");
    }


    public static void trainerLogin()
    {
        render("trainerlogin.html");
    }


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

    public static void logout()
    {
        session.clear();
        redirect ("/");
    }

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
