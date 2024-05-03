package com.example.Ui;

import com.example.Game.GameGrid;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private int gridSize = 75;

    @FXML
    private AnchorPane pane;

    private GridHandler gridHandler;
    private GameGrid gameGrid = new GameGrid();
    ArrayList<Component> images ;
    int x;
    int y;
    int selectedPiece ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gridHandler = new GridHandler(pane.getPrefWidth(), pane.getPrefHeight(), gridSize, pane);
        gridHandler.updateGrid();
        try {
            images = gameGrid.getImageGrid();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (Component c : images){
            pane.getChildren().add(c.getImage());
        }
        pane.setOnMousePressed(mouseEvent -> {
            double  mouseAnchorX = mouseEvent.getSceneX();
            double mouseAnchorY = mouseEvent.getSceneY();
            int x = (int) ((mouseAnchorX/gridSize) % 8);
            int y = (int) ((mouseAnchorY/gridSize) % 8);
            System.out.println(x + "," + y);
            if(selectedPiece != 100){

                selectedPiece = 100;
            }else {
                selectedPiece = gameGrid.getIndexFrom(x,y);
            }
        });
    }
}
