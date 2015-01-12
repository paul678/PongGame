package ro.core.pong.gameControls;

import javafx.scene.input.KeyCode;

/**
 * Created by Paul Berbec.
 */
public class PlayerControlHandler extends KeyboardEventHandler {
    Interactable mPlayer;

    public PlayerControlHandler(Interactable player) {
        mPlayer = player;
    }

    @Override
    protected void handleKeyUp(KeyCode code) {
        mPlayer.stop();
    }

    @Override
    protected void handleKeyDown(KeyCode code) {
        if(code.equals(mPlayer.getActionKeyCode(InteractDirection.UP))) {
            mPlayer.goUp();
        } else if(code.equals(mPlayer.getActionKeyCode(InteractDirection.DOWN))) {
            mPlayer.goDown();
        }
    }
}
