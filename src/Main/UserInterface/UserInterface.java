package UserInterface;

import Observer.InterfaceObserver;
import Observer.Observer;
import States.Events;
import Tools.CoordinatesTransformer;
import Tools.Stack;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UserInterface implements Observer {
    private static int jump = 0;

    private static void askInitialPos(){
        Scanner inputScaner = new Scanner(System.in);
        System.out.println("Log the initial position of the horse (A1):");
        String pos = inputScaner.nextLine();
        if (pos.length() != 2){
            System.out.println("pos takes one character from A to H and a number from 1 to 8");
            askInitialPos();
        }
        System.out.println("How many jumps?");
        byte n = inputScaner.nextByte();
        InterfaceObserver.notify(Events.InitialConditions, new Object[] {pos, n});
        movementMenu();
    }

    private static void movementMenu(){
        Scanner inputScaner = new Scanner(System.in);
        boolean running = true;
        while (running){
            System.out.println("Chose one of the followings: jump, show stack (shs), exit, results:");
            String option = inputScaner.nextLine();
            switch (option) {
                case "exit" -> running = false;
                case "shs" -> InterfaceObserver.notify(Events.ShowPiles, new Object[]{});
                case "jump" -> {
                    InterfaceObserver.notify(Events.Jump, new Object[]{jump});
                    jump++;
                }
                case "results" -> InterfaceObserver.notify(Events.ShowResults, new Object[]{});
                default -> System.out.println("None of the options was selected, make sure to write them correctly");
            }
        }
    }



    private void showStack(Stack<byte[]>[] arrayStacks){
        /*get a copy of the arrayStack and print it*/
        Stack<byte[]>[] aux = new Stack[arrayStacks.length];
        int i = 0;
        while (i<arrayStacks.length){
            aux[i] = new Stack<>();
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
            while (j< arrayStacks.length && !arrayStacks[j].isEmpty()){
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

    private void showResults(ArrayList<ArrayList<String>> results){
        /*get a list of strings of the results and prints it*/
        for (ArrayList<String> path: results){
            String[] printeablePath = new String[path.size()];
            int i = 0;
            while (i<path.size()){
                printeablePath[i] = path.get(i);
                i++;
            }
            System.out.println(Arrays.toString(printeablePath));
        }

    }


    @Override
    public void update(Events event, Object[] data) {
        if (event == Events.Start){
            askInitialPos();
        }
        else if (event == Events.Jump){
            ArrayList<byte[]> path = (ArrayList<byte[]>) data[0];
            String[] redablePath = new String[path.size()];
            int i = 0;
            while (i<path.size()){
                redablePath[i] = CoordinatesTransformer.transformFromNumbersToChess(path.get(i)[0], path.get(i)[1]);
                i++;
            }
            System.out.println(Arrays.toString(redablePath));
        }
        else if (event == Events.ShowResults){
            ArrayList<ArrayList<byte[]>> allResults = (ArrayList<ArrayList<byte[]>>) data[0];
            ArrayList<ArrayList<String>> readableResults = new ArrayList<>();
            for (int i = 0; i < allResults.size(); i ++){
                ArrayList<String> onePath = new ArrayList<>();
                for (int j = 0; j < allResults.get(i).size(); j ++){
                    onePath.add(CoordinatesTransformer.transformFromNumbersToChess(allResults.get(i).get(j)[0], allResults.get(i).get(j)[1]));
                }
                readableResults.add(onePath);
            }
            showResults(readableResults);
        }
    }
}
