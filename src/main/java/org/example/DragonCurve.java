package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DragonCurve extends JPanel{
    private final ArrayList<Segment> curve;
    private Point last;

    public DragonCurve(Point start){

        curve = new ArrayList<>();
        Segment first = new Segment(start, new Point(start.x(), start.y() + 500));
        curve.add(first);
        last = first.end();
    }


    public void expand(){
        ArrayList<Segment> newCurve = new ArrayList<>();
        for (Segment s : curve){
            Segment s1 = s.rotate(last);
            newCurve.add(s);
            newCurve.add(s1);

        }
        curve.clear();
        curve.addAll(newCurve);

        last = newCurve.getFirst().rotate(last).start();
    }

    public ArrayList<Segment> getCurve(){
        return curve;
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        java.awt.Graphics2D g2d = (java.awt.Graphics2D) g;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        g2d.translate(centerX, centerY);
        g2d.scale(1, -1);
        g2d.setStroke(new java.awt.BasicStroke(3));
        for(Segment s : curve) {
            g2d.drawLine(s.start().x(), s.start().y(), s.end().x(), s.end().y());
        }
    }



//    private BufferedImage export(){
//        getGraphics().clone();
//    }



    @Override
    public String toString() {
        return Arrays.toString(curve.toArray());
    }
}
