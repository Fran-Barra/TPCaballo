package UserInterface;

import Observer.InterfaceObserver;
import Observer.Observer;
import States.Events;
import Tools.CoordinatesTransformer;
import Tools.Stack;


import java.util.Arrays;
import java.util.Scanner;

public class UserInterface implements Observer {

    public static void askInitialPos(){
        Scanner inputScaner = new Scanner(System.in);
        System.out.println("Log the initial position of the horse (A1):");
        String pos = inputScaner.nextLine();
        if (pos.length() != 2){
            System.out.println("pos takes one character from A to H and a number from 1 to 8");
            askInitialPos();
        }
        InterfaceObserver.notify(Events.InitialPos, new Object[] {pos});
        movementMenu();
    }

    private static void movementMenu(){
        Scanner inputScaner = new Scanner(System.in);
        boolean running = true;
        while (running){
            System.out.println("Chose one of the followings: jump, show stack (shs), exit, results:");
            String option = inputScaner.nextLine();
            switch (option) {
                case "exit":
                    running = false;
                case "shs":
                    InterfaceObserver.notify(Events.ShowPiles, new Object[]{});
                    break;
                case "jump":
                    InterfaceObserver.notify(Events.Jump, new Object[]{});
                    break;
                case "results":
                    InterfaceObserver.notify(Events.ShowResults, new Object[]{});
                default:
                    System.out.println("None of the options was selected, make sure to write them correctly");
                    break;
            }
        }
    }

    private void showStack(Stack<byte[]>[] arrayStacks){
        /*get a copy of the arrayStack and print it*/
        Stack<byte[]>[] aux = new Stack[arrayStacks.length];
        int i = 0;
        while (i<arrayStacks.length){
            aux[i] = new Stack<byte[]>();
            while (!arrayStacks[i].isEmpty()){
                aux[i].push(arrayStacks[i].peek());
                arrayStacks[i].pop();
            }
            i++;
        }
        String[][] toPrint = new String[6][arrayStacks.length];
        i = 0;
        int j;
        while (i<6){
            //i is asociated with the number of opcion a jump have(6 per pos)
            j = 0;
            while (j< arrayStacks.length){
                //j is associated for de number of jump
                byte[] pos = arrayStacks[j].peek();
                toPrint[i][j] = CoordinatesTransformer.transformFromNumbersToChess(pos[0], pos[1]);
                arrayStacks[j].pop();
                j++;
            }
            i++;
        }

        for (String[] line: toPrint){
            System.out.println(Arrays.toString(line));
        }
    }

    private void showResults(String[][] results){
        /*get a list of strings of the results and prints it*/
        for(String[] option: results){
            System.out.println(Arrays.toString(option));
        }

    }


    @Override
    public void update(Events event, Object[] data) {

    }
}
