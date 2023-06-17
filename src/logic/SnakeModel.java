package logic;

import events.DirectionEvent;
import events.DirectionListener;
import events.ScoreEvent;
import events.ScoreListener;
import other.Direction;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SnakeModel extends AbstractTableModel implements DirectionListener {
    private int[][] board;
    private Direction direction;
    private int score;
    public SnakeModel() {
        this.board = new int[25][16];
        this.direction = Direction.DOWN;
        this.score = 0;
        Game game = new Game(this);
        new Thread(game).start();
    }

    @Override
    public int getRowCount() {
        return this.board.length;
    }

    @Override
    public int getColumnCount() {
        return this.board[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.board[rowIndex][columnIndex];
    }

    @Override
    public void directionSet(DirectionEvent e) {
        if(e.getDirection() == Direction.LEFT && this.direction != Direction.RIGHT){
            this.direction = e.getDirection();
        }else if(e.getDirection() == Direction.UP && this.direction != Direction.DOWN){
            this.direction = e.getDirection();
        }
        else if(e.getDirection() == Direction.RIGHT && this.direction != Direction.LEFT){
            this.direction = e.getDirection();
        }
        else if(e.getDirection() == Direction.DOWN && this.direction != Direction.UP){
            this.direction = e.getDirection();
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public void setValueAt(int value, int rowIndex, int columnIndex){
        this.board[rowIndex][columnIndex] = value;
        this.fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void addScore(int score){
        this.score += score;
        fireScoreChange();
    }

    private ArrayList<ScoreListener> listeners = new ArrayList<>();

    public void addScoreListener(ScoreListener listener){
        this.listeners.add(listener);
    }

    public void fireScoreChange(){
        ScoreEvent event = new ScoreEvent(this, this.score);
        for(ScoreListener listener : listeners){
            listener.scoreChange(event);
        }
    }
}
