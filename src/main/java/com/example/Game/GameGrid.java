package com.example.Game;

import com.example.Ui.Component;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GameGrid {
    public static char[] board = new char[64];
    public void PositionFromFen (String fen) {
        String[] sections = fen.split(" ");
        int file = 0;
        int rank = 7;
        for (char symbol : sections[0].toCharArray()) {
            if (symbol == '/') {
                file = 0;
                rank--;
            } else {
                if (Character.isDigit(symbol)) {
                    file += (int) Character.getNumericValue(symbol);
                } else {
                    board[rank * 8 + file] = symbol;
                    file++;
                }
            }
        }
    }
    public  ArrayList<Component> getImageGrid() throws FileNotFoundException {
        FileInputStream inputstream = new FileInputStream("Images\\b_pawn_2x_ns.png");
        ArrayList<Component> components = new ArrayList<>();
        for (int i = 63; i >= 0 ; i--){
            switch (board[i]){
                case 'r' :  inputstream = new FileInputStream("Images\\b_rook_2x_ns.png"); break;
                case 'R' :  inputstream = new FileInputStream("Images\\w_rook_2x_ns.png"); break;
                case 'n' :  inputstream = new FileInputStream("Images\\b_knight_2x_ns.png"); break;
                case 'N' :  inputstream = new FileInputStream("Images\\W_knight_2x_ns.png"); break;
                case 'b' :  inputstream = new FileInputStream("Images\\b_bishop_2x_ns.png"); break;
                case 'B' :  inputstream = new FileInputStream("Images\\w_bishop_2x_ns.png"); break;
                case 'q' :  inputstream = new FileInputStream("Images\\b_queen_2x_ns.png"); break;
                case 'Q' :  inputstream = new FileInputStream("Images\\w_queen_2x_ns.png"); break;
                case 'k' :  inputstream = new FileInputStream("Images\\b_king_2x_ns.png"); break;
                case 'K' :  inputstream = new FileInputStream("Images\\w_king_2x_ns.png"); break;
                case 'p' :  inputstream = new FileInputStream("Images\\b_pawn_2x_ns.png"); break;
                case 'P' :  inputstream = new FileInputStream("Images\\w_pawn_2x_ns.png"); break;
            }
            components.add(new Component( (7 -getFileFromIndex(i))  * 75,(7-getRankFromIndex(i)) * 75,new Image(inputstream)));
        }
        return  components;
    }
    private static int getRankFromIndex(int index){
        return index / 8;
    }
    private static int getFileFromIndex(int index){
        return index % 8;
    }
    public boolean canGoThere(int rank, int file, boolean color){
        if(Character.isLowerCase(board[rank * 8 + file]) && color){
            return true;
        }
        return !Character.isLowerCase(board[rank * 8 + file]) && !color;
    }

    public GameGrid() {
        PositionFromFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    }


}
