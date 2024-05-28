package com.example.Ui;

import com.example.Game.GameGrid;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private int gridSize = 75;

    @FXML
    private AnchorPane pane;

    private GridHandler gridHandler;
    private GameGrid gameGrid = new GameGrid();
    Square selectedSquare ;
    ArrayList<Move> moves = new ArrayList<>();
    int x;
    int y;
    boolean hasSelectedPiece = false;
    boolean moved = false;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gridHandler = new GridHandler(pane.getPrefWidth(), pane.getPrefHeight(), gridSize, pane);
        gridHandler.updateGrid();
        DrawGame();
        pane.setOnMousePressed(mouseEvent -> {
            double  mouseAnchorX = mouseEvent.getSceneX();
            double mouseAnchorY = mouseEvent.getSceneY();
            x = (int) ((mouseAnchorX/gridSize) % 8);
            y = (int) ((mouseAnchorY/gridSize) % 8);
           if(!hasSelectedPiece){
               selectedSquare = gameGrid.getFromCords(x,y);
               moves =  gameGrid.movesForPiece(selectedSquare);
               System.out.println("Selected square: " +  selectedSquare);
                for (Move m:moves){
                    System.out.println("move: " + m.getTo() );
                }
               hasSelectedPiece = !hasSelectedPiece;
           }else {
               selectedSquare = gameGrid.getFromCords(x,y);
            for(Move m : moves){
                if(m.getTo() == selectedSquare){
                    gameGrid.getBoard().doMove(m);
                    DrawGame();
                    moved = true;
                }
            }
            if(moved) {
                hasSelectedPiece = !hasSelectedPiece;
                gameGrid.getBoard().doMove(gameGrid.getBestMove());
            }
           }
        });
    }
    public  void HandelMove(){

    }
    public void DrawGame()  {
        pane.getChildren().clear();
        gridHandler.updateGrid();
        try {
            FileInputStream inputstream = new FileInputStream("Images\\b_pawn_2x_ns.png");
            for (int x = 0; x <= 7; x++) {
                for (int y = 0; y <= 7; y++) {
                    switch (gameGrid.getGrid()[y][x]) {
                        case "r":
                            inputstream = new FileInputStream("Images\\b_rook_2x_ns.png");
                            break;
                        case "R":
                            inputstream = new FileInputStream("Images\\w_rook_2x_ns.png");
                            break;
                        case "n":
                            inputstream = new FileInputStream("Images\\b_knight_2x_ns.png");
                            break;
                        case "N":
                            inputstream = new FileInputStream("Images\\W_knight_2x_ns.png");
                            break;
                        case "b":
                            inputstream = new FileInputStream("Images\\b_bishop_2x_ns.png");
                            break;
                        case "B":
                            inputstream = new FileInputStream("Images\\w_bishop_2x_ns.png");
                            break;
                        case "q":
                            inputstream = new FileInputStream("Images\\b_queen_2x_ns.png");
                            break;
                        case "Q":
                            inputstream = new FileInputStream("Images\\w_queen_2x_ns.png");
                            break;
                        case "k":
                            inputstream = new FileInputStream("Images\\b_king_2x_ns.png");
                            break;
                        case "K":
                            inputstream = new FileInputStream("Images\\w_king_2x_ns.png");
                            break;
                        case "p":
                            inputstream = new FileInputStream("Images\\b_pawn_2x_ns.png");
                            break;
                        case "P":
                            inputstream = new FileInputStream("Images\\w_pawn_2x_ns.png");
                            break;
                        case ".":
                            continue;
                    }
                    ImageView imageView = new ImageView(new Image(inputstream));
                    imageView.setX(x * 75);
                    imageView.setY(y * 75);
                    imageView.setFitHeight(75);
                    imageView.setFitWidth(75);
                    imageView.setPreserveRatio(true);
                    pane.getChildren().add(imageView);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
  /*  public  ArrayList<Component> getImageGrid() throws FileNotFoundException {
        FileInputStream inputstream = new FileInputStream("Images\\b_pawn_2x_ns.png");
        ArrayList<Component> components = new ArrayList<>();
        for (int i = 63; i >= 0 ; i--){
            switch (board[i]){
                case "r" :  inputstream = new FileInputStream("Images\\b_rook_2x_ns.png"); break;
                case "R" :  inputstream = new FileInputStream("Images\\w_rook_2x_ns.png"); break;
                case "n" :  inputstream = new FileInputStream("Images\\b_knight_2x_ns.png"); break;
                case "N" :  inputstream = new FileInputStream("Images\\W_knight_2x_ns.png"); break;
                case "b" :  inputstream = new FileInputStream("Images\\b_bishop_2x_ns.png"); break;
                case "B" :  inputstream = new FileInputStream("Images\\w_bishop_2x_ns.png"); break;
                case "q" :  inputstream = new FileInputStream("Images\\b_queen_2x_ns.png"); break;
                case "Q" :  inputstream = new FileInputStream("Images\\w_queen_2x_ns.png"); break;
                case "k" :  inputstream = new FileInputStream("Images\\b_king_2x_ns.png"); break;
                case "K" :  inputstream = new FileInputStream("Images\\w_king_2x_ns.png"); break;
                case "p" :  inputstream = new FileInputStream("Images\\b_pawn_2x_ns.png"); break;
                case "P" :  inputstream = new FileInputStream("Images\\w_pawn_2x_ns.png"); break;
            }
            ImageView imageView = new ImageView(new Image(inputstream));
            imageView.setX((7-getFileFromIndex(i)) * 75);
            imageView.setY((7 -getRankFromIndex(i))  * 75);
            imageView.setFitHeight(75);
            imageView.setFitWidth(75);
            imageView.setPreserveRatio(true);
            components.add(new Component( (7 -getFileFromIndex(i))  * 75,(7-getRankFromIndex(i)) * 75,imageView));
        }
        return  components;
    }*/
}
