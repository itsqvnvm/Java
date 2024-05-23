// This file is the implementation of the IFileReadWrite interface. It reads and writes data to a text file.
/* This file have work below
    * Read data from file, that will be used to read the data from the file, and that data can be use for the next time
    * Write data to file, that will be used to write the data to the file, and that data can be use for the next time
* */

package fileio;

import model.Employee;
import model.Tester;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextFile implements IFileReadWrite<Employee> {
    private final String fileName = "./src/fileio/Employees.txt";

    @Override
    public List<Employee> read() throws Exception { // read the data from the file
        List<Employee> result = new ArrayList<>(); // create a list to store the data
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                return null;
            }

            BufferedReader buff = null; // create a buffer to read the file

            FileInputStream input = new FileInputStream(file); // create a file input stream to read the file
            buff = new BufferedReader(new InputStreamReader(input)); // create a buffer reader to read the file line by line
            String line;
            while ((line = buff.readLine()) != null) {
                if (line.trim().isEmpty()) { // check if the line is empty
                    continue;
                }
                Employee emp = null;
                String split[] = line.split(","); // split the line by comma
                switch (split[0].charAt(0)) {
                    case 'T':
                        String empID = split[1].trim(); // get the employee ID from the file
                        String empName = split[2].trim(); // get the employee name from the file
                        double baseSal = Double.parseDouble(split[3].trim()); // get the base salary from the file
                        double bonus = Double.parseDouble(split[4].trim()); // get the bonus from the file
                        String type = split[5].trim(); // get the type from the file
                        emp = new Tester(empID, empName, baseSal, bonus, type); // create a new tester object
                        break;
                    case 'D':
                        empID = split[1].trim(); // get the employee ID from the file
                        empName = split[2].trim(); // get the employee name from the file
                        baseSal = Double.parseDouble(split[3].trim()); // get the base salary from the file
                        String teamName = split[4].trim(); // get the team name from the file
                        int expYear = Integer.parseInt(split[5].trim()); // get the experience year from the file
                        emp = new model.Developer(empID, empName, baseSal, teamName, expYear); // create a new developer object
                        break;
                    case 'L':
                        empID = split[1].trim(); // get the employee ID from the file
                        empName = split[2].trim(); // get the employee name from the file
                        baseSal = Double.parseDouble(split[3].trim()); // get the base salary from the file
                        teamName = split[4].trim(); // get the team name from the file
                        expYear = Integer.parseInt(split[5].trim()); // get the experience year from the file
                        double bonusRate = Double.parseDouble(split[6].trim()); // get the bonus rate from the file
                        emp = new model.TeamLeader(empID, empName, baseSal, teamName, expYear, bonusRate); // create a new team leader object
                        break;
                }
                result.add(emp); // add the employee to the list to store the data
            }
        } catch (Exception e) { // catch the exception

        }
        return result.isEmpty() ? null : result; // return the result, if not, return null
    }

    @Override
    public boolean write(List<Employee> list) throws Exception { // write the data to the file
        try {
            FileWriter fw = new FileWriter(fileName); // create a file writer to write the data to the file
            for (Employee e : list) { // loop through the list
                String s = e.writeInfo(); // get the information of the employee
                fw.write(s + "\n"); // write the information to the file
            }
            fw.close(); // close the file writer
        } catch (Exception e) {
            throw e; // throw the exception
        }
        return true; // return true when the data is written successfully
    }

}
