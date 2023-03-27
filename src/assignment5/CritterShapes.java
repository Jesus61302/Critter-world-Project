/*
 * EE422C Project 5 submission by
 * Jesus Hernandez
 * jh69848
 * 17155
 * Jaime Sanchez
 * js96757
 * 17160
 * Slip days used: 0
 * Spring 2023
 */
package assignment5;

import javafx.scene.shape.*;

public class CritterShapes {
    public static Shape getCircle(Double length, Double width, javafx.scene.paint.Color color, javafx.scene.paint.Color outline){
        Circle circle = new Circle(length/2);
        circle.setFill(color);
        circle.setStroke(outline);
        return circle;
    }
    public static Shape getSquare(Double length, Double width, javafx.scene.paint.Color color, javafx.scene.paint.Color outline){
        Rectangle square = new Rectangle(length,length,length,length);
        square.setFill(color);
        square.setStroke(outline);
        return square;
    }
    public static Shape getTriangle(Double length, Double width, javafx.scene.paint.Color color, javafx.scene.paint.Color outline){
        Polygon triangle = new Polygon();

        triangle.getPoints().addAll(new Double[]{
                0.0,0.0,
                length-1,0.0,
                length/2,width-1
        });

        triangle.setFill(color);
        triangle.setStroke(outline);



        return triangle;


    }
    public static Shape getDiamond(Double length, Double width, javafx.scene.paint.Color color, javafx.scene.paint.Color outline){
        Polygon diamond = new Polygon();

        double halfWidth = width / 2;
        double halfHeight = length / 2;
        double x1 = 0;
        double y1 = halfHeight;
        double x2 = halfWidth;
        double y2 = 0;
        double x3 = width;
        double y3 = halfHeight;
        double x4 = halfWidth;
        double y4 = length;

        // add the points to the polygon in clockwise order
        diamond.getPoints().addAll(
                x1, y1,
                x2, y2,
                x3, y3,
                x4, y4
        );
        diamond.setFill(color);
        diamond.setStroke(outline);



        return diamond;

    }

    public static Shape getStar(Double length, Double width, javafx.scene.paint.Color color, javafx.scene.paint.Color outline){


//        Polygon star = new Polygon();
        Path star = new Path();
        star.getElements().addAll(new MoveTo((.3)*width, 0),
                new LineTo(0, (.3)*length),
                new LineTo((.6)*width, (.3)*length),
                new ClosePath(),
                new MoveTo(0, (.1)*length),
                new LineTo((.6)*width, (.1)*length),
                new LineTo((.3)*width, (.4)*length),
                new ClosePath());

        star.setFill(color);
        star.setStroke(outline);

        return star;
    }

    public static Shape  getShape(Critter.CritterShape shape,Double length, Double width,
                            javafx.scene.paint.Color color, javafx.scene.paint.Color outline ) throws InvalidCritterException {
        String shapeStr = shape.toString().toLowerCase();
        if ( shapeStr.equals("circle")){
            return getCircle(length, width, color, outline);
        } else if (shapeStr.equals("square")) {
            return getSquare(length, width, color, outline);
        } else if (shapeStr.equals("triangle")) {
            return getTriangle(length, width, color, outline);
        } else if (shapeStr.equals("diamond")) {
            return getDiamond(length, width, color, outline);
        } else if (shapeStr.equals("star")) {
            return getStar(length, width, color, outline);
        }else {
            throw new  InvalidCritterException(shapeStr);
        }
    }

}
