package models;

import play.db.jpa.Model;
import javax.persistence.Entity;

import javax.persistence.Entity;

/**
 * Created by Robert Alexander on 12/05/2017.
 */
@Entity
public class Trainer extends Person {
    public String speciality;

    public Trainer(String firstname, String lastname, String email, String password, String speciality)
    {
        super(firstname, lastname, email, password);
        this.speciality = speciality;
    }

}
