package Tools;

import java.util.HashMap;

public class CoordinatesTransformer {
    public static byte[] transformFromChessToNumbers(String pos){
        HashMap<Character, Byte> charToByte = new HashMap<>();
        charToByte.put('A', (byte)0);
        charToByte.put('B', (byte)1);
        charToByte.put('C', (byte)2);
        charToByte.put('D', (byte)3);
        charToByte.put('E', (byte)4);
        charToByte.put('F', (byte)5);
        charToByte.put('G', (byte)6);
        charToByte.put('H', (byte)7);

        return new byte[]{charToByte.get(pos.charAt(0)), (byte)Character.getNumericValue(pos.charAt(1))};
    }

    public static String transformFromNumbersToChess(byte x, byte y){
        String[] byteToChar = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};
        return byteToChar[x] +String.valueOf(y);
    }
}
