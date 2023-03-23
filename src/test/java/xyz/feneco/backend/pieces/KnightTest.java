package xyz.feneco.backend.pieces;

import org.junit.jupiter.api.Test;
import xyz.feneco.backend.Board;
import xyz.feneco.backend.Piece;
import xyz.feneco.backend.Position;
import xyz.feneco.backend.Team;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {

    @Test
    void isMoveValid() {
        {
            Board board = new Board();
            Piece wKnight1 = new Knight(Team.White, new Position(4, 4), board);
            board.addPiece(wKnight1);

            assertTrue(wKnight1.canMove(new Position(5, 2)));
            assertTrue(wKnight1.canMove(new Position(6, 3)));
            assertTrue(wKnight1.canMove(new Position(6, 5)));
            assertTrue(wKnight1.canMove(new Position(5, 6)));
            assertTrue(wKnight1.canMove(new Position(3, 6)));
            assertTrue(wKnight1.canMove(new Position(2, 5)));
            assertTrue(wKnight1.canMove(new Position(2, 3)));
            assertTrue(wKnight1.canMove(new Position(3, 2)));
            assertFalse(wKnight1.canMove(new Position(4, 6)));

        }
        {
            Board board = new Board();
            Piece wKnight1 = new Knight(Team.White, new Position(4, 4), board);
            Piece wPawn1 = new Pawn(Team.White, new Position(3, 3), board);
            Piece bPawn1 = new Pawn(Team.Black, new Position(3, 2), board);
            board.addPiece(wKnight1);
            board.addPiece(wPawn1);
            board.addPiece(bPawn1);

            assertTrue(wKnight1.canMove(bPawn1.getPosition()));
            assertFalse(wKnight1.canMove(wPawn1.getPosition()));

        }
    }
}