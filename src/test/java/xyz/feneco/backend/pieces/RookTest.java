package xyz.feneco.backend.pieces;

import org.junit.jupiter.api.Test;
import xyz.feneco.backend.*;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {

    @Test
    void isMoveValid() {
        {
            Board board = new Board();
            Piece bRook1 = new Rook(Team.Black, new Position(4, 4), board);
            Piece wPawn1 = new Pawn(Team.White, new Position(7, 4), board);
            Piece wPawn2 = new Pawn(Team.White, new Position(4, 2), board);
            Piece wPawn3 = new Pawn(Team.White, new Position(4, 1), board);
            Piece wPawn4 = new Pawn(Team.White, new Position(2, 3), board);
            Piece bPawn1 = new Pawn(Team.Black, new Position(2, 4), board);
            board.addPiece(bRook1);
            board.addPiece(wPawn1);
            board.addPiece(wPawn2);
            board.addPiece(wPawn3);
            board.addPiece(wPawn4);
            board.addPiece(bPawn1);

            assertTrue(bRook1.canMove(new Position(4,6)));
            assertTrue(bRook1.canMove(new Position(6,4)));
            assertFalse(bRook1.canMove(new Position(5,3)));
            assertTrue(bRook1.canMove(new Position(7,4)));
            assertFalse(bRook1.canMove(new Position(4,1)));
            assertFalse(bRook1.canMove(new Position(2,4)));
            assertFalse(bRook1.canMove(new Position(2,3)));
        }
    }
}