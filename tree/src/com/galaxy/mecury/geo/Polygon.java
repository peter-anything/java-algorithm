package com.galaxy.mecury.geo;

import java.util.ArrayList;
import java.util.List;

public class Polygon {
    public List<Point> points = new ArrayList<>();

    public Polygon() {}

    public void addPoint(Point point) {
        points.add(point);
    }

    public void setClosed() {
        points.add(points.get(0));
    }

    public Rectangle getRectanle() {
        return Rectangle.fromPolygon(this);
    }

    public Point getCenter() {
        Rectangle rectangle = getRectanle();

        return new Point((rectangle.topLeft.x + rectangle.topRight.x) / 2, (rectangle.topLeft.y + rectangle.bottomLeft.y) / 2);
    }

    public double area() {
        int point_num = points.size();
        if(point_num < 3) return 0.0;
        double s = points.get(0).y * (points.get(point_num-1).x - points.get(1).x);
        for(int i = 1; i < point_num; ++i)
            s += points.get(i).y * (points.get(i - 1).x - points.get((i+1) % point_num).x);
        return s / 2;
    }

    public boolean isPointInPolygon(Point p) {
        Rectangle rectangle = getRectanle();
        if (rectangle.topLeft.x < p.x  && p.x < rectangle.topRight.x && p.y > rectangle.bottomLeft.y && p.y < rectangle.topLeft.y) {
            //
            return true;
        }

        return false;
    }
}
