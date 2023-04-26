package xyz.feneco.backend.pieces;

import org.junit.jupiter.api.Test;
import xyz.feneco.backend.*;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    @Test
    void canMove() {
        {
            Board b = new EmptyBoardFactory().getBoard();
            Piece wPawn1 = new Pawn(Team.White, new Position(1, 1), true);
            b.addPiece(wPawn1);

            assertTrue(wPawn1.canMove(new Position(1, 3), b));
            assertTrue(wPawn1.canMove(new Position(1, 2), b));
            assertFalse(wPawn1.canMove(new Position(2, 2), b));
            assertFalse(wPawn1.canMove(new Position(0, 2), b));

            Piece wPawn2 = new Pawn(Team.White, new Position(0, 2), false);
            b.addPiece(wPawn2);
            Piece bPawn1 = new Pawn(Team.Black, new Position(2,2), false);
            b.addPiece(bPawn1);
            assertTrue(wPawn1.canMove(new Position(2, 2), b));
            assertFalse(wPawn1.canMove(new Position(0, 2), b));
        }
        { // EnPassant test
            Board b = new EmptyBoardFactory().getBoard();
            Piece wPawn1 = new Pawn(Team.White, new Position(1, 4), false);
            b.addPiece(wPawn1);
            Piece bPawn1 = new Pawn(Team.Black, new Position(2, 6), true);
            b.addPiece(bPawn1);

            assertFalse(wPawn1.canMove(new Position(1, 6), b));
            assertFalse(wPawn1.canMove(new Position(2, 5), b));
            assertTrue(wPawn1.canMove(new Position(1, 5), b));
            bPawn1.move(new Position(2, 4));
            assertTrue(wPawn1.canMove(new Position(2, 5), b));

            Piece wPawn2 = new Pawn(Team.White, new Position(3, 4), false);
            b.addPiece(wPawn2);
            Piece bPawn2 = new Pawn(Team.Black, new Position(4, 4), true);
            b.addPiece(bPawn2);
            assertFalse(wPawn1.canMove(new Position(4, 5), b));
        }
    }

    @Test
    void move() {
        Pawn wPawn1 = new Pawn(Team.White, new Position(1, 1), true);

        assertFalse(wPawn1.getEnPassant());
        wPawn1.move(new Position(1, 3));
        assertTrue(wPawn1.getEnPassant());
        wPawn1.move(new Position(1, 4));
        assertFalse(wPawn1.getEnPassant());

        Pawn wPawn2 = new Pawn(Team.White, new Position(1, 1), true);
        wPawn1.move(new Position(1, 1));
        assertFalse(wPawn1.getEnPassant());
        wPawn1.move(new Position(1, 2));
        assertFalse(wPawn1.getEnPassant());
    }
}