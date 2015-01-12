package ro.core.pong.Utils;

/**
 * Created by CORE on 1/11/2015.
 */
public class Sounds
{
    private Sounds() {}

    public static final String HIT_WALL = Sounds.class.getResource("../hit-wall.m4a").toExternalForm();
    public static final String HIT_PADDLE = Sounds.class.getResource("../hit-paddle.m4a").toExternalForm();
    public static final String SCORE_PLAYER = Sounds.class.getResource("../score-player.m4a").toExternalForm();
    public static final String SCORE_OPPONENT = Sounds.class.getResource("../score-opponent.m4a").toExternalForm();
}
