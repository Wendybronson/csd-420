/*
 * Wendy Bronson
 * October 25, 2026
 * CSD 420 - Module 1.3 Programming Assignment
 * Random Playing Cards Using JavaFX
 */

package com.mycompany.bronson_module_1_3_cards;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class CardRefreshApp extends Application {

    private final ImageView[] cardViews = new ImageView[4];

    @Override
    public void start(Stage primaryStage) {

        // Create a row to display four playing cards.
        HBox cardBox = new HBox(15);
        cardBox.setAlignment(Pos.CENTER);

        // Create four image views for the cards.
        for (int i = 0; i < 4; i++) {
            cardViews[i] = new ImageView();
            cardViews[i].setFitWidth(100);
            cardViews[i].setFitHeight(145);
            cardViews[i].setPreserveRatio(true);

            cardBox.getChildren().add(cardViews[i]);
        }

        // Create the Refresh button.
        Button refreshButton = new Button("Refresh");

        // Lambda expression runs when the button is clicked.
        refreshButton.setOnAction(event -> displayCards());

        // Arrange the cards and button vertically.
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(cardBox, refreshButton);

        // Display four cards when the program starts.
        displayCards();

        // Create and display the application window.
        Scene scene = new Scene(root, 500, 280);

        primaryStage.setTitle("Module 1.3 - Random Playing Cards");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Select four different cards from the deck.
    private void displayCards() {

        ArrayList<Integer> deck = new ArrayList<>();

        // Add all 52 card numbers to the deck.
        for (int i = 1; i <= 52; i++) {
            deck.add(i);
        }

        // Shuffle to randomly change the card order.
        Collections.shuffle(deck);

        // Display the first four cards after shuffling.
        for (int i = 0; i < 4; i++) {

            int cardNumber = deck.get(i);

            String imagePath = "/cards/" + cardNumber + ".png";

            // Load the card image from project resources.
            java.net.URL imageURL =
                    getClass().getResource(imagePath);

            if (imageURL == null) {
                throw new IllegalStateException(
                    "Missing card image: " + imagePath);
            }

            Image cardImage = new Image(
                    imageURL.toExternalForm());

            cardViews[i].setImage(cardImage);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
