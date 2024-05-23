/*
    * TeamLeader.java
    * This class is a subclass of Developer class
    * This class is used to store information of a team leader, including all data below
    * Team leader ID, which ID is unique
    * Team leader name
    * Team leader base salary
    * Team leader team name
    * Team leader experience year
    * Team leader bonus rate
    * Next, function in this class is
    * Get team leader bonus rate
    * Set team leader bonus rate
    * Get team leader salary
    * Convert team leader information to string, that is used to write to file or print to screen
    * Write information of team leader to file, which format: L, Team leader ID, Team leader name, Team leader base salary, Team leader team name, Team leader experience year, Team leader bonus rate
    * Get salary of team leader, that is used to calculate salary of team leader
 */
package model;

/**
 *
 * @author quangvinhdao
 */
public class TeamLeader extends Developer {

    protected double bonusRate;

    public TeamLeader(String empID, String empName, double baseSal,
            String teamName, int expYear, double bonusRate) {
        super(empID, empName, baseSal, teamName, expYear);
        this.bonusRate = bonusRate;
    }

    public double getBonusRate() {
        return bonusRate;
    }

    public void setBonusRate(double bonusRate) {
        this.bonusRate = bonusRate;
    }

    public double getSalary() {
        //Salary = developer salary + bonusRate * developer salary
        double salary = super.getSalary();
        salary += baseSal * bonusRate;
        return salary;
    }

    public String toString() {
        String s = String.format("%s_%s_%.2f_%s_%d_%.2f", empID, empName, baseSal, teamName, expYear, bonusRate);
        return s;
    }

    public String writeInfo() {
        String s = String.format("%s,%s,%s,%.2f,%s,%d,%.2f", "L", empID, empName, baseSal, teamName, expYear, bonusRate);
        return s;
    }
}
