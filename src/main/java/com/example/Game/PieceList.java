package com.example.Game;

import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.PieceType;
import com.github.bhlangonijr.chesslib.Side;

import java.util.ArrayList;

public class PieceList {
    private ArrayList<Piece> list = new ArrayList<>();
    private PieceType pieceType;
    private Side side;

    public void Add(Piece piece) {
        list.add(piece);
    }

    public ArrayList<Piece> getList() {
        return list;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Side getSide() {
        return side;
    }

    public boolean isWhite() {
        return side == Side.WHITE;
    }

    public PieceList(PieceType pieceType, Side side) {
        this.pieceType = pieceType;
        this.side = side;
    }

    public PieceList(PieceType pieceType, Side side, Piece piece) {
        this.pieceType = pieceType;
        this.side = side;
        list.add(piece);
    }
}
