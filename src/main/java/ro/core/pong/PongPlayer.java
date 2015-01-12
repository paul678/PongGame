package ro.core.pong;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyCode;
import ro.core.pong.gameControls.InteractDirection;
import ro.core.pong.gameControls.Interactable;
import ro.core.pong.graphics.Paddle;

import java.util.HashMap;

/**
 * Created by Paul Berbec.
 */
public class PongPlayer implements Interactable {
    private HashMap<InteractDirection, KeyCode> mControlMap = new HashMap<>();
    private String mName;
    private final IntegerProperty mScore = new SimpleIntegerProperty(0);
    private Paddle mPlayerPaddle;

    public PongPlayer(String name, double paddleSpeed) {
        this.mName = name;
        mPlayerPaddle = new Paddle(paddleSpeed);
        initControlMap();
    }

    @Override
    public void initControlMap() {
        if(mControlMap == null) mControlMap = new HashMap<>();
        mControlMap.put(InteractDirection.UP, KeyCode.W);
        mControlMap.put(InteractDirection.DOWN, KeyCode.S);
    }

    @Override
    public void goDown() {
        if(mPlayerPaddle.getMovement() == InteractDirection.NONE) {
            mPlayerPaddle.setMovement(InteractDirection.DOWN);
        }
    }

    @Override
    public void goUp() {
        if(mPlayerPaddle.getMovement() == InteractDirection.NONE) {
            mPlayerPaddle.setMovement(InteractDirection.UP);
        }
    }

    @Override
    public void stop() {
        mPlayerPaddle.setMovement(InteractDirection.NONE);
    }

    @Override
    public KeyCode getActionKeyCode(InteractDirection action) {
        return mControlMap.get(action);
    }

    public Paddle getPaddle() {
        return mPlayerPaddle;
    }

    public IntegerProperty getScoreProp() {
        return mScore;
    }

    public int getScore() {
        return mScore.get();
    }

    public void setScore(int mScore) {
        this.mScore.set(mScore);
    }
}
