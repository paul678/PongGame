package ro.core.pong.Utils;

import javafx.scene.media.AudioClip;
import ro.core.pong.PlayerType;
import ro.core.pong.PongPlayer;
import ro.core.pong.graphics.Ball;
import ro.core.pong.graphics.GraphicObject;
import ro.core.pong.graphics.PlanarObject;

import java.util.ArrayList;
import java.util.List;

import static ro.core.pong.Utils.Constants.*;

/*
 * Created by CORE on 1/13/2015.
*/

public class CollisionDetector {

    private static CollisionDetector mInstance = new CollisionDetector();

    private Ball mBall;
    private List<PongPlayer> mPlayers;

    public static CollisionDetector getInstance() {
        return mInstance;
    }

    private CollisionDetector() {
        mPlayers = new ArrayList<>();
    }

    public void addObject(Object obj) throws UnsupportedOperationException {
        if (obj instanceof GraphicObject) {
            mBall = (Ball) obj; // only one ball supported now
        } else if (obj instanceof PongPlayer) {
            mPlayers.add((PongPlayer) obj); //include support for 2x2 in the future
        } else {
            throw new UnsupportedOperationException("Cannot handle this type of object");
        }
    }

    /**
     * Runs all collision test including goal check
     * @return if a goal was scored or not
     */
    public boolean runCollisionChecks() {
        checkTopBottomCollision(mBall);
        mPlayers.forEach(this::checkTopBottomCollision);
        return checkGoal();

    }

    private boolean checkGoal() {
        for(PongPlayer player : mPlayers) {
            if(checkGoal(player)) return true;
        }
        return false;
    }

    private void checkTopBottomCollision(PlanarObject object) {
        boolean hitTopWall = object.getY() < MARGIN_TOP_BOTTOM;
        boolean hitBottomWall = object.getY() + object.getHeight() > HEIGHT - MARGIN_TOP_BOTTOM;

        if ((hitTopWall || hitBottomWall) && object instanceof Ball) {
            ((Ball) object).setAngle(((Ball) object).getAngle() * -1);
            new AudioClip(Sounds.HIT_WALL).play();
        }

        if (hitTopWall) {
            object.setY(MARGIN_TOP_BOTTOM);
        } else if (hitBottomWall) {
            object.setY(HEIGHT - MARGIN_TOP_BOTTOM - object.getHeight());
        }
    }

    private boolean checkGoal(PongPlayer player) {
        boolean ballHitEdge;
        if (player.getType() == PlayerType.PLAYER_LEFT) {
            ballHitEdge = mBall.getX() < MARGIN_LEFT_RIGHT + GOAL_WIDTH;
        } else {
            ballHitEdge = mBall.getX() + BALL_SIZE > WIDTH - MARGIN_LEFT_RIGHT - GOAL_WIDTH;
        }
        if (!ballHitEdge) {
            return false;
        }

        boolean ballHitPaddle = mBall.getY() + BALL_SIZE > player.getY() && mBall.getY() < player.getY() + PADDLE_HEIGHT;
        if (ballHitPaddle) {

            /*
             * Find out what section of the paddle was hit.
             */
            for (int i = 0; i < PADDLE_SECTIONS; i++) {
                boolean ballHitCurrentSection = mBall.getY() < player.getY() + (i + 0.5) * PADDLE_SECTION_HEIGHT;
                if (ballHitCurrentSection) {
                    mBall.setAngle(PADDLE_SECTION_ANGLES[i] * (player.getType() == PlayerType.PLAYER_RIGHT ? -1 : 1));
                    break; /* Found our match. */
                } else if (i == PADDLE_SECTIONS - 1) { /* If we haven't found our match by now, it must be the last section. */
                    mBall.setAngle(PADDLE_SECTION_ANGLES[i] * (player.getType() == PlayerType.PLAYER_RIGHT ? -1 : 1));
                }
            }

            /*
             * Update and reposition the ball.
             */
            mBall.setSpeed(mBall.getSpeed() * BALL_SPEED_INCREASE);
            if (player.getType() == PlayerType.PLAYER_LEFT) {
                mBall.setX(MARGIN_LEFT_RIGHT + GOAL_WIDTH);
            } else {
                mBall.setX(WIDTH - MARGIN_LEFT_RIGHT - GOAL_WIDTH - BALL_SIZE);
            }
            new AudioClip(Sounds.HIT_PADDLE).play();
            return false;
        } else {

            /*
             * Update the score.
             */
            if (player.getType() == PlayerType.PLAYER_RIGHT) {
                new AudioClip(Sounds.SCORE_PLAYER).play();
            } else {
                new AudioClip(Sounds.SCORE_OPPONENT).play();
            }
            player.setScore(player.getScore() + 1);
            return true;
        }
     }
}
