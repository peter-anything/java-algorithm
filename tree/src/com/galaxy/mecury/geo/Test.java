package com.galaxy.mecury.geo;

public class Test {
    public static void main(String[] args) {
        Point p1 = new Point(0, 1);
        Point p2 = new Point(0, 0);
        Point p3 = new Point(1, -1);
        Point p4 = new Point(2, 0);
        Point p5 = new Point(2, 2);
        Polygon polygon = new Polygon();
        polygon.addPoint(p1);
        polygon.addPoint(p2);
        polygon.addPoint(p3);
        polygon.addPoint(p4);
        polygon.addPoint(p5);
        polygon.setClosed();

        System.out.println(polygon.isPointInPolygon(new Point(0.5, -0.5)));

        System.out.println(Line.getCrossLine(new Line(p1, p5), new Line(p4, p5)));
    }
}
