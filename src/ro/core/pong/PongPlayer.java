package ro.core.pong;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import ro.core.pong.gameControls.InteractDirection;
import ro.core.pong.gameControls.Interactable;

import java.util.HashMap;

/**
 * Created by Paul Berbec.
 */
public class PongPlayer implements Interactable {
    private HashMap<InteractDirection, KeyCode> controlMap;
    private final GraphicsContext mGraphicContext;

    public PongPlayer(GraphicsContext graphicContext) {
        mGraphicContext = graphicContext;
    }

    @Override
    public void goDown() {

    }

    @Override
    public void goUp() {

    }

    @Override
    public void stop() {

    }

    @Override
    public KeyCode getActionKeyCode(InteractDirection action) {
        return controlMap.get(action);
    }
}
