package gamestates;

import java.awt.Graphics2D;
import java.util.HashMap;

/**
 *
 * @author Jakub Vitásek & Matěj Stuchlík
 *
 * Singleton (existuje pouze jedna instance)
 *
 * Přepíná mezi stavama hry
 *
 * Příklad užití:
 * GameStateManager.changeState(GameStateManager.State.PLAYSTATE);
 */
public final class GameStateManager {

    public enum State {
        PLAYSTATE,

        EXIT
    }

    private static GameStateManager instance = new GameStateManager();

    public static GameStateManager getInstance() {
            return instance;
        }

    // seznam stavů
    private HashMap<State, GameState> gamestates = new HashMap<>();
    // právě používaný stav
    private GameState currentState;

    private long ticks = 0;
    
    public GameStateManager(){
        // Vytvoření všech stavů
        gamestates.put(State.PLAYSTATE, new PlayState());

        // Nastavení defaultního stavu
        currentState = gamestates.get(State.PLAYSTATE);
    }

    public void tick(){
        ticks++;
        currentState.tick();
    }

    public void draw(Graphics2D g, double interpolation){
        currentState.draw(g, interpolation);
    }

    /**
     * @param state one of the states from enum State (eg. GameStateManager.State.PLAYSTATE)
     * @return newly set GameState
     */
    public static GameState changeState(State state){
        if (state == State.EXIT)
            System.exit(0);

        GameStateManager gsm = GameStateManager.getInstance();

        gsm.currentState.pause();
        gsm.currentState = gsm.gamestates.get(state);
        gsm.currentState.resume();

        return gsm.currentState;
    }

    public static GameState getCurrentState(){
        return GameStateManager.getInstance().currentState;
    }

    public static long getTicks(){
        return GameStateManager.getInstance().ticks;
    }
}
