package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import events.DirectionEvent;
import events.DirectionListener;
import logic.*;
import other.Direction;

public class SnakeView extends JTable {
    public SnakeView(SnakeModel snakeModel) {
        super(snakeModel);
        this.setRowHeight(20);
        for (int i = 0; i < 16; i++) {
            this.getColumnModel().getColumn(i).setPreferredWidth(20);
            this.getColumnModel().getColumn(i).setMinWidth(20);
            this.getColumnModel().getColumn(i).setMaxWidth(20);
        }

        this.setAutoResizeMode(AUTO_RESIZE_OFF);
        this.setGridColor(Color.BLACK);

        this.setDefaultRenderer(Object.class, new SnakeCellRenderer());

        addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        int keyCode = e.getKeyCode();
                        switch(keyCode){
                            case KeyEvent.VK_UP -> fireDirectionSet(Direction.UP);
                            case KeyEvent.VK_DOWN -> fireDirectionSet(Direction.DOWN);
                            case KeyEvent.VK_LEFT -> fireDirectionSet(Direction.LEFT);
                            case KeyEvent.VK_RIGHT -> fireDirectionSet(Direction.RIGHT);
                        }
                    }
                    @Override
                    public void keyTyped(KeyEvent e) {

                    }
                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
    }

    private ArrayList<DirectionListener> listeners = new ArrayList<>();

    public void addDirectionListener(DirectionListener listener){
        this.listeners.add(listener);
    }

    public void fireDirectionSet(Direction direction){
        DirectionEvent event = new DirectionEvent(this, direction);
        for(DirectionListener listener : listeners){
            listener.directionSet(event);
        }
    }
}


