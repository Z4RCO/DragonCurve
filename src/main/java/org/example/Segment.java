package org.example;

import java.util.Objects;

public class Segment {
    private final Point start;
    private final Point end;

    public Segment(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point start() {
        return start;
    }

    public Point end() {
        return end;
    }

    public Segment rotate(Point center) {
        // Translate points to origin relative to the center
        int translatedStartX = start.x() - center.x();
        int translatedStartY = start.y() - center.y();
        int translatedEndX = end.x() - center.x();
        int translatedEndY = end.y() - center.y();

        // Perform the rotation
        int rotatedStartX = -translatedStartY;
        int rotatedStartY = translatedStartX;
        int rotatedEndX = -translatedEndY;
        int rotatedEndY = translatedEndX;

        // Translate points back
        Point newStart = new Point(rotatedStartX + center.x(), rotatedStartY + center.y());
        Point newEnd = new Point(rotatedEndX + center.x(), rotatedEndY + center.y());

        // Return the new segment
        return new Segment(newStart, newEnd);
    }

    @Override
    public String toString() {
        return "[" + start + ", " + end + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segment segment = (Segment) o;
        return Objects.equals(start, segment.start) && Objects.equals(end, segment.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
