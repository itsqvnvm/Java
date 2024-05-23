/*
    * Interface for reading and writing data to file

 */

package fileio;

import java.util.List;

public interface IFileReadWrite<E> // E is the type of object that will be read and written to file
{
    
    List<E> read()throws Exception; // read data from file and return a list of objects
    boolean write(List<E> list)throws Exception; // write data to file
}
