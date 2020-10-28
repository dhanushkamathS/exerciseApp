package com.example.fitnessproject;

import java.io.Serializable;

public class ExeDatatype implements Serializable {
    String exeName;
    int img;
    String  reps;
    boolean useTimer;

    public ExeDatatype(String exeName, int img, String reps, boolean useTimer) {
        this.exeName = exeName;
        this.img = img;
        this.reps = reps;
        this.useTimer = useTimer;
    }

    public ExeDatatype(String exeName, int img, String reps) {
        this.exeName = exeName;
        this.img = img;
        this.reps = reps;
    }
    public ExeDatatype(String exeName, String reps) {
        this.exeName = exeName;
        this.reps = reps;
    }
    public ExeDatatype() {
    }

    public ExeDatatype(String exeName) {
        this.exeName = exeName;
    }

    public String getExeName() {
        return exeName;
    }

    public void setExeName(String exeName) {
        this.exeName = exeName;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) { this.reps = reps; }

    public boolean isUseTimer() {
        return useTimer;
    }

    public void setUseTimer(boolean useTimer) {
        this.useTimer = useTimer;
    }
}
