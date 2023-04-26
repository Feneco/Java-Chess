package xyz.feneco.backend.pieces;

import org.junit.jupiter.api.Test;
import xyz.feneco.backend.*;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {

    @Test
    void canMove() {
        Piece wKnight1 = new Knight(Team.White, new Position(4, 4), true);
        Piece wKnight2 = new Knight(Team.White, new Position(5, 6), true);

        Piece bBishop1 = new Bishop(Team.Black, new Position(5, 2), true);
        Piece bBishop2 = new Bishop(Team.Black, new Position(5, 3), true);
        Piece bBishop3 = new Bishop(Team.Black, new Position(6, 3), true);

        Board board = new EmptyBoardFactory().getBoard();
        board.addPiece(wKnight1);
        board.addPiece(wKnight2);
        board.addPiece(bBishop1);
        board.addPiece(bBishop2);
        board.addPiece(bBishop3);

        assertTrue(wKnight1.canMove(new Position(3, 6), board));
        assertTrue(wKnight1.canMove(new Position(2, 3), board));
        assertTrue(wKnight1.canMove(new Position(3, 2), board));
        assertFalse(wKnight1.canMove(new Position(4, 2), board));
        assertFalse(wKnight1.canMove(wKnight2.getPosition(), board));
        assertTrue(wKnight1.canMove(bBishop1.getPosition(), board));
        assertFalse(wKnight1.canMove(bBishop2.getPosition(), board));
        assertTrue(wKnight1.canMove(bBishop3.getPosition(), board));
    }
}