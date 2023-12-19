package com.example.project;

public class ViewpagerItem {
    String heading;
    double numtotal, numleft;
    int image;


    public ViewpagerItem(String heading, double numtotal, double numleft, int image) {
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

    public double getNumtotal() {
        return numtotal;
    }

    public void setNumtotal(double numtotal) {
        this.numtotal = numtotal;
    }

    public double getNumleft() {
        return numleft;
    }

    public void setNumleft(double numleft) {
        this.numleft = numleft;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
