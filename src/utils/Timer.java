package utils;

import gamestates.GameStateManager;

/**
 * Created by Martin Omacht on 26. 2. 2016.
 *
 * Slouží k odpočtu času
 */
public class Timer {

    protected long startTicks;
    protected int duration;

    /**
     * Vytvoří nový časovač s určitou délkou
     * @param duration - počet ticků
     */
    public Timer(int duration) {
        this.duration = duration;
        startTicks = -1;
    }

    /**
     * Zapne nebo restartuje časovač
     * @return this
     */
    public Timer start(){
        startTicks = GameStateManager.getTicks();
        return this;
    }

    /**
     * Vypne časovač
     * @return this
     */
    public Timer stop(){
        startTicks = -1;
        return this;
    }

    /**
     * Nastaví délku časovače
     * @param duration - počet ticků
     * @return this
     */
    public Timer setDuration(int duration){
        this.duration = duration;
        return this;
    }

    /**
     * Přidá délku časovače
     * @param duration - počet ticků
     * @return this
     */
    public Timer addDuration(int duration){
        this.duration += duration;
        return this;
    }

    /**
     * @return duration
     */
    public int getDuration(){
        return duration;
    }

    /**
     * Vrátí zda časovač již skončil
     * @return whether timer has finished
     */
    public boolean hasFinished(){
        if (startTicks == -1)
            return false;

        return (GameStateManager.getTicks() - startTicks) >= duration;
    }

    /**
     * Vrátí zda časovač běží nebo ne
     * @return wheter timer is running
     */
    public boolean isRunning(){
        if(GameStateManager.getTicks() - startTicks >= duration)
            stop();

        return startTicks != -1;
    }
}
