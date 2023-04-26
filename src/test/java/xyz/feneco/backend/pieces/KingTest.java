package xyz.feneco.backend.pieces;

import org.junit.jupiter.api.Test;
import xyz.feneco.backend.*;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    @Test
    void canMove() {
        {
            Piece wKing1 = new King(Team.White, new Position(4, 0), true);
            Piece wRook1 = new Rook(Team.White, new Position(7, 0), true);
            Piece wRook2 = new Rook(Team.White, new Position(0, 0), true);

            Piece bQueen1 = new Queen(Team.Black, new Position(3, 1), true);

            Board b = new EmptyBoardFactory().getBoard();
            b.addPiece(wKing1);
            b.addPiece(wRook1);
            b.addPiece(wRook2);
            b.addPiece(bQueen1);

            assertTrue(wKing1.canMove(new Position(4, 1), b));
            assertTrue(wKing1.canMove(new Position(6, 0), b));
            assertTrue(wKing1.canMove(new Position(1, 0), b));
            assertTrue(wKing1.canMove(bQueen1.getPosition(), b));

            assertFalse(wKing1.canMove(new Position(6, 1), b));
        }
        {
            Piece wKing1 = new King(Team.White, new Position(4, 0), true);
            Piece wRook1 = new Rook(Team.White, new Position(7, 0), false);
            Piece wRook2 = new Rook(Team.White, new Position(0, 0), false);

            Piece bQueen1 = new Queen(Team.Black, new Position(3, 1), true);

            Board b = new EmptyBoardFactory().getBoard();
            b.addPiece(wKing1);
            b.addPiece(wRook1);
            b.addPiece(wRook2);
            b.addPiece(bQueen1);

            assertTrue(wKing1.canMove(new Position(4, 1), b));
            assertFalse(wKing1.canMove(new Position(6, 0), b));
            assertFalse(wKing1.canMove(new Position(1, 0), b));
            assertTrue(wKing1.canMove(bQueen1.getPosition(), b));

            assertFalse(wKing1.canMove(new Position(6, 1), b));
        }
    }
}