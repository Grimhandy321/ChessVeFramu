package com.example.Ui;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Component {

    private Image image;
    private int startPositionX;
    private int startPositionY;

    public Component( int startPositionX, int startPositionY,Image image) {
        this.startPositionX = startPositionX;
        this.startPositionY = startPositionY;
        this.image = image;
    }
    public Component( int startPositionX, int startPositionY) {
        this.startPositionX = startPositionX;
        this.startPositionY = startPositionY;
        try {
            FileInputStream inputstream = new FileInputStream("Images\\b_pawn_2x_ns.png");
            image = new Image(inputstream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getStartPositionX() {
        return startPositionX;
    }

    public void setStartPositionX(int startPositionX) {
        this.startPositionX = startPositionX;
    }

    public int getStartPositionY() {
        return startPositionY;
    }

    public void setStartPositionY(int startPositionY) {
        this.startPositionY = startPositionY;
    }
}
