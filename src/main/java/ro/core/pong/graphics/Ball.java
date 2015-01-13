package ro.core.pong.graphics;

import static ro.core.pong.Utils.Constants.BALL_SIZE;

/**
 * Created by Paul Berbec.
 */
public class Ball extends GraphicObject{

    private final double mMaxSpeed;
    private double mAngle = 0;
    private double mSpeed = 0;

    public Ball(double maxSpeed)
    {
        this.mMaxSpeed = maxSpeed;

        mShape.setWidth(BALL_SIZE);
        mShape.setHeight(BALL_SIZE);
        mShape.translateXProperty().bind(getXProp());
        mShape.translateYProperty().bind(getYProp());
        mShape.getStyleClass().add("ball");
    }

    public double getMaxSpeed() {
        return mMaxSpeed;
    }

    public double getAngle() {
        return mAngle;
    }

    public void setAngle(double angle) {
        this.mAngle = angle;
    }

    public double getSpeed() {
        return mSpeed;
    }

    public void setSpeed(double speed) {
        if (speed >= 0) {
            this.mSpeed = Math.min(speed, mMaxSpeed);
        } else {
            this.mSpeed = Math.max(speed, -mMaxSpeed);
        }
    }

    @Override
    public void update(double deltaTime) {
        double distanceTravelled = mSpeed * deltaTime;
        double deltaX = distanceTravelled * Math.cos(mAngle);
        double deltaY = distanceTravelled * Math.sin(mAngle);

        setX(getX() + deltaX);
        setY(getY() + deltaY);
    }
}
