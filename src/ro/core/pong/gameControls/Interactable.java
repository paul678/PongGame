package ro.core.pong.gameControls;

import javafx.scene.input.KeyCode;

/**
 * Created by Paul Berbec.
 */
public interface Interactable {
    void goDown();
    void goUp();
//    void goLeft();
//    void goRight();
    void stop();
    KeyCode getActionKeyCode(InteractDirection action);
}
