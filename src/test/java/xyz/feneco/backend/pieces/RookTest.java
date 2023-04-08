package xyz.feneco.backend.pieces;

import org.junit.jupiter.api.Test;
import xyz.feneco.backend.*;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {

    @Test
    void canMove() {
        Piece wRook1 = new Rook(Team.White, new Position(4, 4), true);
        Piece wRook2 = new Rook(Team.White, new Position(4, 1), true);

        Piece bRook1 = new Rook(Team.Black, new Position(0, 4), true);
        Piece bRook2 = new Rook(Team.Black, new Position(6, 4), true);
        Piece bRook3 = new Rook(Team.Black, new Position(4, 7), true);
        Piece bRook4 = new Rook(Team.Black, new Position(5, 7), true);

        Board board = BoardFactory.getEmptyBoard();
        board.addPiece(wRook1);
        board.addPiece(wRook2);
        board.addPiece(bRook1);
        board.addPiece(bRook2);
        board.addPiece(bRook3);
        board.addPiece(bRook4);

        assertTrue(wRook1.canMove(bRook1.getPosition(), board));
        assertTrue(wRook1.canMove(bRook2.getPosition(), board));
        assertTrue(wRook1.canMove(bRook3.getPosition(), board));
        assertTrue(wRook1.canMove(new Position(3, 4), board));
        assertTrue(wRook1.canMove(new Position(1, 4), board));
        assertTrue(wRook1.canMove(new Position(5, 4), board));
        assertTrue(wRook1.canMove(new Position(4, 6), board));

        assertFalse(wRook1.canMove(wRook2.getPosition(), board));
        assertFalse(wRook1.canMove(bRook4.getPosition(), board));
        assertFalse(wRook1.canMove(new Position(4, 0), board));
        assertFalse(wRook1.canMove(new Position(7, 4), board));
        assertFalse(wRook1.canMove(new Position(0, 0), board));
    }
}