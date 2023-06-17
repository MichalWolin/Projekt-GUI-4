package events;

import java.util.EventObject;

public class ScoreEvent extends EventObject {
    private int score;
    public ScoreEvent(Object source, int score) {
        super(source);
        this.score = score;
    }

    public int getScore(){
        return this.score;
    }
}
