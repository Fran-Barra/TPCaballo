package UserInterface;

import java.util.Scanner;

public class UserInterface {
    static void askInitialPos(){
        Scanner inputScaner = new Scanner(System.in);
        System.out.println("Log the initial position of the horse (A1):");
        String pos = inputScaner.nextLine();
    }
}
