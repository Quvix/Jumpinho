package utils;

import gamestates.GameStateManager;
import main.GameCanvas;

/**
 * Created by Martin Omacht on 26. 2. 2016.
 */
public class Timer {

    private long startTicks;
    private int duration;

    public Timer(int duration) {
        this.duration = duration;
        startTicks = -1;
    }

    public Timer start(){
        startTicks = GameStateManager.getTicks();
        return this;
    }

    public Timer stop(){
        startTicks = -1;
        return this;
    }

    public Timer setDuration(int duration){
        this.duration = duration;
        return this;
    }

    public Timer addDuration(int duration){
        this.duration += duration;
        return this;
    }

    public boolean hasFinished(){
        if (startTicks == -1)
            return false;

        return (GameStateManager.getTicks() - startTicks) % duration == 0;
    }

    public boolean isRunning(){
        if(GameStateManager.getTicks() - startTicks >= duration)
            stop();

        return startTicks != -1;
    }
}
