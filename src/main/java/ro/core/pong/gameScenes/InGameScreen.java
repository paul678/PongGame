package ro.core.pong.gameScenes;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import ro.core.pong.PongGame;

import static ro.core.pong.Utils.Constants.*;

/**
 * Created by CORE on 1/12/2015.
 */
public class InGameScreen extends Pane
{
    private final PongGame mGame;
    private final Text playerScore = new Text("0");
    private final Text opponentScore = new Text("0");
    private Line mFieldSeparator = new Line(WIDTH/2, 0, WIDTH/2, HEIGHT);

    public InGameScreen(PongGame game)
    {
        this.mGame = game;
        mFieldSeparator.getStrokeDashArray().addAll(25d, 10d);
        mFieldSeparator.getStyleClass().add("separator");

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
        getChildren().addAll(
                mFieldSeparator,
                game.getBall().getShape(),
                game.getPlayer().getShape(),
                game.getOpponent().getShape(),
                playerScore,
                opponentScore);
        getStyleClass().add("screen");

        addEventHandler(KeyEvent.ANY, game.getControlHandler());
    }
}
