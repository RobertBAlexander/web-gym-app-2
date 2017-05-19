package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert Alexander on 12/05/2017.
 */
@Entity
public class Trainer extends Model {
    public String firstname;
    public String lastname;
    public String email;
    public String password;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Member> memberlist = new ArrayList<Member>();

    /**
     * This method creates a new trainer
     * @param firstname  of the trainer
     * @param lastname  of the trainer
     * @param email  of the trainer
     * @param password of the trainer
     */
    public Trainer(String firstname, String lastname, String email, String password)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    /**
     * find the trainer by his e-mail
     * @param email to use in searching trainer
     * @return
     */
    public static Trainer findByEmail(String email)
    {
        return find("email", email).first();
    }

    /**
     * check the password is correct
     * @param password the password to check against
     * @return
     */
    public boolean checkPassword(String password)
    {
        return this.password.equals(password);
    }

    /**
     * Add a new member to this trainer array list of members
     * @param member to add to array list
     */
    public void addMember(Member member) {

        memberlist.add(member);
    }
}
