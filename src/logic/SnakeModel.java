package logic;

import events.DirectionEvent;
import events.DirectionListener;

import javax.swing.table.AbstractTableModel;

public class SnakeModel extends AbstractTableModel implements DirectionListener {
    private int[][] board;
    private Direction direction;
    public SnakeModel() {
        this.board = new int[25][16];
        board[10][10] = 3;
        this.direction = Direction.DOWN;
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
        this.direction = e.getDirection();
    }

    public Direction getDirection() {
        return direction;
    }
}
