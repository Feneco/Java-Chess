package xyz.feneco.backend;

public class BoardFactory {
    public static Board getStandardBoard() {
        return null;
    }

    public static Board getEmptyBoard() {
        return new Board(null, null);
    }
}
