package xyz.feneco.backend;

import java.util.ArrayList;

public class BoardFactory {
    public static Board getStandardBoard() {
        return null;
    }

    public static Board getEmptyBoard() {
        return new Board(new ArrayList<>(), new ArrayList<>());
    }
}
