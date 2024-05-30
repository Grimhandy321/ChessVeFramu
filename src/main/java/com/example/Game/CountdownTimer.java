package com.example.Game;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {
    private long milliseconds;
    private Timer timer;
    private boolean isDone = false;

    public CountdownTimer(long milliseconds) {
        this.milliseconds = milliseconds;
        this.timer = new Timer();
    }

    public boolean isDone() {
        return isDone;
    }

    public void start() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (milliseconds > 0) {
                    milliseconds -= 10;
                } else {
                    timer.cancel();
                    isDone = true;
                }
            }
        }, 0, 10); // Adjust interval to 10 milliseconds for better resolution
    }

    public long getRemainingTime() {
        return milliseconds;
    }
}
