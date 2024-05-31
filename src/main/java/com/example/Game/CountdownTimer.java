package com.example.Game;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {
    private long milliseconds;
    private Timer timer;
    private boolean isDone = false;

    public CountdownTimer() {
        this.timer = new Timer();
    }

    public boolean isDone() {
        return isDone;
    }

    public void start() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                    milliseconds += 10;
            }
        }, 0, 10); // Adjust interval to 10 milliseconds for better resolution
    }

    public long getRemainingTime() {
        return milliseconds;
    }
}
