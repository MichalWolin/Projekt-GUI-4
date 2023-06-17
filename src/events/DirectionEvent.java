package events;

import logic.Direction;

import java.util.EventObject;

public class DirectionEvent extends EventObject {
    private Direction direction;

    public DirectionEvent(Object source, Direction direction) {
        super(source);
        this.direction = direction;
    }

    public Direction getDirection(){
        return this.direction;
    }
}
