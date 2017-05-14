package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert Alexander on 13/05/2017.
 */
@Entity
public class Assessment extends Model {
    public double weight;
    public double chest;
    public double thigh;
    public double upperArm;
    public double waist;
    public double hips;
    public String comment;
    //public Trainer trainer;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Assessment> assessmentlist = new ArrayList<Assessment>();

    public Assessment(double weight, double chest, double thigh, double upperArm, double waist, double hips) {
        this.weight = weight;
        this.chest = chest;
        this.thigh = thigh;
        this.upperArm = upperArm;
        this.waist = waist;
        this.hips = hips;
        this.comment = "";
        //this.trainer = trainer;
    }

    public static double calculateBMI (Member member, Assessment assessment)
    {
        if (member.getHeight() <= 0)
            return 0;
        else
        {
            return toTwoDecimalPlaces(assessment.getWeight() / (member.getHeight() * member.getHeight()));
        }
    }

    private static double toTwoDecimalPlaces ( double num)
    {
        return (int)(num * 100) / 100.0;
    }


//********************************************************************************
    //  SETTERS
    //********************************************************************************

    /**
     * Updates the assessment weight field. The Member's weight, as measured during this assessment.
     *
     * @param weight There is no validation on the assessment weight.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Updates the assessment chest field. The Member's chest, as measured during this assessment.
     *
     * @param chest There is no validation on the assessment chest.
     */
    public void setChest(double chest) {
        this.chest = chest;
    }

    /**
     * Updates the assessment thigh field. The Member's thigh, as measured during this assessment.
     *
     * @param thigh There is no validation on the assessment thigh.
     */
    public void setThigh(double thigh) {
        this.thigh = thigh;
    }

    /**
     * Updates the assessment upperArm field. The Member's upperArm, as measured during this assessment.
     *
     * @param upperArm There is no validation on the assessment upperArm.
     */
    public void setUpperArm(double upperArm) {
        this.upperArm = upperArm;
    }

    /**
     * Updates the assessment waist field. The Member's waist, as measured during this assessment.
     *
     * @param waist There is no validation on the assessment waist.
     */
    public void setWaist(double waist) {
        this.waist = waist;
    }

    /**
     * Updates the assessment hips field. The Member's hips, as measured during this assessment.
     *
     * @param hips There is no validation on the assessment hips.
     */
    public void setHips(double hips) {
        this.hips = hips;
    }

    /**
     * Updates the assessment comment field. The comment left by the Trainer about this Member,
     * written for this assessment.
     * @param comment There is no validation on the assessment comment.
     */
    // public void setComment(String comment) {
    //     this.comment = comment;
    // }


    //********************************************************************************
    //  GETTERS
    //********************************************************************************

    /**
     * Returns the Assessment weight.
     *
     * @return the assessment weight.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Returns the assessment chest measurement.
     *
     * @returnthe assessment chest measurement.
     */
    public double getChest() {
        return chest;
    }

    /**
     * Returns the assessment thigh measurement.
     *
     * @return the assessment thigh measurement.
     */
    public double getThigh() {
        return thigh;
    }

    /**
     * Returns the assessment upperArm measurement.
     *
     * @return the assessment upperArm measurement.
     */
    public double getUpperArm() {
        return upperArm;
    }

    /**
     * Returns the assessment waist measurement.
     *
     * @return the assessment waist measurement.
     */
    public double getWaist() {
        return waist;
    }

    /**
     * Returns the assessment hips measurement.
     *
     * @return the assessment hips measurement.
     */
    public double getHips() {
        return hips;
    }

    /**
     * Returns the assessment comment from trainer.
     *
     * @return the assessment comment from trainer.
     */
    // public String getComment() {
    //     return comment;
    // }
    @Override
    public String toString() {
        return "Assessment{" +
                "weight=" + weight +
                ", chest=" + chest +
                ", thigh=" + thigh +
                ", upperArm=" + upperArm +
                ", waist=" + waist +
                ", hips=" + hips +
                //", comment='" + comment + '\'' +
                //", trainer=" + trainer +
                '}';
    }

}