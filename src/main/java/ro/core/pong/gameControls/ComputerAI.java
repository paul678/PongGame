package ro.core.pong.gameControls;

import ro.core.pong.PongPlayer;
import ro.core.pong.graphics.Ball;

/**
 * Created by CORE on 1/14/2015.
 */
public class ComputerAI {
    private static final double timeBetweenUpdates = 0.2;
    
    private PongPlayer mPlayer;
    private Ball mBall;

    public ComputerAI(PongPlayer player, Ball ball) {
        mPlayer = player;
        mBall = ball;
    }

    private double timeSinceLastUpdate = 0;

    public void update(double deltaTime)
    {
        timeSinceLastUpdate += deltaTime;

        if (timeSinceLastUpdate < timeBetweenUpdates) {
            return; /* Wait a while longer. */
        }

        timeSinceLastUpdate -= timeBetweenUpdates;

        double distanceFromPaddle = mPlayer.getX() - mBall.getX();
        
        /*
         * Do nothing if the ball is not moving towards us.
         */
        if (Math.signum(distanceFromPaddle) != Math.signum(mBall.getSpeed())) {
            mPlayer.setMovement(InteractDirection.NONE);
            return;
        }

        /*
         * Find out where the ball is heading for and move in that direction (this does not look
         * ahead past collisions).
         */
        double targetY = mBall.getY() + distanceFromPaddle * Math.tan(mBall.getAngle()) + mBall.getHeight();
        boolean paddleOnTarget = targetY >= mPlayer.getY() && targetY + mBall.getHeight() <= mPlayer.getY() + mPlayer.getHeight();
        if (paddleOnTarget) {
            mPlayer.setMovement(InteractDirection.NONE);
           // mPlayer.setY(mPlayer.getY() + mBall.getHeight());
        } else if (targetY < mPlayer.getY()) {
            mPlayer.setMovement(InteractDirection.UP);
        } else if (targetY > mPlayer.getY()) {
            mPlayer.setMovement(InteractDirection.DOWN);
        }
    }
}
