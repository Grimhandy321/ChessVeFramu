package com.example.Game;

import com.example.Ui.Component;
import com.github.bhlangonijr.chesslib.*;
import com.github.bhlangonijr.chesslib.move.Move;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;


public class GameGrid {
    Board board = new Board();
    String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 1 2";
    public GameGrid() {
        board.loadFromFen(fen);
    }

    public ArrayList<Move> movesForPiece(Square fromSquare) {
        ArrayList<Move> ret = new ArrayList<>();
        for (Move m : board.legalMoves()) {
            if (m.getFrom() == fromSquare) {
                ret.add(m);
            }
        }
        return ret;
    }

    public Move getBestMove() {
        return board.legalMoves().get(0);
    }

    public Board getBoard() {
        return board;
    }

    public Move Think(Board board) {

        Move chosenMove = board.legalMoves().get(0);
        Move newChosenMove = board.legalMoves().getLast();
        int depth = 1;

        while (timer < timer.MillisecondsRemaining / 30) {
            chosenMove = newChosenMove;
            evaluate(newChosenMove, depth++, -1000000000, 1000000000);
        }

        return chosenMove;
    }
    private int evaluate(Move bestMove, int depth, int alpha, int beta)
    {
        if (board.isDraw()) {
            return 0;
        }

        int eval = board.legalMoves().size() - board.getMoveCounter(), iMax = -1000000 * depth;
        if (depth == 0) {
            for (PieceList pieceList : getPieceList()) {
                eval += (int) pieceList.getPieceType().ordinal() * pieceList.getList().size() * (pieceList.getSide() == board.getSideToMove() ? 20 : -20);
            }
            return eval;
        }

        List<Move> sortedMoves = board.legalMoves().stream()
                .sorted((m1, m2) -> Integer.compare(m2.getType().ordinal(), m1.getType().ordinal()))
                .toList();

        for (Move move : sortedMoves) {
            board.doMove(move);
            eval = -evaluate(null, depth - (board.isKingAttacked() ? 0 : 1), -beta, -alpha);
            board.undoMove();

            if (timer.MillisecondsElapsedThisTurn > timer.MillisecondsRemaining / 30) {
                return 0;
            }

            if (eval > iMax) {
                iMax = eval;
                bestMove = move;
            }
            if (eval > alpha) {
                alpha = eval;
            }
            if (alpha >= beta) {
                break;
            }
        }

        return iMax;
    }


    public void setBoard(Board board) {
        this.board = board;
    }

    public String getFen() {
        return fen;
    }

    public void setFen(String fen) {
        this.fen = fen;
    }

    public String[][] getGrid() {
        String[] lines = board.toString().replace("\"", "").split("\n");
        lines[8] = null;
        int rows = 8;
        int cols = 8;
        String[][] array = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            String[] elements = lines[i].split("");
            for (int j = 0; j < cols; j++) {
                array[i][j] = elements[j];
            }
        }
        return array;
    }

    public Square getFromCords(int x, int y) {
        Rank rank = Rank.values()[7 - y];
        File file = File.values()[x];
        Square square = Square.encode(rank, file);
        return square;
    }

    public ArrayList<PieceList> getPieceList() {
        Piece[] pieces = board.boardToArray();
        ArrayList<PieceList> pieceLists = new ArrayList<>();
        for (Piece piece : pieces) {
            boolean added = false;
            for (PieceList pieceList : pieceLists) {
                if (piece.getPieceType() == pieceList.getPieceType()) {
                    pieceList.Add(piece);
                    added = true;
                }
            }
            if (!added) {
                pieceLists.add(new PieceList(piece.getPieceType(), piece.getPieceSide(), piece));
            }

        }
    }
}
