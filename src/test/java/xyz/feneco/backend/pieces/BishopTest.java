package xyz.feneco.backend.pieces;

import org.junit.jupiter.api.Test;
import xyz.feneco.backend.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    @Test
    void moveMask() {
        Piece wBishop1 = new Bishop(Team.White, new Position(4, 4), true);
        Piece wBishop2 = new Bishop(Team.White, new Position(6, 2), true);

        Piece bBishop1 = new Bishop(Team.Black, new Position(3, 3), true);
        Piece bBishop2 = new Bishop(Team.Black, new Position(2, 6), true);
        Piece bBishop3 = new Bishop(Team.Black, new Position(4, 6), true);
        Piece bBishop4 = new Bishop(Team.Black, new Position(7, 7), true);
        Piece bBishop5 = new Bishop(Team.Black, new Position(0, 0), true);

        Board b = BoardFactory.getEmptyBoard();
        b.addPiece(wBishop1);
        b.addPiece(wBishop2);
        b.addPiece(bBishop1);
        b.addPiece(bBishop2);
        b.addPiece(bBishop3);
        b.addPiece(bBishop4);
        b.addPiece(bBishop5);

        List<Position> validPositions = wBishop1.getValidPositions(wBishop1.getPosition(), b);
        assertFalse(validPositions.contains(wBishop1.getPosition()));
        assertFalse(validPositions.contains(wBishop2.getPosition()));

        assertTrue(validPositions.contains(bBishop1.getPosition()));
        assertTrue(validPositions.contains(bBishop2.getPosition()));
        assertFalse(validPositions.contains(bBishop3.getPosition()));
        assertTrue(validPositions.contains(bBishop4.getPosition()));

        assertFalse(validPositions.contains(new Position(2, 2)));
        assertFalse(validPositions.contains(new Position(6, 1)));
        assertFalse(validPositions.contains(new Position(4, 5)));
    }
}