package logic;

import javax.swing.table.AbstractTableModel;

public class SnakeModel extends AbstractTableModel {
    private int[][] board;

    public SnakeModel() {
        this.board = new int[25][16];
        board[10][10] = 3;
        Game game = new Game();
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
}
