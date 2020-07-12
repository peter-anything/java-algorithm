package com.galaxy.mecury.geo;

public class Line {
    public Point begin;
    public Point end;
    public Line(Point begin, Point end) {
        this.begin = begin;
        this.end = end;
    }

    public static Point getCrossLine(Line line1, Line line2) {
        double a1 = line1.end.y - line1.begin.y;
        double b1 =  line1.begin.x - line1.end.x;
        double c1 = line1.begin.x * line1.end.y - line1.end.x * line1.begin.y;
        double a2 = line2.end.y - line2.begin.y;
        double b2 = line2.begin.x - line2.end.x;
        double c2 = line2.begin.x * line2.end.y - line2.end.x * line2.begin.y;
        double det= a1*b2 - a2*b1;

        if(det == 0) return null;

        Point p = new Point((c1 * b2 - c2 * b1) / det, (a1 * c2 - a2 * c1) / det);

        return p;
    }
}
