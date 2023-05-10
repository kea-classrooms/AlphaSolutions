package com.example.alphasolutions.DTOs;

public class PositionDTO {
    private int posID;
    private String posName;


    public PositionDTO(int posID, String posName) {
        this.posID = posID;
        this.posName = posName;
    }

    public int getPosID() {
        return posID;
    }

    public void setPosID(int posID) {
        this.posID = posID;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }
}
