package com.example.Ui;

import com.example.Game.CountdownTimer;
import com.example.Game.GameGrid;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
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
    boolean isGameFinished = false;
    int gameTime ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gridHandler = new GridHandler(pane.getPrefWidth(), pane.getPrefHeight(), gridSize, pane);
        gridHandler.updateGrid();
        CountdownTimer countdownTimer = new CountdownTimer();
        countdownTimer.start();
        DrawGame();
        pane.setOnMousePressed(mouseEvent -> {
            double  mouseAnchorX = mouseEvent.getSceneX();
            double mouseAnchorY = mouseEvent.getSceneY();
            x = (int) ((mouseAnchorX/gridSize) % 8);
            y = (int) ((mouseAnchorY/gridSize) % 8);
            if(isGameFinished){
                Text text = new Text();
                text.setFont(new Font(50));
                text.setX(pane.getHeight()/2 -200 );
                text.setY(pane.getHeight()/2 );
                text.setText("Game over"+ gameTime );
                pane.getChildren().add(text);
            }
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
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            if(moved) {
                hasSelectedPiece = !hasSelectedPiece;
                gameGrid.getBoard().doMove(gameGrid.getBestMove());
                DrawGame();
                if(gameGrid.getBoard().isMated()){
                    isGameFinished = true;
                }
                moved = false;
            }
           }
        });
    }
    public void DrawGame()  {
        pane.getChildren().clear();
        gridHandler.updateGrid();
        String file = new String();
        try {
            for (int x = 0; x <= 7; x++) {
                for (int y = 0; y <= 7; y++) {

                    switch (gameGrid.getGrid()[y][x]) {
                        case "r":
                            file = "b_rook_2x_ns.png";
                            break;
                        case "R":
                            file = "w_rook_2x_ns.png";
                            break;
                        case "n":
                            file = "b_knight_2x_ns.png";
                            break;
                        case "N":
                            file = "W_knight_2x_ns.png";
                            break;
                        case "b":
                            file = "b_bishop_2x_ns.png";
                            break;
                        case "B":
                            file = "w_bishop_2x_ns.png";
                            break;
                        case "q":
                            file = "b_queen_2x_ns.png";
                            break;
                        case "Q":
                            file = "w_queen_2x_ns.png";
                            break;
                        case "k":
                            file = "b_king_2x_ns.png";
                            break;
                        case "K":
                            file = "w_king_2x_ns.png";
                            break;
                        case "p":
                            file = "b_pawn_2x_ns.png";
                            break;
                        case "P":
                            file = "w_pawn_2x_ns.png";
                            break;
                        case ".":
                            continue;
                    }
                    BufferedImage bufferedImage = ImageIO.read(Objects.requireNonNull(Controller.class.getResource(file)));
                    ImageView imageView = new ImageView(convertToFxImage(bufferedImage));
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
    private static Image convertToFxImage(BufferedImage image) {// java Moment
        WritableImage writableImage = null;
        if (image != null) {
            writableImage = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = writableImage.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }

        return new ImageView(writableImage).getImage();
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
