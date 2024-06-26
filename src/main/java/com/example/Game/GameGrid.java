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
    Engine engine = new Engine();

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
        Engine engine = new Engine();
        return engine.Think(board);
    }

    public Board getBoard() {
        return board;
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

}
