package logic;

public class Game implements Runnable {
    private SnakeModel snakeModel;
    public Game(SnakeModel snakeModel) {
        this.snakeModel = snakeModel;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(500);
                System.out.println(snakeModel.getDirection());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
