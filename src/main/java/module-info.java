

module com.example.simulatingoperationsofanewspaper {
    requires javafx.controls;
    requires javafx.fxml;

    // Allow FXML to access these packages
    opens com.example.top_clean to javafx.fxml;
    opens com.example.top_clean.AdministrationOfficer to javafx.fxml;

    // Export main package (optional)
    exports com.example.top_clean;
}

