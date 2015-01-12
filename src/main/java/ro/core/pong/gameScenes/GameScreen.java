package ro.core.pong.gameScenes;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import ro.core.pong.PongGame;
import ro.core.pong.gameControls.PlayerControlHandler;

import static ro.core.pong.Utils.Constants.*;

/**
 * Created by CORE on 1/12/2015.
 */
public class GameScreen extends Pane
{
    private final PongGame game;

    private final Rectangle ball = new Rectangle(BALL_SIZE, BALL_SIZE);
    private final Rectangle player = new Rectangle(PADDLE_WIDTH, PADDLE_HEIGHT);
    private final Rectangle opponent = new Rectangle(PADDLE_WIDTH, PADDLE_HEIGHT);

    private final Text playerScore = new Text("0");
    private final Text opponentScore = new Text("0");

    public GameScreen(PongGame game)
    {
        this.game = game;

        ball.translateXProperty().bind(game.getBall().getXProp());
        ball.translateYProperty().bind(game.getBall().getYProp());
        ball.getStyleClass().add("ball");

        player.translateXProperty().bind(game.getPlayer().getPaddle().getXProp());
        player.translateYProperty().bind(game.getPlayer().getPaddle().getYProp());
        player.getStyleClass().add("paddle");

        opponent.translateXProperty().bind(game.getOpponent().getPaddle().getXProp());
        opponent.translateYProperty().bind(game.getOpponent().getPaddle().getYProp());
        opponent.getStyleClass().add("paddle");

        playerScore.textProperty().bind(game.getPlayer().getScoreProp().asString());
        playerScore.boundsInLocalProperty().addListener(observable ->
        {
            /*
             * When using CSS, the width and height (with CSS applied) aren't available right away.
             * Therefore, we listen for changes and update the position once the width and height
             * are available.
             */
            playerScore.setTranslateX(WIDTH / 2 - SCORE_SPACING / 2 - playerScore.getBoundsInLocal().getWidth());
        });
        playerScore.setTranslateY(TEXT_MARGIN_TOP_BOTTOM);
        playerScore.getStyleClass().add("score");

        opponentScore.textProperty().bind(game.getOpponent().getScoreProp().asString());
        opponentScore.setTranslateX(WIDTH / 2 + SCORE_SPACING / 2);
        opponentScore.setTranslateY(TEXT_MARGIN_TOP_BOTTOM);
        opponentScore.getStyleClass().add("score");

        setPrefSize(WIDTH, HEIGHT);
        getChildren().addAll(ball, player, opponent, playerScore, opponentScore);
        getStyleClass().add("screen");

        addEventHandler(KeyEvent.ANY, new PlayerControlHandler(game.getPlayer()));
//        setOnKeyPressed(this::keyPressed);
//        setOnKeyReleased(this::keyReleased);
    }

/*    private void keyPressed(KeyEvent event)
    {
        if (event.getCode() == KeyCode.P) {
            game.pause();
        } else if (event.getCode() == KeyCode.ESCAPE) {
            game.forfeit();
        } else if (game.getPlayer().getPaddle().getMovement() == Paddle.Movement.NONE && event.getCode() == KeyCode.UP) {
            game.getPlayer().getPaddle().setMovement(Movement.UP);
        } else if (game.getPlayer().getPaddle().getMovement() == Movement.NONE && event.getCode() == KeyCode.DOWN) {
            game.getPlayer().getPaddle().setMovement(Movement.DOWN);
        }
    }*/
}
