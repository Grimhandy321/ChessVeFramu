package com.example.Game;

public class GameGrid {
    public char[] board = new char[64];
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

    public GameGrid() {
        PositionFromFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    }


}
