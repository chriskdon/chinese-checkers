package ca.brocku.chinesecheckers.gamestate;

/**
 * Created by zz on 2/5/14.
 */
public class CcPlayer implements Player {
    String name;
    public CcPlayer(String n) {
        name = n;
    }
    public String getName() {
        return name;
    }
}
