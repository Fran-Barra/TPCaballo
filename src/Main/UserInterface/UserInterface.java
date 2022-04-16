package UserInterface;

import Observer.InterfaceObserver;
import Observer.Observer;
import States.Events;


import java.util.Scanner;

public class UserInterface implements Observer {

    public static void askInitialPos(){
        Scanner inputScaner = new Scanner(System.in);
        System.out.println("Log the initial position of the horse (A1):\n");
        String pos = inputScaner.nextLine();
        if (pos.length() != 2){
            System.out.println("pos takes one character from A to H and a number from 1 to 8\n");
            askInitialPos();
        }
        InterfaceObserver.Notify(Events.InitialPos, new Object[] {pos});
        movementMenu();
    }

    private static void movementMenu(){
        Scanner inputScaner = new Scanner(System.in);
        System.out.println("Chose one of the followings: jump, show piles (shp), exit:\n");
        String option = inputScaner.nextLine();
        switch (option) {
            case "exit":
                return;
            case "shp":
                InterfaceObserver.Notify(Events.ShowPiles, new Object[]{});
                break;
            case "jump":
                InterfaceObserver.Notify(Events.Jump, new Object[]{});
                break;
            default:
                System.out.println("None of the options was selected, make sure to write them correctly\n");
                movementMenu();
                break;
        }
    }


    @Override
    public void update(Events event, Object[] data) {

    }
}
