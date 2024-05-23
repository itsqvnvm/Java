/*
    * Menu.java
    * This class is used to display the menu and get the choice from the user
    * The getChoice() method will display the menu and get the choice from the user
 */

package viewer;

import java.util.Scanner;

public class Menu {
    
    public static int getChoice(Object[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.print("Your options from 1 - " + options.length + ": ");
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }
}
