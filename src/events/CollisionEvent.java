package events;

import java.util.EventObject;

public class CollisionEvent extends EventObject {
    private int score;
    public CollisionEvent(Object source, int score) {
        super(source);
        this.score = score;
    }

    public int getScore(){
        return this.score;
    }
}
