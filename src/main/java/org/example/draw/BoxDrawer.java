package org.example.draw;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoxDrawer extends JPanel {
    private int ballX;
    private int ballY;
    private float ovalScale = 1.2f;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final int BALL_SIZE = 50;
    private static final int SHADOW_OFFSET = 5;
    private static final int TRANSFORMATION_DURATION = 200;
    public BoxDrawer(){
        Timer timer = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBall();
                repaint();
            }
        });
        timer.start();
        Thread ovalThread = new Thread(new OvalTransformation());
        ovalThread.start();
    }
    public void setDimensions(int length, int width){
        this.length = length;
        this.width = width;
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        drawBall(g);
        g.drawRect(50,50,500,500);
        g.drawOval(50,50,50,50);
    }
    // Method to draw the ball and its shadow
    private void drawBall(Graphics g) {
        // Clear the previous frame
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // Draw the shadow
        g.setColor(Color.BLACK);
        drawOval(g, ballX + SHADOW_OFFSET, ballY + SHADOW_OFFSET, BALL_SIZE, BALL_SIZE);

        // Draw the ball
        g.setColor(Color.RED);
        drawOval(g, ballX, ballY, BALL_SIZE, BALL_SIZE);
    }

    private void drawOval(Graphics g, int x, int y, int width, int height) {
        g.fillOval(x, y, (int) (width * ovalScale), height);
    }

    private void moveBall() {
        ballX = 50;
        ballY = 50;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BoxDrawer frame = new BoxDrawer();
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