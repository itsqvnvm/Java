/*
    * Tester class
    * This class is a subclass of Employee class
    * This class is used to store information of a tester, including all data below
    * Tester ID, which ID is unique
    * Tester name
    * Tester base salary
    * Tester bonus rate
    * Tester type
    * Next, function in this class is
    * Get tester bonus rate
    * Get tester type
    * Set tester bonus rate
    * Set tester type
    * Get tester salary
    * Convert tester information to string, that is used to write to file or print to screen
    * Get salary of tester, that is used to calculate salary of tester
    * Write information of tester to file, which format: T, Tester ID, Tester name, Tester base salary, Tester bonus rate, Tester type
 */
package model;

/**
 *
 * @author quangvinhdao
 */
public class Tester extends Employee {

    protected double bonusRate;
    protected String type;

    public Tester(String empID, String empName, double baseSal, double bonusRate, String type) {
        super(empID, empName, baseSal);
        this.bonusRate = bonusRate;
        this.type = type;
    }

    public double getBonusRate() {
        return bonusRate;
    }

    public String getType() {
        return type;
    }

    public void setBonusRate(double bonusRate) {
        this.bonusRate = bonusRate;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSalary() {
        //Salary = developer salary + bonusRate * developer salary
        double salary = baseSal;
        salary += baseSal * bonusRate;
        return salary;
    }

    public String toString() {
        String s = String.format("%s_%s_%.2f_%.2f_%s", empID, empName, baseSal, bonusRate, type);
        return s;

    }

    public String writeInfo() {
        String s = String.format("%s,%s,%s,%.2f,%.2f,%s", "T", empID, empName, baseSal, bonusRate, type);
        return s;
    }

}
