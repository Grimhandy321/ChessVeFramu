package com.example.Ui;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class DraggableMakerGrid extends GridBase{

    private double mouseAnchorX;
    private double mouseAnchorY;

    public DraggableMakerGrid(double planeWidth, double planeHeight, int gridSize, AnchorPane anchorPane) {
        super(planeWidth, planeHeight, gridSize, anchorPane);
    }

    public void makeDraggable(Node node){
        node.setOnMouseReleased(mouseEvent -> {
            mouseAnchorX = mouseEvent.getSceneX();
            mouseAnchorY = mouseEvent.getSceneY();

            int x = (int) ((mouseAnchorX/getGridSize()) % getTilesAcross()) * getGridSize();
            int y = (int) ((mouseAnchorY/getGridSize()) % getTilesDown()) * getGridSize();

            node.setLayoutX(x);
            node.setLayoutY(y);
        });
    }

    public Node makeDraggable(Component component){
        ImageView imageView = new ImageView(component.getImage());
        imageView.setX(component.getStartPositionX()+ 5);
        imageView.setY(component.getStartPositionY());
        imageView.setFitHeight(75);
        imageView.setFitWidth(75);
        imageView.setPreserveRatio(true);
        Node node = imageView;
        node.setOnMouseDragged(mouseEvent -> {
            mouseAnchorX = mouseEvent.getSceneX();
            mouseAnchorY = mouseEvent.getSceneY();
            int x = (int) (Math.floor(mouseAnchorX/getGridSize())) * getGridSize();
            int y = (int) (Math.floor(mouseAnchorY/getGridSize())) * getGridSize();

            node.setLayoutX(x - component.getStartPositionX());
            node.setLayoutY(y - component.getStartPositionY());
        });
        return node;
    }
}