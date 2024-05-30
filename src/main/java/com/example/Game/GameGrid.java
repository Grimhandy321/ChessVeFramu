package com.example.Game;

import com.github.bhlangonijr.chesslib.*;
import com.github.bhlangonijr.chesslib.move.Move;

import java.util.*;
import java.util.Timer;



public class GameGrid {
    Board board = new Board();
    String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 1 2";
    public GameGrid() {
        board.loadFromFen(fen);
    }
    M_Timer timer;
    Move bestRootMove;

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
       timer = new M_Timer(100000000);
        return Think(board);
    }

    public Board getBoard() {
        return board;
    }

    public Move Think(Board board) {
        return null;
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
                if (piece.getPieceType() == pieceList.getPieceType() && piece.getPieceSide() == pieceList.getSide() ) {
                    pieceList.Add(piece);
                    added = true;
                }
            }
            if (!added && !Objects.equals(piece.value(), "NONE")) {
                pieceLists.add(new PieceList(piece.getPieceType(), piece.getPieceSide(), piece));
            }

        }
        return pieceLists;
    }
}
