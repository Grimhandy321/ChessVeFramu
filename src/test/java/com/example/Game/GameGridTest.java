package com.example.Game;

import com.github.bhlangonijr.chesslib.Square;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameGridTest {

    @Test
    void getGrid() {
        GameGrid gameGrid = new GameGrid();
        assertEquals(Arrays.deepToString(gameGrid.getGrid()),"[[r, n, b, q, k, b, n, r], [p, p, p, p, p, p, p, p], [., ., ., ., ., ., ., .], [., ., ., ., ., ., ., .], [., ., ., ., ., ., ., .], [., ., ., ., ., ., ., .], [P, P, P, P, P, P, P, P], [R, N, B, Q, K, B, N, R]]");
    }

    @Test
    void getFromCords() {
        GameGrid gameGrid = new GameGrid();
        Square square = gameGrid.getFromCords(5,5);
        assertEquals(square,Square.F3);
    }
}