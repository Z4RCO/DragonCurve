package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Timer;

public class Main {
    private static double scale = 1;
    public static final Object lock = new Object();
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dragon Curve");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        DragonCurve dc = new DragonCurve(new Point(100, 100));
        JPanel panel = getjPanel(dc);

        frame.add(panel);
        frame.setVisible(true);
        Scanner s = new Scanner(System.in);
        while(true){
            dc.expand();
            panel.repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static JPanel getjPanel(DragonCurve dc) {
        Set<Segment> drawn = new HashSet<>();
        return new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                synchronized (lock){
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    scale /= Math.sqrt(2);

                    g2d.scale(scale, scale);

                    int centerX = getWidth() / 2;
                    int centerY = getHeight() / 2;
                    int scaledCenterX = (int) (centerX / scale);
                    int scaledCenterY = (int) (centerY / scale);
                    g2d.translate(scaledCenterX - centerX, scaledCenterY - centerY);
                    g2d.translate(400, 400);

                    g2d.setStroke(new BasicStroke(3));
                    for(Segment s : dc.getCurve()) {
                        g2d.drawLine(s.start().x(), s.start().y(), s.end().x(), s.end().y());
                    }
                }

            }
        };
    }
}