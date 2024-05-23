/*
 * This class is used to read and write data to a binary file.
 * It implements IFileReadWrite interface.
 * It has two methods read and write.
 * Which read methods have work below
 * 1. Create a File object with the file name, make sure that the file exists and ready to read.
 * 2. Create a FileInputStream object with the file object.
 * 3. Create an ObjectInputStream object with the FileInputStream object
 * 4. Read the object from the ObjectInputStream object.
 * 5. If the object is not null, cast the object to List<Employee> and assign it to the result.
 * 6. Return the result.
 * 7. If any exception occurs, throw it.
 * 8. Finally, close the ObjectInputStream and FileInputStream objects.
 * Which write methods have work below
 * 1. Create a File object with the file name.
 * 2. Create a FileOutputStream object with the file object.
 * 3. Create an ObjectOutputStream object with the FileOutputStream object.
 * 4. Write the list to the ObjectOutputStream object.
 * 5. If any exception occurs, throw it, make sure that the file is closed.
 * 6. Finally, close the ObjectOutputStream and FileOutputStream objects.
 * 7. Return true.
 */

package fileio;

import model.Employee;

import java.io.*;
import java.util.List;
import java.util.Objects;


public class BinaryFile implements IFileReadWrite<Employee> {

    private final String fileName = "src/fileio/Employees.dat"; //Create a file name in the src/fileio folder

    @Override
    public List<Employee> read() throws Exception {
        List<Employee> result = null; //Create a list of Employee object to store the data from the file
        File file = null; //Create a file object to store the file name
        FileInputStream fileIn = null; // Create a FileInputStream object to read the file, which FileInputStream is a byte stream, that will read the file byte by byte
        ObjectInputStream outIn = null; // Create an ObjectInputStream object to read the object from the file, which ObjectInputStream is a byte stream, that will read the object from the file
        try { //Try to read the file, if any exception occurs, throw it
            file = new File(fileName); //Create a file object with the file name
            fileIn = new FileInputStream(file); //Create a FileInputStream object with the file object
            outIn = new ObjectInputStream(fileIn); //Create an ObjectInputStream object with the FileInputStream object
            Object obj = outIn.readObject(); //Read the object from the ObjectInputStream object
            if (obj != null) {
                result = (List<Employee>) obj; //If the object is not null, cast the object to List<Employee> and assign it to the result
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (outIn != null) {
                outIn.close(); //Close the ObjectInputStream object after reading the object
            }

            if (fileIn != null) {
                fileIn.close(); //Close the FileInputStream object after reading the file
            }
        }

        return result; //Return the result if the file is read successfully, then will appear the list of Employee object
    }

    @Override
    public boolean write(List<Employee> list) throws Exception { //Write the list of Employee object to the file
        File file = null; //Create a file object to store the file name
        FileOutputStream fileOut = null; //Create a FileOutputStream object to write the file, which FileOutputStream is a byte stream, that will write the file byte by byte
        ObjectOutputStream output = null; //Create an ObjectOutputStream object to write the object to the file, which ObjectOutputStream is a byte stream, that will write the object to the file
        try { //Try to write the file, if any exception occurs, throw it
            file = new File(fileName); //Create a file object with the file name
            fileOut = new FileOutputStream(file); //Create a FileOutputStream object with the file object
            output = new ObjectOutputStream(fileOut); //Create an ObjectOutputStream object with the FileOutputStream object
            output.writeObject(list); //Write the list to the ObjectOutputStream object

        } catch (Exception e) {
            throw e;
        } finally {
            if (output != null) {
                output.close(); //Close the ObjectOutputStream object after writing the object
            }

            if (fileOut != null) {
                fileOut.close(); //Close the FileOutputStream object after writing the file
            }
        }
        return true; //Return true if the file is written successfully
    }

}
