package org.example.draw;


import org.example.object.Ball;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CollidingBalls extends JFrame {
    public static final int BALL_COUNT = 20;
    private final List<Ball> balls = new ArrayList<>();
    private float ovalScale = 1.2f;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int SHADOW_OFFSET = 4;
    private static final int TRANSFORMATION_DURATION = 200;
    private Timer ovalTimer;
    public CollidingBalls(){
        setTitle("Bouncing Ball");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for(int i = 0; i< BALL_COUNT; i++){
            balls.add(new Ball());
        }
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Ball ball: balls){
                    moveBall(ball);
                }
                repaint();
            }
        });
        timer.start();
        Thread ovalThread = new Thread(new OvalTransformation());
        ovalThread.start();
    }

    private void moveBall(Ball ball) {
        ball.x = ball.x + ball.speedX;
        ball.y = ball.y + ball.speedY;
        if ((ball.x <=0 && ball.speedX<0)|| (ball.x >=WIDTH-ball.size && ball.speedX>0)){
            ball.speedX = -ball.speedX;
//            ball.speedX += ball.speedX>0?1:-1;
        }
        if((ball.y <= 0 && ball.speedY <0)|| (ball.y >= HEIGHT-ball.size && ball.speedY>0)){
            ball.speedY = -(ball.speedY);
//            ball.speedY += ball.speedY>0?1:-1;
        }
        if(ball.x<0){
            ball.x=0;
        }
        if(ball.y<0){
            ball.y=0;
        }
        if(ball.x>WIDTH-ball.size){
            ball.x=WIDTH-ball.size;
        }
        if(ball.y>HEIGHT-ball.size){
            ball.y=HEIGHT-ball.size;
        }
        for(Ball anotherBall: balls){
            if(anotherBall != ball && ball.intersect(anotherBall)){
                handleCollision(ball,anotherBall);
            }
        }

    }

    private void handleCollision(Ball ball, Ball anotherBall) {
        double normalX = ball.x - anotherBall.x;
        double normalY = ball.y - anotherBall.y;
        double distance = Math.sqrt(Math.pow(normalX,2)+Math.pow(normalY,2));
        normalX /=distance;
        normalY /=distance;

    // Calculate overlap distance
        double overlap = ball.size + anotherBall.size - distance;

    // Separate the balls to eliminate overlap
        anotherBall.x -= overlap * normalX / 2;
        anotherBall.y -= overlap * normalY / 2;
        ball.x += overlap * normalX / 2;
        ball.y += overlap * normalY / 2;

        double tangentX = -normalY;
        double tangentY = normalX;
        // Dot product of velocity and normal/tangent vectors
        double v1Normal = normalX * anotherBall.speedX + normalY * anotherBall.speedY;
        double v1Tangent = tangentX * anotherBall.speedX + tangentY * anotherBall.speedY;
        double v2Normal = normalX * ball.speedX + normalY * ball.speedY;
        double v2Tangent = tangentX * ball.speedX + tangentY * ball.speedY;

        // Swap the normal components of the velocities
        double anotherBallVxNormal = v2Normal * normalX;
        double anotherBallVyNormal = v2Normal * normalY;
        double ballVxNormal = v1Normal * normalX;
        double ballVyNormal = v1Normal * normalY;

        // Update velocities
        anotherBall.speedX =anotherBallVxNormal + v1Tangent * tangentX;
        anotherBall.speedY = anotherBallVyNormal + v1Tangent * tangentY;
        ball.speedX = ballVxNormal + v2Tangent * tangentX;
        ball.speedY = ballVyNormal + v2Tangent * tangentY;
        ball.color = anotherBall.color;
//        anotherBall.size = ball.size;

    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        balls.forEach(ball->drawBall(g,ball));
        g.drawRect(0,0,WIDTH,HEIGHT);
    }

    private void drawBall(Graphics g, Ball ball) {
        // Draw the shadow
        g.setColor(Color.BLACK);
        drawOval(g, ball.x -((double) ball.size /2)+ SHADOW_OFFSET, ball.y -((double) ball.size /2)+ SHADOW_OFFSET, ball.size, ball.size);

        // Draw the ball
        g.setColor(ball.color);
        drawOval(g, ball.x -((double) ball.size /2), ball.y-((double) ball.size /2), ball.size, ball.size);
    }


    private void drawOval(Graphics g, double x, double y, int width, int height) {
        g.fillOval((int)Math.round(x), (int)Math.round(y), (int) (width * ovalScale), height);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CollidingBalls frame = new CollidingBalls();
                frame.setVisible(true);
            }
        });
    }

    private class OvalTransformation implements Runnable {
        @Override
        public void run(){
            try {
                Thread.sleep(TRANSFORMATION_DURATION);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}