package ro.core.pong;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import ro.core.pong.gameControls.PlayerControlHandler;

/**
 * Created by Paul Berbec.
 */
public class PongGame {
    private final Stage mPrimaryStage;
    private GraphicsContext mGraphicsContext;
    private PongPlayer mPlayerOne;

    public PongGame(Stage primaryStage) {
        this.mPrimaryStage = primaryStage;
        mPlayerOne = new PongPlayer(mGraphicsContext);
    }

    private void initUI() {
        Group root = new Group();
        Scene scene = new Scene(root, 1024, 768);

        scene.addEventHandler(KeyEvent.ANY, new PlayerControlHandler(mPlayerOne));

        Canvas canvas = new Canvas();
        canvas.widthProperty().bind(mPrimaryStage.widthProperty());
        canvas.heightProperty().bind(mPrimaryStage.heightProperty());

        mGraphicsContext = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);

        mPrimaryStage.setTitle("Pong !");
        mPrimaryStage.setScene(scene);
    }

    private class GameAnimationTimer extends AnimationTimer {

        @Override
        public void handle(long now) {
            mGraphicsContext.clearRect(0, 0, mPrimaryStage.getWidth(), mPrimaryStage.getHeight());
            //update and draw graphic objects here
        }
    }
}
