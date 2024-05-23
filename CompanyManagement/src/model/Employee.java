/*
 * This class is the abstract class for all employee types
 * It contains the basic information of an employee
 * It also contains the abstract method getSalary() and writeInfo()
 * The getSalary() method will be implemented in the subclasses
 * The writeInfo() method will be implemented in the subclasses
 * */
package model;

import java.io.Serializable;

/**
 *
 * @author quangvinhdao
 */
public abstract class Employee implements Serializable // "implements Serializable" is used to serialize the object, so that we can save the object to a file
    //Serialization trong Java là cơ chế chuyển đổi trạng thái của một đối tượng (giá trị các thuộc tính trong object
    // thành một chuỗi byte sao cho chuỗi byte này có thể chuyển đổi ngược lại thành một đối tượng.
{
    protected String empID;
    protected String empName;
    protected double baseSal;

    public Employee(String empID, String empName, double baseSal) {
        this.empID = empID;
        this.empName = empName;
        this.baseSal = baseSal;
    }
    
    public String getEmpID() {
        return empID;
    }

    public String getEmpName() {
        return empName;
    }

    public double getBaseSal() {
        return baseSal;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setBaseSal(double baseSal) {
        this.baseSal = baseSal;
    }
    
    public abstract double getSalary();

    public abstract String writeInfo();
    
}
