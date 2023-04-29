package xyz.feneco.backend;

import xyz.feneco.backend.pieces.*;

import java.util.ArrayList;
import java.util.List;

public class StandardBoardFactory implements BoardFactoryInterface {
    @Override
    public Board getBoard() {
        List<Piece> boardPieces = new ArrayList<>();
        {
            Team[] sides = {Team.White, Team.Black};
            for (Team team : sides) {
                int pawnY = team == Team.White ? 1 : 6;
                for (int x = 0; x < 8; x++) {
                    boardPieces.add(new Pawn(team, new Position(x, pawnY), true));
                }
                int row   = team == Team.White ? 0 : 7;
                boardPieces.add(new Rook(team, new Position(0, row), true));
                boardPieces.add(new Rook  (team, new Position(7, row), true));
                boardPieces.add(new Knight(team, new Position(1, row), true));
                boardPieces.add(new Knight(team, new Position(6, row), true));
                boardPieces.add(new Bishop(team, new Position(2, row), true));
                boardPieces.add(new Bishop(team, new Position(5, row), true));
                boardPieces.add(new Queen(team, new Position(3, row), true));
                boardPieces.add(new King  (team, new Position(4, row), true));
            }
        }
        return new Board(boardPieces, new ArrayList<>());
    }
}
