package logic;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class LeaderboardModel extends AbstractTableModel {
    private ArrayList<Player> players;

    public LeaderboardModel(int score) {
        players = new ArrayList<>();
        readLeaderboard();
        addPlayer(score);
    }

    @Override
    public int getRowCount() {
        return 10;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < players.size()) {
            Player player = players.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> player.getName();
                case 1 -> player.getScore();
                default -> null;
            };
        }
        return null;
    }

    public void addPlayer(int score) {
        boolean isQualifying = players.size() < 10 || score > players.get(players.size() - 1).getScore();

        if (isQualifying) {
            String playerName = JOptionPane.showInputDialog("Please enter your name:");
            if (playerName != null && !playerName.isEmpty()) {
                Player newPlayer = new Player(playerName, score);
                players.add(newPlayer);

                players = players.stream()
                        .sorted(Comparator.comparingInt(Player::getScore).reversed())
                        .limit(10)
                        .collect(Collectors.toCollection(ArrayList::new));

                fireTableDataChanged();
                writeLeaderboard();
            }
        }
    }

    public void readLeaderboard() {
        File file = new File("src/leaderboard.bin");

        try{
            FileInputStream fis = new FileInputStream(file);

            int nameLength;
            while((nameLength = fis.read()) != -1){
                StringBuilder name = new StringBuilder();
                for (int j = 0; j < nameLength; j++) {
                    name.append((char) fis.read());
                }
                int score = 0;
                for (int j = 3; j >= 0; j--) {
                    score |= fis.read() << (j * 8);
                }
                players.add(new Player(name.toString(), score));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeLeaderboard() {
        File file = new File("src/leaderboard.bin");

        try{
            FileOutputStream fos = new FileOutputStream(file);

            for (int i = 0; i < 10 && players.size() > i; i++) {
                Player player = players.get(i);
                fos.write(player.getName().length());
                for (int j = 0; j < player.getName().length(); j++) {
                    fos.write(player.getName().charAt(j));
                }
                for (int j = 3; j >= 0; j--) {
                    fos.write(player.getScore() >> (j * 8));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
