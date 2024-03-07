package com.example.project;

public class ViewpagerItem {
    String heading, numtotal, numleft;

    int image;

    /**
     * constructor for ViewpagerItem
     * @param heading-string
     * @param numtotal-string
     * @param numleft-string
     * @param image -int
     */
    public ViewpagerItem(String heading, String numtotal, String numleft, int image) {
        this.heading = heading;
        this.numtotal = numtotal;
        this.numleft = numleft;
        this.image = image;
    }

    /**
     *
     * @return string heading
     */
    public String getHeading() {
        return heading;
    }

    /**
     * sets heading
     * @param heading-String
     */
    public void setHeading(String heading) {
        this.heading = heading;
    }
    /**
     * Gets the total number associated with the ViewpagerItem.
     *
     * @return The total number as a string.
     */
    public String getNumtotal() {
        return numtotal;
    }

    /**
     * Sets the total number for the ViewpagerItem.
     *
     * @param numtotal The string representing the total number to set.
     */
    public void setNumtotal(String numtotal) {
        this.numtotal = numtotal;
    }

    /**
     * Gets the remaining number associated with the ViewpagerItem.
     *
     * @return The remaining number as a string.
     */
    public String getNumleft() {
        return numleft;
    }

    /**
     * Sets the remaining number for the ViewpagerItem.
     *
     * @param numleft The string representing the remaining number to set.
     */
    public void setNumleft(String numleft) {
        this.numleft = numleft;
    }

    /**
     * Gets the image resource ID associated with the ViewpagerItem.
     *
     * @return The image resource ID.
     */
    public int getImage() {
        return image;
    }

    /**
     * Sets the image resource ID for the ViewpagerItem.
     *
     * @param image The image resource ID to set.
     */
    public void setImage(int image) {
        this.image = image;
    }

}
