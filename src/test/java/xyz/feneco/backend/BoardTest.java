package xyz.feneco.backend;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void initBoard() {
        Board b = new Board();
        b.initBoard();
        assertEquals(b.movePiece(new Position(3, 1), new Position(3, 3)), MovReport.Normal);
        b.changeTeam();
        assertEquals(b.getPlayingTeam(), Team.Black);
        assertEquals(b.movePiece(new Position(2, 0), new Position(5, 3)), MovReport.Invalid);
        assertEquals(b.movePiece(new Position(3, 6), new Position(3, 4)), MovReport.Normal);
    }
}