package ro.core.pong.graphics;

import ro.core.pong.gameControls.InteractDirection;

/**
 * Created by Paul Berbec.
 */
public class Paddle extends GraphicObject {

    private final double mSpeed;

    private InteractDirection mMovement = InteractDirection.NONE;

    public Paddle(double speed)
    {
        this.mSpeed = speed;
    }

    public double getSpeed() {
        return mSpeed;
    }

    public InteractDirection getMovement() {
        return mMovement;
    }

    public void setMovement(InteractDirection movement) {
        this.mMovement = movement;
    }

    @Override
    public void update(double deltaTime) {
        if (mMovement == InteractDirection.DOWN) {
            setY(getY() + mSpeed * deltaTime);
        } else if (mMovement == InteractDirection.UP) {
            setY(getY() - mSpeed * deltaTime);
        }
    }
}
