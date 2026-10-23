/*
 * Wendy Bronson
 * October 22, 2026
 * CSD 420 - Module 1.1 Discussion
 * JavaFX Circle/Arc and Color
 */

package com.mycompany.bronson_module_1_1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class JavaFXShapeDemo extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Create a pane to hold the shapes.
        Pane pane = new Pane();

        // Create a circle at (150, 100)
        // with a radius of 60 pixels.
        Circle circle = new Circle(150, 100, 60);

        // Fill the circle with yellow
        // and give it a black outline.
        circle.setFill(Color.YELLOW);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(3);

        // Create an arc below the circle.
        // Parameters: centerX, centerY,
        // radiusX, radiusY, startAngle, length.
        Arc arc = new Arc(150, 210, 70, 40, 180, 180);

        // Display only the arc's curved line.
        arc.setType(ArcType.OPEN);
        arc.setFill(Color.TRANSPARENT);
        arc.setStroke(Color.BLUE);
        arc.setStrokeWidth(4);

        // Add both shapes to the pane.
        pane.getChildren().addAll(circle, arc);

        // Create the scene and window.
        Scene scene = new Scene(pane, 300, 300);

        primaryStage.setTitle("JavaFX Shapes and Colors");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
