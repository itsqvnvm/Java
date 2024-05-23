/*
 * This file have the main method to run the program include below function
 * 1. Add Employee: add new employee to the list
 * 2. Update Employee: update the employee information
 * 3. Show the Employee list: show the list of employee
 * 4. Search Employee: search the employee by name or salary
 * 5. Save: save the employee list to the file
 * 6. Sort Employees: sort the employee list by salary and name
 * 7. Exit: exit the program
 * */

package viewer;

import controller.CompanyManagement;
import model.Developer;
import model.Employee;
import model.TeamLeader;
import model.Tester;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    CompanyManagement cm = new CompanyManagement(); // create new CompanyManagement object

    public static void main(String[] args) throws Exception {
        // Menu options
        String[] options = {"Show the Employee list", "Add Employee", "Update Employee ", "Search Employee", "Save", "Sort Employees", "Exit"};
        Main main = new Main(); // create new Main object

        int choice = 0;
        System.out.println("Note: \nAll employee's salary based on the actual salary after multiply with the bonus and casted into integer!!!");
        do {
            System.out.println("\nCompany Employee Management Program");
            choice = Menu.getChoice(options); // show Menu options
            switch (choice) {
                case 1:
                    main.printEmployee(main.cm);
                    break;
                case 2:
                    main.addEmployee();
                    break;
                case 3:
                    main.updateEmployee();
                    break;
                case 4:
                    main.searchEmployee();
                    break;
                case 5:
                    main.write();
                    //main.storeDataToFile("Employees.txt", main.cm);
                    break;
                case 6:
                    main.sortByNaSalaryAsc();

                    break;
                default:
                    System.out.println("Good bye!");
            }
        } while (choice > 0 && choice < options.length); // loop until the choice is not in the range of the options
    }

    private void addEmployee() {
        String[] optionsAddEmp = {"Add Tester", "Add Developer", "Add TeamLeader", "Return to Main Menu"};

        int choice = 0;
        System.out.println("Note: \nAdd new employee's salary based on the actual salary after multiply with the bonus and casted into integer!!!");
        Scanner sc = new Scanner(System.in);

        Employee emp = null; // create new Employee object
        do {
            System.out.println("\nCompany Employee Management Program");
            choice = Menu.getChoice(optionsAddEmp); // show Menu options
            switch (choice) {
                case 1:
                    String empID;
                    do {
                        System.out.println("Enter employee ID: ");
                        empID = sc.nextLine().trim();
                    } while (cm.isExistCode(empID));    // check if the employee ID is already existed, if true, ask again, make sure that the employee ID is unique

                    System.out.println("Enter employee name: ");
                    String empName = sc.nextLine().trim(); // get employee name

                    System.out.println("Enter employee's base salary: ");
                    double baseSal = Double.parseDouble(sc.nextLine().trim()); // get employee's base salary

                    System.out.println("Enter employee's bonus: ");
                    double bonusRate = Double.parseDouble(sc.nextLine().trim()); // get employee's bonus

                    System.out.println("Enter tester type: ");
                    String type = sc.nextLine().trim(); // get tester type

                    emp = new Tester(empID, empName, baseSal, bonusRate, type); // create new Tester object
                    cm.add(emp); // add the new Tester object to the list
                    break; // break the loop

                case 2:
                    do {
                        System.out.println("Enter employee ID: ");
                        empID = sc.nextLine().trim();
                    } while (cm.isExistCode(empID));   // check if the employee ID is already existed, if true, ask again, make sure that the employee ID is unique

                    System.out.println("Enter employee name: ");
                    empName = sc.nextLine().trim(); // get employee name

                    System.out.println("Enter employee's base salary: "); // get employee's base salary
                    baseSal = Double.parseDouble(sc.nextLine().trim());

                    String teamName;
                    do {
                        System.out.println("Enter team name: ");
                        teamName = sc.nextLine().trim();
                    } while (cm.isExistTeamName(teamName)); // check if the team name is already existed, if true, ask again, make sure that the team name is unique

                    System.out.println("Enter exp year: ");
                    int expYear = Integer.parseInt(sc.nextLine().trim()); // get employee's experience year

                    emp = new Developer(empID, empName, baseSal, teamName, expYear); // create new Developer object
                    cm.add(emp); // add the new Developer object to the list
                    break;

                case 3:
                    do {
                        System.out.println("Enter employee ID: ");
                        empID = sc.nextLine().trim(); // get employee ID
                    } while (cm.isExistCode(empID)); // check if the employee ID is already existed, if true, ask again, make sure that the employee ID is unique

                    System.out.println("Enter employee name: ");
                    empName = sc.nextLine().trim(); // get employee name

                    System.out.println("Enter employee's base salary: ");
                    baseSal = Double.parseDouble(sc.nextLine().trim()); // get employee's base salary

                    do {
                        System.out.println("Enter team name: ");
                        teamName = sc.nextLine().trim(); // get team name
                    } while (cm.isExistTeamName(teamName)); // check if the team name is already existed, if true, ask again, make sure that the team name is unique

                    System.out.println("Enter exp year: ");
                    expYear = Integer.parseInt(sc.nextLine().trim()); // get employee's experience year

                    System.out.println("Enter bonus: ");
                    bonusRate = Double.parseDouble(sc.nextLine().trim()); // get employee's bonus

                    emp = new TeamLeader(empID, empName, baseSal, teamName, expYear, bonusRate); // create new TeamLeader object
                    cm.add(emp); // add the new TeamLeader object to the list
                    break;
            }

        } while (choice > 0 && choice < optionsAddEmp.length); // loop until the choice is not in the range of the options

    }

    public void updateEmployee() {
        String[] optionsUpdateEmp = {"Update Tester", "Update Developer", "Update TeamLeader", "Return to Main Menu"};

        System.out.println("Note: \nUpdate employee's salary based on the actual salary after multiply with the bonus and casted into integer!!!");
        Scanner sc = new Scanner(System.in);

        Employee emp = null; // create new Employee object

        String empID; // create new String object
        System.out.println("Enter employee ID: ");
        empID = sc.nextLine().trim(); // get employee ID

        emp = cm.getEmployee(empID); // get the employee by the employee ID
        if (emp == null) {
            System.out.println("Employee not found");
            return;
        } else {
            System.out.println("Enter employee name: ");
            String empName = sc.nextLine().trim();
            emp.setEmpName(empName); // set employee name

            System.out.println("Enter employee's base salary: ");
            double baseSal = Double.parseDouble(sc.nextLine().trim());
            emp.setBaseSal(baseSal); // set employee's base salary

            if (emp instanceof Tester) { // check if the employee is an instance of Tester
                System.out.println("Enter employee's bonus: ");
                double bonusRate = Double.parseDouble(sc.nextLine().trim());
                ((Tester) emp).setBonusRate(bonusRate); // update bonus
                System.out.println("Enter tester type: ");
                String type = sc.nextLine().trim();
                ((Tester) emp).setType(type); // update tester type
            } else if (emp instanceof TeamLeader) {
                System.out.println("Enter team name: ");
                String teamName = sc.nextLine().trim();
                ((TeamLeader) emp).setTeamName(teamName); // update team name
                System.out.println("Enter exp Year: ");
                int expYear = Integer.parseInt(sc.nextLine().trim());
                ((TeamLeader) emp).setExpYear(expYear); // update experience year
                System.out.println("Enter bonus");
                double bonusRate = Double.parseDouble(sc.nextLine().trim());
                ((TeamLeader) emp).setBonusRate(bonusRate); // update bonus
            } else {
                System.out.println("Enter team name: ");
                String teamName = sc.nextLine().trim();
                ((Developer) emp).setTeamName(teamName); // update team name
                System.out.println("Enter exp Year: ");
                int expYear = Integer.parseInt(sc.nextLine().trim());
                ((Developer) emp).setExpYear(expYear); // update experience year
            }
        }
    }

    private void printEmployee(CompanyManagement cm) {
        if (cm == null || cm.isEmpty()) // check if the employee list is empty or not, if true, print the message
        {
            System.out.println("No employee in the list");
            return;
        }
        for (Employee emp : cm) // loop through the employee list
        {
            System.out.println(emp); // print the employee information
        }
    }

    private void searchEmployee() {
        String[] optionsSearchEmp = {"Search by Name", "Search by Salary", "Return to Main Menu"};

        int choice = 0;
        System.out.println("Note: \nAdd new employee's salary based on the actual salary after multiply with the bonus and casted into integer!!!");
        Scanner sc = new Scanner(System.in);

        Employee emp = null; // create new Employee object
        do {
//            System.out.println("\nCompany Employee Management Program");
            choice = Menu.getChoice(optionsSearchEmp); // show Menu options
            switch (choice) {
                case 1:
                    System.out.println("Enter employee name: ");
                    String empName = sc.nextLine().trim();
                    CompanyManagement result = cm.getEmployeeByName(empName); // get the employee by the employee name
                    printEmployee(result); // print the employee information after searching
                    break;
                case 2:
                    System.out.println("Enter salary: ");
                    double salary = Double.parseDouble(sc.nextLine().trim()); // get the salary of the employee by the user input
                    result = cm.getEmployeeBySalaryHigher(salary); // get the employee by the salary
                    printEmployee(result);
                    break;
            }
        } while (choice > 0 && choice < optionsSearchEmp.length); // loop until the choice is not in the range of the options
    }

    private void sortByNaSalaryAsc() {
        CompanyManagement s = cm.sortBySalaryNameAsc(); // sort the employee list by salary and name
        printEmployee(s);
    }

//    public void storeDataToFile(String fileName, CompanyManagement cm) {
//        try {
//            PrintWriter writer = new PrintWriter(fileName);
//
//            for (Employee emp : cm) {
//                writer.println(emp.toString());
//            }
//
//            writer.close();
//            System.out.println("Data has been saved to file " + fileName);
//        } catch (FileNotFoundException e) {
//            System.out.println("An error occurred while trying to write to the file: " + e.getMessage());
//        }
//    }

    private void write() {
        // write the employee list to the file
        try {
            cm.write();
            System.out.println("Data has been saved to file Employees.txt");
        } catch (Exception e) {
            System.out.println("An error occurred while trying to write to the file: " + e.getMessage());
        }
    }

}

