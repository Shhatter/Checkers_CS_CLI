package checkers.classes;

import checkers.enums.PawnColor;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;




public class FieldViewControl {

    StackPane gridField;
    int n;
    int m;
    ImageView viewedImage;
    PawnColor pawnColor;


    public FieldViewControl(StackPane gridField, int n, int m, PawnColor pawnColor) {

        this.gridField = gridField;
        this.n = n;
        this.m = m;
        this.pawnColor = pawnColor;
        ImageView viewedImage = new ImageView();

    }

    public PawnColor getPawnColor() {
        return pawnColor;
    }

    public void setPawnColor(PawnColor pawnColor) {
        this.pawnColor = pawnColor;
    }

    public StackPane getGridField() {
        return gridField;
    }

    public void setGridField(StackPane gridField) {
        this.gridField = gridField;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public ImageView getViewedImage() {
        return viewedImage;
    }

    public void setViewedImage(ImageView viewedImage) {
        this.viewedImage = viewedImage;
    }

}
