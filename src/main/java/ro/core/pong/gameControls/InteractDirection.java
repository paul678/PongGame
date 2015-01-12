package ro.core.pong.gameControls;

/**
 * Created by Paul Berbec.
 */
public enum InteractDirection {
    UP(1), DOWN(-1), NONE(0);
    int value;

    InteractDirection(int value) {
        this.value = value;
    }
}
