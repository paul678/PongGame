package ro.core.pong;

import javafx.animation.AnimationTimer;
import ro.core.pong.gameControls.InteractDirection;
import ro.core.pong.graphics.Ball;

import java.util.Random;

import static ro.core.pong.Utils.Constants.*;

/**
 * Created by Paul Berbec.
 */
public class PongGame {

    public enum State
    {
        PLAYING, PAUSED, ENDED;
    }

    private final GameLoop loop = new GameLoop();
    private State mGameState = State.ENDED;
    private static final Random mRandom = new Random();
    private final int mWinningScore;

    private PongPlayer mPlayer = new PongPlayer("Player", PLAYER_PADDLE_SPEED);
    private PongPlayer mOpponent = new PongPlayer("Opponent", OPPONENT_PADDLE_SPEED);
    private final Ball mBall = new Ball(BALL_MAX_SPEED);

    public PongGame(int maxScore)
    {
        this.mWinningScore = maxScore;
        loop.start();
    }

    public void start()
    {
        mPlayer.getPaddle().setX(MARGIN_LEFT_RIGHT + GOAL_WIDTH - PADDLE_WIDTH); /* Aligned with the goal area. */
        mPlayer.getPaddle().setY((HEIGHT - PADDLE_HEIGHT) / 2); /* Centered. */

        mOpponent.getPaddle().setX(WIDTH - MARGIN_LEFT_RIGHT - GOAL_WIDTH); /* Aligned with the goal area. */
        mOpponent.getPaddle().setY((HEIGHT - PADDLE_HEIGHT) / 2); /* Centered. */

        mPlayer.setScore(0);
        mOpponent.setScore(0);

        mPlayer.getPaddle().setMovement(InteractDirection.NONE);
        mOpponent.getPaddle().setMovement(InteractDirection.NONE);

        launchBall();

        mGameState = State.PLAYING;
    }

    public void launchBall()
    {
        boolean towardsOpponent = mRandom.nextBoolean();
        double initialAngle = PADDLE_SECTION_ANGLES[mRandom.nextInt(2) + 1]; /* We don't use the steepest angle. */

        mBall.setSpeed(towardsOpponent ? -BALL_INITIAL_SPEED : BALL_INITIAL_SPEED);
        mBall.setAngle(towardsOpponent ? -initialAngle : initialAngle);
        mBall.setX((WIDTH - BALL_SIZE) / 2); /* Centered. */
        mBall.setY(MARGIN_TOP_BOTTOM);
    }

    private void update(double deltaTime)
    {
        if (mGameState == State.PAUSED || mGameState == State.ENDED) {
            return; /* This is necessary because the loop keeps running even when the game is paused or stopped. */
        }

        //update objects
        mPlayer.update(deltaTime);
        mOpponent.update(deltaTime);
        mBall.update(deltaTime);
    }

    public State getGameState() {
        return mGameState;
    }

    public Ball getBall() {
        return mBall;
    }

    public PongPlayer getPlayer() {
        return mPlayer;
    }

    public PongPlayer getOpponent() {
        return mOpponent;
    }

    private class GameLoop extends AnimationTimer {

        private long previousTime = 0;

        @Override
        public void handle(long currentTime)
        {
            /*
             * If this is the first frame, simply record an initial time.
             */
            if (previousTime == 0) {
                previousTime = currentTime;
                return;
            }

            double secondsElapsed = (currentTime - previousTime) / 1_000_000_000.0; /* Convert nanoseconds to seconds. */

            /*
             * Avoid large time steps by imposing an upper bound.
             */
            if (secondsElapsed > 0.0333) {
                secondsElapsed = 0.0333;
            }

            update(secondsElapsed);

            previousTime = currentTime;
        }
    }
}
