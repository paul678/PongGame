package ro.core.pong;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Shape;
import ro.core.pong.gameControls.InteractDirection;
import ro.core.pong.gameControls.Interactable;
import ro.core.pong.graphics.Paddle;
import ro.core.pong.graphics.PlanarObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul Berbec.
 */
public class PongPlayer implements Interactable, PlanarObject {
    private PlayerType mType;
    private final IntegerProperty mScore = new SimpleIntegerProperty(0);
    private Paddle mPlayerPaddle;

    public PongPlayer(PlayerType type, double paddleSpeed) {
        this.mType = type;
        mPlayerPaddle = new Paddle(paddleSpeed);
    }

    public void update(double deltaTime) {
        mPlayerPaddle.update(deltaTime);
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
        if(mPlayerPaddle.getMovement() != InteractDirection.NONE) {
            mPlayerPaddle.setMovement(InteractDirection.NONE);
        }
    }

    @Override
    public KeyCode getActionKeyCode(InteractDirection action) {
        return ControlSchema.getActionKeyCode(mType, action);
    }

    @Override
    public boolean ignoreKeyCode(KeyCode code) {
        return ControlSchema.ignoreKeyCode(mType, code);
    }

    public IntegerProperty getScoreProp() {
        return mScore;
    }

    public int getScore() {
        return mScore.get();
    }

    public PlayerType getType() {
        return mType;
    }

    public void setScore(int score) {
        mScore.set(score);
    }

    public void setX(double x) {
        mPlayerPaddle.setX(x);
    }

    @Override
    public double getX() {
        return mPlayerPaddle.getX();
    }

    public void setY(double y) {
        mPlayerPaddle.setY(y);
    }

    @Override
    public double getY() {
        return mPlayerPaddle.getY();
    }

    @Override
    public Shape getShape() {
        return mPlayerPaddle.getShape();
    }

    @Override
    public double getWidth() {
        return mPlayerPaddle.getWidth();
    }

    @Override
    public double getHeight() {
        return mPlayerPaddle.getHeight();
    }

    public void setMovement(InteractDirection movement) {
        mPlayerPaddle.setMovement(movement);
    }


    private static class ControlSchema {
        private static final List<KeyCode> LEFT = new ArrayList<>();
        private static final List<KeyCode> RIGHT = new ArrayList<>();

        static {
            LEFT.add(KeyCode.W);LEFT.add(KeyCode.S);
            RIGHT.add(KeyCode.UP);RIGHT.add(KeyCode.DOWN);
        }

        private ControlSchema() {
        }

        public static KeyCode getActionKeyCode(PlayerType type, InteractDirection action) {
            switch (type) {
                case PLAYER_LEFT:
                    return LEFT.get(action.ordinal());
                case PLAYER_RIGHT:
                    return RIGHT.get(action.ordinal());
                default:
                    return null;
            }
        }

        public static boolean ignoreKeyCode(PlayerType type, KeyCode code) {
            switch (type) {
                case PLAYER_LEFT:
                    return !LEFT.contains(code);
                case PLAYER_RIGHT:
                    return !RIGHT.contains(code);
                default:
                    return true;
            }
        }
    }
}
