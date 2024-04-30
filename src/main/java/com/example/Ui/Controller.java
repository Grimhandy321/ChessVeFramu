package com.example.Ui;

import com.example.Game.GameGrid;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private int gridSize = 75;

    @FXML
    private AnchorPane pane;

    private GridHandler gridHandler;
    private DraggableMakerGrid draggableMakerGrid;
    private DraggableMaker draggableMaker = new DraggableMaker();
    private GameGrid gameGrid = new GameGrid();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        draggableMakerGrid = new DraggableMakerGrid(pane.getPrefWidth(), pane.getPrefHeight(), gridSize, pane);
        gridHandler = new GridHandler(pane.getPrefWidth(), pane.getPrefHeight(), gridSize, pane);
        gridHandler.updateGrid();
        try {
            for (Component c : gameGrid.getImageGrid()) {
                pane.getChildren().add(draggableMakerGrid.makeDraggable(c));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
