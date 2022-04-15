package UserInterface;

import java.util.Scanner;

public class UserInterface {
    public static void askInitialPos(){
        Scanner inputScaner = new Scanner(System.in);
        System.out.println("Log the initial position of the horse (A1):");
        String pos = inputScaner.nextLine();
        if (pos.length() != 2){
            System.out.println("pos takes one character from A to H and a number from 1 to 8");
        }
    }
}
