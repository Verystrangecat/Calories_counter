package com.example.project;

public class Step {
    int amount_steps;
    double date;

    public Step(int amount_steps, double date) {
        this.amount_steps = amount_steps;
        this.date = date;
    }

    public int getAmount_steps() {
        return amount_steps;
    }

    public void setAmount_steps(int amount_steps) {
        this.amount_steps = amount_steps;
    }

    public double getDate() {
        return date;
    }

    public void setDate(double date) {
        this.date = date;
    }
}
