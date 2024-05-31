package com.example.Game;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Square;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {

    @Test
    void evaluatePosition() {
        Engine engine = new Engine();
        int i = engine.EvaluatePosition(new Board());
        assertEquals(12000,i);
    }

    @Test
    void alphaBeta() {
        Engine engine = new Engine();
        int i = engine.AlphaBeta(new Board(),2,1000,-1000,false);
        assertEquals(12000,i);
    }

    @Test
    void getValueOfAtSquare() {
        Engine engine = new Engine();
        assertEquals(1,engine.getValueOfAtSquare(new Board(), Square.E2));
    }
}