package org.example.draw;


import javax.swing.*;
import java.awt.*;

public class BoxDrawer extends JFrame {
    private JTextField lengthField;
    private JTextField widthField;
    private DrawPanel drawPanel;

    public BoxDrawer(){
        setTitle("Box Drawer");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        inputPanel.add(new JLabel("Length:"));
        lengthField = new JTextField();
        inputPanel.add(lengthField);

        inputPanel.add(new JLabel("Width:"));
        widthField = new JTextField();
        inputPanel.add(widthField);

        JButton drawButton = new JButton("Draw");
        drawButton.addActionListener(e -> {
            int length = Integer.parseInt(lengthField.getText());
            int width = Integer.parseInt(widthField.getText());
            drawPanel.setDimensions(length, width);
            drawPanel.repaint();
        });
        inputPanel.add(drawButton);

        add(inputPanel, BorderLayout.NORTH);

        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);
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

    private class DrawPanel extends JPanel {
        private int length;
        private int width;
        public void setDimensions(int length, int width){
            this.length = length;
            this.width = width;
        }
        @Override
        protected void paintComponent(Graphics g){
           super.paintComponent(g);
           g.drawRect(50,50,width,length);
        }
    }
}
