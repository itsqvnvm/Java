/* This file is used to manage the employees in the company
 * This file have work below
     * Add an employee to the company, that will be used to add the new employee to the company
     * Get an employee by ID, that will be used to check if the ID is exist, if exist, the employee will not be added
     * Check if the code is exist, that will be available for add the new employee
     * Check if the team name is exist, that will be used to check if the team name is exist, if exist, the team name will not be added
     * Get the employee by name, that will be used to search the employee by name
     * Get the employee by salary higher, that will be used to search the employee by salary
     * Sort the employee by salary and name ascending, by using the comparator to sort the employee by salary and name
     * Write the data to file, make sure the data is saved to the file, and that data can be use for the next time
     * Read the data from file, make sure the data is read from the file, and that data can be use for the next time
* */


package controller;

import fileio.BinaryFile;
import fileio.IFileReadWrite;
import fileio.TextFile;
import model.Employee;
import model.TeamLeader;
import model.Developer;

import java.sql.SQLOutput;
import java.util.*;

public class CompanyManagement extends ArrayList<Employee> // CompanyManagement class extends from ArrayList<Employee> class
    {
    public CompanyManagement() {
        this.read();
    }

    public boolean addEmployee(Employee emp) {
        if (emp == null) // this code is meaning that if the employee is null, it will return false
        {
            return false;
        }
        // if the employee is not null, it will add the employee to the company
        return this.add(emp);
    }

    public Employee getEmployee(String code)
    {
        Employee emp = null;

        for (Employee e : this) {
            if (e.getEmpID().equals(code)) // if the employee ID is equal to the code, it will return the employee
            {
                emp = e;
                break;
            }
        }

        return emp;
    }

    public boolean isExistCode(String code) // this function is used to check if the ID is exist, if exist, it will return true, so that the code is exist and do not add the code until the code is not exist before
            // that make sure that the code is unique
    {
        return (this.getEmployee(code) != null);
    }

    public boolean isExistTeamName(String teamName) // this function is used to check if the team name is exist, if exist, it will return true, so that the team name is exist and do not add the team name until the team name is not exist before
            // that make sure that the team name is unique
    {
        for (Employee e : this) // this loop is used to check if the team name is exist, if exist, it will return true
        {
            if (e instanceof TeamLeader) // if the employee is a TeamLeader, it will check if the team name is exist
            {
                TeamLeader leader = (TeamLeader) e; // cast the employee to TeamLeader, so that we can get the team name
                if (leader.getTeamName().equals(teamName)) // if the team name is equal to the team name, it will return true, so that the team name is exist
                {
                    System.out.println("Team name is exist");
                    return true;
                }
                break;
            }
        }
        return false;
    }

    public CompanyManagement getEmployeeByName(String Name) {
        CompanyManagement result = new CompanyManagement(); // create a new CompanyManagement object
        for (Employee e : this) //this loop meaning that loop for get the employee by name, then add the employee to the result
        {
            if (e.getEmpName().contains(Name)) {
                result.addEmployee(e);
            }
        }

        return result.isEmpty() ? null : result; // if the result is empty, it will return null, else it will return the result
    }

    public CompanyManagement getEmployeeBySalaryHigher(double salary) {
        CompanyManagement result = new CompanyManagement();
        for (Employee e : this) {
            if (e.getSalary() > salary) // if the salary is higher than the salary, it will add the employee to the result
            {
                result.addEmployee(e); // add the employee to the result
            }
        }

        return result.isEmpty() ? null : result; // if the result is empty, it will return null, else it will return the result
    }

    public CompanyManagement sortBySalaryNameAsc() {
        CompanyManagement result = (CompanyManagement) this.clone(); // clone the company, make sure that the company is not changed

        Comparator com = new Comparator<Employee>() // create a new comparator to compare the employee by salary and name
        {
            @Override
            public int compare(Employee o1, Employee o2) {
                int i = (int) (o1.getSalary() - o2.getSalary()); // compare the salary of the employee
//             i = Double.compare(o1.getSalary(), o2.getSalary()); (tuong duong phia tren)
                if (i == 0) {
                    i = o1.getEmpName().compareTo(o2.getEmpName());
                }
                return i;
            }
        };
        Collections.sort(result, com); // sort the employee by salary and name in ascending order
        return result;
    }

    public boolean write() throws Exception //this function is used to write the data to the file
    {
        IFileReadWrite file = new TextFile(); // create a new TextFile object
        //IFileReadWrite file = new BinaryFile(); // create a new BinaryFile object
        return file.write(this);
    }

    private boolean read()  //this function is used to read the data from the file
    {
        try // try to read the data from the file
        {
            IFileReadWrite file = new TextFile(); // create a new TextFile object
            //IFileReadWrite file = new BinaryFile(); // create a new BinaryFile object
            List<Employee> list = file.read();
            if (list != null) // if the list is not null, it will clear the company, then add all the employee to the company
            {
                this.clear(); // clear the company, make sure that the company is empty
                this.addAll(list); // add all the employee to the company
                return true; // if the list is not null, it will return true, meaning that the data is read from the file
            }
        } catch (Exception e) {
            this.clear();
        }
        return false; // if the list is null, it will return false
    }
}
