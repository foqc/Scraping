package com.foqc.entities;

/**
 *
 * @author foqc
 */
public class Entry {

    private int numberOrder;
    private String title;
    private int amountComents;
    private int amountPoints;

    public Entry() {
        numberOrder = 0;
        title = "";
        amountComents = 0;
        amountPoints = 0;
    }

    public Entry(int numberOrder, String title, int amountComents, int amountPoints) {
        this.numberOrder = numberOrder;
        this.title = title;
        this.amountComents = amountComents;
        this.amountPoints = amountPoints;
    }
    
    

    public int getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(int numberOrder) {
        this.numberOrder = numberOrder;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAmountComents() {
        return amountComents;
    }

    public void setAmountComents(int amountComents) {
        this.amountComents = amountComents;
    }

    public int getAmountPoints() {
        return amountPoints;
    }

    public void setAmountPoints(int amountPoints) {
        this.amountPoints = amountPoints;
    }

}
