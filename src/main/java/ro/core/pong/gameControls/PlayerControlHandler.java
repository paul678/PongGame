package ro.core.pong.gameControls;

import javafx.scene.input.KeyCode;

import java.util.ArrayList;

/**
 * Created by Paul Berbec.
 */
public class PlayerControlHandler extends KeyboardEventHandler {
    private static PlayerControlHandler mInstance = new PlayerControlHandler();
    ArrayList<Interactable> mPlayerList;

    public static PlayerControlHandler getInstance() {
        return mInstance;
    }
    private PlayerControlHandler() {
        mPlayerList = new ArrayList<>();
    }

    public void addPlayer(Interactable player) {
        mPlayerList.add(player);
    }

    @Override
    protected void handleKeyUp(KeyCode code) {
        mPlayerList.stream().filter(player -> !player.ignoreKeyCode(code)).forEach(Interactable::stop);
    }

    @Override
    protected void handleKeyDown(KeyCode code) {
        for (Interactable player : mPlayerList) {
            if (code.equals(player.getActionKeyCode(InteractDirection.UP))) {
                player.goUp();
            } else if (code.equals(player.getActionKeyCode(InteractDirection.DOWN))) {
                player.goDown();

            }
        }
    }
}
