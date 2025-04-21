package com.example.topclean.Customer;

import com.example.topclean.AppendableObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.Random;

public class WriteAreview
{
    @javafx.fxml.FXML
    private TextArea revComment;
    @javafx.fxml.FXML
    private Label ReviewStatus;
    @javafx.fxml.FXML
    private TextField revService;
    @javafx.fxml.FXML
    private TextArea revDescription;

    private static final String REVIEWS_FILE = "reviews.bin";
    private int customerId; // To store the logged in customer s ID

    @javafx.fxml.FXML
    public void initialize() {
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
        System.out.println("Customer ID received in WriteAreview: " + customerId);
    }

    @javafx.fxml.FXML
    public void AddReviewbtnOnAction(ActionEvent actionEvent) {
        String service = revService.getText().trim();
        String description = revDescription.getText().trim();
        String comment = revComment.getText().trim();
        LocalDate reviewDate = LocalDate.now();

        if (service.isEmpty() || description.isEmpty() || comment.isEmpty()) {
            ReviewStatus.setText("Please fill in all fields.");
            return;
        }

        // Generate a unique Review ID
        Random random = new Random();
        int reviewId = random.nextInt(1000000);

        Review newReview = new Review(reviewId, this.customerId, service, description, comment, reviewDate);

        if (saveReviewToFile(newReview)) {
            ReviewStatus.setText("Review added successfully!");
            clearInputFields();
        } else {
            ReviewStatus.setText("Failed to add review. Please try again.");
        }
    }

    private boolean saveReviewToFile(Review review) {
        File file = new File(REVIEWS_FILE);
        boolean append = file.exists();

        try (FileOutputStream fos = new FileOutputStream(file, append);
             ObjectOutputStream oos = append ? new AppendableObjectOutputStream(fos) : new ObjectOutputStream(fos)) {
            oos.writeObject(review);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void clearInputFields() {
        revService.clear();
        revDescription.clear();
        revComment.clear();
    }
}