package com.example.Game;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.move.Move;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Timer;

public class Engine {
    Move bestRootMove;
    Board board;
    Timer timer;
    int searchDepth = 0;
    CountdownTimer countdownTimer;
    public int getPieceValue(Piece piece) {
        switch (piece.getPieceType()) {
            case KING -> {
                return 0;
            }
            case ROOK -> {
                return 5;
            }
            case BISHOP, KNIGHT -> {
                return 3;
            }
            case QUEEN -> {
                return 9;
            }
            case PAWN -> {
                return 1;
            }
        }
        return 0;
    }
    public Move Think(Board board, Timer timer) {
        this.board = board;
        this.timer = timer;
        countdownTimer = new CountdownTimer(1000);
        int searchDepth = 0;

        try {
            for (;;)
                Search(++searchDepth, -30000, 30000, 0);
        } catch (Exception e) {
            return bestRootMove;
        }

    }
    double Search(int depth, double alpha, double beta, double currentEval) throws Exception {
        if (depth <= 0 && alpha < currentEval)
            alpha = currentEval;

      List<Move> moves = board.legalMoves().stream().sorted((m1, m2) -> {
          int cmp = Boolean.compare(m2 == bestRootMove, m1 == bestRootMove);
          if (cmp != 0) return cmp;
          cmp = Integer.compare(getPieceValue(getMoveCapture(m2)), getPieceValue(getMoveCapture(m1)));
          if (cmp != 0) return cmp;
          return Integer.compare(getPieceValue(m2.getPromotion()) - getPieceValue(getMovePiece(m2)), getPieceValue(m1.getPromotion())  - getPieceValue(getMovePiece(m1)));
      }).toList();
        for (Move move : moves )
        {
            if (alpha >= beta)
                break;
            board.doMove(move);
            double score =
                    board.isDraw() ? 0 :
                            -Search(depth - 1, -beta, -alpha,
                                    Math.log(board.legalMoves().size())
                                            - currentEval
                            );
            if (score > alpha) {
                alpha = score;
                if (depth == searchDepth)
                    bestRootMove = move;
            }
            if(countdownTimer.isDone()){
                throw new Exception();
            }
            board.undoMove();
        }

        return alpha;
    }
    private Piece getMoveCapture(Move move){
        return board.getPiece(move.getTo());
    }
    private Piece getMovePiece(Move move){
        return board.getPiece(move.getFrom());
    }
}
