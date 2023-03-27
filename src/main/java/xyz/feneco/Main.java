package xyz.feneco;

import xyz.feneco.frontend.ChessGame;

public class Main {
    public static void main(String[] args) {
        System.out.println("New game!");
        ChessGame ch = new ChessGame();
        ch.init();
    }
}