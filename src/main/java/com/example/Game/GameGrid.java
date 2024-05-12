package com.example.Game;

import com.example.Ui.Component;
import com.github.bhlangonijr.chesslib.File;
import com.github.bhlangonijr.chesslib.Rank;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.github.bhlangonijr.chesslib.Board;

public class GameGrid {
    Board board = new Board();
    String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 1 2";
    public GameGrid() {
        board.loadFromFen(fen);
    }
    public ArrayList<Move> movesForPiece(Square fromSquare){
        ArrayList<Move> ret = new ArrayList<>();
        for(Move m :board.legalMoves()){
            if(m.getFrom() == fromSquare){
                ret.add(m);
            }
        }
        return ret;
    }
    private Move getBestMove(){
        return board.legalMoves().get(0);
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
        String[] lines = board.toString().replace("\"",""). split("\n");
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
    public Square getFromCords(int x,int y){
        Rank rank = Rank.values()[7 - y];
        File file = File.values()[ x];
        Square square =  Square.encode(rank,file);
        return square;
    }
}
