package ro.core.pong.gameControls;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Base class to handle keyboard events
 * Implement the two abstract methods to set custom behaviour
 * Created by Paul Berbec.
 */
public abstract class KeyboardEventHandler implements EventHandler<KeyEvent> {

    @Override
    public void handle(KeyEvent event) {
        EventType<KeyEvent> type = event.getEventType();
        if(type == KeyEvent.KEY_RELEASED) {
            handleKeyUp(event.getCode());
        } else if(type == KeyEvent.KEY_PRESSED) {
            handleKeyDown(event.getCode());
        }
    }

    protected abstract void handleKeyUp(KeyCode code);

    protected abstract void handleKeyDown(KeyCode code);
}
