package xyz.feneco.frontend;

import xyz.feneco.backend.*;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;
import java.util.regex.*;

public class PlayerQuery {

    private static List<Character> convertStringToCharList(String str) {
        List<Character> chars = new ArrayList<>();
        for (char ch : str.toCharArray()) {
            chars.add(ch);
        }
        return chars;
    }

    static public PlayerQueryAnswer query() {
        System.out.print("Make your move: ");
        Scanner input = new Scanner(System.in);
        Pattern move = Pattern.compile("^([A-H][1-8]){2}");
        Pattern resign = Pattern.compile("^(resign)");
        String textInput = input.nextLine();
        Matcher m = move.matcher(textInput);
        Matcher r = resign.matcher(textInput);
        if (m.find()) {
            List<Character> parts = convertStringToCharList(textInput);
            int x1 = (int) parts.get(0) - (int) 'A';
            int y1 = (int) parts.get(1) - (int) '0' - 1;
            int x2 = (int) parts.get(2) - (int) 'A';
            int y2 = (int) parts.get(3) - (int) '0' - 1;
            Position from = new Position(x1, y1);
            Position to = new Position(x2, y2);
            return new PlayerQueryAnswer(0, from, to);
        } else if (r.find()) {
            return new PlayerQueryAnswer(2, null, null  );
        }
        return new PlayerQueryAnswer(1, null, null);
    }

    static public int QueryPromotion() {
        Scanner input = new Scanner(System.in);
        System.out.print("Select piece to promote pawn: ");
        String textInput = input.nextLine();

        Pattern Bishop = Pattern.compile("^B");
        Pattern Knight = Pattern.compile("^N");
        Pattern Rook = Pattern.compile("^R");
        Pattern Queen = Pattern.compile("^Q");
        Matcher m;
        m = Queen.matcher(textInput);
        if (m.find()){
            return 0;
        }
        m = Bishop.matcher(textInput);
        if (m.find()){
            return 1;
        }
        m = Knight.matcher(textInput);
        if (m.find()){
            return 2;
        }
        m = Rook.matcher(textInput);
        if (m.find()){
            return 3;
        }
        else {
            return -1;
        }
    }
}
