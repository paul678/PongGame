package ro.core.pong;

import javafx.animation.AnimationTimer;
import ro.core.pong.Utils.CollisionDetector;
import ro.core.pong.gameControls.ComputerAI;
import ro.core.pong.gameControls.InteractDirection;
import ro.core.pong.gameControls.PlayerControlHandler;
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
    public enum GameType {
        SINGLE_PLAYER, MUTIPLAYER, ONLY_AI;
    }
    public static final GameType mType = GameType.SINGLE_PLAYER;

    private final GameLoop loop = new GameLoop();
    private State mGameState = State.ENDED;
    private static final Random mRandom = new Random();
    private final int mWinningScore;

    private PlayerControlHandler mControlHandler = PlayerControlHandler.getInstance();
    private CollisionDetector mCollisionDetector = CollisionDetector.getInstance();
    private ComputerAI mComputerAI;
    private ComputerAI mComputerAI2;

    private PongPlayer mPlayer = new PongPlayer(PlayerType.PLAYER_LEFT, PLAYER_PADDLE_SPEED);
    private PongPlayer mOpponent = new PongPlayer(PlayerType.PLAYER_RIGHT, PLAYER_PADDLE_SPEED);
    private final Ball mBall = new Ball(BALL_MAX_SPEED);

    public PongGame(int maxScore)
    {
        this.mWinningScore = maxScore;
        initControlHandler();
        initCollisionDetector();
        loop.start();
    }

    private void initControlHandler() {

        switch (mType) {
            case SINGLE_PLAYER:
                mControlHandler.addPlayer(mPlayer);
                mComputerAI = new ComputerAI(mOpponent, mBall);
                break;
            case MUTIPLAYER:
                mControlHandler.addPlayer(mPlayer);
                mControlHandler.addPlayer(mOpponent);
                break;
            case ONLY_AI:
                mComputerAI = new ComputerAI(mOpponent, mBall);
                mComputerAI2 = new ComputerAI(mPlayer, mBall);
                break;
        }
    }

    private void initCollisionDetector() {
        mCollisionDetector.addObject(mPlayer);
        mCollisionDetector.addObject(mOpponent);
        mCollisionDetector.addObject(mBall);
    }

    public void start()
    {
        mPlayer.setX(MARGIN_LEFT_RIGHT + GOAL_WIDTH - PADDLE_WIDTH); /* Aligned with the goal area. */
        mPlayer.setY((HEIGHT - PADDLE_HEIGHT) / 2); /* Centered. */

        mOpponent.setX(WIDTH - MARGIN_LEFT_RIGHT - GOAL_WIDTH); /* Aligned with the goal area. */
        mOpponent.setY((HEIGHT - PADDLE_HEIGHT) / 2); /* Centered. */

        mPlayer.setScore(0);
        mOpponent.setScore(0);

        mPlayer.setMovement(InteractDirection.NONE);
        mOpponent.setMovement(InteractDirection.NONE);

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

        if(mComputerAI != null) {
            mComputerAI.update(deltaTime);
        }

        if(mComputerAI2 != null) {
            mComputerAI2.update(deltaTime);
        }

        if(mCollisionDetector.runCollisionChecks()) {
            //reset ball on any scored goal
            launchBall();
        }
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

    public PlayerControlHandler getControlHandler() {
        return mControlHandler;
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
