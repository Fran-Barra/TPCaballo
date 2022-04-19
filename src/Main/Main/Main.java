package Main;

import Tools.Stack;
import UserInterface.UserInterface;

public class Main {
    public static void main(String[] args) {
        Stack<byte[]>[] aux = new Stack[5];
        Stack<byte[]> stuck1 = new Stack<byte[]>();
        aux[0] = stuck1;
        aux[0].push(new byte[] {1,2});
    }
}
