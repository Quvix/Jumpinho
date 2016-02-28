package utils;

import gamestates.GameStateManager;

/**
 * Created by Martin on 27.2.2016.
 *
 * Časovač, který běží stále dokola.
 * Užití pro pravidelné eventy
 */
public class TimerLooping extends Timer {

    public TimerLooping(int duration) {
        super(duration);
    }

    @Override
    public boolean isRunning(){
        return startTicks != -1;
    }

    @Override
    public boolean hasFinished(){
        return startTicks != -1 && (GameStateManager.getTicks() - startTicks) % duration == 0;
    }
}
