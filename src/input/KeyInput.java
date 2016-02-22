package input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author test
 */
public class KeyInput extends KeyAdapter {
    
    public static final Set<Integer> pressed = new HashSet<>();
    
    @Override
    public void keyPressed (KeyEvent e){
        pressed.add(e.getKeyCode());
    }
    
    @Override
    public void keyReleased (KeyEvent e){
        pressed.remove(e.getKeyCode());
    }
}
