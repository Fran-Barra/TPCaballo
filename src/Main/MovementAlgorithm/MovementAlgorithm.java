package MovementAlgorithm;

import Observer.Observer;
import Observer.BackendObserver;
import States.Events;
import Tools.CoordinatesTransformer;

import java.util.ArrayList;
import Tools.Stack;

public class MovementAlgorithm implements Observer {
    ArrayList<ArrayList<byte[]>> finalPaths = new ArrayList<>();
    Stack<byte[]>[] stacks;

    public static void  backTracking(byte[] HorsePosition, byte n, byte[] previousPos, Stack<byte[]>[] stacks, ArrayList<ArrayList<byte[]>> paths){
        if (n == 0){
            ArrayList<byte[]> path = new ArrayList<>();
            for (int i = stacks.length - 1; i >= 0; i--)
                path.add(stacks[i].peek());
            paths.add(path);
            stacks[stacks.length - 1].pop();
        }
        else {
            byte minX = (byte) (HorsePosition[0] - 2);
            byte maxX = (byte) (HorsePosition[0] + 2);
            byte minY = (byte) (HorsePosition[1] - 2);
            byte maxY = (byte) (HorsePosition[1] + 2);
            Stack<byte[]> validPositions = new Stack<>();
            for (int i = 0; i <= 4; i++){
                if ((i+1) % 2 == 0){
                    validPositions.push(new byte[]{minX, (byte)(minY + i)});
                    validPositions.push(new byte[]{(byte) (minX + i), minY});
                    validPositions.push(new byte[]{maxX, (byte) (maxY - i)});
                    validPositions.push(new byte[]{(byte) (maxX - i), maxY});
                }
            }
            Stack<byte[]> auxValidPositions = new Stack<>();
            while (!validPositions.isEmpty()) {
                if (isValid(validPositions.peek()[0], validPositions.peek()[1], previousPos)){
                    auxValidPositions.push(validPositions.peek());
                }
                validPositions.pop();
            }
            validPositions = auxValidPositions;
            for (int i = 0; i < stacks.length; i++) {
                if (stacks[i] == null || stacks[i].isEmpty()) {
                    stacks[i] = validPositions;
                    break;
                }
            }

            while (!auxValidPositions.isEmpty()){
                backTracking(auxValidPositions.peek(), (byte) (n-1), HorsePosition, stacks, paths);
            }
            if (!stacks[0].isEmpty())
                stacks[stacks.length - 1 - n].pop();
        }

    }

    private static  boolean isValid(byte x, byte y, byte[] previousPos){
        if (x <= 7 && y <= 7 && x >= 0 && y >= 0) {
            return !(x == previousPos[0] && y == previousPos[1]);
        }
        return false;
    }

    @Override
    public void update(Events event, Object[] data) {
        if (Events.InitialConditions == event){
            String posS = (String) data[0];
            byte n = (byte) data[1];
            byte[] posB = CoordinatesTransformer.transformFromChessToNumbers(posS);
            stacks = new Stack[n];
            backTracking(posB, n, posB, stacks, finalPaths);
        }
        else if (Events.Jump == event){
            BackendObserver.notify(Events.Jump, new Object[] {finalPaths.get((int) data[0])});
        }
        else if (Events.ShowResults == event){
            BackendObserver.notify(Events.ShowResults, new Object[] {finalPaths});
        }
        else if (Events.ShowPiles == event){
            BackendObserver.notify(Events.ShowPiles, new Object[] {stacks});
        }
    }
}
