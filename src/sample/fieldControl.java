package sample;

import javafx.scene.layout.StackPane;




public class fieldControl {

    StackPane gridField;
    int n;
    int m;

    public fieldControl(StackPane gridField, int n, int m, PawnColor pawnColor) {
        this.gridField = gridField;
        this.n = n;
        this.m = m;
        this.pawnColor = pawnColor;
    }
/*
  ##########################################################
    */
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

    public PawnColor getPawnColor() {
        return pawnColor;
    }

    public void setPawnColor(PawnColor pawnColor) {
        this.pawnColor = pawnColor;
    }

    PawnColor pawnColor;



}
