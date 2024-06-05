package org.example.object;


import java.awt.*;
import java.util.Random;

public class Ball {
    public double x;
    public double y;
    public int size;
    public double speedX;
    public double speedY;
    public Color color;
    public Ball(){
        Random random = new Random();
        this.x = random.nextInt(500 - 0 + 1) + 0;
        this.y = random.nextInt(500 - 0 + 1) + 0;
        this.size = random.nextInt(50-20+1)+20;
        this.speedX = random.nextInt(5- (-5)+1)-5;
        this.speedY = random.nextInt(5- (-5) +1)-5;
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
            System.out.println("INTERSECTED");
            return true;
        }
        return false;
    }
}
