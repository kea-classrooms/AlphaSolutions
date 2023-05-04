package com.example.alphasolutions.mainClasses;

public class SubTasks {
        private int id,cost, timeEstimate;
        private String name;

        public SubTasks(int id, String name,  int cost,int timeEstimate) {
            this.id = id;
            this.name = name;
            this.cost = cost;
            this.timeEstimate = timeEstimate;
        }

    }
    // consider making it a subclass
