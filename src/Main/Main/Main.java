package Main;

import MovementAlgorithm.MovementAlgorithm;
//import UserInterface.UserInterface;

import java.util.ArrayList;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
//        Stack<byte[]>[] aux = new Stack[5];
//        Stack<byte[]> stuck1 = new Stack<byte[]>();
//        aux[0] = stuck1;
//        aux[0].push(new byte[] {1,2});
//        Stack<byte[]>[] stacks, ArrayList<byte[]> path;
        int n = 2;
        Stack<byte[]>[] stacks = new Stack[n];
        ArrayList<ArrayList<byte[]>> paths = new ArrayList<>();
        MovementAlgorithm.backTracking(new byte[] {2,2}, (byte) n, new byte[] {-1, -1}, stacks, paths);
        String text = "";
        for (int i = 0; i < paths.size(); i ++){
            text += "<<" + (i + 1) + ">>\t(" ;
            for (int j = 0; j < paths.get(i).size(); j ++){
                text += "(" + paths.get(i).get(j)[0] + "," + paths.get(i).get(j)[1] + ")  ";
            }
            text += ")\n";
            System.out.println(text);

        }
    }
}
