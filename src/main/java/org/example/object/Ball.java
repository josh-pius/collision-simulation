package org.example.object;


import java.awt.*;
import java.util.Random;

public class Ball {
    public static final int MAX_X = 1800;
    public static final int MAX_Y = 1000;
    public static final int MIN_Y = 0;
    public static final int MIN_X = 0;
    public static final int MAX_SIZE = 40;
    public static final int MIN_SIZE = 10;
    public static final int MAX_SPEED = 5;
    public static final int MIN_SPEED = -5;
    public double x;
    public double y;
    public int size;
    public double speedX;
    public double speedY;
    public Color color;
    public Ball(){
        Random random = new Random();
        this.x = random.nextInt(MAX_X - MIN_X + 1) + MIN_X;
        this.y = random.nextInt(MAX_Y - MIN_Y + 1) + MIN_Y;
        this.size = random.nextInt(MAX_SIZE - MIN_SIZE +1)+ MIN_SIZE;
        this.speedX = random.nextInt(MAX_SPEED - MIN_SPEED+1)+ MIN_SPEED;
        this.speedY = random.nextInt(MAX_SPEED- MIN_SPEED +1) + MIN_SPEED;
        this.color = randomColor();
    }

    private Color randomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r,g,b);
    }

    public boolean intersect(Ball anotherBall) {
        double d = Math.sqrt(Math.pow(anotherBall.x-this.x,2)+Math.pow(anotherBall.y-this.y,2));
        if(d<=anotherBall.size+this.size){
//            System.out.println("INTERSECTED");
            return true;
        }
        return false;
    }
}
