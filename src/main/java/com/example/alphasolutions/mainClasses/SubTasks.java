package com.example.alphasolutions.mainClasses;

public class SubTasks {


    private int id, cost, timeEstimate;
    private String name;

    public SubTasks(int id, String name, int cost, int timeEstimate) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.timeEstimate = timeEstimate;
    }

    public int getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    public int getTimeEstimate() {
        return timeEstimate;
    }

    public String getName() {
        return name;
    }

}
// consider making it a subclass


