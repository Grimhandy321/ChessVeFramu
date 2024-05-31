package com.example.Game;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Side;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Engine {
    int searchDepth = 3;
    public int EvaluatePosition(Board board) {
        int eval = 0;
        eval += board.getPieceLocation(Piece.BLACK_ROOK).size() * 500;
        eval += board.getPieceLocation(Piece.BLACK_BISHOP).size() * 330;
        eval += board.getPieceLocation(Piece.BLACK_PAWN).size() * 100;
        eval += board.getPieceLocation(Piece.BLACK_QUEEN).size() * 900;
        eval += board.getPieceLocation(Piece.BLACK_KING).size() * 2000;
        eval += board.getPieceLocation(Piece.BLACK_KNIGHT).size() * 320;
        eval += board.getPieceLocation(Piece.WHITE_ROOK).size() * 500;
        eval += board.getPieceLocation(Piece.WHITE_BISHOP).size() * 330;
        eval += board.getPieceLocation(Piece.WHITE_PAWN).size() * 100;
        eval += board.getPieceLocation(Piece.WHITE_QUEEN).size() * 900;
        eval += board.getPieceLocation(Piece.WHITE_KING).size() * 2000;
        eval += board.getPieceLocation(Piece.WHITE_KNIGHT).size() * 320;
        return eval;
    }

    public Move Think(Board board) {
        List<Move> allMoves = board.legalMoves();
        for (Move move : allMoves) {
            if (MoveIsCheckmate(board, move)) {
                return move;
            }
        }

        allMoves = allMoves.stream().sorted(Comparator.comparingInt(move -> 8 - getValueOfAtSquare(board, move.getTo()))).toList();

        int bestEval = board.getSideToMove() == Side.WHITE ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        ArrayList<Move> bestMoves = new ArrayList<Move>();

        for (Move allMove : allMoves) {
            int eval = 0;
            Move current = allMove;
            board.doMove(current);
            eval += AlphaBeta(board, this.searchDepth, Integer.MIN_VALUE, Integer.MAX_VALUE, board.getSideToMove() == Side.WHITE);
            board.undoMove();
            if (bestMoves.isEmpty()) {
                bestMoves.add(current);
                bestEval = eval;
            } else if ((board.getSideToMove() == Side.WHITE && eval > bestEval) || (!(board.getSideToMove() == Side.WHITE) && eval < bestEval)) {
                bestMoves.clear();
                bestMoves.add(current);
                bestEval = eval;
            } else if (eval == bestEval) {
                bestMoves.add(current);
            }
        }
        Random random = new Random();
        int randomIndex = random.nextInt(bestMoves.size());
        Move randomBestMove = bestMoves.get(randomIndex);
        return randomBestMove;
    }

    public int AlphaBeta(Board node, int depth, int alpha, int beta, boolean maximizingPlayer) {
        List<Move> childNodes = node.legalMoves();
        childNodes = childNodes.stream().sorted(Comparator.comparingInt(move -> 8 - getValueOfAtSquare(node, move.getTo()))).toList();
        if (depth == 0 || childNodes.isEmpty()) {
            return EvaluatePosition(node);
        }
        if (maximizingPlayer) {
            int value = Integer.MIN_VALUE;
            for (Move child : childNodes) {
                node.doMove(child);
                value = Math.max(value, AlphaBeta(node, depth - 1, alpha, beta, false));
                node.undoMove();
                alpha = Math.max(alpha, value);
                if (beta <= alpha) {
                    break;
                }
            }
            return value;
        } else {
            int value = Integer.MAX_VALUE;
            for (Move child : childNodes) {
                node.doMove(child);
                value = Math.min(value, AlphaBeta(node, depth - 1, alpha, beta, true));
                node.undoMove();
                beta = Math.min(beta, value);
                if (beta <= alpha) {
                    break;
                }
            }
            return value;
        }
    }

    public int getValueOfAtSquare(Board board, Square square) {
        Piece piece = board.getPiece(square);
        int i = 0;
        switch (piece.getPieceType()){
            case KNIGHT -> i = 2;
            case PAWN -> i =1;
            case ROOK -> i=5;
            case BISHOP -> i=2;
            case QUEEN -> i=7;
            case KING -> i=8;
            case null -> i=0;
            case NONE -> {
                i=0;
            }
        }
        return i;
    }

    private boolean MoveIsCheckmate(Board board, Move move) {
        board.doMove(move);
        boolean isMate = board.isMated();
        board.undoMove();
        return isMate;
    }
}
