package com.example.project;

public class ViewpagerItem {
    String heading, numtotal, numleft;

    int image;


    public ViewpagerItem(String heading, String numtotal, String numleft, int image) {
        this.heading = heading;
        this.numtotal = numtotal;
        this.numleft = numleft;
        this.image = image;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getNumtotal() {
        return numtotal;
    }

    public void setNumtotal(String numtotal) {
        this.numtotal = numtotal;
    }

    public String getNumleft() {
        return numleft;
    }

    public void setNumleft(String numleft) {
        this.numleft = numleft;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
