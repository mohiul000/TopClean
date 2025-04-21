package com.example.topclean.Customer;

import java.io.Serializable;
import java.time.LocalDate;

public class Review implements Serializable {
    private int reviewId;
    private int customerId;
    private String service;
    private String description;
    private String comment;
    private LocalDate reviewDate;

    public Review(int reviewId, int customerId, String service, String description, String comment, LocalDate reviewDate) {
        this.reviewId = reviewId;
        this.customerId = customerId;
        this.service = service;
        this.description = description;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    // Getters and setters for all fields

    public int getReviewId() {
        return reviewId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getService() {
        return service;
    }

    public String getDescription() {
        return description;
    }

    public String getComment() {
        return comment;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }


}