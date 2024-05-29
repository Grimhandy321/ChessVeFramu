package com.example.Game;

public class M_Timer {
    int maxTime;
    int currentTime = 0;

    public M_Timer(int maxTime) {
        this.maxTime = maxTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }
    public int getRemainingTime(){
        return maxTime - currentTime;
    }
    public void decrement(){
        if(currentTime > 0){
            currentTime--;
        }
    }
}
