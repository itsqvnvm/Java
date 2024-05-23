/*
    * Developer class
    * This class is a subclass of Employee
    * This class is used to store information of a developer, including all data below
    * Developer ID, which ID is unique
    * Developer name
    * Developer base salary
    * Developer team name
    * Developer experience year
    * Next, function in this class is
    * Get developer team name
    * Get developer experience year
    * Set developer team name
    * Set developer experience year
    * Get developer salary
    * Convert developer information to string, that is used to write to file or print to screen
    * Write information of developer to file, which format: D, Developer ID, Developer name, Developer base salary, Developer team name, Developer experience year
    * Get salary of developer, that is used to calculate salary of developer
*/
package model;

/**
 *
 * @author quangvinhdao
 */
public class Developer extends Employee {

    protected String teamName;
    protected int expYear;

    public Developer(String empID, String empName, double baseSal, String teamName, int expYear) {
        super(empID, empName, baseSal);
        this.teamName = teamName;
        this.expYear = expYear;
    }
    public String getTeamName() {
        return teamName;
    }

    public double getExpYear() {
        return expYear;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setExpYear(int expYear) {
        this.expYear = expYear;
    }

    public double getSalary() {
        //Salary = developer salary + bonusRate * developer salary
        double salary = baseSal;
        double bonus = 0;
        if (expYear >= 5) {
            bonus = expYear * 2000000;
        } else if (expYear >= 3) {
            bonus = expYear * 1000000;
        }

        salary += bonus;
        return salary;
    }

    @Override
    public String toString() {
        String s = String.format("%s_%s_%.2f_%s_%d", empID, empName, baseSal, teamName, expYear);
        return s;
    }

    public String writeInfo() {
        String s = String.format("%s,%s,%s,%.2f,%s,%d", "D", empID, empName, baseSal, teamName, expYear);
        return s;
    }
}
