package ro.core.pong.graphics;

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
            this.mSpeed = Math.min(speed, mSpeed);
        } else {
            this.mSpeed = Math.max(speed, -mSpeed);
        }
    }

    @Override
    public void update(long deltaTime) {
        double distanceTravelled = mSpeed * deltaTime;
        double deltaX = distanceTravelled * Math.cos(mAngle);
        double deltaY = distanceTravelled * Math.sin(mAngle);

        setX(getX() + deltaX);
        setY(getY() + deltaY);
    }
}
