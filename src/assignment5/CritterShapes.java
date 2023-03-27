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
                length,0.0,
                length/2,width
        });

        triangle.setFill(color);
        triangle.setStroke(outline);



        return triangle;


    }
    public static Shape getDiamond(Double length, Double width, javafx.scene.paint.Color color, javafx.scene.paint.Color outline){
        Shape diamond = new Polygon();
        return diamond;
        //todo
    }

    public static Shape getStar(Double length, Double width, javafx.scene.paint.Color color, javafx.scene.paint.Color outline){
        //Shape star = new Polygon();
        //todo

        Path star = new Path();
        star.getElements().addAll(new MoveTo(30, 0),
                new LineTo(0, 30),
                new LineTo(60, 30),
                new ClosePath(),
                new MoveTo(0, 10),
                new LineTo(60, 10),
                new LineTo(30, 40),
                new ClosePath());


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
        } else if (shapeStr.equals("diamonmd")) {
            return getDiamond(length, width, color, outline);
        } else if (shapeStr.equals("star")) {
            return getStar(length, width, color, outline);
        }else {
            throw new  InvalidCritterException(shapeStr);
        }
    }

}
