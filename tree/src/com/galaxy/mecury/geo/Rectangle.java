package com.galaxy.mecury.geo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Rectangle {
    public Point topLeft;
    public Point topRight;
    public Point bottomRight;
    public Point bottomLeft;

    public static Rectangle fromPolygon(Polygon polygon) {
        List<Point> pointList = polygon.points;
        List<Double> xs = new ArrayList<>();
        List<Double> ys = new ArrayList<>();
        pointList.stream().forEach(p -> {
            xs.add(p.x);
            ys.add(p.y);
        });
        Collections.sort(xs);
        Collections.sort(ys);
        double maxX = xs.get(xs.size() - 1);
        double minX = xs.get(0);
        double maxY = ys.get(ys.size() - 1);
        double minY = ys.get(0);
        Rectangle rectangle = new Rectangle();
        rectangle.topLeft = new Point(minX, maxY);
        rectangle.topRight = new Point(maxX, maxY);
        rectangle.bottomRight = new Point(maxX, minY);
        rectangle.bottomLeft = new Point(minX, minY);
        return rectangle;
    }
}
