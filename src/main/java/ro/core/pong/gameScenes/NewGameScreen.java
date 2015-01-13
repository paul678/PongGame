package ro.core.pong.gameScenes;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import static ro.core.pong.Utils.Constants.*;

/**
 * Created by CORE on 1/12/2015.
 */
public class NewGameScreen extends Pane {

    private Runnable onStart = () -> {
    }; /* Do nothing for now. */

    public void setOnStart(Runnable onStart) {
        this.onStart = onStart;
    }

    public NewGameScreen() {
        Text header = new Text("pong");
        header.boundsInLocalProperty().addListener(observable ->
        {
            /*
             * When using CSS, the width and height (with CSS applied) aren't available right away.
             * Therefore, we listen for changes and update the position once the width and height
             * are available.
             */
            header.setTranslateX((WIDTH - header.getBoundsInLocal().getWidth()) / 2); /* Centered. */
            header.setTranslateY(TEXT_MARGIN_TOP_BOTTOM);
        });
        header.getStyleClass().add("header");

        Text info = new Text("Player 1: use the W(up) and S(down) keys to move\nPlayer 2: use arrow keys to move\n\npress enter to start\npress escape to quit");
        info.boundsInLocalProperty().addListener(observable ->
        {
            /*
             * When using CSS, the width and height (with CSS applied) aren't available right away.
             * Therefore, we listen for changes and update the position once the width and height
             * are available.
             */
            info.setTranslateX((WIDTH - info.getBoundsInLocal().getWidth()) / 2); /* Centered. */
            info.setTranslateY(HEIGHT - TEXT_MARGIN_TOP_BOTTOM - info.getBoundsInLocal().getHeight());
        });
        info.getStyleClass().add("info");

        setPrefSize(WIDTH, HEIGHT);
        getChildren().addAll(header, info);
        getStyleClass().add("screen");

        setOnKeyPressed(event ->
        {
            if (event.getCode() == KeyCode.ENTER) {
                onStart.run();
            } else if (event.getCode() == KeyCode.ESCAPE) {
                Platform.exit();
            }
        });
    }
}
