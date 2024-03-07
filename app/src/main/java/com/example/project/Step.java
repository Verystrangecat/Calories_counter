package com.example.project;

public class Step {
    int amount_steps;
    double date;

    /**
     *
     * @param amount_steps int
     * @param date double
     */
    public Step(int amount_steps, double date) {
        this.amount_steps = amount_steps;
        this.date = date;
    }

    /**
     *
     * @return amount_steps
     */
    public int getAmount_steps() {
        return amount_steps;
    }

    /**
     *
     * @param amount_steps
     * sets the amount steps
     */
    public void setAmount_steps(int amount_steps) {
        this.amount_steps = amount_steps;
    }

    /**
     *
     * @return double date
     */
    public double getDate() {
        return date;
    }

    /**
     * sets the date
     * @param date
     */
    public void setDate(double date) {
        this.date = date;
    }
}
//todo check if i can delete the date
