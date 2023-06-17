package logic;

import java.util.ArrayList;
import java.util.Random;

public class Game implements Runnable {
    private SnakeModel snakeModel;
    private boolean isRunning;
    private Random random;
    private ArrayList<BodyPart> bodyParts;
    public Game(SnakeModel snakeModel) {
        this.isRunning = true;
        this.snakeModel = snakeModel;
        this.random = new Random();
        this.bodyParts = new ArrayList<>();
        setSnake();
        for (int i = 0; i < 5; i++) {
            spawnApple();
        }
    }

    @Override
    public void run() {
        while(isRunning){
            try {
                Thread.sleep(200);
                if(!checkForCollision()){
                    snakeModel.addScore(10);
                    moveSnake();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void spawnApple(){
        int x = random.nextInt(25);
        int y = random.nextInt(16);
        if((int)(snakeModel.getValueAt(x, y)) == 0){
            snakeModel.setValueAt(3, x, y);
        }else{
            spawnApple();
        }
    }

    public void setSnake(){
        snakeModel.setValueAt(1, 11, 7);
        bodyParts.add(new BodyPart(11, 7, 11, 7, 1));
    }

    public void moveSnake(){
        int y = 0, x = 0;
        switch(snakeModel.getDirection()){
            case LEFT -> x = -1;
            case UP -> y = -1;
            case RIGHT -> x = 1;
            case DOWN -> y = 1;
        }

        for (int i = 0; i < bodyParts.size(); i++) {
            BodyPart bodyPart = bodyParts.get(i);
            if(i == 0){
                checkForApple(bodyPart.getCurrY() + y, bodyPart.getCurrX() + x);
                snakeModel.setValueAt(0, bodyPart.getCurrY(), bodyPart.getCurrX());
                bodyPart.setCurrY(bodyPart.getCurrY() + y);
                bodyPart.setCurrX(bodyPart.getCurrX() + x);
                snakeModel.setValueAt(bodyPart.getValue(), bodyPart.getCurrY(), bodyPart.getCurrX());
            }else{
                snakeModel.setValueAt(0, bodyPart.getCurrY(), bodyPart.getCurrX());
                bodyPart.setCurrY(bodyPart.getNextY());
                bodyPart.setCurrX(bodyPart.getNextX());
                snakeModel.setValueAt(bodyPart.getValue(), bodyPart.getCurrY(), bodyPart.getCurrX());
                bodyPart.setNextY(bodyParts.get(i - 1).getCurrY());
                bodyPart.setNextX(bodyParts.get(i - 1).getCurrX());
            }
        }
    }

    public void addTail(){
        BodyPart bp1 = bodyParts.get(bodyParts.size() - 1);
        if(bodyParts.size() == 1){
            int y = bp1.getCurrY(), x = bp1.getCurrX();
            switch(snakeModel.getDirection()){
                case LEFT -> x += 1;
                case UP -> y += 1;
                case RIGHT -> x -= 1;
                case DOWN -> y -= 1;
            }
            bodyParts.add(new BodyPart(y, x, bp1.getCurrY(), bp1.getCurrX(), 2));
            snakeModel.setValueAt(2, y, x);
        }else{
            BodyPart bp2 = bodyParts.get(bodyParts.size() - 2);
            int y = bp1.getCurrY() - bp2.getCurrY();
            int x = bp1.getCurrX() - bp2.getCurrX();
            bodyParts.add(new BodyPart(bp1.getCurrY() + y, bp1.getCurrX() + x, bp1.getCurrY(),
                    bp1.getCurrX(), 2));
            snakeModel.setValueAt(2, bp1.getCurrY() + y, bp1.getCurrX() + x);
        }
    }

    public void checkForApple(int y, int x){
        if((int)(snakeModel.getValueAt(y, x)) == 3){
            snakeModel.addScore(100);
            addTail();
            spawnApple();
        }
    }

    public boolean checkForCollision(){
        BodyPart bodyPart = bodyParts.get(0);
        int y = bodyPart.getCurrY(), x = bodyPart.getCurrX();
        switch(snakeModel.getDirection()){
            case LEFT -> x -= 1;
            case UP -> y -= 1;
            case RIGHT -> x += 1;
            case DOWN -> y += 1;
        }

        if(x < 0 || x > 15 || y < 0 || y > 24){
            System.out.println("Collision with wall");
            isRunning = false;
            return true;
        }
        if((int)(snakeModel.getValueAt(y, x)) == 2){
            System.out.println("Collision with tail");
            isRunning = false;
            return true;
        }

        return false;
    }
}
